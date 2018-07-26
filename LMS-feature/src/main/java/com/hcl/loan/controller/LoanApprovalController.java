package com.hcl.loan.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loan.model.Loan;
import com.hcl.loan.service.LoanApprovalService;

/**
 * @author Shashikanth.p Class for Loan Approval System
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoanApprovalController {
	private Logger logger = Logger.getLogger(LoanApprovalController.class.getName());

	@Autowired
	LoanApprovalService loanApprovalService;

	/**
	 * Method will handle the auto approval of the applied loan
	 * 
	 * @param loan
	 * @return
	 */
	@RequestMapping(value = "/approval", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateAutoApproval(@RequestBody Loan loan) {
		logger.info("Inside LoanApprovalController: updateAutoApproval()");
		loanApprovalService.updateAutoApproval(loan);
		logger.info("LoanApprovalController - updateAutoApproval Successfully update Loan details");
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	/**
	 * Method will handle the manual approval of the applied loan
	 * 
	 * @param loan
	 * @return
	 */
	@RequestMapping(value = "/manual", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> updateManualApproval(@RequestBody Loan loan) {
		logger.info("Inside LoanApprovalController: updateManualApproval()");
		loanApprovalService.updateManualApproval(loan);
		logger.info("LoanApprovalController - updateManualApproval Successfully update Loan details");
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	/**
	 * Method return all the pending loans with approver
	 * 
	 * @return List<Loan>
	 */
	@RequestMapping(value = "/pendingloans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Loan>> getAllPendingLoans() {
		logger.info("Inside LoanApprovalController: getAllPendingLoans()");
		ResponseEntity<List<Loan>> response = null;
		List<Loan> loanList = loanApprovalService.getAllPendingLoans();
		if (loanList == null || (loanList.isEmpty())) {
			response = new ResponseEntity<>(loanList, HttpStatus.NOT_FOUND);
		} else {
			response = new ResponseEntity<>(loanList, HttpStatus.OK);
		}
		logger.info("LoanApprovalController - getAllPendingLoans Successfully fetch Loan details");
		return response;

	}
}
