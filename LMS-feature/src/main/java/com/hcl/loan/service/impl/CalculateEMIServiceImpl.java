package com.hcl.loan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hcl.loan.constants.LoanApprovalConstants;
import com.hcl.loan.controller.EMICalculatorController;
import com.hcl.loan.model.EMI;
import com.hcl.loan.service.CalculateEMIService;
/**
 * 
 * @author shashank.sr
 *
 *
 */
@Service
public class CalculateEMIServiceImpl implements CalculateEMIService {
	private static final Logger logger = Logger.getLogger(EMICalculatorController.class);
/**
 * takes the input year,rateOfInterest and amount and return the EMI
 */
	@Override
	public EMI calculateEMI(EMI emi) {
		logger.debug("statrt calculateEMI() emi:" + emi);
		boolean seniorCitizen = emi.isSeniorCitizen();
		double principal = emi.getAmount();
		double rate = emi.getRateOfInterest();
		double time = emi.getYear();
		// for senior citizen
		if (seniorCitizen) {
			rate = rate - LoanApprovalConstants.SENIOR_CITEZEN_BENIFITE;
		}
		// rate of interest will be differ in 5 years of interval
		if (time > LoanApprovalConstants.TENURE_FIVE && time <= LoanApprovalConstants.TENURE_TEN) {
			rate = rate - LoanApprovalConstants.TENURE_BENIFITE_TWENTYFIVE;
		} else if (time > LoanApprovalConstants.TENURE_TEN && time <= LoanApprovalConstants.TENURE_FIFTEEN) {
			rate = rate - LoanApprovalConstants.TENURE_BENIFITE_FIFTY;
		}
		emi.setRateOfInterest(rate);

		rate = rate / (12 * 100); /* one month interest */
		time = time * 12; /* one month period */

		Double monthlyEmi = (principal * rate * Math.pow(1 + rate, time)) / (Math.pow(1 + rate, time) - 1);

		logger.debug("end calculateEMI() monthlyEmi:" + monthlyEmi);
		emi.setMonthlyEMI(Math.round(monthlyEmi));

		return emi;
	}

}
