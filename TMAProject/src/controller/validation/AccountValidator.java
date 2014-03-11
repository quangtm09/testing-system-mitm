package controller.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import controller.bean.AccountBean;

public class AccountValidator implements Validator{

	@Override
	public boolean supports(Class<?> obj) {
		// TODO Auto-generated method stub
		return AccountBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountName", "accountName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personId", "personId.required");
	}

}
