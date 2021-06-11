package com.eleveneleven.seoulapt.model.dto;

public class Member {
	private String userid; //유니크키
	private String userpwd;
	private String name;
	private String email;
	private String address;

	public Member() {
	}

	public Member(String userid, String userpwd, String name, String email, String address) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Member [userid=" + userid + ", userpwd=" + userpwd + ", name=" + name + ", email=" + email
				+ ", address=" + address + "]";
	}

	

}
