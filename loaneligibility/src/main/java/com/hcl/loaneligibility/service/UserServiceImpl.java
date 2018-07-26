package com.hcl.loaneligibility.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.loaneligibility.dao.UserRepository;
import com.hcl.loaneligibility.model.Customer;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Customer createUser(Customer customer) {
		return userRepository.save(customer);
	}

}
