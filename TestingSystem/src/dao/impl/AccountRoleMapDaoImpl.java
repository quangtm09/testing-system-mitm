package dao.impl;

import model.AccountRoleMap;
import dao.AbstractHibernateDaoSupport;
import dao.AccountRoleMapDao;

public class AccountRoleMapDaoImpl extends
AbstractHibernateDaoSupport<AccountRoleMap, Integer> implements AccountRoleMapDao  {

	public AccountRoleMapDaoImpl() {
		super(AccountRoleMap.class);
	}

}
