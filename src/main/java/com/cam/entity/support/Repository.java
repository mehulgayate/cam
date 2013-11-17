package com.cam.entity.support;

import java.util.Date;

import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cam.entity.Token;
import com.cam.entity.User;

public class Repository {

	private SessionFactory sessionFactory;



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User findUserById(Long id){

		Session session=getSession();
		User user=(User) session.get(User.class, id);
		session.close();
		return user;

	}

	public User findUserByUserPass(String email,String password){

		Session session=getSession();
		Query query=session.createQuery("from "+User.class.getName()+" where email=:email AND password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		User user=(User) query.uniqueResult();
		session.close();
		return  user;
	}

	private Session getSession(){
		Session session=sessionFactory.openSession();
		return session;
	}

}
