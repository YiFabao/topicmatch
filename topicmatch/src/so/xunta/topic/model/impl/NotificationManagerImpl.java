package so.xunta.topic.model.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.topic.entity.SystemMessageNotification;
import so.xunta.topic.entity.TopicInviteNotification;
import so.xunta.topic.model.NotificationManager;
import so.xunta.utils.HibernateUtils;

public class NotificationManagerImpl implements NotificationManager{

	@Override
	public void addTopicNotificationMsg(TopicInviteNotification notification) {
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
	public List<TopicInviteNotification> findUserIdByTopicInviteNotification(String userId) {
		Session session = HibernateUtils.openSession();
		String hql = "from TopicInviteNotification where toUserId=? ";
		Query query = session.createQuery(hql).setString(0, userId);
		return query.list();
	}

	@Override
	public void deleteTopicInviteNotification(String topicId,String toUserId) {
		System.out.println("topicId : "+topicId+"  toUserId : "+toUserId);
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "delete TopicInviteNotification where topicId = ? and toUserId = ?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
			query.setString(1, toUserId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void addSystemNotificationMsg(SystemMessageNotification notification) {
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

	@Override
	public List<SystemMessageNotification> findUserIdBySystemNotification(String userId) {
		Session session = HibernateUtils.openSession();
		String hql = "from SystemMessageNotification where userId=? ";
		Query query = session.createQuery(hql).setString(0, userId);
		return query.list();
	}
	
	@Override
	public void deleteSystemMessageNotification(String topicId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String hql = "delete SystemMessageNotification where userId = ?";
			org.hibernate.Query query = session.createQuery(hql);
			query.setString(0, topicId);
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