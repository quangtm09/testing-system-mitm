package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
import model.Person;
import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManageAccountUtil;

import controller.bean.AccountBean;

import business.ManageAccount;
import business.ManagePerson;
import business.ManageRole;

@SuppressWarnings("deprecation")
public class AddAccountController extends SimpleFormController {
	private ManagePerson managePerson;
	private ManageAccount manageAccount;
	private ManageRole manageRole;
	public ManagePerson getManagePerson() {
		return managePerson;
	}
	public void setManagePerson(ManagePerson managePerson) {
		this.managePerson = managePerson;
	}
	public ManageAccount getManageAccount() {
		return manageAccount;
	}
	public void setManageAccount(ManageAccount manageAccount) {
		this.manageAccount = manageAccount;
	}
	public ManageRole getManageRole() {
		return manageRole;
	}
	public void setManageRole(ManageRole manageRole) {
		this.manageRole = manageRole;
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AccountBean model=(AccountBean)command;
		ModelMap modelMap=new ModelMap();
		Role role=manageRole.findRoleByName(model.getRoleName());
		Person person=managePerson.getPerson(model.getPersonId());
		Account account=new Account(model.getAccountName(), model.getPassword());
		account.setRole(role);
		account.setPerson(person);
		boolean result=manageAccount.insertAccount(account);
		person.setAccount(account);
		managePerson.updatePerson(person);
		List<AccountBean> accountList=ManageAccountUtil.getAccountList(request);
		Account temp_account=manageAccount.getAccountByName(model.getAccountName());
		model.setAccountId(temp_account.getAccountId());
		if(result){
			accountList.add(model);
			modelMap.addAttribute("message","Account added successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot add account");
		}
		modelMap.addAttribute("accountBean",new AccountBean());
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",ManageAccountUtil.getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	
	
}
