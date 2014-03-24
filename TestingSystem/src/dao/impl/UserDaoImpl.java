package dao.impl;

import javax.ejb.Stateless;

import model.User;
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


}
