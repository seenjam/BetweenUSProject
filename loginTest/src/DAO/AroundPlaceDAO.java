package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

import org.json.JSONArray;
import org.json.JSONObject;

import VO.CategoryRadiusPlace;

public class AroundPlaceDAO {

	private String[] postAroundPlace= new String[4];
	private HashMap<String,String> category_map = new HashMap<>();
	private int[] radius = {500,1000,3000,5000}; //500m(500), 1km(1000), 3km(3000), 5km(5000)
	public int zoom;
	
	public HashMap<String, String> getCategory_map() {
		return category_map;
	}
	public void setCategory_map() {
		category_map.put("지하철역","SW8");
		category_map.put("음식점","FD6");
		category_map.put("카페","CE7");
		category_map.put("병원","HP8");
		category_map.put("숙박","AD5");
		category_map.put("관광명소","AT4");
		category_map.put("문화시설","CT1");
		category_map.put("대형마트","MT1");
	}
	public int[] getRadius() {
		return radius;
	}

	public void setPostAroundPlace(String[] middlePlaceInfoArray) {
		
		for (int i = 0; i < middlePlaceInfoArray.length; i++) {
			postAroundPlace[i] = middlePlaceInfoArray[i];
		}

	}
	public String[] getPostAroundPlace() {
		return postAroundPlace;
	}
	public ArrayList<CategoryRadiusPlace> receivekakaoMapAPI(String category,String lon, String lat,String radius) {

		//��) �Ｚ�� �ݰ� 20km�̳��� �������� arraylist
		ArrayList<CategoryRadiusPlace> CategoryRadiusPlace_alist = new ArrayList<>();
		try {
			

	        URL obj = new URL(" https://dapi.kakao.com/v2/local/search/category.json?"
	            		+ "category_group_code=" + category
	            		+ "&x=" + lon
	            		+ "&y=" + lat
	            		+ "&radius=" + radius);
	        
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// API key �Է�
			con.setRequestProperty("Authorization", "KakaoAK b6ec5ff96d29443fdeb760fcbcf289ef");
			/* con.setRequestProperty("Accept-Charset", "UTF-8"); */

			int responseCode = con.getResponseCode();
			System.out.println("응답코드 : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println("응답내용 : ");
			System.out.println(response.toString());

			
			//���, �ּ� , ��ȭ��ȣ, �浵, ���� 
			
			JSONObject json = new JSONObject(response.toString());
			JSONObject jsonMeta = json.getJSONObject("meta");
			
			//JSONArray�� 15���� �ִ��ε�!!!!!!!!!!!!!!!Ȯ���ϱ�!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
			JSONArray jsonDoucumentArray = (JSONArray) json.get("documents");
			int searchCount = jsonMeta.getInt("total_count");
			System.out.println(searchCount+"��");
			
			//searchCount�� �ϳ� �̻��϶�
			if (searchCount != 0) {
				for (int i = 0; i < searchCount; i++) {

					JSONObject o = (JSONObject) jsonDoucumentArray.get(i);
					String place_name = o.getString("place_name");
					String address_name = o.getString("address_name");
					String phone = o.getString("phone");
					String x = o.getString("x");
					System.out.println("place_name : "+ place_name);
					String y = o.getString("y");
					//URLDecoder.decode(place_name, "")
					String jebal1 = new String(place_name.getBytes("MS949"), "utf-8");
					System.out.println("jebal1 :" + jebal1);
					String jebal2 = new String(place_name.getBytes("utf-8"), "MS949");
					System.out.println("jebal2 :" + jebal2);
					/*String jebal3 = new String(place_name.getBytes("MS949"), "euc-kr");
					System.out.println("jebal3 :" + jebal3);
					String jebal4 = new String(place_name.getBytes("MS949"), "euc-kr");
					System.out.println("jebal4 :" + jebal4);*/
					CharBuffer cbuffer = CharBuffer.wrap(new String(jebal1.getBytes("MS949"), "utf-8").toCharArray());
					Charset utf8charset = Charset.forName("UTF-8");
					ByteBuffer bbuffer = utf8charset.encode(cbuffer);
					String tmpDe= new String(bbuffer.array());
					System.out.println("tmpDe : " + tmpDe);
					
					CharBuffer cbuffer2 = CharBuffer.wrap(new String(jebal1.getBytes("utf-8"), "utf-8").toCharArray());
					Charset utf8charset2 = Charset.forName("UTF-8");
					ByteBuffer bbuffer2 = utf8charset2.encode(cbuffer2);
					String tmpDe2= new String(bbuffer2.array());
					System.out.println("tmpDe2 : " + tmpDe2);
					
					CategoryRadiusPlace_alist.add(new CategoryRadiusPlace(place_name,address_name,phone,x,y));
				}
			} else {
				System.out.println("ã�� ���߽��ϴ�...");
			}
			for(CategoryRadiusPlace s: CategoryRadiusPlace_alist) {
				System.out.println(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return CategoryRadiusPlace_alist;

	}
	public int valueZoom(int inOut) {
		// zoom ����
		if (inOut == 1) {
			return zoom++;
		}
		// zoom ���
		else if (inOut == 0) {
			return zoom--;
		} else {
			//inOut���� 9�϶��� zoom ��ư�� Ŭ���� ���� �ƴ϶� list�� Ŭ�� �� 
		}
		return zoom;

	}
	//zoom �Ҷ� dowmloadMap
		public void zoomDownloadMap(String location, int zoom) {
			// TODO Auto-generated method stub
			try {

				System.out.println(location + ",zoom��" + zoom);

				// location�� "latitude,longitude" ������� ����.
				String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + location + "&zoom=" + zoom
						+ "&size=300x300" + "&scale=2" + "&markers=color:blue%7Clabel:S%7C" + location
						+ "&key=AIzaSyBmI8tw1VHRQKDavypHLpgwcXKrG-Xx08Y ";

				// ���۷� ���� map image�� �д´�
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
	//list Ŭ�� �Ҷ� dowmloadMap
		public void listClickdownloadMap(String location, int zoom) {
			// TODO Auto-generated method stub
			try {
				
				//zoom���� 11�� �ʱ�ȭ �����ش�.
				zoom = 11;
				
				System.out.println(location + ",zoom��" + zoom);
				// location�� "latitude,longitude" ������� ����.
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

		public ImageIcon getMap(String location, int zoom) {
			// TODO Auto-generated method stub
			return new ImageIcon((new ImageIcon(location + "" + zoom)).getImage());
		}

		public void fileDelete(String fileName) {
			// TODO Auto-generated method stub
			File f = new File(fileName);
			f.delete();
		}
	
	
	
	/*�����ڵ� -> �ѱ�
	 * public static String unicodeConvert(String str) {
        StringBuilder sb = new StringBuilder();
        char ch;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            ch = str.charAt(i);
            if (ch == '\\' && str.charAt(i+1) == 'u') {
                sb.append((char) Integer.parseInt(str.substring(i+2, i+6), 16));
                i+=5;
                continue;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

	protected String uniToKsc(String uni) throws UnsupportedEncodingException{
	     return new String (uni.getBytes("8859_1"),"KSC5601");
	 }*/
	

	
}
