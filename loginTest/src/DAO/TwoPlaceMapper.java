package DAO;

import java.util.ArrayList;

import VO.TestPlaceVO;
import VO.TwoPlaceVO;

public interface TwoPlaceMapper {
	public void insertTwoPlace(TestPlaceVO testPlaceVO) ;
	public ArrayList<TwoPlaceVO> listTwoPlace();
	public int twoPlaceCheck(TestPlaceVO testPlaceVO);
	public String findIdTwoPlace(TestPlaceVO testPlaceVO);	
}
