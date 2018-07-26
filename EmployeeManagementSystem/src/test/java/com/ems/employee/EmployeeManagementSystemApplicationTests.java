package com.ems.employee;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.ems.employee.controller.EmployeeController;
import com.ems.employee.model.Employee;
import com.ems.employee.repository.EmployeeRepository;
import com.ems.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.Module.SetupContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeManagementSystemApplicationTests {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Before(value = "")
	public void setUp()  {
		 MockitoAnnotations.initMocks(this);
	}
	
		
	@Test
	public void testEmployeeController() {
		
		Employee e = new Employee();
		e.setEmployeeId(1L);
		e.setDepartmentName("dept");
		e.setEmployeeAge(30);
		e.setEmployeeName("sree");
		
		when(employeeService.createEmployee(e)).thenReturn(e);
	}

}
