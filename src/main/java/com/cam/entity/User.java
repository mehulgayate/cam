package com.cam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.evalua.entity.support.EntityBase;


@Entity
public class User extends EntityBase {
	
	private String name;
	private String email;
	private String password;
	private Token token;	
	private String privateKey;	
	
	public String getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}	

}
