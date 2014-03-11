package controller.SubjectManagement.validator;

import model.Subject;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import business.SubjectManager;
import controller.SubjectManagement.bean.SubjectBean;

public class ModifySubjectValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		// just validate the FileUpload instances
		return SubjectBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {

		SubjectBean subjectBean = (SubjectBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectName",
				"subject.error.emptyName");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectDescription",
				"subject.error.emptyDescription");
		
		try{
			Integer subjectId = subjectBean.getSubjectId();
		} catch (Exception ex){
			errors.rejectValue("subjectName", "subject.error.Id");
		}

		if (subjectBean.getSubjectName().length() > 0) {
			if (subjectBean.getSubjectName().length() < 5) {
				errors.rejectValue("subjectName", "subject.error.lengthName");
			}
		}

		if (subjectBean.getSubjectDescription().length() > 0) {
			if (subjectBean.getSubjectDescription().length() < 20) {
				errors.rejectValue("subjectName",
						"subject.error.lengthDescription");
			}
		}

	}
}
