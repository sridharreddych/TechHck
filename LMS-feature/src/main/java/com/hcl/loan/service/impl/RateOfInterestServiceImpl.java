package com.hcl.loan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.loan.dao.RateOfInterestDAO;
import com.hcl.loan.service.RateOfInterestService;

/**
 * 
 * @author shashank.sr
 * 
 */
@Service
public class RateOfInterestServiceImpl implements RateOfInterestService {
	@Autowired
	RateOfInterestDAO rateOfInterestDAO;

	/**
	 * 
	 * @param loanType
	 * @return the rete of interest
	 */
	@Override
	public double fetchRateOfInterest(String loanType) {

		return rateOfInterestDAO.fetchRateOfInterest(loanType);
	}

}
