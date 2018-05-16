package VO;

public class CategoryRadiusPlace {

	private String place_name;
	private String address_name;
	private String phone;
	private String lon;
	private String lat;
	public CategoryRadiusPlace(String place_name, String address_name, String phone, String lon, String lat) {
		super();
		this.place_name = place_name;
		this.address_name = address_name;
		this.phone = phone;
		this.lon = lon;
		this.lat = lat;
	}
	public CategoryRadiusPlace() {
		super();
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "place_name=" + place_name + ", address_name=" + address_name + ", phone=" + phone
				+ ", lon=" + lon + ", lat=" + lat;
	}
	
	
}
