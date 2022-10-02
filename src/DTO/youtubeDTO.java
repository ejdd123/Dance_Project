package DTO;

// 유튜브 영상
public class youtubeDTO {
	int yt_id;						// 유튜브 영상 시리얼 넘버
	String singer;				// 가수(그룹)
	String title;					// 노래 제목
	String yt_url;				// 유튜브 영상 URL
	String yt_password;	// 비밀번호 - 게시물 보호용
	
	String me_id;				// 회원 아이디
	
	public int getYt_id() {
		return yt_id;
	}
	public void setYt_id(int yt_id) {
		this.yt_id = yt_id;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYt_url() {
		return yt_url;
	}
	public void setYt_url(String yt_url) {
		this.yt_url = yt_url;
	}	
	public String getYt_password() {
		return yt_password;
	}
	public void setYt_password(String yt_password) {
		this.yt_password = yt_password;
	}
	
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
	@Override
	public String toString() {
		return "youtubeDTO [yt_id=" + yt_id + ", singer=" + singer + ", title=" + title + ", yt_url=" + yt_url
				+ ", yt_password=" + yt_password + ", me_id=" + me_id + "]";
	}

}
