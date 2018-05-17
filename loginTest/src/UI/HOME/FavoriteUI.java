package UI.HOME;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.FavoriteDAO;
import VO.FavoriteListValueVO;
import VO.FavoriteVO;
import VO.MemberVO;
import VO.PlaceVO;

import javax.swing.JButton;

public class FavoriteUI extends JPanel implements ActionListener, ListSelectionListener{
	private JPanel favoritePanel;
	private JList favorite_list;
	private FavoriteDAO favoriteDao = new FavoriteDAO();
	private ArrayList<FavoriteListValueVO> FavoriteListValueVO_aList = new ArrayList<>();
	private ArrayList<FavoriteVO> favorite_aList ;
	private JButton btnNewButton;
	private MemberVO myInfo = null;
	public FavoriteUI(MemberVO mem) {
		setSize(1220, 640);
		setLayout(null);
		
		this.myInfo = mem;
		
		favoritePanel = new JPanel();
		favoritePanel.setBounds(14, 30, 881, 513);
		add(favoritePanel);
		favoritePanel.setLayout(null);
		
		/*
		JScrollPane scrollpane = new JScrollPane();
		panel.add(scrollpane);*/
		
		JScrollPane scrollpane = new JScrollPane();
		favorite_list = new JList();
		
		
		scrollpane.setViewportView(favorite_list);
		favoritePanel.add(scrollpane);
		scrollpane.setBounds(58, 60, 784, 386);

		
		/*favorite_aList = favoriteDao.listFavorite();
		for (int i = 0; i < favorite_aList.size(); i++) {
			System.out.println(favorite_aList.get(i).getTwoPlaceSeq());
		}*/
		
		btnNewButton = new JButton("새로고침");
		btnNewButton.setBounds(58, 21, 105, 27);
		btnNewButton.addActionListener(this);
		favoritePanel.add(btnNewButton);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnNewButton) {
			FavoriteListValueVO_aList = favoriteDao.favoriteListValueSQL(myInfo.getMemID());
			for (int i = 0; i < FavoriteListValueVO_aList.size(); i++) {
				System.out.println(FavoriteListValueVO_aList.get(i));
			}
			favoriteDao.test(myInfo.getMemID());
			favorite_list.setListData(FavoriteListValueVO_aList.toArray());
			

		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (!e.getValueIsAdjusting()) {
			if(e.getSource() == favorite_list&&favorite_list.getSelectedIndex()!=-1) {
				//setSelectedComponent(searchTwoPlaceUI)
			}	
		}
		
	}
}
