package DTO;

import java.util.Date;

// 유튜브 영상 - 메시지 보내기
public class youtubeMsgDTO {
	int msg_id;						// 메시지 시리얼 넘버
	String from_msg;			// 메시지 보내는 사람
	String to_msg;					// 메시지 받는 사람
	String title;						// 메시지 제목
	String content;				// 메시지 내용
	Date msg_date;				// 메시지 보낸 시간
	String check_at;				// 메시지 체크 여부
	
	public int getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}
	public String getFrom_msg() {
		return from_msg;
	}
	public void setFrom_msg(String from_msg) {
		this.from_msg = from_msg;
	}
	public String getTo_msg() {
		return to_msg;
	}
	public void setTo_msg(String to_msg) {
		this.to_msg = to_msg;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Date msg_date) {
		this.msg_date = msg_date;
	}
	public String getCheck_at() {
		return check_at;
	}
	public void setCheck_at(String check_at) {
		this.check_at = check_at;
	}
	
	@Override
	public String toString() {
		return "youtubeMsgDTO [msg_id=" + msg_id + ", from_msg=" + from_msg + ", to_msg=" + to_msg + ", title=" + title
				+ ", content=" + content + ", msg_date=" + msg_date + ", check_at=" + check_at + "]";
	}
	
}
