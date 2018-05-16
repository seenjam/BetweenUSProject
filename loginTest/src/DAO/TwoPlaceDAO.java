package DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.TestPlaceVO;
import VO.TwoPlaceVO;

public class TwoPlaceDAO {
	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public void insertTwoPlace(TestPlaceVO testPlaceVO) {
		SqlSession session = null;
/*		ArrayList<String> placeID = new ArrayList<>();
*/		try {
			
			/*placeID.add(placeID1);
			placeID.add(placeID2);
			for (int i = 0; i < placeID.size(); i++) {
				System.out.println(placeID.get(i));
			}*/
			System.out.println(testPlaceVO);
			System.out.println("insertTwoPlace session made success");
			session = factory.openSession();
			TwoPlaceMapper mapper = session.getMapper(TwoPlaceMapper.class);
			mapper.insertTwoPlace(testPlaceVO);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	}
	public int twoPlaceCheck(TestPlaceVO testPlaceVO) {
		int flag = 0;
		SqlSession session = null;
		try {
			System.out.println("twoPlaceIDCheck session made success");
			System.out.println(testPlaceVO);
			session = factory.openSession();
			TwoPlaceMapper mapper = session.getMapper(TwoPlaceMapper.class);
			if(mapper.twoPlaceCheck(testPlaceVO) == 0) { //null이면 (id가 없으면) 0 
				flag = 0;  
			}else {
				flag = 1; 
			}
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return flag;
	}
	public String findIdTwoPlace(TestPlaceVO testPlaceVO) {
		SqlSession session = null;
		String twoPlaceSeq = null;
		try {
			System.out.println("findIdTwoPlace session made success");
			session = factory.openSession();
			TwoPlaceMapper mapper = session.getMapper(TwoPlaceMapper.class);
			twoPlaceSeq = mapper.findIdTwoPlace(testPlaceVO);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return twoPlaceSeq;
	}
	
	public ArrayList<TwoPlaceVO> listTwoPlace() {
		
		SqlSession session = null;
		ArrayList<TwoPlaceVO> twoPlace_alist = null;
		try {
					
				System.out.println("listTwoPlace session made success");
				session = factory.openSession();
				TwoPlaceMapper mapper = session.getMapper(TwoPlaceMapper.class);
				twoPlace_alist = mapper.listTwoPlace();
				session.commit();

		 }catch(Exception e) {
				e.printStackTrace();
					
		 }finally {
				session.close();
		 }
		return twoPlace_alist;
	}
}
