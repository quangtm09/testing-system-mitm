package test.datalogic;

import static org.junit.Assert.fail;

import java.io.File;

import model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserDaoImplTest {
	private User user;
	private final UserDao userDao = new UserDaoImpl();
	private String userID;
	private static Configuration config;
	private static SessionFactory factory;
	private static Session hibernateSession;

	@Before
	public void setUp() throws Exception {
		config = new AnnotationConfiguration();
		config.configure(new File("hibernate.cfg.xml"));
		factory = config.buildSessionFactory();
		hibernateSession = factory.openSession();
		this.userID = "TEST01";
	}

	@After
	public void tearDown() throws Exception {
		this.userID = null;
	}

	@Test
	public final void testUpdateUser() {
		// TODO
		final String Fname = "TEST";
		final String Lname = "ABC";
		this.user = new User();
		this.user.setUserId(this.userID);
		this.userDao.save(this.user);
	}

	@Test
	public final void testDeleteUser() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSearchUser() {
		fail("Not yet implemented"); // TODO
	}

}
