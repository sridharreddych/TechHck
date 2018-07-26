package com.hcl.loan.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.hcl.loan.model.User;
import com.hcl.loan.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserRegistrationController {

	private static final Logger logger = Logger.getLogger(UserRegistrationController.class);

	@Autowired
	private UserService userService;

	/*
	 * To fetch the user based on user id.
	 * @input: userId : String/long
	 * @output: json data with http status
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> fetchUser(@PathVariable("id") long userId) {

		logger.debug("fetchUser(id) - Method Input - " + userId);

		User user = userService.fetchUser(userId);
		logger.debug("fetchUser(id) - fetched User data - " + user);
		
		if (null == user) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long userId, @Valid @RequestBody User user) {

		logger.debug("updateUser(id) - Method Input - ID:" + userId + "User:" + user);
		userService.updateUser(userId, user);
		logger.debug("updateUser(id) - Updated User - ID:" + userId + "User:" + user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/*
	 * To persist user details
	 * @input: user: User
	 * @output: resource uri with http status (success:200 / failure:409)
	 */
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> registerUser(@Valid @RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
		logger.debug("registerUser(id) - Method Input - User:" + user);
		userService.persistUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/users/{id}").buildAndExpand(user.getUserId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	/*
	 * To delete user details
	 * @input: userId : String/long, user: User
	 * @output: http status (success:200 / failure:404)
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long userId) {

		logger.debug("deleteUser(id) - Method Input - UserId:" + userId);
		userService.deleteUser(userId);
		logger.debug("User with id: " + userId+" deleted");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}