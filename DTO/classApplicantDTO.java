package DTO;

import java.util.Date;

// 클래스 신청자
public class classApplicantDTO {
	int cl_ap_id;				// 클래스 신청자 시리얼 넘버
	String cl_ap_name;		// 클래스 신청자 이름
	Date cl_ap_date;			// 클래스 신청 시간
	
	String me_id;				// 클래스 신청자의 회원 아이디
	
	int sc_id;						// 클래스 시리얼 넘버
	String sc_date;			// 클래스 날짜
	
	public int getCl_ap_id() {
		return cl_ap_id;
	}
	public void setCl_ap_id(int cl_ap_id) {
		this.cl_ap_id = cl_ap_id;
	}
	public String getCl_ap_name() {
		return cl_ap_name;
	}
	public void setCl_ap_name(String cl_ap_name) {
		this.cl_ap_name = cl_ap_name;
	}
	public Date getCl_ap_date() {
		return cl_ap_date;
	}
	public void setCl_ap_date(Date cl_ap_date) {
		this.cl_ap_date = cl_ap_date;
	}
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
	public int getSc_id() {
		return sc_id;
	}
	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}
	public String getSc_date() {
		return sc_date;
	}
	public void setSc_date(String sc_date) {
		this.sc_date = sc_date;
	}
	
	@Override
	public String toString() {
		return "classApplicantDTO [cl_ap_id=" + cl_ap_id + ", cl_ap_name=" + cl_ap_name + ", cl_ap_date=" + cl_ap_date
				+ ", me_id=" + me_id + ", sc_id=" + sc_id + ", sc_date=" + sc_date + "]";
	}
	
}
