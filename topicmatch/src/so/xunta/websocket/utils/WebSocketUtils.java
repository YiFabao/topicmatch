package so.xunta.websocket.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.impl.TopicManagerImpl;
import so.xunta.utils.HibernateUtils;
import so.xunta.websocket.entity.HistoryMessage;
import so.xunta.websocket.entity.OfflineMessage;

public class WebSocketUtils {
	static TopicManager topicManager = new TopicManagerImpl();
	/*
	 * 添加历史消息
	 * */
	public static void addHistoryMessage(HistoryMessage historyMessage) {
		System.out.println("服务器LOG ：  准备添加历史消息");
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(historyMessage);
			//topicManager.updateLastUpdateTime(historyMessage.topicId,historyMessage.dateAndTime);
			topicManager.updateLastResMsgAndTime(historyMessage.topicId, historyMessage.content, historyMessage.dateAndTime);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
		
		System.out.println("服务器LOG ：  添加历史消息成功");
	}
	
	/*
	 * 添加历史消息 yifabao
	 * */
	public static void addHistoryMessageList(List<HistoryMessage> historyMessageList) {
		System.out.println("服务器LOG ：  准备添加历史消息");
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		try {
			for(int i = 0;i<historyMessageList.size();i++){
				session.save(historyMessageList.get(i));
				if(i%10==0){
					session.flush();
					session.clear();
				}
			//topicManager.updateLastUpdateTime(historyMessage.topicId,historyMessage.dateAndTime);
			//topicManager.updateLastResMsgAndTime(historyMessage.topicId, historyMessage.content, historyMessage.dateAndTime);
			}
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			//session.getTransaction().rollback();
			System.out.println("异常："+e.getMessage());
		
		} finally {
			session.close();
		}
		
		
		System.out.println("服务器LOG ：  添加历史消息成功");
	}
	/**
	 * 找出后回复历史消息
	 * @param accepterId
	 * @param topicId
	 */
	public static HistoryMessage findLastHistoryMsgInTopic(String topicId){
		Session session = HibernateUtils.openSession();
		session.beginTransaction();
		try {
			String hql = "from HistoryMessage as h where h.topicId =? order by h.longTime desc";
			HistoryMessage historyMessage = (HistoryMessage) session.createQuery(hql)
					.setParameter(0,topicId).setFirstResult(0).setMaxResults(1).uniqueResult();
			session.getTransaction().commit();
			return historyMessage;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	
	
	/*
	 * 设置未读消息数
	 * */
	public static void setUnreadMessageNum(long accepterId , String topicId){
		System.out.println("服务器LOG ：  准备设置未读消息数");
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("from OfflineMessage where topicId=? and accepterId=?").setString(0, topicId).setLong(1, accepterId);
			int count = query.list().size();
			if(count == 0){
				System.out.println("1");
				session.save(new OfflineMessage(topicId, accepterId, (long) 1));
				session.getTransaction().commit();
			}else{
				System.out.println("2");
				OfflineMessage om = (OfflineMessage) query.uniqueResult();
				Long num = om.getCount();
				om.setCount(++num);
				session.update(om);
				session.getTransaction().commit();
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		System.out.println("服务器LOG ：  设置未读消息数成功");
	}
	/*
	 * 获取话题下的成员列表
	 * */
	public static List<String> getTopicUserIdList(String topicId) {
		System.out.println("服务器LOG ：  准备获取话题下的成员列表");
		List<String> arrayList = new ArrayList<String>();
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("select topicMemberId from TopicGroup where topicId=?").setString(0, topicId);
			session.getTransaction().commit();
			arrayList = query.list();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		System.out.println("服务器LOG ：  获取话题下的成员列表成功");
		return arrayList;
	}
	/*
	 * 通过某用户的ID获取话题Id列表
	 * */
	public static List<String> getTopicId(int accepterId) {
		System.out.println("服务器LOG ：  准备通过某用户的ID获取话题Id列表");
		List<String> arrayList = new ArrayList<String>();
		Session session = HibernateUtils.openSession();
		Query query;
		try {
			session.beginTransaction();
			query = session.createQuery("select topicId from TopicHistory where authorId=?").setString(0, accepterId+"");
			session.getTransaction().commit();
			arrayList = query.list();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		System.out.println("服务器LOG ： 通过某用户的ID获取话题Id列表成功");
		return arrayList;
	}
	/*
	 * 获取未读消息数
	 * */
	public static String getUnreadMessageNum(String topic_id,int accepter_id) {
		System.out.println("服务器LOG ：  准备获取未读消息数");
		Session session = HibernateUtils.openSession();
		Query query;
		String string;
		try {
			session.beginTransaction();
			query = session.createQuery("from OfflineMessage as o where o.topicId=? and o.accepterId=?").setString(0,topic_id).setLong(1, accepter_id);
			session.getTransaction().commit();
			OfflineMessage offlineMessage = (OfflineMessage) query.uniqueResult();
			if(offlineMessage == null){
				string = "0";
			}else{
				string = offlineMessage.getCount()+"";
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		System.out.println("服务器LOG ：  获取未读消息数成功");
		return string;
	}
	
	/*
	 * 删除未读消息
	 * */
	public static void deleteUnreadMessage(String topic_id, int accepter_id) {
		System.out.println("服务器LOG ：  准备删除未读消息");
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from OfflineMessage as o where o.topicId=? and o.accepterId=?").setString(0,topic_id).setLong(1, accepter_id);
			OfflineMessage offlineMessage = (OfflineMessage) query.uniqueResult();
			if(offlineMessage != null){
				session.delete(offlineMessage);
				session.getTransaction().commit();
			}
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		System.out.println("服务器LOG ：  删除未读消息成功");
	}
	
	
	
	public static void main(String[] args) {
		WebSocketUtils w = new WebSocketUtils();
//		w.addHistoryMessage(new HistoryMessage(1, "1", (long) 1, 1, "1", "1", "1", "1", 1, 1));
		w.getUnreadMessageNum("200",100);
//		List<String> list = w.getTopicUserIdList("C798242451CE56E29DE813B131AA2982");
//		for (String string : list) {
//			System.out.println(string);
//		}
//		List<String> list = w.getTopicId("1");
//		for (String string : list) {
//			System.out.println(string);
//		}
//		w.deleteUnreadMessage("200",100);
	}
}