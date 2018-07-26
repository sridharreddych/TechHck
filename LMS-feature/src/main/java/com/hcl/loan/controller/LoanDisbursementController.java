 package com.hcl.loan.controller;

import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loan.model.Loan;
import com.hcl.loan.model.LoanDisbursment;
import com.hcl.loan.service.LoanDisbursmentService;



@RestController
@CrossOrigin
public class LoanDisbursementController {

	
	
	
	@Autowired
    LoanDisbursmentService loanDisbursmentService;

	  /**
     * This method will update the disbursement details of a loan
     */
	@CrossOrigin
    @RequestMapping(value ="/updateLoanDisb" , method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoanDisbursment> updateLoanDisbDetails(@RequestBody LoanDisbursment loanDisb,BindingResult result) {
		
      	loanDisbursmentService.updateLoanDisbursmentDetails(loanDisb);
    	return new ResponseEntity<>(loanDisb, HttpStatus.CREATED);  
    }
    
    
     
    /**
     * This method will list all existing users.
     */
	@CrossOrigin
    @RequestMapping(value ="/retrieveLoan", method = RequestMethod.GET)
    public ResponseEntity<List<Loan>> retrieveLoan(ModelMap model) {
    	
    	List<Loan> loanDetails = loanDisbursmentService.findAllApprovedLoans();
    	return new ResponseEntity<>(loanDetails, HttpStatus.CREATED);
    }
  
    
    

}
