package com.hcl.loan.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hcl.loan.model.EMI;

public class EMIValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return EMI.class.isAssignableFrom(clazz);
	}
/**
 * custom validation for loan type
 */
	@Override
	public void validate(Object target, Errors erro) {
		
		EMI emi=(EMI)target;
		if(emi.getLoanType().isEmpty()) {
			erro.rejectValue("loanType","", "loan type should not be blank");
		}else if(!(emi.getLoanType().equals("car")||emi.getLoanType().equals("home")||emi.getLoanType().equals("personal")||emi.getLoanType().equals("education"))) {
			erro.rejectValue("loanType","", "only accept from (car,home,personla,education)");
		}
		
	}

}
