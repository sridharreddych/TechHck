package com.hcl.loan.service.exception;

public class InvalidUserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1150450347903437142L;
	
	public InvalidUserException(String message)
	{
		super(message);
	}

}
