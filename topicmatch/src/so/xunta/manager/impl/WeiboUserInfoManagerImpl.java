package so.xunta.manager.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import so.xunta.entity.Tag;
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
	
	@Override
	public void addWeiboContentAndWeiboUserId(WeiboDynamicInfoContent weiboDynamicInfoContent) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(weiboDynamicInfoContent);
			session.getTransaction().commit();
		}catch(ConstraintViolationException c){
			System.out.println("使用新浪微博登录后，获取其历史数据存储时，因数据重复，触发此异常");
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
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
		
		WeiboDynamicInfoContent w = new WeiboDynamicInfoContent("1", "1");
		wm.addWeiboContentAndWeiboUserId(w);
	}

	@Override
	public String findWeiboNameByWeiboUid(String weibo_uid) {
		Session session = HibernateUtils.openSession();
		String hql = "select w.nickname from WeiboUserInfo as w where w.weibo_uid =?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setString(0, weibo_uid);
		//由于测试时只删了user表中的，没有删weibouser_info表中的，所以这里有很多相同的，只取一个即可
		String weibo_name = (String) query.list().get(0);
		session.close();
		return weibo_name;
	}
}