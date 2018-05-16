package VO;

public class FavoriteVO {
	private String memID;
	private String twoPlaceSeq;
	public FavoriteVO() {
		super();
	}
	public FavoriteVO(String memID, String twoPlaceSeq) {
		super();
		this.memID = memID;
		this.twoPlaceSeq = twoPlaceSeq;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getTwoPlaceSeq() {
		return twoPlaceSeq;
	}
	public void setTwoPlaceSeq(String twoPlaceSeq) {
		this.twoPlaceSeq = twoPlaceSeq;
	}
	@Override
	public String toString() {
		return "FavoriteVO [memID=" + memID + ", twoPlaceSeq=" + twoPlaceSeq + "]";
	}

	
}