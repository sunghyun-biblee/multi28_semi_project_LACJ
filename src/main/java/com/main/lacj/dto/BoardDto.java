package com.main.lacj.dto;

import java.util.Date;

public class BoardDto {
    private int bno;
    private String bid;
    private String btitle;
    private String bcontent;
    private Date bdate;
    private String bimg;
    private int blikes;
    private int mno; // Foreign Key
}