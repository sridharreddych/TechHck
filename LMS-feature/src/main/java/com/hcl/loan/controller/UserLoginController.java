package com.hcl.loan.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hcl.loan.model.User;
import com.hcl.loan.model.exception.ErrorDetail;
import com.hcl.loan.model.exception.UserNotFoundException;
import com.hcl.loan.model.validator.UserLoginValidator;
import com.hcl.loan.service.UserLoginService;

@Controller
@RequestMapping(value = "/user")
public class UserLoginController extends WebMvcConfigurerAdapter {

	private static final Logger logger = Logger.getLogger(UserLoginController.class.getName());

	@Autowired
	UserLoginService userLoginService;

	@Autowired
	UserLoginValidator userValidator;

	@Autowired
	MessageSource messageSource;

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}

	/**
	 * Validate logged in user
	 * 
	 * @param user
	 * @return User and HttpStatus
	 */
	@CrossOrigin
	@RequestMapping(value = "/loginAuthentication", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> validateUser(@Valid @RequestBody User user, Locale locale) {
		logger.info("user: " + user);
		User userValid = userLoginService.validateLoginUser(user);
		logger.info("user validated successfully: " + userValid);
		return new ResponseEntity<User>(userValid, HttpStatus.OK);
	}

	/**
	 * Custom exception handler for user login module
	 * 
	 * @param ue
	 * @return ErrorDetail and HttpStatus
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleDataAccessException(UserNotFoundException ue) {
		String errorMessage = messageSource.getMessage("error.bad.credentials", new Object[] {},
				LocaleContextHolder.getLocale());
		ErrorDetail errorDetail = new ErrorDetail(HttpStatus.CONFLICT.name(), errorMessage);
		return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
	}

}
