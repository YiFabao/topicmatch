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

	@Override
	public String findWeixinNameByWeixinUid(String weixin_uid) {
		Session session = HibernateUtils.openSession();
		String hql = "select w.nickname from WeixinUserInfo as w where w.weixin_uid =?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, weixin_uid);
		//由于测试时只删了user表中的，没有删weixinuser_info表中的，所以这里有很多相同的，只取一个即可
		String weixin_name = (String) query.list().get(0);
		session.close();
		return weixin_name;
	}
}
