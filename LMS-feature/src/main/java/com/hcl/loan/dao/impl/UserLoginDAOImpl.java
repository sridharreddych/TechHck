 package com.hcl.loan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hcl.loan.dao.UserLoginDAO;
import com.hcl.loan.model.User;

public class UserLoginDAOImpl implements UserLoginDAO {

	JdbcTemplate jdbcTemplate;

	public UserLoginDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User validateLoginUser(User user) {
		return this.jdbcTemplate.queryForObject(
				"select user_id, email_id, role_id, password from user where email_id = ?",
				new Object[] { user.getEmailId() }, new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUserId(rs.getInt("user_id"));
						user.setEmailId(rs.getString("email_id"));
						user.setRoleId(rs.getInt("role_id"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	}
}
