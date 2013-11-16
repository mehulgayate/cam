package com.cam.entity.support;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cam.entity.Token;

public class Repository {

private SessionFactory sessionFactory;



public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

public Token generateToken(){
	
	Token token=new Token();
	token.setExpireTime(new Date());
	token.setGenerationTime(new Date());
	token.setReissuingTime(new Date());
	token.setToken("aaaaDDDeeerrcc");
	
	Session session=sessionFactory.openSession();
	session.save(token);
	return token;
}

}
