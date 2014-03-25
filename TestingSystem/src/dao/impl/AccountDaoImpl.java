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
	public void addAccount(final Account account) {
		log.info("Add Account "+ account);
		this.save(account);
	}

	@Override
	public Account getAccountById(final String accountId) {
		// final Criteria crit = getSession().createCriteria(Account.class);
		// crit.add(Restrictions.eq("accId", accountId));
		// crit.addOrder(Order.asc("accId"));
		//
		// final List<Account> accountList = new ArrayList<Account>();
		// final Iterator<Account> lct = crit.list().iterator();
		//
		// while (lct.hasNext()) {
		// final Account account = lct.next();
		// accountList.add(account);
		// }
		// return accountList.get(0);
		log.info("Get Accounts By ID "+ accountId);
		return this.findById(accountId);
	}

	@Override
	public void deleteAccount(final Account account) {
		log.info("Delete Account "+ account);
		this.delete(account);
	}

	// @Override
	// public List<Account> getAllAccounts() {
	// final Criteria crit = getSession().createCriteria(Account.class);
	// final List<Account> accountList = new ArrayList<Account>();
	// final Iterator<Account> lct = crit.list().iterator();
	//
	// while (lct.hasNext()) {
	// final Account account = lct.next();
	// accountList.add(account);
	// }
	// return accountList;
	// return this.findAll();
	// }
}
