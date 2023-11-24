package com.main.lacj.model.dto;

public class MemberDto {
    private int mno;  // primary key
    private String mid;
    private String mpw;
    private String mname;
	
    public MemberDto() {
		super();
	}
	public MemberDto(int mno, String mid, String mpw, String mname) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mname = mname;
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

}