package dao.impl;

import java.util.List;

import model.Role;
import dao.AbstractHibernateDaoSupport;
import dao.RoleDAO;

public class RoleDAOImpl extends AbstractHibernateDaoSupport<Role, Long> implements RoleDAO{

	protected RoleDAOImpl() {
		super(Role.class);
	}

	@Override
	public Role getRolebyId(long roleId) {
		return findById(roleId);
	}

	@Override
	public Role getRoleByName(String roleName) {
		List<Role> result=findByProperty(ROLE_NAME, roleName);
		if(result.size()==0){
			return null;
		}else{
			return result.get(0);
		}
	}

	@Override
	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		return update(role);
	}

}
