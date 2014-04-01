package dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import model.User;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import dao.AbstractHibernateDaoSupport;
import dao.UserDao;

/**
 * Home object for domain model class Account.
 *
 * @see .Account
 * @author Hibernate Tools
 */
@Stateless
public class UserDaoImpl extends
AbstractHibernateDaoSupport<User, String> implements UserDao {

	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public boolean saveUser(final User user) {
		return this.save(user);
	}

	@Override
	public User getUserById(final String userId) {
		return this.findById(userId);
	}

	@Override
	public boolean updateUser(final User user) {
		return this.update(user);
	}

	@Override
	public boolean deleteUser(final User user) {
		return this.delete(user);
	}

	@Override
	public List<User> searchUser(final String fname, final String lname, final String email) {
		final List<User> userList = new ArrayList<User>();

		// insensitive like
		final Criterion fnameCrit = Restrictions.ilike(FIRST_NAME, criteriaStringForDynamicQuery(fname));
		final Criterion lnameCrit = Restrictions.ilike(LAST_NAME, criteriaStringForDynamicQuery(lname));
		final Criterion emailCrit = Restrictions.ilike(EMAIL, criteriaStringForDynamicQuery(email));

		Criterion searchUserCriterion = Restrictions.and(fnameCrit, lnameCrit);
		searchUserCriterion = Restrictions.and(searchUserCriterion, emailCrit);

		final List<User> result = this.findByCriteria(searchUserCriterion);

		if(result.size() == 0){
			return userList;
		}

		return result;
	}
}
