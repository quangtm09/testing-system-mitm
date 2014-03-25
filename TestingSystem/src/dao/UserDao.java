package dao;

import java.util.List;

import model.User;

public interface UserDao extends Dao<User, String>{

	public static final String FIRST_NAME = "fname";
	public static final String LAST_NAME = "lname";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String ADDRESS = "address";

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

	/**
	 * @return
	 */
	public List<User> searchUser(String fname, String lname, String email);
}
