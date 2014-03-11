package test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Person;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import controller.bean.PersonBean;

import business.ManagePerson;

public class PersonController extends MultiActionController{
	private ManagePerson managePerson;

	public ManagePerson getManagePerson() {
		return managePerson;
	}
	
	public void setManagePerson(ManagePerson managePerson) {
		this.managePerson = managePerson;
	}

	public ModelAndView managePerson(HttpServletRequest request,HttpServletResponse response){
		List<Person> list=managePerson.getPersons();
		List<PersonBean> personList=new ArrayList<PersonBean>();
		for(Person p:list){
			PersonBean temp=new PersonBean(p.getPersonId(),p.getFullName(), p.getCellPhone(), p.getEmail());
			personList.add(temp);
		}
		HttpSession session=request.getSession();
		session.setAttribute("personList", personList);
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("person",new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	//Add new person
	public ModelAndView addPerson(HttpServletRequest request,HttpServletResponse response,PersonBean person){
		Person temp_Person=new Person(person.getFullName(), person.getCellPhone(), person.getEmail());
		List<PersonBean> personList=getPersonList(request);
		boolean result=managePerson.insertPerson(temp_Person);
		Person p=managePerson.getPersonByEmail(person.getEmail());
		person.setPersonId(p.getPersonId());
		ModelMap modelMap=new ModelMap();
		if(result){
			personList.add(person);
			modelMap.addAttribute("message","Person added successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot add person");
		}
		HttpSession session=request.getSession();
		session.setAttribute("personList", personList);
		modelMap.addAttribute("person",new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	//remove Person
	public ModelAndView removePerson(HttpServletRequest request,HttpServletResponse response){
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("person",new PersonBean());
		String[] choosenList=request.getParameterValues("check");
		boolean result=false;
		List<PersonBean> personList=getPersonList(request);
		for(String o:choosenList){
			Person temp_Person=managePerson.getPerson(Long.parseLong(o));
			result=managePerson.deletePerson(temp_Person);
			if(result){
				PersonBean temp_PersonBean=new PersonBean();
				for(PersonBean p:personList){
					if(p.getPersonId()==temp_Person.getPersonId()){
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
	
	public ModelAndView editPerson(HttpServletRequest request,HttpServletResponse response){
		ModelMap modelMap=new ModelMap();
		String choosenId=request.getParameter("choosenId");
		Person choosenPerson=managePerson.getPerson(Long.parseLong(choosenId));
		PersonBean temp_person=new PersonBean(choosenPerson.getPersonId(),choosenPerson.getFullName(), choosenPerson.getCellPhone(), choosenPerson.getEmail());
		modelMap.addAttribute("person",temp_person);
		HttpSession session=request.getSession();
		session.setAttribute("choosenId",Long.parseLong(choosenId));
		List<PersonBean> personList=getPersonList(request);
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	public ModelAndView updatePerson(HttpServletRequest request,HttpServletResponse response,Person person){
		ModelMap modelMap=new ModelMap();
		HttpSession session=request.getSession();
		long personId=(Long) session.getAttribute("choosenId");
		Person choosenPerson=managePerson.getPerson(personId);
		List<PersonBean> personList=getPersonList(request);
		choosenPerson.setFullName(person.getFullName());
		choosenPerson.setCellPhone(person.getCellPhone());
		choosenPerson.setEmail(person.getEmail());
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
		modelMap.put("person", new PersonBean());
		modelMap.addAttribute("personList", personList);
		return new ModelAndView("person",modelMap);
	}
	
	@SuppressWarnings("unchecked")
	private List<PersonBean> getPersonList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return  (List<PersonBean>) session.getAttribute("personList");
	}
	
}
