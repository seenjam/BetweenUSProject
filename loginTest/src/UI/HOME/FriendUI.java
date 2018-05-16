package UI.HOME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.ibatis.javassist.compiler.ast.Member;

import Main.MemberMain;
import VO.MemberVO;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JButton;

public class FriendUI extends JPanel implements ActionListener, ListSelectionListener {
	private JButton searchFriend_btn;
	private MemberMain mm = new MemberMain();
	private ArrayList<MemberVO> favoriteFriend = new ArrayList<>();
	private JList friend_list=new JList();
	private JButton btnNewButton;
	MemberVO myInfo = null;
	
	public FriendUI(MemberVO myInfo) {
		this.myInfo = myInfo;
		setSize(1220, 640);
		setLayout(null);

		friend_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(63, 80, 238, 396);
		scrollPane.setViewportView(friend_list);
		add(scrollPane);

		searchFriend_btn = new JButton("\uCE5C\uAD6C\uCC3E\uAE30");
		searchFriend_btn.setBounds(337, 78, 164, 23);
		add(searchFriend_btn);
		searchFriend_btn.addActionListener(this);


		setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchFriend_btn) {
			mm.showSearchFriend(friend_list, myInfo);
		}


	}

	public void addFavoriteFriend(MemberVO selectedfriend) {


		favoriteFriend.add(selectedfriend);

		for (MemberVO vo : favoriteFriend) {

		
		}
	
		friend_list.setListData(favoriteFriend.toArray());

	
	

	}
}
