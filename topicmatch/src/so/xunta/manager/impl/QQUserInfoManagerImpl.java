package so.xunta.manager.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;
import so.xunta.manager.QQUserInfoManager;
import so.xunta.utils.HibernateUtils;

public class QQUserInfoManagerImpl implements QQUserInfoManager{

	@Override
	public void addStaticQQUserInfo(QQUserInfo qquserInfo) {

		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(qquserInfo);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
	}

	@Override
	public void addDynamicQQInfoContent(QQDynamicInfoContent qqdynamicInfoContent) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(qqdynamicInfoContent);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public String findLatestQQContentByOpenId(String openId) {
		Session session = HibernateUtils.openSession();
		String hql = "select q.content from QQDynamicInfoContent as q where q.qq_openId=? order by q.id DESC";
		Query query = session.createQuery(hql).setString(0, openId).setFirstResult(0).setMaxResults(1);
		String content = (String) query.uniqueResult();
		session.close();
		return content;
	}

}
