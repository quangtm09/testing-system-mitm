package controller.CourseTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseTemplate;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import business.CourseTemplateBO;
import business.impl.CourseTemplateBOimpl;

public class ViewPDFCourseTemplateValidator implements Controller {
	private CourseTemplateBO courseTemplateBO;

	public ViewPDFCourseTemplateValidator() {

	}

	public CourseTemplateBO getCourseTemplateBO() {
		return courseTemplateBO;
	}

	public void setCourseTemplateBO(CourseTemplateBO courseTemplateBO) {
		this.courseTemplateBO = courseTemplateBO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		Set<CourseTemplate> ListCt = courseTemplateBO.getCourseTemplate();
		Map<String, CourseTemplate> Data = new HashMap<String, CourseTemplate>();
		
		Iterator<CourseTemplate> iterCT = ListCt.iterator();
		
		int k = 1;
		while (iterCT.hasNext()) {
			CourseTemplate ct = (CourseTemplate) iterCT.next();
			Data.put(k + "", ct);
			k++;
		}

		return new ModelAndView("pdf-view", "Data", Data);
	}
}
