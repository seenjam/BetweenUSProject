package DAO;

import java.util.ArrayList;

import VO.FavoriteListValueVO;
import VO.FavoriteVO;
import VO.MemberVO;

public interface FavoriteMapper {

	public void insertFavorite(FavoriteVO favoriteVO);
	public int favoriteCheck(FavoriteVO favoriteVO);
	public ArrayList<FavoriteVO> listFavorite();
	public ArrayList<FavoriteListValueVO> favoriteListValueSQL(String memID);
	public MemberVO test(String memID);
}
