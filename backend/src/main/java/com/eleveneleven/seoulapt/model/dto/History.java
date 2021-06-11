package com.eleveneleven.seoulapt.model.dto;

public class History {
	private String historyid;
	private String userid;
	private String sido;
	private String gugun;
	public History(String historyid, String userid, String sido, String gugun) {
		super();
		this.historyid = historyid;
		this.userid = userid;
		this.sido = sido;
		this.gugun = gugun;
	}
	@Override
	public String toString() {
		return "History [historyid=" + historyid + ", userid=" + userid + ", sido=" + sido + ", gugun=" + gugun + "]";
	}
	public String getHistoryid() {
		return historyid;
	}
	public void setHistoryid(String historyid) {
		this.historyid = historyid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	
	
}
