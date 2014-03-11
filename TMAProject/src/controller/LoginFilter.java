package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Person;
import model.Role;
import model.Permission;

public class LoginFilter implements Filter  {

	@SuppressWarnings("rawtypes")
	
	@Override
	public void destroy() {
		System.out.println("Destroy");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)arg0).getSession(true);
		Person person = (Person)session.getAttribute("person");
		String reqUrl = ((HttpServletRequest)arg0).getRequestURL().toString();
		if(person==null) {
			if (reqUrl.contains("CourseTemplate")||reqUrl.contains("show")||reqUrl.contains("SubjectManagement")){			
				
					((HttpServletResponse)arg1).sendRedirect("/ProjectTMA01/login.html");
					return;
			}
			
		}
			
		if(person!=null){		
		
		
		}
		arg2.doFilter(arg0, arg1);		
	
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Init");
	}

}
