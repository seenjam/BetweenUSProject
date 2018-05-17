package UI.HOME;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DAO.AroundPlaceDAO;
import VO.CategoryRadiusPlace;
import VO.PlaceVO;
import java.awt.Color;

public class AroundPlaceUI extends JDialog implements ActionListener,ListSelectionListener{
	private JList aroundPlace_list; //검색어에 따른 값들을 table에 넣어준다. 
	private JComboBox category_combo,radius_combo; // 카테고리 콤보박스 ,반경 콤보박스
	private JButton aroundTable_btn; //카테고리 ,반경에 따른  주변 찾기 버튼
	private JLabel googleMap; 		// 카테고리, 반경에 따른 지도
	private String columnNames[]= {"장소명","주소","전화번호","경도","위도"};private JButton mapZoonIn_btn,mapZoonOut_btn;
	private AroundPlaceDAO aroundPlaceDAO = new AroundPlaceDAO();
	private String location;
	private JLabel firstPlace_lb,secondPlace_lb,middlePlaceLon_lb,middlePlaceLat_lb; //���������ֱ��/.// ��������!

	public AroundPlaceUI(String[] middlePlaceInfoArray) {
		getContentPane().setBackground(Color.WHITE);
		
		setPostAroundPlaceArray(middlePlaceInfoArray); //중간위치의 정보를 set (첫번째장소, 두번째장소,lon경도 ,lat위도)
		aroundPlaceDAO.setCategory_map(); //카테고리 예시 set (지하철역, 카페, 음식점 등등)
		
		setSize(1220, 630);
		getContentPane().setLayout(null);
		
		firstPlace_lb = new JLabel("FirstPlace");
		firstPlace_lb.setBounds(59, 47, 365, 18);
		getContentPane().add(firstPlace_lb);
		firstPlace_lb.setText(getPostAroundPlaceArray()[0]);
		
		
		secondPlace_lb = new JLabel("SecondPlace");
		secondPlace_lb.setBounds(59, 82, 365, 18);
		getContentPane().add(secondPlace_lb);
		secondPlace_lb.setText(getPostAroundPlaceArray()[1]);

		
		middlePlaceLon_lb = new JLabel("MiddlePlace LON");
		middlePlaceLon_lb.setBounds(59, 123, 365, 18);
		getContentPane().add(middlePlaceLon_lb);
		middlePlaceLon_lb.setText(getPostAroundPlaceArray()[2]);
		
		
		middlePlaceLat_lb = new JLabel("MiddlePlace LAT");
		middlePlaceLat_lb.setBounds(59, 158, 365, 18);
		getContentPane().add(middlePlaceLat_lb);
		middlePlaceLat_lb.setText(getPostAroundPlaceArray()[3]);

		
		
		
		
		category_combo = new JComboBox();
		for (Map.Entry<String, String> s: aroundPlaceDAO.getCategory_map().entrySet()) {
			category_combo.addItem(s.getKey());
			System.out.println(s.getKey());
		}
		category_combo.setBounds(229, 201, 96, 24);
		getContentPane().add(category_combo);
		
		
		radius_combo = new JComboBox();
		for(int r : aroundPlaceDAO.getRadius()) {
			radius_combo.addItem(r);
		}
		radius_combo.setBounds(339, 201, 85, 24);
		getContentPane().add(radius_combo);
		
		aroundTable_btn = new JButton("Search");
		aroundTable_btn.setBounds(320, 246, 105, 27);
		getContentPane().add(aroundTable_btn);
		aroundTable_btn.addActionListener(this);
		
		
		//DefaultTableModel dtm=new DefaultTableModel(columnNames);
		//JTable aroundPlace_list = new JTable(dtm);
		
		
		aroundPlace_list = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(aroundPlace_list);
		scrollPane_1.setBounds(33, 285, 392, 277);
		getContentPane().add(scrollPane_1);
		//한번에 한 항목만 선택할 수 있는 모드로 리스트를 사용
		aroundPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//한번에 한 항목만 선택할 수 있는 모드로 리스트를 사용
		//add(new JScrollPane(firstSearchPlace_list));
		aroundPlace_list.addListSelectionListener(this);
		
		
		googleMap = new JLabel();
		googleMap.setBounds(469, 47, 644, 502);
		getContentPane().add(googleMap);
		
		mapZoonIn_btn = new JButton("+");
		mapZoonIn_btn.setBounds(1133, 460, 55, 27);
		mapZoonIn_btn.addActionListener(this);
		getContentPane().add(mapZoonIn_btn);
		
		mapZoonOut_btn = new JButton("-");
		mapZoonOut_btn.setBounds(1133, 499, 55, 27);
		mapZoonOut_btn.addActionListener(this);
		getContentPane().add(mapZoonOut_btn);
		setVisible(true);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==aroundTable_btn) {
			
			System.out.println("getPostAroundPlaceArray()[2] : "+ getPostAroundPlaceArray()[2]); //lon,경도
			System.out.println("getPostAroundPlaceArray()[3] : "+ getPostAroundPlaceArray()[3]); //lat, 위도
			System.out.println("category_combo.getSelectedItem() : " + category_combo.getSelectedItem());
			System.out.println("category_combo.getvALUE() : " + aroundPlaceDAO.getCategory_map().get(category_combo.getSelectedItem()));
			System.out.println("radius_combo.getSelectedItem() : " + radius_combo.getSelectedItem());
			String radius =  radius_combo.getSelectedItem() + "";
			System.out.println("radius : " + radius);
			
			ArrayList<CategoryRadiusPlace> CategoryRadiusPlace_alist =aroundPlaceDAO.receivekakaoMapAPI(aroundPlaceDAO.getCategory_map().get(category_combo.getSelectedItem()), getPostAroundPlaceArray()[2], getPostAroundPlaceArray()[3],radius);
			
			if(CategoryRadiusPlace_alist!=null) {
				
				aroundPlace_list.setListData(CategoryRadiusPlace_alist.toArray());
			}
		
		}
		if(e.getSource() == mapZoonIn_btn) {
			zoomSetMap(location,aroundPlaceDAO.valueZoom(1));
		    setSize(1350, 800);
		}
		if(e.getSource() == mapZoonOut_btn) {
			zoomSetMap(location,aroundPlaceDAO.valueZoom(0));
		    setSize(1350, 800);
		}
	}
	
	//지금 질문한것에 대한 json 값들을 table에 셋팅! 
	public void setPostAroundPlaceArray(String[] middlePlaceInfoArray) {
		// TODO Auto-generated method stub
		aroundPlaceDAO.setPostAroundPlace(middlePlaceInfoArray);

	}
	public String[] getPostAroundPlaceArray() {
		//순서: 첫번째장소, 두번째장소, Lon(경도) , Lat(위도)
		return aroundPlaceDAO.getPostAroundPlace();
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(!e.getValueIsAdjusting()) {
			
			if(e.getSource() == aroundPlace_list&&aroundPlace_list.getSelectedIndex()!=-1) {
				//선택된 값을 SearchPlace VO에 넣음
				System.out.println("이게 나온다면 list를 출력하지않았는데도 요기로 들어오는것임.");
				CategoryRadiusPlace categoryRadiusPlace = (CategoryRadiusPlace)aroundPlace_list.getSelectedValue();
				location = categoryRadiusPlace.getLat()+","+categoryRadiusPlace.getLon();
				//지도 생성
				listClickSetMap(location,aroundPlaceDAO.valueZoom(9));
				//크기재지정
				setSize(1220, 640);
				
				aroundPlaceDAO.zoom=11;
				//secondSearchPlace_list를 선택하고 다시 firstSearchPlace_list를 선택할 때 다시 지도 보여주기 위해
				System.out.println(categoryRadiusPlace);
				
			}
		}
	}

	//설정된 googleMap을 확대, 축소시 사용 
	public void zoomSetMap(String location,int zoom) {
			aroundPlaceDAO.zoomDownloadMap(location,zoom);
			googleMap.setIcon(aroundPlaceDAO.getMap(location,zoom));
			aroundPlaceDAO.fileDelete(location+""+zoom);
	}
	//주소list를 클릭하여 googleMap을 설정할 때 사용
	public void listClickSetMap(String location,int zoom) {
			aroundPlaceDAO.listClickdownloadMap(location,zoom);
			googleMap.setIcon(aroundPlaceDAO.getMap(location,zoom));
			aroundPlaceDAO.fileDelete(location+""+zoom);
	}
}
