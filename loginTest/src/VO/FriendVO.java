package VO;

public class FriendVO {
	private String memID1;					// ���� ģ�� ������ �λ���� ID
	private String memID2;
	public FriendVO(String memID1, String memID2) {
		super();
		this.memID1 = memID1;
		this.memID2 = memID2;
	}
	public FriendVO() {
		super();
	}
	public String getmemID1() {
		return memID1;
	}
	public void setmemID1(String memID1) {
		memID1 = memID1;
	}
	public String getmemID2() {
		return memID2;
	}
	public void setmemID2(String memID2) {
		memID2 = memID2;
	}
	@Override
	public String toString() {
		return "FriendVO [memID1=" + memID1 + ", memID2=" + memID2 + "]";
	}
	
	
	
}
