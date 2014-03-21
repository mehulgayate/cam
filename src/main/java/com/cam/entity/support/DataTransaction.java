package com.cam.entity.support;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.cam.entity.Token;
import com.cam.entity.User;
import com.evalua.entity.support.EntityBase;

@Transactional
public class DataTransaction {
	
	private SessionFactory sessionFactory;
	
	private Repository repository;
	
	

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(EntityBase entity){		
	Session session=getSession();
	session.saveOrUpdate(entity);
	//session.close();
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	public Token generateToken(User user){		
		Token token=new Token();
		token.setExpireTime(new Date());
		token.setGenerationTime(new Date());
		token.setReissuingTime(new Date());
		token.setToken(new Date().getTime()+"_mac@util_"+user.getId());
		save(token);
		user.setToken(token);
		save(user);
		return token;
	}

}
