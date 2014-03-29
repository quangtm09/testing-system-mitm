package dao;

import model.Account;

public interface AccountDao extends Dao<Account, String>{
	public boolean addAccount(Account account);
	public Account getAccountById(String accountId);
	public boolean deleteAccount(Account account);
}
