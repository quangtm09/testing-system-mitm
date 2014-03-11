package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import controller.bean.RoleBean;

import business.ManageRole;

@SuppressWarnings("deprecation")
public class ShowRoleController extends SimpleFormController{
	private ManageRole manageRole;

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
		List<RoleBean> roleList=new ArrayList<RoleBean>();
		for(Role r:rList){
			RoleBean roleBean=new RoleBean(r.getRoleId(), r.getRoleName(), r.getPermissionLists().get(0).getPermissionValue().toString(), r.getPermissionLists().get(1).getPermissionValue().toString(), r.getPermissionLists().get(2).getPermissionValue().toString(), r.getPermissionLists().get(2).getPermissionValue().toString());
			roleList.add(roleBean);
		}
		HttpSession session=request.getSession(true);
		session.setAttribute("rList", roleList);
		modelMap.addAttribute("rList",roleList);
		modelMap.addAttribute("roleBean",new RoleBean());
		return new ModelAndView("role",modelMap);
	}
	
}
