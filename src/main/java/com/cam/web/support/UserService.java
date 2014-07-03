package com.cam.web.support;

import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.cam.entity.User;
import com.cam.entity.support.Repository;
import com.cam.entity.support.UserForm;
import com.evalua.entity.support.DataStoreManager;

public class UserService {

	private DataStoreManager dataStoreManager;

	public void setDataStoreManager(DataStoreManager dataStoreManager) {
		this.dataStoreManager = dataStoreManager;
	}

	private Repository repository;
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	public void addUser(UserForm userForm){

		User user=repository.findUserByEmail(userForm.getEmail());
		if(user==null){
			user=new User();
		}
		user.setEmail(userForm.getEmail());
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
		
		byte[] r = new byte[16]; //Means 2048 bit
		Random random=new Random();
		random.nextBytes(r);
		String s = Base64.encodeBase64String(r);
		
		user.setPrivateKey(s);
		dataStoreManager.save(user);
	}

	public User authenticate(String email,String password){
		User user=repository.findUserByEmail(email);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}
}
