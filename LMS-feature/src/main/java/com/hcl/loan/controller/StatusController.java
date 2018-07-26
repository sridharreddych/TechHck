package com.hcl.loan.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loan.constants.LoanApprovalConstants;
import com.hcl.loan.model.Loan;
import com.hcl.loan.model.exception.InvalidURLParameterException;
import com.hcl.loan.service.StatusService;

@RestController
@CrossOrigin
@RequestMapping(value = LoanApprovalConstants.STATUS_MAPPING)
public class StatusController {
	private static final Logger logger = Logger.getLogger(EMICalculatorController.class);

	@Autowired
	StatusService statusService;

	/**
	 * 
	 * @param statustype
	 * @return List of Loan object
	 */
	@RequestMapping(value = LoanApprovalConstants.STATUS_MAPPING
			+ LoanApprovalConstants.STATUS_TYPE_VARIABLE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)

	public List<Loan> fetchApplicationStatus(@PathVariable("statustype") String statustype,
			@PathVariable("userId") Integer userId) {
		logger.debug("start fetchApplicationStatus statustype:" + statustype + " userId:" + userId);

		List<Loan> loanList = null;
		if (statustype != null && !statustype.isEmpty() && userId != null) {
			if ("dispatched".equals(statustype) || "pending".equals(statustype)) {
				loanList = statusService.fetchStatus(userId, statustype);
			} else {
				throw new InvalidURLParameterException("Invalid iput parameter");
			}
		}

		return loanList;

	}

}
