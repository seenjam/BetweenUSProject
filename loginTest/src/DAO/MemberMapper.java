package DAO;

import java.util.ArrayList;

import VO.MemberVO;

public interface MemberMapper {
	
	public void insertMember(MemberVO vo) ;	//그냥 등록되면 되니까, return 타입이 void
	public int IDcheck(String id);
	public String LoginCheck(String id);
	//public MemberVO FriendList(String id);
	
	public void updateLoginState(String id);
	
	public int searchFriendByNameCount(String name);
	public int searchFriendByIDCount(String id);
	public ArrayList<MemberVO> searchFriendByNameList(String name);
	public ArrayList<MemberVO> searchFriendByIDList(String id);
	public MemberVO searchFriendByName(String name);
	public MemberVO searchFriendByID(String id);
	public MemberVO MyInfo(String id);
}
