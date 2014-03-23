package dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import model.Account;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;
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

	public AccountDaoImpl() {
		super(Account.class);
	}

	@Override
	public void addAccount(final Account account) {
		HibernateUtil.beginTransaction();
		this.save(account);
		HibernateUtil.commitTransaction();
	}

	@Override
	public Account getAccountById(final String accountId) {
		final Criteria crit = getSession().createCriteria(Account.class);
		crit.add(Restrictions.eq("accId", accountId));
		crit.addOrder(Order.asc("accId"));

		final List<Account> accountList = new ArrayList<Account>();
		final Iterator<Account> lct = crit.list().iterator();

		while (lct.hasNext()) {
			final Account account = lct.next();
			accountList.add(account);
		}
		return accountList.get(0);
	}

	@Override
	public void deleteAccount(final Account account) {
		HibernateUtil.beginTransaction();
		this.delete(account);
		HibernateUtil.commitTransaction();
	}

	@Override
	public List<Account> getAllAccounts() {
		final Criteria crit = getSession().createCriteria(Account.class);
		final List<Account> accountList = new ArrayList<Account>();
		final Iterator<Account> lct = crit.list().iterator();

		while (lct.hasNext()) {
			final Account account = lct.next();
			accountList.add(account);
		}
		return accountList;
	}
}
