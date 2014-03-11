package controller.CourseTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import util.HibernateUtil;

import business.CourseTemplateBO;

public class DeleteCourseTemplate implements Controller {

	private CourseTemplateBO courseTemplateBO;

	public CourseTemplateBO getCourseTemplateBO() {
		return courseTemplateBO;
	}

	public void setCourseTemplateBO(CourseTemplateBO courseTemplateBO) {
		this.courseTemplateBO = courseTemplateBO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// Map<String, Object> model = new HashMap<String, Object>();

		int id = Integer.parseInt(request.getParameter("id"));

		if (id != 0) {
			HibernateUtil.beginTransaction();
			courseTemplateBO.DeletebyID(id);
			HibernateUtil.commitTransaction();
		}

		return new ModelAndView(new RedirectView(("../index.jsp"), true));
	}

}
