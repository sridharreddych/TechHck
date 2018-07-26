package com.hcl.loan.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * @author Sridhar
 * LoanDisbursment Pojo
 *
 */

public class LoanDisbursment {

	
	@NotNull
	private Double disbursmentId;
	
	@NotNull
    private Long loanId;
	
	@NotNull
    private Long userId;
	
    @NotNull
    private Date createdDate;
    
    private Long modifiedBy;
    private Date modifiedDate;
    private Date disbursmentDate;
    private Integer  numberOfChecks;
    private String disbursmentMode;
   	private String emailNotification;
    private String accountNumber;
    
    public Double getDisbursmentId() {
		return disbursmentId;
	}
	public void setDisbursmentId(Double disbursmentId) {
		this.disbursmentId = disbursmentId;
	}
	
	public String getEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Date getDisbursmentDate() {
		return disbursmentDate;
	}
	public void setDisbursmentDate(Date disbursmentDate) {
		this.disbursmentDate = disbursmentDate;
	}
	public Integer getNumberOfChecks() {
		return numberOfChecks;
	}
	public void setNumberOfChecks(Integer numberOfChecks) {
		this.numberOfChecks = numberOfChecks;
	}
	public String getDisbursmentMode() {
		return disbursmentMode;
	}
	public void setDisbursmentMode(String disbursmentMode) {
		this.disbursmentMode = disbursmentMode;
	}
	
    public String toString()
    {
    	
    	return "";//  for now leave it
    }
   
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
