package com.hcl.loan.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.loan.constants.LoanApprovalConstants;
import com.hcl.loan.dao.LoanApprovalDAO;
import com.hcl.loan.model.Loan;
import com.hcl.loan.service.LoanApprovalService;

@Service
public class LoanApprovalServiceImpl implements LoanApprovalService {

	private Logger logger = Logger.getLogger(LoanApprovalServiceImpl.class.getName());
	@Autowired
	LoanApprovalDAO loanApprovalDAO;

	/**
	 * Method will handle the auto approval of the applied loan
	 * 
	 * @param loan
	 * @return
	 */
	@Transactional
	public void updateAutoApproval(Loan loan) {
		logger.info("Inside LoanApprovalServiceImpl: updateAutoApproval()");
		if (loan != null) {
			if (loan.isEligible()) {
				if (loan.getAppliedLoanAmount() <= 1000000) {
					loan.setApprovedLoanAmount(loan.getAppliedLoanAmount());
					loan.setLoanStatus(LoanApprovalConstants.LOAN_AUTO_APPROVED);
					loan.setRemarks(LoanApprovalConstants.LOAN_AUTO_APPROVED);
					loan.setPendingWith(LoanApprovalConstants.PENDING_WITH_NONE);
					loanApprovalDAO.updateLoan(loan);
					loanApprovalDAO.updateAuditLog(loan);
				} else {
					if ((loanApprovalDAO.getHNI(loan.getUserId()) == 1) && loan.getAppliedLoanAmount() <= 5000000) {
						loan.setApprovedLoanAmount(loan.getAppliedLoanAmount() * 0.9);
						loan.setLoanStatus(LoanApprovalConstants.LOAN_AUTO_APPROVED);
						loan.setRemarks(LoanApprovalConstants.LOAN_AUTO_APPROVED);
						loan.setPendingWith(LoanApprovalConstants.PENDING_WITH_NONE);
						loanApprovalDAO.updateLoan(loan);
						loanApprovalDAO.updateAuditLog(loan);
					} else {
						loan.setApprovedLoanAmount(0.0);
						loan.setLoanStatus(LoanApprovalConstants.PENDING_WITH_MANAGER);
						loan.setRemarks(LoanApprovalConstants.PENDING_WITH_MANAGER);
						loan.setPendingWith(LoanApprovalConstants.MANAGER);
						loanApprovalDAO.updateLoan(loan);
						loanApprovalDAO.updateAuditLog(loan);
					}
				}
			} else {
				loan.setApprovedLoanAmount(0.0);
				loan.setLoanStatus(LoanApprovalConstants.LOAN_AUTO_REJECTED);
				loan.setRemarks(LoanApprovalConstants.LOAN_AUTO_REJECTED);
				loan.setPendingWith(LoanApprovalConstants.PENDING_WITH_NONE);
				loanApprovalDAO.updateLoan(loan);
				loanApprovalDAO.updateAuditLog(loan);
			}
		}
		logger.info("LoanApprovalServiceImpl - updateAutoApproval Successfully fetch Loan details");
	}

	/**
	 * Method will handle the manual approval of the applied loan
	 * 
	 * @param loan
	 * @return
	 */
	@Transactional
	public void updateManualApproval(Loan loan) {
		logger.info("Inside LoanApprovalServiceImpl: updateManualApproval()");
		if (loan.getStatus().equalsIgnoreCase("1")) {
			loan.setApprovedLoanAmount(loan.getAppliedLoanAmount());
			loan.setLoanStatus(LoanApprovalConstants.LOAN_MANUALLY_APPROVED);
			loan.setRemarks(LoanApprovalConstants.LOAN_MANUALLY_APPROVED);
			loan.setPendingWith(LoanApprovalConstants.PENDING_WITH_NONE);
			loanApprovalDAO.updateLoan(loan);
			loanApprovalDAO.updateAuditLog(loan);
		} else {
			loan.setApprovedLoanAmount(0.0);
			loan.setLoanStatus(LoanApprovalConstants.LOAN_MANUALLY_REJECTED);
			loan.setRemarks(LoanApprovalConstants.LOAN_MANUALLY_REJECTED);
			loan.setPendingWith(LoanApprovalConstants.PENDING_WITH_NONE);
			loanApprovalDAO.updateLoan(loan);
			loanApprovalDAO.updateAuditLog(loan);
		}
		logger.info("LoanApprovalServiceImpl - updateAutoApproval Successfully fetch Loan details");
	}

	/**
	 * Method return all the pending loans with approver
	 * 
	 * @return List<Loan>
	 */
	@Transactional
	public List<Loan> getAllPendingLoans() {
		logger.info("Inside LoanApprovalServiceImpl: getAllPendingLoans()");
		List<Loan> loanList = loanApprovalDAO.getPendingLoans();
		return loanList;

	}


}
