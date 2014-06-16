package com.cam.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.evalua.entity.support.EntityBase;

import net.sf.json.JSONObject;

@Entity
public class Token extends EntityBase {

	
	
	private String token;
	private Date generationTime;
	private Date expireTime;
	private Date reissuingTime;	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getGenerationTime() {
		return generationTime;
	}
	public void setGenerationTime(Date generationTime) {
		this.generationTime = generationTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getReissuingTime() {
		return reissuingTime;
	}
	public void setReissuingTime(Date reissuingTime) {
		this.reissuingTime = reissuingTime;
	}
	
	@Transient
	public JSONObject toJSON(){
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("token", this.token);
		jsonObject.put("expires", this.expireTime.toString());
		return jsonObject;
	}
	
	
}
