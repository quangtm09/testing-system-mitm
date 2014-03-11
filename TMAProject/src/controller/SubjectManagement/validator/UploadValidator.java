package controller.SubjectManagement.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import controller.SubjectManagement.bean.MaterialBean;

public class UploadValidator implements Validator {

	@Override
	public boolean supports(Class<?> obj) {
		// just validate the FileUpload instances
		return MaterialBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {

		// check if subject name and upload fields were blank or not
		MaterialBean materialBean = (MaterialBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subjectName",
				"subject.error.emptyName");

		System.out.println(materialBean);
		
		if (materialBean.getMaterialFile().getSize() == 0) {
			errors.rejectValue("materialFile", "required.fileUpload");
		}		

	}

}
