package Controller;

import java.nio.charset.Charset;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    
	protected PasswordAuthentication getPasswordAuthentication() {
		// AuditionMailServlet - sendMail.do 에서 구글 SMTP 사용했음
		String mail_id = "dmswls987@gmail.com";	// 구글 ID
		String mail_pw = "vxdnrqeptiguiozv";			// 구글 2단계 인증 - 앱 비밀번호 사용
		
		return new PasswordAuthentication(mail_id, mail_pw);
	}
}
