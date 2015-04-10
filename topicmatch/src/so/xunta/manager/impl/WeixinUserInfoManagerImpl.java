package so.xunta.manager.impl;

import org.hibernate.Session;
import so.xunta.entity.WeixinUserInfo;
import so.xunta.manager.WeixinUserInfoManager;
import so.xunta.utils.HibernateUtils;

public class WeixinUserInfoManagerImpl implements WeixinUserInfoManager {

	@Override
	public void addStaticWeiBoUserInfo(WeixinUserInfo weixinUserInfo) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(weixinUserInfo);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
