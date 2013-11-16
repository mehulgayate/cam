package com.cam.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Token {

	@Id
	@GeneratedValue
	private Long id;
	private String token;
	private Date generationTime;
	private Date expireTime;
	private Date reissuingTime;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	
	
	
}
