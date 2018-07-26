package com.hcl.loan.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loan.constants.LoanApprovalConstants;
import com.hcl.loan.model.EMI;
import com.hcl.loan.model.exception.InvalidEMIInputException;
import com.hcl.loan.model.validator.EMIValidator;
import com.hcl.loan.service.CalculateEMIService;
import com.hcl.loan.service.RateOfInterestService;

/**
 * 
 * @author shashank.sr This controller contains the all the emi related handler
 *         methods
 *
 */
@RestController
@CrossOrigin
@RequestMapping(value = LoanApprovalConstants.CALCULATE_MAPPING)
public class EMICalculatorController {

	@Autowired
	RateOfInterestService rateOfInterestService;
	@Autowired
	CalculateEMIService calculateEMIService;
	@Autowired
	EMIValidator emiValidator;

	private static final Logger logger = Logger.getLogger(EMICalculatorController.class);

	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		binder.addValidators(emiValidator);

	}

	/**
	 * 
	 * @param emi
	 * @return the EMI object that holds the monthly emi.
	 */
	@RequestMapping(value = LoanApprovalConstants.EMI_MAPPING, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public EMI calculateEMI(@Valid @RequestBody EMI emi, BindingResult result) {
		logger.debug("start calculateEMI method :" + emi);
		if (result.hasErrors()) {
			throw new InvalidEMIInputException("Invalid Input EMI: " + result.getAllErrors());
		}
		String loanType = emi.getLoanType();
		double rateOfInterest = rateOfInterestService.fetchRateOfInterest(loanType);
		emi.setRateOfInterest(rateOfInterest);
		EMI calculateEMI = calculateEMIService.calculateEMI(emi);
		logger.debug("end calculateEMI method :" + calculateEMI);
		return calculateEMI;

	}

}
