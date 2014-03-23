package dao.interfaces;

//import java.sql.Date;
import java.util.Date;
import java.util.List;

import model.Account;
import model.User;

public interface UserDao {
	
	/*
	 * search User by User_ID
	 */
	User findUserByUserID(String userID);
	
	/*
	 * get List Account By UserID
	 */
	List<Account> getListAccountByUserID(String userID);
	
	/*
	 * add User into database
	 */
	public void insertUser(String userId, String fName, String lName, String email, String mobile, Date bdate, String address);
	
	/*
	 * delete User into database
	 */
	public void deleteUser(String userId);
}
