package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Person;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManagePersonUtil;

import controller.bean.PersonBean;

import business.ManagePerson;

@SuppressWarnings("deprecation")
public class EditPersonController extends SimpleFormController{
	private ManagePerson managePerson;

	public ManagePerson getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(ManagePerson managePerson) {
		this.managePerson = managePerson;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap=new ModelMap();
		String choosenId=request.getParameter("choosenId");
		Person choosenPerson=managePerson.getPerson(Long.parseLong(choosenId));
		PersonBean personBean=new PersonBean(choosenPerson.getPersonId(),choosenPerson.getFullName(), choosenPerson.getCellPhone(), choosenPerson.getEmail());
		modelMap.addAttribute("personBean",personBean);
		HttpSession session=request.getSession();
		session.setAttribute("choosenPersonId",Long.parseLong(choosenId));
		List<PersonBean> personList=ManagePersonUtil.getPersonList(request);
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	
	
}
