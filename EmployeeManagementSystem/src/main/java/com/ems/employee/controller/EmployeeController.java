package com.ems.employee.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ems.employee.model.Employee;
import com.ems.employee.service.EmployeeService;

import com.ems.employee.controller.*;
import com.ems.employee.exception.DataFormatException;

/*import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;*/

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping(value = "/cre", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerUser(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder) {
		//logger.debug("registerUser(id) - Method Input - User:" + employee);
		employeeService.createEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        System.out.println("Fetching Employee with id " + id);
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

	 @PostMapping(value="/create",headers="Accept=application/json")
	    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder){
	        System.out.println("Creating User "+employee.getEmployeeName());
	        employeeService.createEmployee(employee);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	 @PutMapping(value = "/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,  @RequestBody Employee employee) {

			//logger.debug("updateUser(id) - Method Input - ID:" + userId + "User:" + user);
		 	employeeService.updateEmployee(employee);
			//logger.debug("updateUser(id) - Updated User - ID:" + userId + "User:" + user);
			return new ResponseEntity<>(employee, HttpStatus.OK);
		}

	 @DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long employeeId) {

			//logger.debug("deleteUser(id) - Method Input - UserId:" + userId);
			employeeService.deleteEmployee(employeeId);
			//logger.debug("User with id: " + userId+" deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		}


	
	/*@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Employee createHotel(@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response) {
		Employee createdEmployee = this.employeeService.createEmployee(employee);
		return createdEmployee;
	}

	

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a single hotel.", notes = "You have to provide a valid hotel ID.")
	public @ResponseBody Employee getHotel(
			@ApiParam(value = "The ID of the hotel.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee hotel = this.employeeService.getEmployee(id);

		return hotel;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update a hotel resource.", notes = "You have to provide a valid hotel ID in the URL and in the payload. The ID attribute can not be updated.")
	public void updateHotel(
			@ApiParam(value = "The ID of the existing hotel resource.", required = true) @PathVariable("id") Long id,
			@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response) {
		
		if (id != employee.getEmployeeId())
			throw new DataFormatException("ID doesn't match!");
		this.employeeService.updateHotel(employee);
	}

	// todo: @ApiImplicitParams, @ApiResponses
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json",
			"application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a hotel resource.", notes = "You have to provide a valid hotel ID in the URL. Once deleted the resource can not be recovered.")
	public void deleteHotel(
			@ApiParam(value = "The ID of the existing hotel resource.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
				this.employeeService.deleteHotel(id);
	}*/
}
