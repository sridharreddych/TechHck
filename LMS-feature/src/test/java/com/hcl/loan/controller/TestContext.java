package com.hcl.loan.controller;

import org.mockito.Mockito; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hcl.loan.dao.LoanDisbursmentDao;
import com.hcl.loan.dao.impl.LoanDisbursmentDaoImpl;
import com.hcl.loan.service.LoanDisbursmentService;
import com.hcl.loan.service.impl.LoanDisbursmentServiceImpl;


@Configuration
public class TestContext {

//    @Bean
//    public LoanDisbursmentService LoanDisbService() 
//    {
//	return Mockito.mock(LoanDisbursmentServiceImpl.class);
//    }
//    @Bean
//    public LoanDisbursmentDao LoanDisbDAO() {
//        return Mockito.mock(LoanDisbursmentDaoImpl.class);
//    }
}