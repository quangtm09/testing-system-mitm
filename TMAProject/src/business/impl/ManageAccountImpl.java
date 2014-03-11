package business.impl;

import java.util.List;

import dao.AccountDAO;

import model.Account;
import business.ManageAccount;

public class ManageAccountImpl implements ManageAccount{
	private AccountDAO accountDAO;
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public List<Account> getAccounts() {
		return accountDAO.findAll();
	}

	@Override
	public Account getAccount(long accountId) {
		return accountDAO.getAccountbyId(accountId);
	}

	@Override
	public boolean deleteAccount(Account account) {
		return accountDAO.delete(account);
	}

	@Override
	public boolean insertAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.save(account);
	}

	@Override
	public boolean updateAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDAO.update(account);
	}

	@Override
	public Account getAccountByName(String accountName) {
		// TODO Auto-generated method stub
		return accountDAO.getAccountbyName(accountName);
	}

	@Override
	public Account getAccountByNameAndPass(String accountName, String password) {
		return accountDAO.getAccountByNameAndPass(accountName, password);
	}

}
