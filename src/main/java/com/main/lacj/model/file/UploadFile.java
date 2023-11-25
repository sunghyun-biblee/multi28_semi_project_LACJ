package com.main.lacj.model.file;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	private MultipartFile file;

	public UploadFile() {
		super();
	}
	
	public UploadFile(MultipartFile file) {
		super();
		this.file = file;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
