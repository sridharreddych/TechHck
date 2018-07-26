package com.hcl.loan.dao.impl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.loan.controller.EMICalculatorController;
import com.hcl.loan.dao.RateOfInterestDAO;

@Repository
public class RateOfInterestDAOImpl implements RateOfInterestDAO {
	private static final Logger logger = Logger.getLogger(RateOfInterestDAOImpl.class);
	@Autowired
	Environment env;

	JdbcTemplate jdbcTemplate;

	public RateOfInterestDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public double fetchRateOfInterest(String loanType) {
		logger.debug("start fetchRateOfInterest() :" + loanType);
		String selectQuery = env.getProperty("LOAN_QUERY");
		Object[] inputs = new Object[] { loanType };
		double rateOfInterest = jdbcTemplate.queryForObject(selectQuery, inputs, Double.class);
		logger.debug("end fetchRateOfInterest() :" + rateOfInterest);
		return rateOfInterest;

	}

}
