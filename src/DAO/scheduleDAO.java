package DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import DTO.classApplicantDTO;
import DTO.scheduleDTO;
import mapper.MybatisManager;

public class scheduleDAO {

	// 스케줄(클래스) 등록
	public void uploadClass(scheduleDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Schedule.uploadClass", dto);
		
		session.commit();
		session.close();
	}

	// 스케줄 조회
	public List<scheduleDTO> selectScheduleList() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<scheduleDTO> d_list = session.selectList("Schedule.selectScheduleList");
		
		// System.out.println("DAO selectScheduleList : " + d_list);
		session.close();
		return d_list;
	}

	// 클래스 삭제
	public void deleteClass(int sc_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("Schedule.deleteClass", sc_id);
		
		session.commit();
		session.close();
	}

	// 클래스 신청 - 클래스 신청자 정보 등록
	public void applyforClass(classApplicantDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Schedule.applyforClass", dto);
		
		// System.out.println("Schedule.applyforClass : " + dto);
		session.commit();
		session.close();
	}

	// 클래스 신청 시 중복 불가
	public int selectCheckId(String me_id, int sc_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<Object, Object> check_map = new HashMap<Object, Object>();
		check_map.put("me_id", me_id);
		check_map.put("sc_id", sc_id);
		
		String check_id = session.selectOne("Schedule.selectCheckId", check_map);
		
		int check = 1;
		
		if(check_id == null || check_id.equals("")) {
			check = 0;
		}
		
		session.close();
		return check;
	}

	// 클래스 신청자 정보 조회
	public List<classApplicantDTO> selectClassApplicant(int sc_id, String sc_date) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<Object, Object> check_map = new HashMap<Object, Object>();
		check_map.put("sc_id", sc_id);
		check_map.put("sc_date", sc_date);
		
		List<classApplicantDTO> class_applicant = session.selectList("Schedule.selectClassApplicant", check_map);
		
		session.close();
		return class_applicant;
	}

	// 신청 클래스 정보 조회
	public List<scheduleDTO> selectClassList(int sc_id, String sc_date) {
		SqlSession session = MybatisManager.getInstance().openSession();

		Map<Object, Object> check_map = new HashMap<Object, Object>();
		check_map.put("sc_id", sc_id);
		check_map.put("sc_date", sc_date);
		
		List<scheduleDTO> applicant_class_list = session.selectList("Schedule.selectClassList", check_map);
		
		session.close();
		return applicant_class_list;
	}

	// 클래스 신청자 총 인원수 조회
	public int selectClassApplicantCnt(int sc_id, String sc_date) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		Map<Object, Object> check_map = new HashMap<Object, Object>();
		check_map.put("sc_id", sc_id);
		check_map.put("sc_date", sc_date);
		
		int total = session.selectOne("Schedule.selectClassApplicantCnt", check_map);
		// System.out.println("DAO total : " + total);
		
		session.close();
		return total;
	}

	// 클래스 신청자 정보 삭제
	public void deleteClassApplicant(int sc_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("Schedule.deleteClassApplicant", sc_id);
		
		session.commit();
		session.close();
	}
}
