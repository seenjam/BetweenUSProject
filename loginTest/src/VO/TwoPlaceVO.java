package VO;

public class TwoPlaceVO {
	
	private String twoPlaceSeq;
	private String placeID1;
	private String placeID2;
	public TwoPlaceVO() {
		super();
	}
	public TwoPlaceVO(String twoPlaceSeq, String placeID1, String placeID2) {
		super();
		this.twoPlaceSeq = twoPlaceSeq;
		this.placeID1 = placeID1;
		this.placeID2 = placeID2;
	}
	public String getTwoPlaceSeq() {
		return twoPlaceSeq;
	}
	public void setTwoPlaceSeq(String twoPlaceSeq) {
		this.twoPlaceSeq = twoPlaceSeq;
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
		return "TwoPlaceVO [twoPlaceSeq=" + twoPlaceSeq + ", placeID1=" + placeID1 + ", placeID2=" + placeID2 + "]";
	}
	
	
}
