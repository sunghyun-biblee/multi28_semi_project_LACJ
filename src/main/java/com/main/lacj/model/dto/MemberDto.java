package com.main.lacj.model.dto;

public class MemberDto {
    private int mno;  // primary key
    private String mid;
    private String mpw;
    private String mname;
    private String mimg;
    private String mstatus;
	
    public MemberDto() {
		super();
	}
	public MemberDto(int mno, String mid, String mpw, String mname, String mimg, String mstatus) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
		this.mimg = mimg;
		this.mstatus = mstatus;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMimg() {
		return mimg;
	}
	public void setMimg(String mimg) {
		this.mimg = mimg;
	}
	public String getMstatus() {
		return mstatus;
	}
	public void setMstatus(String mstatus) {
		this.mstatus = mstatus;
	}

}