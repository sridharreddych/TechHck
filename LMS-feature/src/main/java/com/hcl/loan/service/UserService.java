package com.hcl.loan.service;

import com.hcl.loan.model.User;

public interface UserService {

	public User fetchUser(long userId);	
	public User updateUser(long userId, User user);
	 
	public int persistUser(User user);
	
	public void deleteUser(long userId);
	
	
}
