package dao.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import model.Account;
import dao.AbstractHibernateDaoSupport;
import dao.AccountDAO;

public class AccountDAOImpl extends AbstractHibernateDaoSupport<Account, Long> implements AccountDAO{
	
	protected AccountDAOImpl() {
		super(Account.class);
	}

	@Override
	public Account getAccountbyId(long accountId) {
		return findById(accountId);
	}

	@Override
	public Account getAccountbyName(String accountName) {
		List<Account> result= findByProperty(AccountDAO.ACCOUNT_NAME, accountName);
		if(result.size()==0){
			return null;
		}else{
			return result.get(0);
		}
	}

	@Override
	public Account getAccountByNameAndPass(String accountName, String password) {
		Criterion name=Restrictions.eq(ACCOUNT_NAME, accountName);
		Criterion pass=Restrictions.eq(PASSWORD, password);
		List<Account> result= findByCriteria(name,pass);
		if(result.size()==0){
			return null;
		}
		return result.get(0);
	}



}
