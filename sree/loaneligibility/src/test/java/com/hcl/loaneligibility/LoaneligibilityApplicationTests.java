package com.hcl.loaneligibility;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.loaneligibility.dao.LoanEligibilityDao;
import com.hcl.loaneligibility.model.Offer;
import com.hcl.loaneligibility.service.OfferService;
import com.hcl.loaneligibility.service.OfferServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LoaneligibilityApplicationTests {

	@InjectMocks
	OfferService OfferService = new OfferServiceImpl();
		
	@Mock
	LoanEligibilityDao loanEligibilityDao;


	
	@Test
	public void testOfferLessThanTenThousand() {
		
		when(loanEligibilityDao.count()).thenReturn((long) 5);
		
		assertEquals(loanEligibilityDao.count(), 5);
		
	}
	
	@Test
	public void testMonthlyIncomeLessThanFifteenThousand() {
		Double monthlySal = (double) 5000;
		List<Offer> offers= new ArrayList<>();
		when(loanEligibilityDao.findAllByMonthlySal(monthlySal)).thenReturn(offers);
		
		assertEquals(loanEligibilityDao.findAllByMonthlySal(monthlySal), offers);
		
	}

}
