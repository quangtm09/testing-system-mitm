package controller.CourseTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseTemplate;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import util.HibernateUtil;

import Bean.CourseTemplateBean;
import Bean.SearchBean;
import business.CourseTemplateBO;

public class SearchCourseTemplate implements Controller {

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

		String strSearch = request.getParameter("keyword");

		Map<String, Object> model = new HashMap<String, Object>();
		int pageNo = 0;
		if (request.getParameter("page") == null) {
			pageNo = 1;
			strSearch = " ";
		} else {
			pageNo = Integer.parseInt(request.getParameter("page"));
		}
		SearchBean search = new SearchBean();
		search.setSearchname(strSearch);

		Set<Integer> pages = courseTemplateBO.CountTemplatePages(strSearch);
		int total = courseTemplateBO.CountTemplate(strSearch);
		model.put("Totalpages", total);
		model.put("pages", pages);
		model.put("pageNo", pageNo);
		model.put("keyword1", search);
		HibernateUtil.beginTransaction();
		Set<CourseTemplate> cts = new HashSet<CourseTemplate>();
		if (strSearch == null || strSearch == "") {
			cts = courseTemplateBO.search(pageNo);
		} else {
			cts = courseTemplateBO.Search(strSearch, pageNo);
		}
		HibernateUtil.commitTransaction();
		List<CourseTemplateBean> Beans = new ArrayList<CourseTemplateBean>();
		Iterator<CourseTemplate> iter = cts.iterator();
		CourseTemplateBean Bean = new CourseTemplateBean();
		while (iter.hasNext()) {

			CourseTemplate ct = (CourseTemplate) iter.next();

			String strStartDate = null;
			String strEndDate = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			if (ct.getStartDay() != null) {
				strStartDate = sdf.format(ct.getStartDay().getTime());
			}
			if (ct.getEndDay() != null) {
				strEndDate = sdf.format(ct.getEndDay().getTime());
			}
			Bean.setName(ct.getName());
			Bean.setDescription(ct.getDescription());
			Bean.setId(ct.getId());
			Bean.setStartDay(strStartDate);
			Beans.add(new CourseTemplateBean(ct.getId(), ct.getName(),
					strStartDate, strEndDate, ct.getDescription()));

		}
		model.put("Beans", Beans);

		return new ModelAndView("CourseTemplate/Search", "model", model);

	}

}
