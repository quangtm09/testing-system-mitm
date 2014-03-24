package dao;

import model.User;

public interface UserDao extends Dao<User, String>{
	/**
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user);
	/**
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);
	/**
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * @param user
	 * @return
	 */
	public boolean deleteUser(User user);
}
