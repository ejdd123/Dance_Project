package DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DTO.auditionDTO;
import DTO.youtubeDTO;
import DTO.youtubeMsgDTO;
import mapper.MybatisManager;

public class youtubeDAO {

	// 유튜브 영상 업로드
	public void uploadVideo(youtubeDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Youtube.uploadVideo", dto);
		
		session.commit();
		session.close();
	}
	
	// 업로드한 유튜브 영상 조회
	public List<youtubeDTO> selectVideoList() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<youtubeDTO> d_list = session.selectList("Youtube.selectVideoList");
		
		// System.out.println("selectVideoList 값 : " + d_list + "\n");
		session.close();
		
		return d_list;
	}
	
	// 유튜브 영상 삭제
	public void deleteVideo(youtubeDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("Youtube.deleteVideo", dto);
		
		session.commit();
		session.close();
	}

	// 메시지 보내기
	public void sendMessage(youtubeMsgDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Youtube.sendMessage", dto);
		
		session.commit();
		session.close();
	}

}
