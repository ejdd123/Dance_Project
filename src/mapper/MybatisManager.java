package mapper;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager { 
	//생성자
	private MybatisManager() {
	}
	
	//SQLSession객체를 만들어내는 SQLSessionFactory 참조변수
	//mybatis query를 수행하는 객체
	private static SqlSessionFactory instance; 			//sql실제 명령을 수행하는 객체
	
	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		
		if(reader ==null) {
			try {
				// mapper 패키지 내의 SqlConfig.xml(마이바티스 환경설정) 파일 정보를 읽음
				String resource = "mapper/SqlConfig.xml";
				reader = Resources.getResourceAsReader(resource);
				
				instance = new SqlSessionFactoryBuilder().build(reader);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(reader != null) {
						reader.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return instance;
	}
}
