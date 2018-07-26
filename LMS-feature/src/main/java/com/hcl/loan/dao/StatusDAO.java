package com.hcl.loan.dao;

import java.util.List;
import java.util.Map;

import com.hcl.loan.model.Loan;



public interface StatusDAO {
	
	public List<Loan> fetchApplicationStatus(Integer UserID,String statusType);

}
