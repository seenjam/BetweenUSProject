package VO;

public class FavoriteListValueVO {

	private String placeName1; //즐겨찾기의 한 목록의 첫번째 장소
	private String placeName2; //즐겨찾기의 한 목록의 두번째 장소
	public FavoriteListValueVO(String placeName1, String placeName2) {
		super();
		this.placeName1 = placeName1;
		this.placeName2 = placeName2;
	}
	public FavoriteListValueVO() {
		super();
	}
	public String getPlaceName1() {
		return placeName1;
	}
	public void setPlaceName1(String placeName1) {
		this.placeName1 = placeName1;
	}
	public String getPlaceName2() {
		return placeName2;
	}
	public void setPlaceName2(String placeName2) {
		this.placeName2 = placeName2;
	}
	@Override
	public String toString() {
		return "FavoriteListValueVO [placeName1=" + placeName1 + ", placeName2=" + placeName2 + "]";
	}
	
}
