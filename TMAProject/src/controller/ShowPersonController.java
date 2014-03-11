package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Person;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import controller.bean.PersonBean;

@SuppressWarnings("deprecation")
public class ShowPersonController extends SimpleFormController{

	private business.ManagePerson managePerson;
	
	public business.ManagePerson getManagePerson() {
		return managePerson;
	}

	public void setManagePerson(business.ManagePerson managePerson) {
		this.managePerson = managePerson;
	}

	 
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Person> list=managePerson.getPersons();
		List<PersonBean> personList=new ArrayList<PersonBean>();
		for(Person p:list){
			PersonBean temp=new PersonBean(p.getPersonId(),p.getFullName(), p.getCellPhone(), p.getEmail());
			personList.add(temp);
		}
		HttpSession session=request.getSession();
		session.setAttribute("personList", personList);
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("personBean",new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}

}
