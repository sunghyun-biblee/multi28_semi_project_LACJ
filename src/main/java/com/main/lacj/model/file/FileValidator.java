package com.main.lacj.model.file;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object uploadFile, Errors errors) {
		UploadFile file = (UploadFile)uploadFile;
		
		if(file.getFile().getSize() == 0) {
			errors.rejectValue("file", "errorCode", "Please select a file");
		}
	}
	

}
