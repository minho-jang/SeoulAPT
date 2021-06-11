package com.eleveneleven.seoulapt.model.dto;

public class Comment {
	String qnacommentid;
	String userid;
	String qnaboardid;
	String content;
	String comment_timestamp;
	
	Comment(){}

	@Override
	public String toString() {
		return "Comment [qnacommentid=" + qnacommentid + ", userid=" + userid + ", qnaboardid=" + qnaboardid
				+ ", content=" + content + ", comment_timestamp=" + comment_timestamp + "]";
	}

	public Comment(String qnacommentid, String userid, String qnaboardid, String content, String comment_timestamp) {
		super();
		this.qnacommentid = qnacommentid;
		this.userid = userid;
		this.qnaboardid = qnaboardid;
		this.content = content;
		this.comment_timestamp = comment_timestamp;
	}

	public String getQnacommentid() {
		return qnacommentid;
	}

	public void setQnacommentid(String qnacommentid) {
		this.qnacommentid = qnacommentid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getQnaboardid() {
		return qnaboardid;
	}

	public void setQnaboardid(String qnaboardid) {
		this.qnaboardid = qnaboardid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComment_timestamp() {
		return comment_timestamp;
	}

	public void setComment_timestamp(String comment_timestamp) {
		this.comment_timestamp = comment_timestamp;
	}	
	
	
}
