package com.hcl.loan.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hcl.loan.constants.LoanApprovalConstants;
import com.hcl.loan.dao.StatusDAO;
import com.hcl.loan.model.Loan;

@Repository
public class StatusDAOImpl implements StatusDAO {
	@Autowired
	Environment env;
	private static final Logger logger = Logger.getLogger(StatusDAOImpl.class);
	JdbcTemplate jdbcTemplate;

	public StatusDAOImpl(DataSource datasource) {

		jdbcTemplate = new JdbcTemplate(datasource);

	}

	@Override
	public List<Loan> fetchApplicationStatus(Integer userID, String statusType) {
		logger.debug("start fetchApplicationStatus(): userID:" + userID + " statusType:" + statusType);
		String query = "";
		if (statusType.equals(LoanApprovalConstants.PENDING_STATUS)) {
			query = env.getProperty("PENDING_APPLICATION_QUERY");
		} else if (statusType.equals(LoanApprovalConstants.DISPATCHED_STATUS)) {
			query = env.getProperty("DISPACHED_APPLICATION_QUERY");
		}
		logger.debug("query :" + query);
		Object[] inputs = new Object[] { userID };
		List<Loan> loanList = jdbcTemplate.query(query, inputs, new BeanPropertyRowMapper(Loan.class));
		logger.debug("loanList :" + loanList.isEmpty());
		return loanList;

	}

}
