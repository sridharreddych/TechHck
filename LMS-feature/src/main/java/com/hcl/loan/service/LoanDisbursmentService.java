package com.hcl.loan.service;

import java.util.List;

import com.hcl.loan.model.Loan;
import com.hcl.loan.model.LoanDisbursment;


 
public interface LoanDisbursmentService {
     
  
     
    public void updateLoanDisbursmentDetails(LoanDisbursment loanDisb);
     
    public List<Loan> findAllApprovedLoans(); 
     

}
