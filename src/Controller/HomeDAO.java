package Controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DTO.scheduleDTO;
import DTO.youtubeMsgDTO;
import mapper.MybatisManager;

public class HomeDAO {

	// 신청 클래스 조회
	public List<scheduleDTO> selectMyClass(String me_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		List<scheduleDTO> class_list = 	session.selectList("Home.selectMyClass", me_id);
		// System.out.println("class_list : " + class_list);
		
		session.close();
		return class_list;
	}

	// 받은 message 조회
	public List<youtubeMsgDTO> selectMyMessage(String me_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		List<youtubeMsgDTO> message_list = session.selectList("Home.selectMyMessage", me_id);
		// System.out.println("message_list : " + message_list);
		
		session.close();
		return message_list;
	}

}
