package DAO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import DTO.auditionDTO;
import DTO.auditionMailDTO;
import mapper.MybatisManager;

public class auditionDAO {
	
	// 오디션 게시물 업로드(등록)
	public void uploadAudition(auditionDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Audition.uploadAudition", dto);
		
		System.out.println("오디션 업로드 DAO : " + dto + "\n");
		session.commit();
		session.close();
	}

	// 오디션 게시물 리스트 조회
	public List<auditionDTO> selectAuditionList() {		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<auditionDTO> a_list = session.selectList("Audition.selectAuditionList");
		
		// System.out.println("selectAuditionList : " + a_list + "\n");
		session.close();
		return a_list;
	}

	// 오디션 게시물 삭제
	public void deleteAudition(auditionDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("Audition.deleteAudition", dto);
		
		session.commit();
		session.close();
	}

	// 오디션 지원자 팝업 페이지 정보 조회
	public auditionDTO selectApplicant(auditionDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.selectOne("Audition.selectApplicant", dto);
		
		// System.out.println("selectApplicant DAO : " + dto);
		session.close();
		return dto;
	}

	// 지원자 메일 정보 저장
	public void insertApplicant(auditionMailDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Audition.insertApplicant", dto);
		System.out.println("insertApplicant : " + dto);
		
		session.commit();
		session.close();
	}
	
}
