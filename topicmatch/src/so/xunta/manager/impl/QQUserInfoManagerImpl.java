package so.xunta.manager.impl;

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

}
