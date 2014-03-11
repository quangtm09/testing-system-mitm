package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManageAccountUtil;

import business.ManageAccount;

import controller.bean.AccountBean;

@SuppressWarnings("deprecation")
public class EditAccountController extends SimpleFormController{
	private ManageAccount manageAccount;
	public ManageAccount getManageAccount() {
		return manageAccount;
	}
	public void setManageAccount(ManageAccount manageAccount) {
		this.manageAccount = manageAccount;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ModelMap modelMap=new ModelMap();
		String choosenId=request.getParameter("choosenId");
		Account account=manageAccount.getAccount(Long.parseLong(choosenId));
		AccountBean accountBean=new AccountBean(account.getAccountId(), account.getAccountName(), account.getPassword(), account.getRole().getRoleName(), account.getPerson().getPersonId());
		List<AccountBean> accountList=ManageAccountUtil.getAccountList(request);
		modelMap.addAttribute("accountBean",accountBean);
		modelMap.addAttribute("accountList",accountList);
		modelMap.addAttribute("roleList",ManageAccountUtil.getRoleList(request));
		return new ModelAndView("account",modelMap);
	}
	
	

}
