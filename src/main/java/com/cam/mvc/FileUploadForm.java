package com.cam.mvc;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

	public static final String key="fileUploadForm";
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	
}
