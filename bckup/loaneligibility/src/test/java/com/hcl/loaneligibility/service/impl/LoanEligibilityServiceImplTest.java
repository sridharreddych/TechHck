package com.hcl.loaneligibility.service.impl;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcl.loaneligibility.dao.LoanEligibilityDao;

@RunWith(MockitoJUnitRunner.class)
public class LoanEligibilityServiceImplTest {
    
	@Mock
	LoanEligibilityDao loanEligibilityDao;
	
	private static Logger logger =LoggerFactory.getLogger(LoanEligibilityServiceImplTest.class);
}
