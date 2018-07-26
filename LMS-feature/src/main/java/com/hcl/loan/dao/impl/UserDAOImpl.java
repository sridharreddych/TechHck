package com.hcl.loan.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.loan.dao.UserDAO;
import com.hcl.loan.model.Address;
import com.hcl.loan.model.User;

@Repository
@PropertySource("classpath:sql.properties")
public class UserDAOImpl implements UserDAO {

	private static final String FETCH_USER_BYID_STATUS = "SELECT * FROM User where user_id = ? and status = ?";
	private static final String DUPLICATE_USER_CHK="SELECT * FROM User where email_id = ? or (upper(first_name)=upper(?) and date_format(dateofbirth,'%Y-%m-%d')=date_format(?,'%Y-%m-%d'))";
	private static final String UPDATE_USER_STATUS = "update user set status = ? where user_id = ?";
	private static final String INSERT_USER = "INSERT INTO USER(FIRST_NAME , LAST_NAME, ROLE_ID, GENDER, DATEOFBIRTH, PASSWORD, HNI_FLAG, EMAIL_ID, MOBILE_NUMBER, STATUS, PREFERRED_LANG, CURRENT_EMPLOYER, BANK_ACCOUNT_NO , BANK_NAME, BANK_IFSC_CODE, REPAYMENT_MODE,CREATED_DATE,MODIFIED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_USER_ADD = "INSERT INTO USER_ADDRESS(USER_ID,ADDRESS_TYPE,ADDRESS1,ADDRESS2,CITY,STATE,COUNTRY,PINCODE) VALUES(?,?,?,?,?,?,?,?)";
	private static final String FETCH_USER_ADDRESS="select * from user_address where user_id=?";
	private static final String SELECT_MAX_USERID = "select max(user_id) from user";

	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	private JdbcTemplate jdbcTemplate;

	@Resource
	private Environment env;

	// Constructor -initiates jdbcTemplate
	public UserDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public User fetchUser(long userId) {

		logger.debug("fetchUser(id) - Method Input - " + userId);

		User user = jdbcTemplate.queryForObject(FETCH_USER_BYID_STATUS, new Object[] { userId, "ACTIVE" },
				new UserRowMapper());
		user.setUserAddresses(fetchUserAddress(userId));

		return user;
	}

	private List<Address> fetchUserAddress(long userId) {

		logger.debug("fetchUser(id) - Method Input - " + userId);

		logger.debug("Query - " + env.getProperty("FETCH_USER_ADDRESS"));
		return jdbcTemplate.query(FETCH_USER_ADDRESS, new AddressRowMapper(), userId);
	}

	@Override
	public User updateUser(long userId, User user) {

		logger.debug("updateUser(id) - Method Input - UserId: " + userId + " User: " + user);
		int userCount = -1;
		try {
			userCount = jdbcTemplate.update(UPDATE_USER_STATUS, user.getStatus(), userId);
			logger.debug("updateUser(id) - Method Input - UserId: " + userId + " User: " + user
					+ "updated successfully " + userCount);
		} catch (DataAccessException dae) {
			logger.error("Unable to update user with User-Id: " + userId, dae);
		}
		if (userCount > 0)
			return user;

		return new User();
	}

	@Override
	public int persistUser(User user) {

		int userCount = -1;
		logger.debug("persistUser(id) - Method Input - User: " + user);

		try {
			userCount = jdbcTemplate.update(INSERT_USER, user.getFirstName(), user.getLastName(), user.getRole(),
					user.getGender(), user.getDateofbirth(), user.getPassword(), user.getHniFlag(), user.getEmailId(),
					user.getMobileNumber(), "ACTIVE", user.getPreferredLang(), user.getCurrentEmployer(),
					user.getBankAccountNo(), user.getBankName(), user.getBankIfscCode(), user.getRepaymentMode(),
					new Date(), new Date());

			logger.debug("persistUser(id) - User persisted with Status : " + userCount);

			String userId = jdbcTemplate.queryForObject(SELECT_MAX_USERID, String.class);
			user.setUserId(Integer.parseInt(userId));
			logger.debug("persistUser(id) - User persisted with User Id : " + userId);

			persistUserAddress(userId, user.getAddresses());
			logger.debug("persistUser(id) - Address details persisted for User Id : " + userId);
		} catch (DataAccessException dae) {
			logger.error(dae);
		}

		return userCount;

	}

	public int persistUserAddress(String userId, List<Address> userAddress) {

		logger.debug("persistUserAddress(id) - Method Input - UserId: " + userId + " Address: " + userAddress);
		if (userAddress == null || userAddress.isEmpty()) {
			return -1;
		} else {
			for (Address address : userAddress) {
				logger.debug("persistUserAddress(id) -  Address: " + address);
				int count = jdbcTemplate.update(INSERT_USER_ADD, userId, address.getAddressType(),
						address.getAddress1(), address.getAddress2(), address.getCity(), address.getState(),
						address.getCountry(), address.getPincode());
				logger.debug("persistUserAddress(id) -  Address persisted status : " + count);
			}
		}
		return 1;
	}

	@Override
	public void deleteUser(long userId) {

		logger.debug("deleteUser(id) - Method Input - UserId: " + userId);
		int count = 0;
		try {
			count = jdbcTemplate.update(UPDATE_USER_STATUS, "INACTIVE", userId);
		} catch (DataAccessException dae) {
			logger.error(dae);
		}
		logger.debug("deleteUser(id) status: " + count);
	}

	@Override
	public boolean existingUserCheck(User user) {

		if (user == null) {
			return false;
		} else {

			List lst = jdbcTemplate.query(DUPLICATE_USER_CHK,
					new Object[] { user.getEmailId(), user.getFirstName(), user.getDateofbirth() },
					new UserRowMapper());
			if (lst.isEmpty()) {
				return false;
			} else {
				return true;
			}
		}

	}

}
