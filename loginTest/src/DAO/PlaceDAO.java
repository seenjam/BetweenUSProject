package DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.PlaceVO;

public class PlaceDAO {

	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public void insertPlace(PlaceVO vo) {
		SqlSession session = null;
		try {
			System.out.println("insetPlace 메소드로 들어왔습니다. 여기 session 공간입니당");
			session = factory.openSession();
			PlaceMapper mapper = session.getMapper(PlaceMapper.class);
			mapper.insertPlace(vo);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	}
}
