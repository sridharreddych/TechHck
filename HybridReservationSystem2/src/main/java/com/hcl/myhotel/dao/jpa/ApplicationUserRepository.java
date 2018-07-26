package com.hcl.myhotel.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.myhotel.domain.ApplicationUser;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}