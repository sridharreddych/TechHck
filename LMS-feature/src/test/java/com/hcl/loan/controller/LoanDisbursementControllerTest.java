package com.hcl.loan.controller;
import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hcl.loan.service.LoanDisbursmentService;


import com.hcl.loan.controller.LoanDisbursementController;
import com.hcl.loan.dao.LoanDisbursmentDao;
import com.hcl.loan.model.Loan;


import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class LoanDisbursementControllerTest {

    

    

   @InjectMocks
   LoanDisbursementController loanDisbursementController;
    
   @Mock
   private LoanDisbursmentDao loanDisburseDaoMock;
    
    
    @Mock
    private LoanDisbursmentService loanDisbursmentServiceMock;
    
   
    List<Loan> loanList;
    Loan loan;
    
   
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loanList=setUpLoanList();
    
    }
    public List<Loan> setUpLoanList() {

    		
    		loan = new Loan();
    		loan.setLoanId(1L);
    		loan.setAppliedLoanAmount(5800000.00);
    		loan.setApprovedLoanAmount(0.0);
    		loan.setEligible(true);
    		loan.setLoanStatus("Approved");
    		loan.setRoleName(null);
    		loan.setLoanTypeId(1);
    		loan.setUserId(1);
    		loan.setStartDate(null);
    		loan.setEndDate(null);
    		loan.setMonthlyEMI(null);
    		loan.setMonthlyInterest(null);
    		loan.setTenure(20);
    		loan.setRemarks("Remarkd");
    		loan.setPendingWith("Vivek");
    		loanList = new ArrayList<Loan>();
    		loanList.add(loan);
    		return loanList; 
    }
    @Test
    public void testLoanDisbControllerReturn()
    {
    	//LoanDisbursmentService Tst1=new
    	
    	List<Loan> appLoan = new ArrayList<Loan>();
    	when(loanDisbursmentServiceMock.findAllApprovedLoans()).thenReturn(appLoan);
    	
    }
     
    @Test
    public void testMockMethodCall()
    {
    	loanDisbursmentServiceMock.findAllApprovedLoans();
    	
    	verify(loanDisbursmentServiceMock,times(1)).findAllApprovedLoans();
    	verifyNoMoreInteractions(loanDisbursmentServiceMock);
    }
   
    

}