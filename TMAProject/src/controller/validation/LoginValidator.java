package controller.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import controller.bean.AccountBean;


public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> obj) {
		// TODO Auto-generated method stub
		return AccountBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "login.error.username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "login.error.password.required");
	}

}
