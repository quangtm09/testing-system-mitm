package dao.interfaces;

import java.util.List;

import model.Account;

public interface AccountDao {
	/*
	 * find Account by AccountID
	 */
	List<Account> findAccountByAccID(String accID);
	
	/*
	 * get Account with Role
	 */
	
	List<Account> findAccountsByRoleID (String roleID);
	
	/*
	 *  delete Account by Account ID
	 */
	public void deleteAccount(String accountID);
	
	/*
	 * insert Account By AccountID
	 */
	public void insertAccount(String accountID, String userID, String accountPwd);
	
	/*
	 * change password
	 */
	public void changePassword(String accountID, String accountPwd);
	
	/*
	 * Authenticate Account
	 */
	public boolean checkAccount(String accountID, String accountPwd);
	
	/*
	 * find Account By AccountID and Password
	 */
	List<Account> findAccountByAccIDandPwd(String accountID, String accountPwd);
}
