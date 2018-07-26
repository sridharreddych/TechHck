package com.hcl.loan.service.exception;

public class ApprovedLoanNotFoundException  extends RuntimeException { 

	private static final long serialVersionUID = 1L;
	
	public ApprovedLoanNotFoundException(String msg)
	{
		super(msg);
	}
}



