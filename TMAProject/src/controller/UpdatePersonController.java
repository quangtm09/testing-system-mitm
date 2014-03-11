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
public class UpdatePersonController extends SimpleFormController{
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
		ModelMap modelMap=new ModelMap();
		PersonBean model=(PersonBean) command;
		HttpSession session=request.getSession();
		long personId=(Long) session.getAttribute("choosenPersonId");
		Person choosenPerson=managePerson.getPerson(personId);
		List<PersonBean> personList=ManagePersonUtil.getPersonList(request);
		choosenPerson.setFullName(model.getFullName());
		choosenPerson.setCellPhone(model.getCellPhone());
		choosenPerson.setEmail(model.getEmail());
		boolean result=managePerson.updatePerson(choosenPerson);
		if(result){
			modelMap.addAttribute("message","Person updated successfully!");
			for(PersonBean p:personList){
				if(p.getPersonId()==personId){
					p.setPersonId(choosenPerson.getPersonId());
					p.setFullName(choosenPerson.getFullName());
					p.setCellPhone(choosenPerson.getCellPhone());
					p.setEmail(choosenPerson.getEmail());
					break;
				}
			}
		}else{
			modelMap.addAttribute("message","An error has occured,cannot update person");
		}
		session.setAttribute("personList", personList);
		modelMap.put("personBean", new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
}
