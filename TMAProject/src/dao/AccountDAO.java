package dao;


import model.Account;

public interface AccountDAO extends Dao<Account, Long>{
	public String ACCOUNT_NAME="accountName";
	public String PASSWORD="password";
	public Account getAccountbyId(long accountId);
	public Account getAccountbyName(String accountName);
	public Account getAccountByNameAndPass(String accountName, String password);
}
