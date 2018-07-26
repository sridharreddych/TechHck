package com.hcl.loan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.loan.model.Loan;
import com.hcl.loan.model.User;

@RestController
public class LoanController {
	
	
	@RequestMapping(value="/newloan", method= RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Loan> initiateNewLoan(@RequestBody User user){
		Loan loan = new Loan();
		
		return new ResponseEntity<Loan>(loan,HttpStatus.CREATED);
	}

}
