package DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.PlaceVO;
import VO.TwoPlaceVO;

public class PlaceDAO {

	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public void insertPlace(PlaceVO vo) {
		SqlSession session = null;
		try {
			System.out.println("insertPlace session made success");
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
	public PlaceVO selectPlace(String placeID) {
		SqlSession session = null;
		PlaceVO place = null;
		try {
			System.out.println("insertPlace session made success");
			session = factory.openSession();
			PlaceMapper mapper = session.getMapper(PlaceMapper.class);
			place = mapper.selectPlace(placeID);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return place;
	}
	public int placeIDCheck(String placeID) {
		int flag = 0;
		SqlSession session = null;
		try {
			System.out.println("placeIDCheck session made success");
			session = factory.openSession();
			PlaceMapper mapper = session.getMapper(PlaceMapper.class);
			if(mapper.placeIDCheck(placeID) == 0) { //null이면 (id가 없으면) 0 
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
	public ArrayList<PlaceVO> listPlace(){
		SqlSession session = null;
		ArrayList<PlaceVO> place_alist = null;
		try {
					
				System.out.println("listPlace session made success");
				session = factory.openSession();
				PlaceMapper mapper = session.getMapper(PlaceMapper.class);
			    place_alist = mapper.listPlace();
				session.commit();

		 }catch(Exception e) {
				e.printStackTrace();
					
		 }finally {
				session.close();
		 }
		return place_alist;
	}
}
