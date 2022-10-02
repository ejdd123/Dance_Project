package DTO;

import java.util.Date;

// 회원
public class memberDTO {
	String me_id;					// 회원 아이디
	String me_name;				// 회원 이름
	String me_password;		// 회원 비밀번호
	String me_email;				// 이메일
	Date me_date;					// 회원가입 시간
	String manage_at;			// 관리 권한
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	public String getMe_name() {
		return me_name;
	}
	public void setMe_name(String me_name) {
		this.me_name = me_name;
	}
	public String getMe_password() {
		return me_password;
	}
	public void setMe_password(String me_password) {
		this.me_password = me_password;
	}
	public String getMe_email() {
		return me_email;
	}
	public void setMe_email(String me_email) {
		this.me_email = me_email;
	}
	public Date getMe_date() {
		return me_date;
	}
	public void setMe_date(Date me_date) {
		this.me_date = me_date;
	}
	public String getManage_at() {
		return manage_at;
	}
	public void setManage_at(String manage_at) {
		this.manage_at = manage_at;
	}
	
	@Override
	public String toString() {
		return "memberDTO [me_id=" + me_id + ", me_name=" + me_name + ", me_password=" + me_password + ", me_email="
				+ me_email + ", me_date=" + me_date + ", manage_at=" + manage_at + "]";
	}
	
}
