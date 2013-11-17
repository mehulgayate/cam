package com.cam.entity.support;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cam.entity.Token;
import com.cam.entity.User;

public class DataTransaction {
	
	private SessionFactory sessionFactory;
	
	private Repository repository;
	
	

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session save(EntityBase entity){		
	Session session=getSession();
	session.saveOrUpdate(entity);
	return session;
	}
	
	private Session getSession(){
		return sessionFactory.openSession();
	}

	public Token generateToken(User user){		
		Token token=new Token();
		token.setExpireTime(new Date());
		token.setGenerationTime(new Date());
		token.setReissuingTime(new Date());
		token.setToken(new Date()+"_mac@util_"+user.getId());
		save(token);
		user.setToken(token);
		save(user).close();
		return token;
	}

}
