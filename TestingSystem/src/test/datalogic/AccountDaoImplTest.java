package test.datalogic;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;

public class AccountDaoImplTest {
	private Account account;
	private AccountDao accountDao = new AccountDaoImpl();
	private String accountId;

	@Before
	public void setUp() throws Exception {
		accountId = "TEST123";
	}

	@After
	public void tearDown() throws Exception {
		accountId = null;
	}

	@Test
	public final void testAddAccount() {
		// TODO
		checkAccountExist();
		assertNull("Account does not exist: ", account);
		insertAccount();
		assertNotNull("Account is added ", account.getAccId());
	}

	@Test
	public final void testGetAccountById() {
		 // TODO
		insertAccount();
		assertNotNull("Account is gotten ", account.getAccId());
	}

	@Test
	public final void testDeleteAccount() {
		// TODO
		insertAccount();
		checkAccountExist();
		assertNotNull("Account exists "+ account);
		accountDao.delete(account);
		assertNull("Account is deleted ", account);
	}

	private final void insertAccount(){
		account = new Account();
		account.setAccId(accountId);
		accountDao.save(account);
	}

	private final void checkAccountExist()
	{
		account = accountDao.findById(accountId);
	}

}
