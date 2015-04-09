package so.xunta.manager.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;
import so.xunta.manager.QQUserInfoManager;
import so.xunta.utils.HibernateUtils;

public class QQUserInfoManagerImpl implements QQUserInfoManager {

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
		} catch (ConstraintViolationException c) {
			System.out.println("使用新浪微博登录后，获取其历史数据存储时，因数据重复，触发此异常");
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


	@Override
	public String findQQNameByOpenid(String qq_openid) {
		Session session = HibernateUtils.openSession();
		String hql = "select q.nickname from QQUserInfo as q where q.qq_openId =?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, qq_openid);
		String qq_name = (String) query.list().get(0);
		session.close();
		return qq_name;
	}

}
