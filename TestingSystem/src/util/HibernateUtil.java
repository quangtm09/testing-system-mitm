package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static{
		try{
			sessionFactory=new Configuration().configure().buildSessionFactory();
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println("Can't init session Factory");
			throw new ExceptionInInitializerError(e);
			
		}
		
	}
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}
