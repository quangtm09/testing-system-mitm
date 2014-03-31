package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import model.Account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private static final Log log = LogFactory.getLog(AccountDaoImpl.class);
	private static final UserDao userDao = new UserDaoImpl();

	public AccountDaoImpl() {
		super(Account.class);
	}

	@Override
	public boolean addAccount(final Account account) {
		AccountDaoImpl.log.info("Add Account "+ account);
		return this.save(account);
	}

	@Override
	public Account getAccountById(final String accountId) {
		AccountDaoImpl.log.info("Get Accounts By ID "+ accountId);
		return this.findById(accountId);
	}

	@Override
	public boolean deleteAccount(final Account account) {
		AccountDaoImpl.log.info("Delete Account "+ account);
		return this.delete(account);
	}

	@Override
	public List<Account> searchAccount(final String accId, final String fname,
			final String lname, final String email) {
		List<Account> accountList = new ArrayList<Account>();

		try {
			final String queryString = "from Account "
					+ " as a where a."
					+ ACCOUNT_ID + " like :accountId and a.user.fname like :fname and a.user.lname like :lname and a.user.email like :email";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("accountId", criteriaStringForDynamicQuery(accId));
			queryObject.setParameter("fname", criteriaStringForDynamicQuery(fname));
			queryObject.setParameter("lname", criteriaStringForDynamicQuery(lname));
			queryObject.setParameter("email", criteriaStringForDynamicQuery(email));

			accountList = queryObject.list();
		} catch (final RuntimeException re) {
			re.printStackTrace();
		}

		return accountList;
	}

	public static void main(final String[] args) {
		final AccountDaoImpl test = new AccountDaoImpl();
		for(final Account a: test.searchAccount("AD", "", "", "")){
			System.out.println(a.getAccId());
		}
	}
}
