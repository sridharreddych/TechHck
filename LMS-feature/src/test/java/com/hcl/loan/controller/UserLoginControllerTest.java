package com.hcl.loan.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.loan.model.User;
import com.hcl.loan.service.UserLoginService;

@RunWith(SpringRunner.class)
public class UserLoginControllerTest {

	@Mock
	private UserLoginService userLoginService;
	/*
	 * @InjectMocks private UserLoginController userLoginController;
	 */
	User mockUserLogin = new User(1, "kamal.joshi@hcl.com", "password", 22);

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	// @Ignore
	@Test
	public void getUserLoginById() {
		Mockito.when(userLoginService.validateLoginUser(Mockito.anyObject())).thenReturn(mockUserLogin);
		User user = userLoginService.validateLoginUser(mockUserLogin);
		assertEquals(mockUserLogin, user);
	}
}
