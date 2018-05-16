package DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import VO.FavoriteVO;
import VO.TestPlaceVO;
import VO.TwoPlaceVO;

public class FavoriteDAO {
	private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	
	public void insertFavorite(FavoriteVO favoriteVO) {
		SqlSession session = null;
		try {
			
			System.out.println("insertFavorite session made success");
			session = factory.openSession();
			FavoriteMapper mapper = session.getMapper(FavoriteMapper.class);
			mapper.insertFavorite(favoriteVO);
			session.commit();

		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
		}
	}
	public int favoriteCheck(FavoriteVO favoriteVO) {
		int flag = 0;
		SqlSession session = null;
		try {
			System.out.println("favoriteCheck session made success");
			session = factory.openSession();
			FavoriteMapper mapper = session.getMapper(FavoriteMapper.class);
			if(mapper.favoriteCheck(favoriteVO) == 0) { //null이면 (id가 없으면) 0 
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
	public ArrayList<FavoriteVO> listFavorite() {
		
		SqlSession session = null;
		ArrayList<FavoriteVO> favorite_alist = null;
		try {
					
				System.out.println("listTwoPlace session made success");
				session = factory.openSession();
				FavoriteMapper mapper = session.getMapper(FavoriteMapper.class);
				favorite_alist = mapper.listFavorite();
				session.commit();

		 }catch(Exception e) {
				e.printStackTrace();
					
		 }finally {
				session.close();
		 }
		return favorite_alist;
	}
}
