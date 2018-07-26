package com.hcl.loan.dao;

import java.util.List;


import com.hcl.loan.model.Loan;
import com.hcl.loan.model.LoanDisbursment;


 
/**
 * @author vivekanandan_p
 * Interface to update the loan Disbursement Details
 *
 */
public interface LoanDisbursmentDao  {

    List<Loan> findApprovedLoanDeatils();
    
    boolean save(LoanDisbursment loan);
}

