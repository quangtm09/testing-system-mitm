package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import controller.bean.AccountBean;

import business.ManageAccount;
import business.ManageRole;

@SuppressWarnings("deprecation")
public class ShowAccountController extends SimpleFormController{
	private ManageAccount manageAccount;
	private ManageRole manageRole;
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
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelMap modelMap=new ModelMap();
		List<Role> rList=manageRole.getRoles();
		List<String> roleList=new ArrayList<String>();
		for(Role r:rList){
			String temp=new String(r.getRoleName());
			roleList.add(temp);
		}
		List<AccountBean> accountList=new ArrayList<AccountBean>();
		List<Account> aList=manageAccount.getAccounts();
		for(Account a:aList){
			AccountBean accountBean=new AccountBean(a.getAccountId(), a.getAccountName(), a.getPassword(), a.getRole().getRoleName(), a.getPerson().getPersonId());
			accountList.add(accountBean);
		}
		modelMap.addAttribute("accountBean",new AccountBean());
		modelMap.addAttribute("roleList",roleList);
		modelMap.addAttribute("accountList",accountList);
		HttpSession session=request.getSession(true);
		session.setAttribute("accountList", accountList);
		session.setAttribute("roleList", roleList);
		return new ModelAndView("account",modelMap);
	}

	
	
}
