package com.main.lacj.model.dto;

import java.util.Date;

public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private Date bdate;
    private String bimg;
    private int blikes;
    private int mno; // Foreign Key
	public BoardDto() {
		super();
	}
	public BoardDto(int bno, String btitle, String bcontent, Date bdate, String bimg, int blikes, int mno) {
		super();
		this.bno = bno;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bimg = bimg;
		this.blikes = blikes;
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public String getBimg() {
		return bimg;
	}
	public void setBimg(String bimg) {
		this.bimg = bimg;
	}
	public int getBlikes() {
		return blikes;
	}
	public void setBlikes(int blikes) {
		this.blikes = blikes;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
    
}