package controller.CourseTemplate;

import java.text.SimpleDateFormat;
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

import org.hibernate.Session;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import util.HibernateUtil;

import Bean.CourseTemplateBean;
import business.CourseTemplateBO;
import business.impl.SubjectManagerImpl;

public class UpdateCourseTemplate extends SimpleFormController {

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
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		CourseTemplateBean ctBean = new CourseTemplateBean();

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			CourseTemplate ct = this.courseTemplateBO.getById(id);

			String strStartDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			if (ct.getStartDay() != null) {
				strStartDate = sdf.format(ct.getStartDay().getTime());
			}

			String strEndDate = null;
			if (ct.getEndDay() != null) {
				strEndDate = sdf.format(ct.getEndDay().getTime());
			}
			Set<Subject> subjects = new HashSet<Subject>();
			CourseTemplate iter = this.courseTemplateBO.getById(id);
			Set<Integer> inter = new HashSet<Integer>();

			Iterator<Subject> sj = iter.getSubject().iterator();
			while (sj.hasNext()) {
				Subject subject = (Subject) sj.next();
				subjects.add(subject);
				inter.add(subject.getSubjectId());
			}

			System.out.println(subjects);
			ctBean.setId(id);
			ctBean.setName(ct.getName());
			ctBean.setDescription(ct.getDescription());
			ctBean.setStartDay(strStartDate);
			ctBean.setStartEnd(strEndDate);
			ctBean.setSubject(subjects);

			ctBean.setInter(inter);
			return ctBean;
		}
		return null;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		// Session ss = HibernateUtil.getSession();
		// ss.beginTransaction();
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

			CourseTemplate ct1 = this.courseTemplateBO.getById(ctBean.getId());
			this.courseTemplateBO.deleteCourseTemplate(ct1);
			CourseTemplate ct = new CourseTemplate();
			Set<Subject> subjects = ct.getSubject();
			Set<Integer> in = ctBean.getInter();
			Iterator<Integer> iter = in.iterator();
			while (iter.hasNext()) {
				Integer in2 = iter.next();
				Subject s = (Subject) HibernateUtil.getSession().get(
						Subject.class, in2.intValue());
				System.out.println(" thanks " + s.getSubjectName());
				// s.getCourseTemplate().add(ct);
				// ct.getSubject().add(s);
				subjects.add(s);
			}
			ct.setName(ctBean.getName());
			ct.setDescription(ctBean.getDescription());
			ct.setStartDay(dateStartDay);
			ct.setEndDay(dateEndDay);
			ct.setSubject(subjects);

			HibernateUtil.beginTransaction();
			this.courseTemplateBO.saveCourseTemplate(ct);
			HibernateUtil.commitTransaction();
			return new ModelAndView(new RedirectView(
					"../CourseTemplate/View.html"), "id", ct.getId());
			// return new ModelAndView(new RedirectView("Update.html"));
		} catch (Exception e) {
			/*
			 * CourseTemplate ct =
			 * this.courseTemplateBO.getById(ctBean.getId()); return new
			 * ModelAndView(new RedirectView("Update.html"), "id", ct.getId());
			 */
		}
		return null;

	}

	@Override
	protected boolean isFormSubmission(HttpServletRequest request) {

		String name = request.getParameter("update");
		String subject = request.getParameter("_inter");
		if (name != null) {
			return true;
		}
		return false;
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
}
