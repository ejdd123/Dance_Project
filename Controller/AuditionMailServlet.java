package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import DTO.auditionMailDTO;
import DTO.memberDTO;

/**
 * Servlet implementation class AuditionMailServlet
 */
@WebServlet("/AuditionMailServlet/*")
public class AuditionMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_PATH = "C:\\workspace\\Dance_Project\\WebContent\\video";

    public AuditionMailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 한글 깨짐 방지
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// url 값으로 기능 구분
		String url = request.getRequestURL().toString();

		// servlet에서 script 사용할 때
		PrintWriter writer = response.getWriter();

		auditionDAO dao = new auditionDAO();
		
		// 지원하기 email 전송
		if(url.contains("sendMail.do")) {
			int au_id = Integer.parseInt(request.getParameter("au_id"));
			String au_title = request.getParameter("au_title");
			String title = "Let's Dance 지원 메일 : [ " + request.getParameter("au_title") + " ]";
			String au_email = request.getParameter("au_email");
			
			File uploadDir = new File(UPLOAD_PATH);
			
			// 파일 저장 폴더가 없으면 폴더 생성하기
			if(!uploadDir.isDirectory()) {
				System.out.println("파일 생성");
				uploadDir.mkdir();
			}
			
			// 오디션 지원 영상(파일) 첨부를 위해 MultipartRequest 사용 - 기존 request 객체의 역할을 대신 수행함
			MultipartRequest multi = new MultipartRequest( request
																						   , UPLOAD_PATH																							// 업로드될 위치
																						   , 1024*1024*10																							// 파일 최대 사이즈 지정
																						   , "utf-8"																											// 인코딩 방식
																						   , new DefaultFileRenamePolicy()																	// 파일명에 대한 정책
																						   );
			
			String ap_name = multi.getParameter("ap_name");
			int ap_age = Integer.parseInt(multi.getParameter("ap_age"));
			String ap_field = multi.getParameter("ap_field");
			String ap_content = multi.getParameter("ap_content");
			String ap_email = multi.getParameter("ap_email");
			String ap_video = multi.getParameter("ap_video");
			
			auditionMailDTO dto = new auditionMailDTO();
			dto.setAp_name(ap_name);
			dto.setAp_age(ap_age);
			dto.setAp_field(ap_field);
			dto.setAp_content(ap_content);
			dto.setAp_email(ap_email);
			dto.setAp_video(ap_video);
			
			dto.setAu_id(au_id);
			dto.setAu_title(au_title);
			
			// 로그인 정보
			HttpSession loginsession = request.getSession();
			memberDTO member = (memberDTO) loginsession.getAttribute("loginMember");
			String me_id = member.getMe_id();
			dto.setMe_id(me_id);
						
			System.out.println("au_id: " + au_id + "au_title: " + au_title + " / au_email: " + au_email 
									+ " / ap_name: " +ap_name+ " / ap_age: " + ap_age + " / ap_field: " + ap_field+ " / ap_content: " + ap_content + " / ap_email: " + ap_email + " / ap_video: " + ap_video);
			
			// SMTP 정보를 담을 객체
			Properties pr = new Properties();
			
			// SMTP 기본 정보 - 구글
			pr.put("mail.smtp.host","smtp.gmail.com");			// 구글
			pr.put("mail.smtp.port", "465");								// TLS의 포트번호는 587이며 SSL의 포트번호는 465이다
			pr.put("mail.smtp.starttls.enable", "true");				// gmail은 무조건 true로 고정
			pr.put("mail.smtp.auth", "true");								// gmail은 무조건 true로 고정
			pr.put("mail.smtp.debug", "true");
			pr.put("mail.smtp.socketFactory.port", "465");
			pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			// soket 문제와 protocol 문제 해결 - 없어도 작동은 잘됨 (서버에서 테스트 완료)
			pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			pr.put("mail.smtp.socketFactory.fallback", "false");
			pr.put("mail.smtp.ssl.protocols", "TLSv1.2");

			// email 전송
			try {
				// 메일 인증
				Authenticator auth = new SMTPAuthenticator();

				Session session = Session.getInstance(pr, auth);
				session.setDebug(true);

				MimeMessage msg = new MimeMessage(session);		// 메일의 내용을 담을 객체
				Address toAddr = new InternetAddress(au_email);		// 받는 사람 email 주소
				/*
					Address fromAddr = new InternetAddress(ap_email);	// 보내는 사람 email 주소
					msg.setFrom(fromAddr);		// 보내는 사람
				 */
				
				msg.setSubject(title);	//	제목
				// SMTP 인증을 하려면 로그인 할 수 있는 계정과 패스워드가 필요하기 때문에, 외부에서 메일링 리스트를 발신 주소로 설정할 수 없다
				// msg.setFrom(new InternetAddress(ap_email, ap_name, "UTF-8"));	// 보내는 사람(이메일 주소, 이름, 인코딩 타입)
				msg.setFrom(new InternetAddress("ej.lets.dance@gmail.com", "Let's Dance", "UTF-8"));	// 보내는 사람(이메일 주소, 이름, 인코딩 타입)
				msg.addRecipient(Message.RecipientType.TO, toAddr);					// 받는 사람
				msg.setContent("<table border=1> <colgroup><col width='150'><col width='200'></colgroup>"
										 + "<tr><td>지원자명 (이메일 주소) </td><td>" + ap_name +" (" + ap_email + ") </td></tr>"
									 	 + "<tr><td>지원자 나이 </td><td>" + ap_age +"</td></tr>"
									 	 + "<tr><td>지원 분야 </td><td>" + ap_field +"</td></tr>"
									 	 + "<tr><td>지원 내용 </td><td><pre>" + ap_content +"</pre></td></tr></table>", "text/html; charset=EUC-KR");		// 메일 내용, 인코딩 타입

				// System.out.println("제목 : " + title + " / 보내는 사람 : "  + ap_email+ " / 받는 사람 :  " + toAddr + " / 지원자명 : " + ap_name + " / 지원자 나이 : " + ap_age +  " / 지원 분야 : " + ap_field +  " / 내용 : " + ap_content);
				// System.out.println("msg : " + msg);
				
				// 이메일 전송
				Transport.send(msg);

				// 이메일 전송 성공하면 지원자 정보 insert
				dao.insertApplicant(dto);
				System.out.println("sendMail.do : " + dto);
				
			} // 예외 처리
			  catch (AddressException ae) {
				ae.printStackTrace();
				System.out.println("AddressException : " + ae.getMessage());
			} catch (MessagingException me) {
				me.printStackTrace();
				System.out.println("MessagingException : " + me.getMessage());
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Exception : " + e.getMessage());
				
				// 오류 발생 시 이전 페이지로 돌아간다
				writer.println("<script> alert('메일 전송 실패..');  window.history.back(); </script>");
				writer.close();
			}
			
			writer.println("<script> alert('메일 전송 성공!!');  window.close(); </script>");
			writer.close();
		}
		
		// 지원하기 버튼 클릭 시 팝업창 오픈할 것인지 체크
		else if(url.contains("checkapplyforAudition.do")) {
			int au_id = Integer.parseInt(request.getParameter("au_id"));
			String au_title = request.getParameter("au_title");
			String au_email = request.getParameter("au_email");
			
			// writer.println("<script> alert( "+au_id+" + \" -  \" +'" + au_email + "' ); </script>");
			
			// 오디션 지원하는 경우 팝업창 띄우기
			writer.println("<script>var check = confirm('지원하시겠습니까?');"
								+ "if (check == true) { "
								+ "window.open(' "+ request.getContextPath() +"/AuditionMailServlet/applyforAuditionPopup.do?au_id="+au_id+"&au_title="+au_title+ "&au_email="+au_email +" ', '지원자 페이지', 'width=700, height=700 left=500 top=100'); "
								+ "window.history.back(); } "
								// 취소 시 이전 페이지로 돌아가기
								+ "else { window.history.back(); } </script>");
		}
		
		// 오디션 지원 팝업창 오픈
		else if(url.contains("applyforAuditionPopup.do")) {
			int au_id = Integer.parseInt(request.getParameter("au_id"));
			String au_title = request.getParameter("au_title");
			String au_email = request.getParameter("au_email");
			
			auditionDTO dto = new auditionDTO();
			dto.setAu_id(au_id);
			dto.setAu_title(au_title);
			dto.setAu_email(au_email);
			
			// id값 이용하여 전체 데이터 조회
			auditionDTO ap_list = dao.selectApplicant(dto);
			request.setAttribute("ap_list", ap_list);
			// System.out.println("ap_list : " + ap_list);

			// 로그인 정보
			HttpSession session = request.getSession();
			memberDTO member = (memberDTO) session.getAttribute("loginMember");
			
			// 디스패처 객체로 view(jsp) page를 담아 요청한다.
			// 클라이언트가 요청하면서 전송한 데이터를 그대로 유지할 수 있다.
			String page = "/audition/apply_for_audition.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			// 인증이 완료되면 포워딩을 한다.
			rd.forward(request, response);
						
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
