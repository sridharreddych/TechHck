package com.hcl.loan.model;

/**
 * This class contains the status of the running loan and closed loan.
 */
public class DispachedLoanStatusDetail {
	
	private long userId;
	private long loanId;
	private String loanType;	
	private String message;
	private double paidLoanAmt;
	private double approvedLoanAmt;
	private double outstandingLoanAmt;
	private int totalDuration;
	private int durationLeft;
	
	
	
	public double getPaidLoanAmt() {
		return paidLoanAmt;
	}
	public void setPaidLoanAmt(double paidLoanAmt) {
		this.paidLoanAmt = paidLoanAmt;
	}
	public double getApprovedLoanAmt() {
		return approvedLoanAmt;
	}
	public void setApprovedLoanAmt(double approvedLoanAmt) {
		this.approvedLoanAmt = approvedLoanAmt;
	}
	public double getOutstandingLoanAmt() {
		return outstandingLoanAmt;
	}
	public void setOutstandingLoanAmt(double outstandingLoanAmt) {
		this.outstandingLoanAmt = outstandingLoanAmt;
	}
	public int getTotalDuration() {
		return totalDuration;
	}
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}
	public int getDurationLeft() {
		return durationLeft;
	}
	public void setDurationLeft(int durationLeft) {
		this.durationLeft = durationLeft;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
	

}
