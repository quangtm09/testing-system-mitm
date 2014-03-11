package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Account;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManageAccountUtil;

import controller.bean.AccountBean;

import business.ManageAccount;

@SuppressWarnings("deprecation")
public class RemoveAccountController extends SimpleFormController{
	private ManageAccount manageAccount;

	public ManageAccount getManageAccount() {
		return manageAccount;
	}

	public void setManageAccount(ManageAccount manageAccount) {
		this.manageAccount = manageAccount;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ModelMap modelMap=new ModelMap();
		List<AccountBean> accountList=ManageAccountUtil.getAccountList(request);
		String[] choosenList=request.getParameterValues("check");
		boolean result=false;
		for(String o:choosenList){
			Account account=manageAccount.getAccount(Long.parseLong(o));
			result=manageAccount.deleteAccount(account);
			if(result){
				AccountBean accountBean=new AccountBean();
				for(AccountBean a:accountList){
					if(a.getAccountId()==account.getAccountId()){
						accountBean=a;
						break;
					}
				}
				accountList.remove(accountBean);
			}
		}
		if(result){
			modelMap.addAttribute("message","Account deleted successfully!");
		}else{
			modelMap.addAttribute("message","An error has occured,cannot delete account");
		}
		modelMap.addAttribute("accountBean",new AccountBean());
		modelMap.addAttribute("roleList",ManageAccountUtil.getRoleList(request));
		modelMap.addAttribute("accountList",accountList);
		return new ModelAndView("account",modelMap);
	}
	
	
}
