package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.scheduleDAO;
import DTO.memberDTO;
import DTO.scheduleDTO;
import DTO.youtubeMsgDTO;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet/*")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HomeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();

		HomeDAO dao = new HomeDAO();
		
		// 홈 화면 - 신청한 클래스 조회
		if(url.contains("home_class_list.do")) {
			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();

			List<scheduleDTO> class_list = 	dao.selectMyClass(me_id);
			request.setAttribute("my_class", class_list);
			// System.out.println("my_class : " + class_list);
			
			String page = "/home/home_class_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		// 홈 화면 - message 조회
		else if(url.contains("home_message.do")) {
			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();
			
			List<youtubeMsgDTO> message_list = dao.selectMyMessage(me_id);
			request.setAttribute("my_message", message_list);
			// System.out.println("my_message : " + message_list);
			
			String page = "/home/home_message.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
