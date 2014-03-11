package util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.bean.AccountBean;

public class ManageAccountUtil {
	
	@SuppressWarnings("unchecked")
	public static List<String> getRoleList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return (List<String>) session.getAttribute("roleList");
	}
	
	@SuppressWarnings("unchecked")
	public static List<AccountBean> getAccountList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return (List<AccountBean>) session.getAttribute("accountList");
	}
}
