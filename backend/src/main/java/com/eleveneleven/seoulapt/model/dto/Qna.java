package com.eleveneleven.seoulapt.model.dto;

public class Qna {
	String qnaboardid;
	String userid;
	String qnatitle;
	String qnacontent;
	String qna_timestamp;
	@Override
	public String toString() {
		return "Qna [qnaboardid=" + qnaboardid + ", userid=" + userid + ", qnatitle=" + qnatitle + ", qnacontent="
				+ qnacontent + ", qnatimestamp=" + qna_timestamp + "]";
	}
	public Qna(String qnaboardid, String userid, String qnatitle, String qnacontent, String qnatimestamp) {
		super();
		this.qnaboardid = qnaboardid;
		this.userid = userid;
		this.qnatitle = qnatitle;
		this.qnacontent = qnacontent;
		this.qna_timestamp = qnatimestamp;
	}
	public Qna() {}
	public String getQnaboardid() {
		return qnaboardid;
	}
	public void setQnaboardid(String qnaboardid) {
		this.qnaboardid = qnaboardid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getQnatitle() {
		return qnatitle;
	}
	public void setQnatitle(String qnatitle) {
		this.qnatitle = qnatitle;
	}
	public String getQnacontent() {
		return qnacontent;
	}
	public void setQnacontent(String qnacontent) {
		this.qnacontent = qnacontent;
	}
	public String getQna_timestamp() {
		return qna_timestamp;
	}
	public void setQna_timestamp(String qnatimestamp) {
		this.qna_timestamp = qnatimestamp;
	}
	
}
