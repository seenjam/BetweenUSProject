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

public class AroundPlaceUI extends JDialog implements ActionListener,ListSelectionListener{
	private JList aroundPlace_list; // around places list
	private JComboBox category_combo,radius_combo; //  comboBox of category, radius 
	private JButton aroundTable_btn; //ī�װ� ,�ݰ濡 ����  �ֺ� ã�� ��ư
	private JLabel googleMap; 		// ī�װ�, �ݰ濡 ���� ����
	private String columnNames[]= {"��Ҹ�","�ּ�","��ȭ��ȣ","�浵","����"};
	private JButton mapZoonIn_btn,mapZoonOut_btn;
	private AroundPlaceDAO aroundPlaceDAO = new AroundPlaceDAO();
	private String location;
	private JLabel firstPlace_lb,secondPlace_lb,middlePlaceLon_lb,middlePlaceLat_lb; //���������ֱ��/.// ��������!

	public AroundPlaceUI(String[] middlePlaceInfoArray) {
		
		setPostAroundPlaceArray(middlePlaceInfoArray); //�߰���ġ�� ������ set (ù��°���, �ι�°���,lon�浵 ,lat����)
		aroundPlaceDAO.setCategory_map(); //ī�װ� ���� set (����ö��, ī��, ������ ���)
		
		setSize(1220, 640);
		getContentPane().setLayout(null);
		
		firstPlace_lb = new JLabel("ù��°���");
		firstPlace_lb.setBounds(59, 47, 306, 18);
		getContentPane().add(firstPlace_lb);
		firstPlace_lb.setText(getPostAroundPlaceArray()[0]);
		
		
		secondPlace_lb = new JLabel("�ι�° ���");
		secondPlace_lb.setBounds(59, 77, 326, 18);
		getContentPane().add(secondPlace_lb);
		secondPlace_lb.setText(getPostAroundPlaceArray()[1]);

		
		middlePlaceLon_lb = new JLabel("�߰���ġ�� �浵 LON");
		middlePlaceLon_lb.setBounds(59, 107, 326, 18);
		getContentPane().add(middlePlaceLon_lb);
		middlePlaceLon_lb.setText(getPostAroundPlaceArray()[2]);
		
		
		middlePlaceLat_lb = new JLabel("�߰� ��ġ�� ���� LAT");
		middlePlaceLat_lb.setBounds(59, 138, 326, 18);
		getContentPane().add(middlePlaceLat_lb);
		middlePlaceLat_lb.setText(getPostAroundPlaceArray()[3]);

		
		
		
		
		category_combo = new JComboBox();
		for (Map.Entry<String, String> s: aroundPlaceDAO.getCategory_map().entrySet()) {
			category_combo.addItem(s.getKey());
			System.out.println(s.getKey());
		}
		category_combo.setBounds(56, 201, 96, 24);
		getContentPane().add(category_combo);
		
		
		radius_combo = new JComboBox();
		for(int r : aroundPlaceDAO.getRadius()) {
			radius_combo.addItem(r);
		}
		radius_combo.setBounds(166, 201, 85, 24);
		getContentPane().add(radius_combo);
		
		aroundTable_btn = new JButton("Search");
		aroundTable_btn.setBounds(59, 237, 105, 27);
		getContentPane().add(aroundTable_btn);
		aroundTable_btn.addActionListener(this);
		
		
		//DefaultTableModel dtm=new DefaultTableModel(columnNames);
		//JTable aroundPlace_list = new JTable(dtm);
		
		
		aroundPlace_list = new JList();
		JScrollPane scrollPane_1 = new JScrollPane(aroundPlace_list);
		scrollPane_1.setBounds(33, 285, 392, 277);
		getContentPane().add(scrollPane_1);
		//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		aroundPlace_list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//�ѹ��� �� �׸� ������ �� �ִ� ���� ����Ʈ�� ���
		//add(new JScrollPane(firstSearchPlace_list));
		aroundPlace_list.addListSelectionListener(this);
		
		
		googleMap = new JLabel();
		googleMap.setBounds(447, 47, 666, 502);
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
			
			System.out.println("getPostAroundPlaceArray()[2] : "+ getPostAroundPlaceArray()[2]); //lon,�浵
			System.out.println("getPostAroundPlaceArray()[3] : "+ getPostAroundPlaceArray()[3]); //lat,����
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
	
	//���� �����ѰͿ� ���� json ������ table�� ����! 
	public void setPostAroundPlaceArray(String[] middlePlaceInfoArray) {
		// TODO Auto-generated method stub
		aroundPlaceDAO.setPostAroundPlace(middlePlaceInfoArray);

	}
	public String[] getPostAroundPlaceArray() {
		//����: ù��°���, �ι�°���, Lon(�浵) , Lat(����)
		return aroundPlaceDAO.getPostAroundPlace();
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if(!e.getValueIsAdjusting()) {
			
			if(e.getSource() == aroundPlace_list&&aroundPlace_list.getSelectedIndex()!=-1) {
				//���õ� ���� SearchPlace VO�� ����
				System.out.println("�̰� ���´ٸ� list�� ��������ʾҴµ��� ���� �����°���.");
				CategoryRadiusPlace categoryRadiusPlace = (CategoryRadiusPlace)aroundPlace_list.getSelectedValue();
				location = categoryRadiusPlace.getLat()+","+categoryRadiusPlace.getLon();
				//���� ����
				listClickSetMap(location,aroundPlaceDAO.valueZoom(9));
				//ũ��������
				setSize(1220, 640);
				
				aroundPlaceDAO.zoom=11;
				//secondSearchPlace_list�� �����ϰ� �ٽ� firstSearchPlace_list�� ������ �� �ٽ� ���� �����ֱ� ����
				System.out.println(categoryRadiusPlace);
				
			}
		}
	}

	//������ googleMap�� Ȯ��, ��ҽ� ��� 
	public void zoomSetMap(String location,int zoom) {
		aroundPlaceDAO.zoomDownloadMap(location,zoom);
		googleMap.setIcon(aroundPlaceDAO.getMap(location,zoom));
		aroundPlaceDAO.fileDelete(location+""+zoom);
	}
	//�ּ�list�� Ŭ���Ͽ� googleMap�� ������ �� ���
	public void listClickSetMap(String location,int zoom) {
		aroundPlaceDAO.listClickdownloadMap(location,zoom);
		googleMap.setIcon(aroundPlaceDAO.getMap(location,zoom));
		aroundPlaceDAO.fileDelete(location+""+zoom);
	}
}
