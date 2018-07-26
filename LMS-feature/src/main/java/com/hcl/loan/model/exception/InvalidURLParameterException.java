package com.hcl.loan.model.exception;

public class InvalidURLParameterException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2236121045559449129L;

	public InvalidURLParameterException(String errorMsg) {
		super(errorMsg);
	}

}
