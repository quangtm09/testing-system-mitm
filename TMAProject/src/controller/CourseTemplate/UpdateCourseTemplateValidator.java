package controller.CourseTemplate;

import java.util.GregorianCalendar;

import model.CourseTemplate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import business.CourseTemplateBO;

import Bean.CourseTemplateBean;

public abstract class UpdateCourseTemplateValidator implements Validator {

	private CourseTemplateBO courseTemplate;

	public CourseTemplateBO getCourseTemplate() {
		return courseTemplate;
	}

	public void setCourseTemplate(CourseTemplateBO courseTemplate) {
		this.courseTemplate = courseTemplate;
	}

        @Override
	public boolean supports(Class<?> obj) {
		// TODO Auto-generated method stub
		return CourseTemplateBean.class.equals(obj);
	}
	

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		CourseTemplateBean ct = (CourseTemplateBean) obj;
		
		if (ct.getName() == null || ct.getName().trim().isEmpty()) {
			errors.rejectValue("name", "Insert.error.name");
		}

		if (ct.getStartDay() == null || ct.getStartDay().trim().isEmpty()
				|| !testDate(ct.getStartDay())) {
			errors.rejectValue("startDay", "Insert.error.startDay");
		}
		if (!LessThanDate(ct.getStartDay(), ct.getStartEnd())) {
			errors.rejectValue("startDay", "Insert.error.startDay1");
		}
		if (ct.getStartEnd() == null || ct.getStartEnd().trim().isEmpty()
				|| !testDate(ct.getStartEnd())) {
			errors.rejectValue("startEnd", "Insert.error.endDay");
		}
		if (!LessThanDate(ct.getStartDay(), ct.getStartEnd())) {
			errors.rejectValue("startEnd", "Insert.error.endDay1");
		}
		if (ct.getInter() == null) {
			errors.rejectValue("SubjectError", "Insert.error.subject");
		}

		/*if (this.courseTemplate.CheckName(ct.getName().toLowerCase())) {
			errors.rejectValue("name", "Insert.error.name.exits");
		}*/

	}

	private boolean testDate(String date) {
		if (date.trim() == "" || date == null) {
			System.out.println("null");
			return false;
		}
		String[] arrDate = date.split("/");
		if (arrDate.length != 3)
			return false;
		else {
			try {
				if (arrDate[0].length() != 2 || arrDate[1].length() != 2
						|| arrDate[2].length() != 4) {
					System.out.println("null1");
					return false;
				}
				int day = Integer.parseInt(arrDate[0]);
				int month = Integer.parseInt(arrDate[1]);
				int year = Integer.parseInt(arrDate[2]);
				if (!(day >= 1 && day <= 31) || !(month >= 1 && month <= 12)
						|| !(year >= 1900 && year <= 2020))
					return false;

			} catch (Exception ex) {
				return false;
			}
		}
		return true;
	}

	public boolean LessThanDate(String date1, String date2) {
		String[] str1 = date1.split("/");
		String[] str2 = date2.split("/");

		if (!date1.trim().isEmpty() && !date2.trim().isEmpty()) {
			int int11 = Integer.parseInt(str1[0]);
			int int12 = Integer.parseInt(str1[1]);
			int int13 = Integer.parseInt(str1[2]);

			int int21 = Integer.parseInt(str2[0]);
			int int22 = Integer.parseInt(str2[1]);
			int int23 = Integer.parseInt(str2[2]);

			if (int13 < int23) {
				return true;
			} else if (int13 > int23) {
				return false;
			} else {
				if (int12 < int22) {
					return true;
				} else if (int12 > int22) {
					return false;
				} else {
					if (int11 < int21) {
						return true;
					} else if (int11 > int21) {
						return false;
					} else {
						return false;
					}
				}
			}
		}
		return false;

	}

}
