package com.ing.loan.exception;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ing.loan.model.Error;
import com.ing.loan.model.Errors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Errors errors = new Errors();			
		Error error = new Error(HttpStatus.BAD_REQUEST.toString(),ex.getLocalizedMessage());
		errors.add(error);
        return new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();        
		Errors errorObject = processFieldErrors(result.getFieldErrors(), status.toString());			
		
        return new ResponseEntity(errorObject, headers, status);
	}
	
	@ExceptionHandler(value=RecordNotFoundException.class)
	protected ResponseEntity<Object> recordNotFoundExceptionHandler(RecordNotFoundException dataNotFoundException) {
		Errors errors = new Errors();			
		Error error = new Error(HttpStatus.NOT_FOUND.toString(),dataNotFoundException.getMessage());
		errors.add(error);
        return new ResponseEntity(errors, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=Exception.class)
	protected ResponseEntity<Object> exceptionHandler(Exception exception) {
		Errors errors = new Errors();			
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.toString(),exception.getMessage());
		errors.add(error);
        return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value=LoanOfferException.class)    
	protected ResponseEntity<Error> handleLoanOfferException(
			LoanOfferException ex) {
			
		Error error = new Error(HttpStatus.NOT_FOUND.toString(),ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	public Errors processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors , String errorCode){
		Errors errorObject=new Errors();
		for(org.springframework.validation.FieldError fieldError : fieldErrors){
			errorObject.add(new Error(errorCode,fieldError.getDefaultMessage()));
		}		
		return errorObject;
		
	}
	
	@ExceptionHandler(CustomerNotFoundExcetion.class)
	protected ResponseEntity<Error> customerNotFoundExcetion(CustomerNotFoundExcetion ex) {
		Error error = new Error("Not Valid customer", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerNotHavingOffersException.class)
	protected ResponseEntity<Error> customerOfferNotFoundExcetion(CustomerNotHavingOffersException ex) {
		Error error = new Error("Not having any offers", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CustomerException.class)
	protected ResponseEntity<Error> customerOfferNotFoundExcetion(CustomerException ex) {
		Error error = new Error("BAD_REQUEST", ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}

}
