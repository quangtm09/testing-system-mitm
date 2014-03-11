package util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.bean.PersonBean;

public class ManagePersonUtil {
	@SuppressWarnings("unchecked")
	public static List<PersonBean> getPersonList(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		return  (List<PersonBean>) session.getAttribute("personList");
	}
		
}
