package com.hcl.loan.service;

import java.util.List;

import com.hcl.loan.model.Loan;
/**
 * 
 * @author shashank.sr
 *
 */
public interface StatusService {
	
	/**
	 * 
	 * @param userID
	 * @return
	 */
	public List<Loan> fetchStatus(Integer userID,String statusType);

}
