package com.hcl.loan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hcl.loan.model.Loan;

@Service
public interface LoanApprovalService {

	/**
	 * Method will handle the auto approval of the applied loan
	 * @param loan
	 * @return
	 */
	public void updateAutoApproval(Loan loan);

	/**
	 * Method will handle the manual approval of the applied loan
	 * @param loan
	 * @return
	 */
	public void updateManualApproval(Loan loan);

	/**
	 * Method return all the pending loans with approver
	 * @return List<Loan>
	 */
	public List<Loan> getAllPendingLoans();

}
