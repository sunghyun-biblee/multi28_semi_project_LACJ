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
}