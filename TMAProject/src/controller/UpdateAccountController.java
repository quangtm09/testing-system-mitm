package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;
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
public class UpdateAccountController extends SimpleFormController{
	private ManageAccount manageAccount;
	private ManageRole manageRole;
	private ManagePerson managePerson;
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
		AccountBean model=(AccountBean)command;
		Account temp=manageAccount.getAccountByName(model.getAccountName());
		temp.setAccountName(model.getAccountName());
		temp.setPassword(model.getPassword());
		Role role=manageRole.findRoleByName(model.getRoleName());
		temp.setRole(role);
		boolean result=manageAccount.updateAccount(temp);
		List<AccountBean> accountList=ManageAccountUtil.getAccountList(request);
		if(result){
			modelMap.addAttribute("message","Account updated successfully!");
			for(AccountBean a:accountList){
				if(a.getAccountId()==temp.getAccountId()){
					a.setAccountName(model.getAccountName());
					a.setPassword(model.getPassword());
					a.setPersonId(model.getPersonId());
					a.setRoleName(model.getRoleName());
					break;
				}
			}
		}else{
			modelMap.addAttribute("message","An error has occured,cannot update account");
		}
		modelMap.addAttribute("accountBean",new AccountBean());
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",ManageAccountUtil.getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	
	
	
}
