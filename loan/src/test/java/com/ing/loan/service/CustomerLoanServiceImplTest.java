package com.ing.loan.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.loan.dao.CustomerLoanDetailsRepository;
import com.ing.loan.dao.CustomerRepository;
import com.ing.loan.dao.LoanOfferRespsitory;
import com.ing.loan.exception.LoanOfferException;
import com.ing.loan.exception.RecordNotFoundException;
import com.ing.loan.model.Customer;
import com.ing.loan.model.LoanOffer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerLoanServiceImplTest {
	
	@Mock
	private LoanOfferRespsitory loanOfferRespsitory;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Mock
	private CustomerLoanDetailsRepository customerLoanDetailsRepository;
	
	@InjectMocks
	private CustomerLoanServiceImpl customerLoanServiceImpl;
	
	private static final Integer LOAN_ID = 200;
	
	private static final Integer CUSTOMER_ID = 100;
	
	private LoanOffer loanOffer;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		loanOffer = new LoanOffer();
		loanOffer.setLoanOfferId(200);
		loanOffer.setEmi(9322.00);
		loanOffer.setLoanAmount(200000.00);
		loanOffer.setMinimumSalaryEligibity(15000.00);
		loanOffer.setTenure(2);
		loanOffer.setRateOfInterest(11.00);		
	}
	@Test
	public void testSaveLoanDetailsPositive()throws Exception{				
		Optional<LoanOffer> optionalLoanOffer = Optional.of(loanOffer);
		Mockito.when(loanOfferRespsitory.findById(LOAN_ID)).thenReturn(optionalLoanOffer);
		
		Customer customer = new Customer();
		customer.setCustomerId(100);
		Optional<Customer> optional = Optional.of(customer);
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(optional);
		
		LoanOffer loanOff = customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID);
		assertNotNull(loanOff);
	}
	
	@Test(expected=RecordNotFoundException.class)
	public void testSaveLoanDetailsException()throws Exception{				
		Optional<LoanOffer> optionalLoanOffer = Optional.empty();
		Mockito.when(loanOfferRespsitory.findById(LOAN_ID)).thenReturn(optionalLoanOffer);
		
		Customer customer = new Customer();
		customer.setCustomerId(100);
		Optional<Customer> optional = Optional.empty();
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(optional);
		
		LoanOffer loanOff = customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID);
		assertNotNull(loanOff);
	}
	
	@Test(expected=RecordNotFoundException.class)
	public void testSaveLoanDetailsCustomerIdNullCheckException()throws Exception{				
		Optional<LoanOffer> optionalLoanOffer = Optional.of(loanOffer);
		Mockito.when(loanOfferRespsitory.findById(LOAN_ID)).thenReturn(optionalLoanOffer);
		
		Customer customer = new Customer();
		customer.setCustomerId(100);
		Optional<Customer> optional = Optional.empty();
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(optional);
		
		LoanOffer loanOff = customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID);
		assertNotNull(loanOff);
	}
	
	@Test(expected=RecordNotFoundException.class)
	public void testSaveLoanDetailsLoanIdNullCheckException()throws Exception{
		loanOffer = new LoanOffer();
		loanOffer.setLoanOfferId(200);
		loanOffer.setEmi(9322.00);
		loanOffer.setLoanAmount(200000.00);
		loanOffer.setMinimumSalaryEligibity(15000.00);
		loanOffer.setTenure(2);
		loanOffer.setRateOfInterest(11.00);		
		Optional<LoanOffer> optionalLoanOffer = Optional.empty();
		Mockito.when(loanOfferRespsitory.findById(LOAN_ID)).thenReturn(optionalLoanOffer);
		
		Customer customer = new Customer();
		customer.setCustomerId(100);
		Optional<Customer> optional = Optional.of(customer);
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(optional);
		
		LoanOffer loanOff = customerLoanServiceImpl.saveLoanDetails(LOAN_ID, CUSTOMER_ID);
		assertNotNull(loanOff);
	}
	
	/*Jaison Code Start*/
	@Test
	public void testGetLoanOffer() {
		Optional<LoanOffer> optional = Optional.of(loanOffer);
		when(loanOfferRespsitory.findById(loanOffer.getLoanOfferId())).thenReturn(optional);
		try {
			LoanOffer loanOfferObj = customerLoanServiceImpl.getLoanOffer(loanOffer.getLoanOfferId());
			assertEquals(loanOffer, loanOfferObj);
		} catch (LoanOfferException e) {

		}
	}

	@Test
	public void testGetLoanOfferInvalid() {
		try {
			LoanOffer loanOfferAct = customerLoanServiceImpl.getLoanOffer(loanOffer.getLoanOfferId());
			assertEquals(loanOffer, loanOfferAct);
		} catch (LoanOfferException e) {
			assertThat(e.getMessage()).isEqualTo("Offer not available");

		}
	}

	@Test
	public void testAddLoanOffers() {
		when(loanOfferRespsitory.save(loanOffer)).thenReturn(loanOffer);
		try {
			LoanOffer loanOfferAct = customerLoanServiceImpl.addLoanOffer(loanOffer);
			assertEquals(loanOffer, loanOfferAct);

		} catch (LoanOfferException e) {
		}

	}

	@Test
	public void testAddLoanOffersException() {
		when(loanOfferRespsitory.findByMinimumSalaryEligibity(loanOffer.getMinimumSalaryEligibity()))
				.thenReturn(loanOffer);

		try {
			LoanOffer loanOfferAct = customerLoanServiceImpl.addLoanOffer(loanOffer);
			assertEquals(loanOffer, loanOfferAct);

		} catch (LoanOfferException e) {
			assertThat(e.getMessage()).isEqualTo("Offer already exists for the the given Salary eligibility");
		}

	}

	@Test
	public void testUpdateLoanOffers() {
		when(loanOfferRespsitory.existsById(loanOffer.getLoanOfferId())).thenReturn(true);
		when(loanOfferRespsitory.save(loanOffer)).thenReturn(loanOffer);

		try {
			LoanOffer loanOfferAct = customerLoanServiceImpl.updateLoanOffer(loanOffer);
			assertEquals(loanOffer, loanOfferAct);

		} catch (LoanOfferException e) {
		}

	}

	@Test
	public void testUpdateLoanOffersException() {
		when(loanOfferRespsitory.existsById(loanOffer.getLoanOfferId())).thenReturn(false);

		try {
			LoanOffer loanOfferAct = customerLoanServiceImpl.updateLoanOffer(loanOffer);
			assertEquals(loanOffer, loanOfferAct);

		} catch (LoanOfferException e) {
			assertThat(e.getMessage()).isEqualTo("Offer not exists");
		}

	}

	@Test
	public void testdeleteLoanOffers() {
		when(loanOfferRespsitory.existsById(loanOffer.getLoanOfferId())).thenReturn(true);
		try {
			customerLoanServiceImpl.deleteLoanOffer(loanOffer.getLoanOfferId());

		} catch (LoanOfferException e) {
		}

	}
	@Test
	public void testdeleteLoanOffersException() {
		when(loanOfferRespsitory.existsById(loanOffer.getLoanOfferId())).thenReturn(false);
		try {
			customerLoanServiceImpl.deleteLoanOffer(loanOffer.getLoanOfferId());

		} catch (LoanOfferException e) {
			assertThat(e.getMessage()).isEqualTo("Offer not exists");
		}

	}
	/*Jaison Code Ends*/
	
}
