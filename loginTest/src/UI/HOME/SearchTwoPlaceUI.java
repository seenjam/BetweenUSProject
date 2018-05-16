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

import DAO.PlaceDAO;
import DAO.SearchTwoPlaceDao;
import VO.PlaceVO;

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
	private JList firstSearchPlace_list; //
	private JList secondSearchPlace_list;//�ι�° ��� �Է¿� ���� ����� List
	private PlaceVO searchPlace = null, searchPlace2 = null; //list���� ���õ� ���� �� SearchPlace VO
	private JButton mapZoonIn_btn,mapZoonOut_btn; //Map ����, �ܾƿ� Button
	private boolean isChanging = false; //�� ���� list�� �����Ƽ� Ŭ���ϱ� ������ �������ִ� ������ �ʿ�
	private int isCurrentValue = 0; //Zoom Button Ŭ���� ����ϴ� ���� (isCurrentValue�� 1: ù��° �ּҰ� ���� , 2:�ι�° �ּҰ� ���� ,3: ��� �ּ�)
	private String location = "",location2 =""; //list�� Ŭ���� ��, zoom button Ŭ���� �� location�� ������ �޴� String 
	private String pathLocation1,pathLocation2; //�߰�ã�� ��ư�� Ŭ���� ��, zoom button Ŭ���� �� pathLocation�� ������ �޴� String 
	JLabel lblNewLabel_1,lblNewLabel_2,lblNewLabel_3; //��� Ȯ�ο� (���￹��) ************
	private PlaceDAO placeDao = new PlaceDAO();
	private SearchTwoPlaceDao searchTwoPlaceDao = new SearchTwoPlaceDao(); 
	public SearchTwoPlaceUI() {
		setSize(1220, 640);
		
		
		//gogJTabbedPane
		
		JLabel first_lb = new JLabel("Place1");
		first_lb.setBounds(14, 80, 42, 30);
		searchTwoPlaceUIPanel.add(first_lb);
		
		JLabel second_lb = new JLabel("Place2");
		second_lb.setBounds(14, 213, 42, 30);
		searchTwoPlaceUIPanel.add(second_lb);
		
		//�˻�â panel�� ��ġ ����
		searchTwoPlaceUIPanel.setLocation(87, 42);
		searchTwoPlaceUIPanel.setBackground(Color.WHITE);
		searchTwoPlaceUIPanel.setSize(372, 558);
		searchTwoPlaceUIPanel.setLayout(null);
		
		//����
		firstSearchPlace_tf = new JTextField(30);
		secondSearchPlace_tf = new JTextField(30);
		firstSearchPlace_btn = new JButton("Search");
		secondSearchPlace_btn = new JButton("Search");
		firstSearchPlace_list = new JList();
		secondSearchPlace_list = new JList();

		
		//�˻�â panel�ȿ� textField, button, List ���
		searchTwoPlaceUIPanel.add(firstSearchPlace_tf);
		searchTwoPlaceUIPanel.add(firstSearchPlace_btn);
		//searchTwoPlaceUIPanel.add(firstSearchPlace_list);
		searchTwoPlaceUIPanel.add(secondSearchPlace_tf);
		searchTwoPlaceUIPanel.add(secondSearchPlace_btn);
		//searchTwoPlaceUIPanel.add(secondSearchPlace_list);
		
		//�˻�â panel�ȿ� textField, button, List ��ġ����
		firstSearchPlace_tf.setBounds(59, 80, 126, 30);
		firstSearchPlace_btn.setBounds(210, 77, 84, 36);
		firstSearchPlace_list.setBounds(58, 122, 236, 78);
		secondSearchPlace_tf.setBounds(59, 213, 126, 30);
		secondSearchPlace_btn.setBounds(210, 210, 84, 36);
		secondSearchPlace_list.setBounds(59, 255, 236, 78);
		
		//btn�� ActionListener ���
		firstSearchPlace_btn.addActionListener(this);
		secondSearchPlace_btn.addActionListener(this);
		
		
		//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		firstSearchPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		//add(new JScrollPane(firstSearchPlace_list));
		firstSearchPlace_list.addListSelectionListener(this);
		
		//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		secondSearchPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		//add(new JScrollPane(firstSearchPlace_list));
		secondSearchPlace_list.addListSelectionListener(this);
		
		
		//list1,2�� scrollpane ����
		JScrollPane scrollPane = new JScrollPane(firstSearchPlace_list);
		JScrollPane scrollPane2 = new JScrollPane(secondSearchPlace_list);
		scrollPane.setViewportView(firstSearchPlace_list);
		scrollPane2.setViewportView(secondSearchPlace_list);
		searchTwoPlaceUIPanel.add(scrollPane);
		searchTwoPlaceUIPanel.add(scrollPane2);
		scrollPane.setBounds(58, 122, 236, 78);
		scrollPane2.setBounds(59, 255, 236, 78);
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //������ũ�Ѹ� ���
		//scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		
		favorite_btn = new JButton("Favorite");
		favorite_btn.setBounds(34, 361, 105, 27);
		favorite_btn.addActionListener(this);
		searchTwoPlaceUIPanel.add(favorite_btn);
		
		middleSearch_btn = new JButton("Middle");
		middleSearch_btn.setBounds(167, 361, 105, 27);
		middleSearch_btn.addActionListener(this);
		searchTwoPlaceUIPanel.add(middleSearch_btn);
		
	
		
		lblNewLabel_1 = new JLabel("firstPlace info");
		lblNewLabel_1.setBounds(34, 414,200, 18);
		searchTwoPlaceUIPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("secondPlace info");
		lblNewLabel_2.setBounds(34, 447, 200, 30);
		searchTwoPlaceUIPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Lat, Lon of middlePlace ");
		lblNewLabel_3.setBounds(34, 480, 200, 50);
		searchTwoPlaceUIPanel.add(lblNewLabel_3);
		
		
		//Map�� ��Ÿ���� Label�� ��ġ ����
		googleMap.setBounds(500, 50, 593, 550);
		setLayout(null);
				
		//��ü�� searchTwoPlaceUIPanel,Label(googleMap)�� ���
		add(searchTwoPlaceUIPanel);
		
		
		searchAroundPlace = new JButton("Around");
		searchAroundPlace.setBounds(34, 519, 105, 27);
		searchAroundPlace.addActionListener(this);
		searchTwoPlaceUIPanel.add(searchAroundPlace);
		
		
		//zoom Ȯ�� Button
		mapZoonIn_btn = new JButton("+");
		mapZoonIn_btn.setBounds(1126, 526, 65, 27);
		mapZoonIn_btn.addActionListener(this);
		add(mapZoonIn_btn);
		
		//zoom ��� Button
		mapZoonOut_btn = new JButton("-");
		mapZoonOut_btn.setBounds(1126, 565, 65, 27);
		mapZoonOut_btn.addActionListener(this);
		add(mapZoonOut_btn);
		
		
		add(googleMap);
		setVisible(true);
		
	}

	//��ư Ŭ���� �׼� ������
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	    //ù��° ��ġ ã�� ��ư Ŭ���� 
		if(e.getSource() == firstSearchPlace_btn ) {
			String firstSearchPlace = firstSearchPlace_tf.getText();
			ArrayList<PlaceVO> sp = searchTwoPlaceDao.receivekakaoMapAPI(firstSearchPlace);
			if(sp!=null) {
				firstSearchPlace_list.setListData(sp.toArray());
			}
		}	
		//�ι�° ��ġ ã�� ��ư Ŭ���� 
		if(e.getSource() == secondSearchPlace_btn ) {
			String secondSearchPlace = secondSearchPlace_tf.getText();
			ArrayList<PlaceVO> sp2 = searchTwoPlaceDao.receivekakaoMapAPI(secondSearchPlace);
			if(sp2!=null) {
				secondSearchPlace_list.setListData(sp2.toArray());
			}
		}	
		//���ã�� ��ư Ŭ���� 
		if(e.getSource() == favorite_btn) {
			
			//�켱 PlaceVO�� searchPlace,searchPlace2�� DB(���̺�Place)�� ����
			//PlaceVO sp = new PlaceVO(x + "" + y, address_name, x, y);// x:lon:�浵    // y:lat:����
			placeDao.insertPlace(searchPlace);
			placeDao.insertPlace(searchPlace2);

			
			
		}	
		//�߰���ġ ã�� ��ư Ŭ����
		if(e.getSource() == middleSearch_btn) {
			System.out.println(searchPlace);
			System.out.println(searchPlace2);
			if(searchPlace != null && searchPlace2 != null) {
				lblNewLabel_1.setText(searchPlace +"");
				lblNewLabel_2.setText(searchPlace2 +"");
				lblNewLabel_3.setText(searchTwoPlaceDao.middlePlaceLon(searchPlace,searchPlace2)+","+searchTwoPlaceDao.middlePlaceLat(searchPlace,searchPlace2));
			}else {
				System.out.println("�� ��Ҹ� ��� �����ؾ� �����մϴ�.");
			}
			pathLocation1 = searchPlace.getPlaceLat()+","+searchPlace.getPlaceLon();
			pathLocation2 = searchPlace2.getPlaceLat()+","+searchPlace2.getPlaceLon();
			middleSearchClickSetMap(pathLocation1,pathLocation2,searchTwoPlaceDao.valueZoom(9));
			setSize(1350, 800);
			searchTwoPlaceDao.zoom=11;
			isCurrentValue = 3;
			
		}	
		//�ֺ���� ã�� ��ư Ŭ����
		if(e.getSource() ==searchAroundPlace) {
			System.out.println("searchTwoPLaceUI���� �ֺ����ã���ư Ŭ���Ҷ��� if ");
			AroundPlaceUI aroundPlaceUI = new AroundPlaceUI(searchTwoPlaceDao.middlePlaceInfoArray(searchPlace,searchPlace2));
			//aroundPlaceUI.setPostAroundPlaceArray(searchTwoPlaceDao.middlePlaceInfoArray(searchPlace,searchPlace2));
			aroundPlaceUI.setVisible(true);
		}
		
		//zoom ��ư Ŭ����
		if(e.getSource() == mapZoonIn_btn){
			
			 //searchTwoPlaceDao.valueZoom(1) : Ȯ��
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
		//zoom ��ư Ŭ����
		if(e.getSource() == mapZoonOut_btn){
			
			 //searchTwoPlaceDao.valueZoom(0) : ���
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
		
		if(!e.getValueIsAdjusting()) { //���� �ι����� �ʵ��� ����� ��.
			
			if(!isChanging) {
				//ù��° ����Ʈ select
				if(e.getSource() == firstSearchPlace_list&&firstSearchPlace_list.getSelectedIndex()!=-1) {
					//���õ� ���� SearchPlace VO�� ����
					System.out.println("�̰� ���´ٸ� list�� ��������ʾҴµ��� ���� �����°���.");
					isChanging = true;
					searchPlace = (PlaceVO)firstSearchPlace_list.getSelectedValue();
					location = searchPlace.getPlaceLat()+","+searchPlace.getPlaceLon();
					//���� ����
					listClickSetMap(location,searchTwoPlaceDao.valueZoom(9));
					//ũ��������
					setSize(1220, 640);
					
					searchTwoPlaceDao.zoom=11;
					//secondSearchPlace_list�� �����ϰ� �ٽ� firstSearchPlace_list�� ������ �� �ٽ� ���� �����ֱ� ����
					secondSearchPlace_list.clearSelection();
					System.out.println(searchPlace);
					isChanging = false;
					isCurrentValue = 1;
				}
			}
			if(!isChanging) {
				//�ι�° ����Ʈ select
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
	
	//������ googleMap�� Ȯ��, ��ҽ� ��� 
	public void zoomSetMap(String location,int zoom) {
		searchTwoPlaceDao.zoomDownloadMap(location,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getMap(location,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//�ּ�list�� Ŭ���Ͽ� googleMap�� ������ �� ���
	public void listClickSetMap(String location,int zoom) {
		searchTwoPlaceDao.listClickdownloadMap(location,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getMap(location,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//������ googleMap�� Ȯ��, ��ҽ� ��� 
	public void zoomSetMap(String location,String location2,int zoom) {
		searchTwoPlaceDao.zoomMiddleSearchDownloadMap(location,location2,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
		searchTwoPlaceDao.fileDelete(location+""+zoom);
	}
	//�߰�ã�� btn�� Ŭ���Ͽ� googleMap�� ������ �� ���
	public void middleSearchClickSetMap(String location,String location2,int zoom) {
		searchTwoPlaceDao.middleSearchClickdownloadMap(location,location2,zoom);
		googleMap.setIcon(searchTwoPlaceDao.getPathMap(location,location2,zoom));
		searchTwoPlaceDao.fileDelete(location+location2+""+zoom);
	}
	

}
