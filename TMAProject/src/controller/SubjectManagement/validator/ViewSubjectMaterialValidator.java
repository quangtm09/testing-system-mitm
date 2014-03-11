package controller.SubjectManagement.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import controller.SubjectManagement.bean.SubjectBean;

public class ViewSubjectMaterialValidator implements Validator{

	@Override
	public boolean supports(Class<?> obj) {
		// TODO Auto-generated method stub
		return SubjectBean.class.equals(obj);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SubjectBean materialList = (SubjectBean) target;

		
		// like others validator
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "materialList",
				"error.selectManyFile");

		if (materialList.getMaterialList() != null) {
			if (materialList.getMaterialList().size() > 1) {
				errors.rejectValue("materialList", "error.selectManyFile");
			}
		}
		
	}

}
