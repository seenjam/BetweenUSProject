package VO;

public class PlaceVO {

	private String placeID;
	private String placeName; //���� �ּ�
	private String placeLon; //x�浵 : longitude
	private String placeLat; //y���� : latitude
	public PlaceVO() {
		super();
	}
	public PlaceVO(String placeID, String placeName, String placeLon, String placeLat) {
		super();
		this.placeID = placeID;
		this.placeName = placeName;
		this.placeLon = placeLon;
		this.placeLat = placeLat;
	}

	public String getPlaceID() {
		return placeID;
	}
	public void setPlaceID(String placeID) {
		this.placeID = placeID;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getPlaceLat() {
		return placeLat;
	}
	public void setPlaceLat(String placeLat) {
		this.placeLat = placeLat;
	}
	public String getPlaceLon() {
		return placeLon;
	}
	public void setPlaceLon(String placeLon) {
		this.placeLon = placeLon;
	}
	@Override
	public String toString() {
		return "장소=" + placeName + ", 경도=" + placeLon + ", 위도=" + placeLat;
	}
	
	
}
