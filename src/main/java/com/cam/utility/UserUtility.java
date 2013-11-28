package com.cam.utility;

import javax.jws.soap.SOAPBinding.Use;

import com.cam.entity.Token;
import com.cam.entity.User;
import com.cam.entity.support.Repository;

public class UserUtility {

	private Repository repository;	
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}



	public User verifyUser(String tokenString){
		
		Token token=repository.findTokenByTokenString(tokenString);
		if(token==null){
			return null;
		}
		User user=repository.findUserByToken(token);
		return user;
	}
}
