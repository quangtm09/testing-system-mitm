package dao.impl;

import java.util.List;

import model.Role;

import model.AccountRoleMap;
import model.RolePermissionMap;
import dao.AbstractHibernateDaoSupport;
import dao.AccountRoleMapDao;
import dao.RolePermissionMapDao;

public class AccountRoleMapDaoImpl extends
AbstractHibernateDaoSupport<AccountRoleMap, Integer> implements AccountRoleMapDao  {

	public AccountRoleMapDaoImpl() {
		super(AccountRoleMap.class);
	}

	/* (non-Javadoc)
	 * @see dao.AccountRoleMapDao#searchAccountByRoleId(javax.management.relation.Role)
	 */
	@Override
	public List<AccountRoleMap> searchAccountByRoleId(Role role) {
		// TODO Auto-generated method stub
		final List<AccountRoleMap> results = this.findByProperty(AccountRoleMapDao.ROLE, role);
		return results;
	}

}
