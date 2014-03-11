package controller.CourseTemplate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import model.CourseTemplate;
import model.Subject;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import business.impl.CourseTemplateBOimpl;
import java.util.*;

public class AjaxTooltips extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		CourseTemplateBOimpl ctv = (CourseTemplateBOimpl) springContext
				.getBean("courseTemplateBOimpl");

		//String targetUserName = request.getParameter("id");

		String targetUserName = request.getParameter("id");
		CourseTemplate ct = ctv.getById(Integer.parseInt(targetUserName));
		if (ct.getId() !=0) {
			System.out.println("True name");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			String str = "<div class=\"valid\"><strong style=\"text-align: center;\"> Lists Subject</strong> </br>";
			Iterator<Subject> subject = ct.getSubject().iterator();
			int number = 0 ; 
			while(subject.hasNext())
			{
				number ++;
				Subject s = subject.next();
				str += number + ". "+ s.getSubjectName() + "</br>";
			}
				
			response.getWriter().write(str+"</div>");
		} else {
			System.out.println("false name");
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<div class=\"valid\">not subject</div>");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		CourseTemplateBOimpl ctv = (CourseTemplateBOimpl) springContext
				.getBean("courseTemplateBOimpl");

		String targetUserName = request.getParameter("id");

		if (ctv.CheckName(targetUserName)) {
			System.out.println("True name");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			response.getWriter().write("<div id=\"valid\">true</div>");
		} else {
			System.out.println("false name");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(
					"<valid class=\"personPopupResult\">false</valid>");
		}
	}

}
