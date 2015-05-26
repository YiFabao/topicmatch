package so.xunta.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils_remote {
	private static SessionFactory sessionFactory=null;
	static{
		Configuration cfg = new Configuration().configure("remote.cfg.xml");
		sessionFactory=cfg.buildSessionFactory();
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void setSessionFactory(SessionFactory sessionFactory) {
		HibernateUtils_remote.sessionFactory = sessionFactory;
	}
	public static Session openSession()
	{
		return sessionFactory.openSession();
	}
}
