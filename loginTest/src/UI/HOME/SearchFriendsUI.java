package UI.HOME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.MemberDAO;
import Main.MemberMain;
import UI.LOGIN.MemberUI;
import VO.MemberVO;
import VO.PlaceVO;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JList;

public class SearchFriendsUI extends JDialog implements ActionListener, ListSelectionListener {
	private JTextField searchFriend_tf;
	private JLabel searchFriend_lb;
	private JButton searchFriend_btn;
	private JList searchedFriend_list;
	private JLabel error_lb;
	private JRadioButton searchById_rbtn;
	private JRadioButton searchByName_rbtn;
	private ButtonGroup rBtnGroup = new ButtonGroup();

	private MemberDAO dao = new MemberDAO();
	private MemberVO member = new MemberVO();
	private FriendUI fu;

	private MemberVO selectedFriend = null;
	boolean isChanging =false;
	String favFriend ="";		// 선택해서 친구추가된 회원의 정보저장
	private MemberMain mm = new MemberMain();
	private JList friend_list;/*=new JList();*/
	private MemberVO myInfo = null;// 내정보
	
	


	
	private ArrayList<MemberVO> flist = new ArrayList<>();
	//private ArrayList<MemberVO> mlist = new ArrayList<>();
	
	
	
	public SearchFriendsUI(JList friend_list, MemberVO myInfo) {		
		this.friend_list=friend_list;
		this.myInfo = myInfo;
		getContentPane().setBackground(Color.WHITE);
		setSize(300, 453);
		getContentPane().setLayout(null);

		searchFriend_lb = new JLabel("\uCE5C\uAD6C\uCC3E\uAE30");
		searchFriend_lb.setHorizontalAlignment(SwingConstants.CENTER);
		searchFriend_lb.setFont(new Font("������� ExtraBold", Font.BOLD, 20));
		searchFriend_lb.setBounds(60, 14, 156, 32);
		getContentPane().add(searchFriend_lb);

		searchFriend_tf = new JTextField();
		searchFriend_tf.setHorizontalAlignment(SwingConstants.CENTER);
		searchFriend_tf.setBounds(38, 81, 136, 21);
		getContentPane().add(searchFriend_tf);
		searchFriend_tf.setColumns(10);

		searchFriend_btn = new JButton("\uAC80\uC0C9");
		searchFriend_btn.setBounds(175, 80, 77, 23);
		getContentPane().add(searchFriend_btn);
		searchFriend_btn.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 130, 216, 192);
		getContentPane().add(scrollPane);

		searchedFriend_list = new JList();
		searchedFriend_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(searchedFriend_list);
		searchedFriend_list.addListSelectionListener(this);//리스트가 클릭되면
		
		error_lb = new JLabel("");
		error_lb.setForeground(Color.RED);
		error_lb.setHorizontalAlignment(SwingConstants.CENTER);
		error_lb.setBounds(36, 115, 216, 15);
		getContentPane().add(error_lb);

		searchById_rbtn = new JRadioButton("id\uB85C \uAC80\uC0C9");
		searchById_rbtn.setSelected(true);
		searchById_rbtn.setActionCommand("id찾기");
		searchById_rbtn.setBounds(39, 52, 82, 23);
		getContentPane().add(searchById_rbtn);
		searchById_rbtn.addActionListener(this);

		searchByName_rbtn = new JRadioButton("\uC774\uB984\uC73C\uB85C \uAC80\uC0C9");
		searchByName_rbtn.setBounds(125, 52, 127, 23);
		searchByName_rbtn.setActionCommand("이름찾기");
		getContentPane().add(searchByName_rbtn);
		searchByName_rbtn.addActionListener(this);

		rBtnGroup.add(searchById_rbtn);
		rBtnGroup.add(searchByName_rbtn);

		setVisible(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String searchingFriendID = searchFriend_tf.getText();
        String choosenBtn= rBtnGroup.getSelection().getActionCommand();
        String searchingFriendName = searchFriend_tf.getText();
        ArrayList<MemberVO> nlist = new ArrayList<>();
        ArrayList<MemberVO> ilist = new ArrayList<>();

        
		if (e.getSource() == searchFriend_btn) {
			searchFriend_tf.setText("");
			if (choosenBtn.equals("id찾기")) {
				//searchFriend_tf.setText("");
				// 빈칸인 경우 오류메세지
				if (searchingFriendID.equals("")) {
					error_lb.setForeground(Color.red);
					error_lb.setText("빈 칸입니다!");
				} else {
					if (dao.searchFriendByIDCount(searchingFriendID) == 0) {  // 검색한 친구가 존재하지 않을 경우 오류메세지
						error_lb.setForeground(Color.red);
						error_lb.setText("존재하지 않는 아이디입니다!");

					} else {
						//ArrayList<MemberVO> mlist = new ArrayList<>();
						ilist = dao.searchFriendByIDList(searchingFriendID);

						//for (MemberVO mem : mlist) {
							// System.out.println(mem.getMemID());
							searchedFriend_list.setListData(ilist.toArray());
					//	}
						error_lb.setForeground(Color.BLACK);
						error_lb.setText("추가할 친구를 클릭해주세요.");
					}
				}

			}

			else if(choosenBtn.equals("이름찾기")){
				//searchFriend_tf.setText("");
				//String searchingFriendName = searchFriend_tf.getText();

				if (e.getSource() == searchFriend_btn) {
					// 빈칸인 경우 오류메세지
					if (searchingFriendName.equals("")) {
						error_lb.setForeground(Color.red);
						error_lb.setText("빈칸입니다!");
					} else {
						if (dao.searchFriendByNameCount(searchingFriendName) == 0) { 
							error_lb.setForeground(Color.red);
							error_lb.setText("존재하지 않는 이름입니다!");
						} else {
							//ArrayList<MemberVO> mlist = new ArrayList<>();
							nlist = dao.searchFriendByNameList(searchingFriendName);

							//for (MemberVO mem : mlist) {
								// System.out.println(mem.getMemID());
								searchedFriend_list.setListData(nlist.toArray());
						//	}
							error_lb.setForeground(Color.BLACK);
							error_lb.setText("추가할 친구를 클릭해주세요.");
						}
					}
				}
			}
			
		}

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(!e.getValueIsAdjusting()) {
			if(!isChanging) {
				if(e.getSource() == searchedFriend_list) {
					//isChanging =true;
					selectedFriend = (MemberVO)searchedFriend_list.getSelectedValue();
					
					fu = new FriendUI(myInfo);
					//mm.showFriend();
					fu.addFavoriteFriend(selectedFriend);
					//flist=new ArrayList<>();
					flist.add(selectedFriend);

					friend_list.setListData(flist.toArray());
					
			//		dao.settingfriends(myInfo.getMemID(), selectedFriend.getMemID());
					
					
				}
					
			}
		}
		
	}

}
