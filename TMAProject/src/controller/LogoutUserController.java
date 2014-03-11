package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;


public class LogoutUserController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("login", null);
		session.setAttribute("account", null);
		session.setAttribute("person", null);
		session.setAttribute("username", null);
		
		return new ModelAndView(new RedirectView("/ProjectTMA01/login.html"));
	}

}
