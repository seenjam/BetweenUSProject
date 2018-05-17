package Main;

import javax.swing.JFrame;
import javax.swing.JList;

import com.wooriclient.ChatClientMain;

import DAO.MemberDAO;
import UI.HOME.FriendUI;
import UI.HOME.HomeUI;
import UI.HOME.SearchFriendsUI;
import UI.LOGIN.LoginUI;
import UI.LOGIN.MemberUI;
import VO.MemberVO;

public class MemberMain extends JFrame{
	private static LoginUI loginUi ;
	private static MemberUI memUi;
	private static HomeUI homeUi;
	private static FriendUI friendUi;
	private static SearchFriendsUI searchFriendsUI;
	private MemberDAO dao;
	private MemberVO mem;
	private static ChatClientMain chatcClientMain;
	
	public static void main(String[] args) {
		//PersonMain main =new PersonMain();
		loginUi = new LoginUI();
		//new MainUI_test();
	}
	
	public void showLogin() {
		//mu.dispose();
		//loginUi = new LoginUI();
		loginUi.setVisible(true);
		memUi.setVisible(false);
	}

	//회원가입클릭 시 
	public void showSignUp() {
		memUi = new MemberUI();
		//loginUi.setVisible(false);
		memUi.setVisible(true);
	}
	
	//로그인 성공시
	public void loginSuccess(MemberVO mem) {
		this.mem = mem;
		//homeUi = new HomeUI();
		homeUi = new HomeUI(mem);
		homeUi.setVisible(true);
		//loginUi.setVisible(false);
		
	}
	
	public void showSearchFriend(JList friend_list, MemberVO myInfo) {
		searchFriendsUI = new SearchFriendsUI(friend_list , myInfo);
		searchFriendsUI.setVisible(true);
	}
	
	public void updateLoginState(String id){
		dao = new MemberDAO();
		dao.updateLoginState(id);
	}
	
	public void showFriend() {
		
		searchFriendsUI.setVisible(false);
		//friendUi.setVisible(true);
		
	}
	


	public void showChattingWindow(MemberVO myInfo, MemberVO friendInfo) {
		//chatcClientMtry {
		try {
			ChatClientMain frame = new ChatClientMain(myInfo, friendInfo,"203.233.196.118", 3333);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//chatcClientMain.setVisible(true);
		
	}
	




}
