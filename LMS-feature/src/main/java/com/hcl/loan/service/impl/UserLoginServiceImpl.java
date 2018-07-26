package com.hcl.loan.service.impl;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hcl.loan.dao.UserLoginDAO;
import com.hcl.loan.model.User;
import com.hcl.loan.model.exception.UserNotFoundException;
import com.hcl.loan.service.UserLoginService;

/**
 * Service class to validate user credentials
 * @author Kamal.Joshi
 */

@Service
public class UserLoginServiceImpl implements UserLoginService {

	private static final Logger logger = Logger.getLogger(UserLoginServiceImpl.class.getName());

	@Autowired
	MessageSource messageSource;

	@Autowired
	private UserLoginDAO userDAO;

	public User validateLoginUser(User pUser) {
		User user = null;
		try {
			user = userDAO.validateLoginUser(pUser);
			if (pUser.getEmailId().equals(user.getEmailId()) && pUser.getPassword().equals(user.getPassword())) {
				user.setValidUser(true);
			} else {
				user.setValidUser(false);
			}
		} catch (DataAccessException ex) {
			Locale locale = new Locale("en", "IN");
			String errorMsg = messageSource.getMessage("error.bad.credentials", new Object[] {}, locale);
			logger.error(errorMsg, ex);
			throw new UserNotFoundException(errorMsg, ex);
		}
		return user;
	}
}
