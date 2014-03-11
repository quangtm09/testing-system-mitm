package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManageRoleUtil;

import controller.bean.RoleBean;

import business.ManageRole;

@SuppressWarnings("deprecation")
public class EditRoleController extends SimpleFormController{
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
		String choosenId=request.getParameter("choosenId");
		Role role=manageRole.findRoleById(Long.parseLong(choosenId));
		RoleBean roleBean=new RoleBean(role.getRoleId(), role.getRoleName(), role.getPermissionLists().get(0).getPermissionValue().toString(), role.getPermissionLists().get(1).getPermissionValue().toString(), role.getPermissionLists().get(2).getPermissionValue().toString(), role.getPermissionLists().get(3).getPermissionValue().toString());
		modelMap.addAttribute("roleBean",roleBean);
		modelMap.addAttribute("rList",ManageRoleUtil.getRoleList(request));
		return new ModelAndView("role",modelMap);
	}
	
	
	
	
}
