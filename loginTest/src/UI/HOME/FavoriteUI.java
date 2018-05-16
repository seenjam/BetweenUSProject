package UI.HOME;


import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DAO.FavoriteDAO;
import VO.MemberVO;

public class FavoriteUI extends JPanel {
	private JPanel favoritePanel;
	private JList favorite_list;
	private FavoriteDAO favoriteDao = new FavoriteDAO();
	public FavoriteUI(MemberVO myInfo) {
		setSize(1220, 640);
		setLayout(null);
		
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

		//favoriteDao.listFavorite()
		//favorite_list.setListData(listData);
		
		setVisible(true);
	}
}
