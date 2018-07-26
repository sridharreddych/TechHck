package com.hcl.loan.model.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> handleException(MethodArgumentNotValidException argInvalidException) {
		BindingResult bindingResult = argInvalidException.getBindingResult();
		StringBuilder errorDetails = new StringBuilder();
		(bindingResult.getAllErrors()).stream().forEach(objectError -> errorDetails.append(objectError.toString()));
		ErrorDetail errorDetail = new ErrorDetail(HttpStatus.BAD_REQUEST.name(), errorDetails.toString());
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetail> handleException(ResourceNotFoundException resourceException) {
		String errorMessage = messageSource.getMessage("error.method.resource.error", new Object[] {},
				LocaleContextHolder.getLocale());
		ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.name(), errorMessage);
		return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorDetail> handleDataAccessException(DataAccessException dae) {
		String errorMessage = messageSource.getMessage("error.method.persistence.error", new Object[] {},
				LocaleContextHolder.getLocale());
		ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.name(), errorMessage);
		return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handleException(Exception exception) {
		ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.name(),
				HttpStatus.INTERNAL_SERVER_ERROR.toString());
		return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
