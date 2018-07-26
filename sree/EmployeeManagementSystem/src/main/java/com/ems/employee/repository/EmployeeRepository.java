package com.ems.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ems.employee.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	  //Employee findemployeeById(Long id);
	Employee findByEmployeeId(Long id);
	  
}
