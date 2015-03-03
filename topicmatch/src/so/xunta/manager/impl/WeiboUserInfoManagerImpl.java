package so.xunta.manager.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.entity.WeiboDynamicInfoContent;
import so.xunta.entity.WeiboUserInfo;
import so.xunta.manager.WeiboUserInfoManager;
import so.xunta.utils.HibernateUtils;

public class WeiboUserInfoManagerImpl implements WeiboUserInfoManager {

	@Override
	public void addStaticWeiBoUserInfo(WeiboUserInfo weiboUserInfo) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(weiboUserInfo);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void addDynamicWeiboInfoContent(
			WeiboDynamicInfoContent weiboDynamicInfoContent) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(weiboDynamicInfoContent);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public String findLatestWeiContentByOpenId(String weibo_uid) {
		Session session = HibernateUtils.openSession();
		String hql = "select w.content from WeiboDynamicInfoContent as w where w.weibo_uid=? order by w.id DESC";
		Query query = session.createQuery(hql).setString(0, weibo_uid).setFirstResult(0).setMaxResults(1);
		String content = (String) query.uniqueResult();
		session.close();
		return content;
	}
	
	public static void main(String[] args) {
		WeiboUserInfoManagerImpl wm=new WeiboUserInfoManagerImpl();
/*		WeiboUserInfo webo=new WeiboUserInfo("bb", "男", "湖北", "最最近一条　", "", "", "dd");
		wm.addStaticWeiBoUserInfo(webo);*/
	/*	String aa = wm.findLatestWeiContentByOpenId("sdsdf");
		System.out.println(aa);*/
/*
		WeiboDynamicInfoContent wd=new WeiboDynamicInfoContent("sdsdf","最近一条　动态内容");
		wm.addDynamicWeiboInfoContent(wd);*/
		
	}
	
	

}
