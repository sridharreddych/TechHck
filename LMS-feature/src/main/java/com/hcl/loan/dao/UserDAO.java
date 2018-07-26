package com.hcl.loan.dao;

import com.hcl.loan.model.User;

public interface UserDAO {

	public User fetchUser(long userId);	
	public User updateUser(long userId, User user);
	 
	public int persistUser(User user);
	
	public void deleteUser(long userId);
	
	public boolean existingUserCheck(User user);
	
}
