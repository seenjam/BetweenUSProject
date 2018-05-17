package UI.HOME;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import VO.MemberVO;

public class HomeUI extends JFrame {
	
	private SearchTwoPlaceUI searchTwoPlaceUI;
	private FriendUI friendUI ;			// 친구 페이지
	private JLabel myID_lb=new JLabel("");
	private FavoriteUI favoriteUI;
	private JTabbedPane tabbedPane;
	private MemberVO myInfo=null;		// 로그인한 나의 정보가 들어가 있음
	
	MemberVO me=null;// = new MemberVO();


	public HomeUI(MemberVO mem) {
		getContentPane().setBackground(new Color(175, 238, 238));			// mem : 내정보
		this.myInfo = mem;
		
		setSize(1350,800);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(47, 54, 1231, 654);
		getContentPane().add(tabbedPane);
		
		searchTwoPlaceUI = new SearchTwoPlaceUI(myInfo);
		tabbedPane.addTab("중간찾기", null, searchTwoPlaceUI, null);
		
		favoriteUI = new FavoriteUI(myInfo);
		tabbedPane.addTab("즐겨찾기", null, favoriteUI, null);
		
		//JPanel panel_3 = new JPanel();
		friendUI = new FriendUI(myInfo);
		tabbedPane.addTab("친구목록", null, friendUI, null);
		/*
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("ㅎㅇㅈㅂ~", null, panel_4, null);
		*/
		//tabbedPane.addChangeListener(this);
		myID_lb = new JLabel("");
		myID_lb.setBounds(995, 31, 304, 25);
		getContentPane().add(myID_lb);
		myID_lb.setBackground(Color.WHITE);
		myID_lb.setHorizontalAlignment(SwingConstants.CENTER);
		myID_lb.setText(myInfo.getMemID()+" 회원님 환영합니다! ");


		//pack();
		setVisible(false);
	
		
	}


	/*@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() ==tabbedPane ) {
			if(tabbedPane.getSelectedIndex()!=0) {
				
			}
		}
	}*/
}
