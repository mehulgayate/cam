package com.cam.web.support;

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
		User user=new User();
		user.setEmail(userForm.getEmail());
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
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
