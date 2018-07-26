package com.hcl.loan.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.hcl.loan.dao.LoanDisbursmentDao;
import com.hcl.loan.model.Loan;  
import com.hcl.loan.model.LoanDisbursment;
import com.hcl.loan.service.exception.ApprovedLoanNotFoundException;



 
 
@Repository
public class LoanDisbursmentDaoImpl implements LoanDisbursmentDao {

	
    private static final String LOAN_DISBURSED = "DISBURSED";
	
	JdbcTemplate jdbcTemplate;
     
    @Autowired
    Environment env;

    public LoanDisbursmentDaoImpl(DataSource aDataSource)
    {
    	jdbcTemplate =new JdbcTemplate(aDataSource);
    }
	
	public List<Loan> findApprovedLoanDeatils() {
       
		List<Loan> loanDtl;
    	
       
    	
		loanDtl  =  jdbcTemplate.query(env.getProperty("APPROVED_LOAN_QUERY"), new Object[] { },
    		   new RowMapper<Loan>()
    		   {
    	   			public Loan mapRow(ResultSet rs,int rownum) throws SQLException
    	   				{
    	   					Loan lnDtl = new Loan();
    	   					lnDtl.setLoanId(rs.getLong(1));
    	   					return lnDtl;
    	   				}
    		   });
		
		if(loanDtl!= null && loanDtl.isEmpty())
		{
			throw new ApprovedLoanNotFoundException("No Approved Loans to Dispatch");
		}
		 
       return loanDtl;
       
    }

    public boolean save(LoanDisbursment loanDisb) 
    {
     	
    	jdbcTemplate.update(env.getProperty("LOAN_DISB_INSERT"),new Object[]{loanDisb.getLoanId(),loanDisb.getUserId(),"2017-08-02",loanDisb.getUserId(),"2017-08-02",loanDisb.getDisbursmentDate(),loanDisb.getNumberOfChecks(),loanDisb.getDisbursmentMode(),loanDisb.getEmailNotification(),loanDisb.getAccountNumber()},
    			new int[]{Types.BIGINT,Types.BIGINT,Types.DATE,Types.BIGINT,Types.DATE,Types.DATE,Types.INTEGER,Types.VARCHAR,Types.CHAR,Types.LONGNVARCHAR});
    	   	System.out.println(loanDisb.getLoanId());
    	jdbcTemplate.update(env.getProperty("UPDATE_LOAN_STATUS_SQL"), LOAN_DISBURSED, loanDisb.getLoanId());
    	System.out.println(loanDisb.getLoanId());
    	return true;
    }

     
 
}

