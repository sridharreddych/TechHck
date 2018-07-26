package com.hcl.loan.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.loan.model.User;

public class UserRowMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user= new User();
		user.setUserId(rs.getInt("USER_ID"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setRole(rs.getString("ROLE_ID"));
		user.setGender(rs.getString("GENDER"));
		user.setDateofbirth(rs.getDate("DATEOFBIRTH"));
		user.setHniFlag(rs.getString("HNI_FLAG"));
		user.setEmailId(rs.getString("EMAIL_ID"));
		user.setMobileNumber(rs.getString("MOBILE_NUMBER"));
		user.setStatus(rs.getString("STATUS"));
		user.setPreferredLang(rs.getString("PREFERRED_LANG"));
		user.setCurrentEmployer(rs.getString("CURRENT_EMPLOYER"));
		user.setBankAccountNo(rs.getString("BANK_ACCOUNT_NO"));
		user.setBankName(rs.getString("BANK_NAME"));
		user.setBankIfscCode(rs.getString("BANK_IFSC_CODE"));
		user.setRepaymentMode(rs.getString("REPAYMENT_MODE"));
		return user;
	}

}