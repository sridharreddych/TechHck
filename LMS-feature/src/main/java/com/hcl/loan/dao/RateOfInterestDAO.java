package com.hcl.loan.dao;

import org.springframework.stereotype.Repository;

/*
 * 
 */
@Repository
public interface RateOfInterestDAO {
	/**
	 * 
	 * @param loanType
	 * @return
	 */
	public double fetchRateOfInterest(String loanType) ;

}
