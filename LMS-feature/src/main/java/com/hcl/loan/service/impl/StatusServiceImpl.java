package com.hcl.loan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.loan.dao.StatusDAO;
import com.hcl.loan.model.Loan;
import com.hcl.loan.service.StatusService;

public class StatusServiceImpl implements StatusService {

	@Autowired
	StatusDAO statusdao;

	@Override
	public List<Loan> fetchStatus(Integer userID,String statusType) {

		return statusdao.fetchApplicationStatus(userID,statusType);
	}

}
