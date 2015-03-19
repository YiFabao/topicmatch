package so.xunta.topic.model.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.topic.entity.Notification;
import so.xunta.topic.model.NotificationManager;
import so.xunta.utils.HibernateUtils;

public class NotificationManagerImpl implements NotificationManager{

	@Override
	public void addNotificationMsg(Notification notification) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(notification);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> findUserIdByNotification(String userId) {
		Session session = HibernateUtils.openSession();
		String hql = "from Notification where userId=? ";
		Query query = session.createQuery(hql).setString(0, userId);
		return query.list();
	}

	@Override
	public void deleteNotification(String userId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "delete Notification where userId = ?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, userId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
