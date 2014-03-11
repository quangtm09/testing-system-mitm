package business;

import java.util.List;

import model.Account;

public interface ManageAccount {
	public List<Account> getAccounts();
	public Account getAccount(long accountId);
	public boolean deleteAccount(Account account);
	public boolean insertAccount(Account account);
	public boolean updateAccount(Account account);
	public Account getAccountByName(String accountName);
	public Account getAccountByNameAndPass(String accountName,String password);
}
