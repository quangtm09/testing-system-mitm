package controller.CourseTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CourseTemplate;
import model.Subject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import Bean.CourseTemplateBean;
import Bean.SubjectBean;
import business.CourseTemplateBO;

public class ViewCourseTemplate implements Controller {

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
		Map<String, Object> model = new HashMap<String, Object>();

		int id = Integer.parseInt(request.getParameter("id"));
		CourseTemplate courseTemplate = courseTemplateBO.getById(id);

		String strStartDate = null;
		String strEndDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if (courseTemplate.getStartDay() != null) {
			strStartDate = sdf.format(courseTemplate.getStartDay().getTime());
		}
		if (courseTemplate.getEndDay() != null) {
			strEndDate = sdf.format(courseTemplate.getEndDay().getTime());
		}
		List<SubjectBean> subject = new ArrayList<SubjectBean>();

		Iterator<Subject> iter = courseTemplate.getSubject().iterator();

		while (iter.hasNext()) {
			Subject s = (Subject) iter.next();
			SubjectBean bean = new SubjectBean();
			bean.setName(s.getSubjectName());
			subject.add(bean);
			System.out.println(s.getSubjectName());
		}

		for (SubjectBean s : subject) {
			System.out.println(s.getName());
		}

		CourseTemplateBean ctb = new CourseTemplateBean();

		ctb.setId(courseTemplate.getId());
		ctb.setName(courseTemplate.getName());
		ctb.setStartDay(strStartDate);
		ctb.setStartEnd(strEndDate);
		ctb.setDescription(courseTemplate.getDescription());
		// ctb.setSubject(courseTemplate.getSubject());

		model.put("courseTemplate", ctb);
		model.put("subject1", subject);

		return new ModelAndView("CourseTemplate/View", "model", model);
	}

}
