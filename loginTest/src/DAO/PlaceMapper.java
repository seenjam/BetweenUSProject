package DAO;

import java.util.ArrayList;

import VO.PlaceVO;

public interface PlaceMapper {
	public void insertPlace(PlaceVO vo);
	public PlaceVO selectPlace(String placeID);
	public ArrayList<PlaceVO> listPlace();
	public int placeIDCheck(String placeID);	
	
}
