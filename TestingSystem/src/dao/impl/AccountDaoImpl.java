package dao.impl;

import javax.ejb.Stateless;

import model.Account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.AbstractHibernateDaoSupport;
import dao.AccountDao;

/**
 * Home object for domain model class Account.
 *
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class AccountDaoImpl extends
AbstractHibernateDaoSupport<Account, String> implements AccountDao {
	private static final Log log = LogFactory.getLog(AccountDaoImpl.class);
	public AccountDaoImpl() {
		super(Account.class);
	}

	@Override
	public boolean addAccount(final Account account) {
		log.info("Add Account "+ account);
		return this.save(account);
	}

	@Override
	public Account getAccountById(final String accountId) {
		log.info("Get Accounts By ID "+ accountId);
		return this.findById(accountId);
	}

	@Override
	public boolean deleteAccount(final Account account) {
		log.info("Delete Account "+ account);
		return this.delete(account);
	}
}
