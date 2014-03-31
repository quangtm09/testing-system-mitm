package dao;

import java.util.List;

import model.Account;

public interface AccountDao extends Dao<Account, String>{
	public final static String ACCOUNT_ID = "accId";


	public boolean addAccount(Account account);
	public Account getAccountById(String accountId);
	public boolean deleteAccount(Account account);
	public List<Account> searchAccount(String accId, String fname, String lname, String email);
}
