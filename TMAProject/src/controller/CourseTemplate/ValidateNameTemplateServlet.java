package controller.CourseTemplate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import model.CourseTemplate;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import business.impl.CourseTemplateBOimpl;

import java.util.*;

public class ValidateNameTemplateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		CourseTemplateBOimpl ctv = (CourseTemplateBOimpl) springContext
				.getBean("courseTemplateBOimpl");

		String targetUserName = request.getParameter("user");

		if (ctv.CheckName(targetUserName)) {
			System.out.println("True name");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<valid>true</valid>");
		} else {
			System.out.println("True name");
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<valid>false</valid>");
		}
		/*
		 * if(targetPassword!=null && ctv.CheckUser(targetPassword.trim())){
		 * response.setContentType("text/xml");
		 * response.setHeader("Cache-Control", "no-cache");
		 * response.getWriter().write("<valid>true</valid>"); }else {
		 * response.setContentType("text/xml");
		 * response.setHeader("Cache-Control", "no-cache");
		 * response.getWriter().write("<valid>false</valid>"); }
		 */
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
		CourseTemplateBOimpl ctv = (CourseTemplateBOimpl) springContext
				.getBean("courseTemplateBOimpl");

		String targetUserName = request.getParameter("user");

		if (ctv.CheckName(targetUserName)) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<valid>true</valid>");
		} else {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<valid>false</valid>");
		}
	}

}
