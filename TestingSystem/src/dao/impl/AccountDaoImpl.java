package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import model.Account;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.hibernate.Query;

import dao.AbstractHibernateDaoSupport;
import dao.AccountDao;
import dao.UserDao;

/**
 * Home object for domain model class Account.
 *
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class AccountDaoImpl extends
		AbstractHibernateDaoSupport<Account, String> implements AccountDao {
	private static final Logger log = Logger.getLogger(AccountDaoImpl.class);

	public AccountDaoImpl() {
		super(Account.class);
	}

	@Override
	public boolean addAccount(final Account account) {
		AccountDaoImpl.log.info("Add Account " + account);
		return this.save(account);
	}

	@Override
	public Account getAccountById(final String accountId) {
		AccountDaoImpl.log.info("Get Accounts By ID " + accountId);
		return this.findById(accountId);
	}

	@Override
	public boolean deleteAccount(final Account account) {
		AccountDaoImpl.log.info("Delete Account " + account);
		return this.delete(account);
	}

	@Override
	public List<Account> searchAccount(final String accId, final String fname,
			final String lname, final String email) {
		List<Account> accountList = new ArrayList<Account>();
		log.info("Search Account By AccountId: "+ accId + " ,Fname: "+ fname + " ,Lname: "+ lname + " ,Email: "+ email);
		try {
			final String queryString = "from Account "
					+ " as a where a."
					+ ACCOUNT_ID
					+ " like :accountId and a.user.fname like :fname and a.user.lname like :lname and a.user.email like :email";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("accountId",
					criteriaStringForDynamicQuery(accId));
			queryObject.setParameter("fname",
					criteriaStringForDynamicQuery(fname));
			queryObject.setParameter("lname",
					criteriaStringForDynamicQuery(lname));
			queryObject.setParameter("email",
					criteriaStringForDynamicQuery(email));

			accountList = queryObject.list();
			log.debug("Search successful");
		} catch (final RuntimeException re) {
			re.printStackTrace();
			log.error("Search Failed "+ re);
		}

		return accountList;
	}

	public static void main(final String[] args) {
		final AccountDaoImpl test = new AccountDaoImpl();
		for (final Account a : test.searchAccount("AD", "", "", "")) {
			System.out.println(a.getAccId());
		}
	}
}
