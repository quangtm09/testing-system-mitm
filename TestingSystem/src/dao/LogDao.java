/**
 *
 */
package dao;

import java.sql.Date;
import java.util.List;

import model.Account;
import model.Logs;

/**
 * @author huynh.ai.loan
 *
 */
public interface LogDao extends Dao<Logs, Integer> {
	public static final String ACCOUNT = "account";
	public static final String ACCOUNT_ID = "accId";
	public static final String USER_ID = "userId";
	public static final String LOG_DATE = "logDate";

	List<Logs> getLogByAccount(Account account);

	public boolean deleteLogCurrentDay(Date date);

	public boolean deleteLogOneMonth(Date date);

	public boolean deleteLogEveryThing();

	public List<Logs> searchLogsByUser(final String accountId,
			final String userId);
}
