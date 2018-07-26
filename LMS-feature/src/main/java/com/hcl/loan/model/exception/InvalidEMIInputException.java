package com.hcl.loan.model.exception;

public class InvalidEMIInputException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7239002475681794226L;

	public InvalidEMIInputException(String errorMsg) {
		super(errorMsg);
	}

}
