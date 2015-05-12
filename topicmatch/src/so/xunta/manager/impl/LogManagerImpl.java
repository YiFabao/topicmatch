package so.xunta.manager.impl;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import so.xunta.entity.UserTrackLog;
import so.xunta.manager.LogManager;
import so.xunta.utils.HibernateUtils;

public class LogManagerImpl implements LogManager {

	@Override
	public void tracklog(UserTrackLog tracklog) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(tracklog);
			session.getTransaction().commit();
		} catch (ConstraintViolationException c) {
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
