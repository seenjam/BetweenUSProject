package UI.HOME;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.FavoriteDAO;
import DAO.PlaceDAO;
import DAO.SearchTwoPlaceDao;
import DAO.TwoPlaceDAO;
import VO.FavoriteVO;
import VO.MemberVO;
import VO.PlaceVO;
import VO.TestPlaceVO;

public class SearchTwoPlaceUI extends JPanel implements ActionListener,ListSelectionListener{
	
	private JTextField firstSearchPlace_tf; //first Search Place textField
	private JTextField secondSearchPlace_tf;//second Search Place textField
	private JButton firstSearchPlace_btn;   //first Place Search Button
	private JButton secondSearchPlace_btn;  //second Place Search  Button
	private JButton favorite_btn; //favorite Button
	private JButton middleSearch_btn; //middleLocation Search  Button
	private JButton searchAroundPlace;//aroundLocation Search Button
	private Panel searchTwoPlaceUIPanel = new Panel(); ; // Search_Panel of SearchTwoPlaceUI
	private JLabel googleMap = new JLabel(); //Map put Label in SearchTwoPlaceUI 
	private JList firstSearchPlace_list; //first place search result values in list
	private JList secondSearchPlace_list;//second place search result values in list
	private PlaceVO searchPlace = null, searchPlace2 = null; //value selected in list put PlaceVO
	private JButton mapZoonIn_btn,mapZoonOut_btn; //Map zoom :  in, out Button
	private boolean isChanging = false; //두개의 list를 번갈아서 클릭하기 때문에 구분해주는 변수가 필요하다
	private int isCurrentValue = 0; //Zoom Button 클릭시 사용하는 변수 (isCurrentValue�� 1: 첫번째주소가 현재, 2: 두번째주소가 현재, 3: 경로주소)
	private String location = "",location2 =""; //list를 클릭할때  zoom button 클릭할때  location정보를 받는 String
	private String pathLocation1,pathLocation2; //중간찾기 버튼을 클릭할때 , zoom button을 클릭할때  pathLocation의  정보를 받는  String (경로를 나타내기위해)
	JLabel lblNewLabel_1,lblNewLabel_2,lblNewLabel_3; //잠깐 확인용 (지울 예정)
	private PlaceDAO placeDao = new PlaceDAO();
	private TwoPlaceDAO twoPlaceDao = new TwoPlaceDAO();
	private MemberVO myInfo = null;
	private SearchTwoPlaceDao searchTwoPlaceDao = new SearchTwoPlaceDao(); 
	private FavoriteDAO favoriteDao = new FavoriteDAO();
	public SearchTwoPlaceUI(MemberVO mem) {
		setBackground(Color.WHITE);
		setSize(1220, 640);
		this.myInfo = mem;
		
		//gogJTabbedPane
		
		JLabel first_lb = new JLabel("Place1");
		first_lb.setBounds(14, 80, 75, 30);
		searchTwoPlaceUIPanel.add(first_lb);
		
		JLabel second_lb = new JLabel("Place2");
		second_lb.setBounds(14, 213, 58, 30);
		searchTwoPlaceUIPanel.add(second_lb);
		
		//
		searchTwoPlaceUIPanel.setLocation(34, 10);
		searchTwoPlaceUIPanel.setBackground(Color.WHITE);
		searchTwoPlaceUIPanel.setSize(425, 558);
		searchTwoPlaceUIPanel.setLayout(null);
		
		//
		firstSearchPlace_tf = new JTextField(30);
		secondSearchPlace_tf = new JTextField(30);
		firstSearchPlace_btn = new JButton("Search");
		secondSearchPlace_btn = new JButton("Search");
		firstSearchPlace_list = new JList();
		secondSearchPlace_list = new JList();

		
		searchTwoPlaceUIPanel.add(firstSearchPlace_tf);
		searchTwoPlaceUIPanel.add(firstSearchPlace_btn);
		//searchTwoPlaceUIPanel.add(firstSearchPlace_list);
		searchTwoPlaceUIPanel.add(secondSearchPlace_tf);
		searchTwoPlaceUIPanel.add(secondSearchPlace_btn);
		//searchTwoPlaceUIPanel.add(secondSearchPlace_list);
		
		firstSearchPlace_tf.setBounds(86, 80, 200, 30);
		firstSearchPlace_btn.setBounds(300, 77, 84, 36);
		firstSearchPlace_list.setBounds(58, 122, 236, 78);
		secondSearchPlace_tf.setBounds(86, 213, 200, 30);
		secondSearchPlace_btn.setBounds(300, 210, 84, 36);
		secondSearchPlace_list.setBounds(59, 255, 236, 78);
		

		
		firstSearchPlace_btn.addActionListener(this);
		secondSearchPlace_btn.addActionListener(this);
		
		
		firstSearchPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		//add(new JScrollPane(firstSearchPlace_list));
		firstSearchPlace_list.addListSelectionListener(this);
		
		secondSearchPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		//add(new JScrollPane(firstSearchPlace_list));
		secondSearchPlace_list.addListSelectionListener(this);
		
		
		JScrollPane scrollPane = new JScrollPane(firstSearchPlace_list);
		JScrollPane scrollPane2 = new JScrollPane(secondSearchPlace_list);
		scrollPane.setViewportView(firstSearchPlace_list);
		scrollPane2.setViewportView(secondSearchPlace_list);
		searchTwoPlaceUIPanel.add(scrollPane);
		searchTwoPlaceUIPanel.add(scrollPane2);
		scrollPane.setBounds(86, 123, 296, 78);
		scrollPane2.setBounds(86, 255, 298, 78);
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //������ũ�Ѹ� ���
		//scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		
		favorite_btn = new JButton("Favorite");
		favorite_btn.setBounds(86, 361, 119, 27);
		favorite_btn.addActionListener(this);
		searchTwoPlaceUIPanel.add(favorite_btn);
		
		middleSearch_btn = new JButton("Middle");
		middleSearch_btn.setBounds(251, 361, 119, 27);
		middleSearch_btn.addActionListener(this);
		searchTwoPlaceUIPanel.add(middleSearch_btn);
		
	
		
		lblNewLabel_1 = new JLabel("firstPlace info");
		lblNewLabel_1.setBounds(14, 414,356, 18);
		searchTwoPlaceUIPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("secondPlace info");
		lblNewLabel_2.setBounds(14, 447, 356, 30);
		searchTwoPlaceUIPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Lat, Lon of middlePlace ");
		lblNewLabel_3.setBounds(14, 489, 356, 30);
		searchTwoPlaceUIPanel.add(lblNewLabel_3);
		
		
		googleMap.setBounds(500, 50, 593, 550);
		setLayout(null);
				
		//��ü�� searchTwoPlaceUIPanel,Label(googleMap)�� ���
		add(searchTwoPlaceUIPanel);
		
		
		searchAroundPlace = new JButton("Around");
		searchAroundPlace.setBounds(265, 531, 105, 27);
		searchAroundPlace.addActionListener(this);
		searchTwoPlaceUIPanel.add(searchAroundPlace);
		
		
		//zoom 인Button
		mapZoonIn_btn = new JButton("+");
		mapZoonIn_btn.setBounds(1126, 526, 65, 27);
		mapZoonIn_btn.addActionListener(this);
		add(mapZoonIn_btn);
		
		//zoom 아웃 Button
		mapZoonOut_btn = new JButton("-");
		mapZoonOut_btn.setBounds(1126, 565, 65, 27);
		mapZoonOut_btn.addActionListener(this);
		add(mapZoonOut_btn);
		
		
		add(googleMap);
		setVisible(true);
		
	}

	//액션 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	    //첫번째 장소 검색 
		if(e.getSource() == firstSearchPlace_btn ) {
			String firstSearchPlace = firstSearchPlace_tf.getText();
			ArrayList<PlaceVO> sp = searchTwoPlaceDao.receivekakaoMapAPI(firstSearchPlace);
			if(sp!=null) {
				firstSearchPlace_list.setListData(sp.toArray());
			}
		}	
		//두번째 장소 검색
		if(e.getSource() == secondSearchPlace_btn ) {
			String secondSearchPlace = secondSearchPlace_tf.getText();
			ArrayList<PlaceVO> sp2 = searchTwoPlaceDao.receivekakaoMapAPI(secondSearchPlace);
			if(sp2!=null) {
				secondSearchPlace_list.setListData(sp2.toArray());
			}
		}	
		//즐겨찾기 버튼 검색
		if(e.getSource() == favorite_btn) {
			
			//�켱 PlaceVO�� searchPlace,searchPlace2�� DB(���̺�Place)�� ����
			//PlaceVO sp = new PlaceVO(x + "" + y, address_name, x, y);// x:lon:�浵    // y:lat:����
			
			//유효성 확인 후 Place 테이블에 searchPlace, searchPlace2 삽입
			if(placeDao.placeIDCheck(searchPlace.getPlaceID()) == 0) {
				System.out.println("searchPlace 삽입");
				placeDao.insertPlace(searchPlace);
			}
			if(placeDao.placeIDCheck(searchPlace2.getPlaceID()) == 0) {
				System.out.println("searchPlace2 삽입");
				placeDao.insertPlace(searchPlace2);
			}
			
			TestPlaceVO testPlaceVO = new TestPlaceVO(searchPlace.getPlaceID(), searchPlace2.getPlaceID());
			
			//유효성 확인 후 TwoPlace 테이블에 testPlaceVO(searchPlace, searchPlace2) 삽입
			if(twoPlaceDao.twoPlaceCheck(testPlaceVO) == 0) {
				System.out.println("twoPlaceDao에 testPlaceVO 삽입");
				twoPlaceDao.insertTwoPlace(testPlaceVO);

			}else {
				System.out.println("동일한 값이 존재합니다. 확인해보세용!");
			}
			FavoriteVO favoriteVO = new FavoriteVO(myInfo.getMemID(),twoPlaceDao.findIdTwoPlace(testPlaceVO));
			
			System.out.println(favoriteVO);
			if(favoriteDao.favoriteCheck(favoriteVO) == 0) {
				System.out.println("favoriteDao에 favoriteVO 삽입");
				favoriteDao.insertFavorite(favoriteVO);
			}else {
				System.out.println("즐겨찾기등록이 안되용 동일한값이 존재해서ㅠㅠ!");
			}
		
			
		}	
		//중간찾기 버튼 검색
		if(e.getSource() == middleSearch_btn) {
			System.out.println(searchPlace);
			System.out.println(searchPlace2);
			if(searchPlace != null && searchPlace2 != null) {
				lblNewLabel_1.setText(searchPlace +"");
				lblNewLabel_2.setText(searchPlace2 +"");
				lblNewLabel_3.setText(searchTwoPlaceDao.middlePlaceLon(searchPlace,searchPlace2)+","+searchTwoPlaceDao.middlePlaceLat(searchPlace,searchPlace2));
			}else {
				System.out.println("searchPlace와 searchPlace2가 없습니당");
			}
			pathLocation1 = searchPlace.getPlaceLat()+","+searchPlace.getPlaceLon();
			pathLocation2 = searchPlace2.getPlaceLat()+","+searchPlace2.getPlaceLon();
			middleSearchClickSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(9));
			setSize(1350, 800);
			searchTwoPlaceDao.zoom=11;
			isCurrentValue = 3;
			
		}	
		//주변찾기 버튼 검색
		if(e.getSource() ==searchAroundPlace) {
			System.out.println("searchTwoPLaceUI생성");
			AroundPlaceUI aroundPlaceUI = new AroundPlaceUI(searchTwoPlaceDao.middlePlaceInfoArray(searchPlace,searchPlace2));
			//aroundPlaceUI.setPostAroundPlaceArray(searchTwoPlaceDao.middlePlaceInfoArray(searchPlace,searchPlace2));
			aroundPlaceUI.setVisible(true);
		}
		
		//zoom인
		if(e.getSource() == mapZoonIn_btn){
			
			 //searchTwoPlaceDao.valueZoom(1) : 줌 인
			 if(isCurrentValue == 1) {
				 	zoomSetMap(location,searchTwoPlaceDao.valueZoom(1));
				    setSize(1350, 800);
			 }else if(isCurrentValue == 2) {
				 	zoomSetMap(location2,searchTwoPlaceDao.valueZoom(1));
					setSize(1350, 800);
			 }else if(isCurrentValue == 3) {
		    	  zoomSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(1));
				  setSize(1350, 800);
		     }
			 
		}
		//zoom아웃
		if(e.getSource() == mapZoonOut_btn){
			
			 //searchTwoPlaceDao.valueZoom(0) : 줌 아웃
			 if(isCurrentValue == 1) {
				 	zoomSetMap(location,searchTwoPlaceDao.valueZoom(0));
					setSize(1350, 800);
			 }else if(isCurrentValue == 2) {
				 	zoomSetMap(location2,searchTwoPlaceDao.valueZoom(0));
					setSize(1350, 800);
		     }else if(isCurrentValue == 3) {
		    	  zoomSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(0));
					setSize(1350, 800);
		     }
		}
	}


	//Item in List selected
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
		if(!e.getValueIsAdjusting()) { //한번의 값만 받도록 도와줌
			
			if(!isChanging) {
				//첫번째 리스트 select
				if(e.getSource() == firstSearchPlace_list&&firstSearchPlace_list.getSelectedIndex()!=-1) {
					//선택된 값을  SearchPlace VO에 넣음
					System.out.println("�̰� ���´ٸ� list�� ��������ʾҴµ��� ���� �����°���.");
					isChanging = true;
					searchPlace = (PlaceVO)firstSearchPlace_list.getSelectedValue();
					location = searchPlace.getPlaceLat()+","+searchPlace.getPlaceLon();
					//지도생성
					listClickSetMap(location,searchTwoPlaceDao.valueZoom(9));
					//크기재지정
					setSize(1220, 640);
					
					searchTwoPlaceDao.zoom=11;
					//secondSearchPlace_list를 선택하고  firstSearchPlace_list를 다시 선택할때 다시 지도 보여주기위해
					secondSearchPlace_list.clearSelection();
					System.out.println(searchPlace);
					isChanging = false;
					isCurrentValue = 1;
				}
			}
			if(!isChanging) {
				//두번째 리스트 select
				if(e.getSource() == secondSearchPlace_list&&secondSearchPlace_list.getSelectedIndex()!=-1) {
					isChanging = true;
					searchPlace2 = (PlaceVO)secondSearchPlace_list.getSelectedValue();
					location2 = searchPlace2.getPlaceLat()+","+searchPlace2.getPlaceLon();
					listClickSetMap(location2,searchTwoPlaceDao.valueZoom(9));
					setSize(1350, 800);
					searchTwoPlaceDao.zoom=11;
					firstSearchPlace_list.clearSelection();
					System.out.println(searchPlace2);
					isChanging = false;
					isCurrentValue = 2;
				}	
			}
			
		}
				
	}
	
	//첫번째, 두번째 장소에 대한 googleMap zoom 클릭시
	public void zoomSetMap(String location,int zoom) {
		searchTwoPlaceDao.zoomDownloadMap(location,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getMap(location,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//첫번째, 두번째 장소에 대한 googleMap
	public void listClickSetMap(String location,int zoom) {
		searchTwoPlaceDao.listClickdownloadMap(location,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getMap(location,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//중간찾기에서  googleMap zoom 클릭시
	public void zoomSetMap(String location,String location2,int zoom) {
		searchTwoPlaceDao.zoomMiddleSearchDownloadMap(location,location2,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//중간찾기버튼을 클릭시 경로를 Map에 보여줌
	public void middleSearchClickSetMap(String location,String location2,int zoom) {
		searchTwoPlaceDao.middleSearchClickdownloadMap(location,location2,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
		searchTwoPlaceDao.fileDelete(location+location2+""+zoom);
	}
	

}
