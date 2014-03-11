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

import controller.bean.PersonBean;

import business.ManagePerson;

@SuppressWarnings("deprecation")
public class RemovePersonController extends SimpleFormController{
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
		modelMap.addAttribute("personBean",new PersonBean());
		String[] choosenList=request.getParameterValues("check");
		boolean result=false;
		List<PersonBean> personList=ManagePersonUtil.getPersonList(request);
		for(String o:choosenList){
			Person person=managePerson.getPerson(Long.parseLong(o));
			result=managePerson.deletePerson(person);
			if(result){
				PersonBean temp_PersonBean=new PersonBean();
				for(PersonBean p:personList){
					if(p.getPersonId()==person.getPersonId()){
						temp_PersonBean=p;
						break;
					}
				}
				personList.remove(temp_PersonBean);
			}
		}
		if(result){
			modelMap.addAttribute("message","Person deleted successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot delete person");
		}
		HttpSession session=request.getSession();
		session.setAttribute("personList", personList);
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	
	
}
