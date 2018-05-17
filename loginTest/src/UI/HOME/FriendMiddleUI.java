package UI.HOME;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DAO.SearchTwoPlaceDao;
import VO.MemberVO;
import VO.PlaceVO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class FriendMiddleUI extends JDialog implements ActionListener{
	private JLabel FrindMiddle_lb ;
	private JLabel googleMap;
	private MemberVO myInfo,friendInfo;
	private Double middlePlaceLat,middlePlaceLon;
	private String middlePlaceLatLonInfo ;
	private SearchTwoPlaceDao searchTwoPlaceDao = new SearchTwoPlaceDao();
	private JButton zoomIn_btn,zoomOut_btn;
	private String pathLocation1,pathLocation2;
	public FriendMiddleUI(MemberVO myInfo,MemberVO friendInfo) {
		getContentPane().setBackground(Color.WHITE);
		this.myInfo = myInfo;
		this.friendInfo =friendInfo;

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(14, 12, 554, 559);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		System.out.println(myInfo + " "+friendInfo);
		
		googleMap = new JLabel();
		googleMap.setBounds(14, 102, 526, 445);
		panel.add(googleMap);
		findMiddle(myInfo.getMemAddress(),friendInfo.getMemAddress());
		
		
		
		
		setSize(600, 630);
		getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("중간 위치 (위도, 경도) : ");
		lblNewLabel_1.setBounds(14, 33, 341, 18);
		panel.add(lblNewLabel_1);
		
		FrindMiddle_lb = new JLabel("New label");
		
		FrindMiddle_lb.setBounds(14, 59, 373, 18);
		FrindMiddle_lb.setText(middlePlaceLatLonInfo);
		panel.add(FrindMiddle_lb);
		
		zoomIn_btn = new JButton("+");
		zoomIn_btn.setBounds(401, 55, 57, 35);
		panel.add(zoomIn_btn);
		zoomIn_btn.addActionListener(this);
		zoomOut_btn = new JButton("-");
		zoomOut_btn.setBounds(472, 55, 57, 35);
		panel.add(zoomOut_btn);
		zoomOut_btn.addActionListener(this);
		setVisible(true);
	}
	public void findMiddle(String imAddress, String friendAddress) {
		// TODO Auto-generated method stub
		PlaceVO memAddress=  searchTwoPlaceDao.friendMapAPI(imAddress);
		
		PlaceVO memAddress2 =  searchTwoPlaceDao.friendMapAPI(friendAddress);
		System.out.println("memAddress "+memAddress);
		System.out.println("memAddress2 "+ memAddress2);
		
		middlePlaceLat = searchTwoPlaceDao.middlePlaceLat(memAddress,memAddress2);
		middlePlaceLon = searchTwoPlaceDao.middlePlaceLon(memAddress,memAddress2);
		middlePlaceLatLonInfo = "위도 : " + middlePlaceLat + ". 경도 :" + middlePlaceLon;
		System.out.println(middlePlaceLatLonInfo);
		
		pathLocation1 = memAddress.getPlaceLat()+","+memAddress.getPlaceLon();
		pathLocation2 = memAddress2.getPlaceLat()+","+memAddress2.getPlaceLon();
		middleSearchClickSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(9));
		setSize(600, 630);
		searchTwoPlaceDao.zoom=11;
	}
	//중간찾기버튼을 클릭시 경로를 Map에 보여줌
	public void middleSearchClickSetMap(String location,String location2,int zoom) {
			searchTwoPlaceDao.middleSearchClickdownloadMap(location,location2,zoom);
			googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
			searchTwoPlaceDao.fileDelete(location+location2+""+zoom);
	}
	public void zoomSetMap(String location,String location2,int zoom) {
		searchTwoPlaceDao.zoomMiddleSearchDownloadMap(location,location2,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == zoomIn_btn) {
			 zoomSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(1));
			 setSize(600, 630);
		}
		if (e.getSource() == zoomOut_btn) {
			 zoomSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(0));
			 setSize(600, 630);
		}
		
	}
}
