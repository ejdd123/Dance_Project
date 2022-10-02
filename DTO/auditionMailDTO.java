package DTO;

import java.util.Date;

// 오디션 지원자
public class auditionMailDTO {
	int ap_id;						// 오디션 지원자 시리얼 넘버
	String ap_name;			// 지원자명
	int ap_age;					// 지원자 나이
	String ap_field;			// 지원 분야
	String ap_content;		// 지원 내용
	String ap_video;			// 지원 영상 url
	String ap_email;			// 지원자 메일
	Date ap_date;				// 지원 시간
	
	int au_id;						// 오디션 게시물 시리얼 넘버
	String au_title;			// 오디션 게시물 제목
	
	String me_id;				// 회원 아이디
	
	public int getAp_id() {
		return ap_id;
	}
	public void setAp_id(int ap_id) {
		this.ap_id = ap_id;
	}
	public String getAp_name() {
		return ap_name;
	}
	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}
	public int getAp_age() {
		return ap_age;
	}
	public void setAp_age(int ap_age) {
		this.ap_age = ap_age;
	}
	public String getAp_field() {
		return ap_field;
	}
	public void setAp_field(String ap_field) {
		this.ap_field = ap_field;
	}
	public String getAp_content() {
		return ap_content;
	}
	public void setAp_content(String ap_content) {
		this.ap_content = ap_content;
	}
	public String getAp_video() {
		return ap_video;
	}
	public void setAp_video(String ap_video) {
		this.ap_video = ap_video;
	}
	public String getAp_email() {
		return ap_email;
	}
	public void setAp_email(String ap_email) {
		this.ap_email = ap_email;
	}
	public Date getAp_date() {
		return ap_date;
	}
	public void setAp_date(Date ap_date) {
		this.ap_date = ap_date;
	}
	
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
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
	@Override
	public String toString() {
		return "auditionMailDTO [ap_id=" + ap_id + ", ap_name=" + ap_name + ", ap_age=" + ap_age + ", ap_field="
				+ ap_field + ", ap_content=" + ap_content + ", ap_video=" + ap_video + ", ap_email=" + ap_email
				+ ", ap_date=" + ap_date + ", au_id=" + au_id + ", au_title=" + au_title + ", me_id=" + me_id + "]";
	}

	
}
