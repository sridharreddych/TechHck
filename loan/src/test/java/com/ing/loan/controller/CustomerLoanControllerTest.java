package com.ing.loan.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ing.loan.exception.LoanOfferException;
import com.ing.loan.exception.RecordNotFoundException;
import com.ing.loan.model.Customer;
import com.ing.loan.model.LoanOffer;
import com.ing.loan.service.CustomerLoanServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class CustomerLoanControllerTest {
	
	@InjectMocks
	private CustomerLoanController customerLoanController;
	
	@Mock
	private CustomerLoanServiceImpl customerLoanServiceImpl;
	
	private static final Integer LOAN_ID = 200;
	
	private static final Integer CUSTOMER_ID = 100;
	
	private LoanOffer loanOffer;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		loanOffer = new LoanOffer();
		loanOffer.setLoanOfferId(100);
		loanOffer.setEmi(2.0);
		loanOffer.setLoanAmount(200000.00);
		loanOffer.setEmi(1234.00);
		loanOffer.setMinimumSalaryEligibity(10000.00);
		loanOffer.setRateOfInterest(11.25);
	}
	
	@Test
	public void testSaveLoanDetailsPositive() throws Exception{
		
		Mockito.when(customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID)).thenReturn(loanOffer);
		ResponseEntity<LoanOffer> loanOfferEntity = customerLoanController.saveLoanDetails(LOAN_ID, CUSTOMER_ID);
		assertNotNull(loanOfferEntity);
	}
	
	@Test(expected=RecordNotFoundException.class)
	public void testSaveLoanDetailsException() throws Exception{		
		Mockito.when(customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID)).thenThrow(new RecordNotFoundException());
		customerLoanController.saveLoanDetails(LOAN_ID, CUSTOMER_ID);		
	}
	
	/*Jaison Test Cases START*/
	@Test
	public void testAddLoanOffer() throws Exception
	{
		
			when(customerLoanServiceImpl.addLoanOffer(loanOffer)).thenReturn(loanOffer);
			customerLoanController.addLoanOffer(loanOffer);
		
	}
	@Test
	public void testUpdateLoanOffer()
	{
		try {
			customerLoanController.updateLoanOffer(loanOffer);
		} catch (LoanOfferException e) {
			
		}
	}
	@Test
	public void testGetLoanOffer()
	{
		try {
			when(customerLoanServiceImpl.getLoanOffer(loanOffer.getLoanOfferId())).thenReturn(loanOffer);
			customerLoanController.getLoanOffer(loanOffer.getLoanOfferId());
		} catch (LoanOfferException e) {
			
		}
	}
	@Test
	public void testDeleteLoanOffer()
	{
		try {
			customerLoanController.deleteLoanOffer(loanOffer.getLoanOfferId());
		} catch (LoanOfferException e) {
			
		}
	}
	/*Jaison Test Cases END*/

	/*manjunath test cases START*/
	@Test
	public void testCreateCustomerInformationPositive(){
		Customer customer = new Customer();
		customer.setCustomerId(100);
		List<LoanOffer> loanOfferList = new ArrayList<>();
		loanOfferList.add(loanOffer);
		Mockito.when(customerLoanServiceImpl.saveCustomerInformation(customer)).thenReturn(loanOfferList);
		ResponseEntity<List<LoanOffer>> responseEntity = customerLoanController.createCustomerInformation(customer);
		assertNotNull(responseEntity);
	}
	/*manjunath test cases END*/
}
