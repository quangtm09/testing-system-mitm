package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Person;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManagePersonUtil;

import business.ManagePerson;

import controller.bean.PersonBean;

@SuppressWarnings("deprecation")
public class AddPersonController extends SimpleFormController{
	
	private ManagePerson managePerson;
	
	public ManagePerson getManagePerson() {
		return managePerson;
	}

	public void setManagePerson(ManagePerson managePerson) {
		this.managePerson = managePerson;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		PersonBean model=(PersonBean) command;
		Person person=new Person(model.getFullName(), model.getCellPhone(), model.getEmail());
		List<PersonBean> personList=ManagePersonUtil.getPersonList(request);
		boolean result=managePerson.insertPerson(person);
		Person p=managePerson.getPersonByEmail(model.getEmail());
		model.setPersonId(p.getPersonId());
		ModelMap modelMap=new ModelMap();
		if(result){
			personList.add(model);
			modelMap.addAttribute("message","Person added successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot add person");
		}
		HttpSession session=request.getSession();
		session.setAttribute("personList", personList);
		modelMap.addAttribute("personBean",new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView(getSuccessView(),modelMap);
	}

}
