/**
 *
 */
package dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import model.Account;
import model.Logs;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import dao.AbstractHibernateDaoSupport;
import dao.AccountDao;
import dao.LogDao;

/**
 * @author huynh.ai.loan
 *
 */
@Stateless
public class LogDaoImpl extends AbstractHibernateDaoSupport<Logs, Integer>
		implements LogDao {
	private static final Logger log = Logger.getLogger(LogDaoImpl.class);

	public LogDaoImpl() {
		super(Logs.class);
	}

	@Override
	public List<Logs> getLogByAccount(Account account) {
		// TODO Auto-generated method stub
		final List<Logs> results = this.findByProperty(LogDao.ACCOUNT, account);
		return results;
	}

	public static void main(String[] args) {
		LogDao ld = new LogDaoImpl();
		AccountDao accD = new AccountDaoImpl();
		Account acc = accD.findById("AD01");
		List<Logs> logs = ld.findAll();
	}

	@Override
	public boolean deleteLogCurrentDay(Date date) {
		// TODO Auto-generated method stub
		final List<Logs> listlog = this.findByProperty(LogDao.LOG_DATE, date);
		log.debug("Delete in Log with Date "+ date);
		try {
			for (Logs logs : listlog)
			{
				this.delete(logs);
			}
			return true;
		} catch (final RuntimeException re) {
			re.printStackTrace();
			log.error("Exception" + re);
			return false;
		}
	}

	@Override
	public boolean deleteLogOneMonth(Date date) {
		// TODO Auto-generated method stub
//		delete from TESTDEPT
//		where to_char(createdate, 'YYYYMMDD')
//		between to_char(sysdate, 'YYYYMMDD') and to_char(sysdate + 28, 'YYYYMMDD');
		return false;
	}

	@Override
	public boolean deleteLogEveryThing() {
		// TODO Auto-generated method stub
		final List<Logs> listlog = this.findAll();
		log.debug("Delete in Log EveryThing");
		try {
			for (Logs logs : listlog)
			{
				this.delete(logs);
			}
			return true;
		} catch (final RuntimeException re) {
			re.printStackTrace();
			log.error("Exception" + re);
			return false;
		}
	}

	@Override
	public List<Logs> searchLogsByUser(final String accountId,
			final String userId) {
		List<Logs> logList = new ArrayList<Logs>();
		log.info("Search Logs By AccountId " + accountId + "or UserId "+ userId);

		try {
			final String queryString = "from Logs " + " as a where a.account."
					+ ACCOUNT_ID
					+ " like :accountId and a.account.user.userId like :userId ";
			final Query queryObject = AbstractHibernateDaoSupport.getSession()
					.createQuery(queryString);
			queryObject.setParameter("accountId",
					criteriaStringForDynamicQuery(accountId));
			queryObject.setParameter("userId",
					criteriaStringForDynamicQuery(userId));

			logList = queryObject.list();
		} catch (final RuntimeException re) {
			re.printStackTrace();
			log.error("Exception" + re);
		}

		return logList;
	}
}
