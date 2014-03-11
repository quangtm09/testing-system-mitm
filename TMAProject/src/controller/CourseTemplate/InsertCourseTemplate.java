package controller.CourseTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseTemplate;
import model.Subject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import business.impl.*;

import util.HibernateUtil;

import Bean.CourseTemplateBean;
import business.CourseTemplateBO;

public class InsertCourseTemplate extends SimpleFormController {

	private CourseTemplateBO courseTemplateBO;
	private SubjectManagerImpl SubjectManagerImpl;

	public SubjectManagerImpl getSubjectManagerImpl() {
		return SubjectManagerImpl;
	}

	public void setSubjectManagerImpl(SubjectManagerImpl subjectManagerImpl) {
		SubjectManagerImpl = subjectManagerImpl;
	}

	public CourseTemplateBO getCourseTemplateBO() {
		return courseTemplateBO;
	}

	public void setCourseTemplateBO(CourseTemplateBO courseTemplateBO) {
		this.courseTemplateBO = courseTemplateBO;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		CourseTemplateBean ctBean = (CourseTemplateBean) command;
		try {
			String[] strStartDay = ctBean.getStartDay().split("/");
			GregorianCalendar dateStartDay = new GregorianCalendar(
					Integer.parseInt(strStartDay[2]),
					Integer.parseInt(strStartDay[1]),
					Integer.parseInt(strStartDay[0]));

			String[] strEndDay = ctBean.getStartEnd().split("/");
			GregorianCalendar dateEndDay = new GregorianCalendar(
					Integer.parseInt(strEndDay[2]),
					Integer.parseInt(strEndDay[1]),
					Integer.parseInt(strEndDay[0]));

			Set<Subject> subjects = new HashSet<Subject>();

			Set<Integer> in = ctBean.getInter();
			for (int i = 0; i < in.size(); i++) {
				System.out.println(ctBean.getInter().toArray()[i].toString()
						+ " ");
				Subject s = (Subject) HibernateUtil.getSession().get(
						Subject.class,
						Integer.parseInt(ctBean.getInter().toArray()[i]
								.toString()));

				System.out.println(" thanks " + s.getSubjectName());
				subjects.add(s);

			}
			CourseTemplate coursetemplate = new CourseTemplate();
			coursetemplate.setName(ctBean.getName());
			coursetemplate.setStartDay(dateStartDay);
			coursetemplate.setEndDay(dateEndDay);
			coursetemplate.setDescription(ctBean.getDescription());
			coursetemplate.setSubject(subjects);

			HibernateUtil.beginTransaction();
			this.courseTemplateBO.saveCourseTemplate(coursetemplate);
			HibernateUtil.commitTransaction();

			return new ModelAndView(new RedirectView(getSuccessView()));
		} catch (Exception e) {
			/*
			 * CourseTemplate ct =
			 * this.courseTemplateBO.getById(ctBean.getId()); return new
			 * ModelAndView(new RedirectView("Update.html"), "id", ct.getId());
			 */
		}
		return null;

	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String, String> javaSkill = new LinkedHashMap<String, String>();
		String id = request.getParameter("id");
		Iterator<Subject> iter = this.SubjectManagerImpl.getAllSubjects()
				.iterator();
		while (iter.hasNext()) {
			Subject ct = (Subject) iter.next();
			javaSkill.put(ct.getSubjectId() + "", ct.getSubjectName());

		}

		referenceData.put("javaSkillsList", javaSkill);
		return referenceData;
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		CourseTemplateBean cust = new CourseTemplateBean();

		return cust;

	}
}
