package DTO;

// 오디션 게시물
public class auditionDTO {
	int au_id;							// 오디션 게시물 시리얼 넘버
	String au_title;				// 오디션 제목
	String au_img;					// 오디션 이미지
	String au_content;			// 상세내용
	String au_email;				// 관계자 이메일
	String au_password;		// 비밀번호 - 게시물 보호용
	
	String me_id;					// 회원 아이디
	
	public int getAu_id() {
		return au_id;
	}
	public void setAu_id(int au_id) {
		this.au_id = au_id;
	}
	public String getAu_title() {
		return au_title;
	}
	public void setAu_title(String au_title) {
		this.au_title = au_title;
	}
	public String getAu_img() {
		return au_img;
	}
	public void setAu_img(String au_img) {
		this.au_img = au_img;
	}
	public String getAu_content() {
		return au_content;
	}
	public void setAu_content(String au_content) {
		this.au_content = au_content;
	}
	public String getAu_email() {
		return au_email;
	}
	public void setAu_email(String au_email) {
		this.au_email = au_email;
	}
	public String getAu_password() {
		return au_password;
	}
	public void setAu_password(String au_password) {
		this.au_password = au_password;
	}
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
	@Override
	public String toString() {
		return "auditionDTO [au_id=" + au_id + ", au_title=" + au_title + ", au_img=" + au_img + ", au_content="
				+ au_content + ", au_email=" + au_email + ", au_password=" + au_password + ", me_id=" + me_id + "]";
	}
	
}
