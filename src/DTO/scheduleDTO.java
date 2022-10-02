package DTO;

// 스케줄
public class scheduleDTO {
	int sc_id;						// 스케줄 시리얼 넘버
	String sc_singer;			// 클래스 - 가수(그룹)
	String sc_title;				// 클래스 - 노래 제목
	String sc_teacher;		// 강사 이름
	String sc_img;				// 강사 사진(이미지)
	String sc_date;			// 클래스 날짜
	int sc_num;					// 수강 인원
	
	String me_id;				// 회원 아이디
	
	public String getSc_singer() {
		return sc_singer;
	}
	public void setSc_singer(String sc_singer) {
		this.sc_singer = sc_singer;
	}
	public int getSc_id() {
		return sc_id;
	}
	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}
	public String getSc_title() {
		return sc_title;
	}
	public void setSc_title(String sc_title) {
		this.sc_title = sc_title;
	}
	public String getSc_teacher() {
		return sc_teacher;
	}
	public void setSc_teacher(String sc_teacher) {
		this.sc_teacher = sc_teacher;
	}
	public String getSc_img() {
		return sc_img;
	}
	public void setSc_img(String sc_img) {
		this.sc_img = sc_img;
	}
	public String getSc_date() {
		return sc_date;
	}
	public void setSc_date(String sc_date) {
		this.sc_date = sc_date;
	}
	public int getSc_num() {
		return sc_num;
	}
	public void setSc_num(int sc_num) {
		this.sc_num = sc_num;
	}
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
	@Override
	public String toString() {
		return "scheduleDTO [sc_id=" + sc_id + ", sc_singer=" + sc_singer + ", sc_title=" + sc_title + ", sc_teacher="
				+ sc_teacher + ", sc_img=" + sc_img + ", sc_date=" + sc_date + ", sc_num=" + sc_num + ", me_id=" + me_id
				+ "]";
	}
	
}
