package com.hcl.loan.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hcl.loan.model.Address;


public class AddressRowMapper implements RowMapper<Address>
{
	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address= new Address();
		address.setUserId(rs.getLong("user_id"));
		address.setAddressType(rs.getString("ADDRESS_TYPE"));
		address.setAddress1(rs.getString("ADDRESS1"));
		address.setAddress2(rs.getString("ADDRESS2"));
		address.setCity(rs.getString("CITY"));
		address.setState(rs.getString("STATE"));
		address.setCountry(rs.getString("COUNTRY"));
		address.setPincode(rs.getInt("PINCODE"));
		return address;
	}

}