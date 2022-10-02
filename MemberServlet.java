package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.memberDAO;
import DTO.memberDTO;
import DTO.youtubeMsgDTO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet/*")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();

		memberDAO dao = new memberDAO();
		
		// 회원가입 - 아이디 중복 체크
		if(url.contains("joinIdCk.do")) {
			String me_id = request.getParameter("me_id");
			
			int check = dao.checkMemberId(me_id);
			// System.out.println(check);
			
			PrintWriter writer = response.getWriter();
			
			if (check == 0) {
				// window.close() : joinIdCk.do(현재 창) 닫기
				writer.println("<script> alert('사용 가능한 아이디 입니다.'); window.close(); opener.document.getElementById('idcheckAt').value='확인';</script>");
			} else {
				writer.println("<script> alert('사용 중인 아이디입니다.'); window.close(); opener.document.getElementById('me_id').value='';</script>");
			}
			writer.close();
		}
		
		// 회원가입
		else if(url.contains("joinMember.do")) {
			String me_id = request.getParameter("me_id");
			String me_name = request.getParameter("me_name");
			String me_password = request.getParameter("me_password");
			String me_email = request.getParameter("me_email");
			Date me_date = new Date();
			
			memberDTO dto = new memberDTO();
			dto.setMe_id(me_id);
			dto.setMe_name(me_name);
			dto.setMe_password(me_password);
			dto.setMe_email(me_email);
			dto.setMe_date(me_date);
			
			dao.insertMember(dto);
			// System.out.println("insertMember : " + dto);
			
			String page = request.getContextPath() + "/main.jsp";
			response.sendRedirect(page);
		}

		// 로그인
		else if(url.contains("login.do")) {
			String me_id = request.getParameter("me_id");
			String me_password = request.getParameter("me_password");

			memberDTO member = new memberDTO();
			member = dao.selectMember(me_id);
			// System.out.println("member.getMe_password() : " + member.getMe_password() + "\n" +"me_password : " + me_password );

			PrintWriter writer = response.getWriter();
			
			// 입력한 id로 찾은 회원이 없는 경우
			if(member == null) {
				writer.println("<script> alert('존재하지 않는 아이디입니다.'); window.history.back(); </script>");
				writer.close();
			}
			// 입력한 id로 찾은 회원이 있는 경우
			else {
				// 입력한 비밀번호가 회원 비밀번호와 같을 때
				if(member.getMe_password().equals(me_password)) {
					HttpSession session = request.getSession();
					session.setAttribute("loginMember", member);
	
					HomeDAO home = new HomeDAO();
					List<youtubeMsgDTO> message_list = home.selectMyMessage(me_id);
					request.setAttribute("my_message", message_list);
					// System.out.println("MemberServlet my_message : " + message_list);
					
					String page = "/main.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(page);
					rd.forward(request, response);
				}
				// 입력한 비밀번호가 회원 비밀번호와 다를 때
				else {
					writer.println("<script> alert('비밀번호가 다릅니다.'); window.history.back(); </script>");
					writer.close();
				}
			}
		}

		// 로그아웃
		else if(url.contains("logout.do")) {
			HttpSession session = request.getSession();
			session.invalidate();
			
			String page = request.getContextPath() + "/main.jsp";
			response.sendRedirect(page);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
