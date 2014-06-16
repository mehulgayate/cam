package com.cam.entity.support;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cam.entity.Company;
import com.cam.entity.Token;
import com.cam.entity.User;
import com.cam.entity.UserMedicalProfile;

public class Repository {

	private SessionFactory sessionFactory;



	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public User findUserById(Long id){

		Session session=getSession();
		User user=(User) session.get(User.class, id);
		//session.close();
		return user;

	}
	
	public User findUserByToken(Token token){
		
		Session session=getSession();
		Query query=session.createQuery("from "+User.class.getName()+" e where e.token=:token");
		query.setParameter("token",token);
		User user=(User) query.uniqueResult();
		//session.close();
		return user;
	}

	public User findUserByUserPass(String email,String password){

		Session session=getSession();
		Query query=session.createQuery("from "+User.class.getName()+" where email=:email AND password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		User user=(User) query.uniqueResult();
		//session.close();
		return  user;
	}
	
	public Token findTokenByTokenString(String tokenString){
		
		Session session=getSession();
		Query query=session.createQuery("from "+Token.class.getName()+" where token=:token");
		query.setParameter("token", tokenString);
		Token token=(Token) query.uniqueResult();
		//session.close();
		return  token;
		
	}
	
	public UserMedicalProfile findMedicalProfileById(Long id){

		Session session=getSession();
		UserMedicalProfile userMedicalProfile=(UserMedicalProfile) session.get(UserMedicalProfile.class, id);
		//session.close();
		return userMedicalProfile;

	}
	

	public Session getSession(){
		Session session=sessionFactory.getCurrentSession();
		return session;
	}
	
	public User findUserByEmail(String email){
		return (User) getSession().createQuery("From "+User.class.getName()+" where email=:email")
				.setParameter("email",email).uniqueResult();
	}
	
	public Company findCompanyByEmail(String email){
		return (Company) getSession().createQuery("From "+Company.class.getName()+" where email=:email")
				.setParameter("email",email).uniqueResult();
	}
	
	public User findCompanyById(Long id){
		return (User) getSession().createQuery("From "+Company.class.getName()+" where id=:id")
				.setParameter("id",id).uniqueResult();
	}
	
	public List<Company> listCompanies(){
		return  getSession().createQuery("From "+Company.class.getName()).
				list();
	}

}
