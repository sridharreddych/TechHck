package com.hcl.loan.service;

import org.springframework.stereotype.Service;
/**
 * 
 * @author shashank.sr
 *This service provide the method to fetch the rate of interest from the Db
 */
@Service
public interface RateOfInterestService {
	/**
	 * 
	 * @param loanType
	 * @return the rete of interest
	 */
public double fetchRateOfInterest(String loanType);
}
