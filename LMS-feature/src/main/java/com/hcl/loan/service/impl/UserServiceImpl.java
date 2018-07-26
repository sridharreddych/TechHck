package com.hcl.loan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.loan.dao.UserDAO;
import com.hcl.loan.model.User;
import com.hcl.loan.service.UserService;
import com.hcl.loan.service.exception.UserAlreadyExist;

@PropertySource("classpath:error.properties")
@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	UserDAO userDao;

	@Autowired
	Environment env;

	@Override
	public User fetchUser(long userId) {

		logger.debug("fetchUser(id) - Method Input - Id:" + userId);

		User fetchedUser = userDao.fetchUser(userId);
		logger.debug("updateUser(id, user) - Method Output - Fetched User:" + fetchedUser);

		return fetchedUser;
	}

	@Override
	public User updateUser(long userId, User user) {

		logger.debug("updateUser(id, user) - Method Input - ID:" + userId + "User:" + user);
		User updateUser = userDao.updateUser(userId, user);
		logger.debug("updateUser(id, user) - Method Output - Updated User:" + updateUser);

		return updateUser;
	}

	@Transactional
	@Override
	public int persistUser(User user) {
		
		int insertedRecordCount = -1;
		logger.debug("persistUser(id) - Method Input - User:" + user);
		if (!userDao.existingUserCheck(user)) {
			insertedRecordCount = userDao.persistUser(user);
			logger.debug("persistedUser(user) - Method Output - Inserted Record Count:" + insertedRecordCount);
		}else {
			throw new UserAlreadyExist(env.getProperty("LMS.USER.USER_ALREADY_EXIST"));
		}
		return insertedRecordCount;
	}

	@Override
	public void deleteUser(long userId) {
		userDao.deleteUser(userId);
	}



}
