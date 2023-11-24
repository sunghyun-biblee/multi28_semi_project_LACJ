package com.main.lacj.model.dto;

public class CommentDto {
    private int commentnum;
    private String comment;
    private int mno; // Foreign Key
	public CommentDto() {
		super();
	}
	public CommentDto(int commentnum, String comment, int mno) {
		super();
		this.commentnum = commentnum;
		this.comment = comment;
		this.mno = mno;
	}
	public int getCommentnum() {
		return commentnum;
	}
	public void setCommentnum(int commentnum) {
		this.commentnum = commentnum;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}


}