package com.hcl.loan.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.loan.dao.LoanDisbursmentDao;

import com.hcl.loan.model.Loan;
import com.hcl.loan.model.LoanDisbursment;
 
import com.hcl.loan.service.LoanDisbursmentService;

@Service
public class LoanDisbursmentServiceImpl implements LoanDisbursmentService {

 	
	@Autowired
    private LoanDisbursmentDao loanDisbursmentDao;

              
                /*
                *
                */
                public void updateLoanDisbursmentDetails(LoanDisbursment loanDisb) {
                	loanDisbursmentDao.save(loanDisb);
                }

                public List<Loan> findAllApprovedLoans() {
                	
                	List<Loan> appLoan;
                	
                	appLoan =loanDisbursmentDao.findApprovedLoanDeatils();
                	               	
                	return appLoan;
                }

}

