package so.xunta.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import so.xunta.entity.TravelForum;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.impl.TopicManagerImpl;
import so.xunta.topic.utils.IpUtils;
import so.xunta.topic.utils.SecurityUtil;
import so.xunta.websocket.entity.HistoryMessage;
import so.xunta.websocket.utils.WebSocketUtils;

public class ImportDataUtils {
	public static void main(String[] args) {

		//定义导入数据的范围
		int init = 22735353;
		int max = 1000000;
		for(int i =init;i<init+max;i+=2001){
			so.xunta.utils.ImportDataUtils.addUser_1(i,1000);
			so.xunta.utils.ImportDataUtils.addTopicxx_2(i,1000);
			so.xunta.utils.ImportDataUtils.addHistoryMessage(i,1000);
		}
	}
	static UserManager usermanager = new UserManagerImpl();
	static TopicManager topicmanager = new TopicManagerImpl();

	// 更新最后回复,查询每个话题的最后回复，有就更新
	public static void updateLastMsgInTopic(List<String> idList) {
		// 查出所有的话题id List<topicId>
		// 从historymessage中找出为topicId的 HistoryMessage 按是时间排序
		if(idList==null||idList.size()==0){
			return;
		}
		Session session =HibernateUtils.openSession();
		TopicManager topicManager = new TopicManagerImpl();
		try {
			String hql = "from Topic as t where t.topicId in (:idList)";
			List<Topic> topicList = session.createQuery(hql).setParameterList("idList", idList).list();
			for(Topic topic:topicList){
				if (topic != null) {
					HistoryMessage historyMessage = WebSocketUtils.findLastHistoryMsgInTopic(topic.getTopicId());
					if (historyMessage != null) {
						topicManager.updateLastResMsgAndTime(historyMessage.topicId, historyMessage.content, historyMessage.dateAndTime);
					}
				}
			}
			
		} catch (HibernateException e) {
			System.out.println("更新最后回复信息异常"+e.getMessage());
		}finally{
			session.close();
		}
	}

	// 创建话题组用户，并创建话题组内成员聊天消息
	public static void addHistoryMessage(int _from, int num) {
		Session session = HibernateUtils_remote.openSession();
		for (int i = _from; i < _from + num; i += 201) {
			int start = i;
			int end = i + 200;
			String hql = "from TravelForum as t where t.id between ? and ?";
			List<TravelForum> travelForumlist = session.createQuery(hql).setInteger(0, start).setInteger(1, end).list();
			if(travelForumlist==null||travelForumlist.size()==0){
				return;
			}
			List<String> authorList = new ArrayList<String>();
			// 根据List<title>查出所有的话题，因为title很多是相同的，所以先取出唯一的title
			Set<String> title_set = new HashSet<String>();
			List<String> title_list = new ArrayList<String>();
			Map<String, String> authorname_title = new HashMap<String, String>();
			for (TravelForum t : travelForumlist) {
				if (t.getLevel() != 0) {
					title_set.add(t.getTitle());
					authorList.add(t.getAuthor());
				}
			}
			title_list.addAll(title_set);
			Map<String, Topic> title_topic_map = new HashMap<String, Topic>();// 通过title
																				// 可以拿到Topic
			List<Topic> t_list = topicmanager.findTopicsByTopicNameList(title_list);// 根据topicTitle查询出所有的Topic
			for (Topic topic : t_list) {
				System.out.println(topic.getTopicName());
				title_topic_map.put(topic.topicName, topic);
			}

			// 根据List<Author>查出所有的user
			List<User> userList = usermanager.findUserListByUsernames(authorList);
			Map<String, User> username_user_map = new HashMap<String, User>();// 根据username
																				// 能拿到User
			for (User u : userList) {
				username_user_map.put(u.getXunta_username(), u);
			}

			// 生成topicgroup, topichistory 及 historymessage
			List<TopicGroup> topicGroupList = new ArrayList<TopicGroup>();
			List<HistoryMessage> historyMessageList = new ArrayList<HistoryMessage>();
			for (TravelForum t : travelForumlist) {
				if (t.getLevel() != 0) {
					String authorId = String.valueOf(username_user_map.get(t.getAuthor()).id);
					System.out.println(t.getTitle());
					if (title_topic_map.get(t.getTitle()) == null) {
						continue;
					}
					String topicId = title_topic_map.get(t.getTitle()).getTopicId();
					String dateTime = DateTimeUtils.getTimeStrFromDate(t.getPublishtime());
					TopicGroup topicGroup = new TopicGroup(topicId, authorId, t.getAuthor(), dateTime);
					// TopicHistory topicHistory = new TopicHistory(authorId,
					// topicId, dateTime,'j');//模似的用户暂时不添加话题历史

					String messageId = authorId + "" + (t.getPublishtime().getTime());

					int date = t.getPublishdate();
					int time = Integer.parseInt(Time.getTime(t.getPublishtime()));
					long longtime = t.getPublishtime().getTime();
					HistoryMessage historyMessage = new HistoryMessage(1, topicId, Long.valueOf(messageId), Integer.parseInt(authorId), "[]", t.getContent(),
							DateTimeUtils.getTimeStrFromDate(t.getPublishtime()), t.getAuthor(), date, time, longtime);
					topicGroupList.add(topicGroup);
					historyMessageList.add(historyMessage);
				}
			}

			// 添加
			topicmanager.saveTopicGroupList(topicGroupList);
			WebSocketUtils.addHistoryMessageList(historyMessageList);
			
			
			List<String> idList = new ArrayList<String>();
			for(HistoryMessage h:historyMessageList){
				idList.add(h.getTopicId());
			}
			updateLastMsgInTopic(idList);
			for(String topicId:idList){
				topicmanager.addTopicJoinNumByOne(topicId);
			}
		}
		session.close();
	}

	public static void addTopicxx_2(int _from, int num) {

		Session session = HibernateUtils_remote.openSession();
		for (int i = _from; i < _from + num; i += 201) {
			int start = i;
			int end = i + 200;
			String hql = "from TravelForum as t where t.id between ? and ?";
			List<TravelForum> travelForumlist = session.createQuery(hql).setInteger(0, start).setInteger(1, end).list();

			List<Topic> topicList = new ArrayList<Topic>();
			List<String> authors = new ArrayList<String>();
			Map<String, Topic> auhtor_topic_map = new HashMap<String, Topic>();
			for (TravelForum t : travelForumlist) {
				if (t.getLevel() == 0) {
					Topic topic = new Topic();
					topic.setUserName(t.getAuthor());
					topic.setTopicName(t.getTitle());
					topic.setTopicContent(t.getContent());
					topic.setCreateTime(DateTimeUtils.getTimeStrFromDate(t.getPublishtime()));
					topic.setLastUpdateTime(DateTimeUtils.getTimeStrFromDate(t.getPublishtime()));
					topic.setLogo_url("default_img.jpg");
					topicList.add(topic);
					// System.out.println(t.getAuthor()+" "+t.getTitle()+
					// " "+t.getLevel());
					authors.add(t.getAuthor());
					auhtor_topic_map.put(t.getAuthor(), topic);
				}else{
					System.out.println("跟贴");
				}
			}
			for(String s:authors)
			{
				System.out.println(s);
			}
			// 根据List<Author>查出所有的user
			List<User> userList = usermanager.findUserListByUsernames(authors);
			Map<String, User> username_user_map = new HashMap<String, User>();
			if(userList==null){
				System.out.println("userList 为空");
			}else{
				for (User u : userList) {
					username_user_map.put(u.getXunta_username(), u);
				}
			}

			// 保存话题，创建索引，话题组
			List<Topic> toSaveTopicList = new ArrayList<Topic>();
			List<TopicGroup> toSaveTopicGroupList = new ArrayList<TopicGroup>();
			List<TopicHistory> toSaveTopicHistoryList = new ArrayList<TopicHistory>();
			for (Topic topic : topicList) {
				User user = username_user_map.get(topic.getUserName());
				long userId = user.getId();
				// System.out.println("保存话题:"+userId+" "+topic.getTopicName());
				String userName = user.getXunta_username();
				String userLogoUrl = user.getImageUrl();
				String topicName = topic.getTopicName();
				String topicContent = topic.getTopicContent();
				// 话题发起时间
				String topicCreateTime = topic.getCreateTime();
				// 话题Id 由 [用户id+话题名称+话题内容+话题创建时间] 的字符串拼接字符串生成的md5
				String topicId = SecurityUtil.strToMD5(userId + topicName + topicContent + topicCreateTime);
				// 保存用户话题
				Topic t = new Topic(topicId, String.valueOf(userId), userName, topicName, topicContent, userLogoUrl, topicCreateTime, topicCreateTime);

				// topicmanager.createTopicIndex(t);

				TopicGroup topicGroup = new TopicGroup(t.topicId, t.userId, t.userName, t.createTime);
				// 保存话题
				// topicmanager.saveTopic(t);
				// 将用户保存到话题组
				// topicmanager.saveTopicGroup(topicGroup);
				// 保存话题历史
				TopicHistory topicHistory = new TopicHistory(t.userId, t.topicId, t.createTime, 'p');
				// topicmanager.addTopicHistory(topicHistory);
				toSaveTopicList.add(t);
				toSaveTopicGroupList.add(topicGroup);
				toSaveTopicHistoryList.add(topicHistory);
			}

			topicmanager.saveTopicList(toSaveTopicList);
			topicmanager.saveTopicGroupList(toSaveTopicGroupList);
			topicmanager.addTopicHistoryList(toSaveTopicHistoryList);
			// 创建索引
			topicmanager.createTopicListIndex(toSaveTopicList);

		}
		session.close();
	}

	public static void addUser_1(int _from, int num) {
		Session session = HibernateUtils_remote.openSession();
		for (int i = _from; i < _from + num; i += 201) {
			int start = i;
			int end = i +200;
			String hql = "from TravelForum as t where t.id between ? and ?";
			List<TravelForum> travelForumlist = session.createQuery(hql).setInteger(0, start).setInteger(1, end).list();
			// List<User> userList = new ArrayList<User>();
			for (TravelForum t : travelForumlist) {
				System.out.println(t.getId() + "  " + t.getAuthor() + " " + t.getTitle());
				// 1.创建User
				User user = new User();
				user.setAuthorId(t.getId());
				user.setXunta_username(t.getAuthor());
				user.setNickname(t.getAuthor());
				user.setPassword("admin");
				user.setImageUrl("default_img.jpg");
				user.setSex(RandomUtils.nextBoolean() == true ? "男" : "女");
				user.setAddress(IpUtils.getInstance().getRandomCountryAddress());
				try {
					usermanager.addUser(user);
				} catch (Exception e) {
					System.out.println("重复");
				}
			}
		}
		session.close();
	}
	

}
