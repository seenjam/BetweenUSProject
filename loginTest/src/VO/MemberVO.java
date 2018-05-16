package VO;

public class MemberVO {
	private String memID;
	private String memPW;
	private String memName;
	private String memAddress;
	private String memState;
	public MemberVO(String memID, String memPW, String memName, String memAddress, String memState) {
		super();
		this.memID = memID;
		this.memPW = memPW;
		this.memName = memName;
		this.memAddress = memAddress;
		this.memState = memState;
	}
	public MemberVO() {
		super();
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getMemPW() {
		return memPW;
	}
	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public String getMemState() {
		return memState;
	}
	public void setMemState(String memState) {
		this.memState = memState;
	}
	@Override
	public String toString() {
		return "MemberVO [memID=" + memID + ", memPW=" + memPW + ", memName=" + memName + ", memAddress="
				+ memAddress + ", memState=" + memState + "]";
	}

}
