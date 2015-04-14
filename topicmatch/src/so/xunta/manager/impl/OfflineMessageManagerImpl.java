package so.xunta.manager.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.manager.OfflineMessageManager;
import so.xunta.utils.HibernateUtils;
import so.xunta.websocket.entity.OfflineMessage;

public class OfflineMessageManagerImpl implements OfflineMessageManager{

	@Override
	public List<OfflineMessage> getOfflineunread(Long accepterId) {
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("from OfflineMessage where accepterId=?").setLong(0, accepterId);
			session.getTransaction().commit();
			return query.list();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
