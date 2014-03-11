package controller.SubjectManagement.validator;

import model.Subject;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import business.SubjectManager;

import controller.SubjectManagement.bean.SubjectBean;

public class AddSubjectValidator implements Validator {
	private SubjectManager subjectManager;

	@Override
	public boolean supports(Class<?> obj) {
		// just validate the FileUpload instances
		return SubjectBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {

		SubjectBean subjectBean = (SubjectBean) target;

		// if subject name, subject description are empty, report error
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectName",
				"subject.error.emptyName");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectDescription",
				"subject.error.emptyDescription");
		

		// if name of subject < 5 characters, and description < 20 chars, report errors
		if (subjectBean.getSubjectName().length() > 0) {
			if (subjectBean.getSubjectName().length() < 5) {
				errors.rejectValue("subjectName", "subject.error.lengthName");
			}
			
			
			String subjectName = subjectBean.getSubjectName();			
			Subject subject = subjectManager.getSubjectByName(subjectName);			
			
			// check if a subject name is exist or not 
			if(subject != null){
				if (subjectName
						.equals(subject.getSubjectName())) {
					errors.rejectValue("subjectName", "subject.error.existName");
				}
			}
		}

		if (subjectBean.getSubjectDescription().length() > 0) {
			if (subjectBean.getSubjectDescription().length() < 20) {
				errors.rejectValue("subjectName",
						"subject.error.lengthDescription");
			}
		}

	}

	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}
}
