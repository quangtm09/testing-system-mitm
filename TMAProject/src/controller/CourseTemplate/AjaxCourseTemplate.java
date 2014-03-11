package controller.CourseTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CourseTemplate;
import net.sf.json.JSONArray;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import business.CourseTemplateBO;

public class AjaxCourseTemplate implements Controller {
	private CourseTemplateBO courseTemplateBO;

	public AjaxCourseTemplate() {
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		String name = request.getParameter("name");
		String term = request.getParameter("term");
		if (term == null) {
			term = "";
		}

		if (name != null) {
			int k = 0;
			JSONArray arrayObj = new JSONArray();
			Set<CourseTemplate> ct = (Set<CourseTemplate>) courseTemplateBO
					.getByName(term);
			// List<String> list = new ArrayList<String>();
			System.out.println(ct.size() + " ");

			Iterator<CourseTemplate> iter = ct.iterator();
			while (iter.hasNext() && k < 7) {
				CourseTemplate ct1 = iter.next();
				arrayObj.add(ct1.getName());
				System.out.println(ct1.getName());
				k++;
			}

			response.getWriter().print(arrayObj);
		}
		return null;
	}

	public CourseTemplateBO getCourseTemplateBO() {
		return courseTemplateBO;
	}

	public void setCourseTemplateBO(CourseTemplateBO courseTemplateBO) {
		this.courseTemplateBO = courseTemplateBO;
	}

}