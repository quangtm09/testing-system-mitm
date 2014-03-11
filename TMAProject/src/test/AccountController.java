package test;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Person;
import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import controller.bean.AccountBean;
import controller.bean.PersonBean;

import business.ManageAccount;
import business.ManagePerson;
import business.ManageRole;

@SessionAttributes({"roleList"})
public class AccountController extends MultiActionController{
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

	public ModelAndView manageAccount(HttpServletRequest request,HttpServletResponse response){
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
			AccountBean temp=new AccountBean(a.getAccountId(), a.getAccountName(), a.getPassword(), a.getRole().getRoleName(), a.getPerson().getPersonId());
			accountList.add(temp);
		}
		modelMap.addAttribute("account",new AccountBean());
		modelMap.addAttribute("roleList",roleList);
		modelMap.addAttribute("accountList",accountList);
		HttpSession session=request.getSession(true);
		session.setAttribute("accountList", accountList);
		session.setAttribute("roleList", roleList);
		return new ModelAndView("account",modelMap);
	}

	public ModelAndView addAccount(HttpServletRequest request,HttpServletResponse response,AccountBean accountBean){
		ModelMap modelMap=new ModelMap();
		Role role=manageRole.findRoleByName(accountBean.getRoleName());
		Person person=managePerson.getPerson(accountBean.getPersonId());
		Account account=new Account(accountBean.getAccountName(), accountBean.getPassword());
		account.setRole(role);
		account.setPerson(person);
		boolean result=manageAccount.insertAccount(account);
		person.setAccount(account);
		managePerson.updatePerson(person);
		List<AccountBean> accountList=getAccountList(request);
		Account temp_account=manageAccount.getAccountByName(accountBean.getAccountName());
		accountBean.setAccountId(temp_account.getAccountId());
		if(result){
			accountList.add(accountBean);
			modelMap.addAttribute("message","Account added successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot add account");
		}
		modelMap.addAttribute("account",new AccountBean());
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	
	public ModelAndView removeAccount(HttpServletRequest request,HttpServletResponse response){
		ModelMap modelMap=new ModelMap();
		List<AccountBean> accountList=getAccountList(request);
		String[] choosenList=request.getParameterValues("check");
		boolean result=false;
		for(String o:choosenList){
			Account temp_Account=manageAccount.getAccount(Long.parseLong(o));
			result=manageAccount.deleteAccount(temp_Account);
			if(result){
				AccountBean temp_AccountBean=new AccountBean();
				for(AccountBean a:accountList){
					if(a.getAccountId()==temp_Account.getAccountId()){
						temp_AccountBean=a;
						break;
					}
				}
				accountList.remove(temp_AccountBean);
			}
		}
		if(result){
			modelMap.addAttribute("message","Account deleted successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot delete account");
		}
		modelMap.addAttribute("account",new AccountBean());
		modelMap.addAttribute("roleList",getRoleList(request));
		modelMap.addAttribute("accountList",accountList);
		return new ModelAndView("account",modelMap);
	}
	
	public ModelAndView editAccount(HttpServletRequest request,HttpServletResponse response){
		ModelMap modelMap=new ModelMap();
		System.out.println(request.getQueryString());
		String choosenId=request.getParameter("choosenId");
		Account temp=manageAccount.getAccount(Long.parseLong(choosenId));
		AccountBean accountBean=new AccountBean(temp.getAccountId(), temp.getAccountName(), temp.getPassword(), temp.getRole().getRoleName(), temp.getPerson().getPersonId());
		List<AccountBean> accountList=getAccountList(request);
		modelMap.addAttribute("account",accountBean);
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	public ModelAndView updateAccount(HttpServletRequest request,HttpServletResponse response,AccountBean accountBean){
		ModelMap modelMap=new ModelMap();
		Account temp=manageAccount.getAccountByName(accountBean.getAccountName());
		temp.setAccountName(accountBean.getAccountName());
		temp.setPassword(accountBean.getPassword());
		Role role=manageRole.findRoleByName(accountBean.getRoleName());
		temp.setRole(role);
		boolean result=manageAccount.updateAccount(temp);
		List<AccountBean> accountList=getAccountList(request);
		if(result){
			modelMap.addAttribute("message","Account updated successfully!");
			for(AccountBean a:accountList){
				if(a.getAccountId()==temp.getAccountId()){
					a.setAccountName(accountBean.getAccountName());
					a.setPassword(accountBean.getPassword());
					a.setPersonId(accountBean.getPersonId());
					a.setRoleName(accountBean.getRoleName());
					break;
				}
			}
		}else{
			modelMap.addAttribute("message","An error has occured,cannot update account");
		}
		modelMap.addAttribute("account",new AccountBean());
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	@SuppressWarnings("unchecked")
	private List<AccountBean> getAccountList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return (List<AccountBean>) session.getAttribute("accountList");
	}
	
	@SuppressWarnings("unchecked")
	private List<String> getRoleList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return (List<String>) session.getAttribute("roleList");
	}

}
