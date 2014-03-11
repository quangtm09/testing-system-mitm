package controller.SubjectManagement.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import controller.SubjectManagement.bean.SubjectBean;

public class SubjectManagementValidator implements Validator {
	@Override
	public boolean supports(Class<?> obj) {
		// just validate the FileUpload instances
		return SubjectBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {

		
		// must selected something to pass this validator
		SubjectBean subjectList = (SubjectBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectList",
				"error.selectManyFile");

		if (subjectList.getSubjectList() != null) {
			if (subjectList.getSubjectList().size() > 1) {
				errors.rejectValue("subjectList", "error.selectManyFile");
			}
		}
	}
}
