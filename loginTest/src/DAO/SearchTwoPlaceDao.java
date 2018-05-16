package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import VO.PlaceVO;

public class SearchTwoPlaceDao {
	public int zoom = 11;
	private String[] valueMiddlePlace= new String[4]; //MiddlePlace�� ����(ù��°���, �ι�°���, lon, lat)

	public ArrayList<PlaceVO> receivekakaoMapAPI(String Searchplace) {

		ArrayList<PlaceVO> searchPlace_aList = null; // 검색한 위치의 위치목록 (검색어 신사동의 위치목록 :강남구 신사동, 관악구 신사동 ...)
		ArrayList<PlaceVO> searchPlaceAround_aList = new ArrayList<>();
		try {
			// url : kakao map api
			URL obj = new URL("https://dapi.kakao.com/v2/local/search/address.json?query="
					+ URLEncoder.encode(Searchplace, "UTF-8"));
			
			System.out.println(Searchplace);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// API키 값 등록
			con.setRequestProperty("Authorization", "KakaoAK b6ec5ff96d29443fdeb760fcbcf289ef");
			/* con.setRequestProperty("Accept-Charset", "UTF-8"); */

			int responseCode = con.getResponseCode();
			System.out.println("응답 코드 : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("응답내용 : ");
			System.out.println(response.toString());

			JSONObject json = new JSONObject(response.toString());
			JSONObject jsonMeta = json.getJSONObject("meta");
			// JSONObject jsonDocuments = json.getJSONObject("documents");
			JSONArray jsonDoucumentArray = (JSONArray) json.get("documents");
			int searchCount = jsonMeta.getInt("total_count");
			searchPlace_aList = new ArrayList<>();

			// 한개 이상의 위치를 찾았을 때
			if (searchCount != 0) {
				for (int i = 0; i < searchCount; i++) {

					JSONObject o = (JSONObject) jsonDoucumentArray.get(i);
					String address_name = o.getString("address_name");
					JSONObject o2 = o.getJSONObject("address");
					String x = o2.getString("x"); // x:lon:경도
					String y = o2.getString("y"); // y:lat:위도
					PlaceVO sp = new PlaceVO(x + "" + y, address_name, x, y);// x:lon경도    // y:lat:위도
					searchPlace_aList.add(sp);
				}
			} else {
				System.out.println("not found.....");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return searchPlace_aList;

	}

	public int valueZoom(int inOut) {
		// zoom 증가
		if (inOut == 1) {
			return zoom++;
		}	
		// zoom 축소
		else if (inOut == 0) {
			return zoom--;	
		} else {
			//inOut값이 9일때는 zoom 버튼을 클릭할 때가 아니라 list를 클릭 시 
		}
		 return zoom;

	}
	//zoom으로 dowmloadMap
	public void zoomDownloadMap(String location, int zoom) {
		// TODO Auto-generated method stub
		try {

			System.out.println(location + ",zoom값" + zoom);

			//location은 "latitude,longitude" 순서대로 들어간다.
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + location + "&zoom=" + zoom
					+ "&size=300x300" + "&scale=2" + "&markers=color:blue%7Clabel:S%7C" + location
					+ "&key=AIzaSyBmI8tw1VHRQKDavypHLpgwcXKrG-Xx08Y ";

			// �// 구글로 부터 map image를 읽는다
			// then save it to a local file: location + "" + zoom
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location + "" + zoom);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//list 클릭 할때 dowmloadMap
		public void listClickdownloadMap(String location, int zoom) {
			// TODO Auto-generated method stub
			try {
				
				//zoom값을 11로 초기화 시켜준다.
			zoom = 11;
			System.out.println(location + ",zoom값" + zoom);
			
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + location + "&zoom=" + zoom
					+ "&size=300x300" + "&scale=2" + "&markers=color:blue%7Clabel:S%7C" + location
					+ "&key=AIzaSyBmI8tw1VHRQKDavypHLpgwcXKrG-Xx08Y ";

			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location + "" + zoom);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void middleSearchClickdownloadMap(String location,String location2,int zoom) {
		// TODO Auto-generated method stub
		try {
			
			//zoom값을 11로 초기화 시켜준다.
			zoom = 11;
		
			System.out.println(location + "," + zoom);
			//location은 lat, lon 순서대로 들어간다
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"
					+ "size=300x300" 
					+ "&zoom=" + zoom
					+ "&scale=2" 
					+ "&path=color:0x0000ff|weight:5|"
					+ location
					+ "|"
					+ location2
					+ "&key=AIzaSyBmI8tw1VHRQKDavypHLpgwcXKrG-Xx08Y ";

			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(location + location2+"" + zoom); //location+location2+""+zoom
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//zoom �Ҷ� dowmloadMap
		public void zoomMiddleSearchDownloadMap(String location,String location2, int zoom) {
			// TODO Auto-generated method stub
			try {

				System.out.println(location + ",zoom��" + zoom);

				// location�� "latitude,longitude" ������� ����.
				String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"
						+ "size=300x300" 
						+ "&zoom=" + zoom
						+ "&scale=2" 
						+ "&path=color:0x0000ff|weight:5|"
						+ location
						+ "|"
						+ location2
						+ "&key=AIzaSyBmI8tw1VHRQKDavypHLpgwcXKrG-Xx08Y ";

				// ���۷� ���� map image�� �д´�
				// then save it to a local file: location + "" + zoom
				URL url = new URL(imageUrl);
				InputStream is = url.openStream();
				OutputStream os = new FileOutputStream(location + location2+"" + zoom);
				byte[] b = new byte[2048];
				int length;
				while ((length = is.read(b)) != -1) {
					os.write(b, 0, length);
				}
				is.close();
				os.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	public ImageIcon getMap(String location, int zoom) {
		// TODO Auto-generated method stub
		return new ImageIcon((new ImageIcon(location + "" + zoom)).getImage());
	}
	public ImageIcon getPathMap(String location,String location2, int zoom) {
		return new ImageIcon((new ImageIcon(location + location2+"" + zoom)).getImage());
	}

	public void fileDelete(String fileName) {
		// TODO Auto-generated method stub
		File f = new File(fileName);
		f.delete();
	}

	public Double middlePlaceLon(PlaceVO sp, PlaceVO sp2) {
		String spLon_str = sp.getPlaceLon();
		String spLon2_str = sp2.getPlaceLon();
		double sp_lon = 0, sp2_lon = 0; 
		if(!"".equals(spLon_str)&&!"".equals(spLon2_str)){
			 sp_lon = Double.parseDouble(spLon_str);
		     sp2_lon = Double.parseDouble(spLon2_str);
		}
		double middlePlaceLon = (sp_lon + sp2_lon) / 2;
		
		return middlePlaceLon;
	}
	
	public Double middlePlaceLat(PlaceVO sp, PlaceVO sp2) {
		String spLat_str = sp.getPlaceLat();
		String spLat2_str = sp2.getPlaceLat();
		double sp_lat = 0, sp2_lat= 0; 
		if(!"".equals(spLat_str)&&!"".equals(spLat2_str)){
			 sp_lat = Double.parseDouble(spLat_str);
		     sp2_lat = Double.parseDouble(spLat2_str);
		}
		double middlePlaceLat = (sp_lat + sp2_lat) / 2;
		
		return middlePlaceLat;
	}
	public String[] middlePlaceInfoArray(PlaceVO sp, PlaceVO sp2) {
		
		valueMiddlePlace[0] = new String(sp +"");
		valueMiddlePlace[1] = new String(sp2 +"");
		valueMiddlePlace[2] = new String(middlePlaceLon(sp, sp2) +"");
		valueMiddlePlace[3] = new String(middlePlaceLat(sp, sp2) +"");
		System.out.println("middlePlaceInfoArray�� ���Դ�");
		/*System.out.println("***************");
		for (int i = 0; i < valueMiddlePlace.length; i++) {
			System.out.println(valueMiddlePlace[i]);
		}
		System.out.println("***************");*/ //�̰� ����
		return valueMiddlePlace;
	}
 }
