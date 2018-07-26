package com.hcl.loan.dao;

import java.util.List;

import com.hcl.loan.model.Loan;

public interface LoanApprovalDAO {
	
	/**
	 * Method which updates the Loan table
	 * @param loan
	 * @return
	 */
	public void updateLoan(Loan loan);
	
	/**
	 * Method return all the pending loans with approver
	 * @return List<Loan>
	 */
	public List<Loan> getPendingLoans(); 
	
	/**
	 * Method which updates Loan Audit table
	 * @param loan
	 */
	public void updateAuditLog(Loan loan);
	
	/**
	 * Method which get hniflag for the user
	 * @param userId
	 * @return hni
	 */
	public Integer getHNI(Integer userId);
}
