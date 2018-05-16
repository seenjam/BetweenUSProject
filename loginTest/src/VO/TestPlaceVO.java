package VO;

public class TestPlaceVO {
	private String placeID1;
	private String placeID2;
	public TestPlaceVO(String placeID1, String placeID2) {
		super();
		this.placeID1 = placeID1;
		this.placeID2 = placeID2;
	}
	public TestPlaceVO() {
		super();
	}
	public String getPlaceID1() {
		return placeID1;
	}
	public void setPlaceID1(String placeID1) {
		this.placeID1 = placeID1;
	}
	public String getPlaceID2() {
		return placeID2;
	}
	public void setPlaceID2(String placeID2) {
		this.placeID2 = placeID2;
	}
	@Override
	public String toString() {
		return "TestPlaceVO [placeID1=" + placeID1 + ", placeID2=" + placeID2 + "]";
	}
	
	
}
