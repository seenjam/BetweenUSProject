package DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.PlaceVO;

public class PlaceDAO {

	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public void insertPlace(PlaceVO vo) {
		SqlSession session = null;
		try {
			System.out.println("insetPlace �޼ҵ�� ���Խ��ϴ�. ���� session �����Դϴ�");
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
