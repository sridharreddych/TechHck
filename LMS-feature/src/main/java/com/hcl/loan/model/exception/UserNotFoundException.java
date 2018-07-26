package com.hcl.loan.model.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
