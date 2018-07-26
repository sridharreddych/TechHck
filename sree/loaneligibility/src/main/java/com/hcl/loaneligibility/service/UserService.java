package com.hcl.loaneligibility.service;

import org.springframework.stereotype.Service;

import com.hcl.loaneligibility.model.Customer;

@Service
public interface UserService {

	Customer createUser(Customer customer);
}
