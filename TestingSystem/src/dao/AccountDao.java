package dao;

import model.Account;

public interface AccountDao extends Dao<Account, String>{
	public void addAccount(Account account);
	public Account getAccountById(String accountId);
	public void deleteAccount(Account account);
}