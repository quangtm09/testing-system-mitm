package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Person;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import controller.bean.AccountBean;

import business.ManageAccount;
import business.ManagePerson;

@SuppressWarnings("deprecation")
public class LoginController extends SimpleFormController{
	private ManageAccount manageAccount;
	private ManagePerson managePerson;
	
	public ManageAccount getManageAccount() {
		return manageAccount;
	}
	public void setManageAccount(ManageAccount manageAccount) {
		this.manageAccount = manageAccount;
	}

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
		AccountBean model=(AccountBean) command;
		Account account=manageAccount.getAccountByNameAndPass(model.getAccountName(), model.getPassword());
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("accountBean", new AccountBean());
		if(account==null){
			modelMap.addAttribute("message","Username or Password do not match,please try again");
			return new ModelAndView("login",modelMap);
		}
		modelMap.addAttribute("message","Login successfully!");
		Person person=managePerson.getPerson(account.getAccountId());
		HttpSession session=request.getSession(true);
		session.setAttribute("login", "true");
		session.setAttribute("account", account);
		session.setAttribute("person", person);
		session.setAttribute("username", person.getFullName());
		return new ModelAndView(new RedirectView(getSuccessView())); 
	}
	
	
}
