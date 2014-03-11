package controller.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Person;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import controller.bean.PersonBean;

public class PersonValidator implements Validator{
	//supports(Class) - Can this Validator validate instances of the supplied Class?
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	@Override
	public boolean supports(Class<?> obj) {
		return  PersonBean.class.equals(obj);	
	}

	@Override
	//validate(Object,Errors) - validates the given object and in case of validation errors, registers those with the given Errors object
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "fullName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellPhone", "cellPhone.required");
		PersonBean person=(PersonBean)target;
		Pattern pattern=Pattern.compile(EMAIL_PATTERN);
		Matcher matcher=pattern.matcher(person.getEmail());
		if(!matcher.matches()){
			errors.rejectValue("email", "email.format");
		}
	}

}
