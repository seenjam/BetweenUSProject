package UI.HOME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.MemberDAO;
import Main.MemberMain;
import VO.MemberVO;
import java.awt.Color;

public class FriendUI extends JPanel implements ActionListener, ListSelectionListener {
	private JButton searchFriend_btn;
	 private MemberMain mm = new MemberMain();
	 private ArrayList<MemberVO> favoriteFriend = new ArrayList<>();
	 private JList friend_list = new JList();
	 private JButton btnNewButton;
	 private JButton chatting_btn;
	 MemberVO myInfo = null;
	 private MemberDAO dao = new MemberDAO();

	 private ArrayList<MemberVO> myFriendsList = new ArrayList<>();
	 private JButton between_btn;

	 private MemberVO friendInfo = null; // 내가 선택한 친구!!!!!!!!!!!!!! 중간값찾을 때도 이 변수 사용

	 public FriendUI(MemberVO myInfo) {
	 	setBackground(Color.WHITE);
	  this.myInfo = myInfo;
	  // myFriendsList.add(dao.settingMyFriend(myInfo.getMemID()));
	  // friend_list.setListData(myFriendsList.toArray());

	  ArrayList<String> friendsIdList = new ArrayList<>();
	  ArrayList<MemberVO> friendsInfo = new ArrayList<>();

	  ArrayList<String> friendList = dao.findingMyFriendsID(myInfo.getMemID());

	  for (String s : friendList) {
	   friendsIdList.add(s);
	  }

	  for (int i = 0; i < friendsIdList.size(); i++) {
	   // dao.findingMyFriend(friendsIdList.get(i));
	   friendsInfo.add(dao.findingMyFriend(friendsIdList.get(i)));
	  }

	  setSize(1220, 640);
	  setLayout(null);

	  friend_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

	  JScrollPane scrollPane = new JScrollPane();
	  scrollPane.setBounds(65, 68, 297, 461);
	  scrollPane.setViewportView(friend_list);
	  add(scrollPane);

	  searchFriend_btn = new JButton("\uCE5C\uAD6C\uCC3E\uAE30");
	  searchFriend_btn.setBounds(403, 100, 164, 23);
	  add(searchFriend_btn);
	  searchFriend_btn.addActionListener(this);

	  friend_list.setListData(friendsInfo.toArray());
	  friend_list.addListSelectionListener(this);

	  chatting_btn = new JButton("\uC774\uCE5C\uAD6C\uC640\uCC44\uD305\uD558\uAE30");
	  chatting_btn.setBounds(403, 138, 164, 23);
	  add(chatting_btn);
	  chatting_btn.addActionListener(this);

	  between_btn = new JButton("\uC774\uCE5C\uAD6C\uC640 \uC911\uAC04\uC9C0\uC810?");
	  between_btn.setBounds(403, 193, 163, 23);
	  add(between_btn);
	  between_btn.addActionListener(this);

	
	  setVisible(true);
	 }

	 @Override
	 public void valueChanged(ListSelectionEvent e) {
	  if (e.getSource() == friend_list) {
	   friendInfo = (MemberVO) friend_list.getSelectedValue(); // 선택한 리스트를 친구 정보에 담기
	  }
	 }

	 @Override
	 public void actionPerformed(ActionEvent e) {
	  if (e.getSource() == searchFriend_btn) {
		  	mm.showSearchFriend(friend_list, myInfo);
	  }

	  // ---------------------------------------------------------------------------------------친구와
	  // 중간지점찾기 추가부분
	  if (e.getSource() == between_btn) {

	  }
	  // ------------------------------------------------------------------------------------------------
	  if (e.getSource() == chatting_btn) {  // 친구와 채팅하기가 클릭되면
	   if (friendInfo.getMemState().equals("1")) {
	    try {
	     //mm.showChattingWindow(myInfo, friendInfo);
	    } catch (Exception e2) {
	    
	    }
	   }
	   else {
	    JOptionPane.showMessageDialog(null, "로그아웃상태인 회원입니다!");
	   }
	   }
	 }

}

	