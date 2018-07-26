package com.hcl.loan.dao.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hcl.loan.dao.LoanApprovalDAO;
import com.hcl.loan.model.Loan;

public class LoanApprovalDAOImpl implements LoanApprovalDAO {

	private Logger logger = Logger.getLogger(LoanApprovalDAOImpl.class.getName());
	@Autowired
	Environment env;

	private JdbcTemplate jdbcTemplate;

	public LoanApprovalDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Method which updates the Loan table
	 * 
	 * @param loan
	 * @return
	 */
	public void updateLoan(Loan loan) {
		logger.info("Inside LoanApprovalDAOImpl: updateLoan()");
		String updateLoanQuery = env.getProperty("update.loan.query2");
		Object[] params = new Object[] { loan.getLoanStatus(), loan.getApprovedLoanAmount(), loan.getRemarks(),
				loan.getLoanId() };
		jdbcTemplate.update(updateLoanQuery, params);
		logger.info("LoanApprovalDAOImpl - updateLoan Successfully update Loan details");
	}

	/**
	 * Method return all the pending loans with approver
	 * 
	 * @return List<Loan>
	 */
	public List<Loan> getPendingLoans() {
		logger.info("Inside LoanApprovalDAOImpl: getPendingLoans()");
		String fetchLoanQuery = env.getProperty("fetch.loan.query");
		logger.info("LoanApprovalDAOImpl - getPendingLoans Successfully fetch pending Loan details");
		return jdbcTemplate.query(fetchLoanQuery, new BeanPropertyRowMapper(Loan.class));
	}

	/**
	 * Method which updates Loan Audit table
	 * 
	 * @param loan
	 */
	public void updateAuditLog(Loan loan) {
		logger.info("Inside LoanApprovalDAOImpl: updateAuditLog()");
		String updateLoanQuery = env.getProperty("update.audit.loan.query");
		Object[] params = new Object[] { loan.getLoanId(), loan.getLoanStatus(), loan.getPendingWith(),
				loan.getRemarks(), "1", new Date(), "1", new Date() };
		jdbcTemplate.update(updateLoanQuery, params);
		logger.info("LoanApprovalDAOImpl - updateAuditLog Successfully update Auditlog details");
	}

	/**
	 * Method which returns hniflag for the user
	 * 
	 * @param userId
	 * @return hni
	 */
	public Integer getHNI(Integer userId) {
		logger.info("Inside LoanApprovalDAOImpl: getHNI()");
		String fetchHNIQuery = env.getProperty("fetch.hni.query");
		logger.info("End LoanApprovalDAOImpl - getHNI()");
		return jdbcTemplate.queryForObject(fetchHNIQuery, new Object[] { userId }, Integer.class);
	}

}
