package DAO;

import org.apache.ibatis.session.SqlSession;

import DTO.memberDTO;
import mapper.MybatisManager;

public class memberDAO {

	// 회원가입 - 아이디 중복 체크
	public int checkMemberId(String me_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		// 입력한 아이디와 같은 아이디 있는지 조회
		String old_id = session.selectOne("Member.checkMemberId", me_id);
		// System.out.println("checkMemberId old_id : " + old_id);
		
		// 아이디 체크값 넘기기 - 동일한 아이디가 있으면 1, 없으면 0
		int check = 1;
		
		// 아이디 사용 가능 : old_id(기존 아이디) 가 null 이거나 공백이면 체크값 0으로 설정
		if( (old_id == null) || old_id.equals("")) {
			check = 0;
		}
		// System.out.println("checkMemberId check : " + check);
		session.close();
		
		return check;
	}

	// 회원가입
	public void insertMember(memberDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("Member.insertMember", dto);
		
		//System.out.println("insert : " + dto);
		session.commit();
		session.close();
	}

	// 로그인 체크
	public memberDTO selectMember(String me_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		memberDTO dto = new memberDTO();
		dto = session.selectOne("Member.selectMember", me_id);
		session.close();
		
		return dto; 
	}

}
