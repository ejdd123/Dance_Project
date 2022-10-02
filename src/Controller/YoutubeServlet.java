package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.youtubeDAO;
import DTO.memberDTO;
import DTO.youtubeDTO;
import DTO.youtubeMsgDTO;

/**
 * Servlet implementation class YoutubeServlet
 */
// jsp에서 받아옴
@WebServlet("/YoutubeServlet/*")
public class YoutubeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public YoutubeServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();
		
		youtubeDAO dao = new youtubeDAO();
		
		// 유튜브 영상 업로드(추가)
		if(url.contains("uploadVideo.do")) {
			// jsp에서 받아온 값 넣기
			String singer = request.getParameter("singer");
			String title = request.getParameter("title");
			String yt_url = request.getParameter("yt_url");
			String yt_password = request.getParameter("yt_password");
			
			youtubeDTO dto = new youtubeDTO();
			dto.setSinger(singer);
			dto.setTitle(title);
			dto.setYt_url(yt_url);
			dto.setYt_password(yt_password);

			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();
			dto.setMe_id(me_id);
			
			dao.uploadVideo(dto);
			
			// 목록으로 바로가기
			String page = request.getContextPath()+"/YoutubeServlet/main_youtube.do";
			response.sendRedirect(page);
		}
		
		// 유튜브 리스트 조회 - yt_list에 video_list에서 가져온 값 넣기
		else if(url.contains("main_youtube.do")) {
			List<youtubeDTO> video_list = dao.selectVideoList();
			request.setAttribute("yt_list", video_list);
			
			// System.out.println("video_list 값 : " + video_list);

			// 디스패처 객체로 view(jsp) page를 담아 요청한다
			// 클라이언트가 요청하면서 전송한 데이터를 그대로 유지할 수 있다
			String page = "/youtube/main_youtube.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			// 인증이 완료되면 포워딩을 한다
			rd.forward(request, response);
		}
		
		// 삭제할 유튜브 영상 비밀번호 체크
		else if(url.contains("checkDeleteVideo.do")) {			
			int yt_id = Integer.parseInt(request.getParameter("yt_id"));
			String yt_password = request.getParameter("yt_password");

			youtubeDTO dto = new youtubeDTO();
			dto.setYt_id(yt_id);
			dto.setYt_password(yt_password);
			
			// Servelt에서 alert창을 띄우기
			// 참고: https://blythe.tistory.com/12
			// 해당 yt_id를 가진 유튜브 영상의 비밀번호를 체크하여 비밀번호가 맞으면 삭제, 틀리면 이전 페이지로 돌아간다
			// 주의 : yt_password은 숫자를 문자열로 받았기 때문에 ' ' 안에 넣어야 함 (그렇지 않으면 다른 숫자로 인식할 수 있음 ex) 0700 의 경우 : 448로 인식했었음)
			PrintWriter writer = response.getWriter();
			writer.println("<script> var del_pw = prompt('비밀번호를 입력하세요'); "
								+ "if (del_pw == '" + yt_password + "') { location.href=' " + request.getContextPath() + "/YoutubeServlet/deleteVideo.do?yt_id=" + yt_id + " '; } "
								+ "else { alert('비밀번호를 다시 확인해주세요. \\n\\n입력한 비밀번호 : ' + del_pw ); history.back(); }; </script>");
								/*+ "else { alert('비밀번호를 다시 확인해주세요. ' + del_pw + ' / ' + '" +  yt_password + "' ); history.back(); }; </script>");*/
			writer.close();
			
			/*
			// (안됨) 목록으로 바로가기
			// (사유) writer.close();를 하면 sendRedirect를 할 수 없게된다. 참고: https://blythe.tistory.com/12
			String page = request.getContextPath()+"/YoutubeServlet/main_youtube.do";
			response.sendRedirect(page);
			*/
		}
		
		// 유튜브 영상 삭제
		else if(url.contains("deleteVideo.do")) {			
			// checkDeleteVideo.do 에서 가져온 yt_id 값을 del_yt_id에 담기
			int del_yt_id = Integer.parseInt(request.getParameter("yt_id"));
						
			youtubeDTO dto = new youtubeDTO();
			dto.setYt_id(del_yt_id);

			System.out.println("유튜브 영상 삭제합니다. del id : " + dto.getYt_id());
			dao.deleteVideo(dto);
			
			// 목록으로 바로가기 
			String page = request.getContextPath()+"/YoutubeServlet/main_youtube.do";
			response.sendRedirect(page);
		}
		
		// 메시지 보낼 것인지 확인
		else if(url.contains("confirmMessage.do")) {
			int yt_id = Integer.parseInt(request.getParameter("yt_id"));
			String writer_id = request.getParameter("me_id");
			
			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			
			// 회원이 아닌 경우 로그인 진행
			if(member == null || member.equals("")) {
				PrintWriter writer = response.getWriter();
				writer.println("<script> alert('로그인 후 이용해주세요.'); window.history.back(); </script>");
				writer.close();
			}
			PrintWriter writer = response.getWriter();
			writer.println("<script> var send_msg = confirm('메시지를 보내시겠습니까?'); "
								+ "if (send_msg == true) { window.open('" + request.getContextPath() + "/youtube/send_message.jsp?yt_id="+yt_id+"&me_id="+writer_id+"' ,'메시지 보내기',  'width=500, height=500 left=600 top=150'); } "
								+ "window.history.back(); </script>");
			writer.close();
			
			/*
			String page = "/youtube/send_message.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			*/
		}

		// 메시지 보내기
		else if(url.contains("sendMessage.do")) {
			String from_msg = request.getParameter("from_msg");
			String to_msg = request.getParameter("to_msg");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			youtubeMsgDTO dto = new youtubeMsgDTO();
			dto.setFrom_msg(from_msg);
			dto.setTo_msg(to_msg);
			dto.setTitle(title);
			dto.setContent(content);
			
			dao.sendMessage(dto);
			// System.out.println(dto);
			
			PrintWriter writer = response.getWriter();
			writer.println("<script> alert('메시지 전송 완료'); window.close(); </script>");
			writer.close();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
