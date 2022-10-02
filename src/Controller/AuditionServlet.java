package Controller;

import java.io.File;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DAO.auditionDAO;
import DTO.auditionDTO;
import DTO.memberDTO;

/**
 * Servlet implementation class AuditionServlet
 */
//jsp에서 받아옴
@WebServlet("/AuditionServlet/*")
public class AuditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 업로드할 오디션 게시물의 이미지 저장 경로
	public static final String UPLOAD_PATH = "C:\\workspace\\Dance_Project\\WebContent\\images\\audition";

    public AuditionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();
		
		auditionDAO dao = new auditionDAO();

		// 오디션 게시물 등록
		if(url.contains("uploadAudition.do")) {
			// System.out.println("오디션 등록 Servlet");

			// 파일 저장 폴더가 없으면 폴더 생성하기
			File uploadDir = new File(UPLOAD_PATH);

			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			// 오디션 게시물 이미지(파일) 첨부를 위해 MultipartRequest 사용 - 기존 request 객체의 역할을 대신 수행함
			MultipartRequest multi = new MultipartRequest( request
																							, UPLOAD_PATH																								// 업로드될 위치
																							, 1024*1024*10																								// 파일 최대 사이즈 지정
																							, "utf-8"																											// 인코딩 방식
																							, new DefaultFileRenamePolicy()																	// 파일명에 대한 정책
																							);
			
			String au_title = multi.getParameter("au_title");
			String au_img = multi.getFilesystemName("au_img");
			
			/*
			// 이미지명 받아오기
			try {
				au_img = multi.getFilesystemName("au_img");
			} catch(Exception e){
				e.printStackTrace();
			}
			*/
			
			String au_content = multi.getParameter("au_content");
			String au_email = multi.getParameter("au_email");
			String au_password = multi.getParameter("au_password");
			
			auditionDTO dto = new auditionDTO();
			dto.setAu_title(au_title);
			dto.setAu_img(au_img);
			dto.setAu_content(au_content);
			dto.setAu_email(au_email);
			dto.setAu_password(au_password);

			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();
			dto.setMe_id(me_id);
			
			dao.uploadAudition(dto);

			// 목록으로 바로가기
			String page = request.getContextPath() + "/AuditionServlet/main_audition.do";
			response.sendRedirect(page);
		}

		// 오디션 게시물 리스트 조회 - au_list에 audition_list에서 가져온 값 넣기
		else if(url.contains("main_audition.do")) {
			List<auditionDTO> audition_list = dao.selectAuditionList();
			request.setAttribute("au_list", audition_list);

			// 디스패처 객체로 view(jsp) page를 담아 요청한다
			// 클라이언트가 요청하면서 전송한 데이터를 그대로 유지할 수 있다
			String page ="/audition/main_audition.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			// 인증이 완료되면 포워딩을 한다
			rd.forward(request, response);
		}
		
		// 삭제할 오디션 게시물 비밀번호 체크
		else if(url.contains("checkDeleteAudition.do")) {
			int au_id = Integer.parseInt(request.getParameter("au_id"));
			String au_password = request.getParameter("au_password");

			auditionDTO dto = new auditionDTO();
			dto.setAu_id(au_id);
			dto.setAu_password(au_password);
			
			// Servelt에서 alert창을 띄우기
			// 참고: https://blythe.tistory.com/12
			// 해당 au_id를 가진 오디션 게시물의 비밀번호를 체크하여 비밀번호가 맞으면 삭제, 틀리면 이전 페이지로 돌아간다
			// 주의 : au_password은 문자열이기 때문에 '' 안에 넣어야 함 (그렇지 않으면 다른 숫자로 인식할 수 있음 ex) 유튜브 영상 삭제 시 0700 입력한 경우 : 448로 인식했었음)
			PrintWriter writer = response.getWriter();
			writer.println("<script> var del_pw = prompt('비밀번호를 입력하세요'); "
								+ "if (del_pw == '" + au_password + "') { location.href=' " + request.getContextPath() + "/AuditionServlet/deleteAudition.do?au_id=" + au_id + " ' } "
								+ "else if (del_pw ==null) { window.history.back(); }"
								+ "else { alert('비밀번호를 다시 확인해주세요. \\n\\n입력한 비밀번호 : ' + del_pw); window.history.back(); } </script>");
			
		}
		
		// 오디션 게시물 삭제
		else if(url.contains("deleteAudition.do")) {			
			int au_id = Integer.parseInt(request.getParameter("au_id"));
			
			auditionDTO dto = new auditionDTO();
			dto.setAu_id(au_id);
			
			// System.out.println("del id : " + dto.getAu_id());
			dao.deleteAudition(dto);
			
			// 목록으로 바로가기
			String page = request.getContextPath() + "/AuditionServlet/main_audition.do";
			response.sendRedirect(page);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
