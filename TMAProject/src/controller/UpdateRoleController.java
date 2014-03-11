package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Role;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import util.ManageRoleUtil;

import controller.bean.RoleBean;

import business.ManageRole;

@SuppressWarnings("deprecation")
public class UpdateRoleController extends SimpleFormController{
	private ManageRole manageRole;

	public ManageRole getManageRole() {
		return manageRole;
	}
	public void setManageRole(ManageRole manageRole) {
		this.manageRole = manageRole;
	}
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ModelMap modelMap=new ModelMap();
		modelMap.addAttribute("roleBean",new RoleBean());
		List<RoleBean> roleList=ManageRoleUtil.getRoleList(request);
		RoleBean model=(RoleBean)command;
		if(model.getRoleName()==""){
			modelMap.addAttribute("rList",roleList);
			modelMap.addAttribute("message","You must choose a role!");
			return new ModelAndView("role",modelMap);
		}
		Role role=manageRole.findRoleByName(model.getRoleName());
		role.getPermissionLists().get(0).setPermissionValue(Boolean.parseBoolean(model.getLoginPermission()));
		role.getPermissionLists().get(1).setPermissionValue(Boolean.parseBoolean(model.getViewPermission()));
		role.getPermissionLists().get(2).setPermissionValue(Boolean.parseBoolean(model.getEditPermission()));
		role.getPermissionLists().get(3).setPermissionValue(Boolean.parseBoolean(model.getManagePermission()));
		boolean result=manageRole.updateRole(role);
		
		if(result){
			modelMap.addAttribute("message","Role updated successfully!");
			for(RoleBean r:roleList){
				if(r.getRoleId()==role.getRoleId()){
					r.setLoginPermission(model.getLoginPermission());
					r.setEditPermission(model.getEditPermission());
					r.setViewPermission(model.getViewPermission());
					r.setManagePermission(model.getManagePermission());
					break;
				}
			}
		}else{
			modelMap.addAttribute("message","An error has occured,cannot update role");
		}
		modelMap.addAttribute("rList",roleList);
		HttpSession session=request.getSession();
		session.setAttribute("rList", roleList);
		return new ModelAndView("role",modelMap);
	}
	
	
}
