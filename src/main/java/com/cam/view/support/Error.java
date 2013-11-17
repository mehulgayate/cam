package com.cam.view.support;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import net.sf.json.JSONObject;


public class Error {
	
	@Id
	@GeneratedValue
	private int errorNumber;
	private int errorCode;
	private String errorMassage;
	
	
	public int getErrorNumber() {
		return errorNumber;
	}
	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMassage() {
		return errorMassage;
	}
	
	public JSONObject toJSON(){
	JSONObject json=new JSONObject();
	json.put("code", this.errorCode);
	json.put("msg", this.errorMassage);
	return json;
	}
	
	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	}
	
	

}
