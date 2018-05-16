package DAO;

import java.util.ArrayList;

import VO.FavoriteVO;

public interface FavoriteMapper {

	public void insertFavorite(FavoriteVO favoriteVO);
	public int favoriteCheck(FavoriteVO favoriteVO);
	public ArrayList<FavoriteVO> listFavorite();

}
