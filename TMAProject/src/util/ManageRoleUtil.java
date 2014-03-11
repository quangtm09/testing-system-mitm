package util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.bean.RoleBean;

public class ManageRoleUtil {
	public static List<RoleBean>getRoleList(HttpServletRequest request){
		HttpSession ss=request.getSession(true);
		return (List<RoleBean>) ss.getAttribute("rList");
	}
}
