package com.hcl.loan.model.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hcl.loan.model.User;

@Component
public class UserLoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		String email = user.getEmailId();
		String password = user.getPassword();

		if (null == email || null == password || "".equalsIgnoreCase(email) || "".equalsIgnoreCase(password)) {
			errors.rejectValue("email", "error.bad.credentials");
		}

		if (email != null && email.indexOf('@') == -1) {
			errors.rejectValue("email", "error.invalid.email");
		}

	}

}
