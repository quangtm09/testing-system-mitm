package dao;

import java.util.List;

import model.Role;

import model.AccountRoleMap;

public interface AccountRoleMapDao extends Dao<AccountRoleMap, Integer>{
	public static final String ROLE = "role";
	public List<AccountRoleMap> searchAccountByRoleId(Role role);
}
