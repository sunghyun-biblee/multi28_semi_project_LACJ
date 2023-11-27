package com.main.lacj.model.dto;

public class CommentDto {
    private int commentnum;
    private String comment;
    private int bno; // Foreign Key
	public CommentDto() {
		super();
	}
	public CommentDto(int commentnum, String comment, int mno) {
		super();
		this.commentnum = commentnum;
		this.comment = comment;
		this.bno = mno;
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
	public int getBno() {
		return bno;
	}
	public void setBno(int mno) {
		this.bno = mno;
	}


}