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

import DAO.scheduleDAO;
import DTO.classApplicantDTO;
import DTO.memberDTO;
import DTO.scheduleDTO;

/**
 * Servlet implementation class ScheduleServlet
 */
@WebServlet("/ScheduleServlet/*")
public class ScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScheduleServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		scheduleDAO dao = new scheduleDAO();
		
		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();
				
		// 스케줄 등록 팝업 - 팝업에서 바로 DB 저장
		if(url.contains("uploadClassPopup.do")) {
			String sc_singer = request.getParameter("sc_singer");
			String sc_title = request.getParameter("sc_title");
			String sc_teacher = request.getParameter("sc_teacher");
			String sc_img = request.getParameter("sc_img");
			String sc_date = request.getParameter("sc_date");
			int sc_num = Integer.parseInt(request.getParameter("sc_num"));
			
			scheduleDTO dto = new scheduleDTO();
			dto.setSc_singer(sc_singer);
			dto.setSc_title(sc_title);
			dto.setSc_teacher(sc_teacher);
			dto.setSc_img(sc_img);
			dto.setSc_date(sc_date);
			dto.setSc_num(sc_num);
			
			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();
			dto.setMe_id(me_id);
			
			dao.uploadClass(dto);
			// System.out.println("uploadClassPopup.do" + dto);
			
			// 등록 완료 후 창 닫기, 부모 창은 새로고침
			PrintWriter writer = response.getWriter();
			writer.println("<script> window.close(); opener.location.reload(); </script>");
			writer.close();
		}
		
		// 스케줄 조회 - sc_list에 video_list에서 가져온 값 넣기
		else if(url.contains("main_schedule.do")) {
			List<scheduleDTO> schedule_list = dao.selectScheduleList();
			request.setAttribute("sc_list", schedule_list);
			// System.out.println("main_schedule.do sc_list : " + schedule_list);
			
			String page = "/schedule/main_schedule.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		// 클래스 신청/삭제, 신청자 조회 체크
		else if(url.contains("checkClass.do")) {
			/*
			int sc_id = Integer.parseInt(request.getParameter("sc_id"));
			String sc_date = request.getParameter("sc_date");
			int sc_num = Integer.parseInt(request.getParameter("sc_num"));
			String me_id= request.getParameter("me_id");
			
			scheduleDTO dto = new scheduleDTO();
			dto.setSc_id(sc_id);
			dto.setSc_date(sc_date);
			dto.setSc_num(sc_num);
			dto.setMe_id(me_id);
			// System.out.println("checkClass.do : " + dto);
			 */
			
			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			
			String page = "/schedule/check_apply_for_class.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
		
		// 클래스 신청
		else if(url.contains("applyforClass.do")) {
			int sc_id = Integer.parseInt(request.getParameter("sc_id"));
			String sc_date = request.getParameter("sc_date");
			int sc_num = Integer.parseInt(request.getParameter("sc_num"));
			
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			
			// 회원이 아닌 경우 로그인 진행
			if(member == null || member.equals("")) {
				// System.out.println("로그인 필요");
				
				PrintWriter writer = response.getWriter();
				// self.close() : (클래스 신청/삭제, 신청자 조회) 버튼창 닫기
				writer.println("<script> self.close(); opener.open('"+ request.getContextPath() +"/member/login.jsp', '로그인 페이지', 'width=550, height=300'); </script>");
				writer.close();
			} 
			// 회원인 경우 클래스 신청
			else {
				// System.out.println("로그인 되어있음" + " / "+ member.getMe_id());
			
				PrintWriter writer = response.getWriter();
				// self.close() : (클래스 신청/삭제, 신청자 조회) 버튼창 닫기
				writer.println("<script> self.close(); opener.location.href='"+ request.getContextPath() + "/ScheduleServlet/complete_apply_for_class.do?sc_id="+ sc_id +"&sc_date="+ sc_date +"&sc_num="+sc_num+"';</script>");
				writer.close();
			}
		}
		
		// 클래스 삭제
		else if(url.contains("delete_class.do")) {
			int sc_id = Integer.parseInt(request.getParameter("sc_id"));
			
			/*
			scheduleDTO dto = new scheduleDTO();
			dto.setSc_id(sc_id);
			*/
			
			// 클래스 삭제
			dao.deleteClass(sc_id);
			// 클래스 신청자 정보 삭제
			dao.deleteClassApplicant(sc_id);
			System.out.println("delete_class id: " + sc_id);
			
			// 클래스 삭제 후 창 닫기, 부모 창은 새로고침
			PrintWriter writer = response.getWriter();
			// self.close() : (클래스 신청/삭제, 신청자 조회) 버튼창 닫기
			writer.println("<script> window.close(); opener.location.reload(); </script>");
			writer.close();
		}

		// 클래스 신청 - 클래스 신청자 정보 등록
		else if(url.contains("complete_apply_for_class.do")) {
			int sc_id = Integer.parseInt(request.getParameter("sc_id"));
			String sc_date = request.getParameter("sc_date");
			int sc_num = Integer.parseInt(request.getParameter("sc_num"));

			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			String me_id = member.getMe_id();
			String me_name = member.getMe_name();
			
			// 클래스 신청 시 동일 클래스 신청 불가 (이미 신청한 클래스는 신청 불가) - scheduleDAO 참고
			int check = dao.selectCheckId(me_id, sc_id);
			
			if(check == 0) {
				// 클래스 신청자 총 인원수 조회
				int total = dao.selectClassApplicantCnt(sc_id, sc_date);
				// System.out.println("Servlet total : " + total);

				// 인원 초과 시 클래스 신청 불가
				if(sc_num == total) {
					PrintWriter writer = response.getWriter();
					writer.println("<script> window.alert('수강인원 초과입니다.'); window.history.back(); </script>");
					writer.close();
				}
				else {
					classApplicantDTO dto = new classApplicantDTO();
					dto.setSc_id(sc_id);
					dto.setSc_date(sc_date);
					
					dto.setMe_id(me_id);
					dto.setCl_ap_name(me_name);
					
					// System.out.println("complete_apply_for_class : " + dto);
					dao.applyforClass(dto);
	
					PrintWriter writer = response.getWriter();
					writer.println("<script> alert('수강 신청되었습니다.'); window.history.back(); </script>");
					writer.close();
					/*
					String page = request.getContextPath() + "/ScheduleServlet/main_schedule.do";
					response.sendRedirect(page);
					*/
				}
			} else if(check == 1) {
				PrintWriter writer = response.getWriter();
				writer.println("<script> alert('이미 신청한 클래스입니다. (중복 신청 불가) '); window.history.back(); </script>");
				writer.close();
			}
		}
		
		// 클래스, 신청자 정보 조회
		else if(url.contains("selectClassApplicant.do")) {
			int sc_id = Integer.parseInt(request.getParameter("sc_id"));
			String sc_date = request.getParameter("sc_date");

			List<scheduleDTO> applicant_class_list = dao.selectClassList(sc_id, sc_date);
			request.setAttribute("class_list", applicant_class_list);
			// System.out.println("applicant_class_list : " + applicant_class_list);
			
			List<classApplicantDTO> applicant_list = dao.selectClassApplicant(sc_id, sc_date);
			request.setAttribute("class_applicant", applicant_list);
			// System.out.println("applicant_list : " + applicant_list);
			
			String page = "/schedule/class_applicant.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
