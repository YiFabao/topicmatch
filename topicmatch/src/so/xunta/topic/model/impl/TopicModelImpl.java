package so.xunta.topic.model.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

import net.sf.json.JSONArray;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.entity.MatchedPeopleDetail;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.TopicModel;
import so.xunta.utils.DateTimeUtils;

public class TopicModelImpl implements TopicModel{
	
	TopicManager topicManager = new TopicManagerImpl();
	UserManager userManager = new UserManagerImpl();
	
	@Override
	public void joinTopic(HttpServletRequest request, HttpServletResponse response,String userId, String topicId) {
		//根据topicId 查询出Topic
		Topic topic = topicManager.findTopicByTopicId(topicId);
		//根据用户Id查询出发起人
		User publisher = userManager.findUserById(Integer.parseInt(topic.userId));
		//查询出参与人
		User joinUser = userManager.findUserById(Integer.parseInt(userId));
		
		//保存参与话题历史,要检查在参与话题历史是否存在
		String currentTime = DateTimeUtils.getCurrentTimeStr();
		if(!topicManager.checkTopicIsExitInHistory(userId, topicId))
		{
			TopicHistory topicHistory = new TopicHistory(userId, topicId,currentTime ,'j');
			topicManager.addTopicHistory(topicHistory);
			topicManager.addTopicJoinNumByOne(topicId);
		}
		//保存话题组,检查是否已经存在到话题组
		if(!topicManager.checkIsTopicMember(userId, topicId))
		{
			TopicGroup topicMember =new TopicGroup(topicId,userId,joinUser.getXunta_username(),currentTime);
			topicManager.saveTopicGroup(topicMember);
		}
		//根据topicId 查询出该话题下的用户列表List<User>
			//1.先从topicgroup中查询出List<userId>
			//２.再从user表中查询出List<memberId>
		List<String> memberIds =topicManager.findMemberIdsByTopicId(topicId);
		
		List<Long> memberId_long_list = new ArrayList<Long>();
		for(String memberId:memberIds)
		{
			memberId_long_list.add(new Long(memberId));
		}
		List<User> memberList = userManager.findUserListByUserIdList(memberId_long_list);
		for(User memUser:memberList)
		{
			System.out.println(memUser.xunta_username);
		}
		
		//将　发起人　Topic 及　用户列表 保存到request范围
		request.setAttribute("topic",topic);
		request.setAttribute("publisher",publisher);
		request.setAttribute("memberList",memberList);
		
	}

	@Override
	public List<MatchedPeopleDetail> matchedPeopleDetaiList(List<Topic> topicList) {
		Map<String,MatchedPeopleDetail> matchedMap = new HashMap<String,MatchedPeopleDetail>();
		//遍历每个topic
		List<String> topicIdList =new ArrayList<String>();
		for(Topic topic:topicList)
		{
			topicIdList.add(topic.topicId);
		}
		//获取与该话题id对应的话题历史
		List<TopicHistory> topicHistoryList = topicManager.findTopicHistoryByTopicId(topicIdList);
		if(topicHistoryList==null){return null;}
		for(TopicHistory t:topicHistoryList)
		{
			//System.out.println(t.topicId+"  "+t.publish_or_join);
			//遍历每个话题下的成员
			String key = t.authorId;
			System.out.println("话题成员id:"+key);
			if(matchedMap.containsKey(key)){//存在直接添加
				MatchedPeopleDetail  matchedPeopleDetail = matchedMap.get(key);
				//判断该用户是发起还是参与
				if(t.publish_or_join=='p'){//发起话题
					matchedPeopleDetail.addPulishTopic(t.topicId);
				}
				else if(t.publish_or_join=='j'){
					matchedPeopleDetail.addJoinTopic(t.topicId);
				}
			}else{//不存在要创建
				MatchedPeopleDetail  matchedPeopleDetail = new MatchedPeopleDetail();
				matchedPeopleDetail.userId=Integer.parseInt(key);
				//判断该用户是发起还是参与
				if(t.publish_or_join=='p'){//发起话题
					matchedPeopleDetail.addPulishTopic(t.topicId);
				}
				else if(t.publish_or_join=='j'){
					matchedPeopleDetail.addJoinTopic(t.topicId);
				}
				matchedMap.put(key,matchedPeopleDetail);
			}
		}
		Iterator<MatchedPeopleDetail> it=matchedMap.values().iterator();
		List<MatchedPeopleDetail> temp_list=new ArrayList<MatchedPeopleDetail>();
		while(it.hasNext()){
			MatchedPeopleDetail m=it.next();
			temp_list.add(m);
		}
		return temp_list;
	}
	public static void main(String[] args) {
	TopicManager topicManager =new TopicManagerImpl();
	List<String> topicIdList = new ArrayList<String>();
	topicIdList.add("A9ABC6801C19522A0329450E29B92F0A");
/*	List<Topic> topiclist=topicManager.getTopicListByTopicIdList(topicIdList);
		for(Topic topic:topiclist)
		{
			System.out.println(topic.userId+"==>"+topic.topicName+" ===>"+topic.topicContent);
		}
		TopicModel topicModel = new TopicModelImpl();*/

		TopicModel t=new TopicModelImpl();
		//打印获取的userId
		List<RecommendedTopicPublisher> rl =t.getRecommendedTopicPUblisher(topicIdList);
		System.out.println("打印获取的userId:");
		for(RecommendedTopicPublisher r:rl)
		{
			System.out.println(r.userId+":"+r.topicId);
		}
		
		//测试获取用户id
/*		List<Long> userIdList = new ArrayList<Long>();
		String userId = "3";
		userIdList.add((Long)userId);
		
		UserManager userManager = new UserManagerImpl();
		List<User> userl = userManager.findUserListByUserIdList(userIdList);
		
		if(userl!=null)
		{
			System.out.println(userl.size());
			for(User u:userl)
			{
				System.out.println(u.id);
			}
		}*/
		
		
		/*List<RecommendedTopicPublisher> rl = t.getRecommendedTopicPUblisher("1");
		
		if(rl==null){
			System.out.println("没有推荐");
		}else{
			JSONArray array = t.getJSONArrayFromRecommendedTopicPublisherList(rl);
			System.out.println(array.toString());
			if(array!=null){
				
				System.out.println(array.get(0));
			}
			else{
				System.out.println("array为空");
			}
		for(RecommendedTopicPublisher r:rl)
		{
				System.out.println("用户　id:"+r.userId+"  topicId:"+r.topicId);
				System.out.println("用户名："+r.user.xunta_username+"  话题名称：==>"+r.topic.topicName+ "  话题内容==>"+r.topic.topicContent);
			}
		}*/
	
		
//		Topic t1 = new Topic();
//		t1.setId(1);
//		List<Topic> topicList = new ArrayList<Topic>();
//		topicList.add(t1);
//		TopicModelImpl t1 = new TopicModelImpl();
//		List<RecommendedTopicPublisher> list = t.getRecommendedTopicPUblisherByTopicList(topicList);
//		for (RecommendedTopicPublisher recommendedTopicPublisher : list) {
//			System.out.println("userId : "+recommendedTopicPublisher.getUser().getId());
//			System.out.println("nickname :"+recommendedTopicPublisher.getUser().getNickname());
//		}
		
		
	}
	

	@Override
	public List<RecommendedPeople> getRecommendedPeople(String userId) {
		//3.List<topic> ==>RecommendPeople
		List<RecommendedPeople> recommendedPeopleList =new ArrayList<RecommendedPeople>();
		Map<String,RecommendedPeople> userId_RecommendedPeople_map=new HashMap<String,RecommendedPeople>();
		
		List<Topic> topicList = topicManager.recommendTopics(userId);
		if(topicList==null){return null;}
		
		//遍历每个topic
		List<String> topicIdList =new ArrayList<String>();
		for(Topic topic:topicList)
		{
			topicIdList.add(topic.topicId);
		}
		
		//获取与该话题id对应的话题历史
		List<TopicHistory> topicHistoryList = topicManager.findTopicHistoryByTopicId(topicIdList);
		if(topicHistoryList==null){return null;}
		
		// 遍历
		for(TopicHistory t:topicHistoryList)
		{
			//System.out.println(t.topicId+"  "+t.publish_or_join);
			//遍历每个话题下的成员
			String key = t.authorId;
			if(userId_RecommendedPeople_map.containsKey(key)){//存在直接添加
				RecommendedPeople  recommendedPeople = userId_RecommendedPeople_map.get(key);
				recommendedPeople.setUserId(key);
				recommendedPeople.setLatestTopicHistory(t);
			}else{//不存在要创建
				RecommendedPeople  recommendedPeople=new RecommendedPeople();
				recommendedPeople.userId=key;
				recommendedPeople.setLatestTopicHistory(t);
				userId_RecommendedPeople_map.put(key,recommendedPeople);
			}
		}
		if(userId_RecommendedPeople_map==null){
			return null;
		}
		
		Collection<RecommendedPeople> recommends=userId_RecommendedPeople_map.values();
		Iterator<RecommendedPeople> it =recommends.iterator();
		while(it.hasNext())
		{
			recommendedPeopleList.add(it.next());
		}
		return recommendedPeopleList;
	}

	@Override
	public List<RecommendedTopicPublisher> getRecommendedTopicPUblisher(String userId) {
		List<Topic> topicList = topicManager.recommendTopics(userId);
		if(topicList==null){
			System.out.println("没有推荐的话题");
			return null;
		}
		//如果一个用户对应多个相关话题，保留最新的 userId==>最新的相关话题
		Map<String,Topic> userId_topic_map = new HashMap<String,Topic>();
		Map<Long,User> userId_user_map = new HashMap<Long, User>();
		List<Long> userIdList = new ArrayList<Long>();
		for(Topic t:topicList)
		{
			if(!userId_topic_map.containsKey(t.userId)){
				userId_topic_map.put(t.userId,t);
				userIdList.add(Long.parseLong(t.userId));
			}else{
				Topic topic_c = userId_topic_map.get(t.userId);
				Long c_time = null;
				Long t_time = null;
				try {
					c_time = DateTimeUtils.getCurrentDateTimeObj(topic_c.createTime).getTime();
					t_time = DateTimeUtils.getCurrentDateTimeObj(t.createTime).getTime();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(c_time<t_time){
					userId_topic_map.put(t.userId,t);
				}
			}
			
		}
		
		List<User> userList = userManager.findUserListByUserIdList(userIdList);
		if(userList==null){
			return null;
		}
		
		for(User u:userList)
		{
			userId_user_map.put(u.id,u);
		}
		
		List<RecommendedTopicPublisher> rtpList = new ArrayList<RecommendedTopicPublisher>();
		Collection<Topic> collection_t=userId_topic_map.values();
		Iterator<Topic> it = collection_t.iterator();
		while(it.hasNext()){
			Topic t=it.next();
			RecommendedTopicPublisher rtp =new RecommendedTopicPublisher();
			rtp.setTopic(t);
			rtp.setUser(userId_user_map.get(Long.parseLong(t.userId)));
			rtpList.add(rtp);
		}
		return rtpList;
	}

	@Override
	public List<RecommendedTopicPublisher> getRecommendedTopicPUblisher(List<String> topicIdList) {
		if(topicIdList==null||topicIdList.size()==0){
			return null;
		}
		List<Topic> topicList = topicManager.getTopicListByTopicIdList(topicIdList);

		List<RecommendedTopicPublisher> rtbl = getRecommendedTopicPUblisherByTopicList(topicList);

		return rtbl;
	}

	
	@Override
	public List<RecommendedTopicPublisher> getRecommendedTopicPUblisherByTopicList(List<Topic> topicList) {
		
		TreeMap<Long,Topic> treeMap = new TreeMap<Long,Topic>();
		List<Long> userIdList = new ArrayList<Long>();
		List<RecommendedTopicPublisher> list = new ArrayList<RecommendedTopicPublisher>();
		Long userId;
		
		for (Topic topic : topicList) {
			userId = Long.parseLong(topic.getUserId());
			treeMap.put(userId, topic);
			userIdList.add(userId);
		}
		List<User> userList = userManager.findUserListByUserIdList(userIdList);
		for (Long id : treeMap.keySet()) {
			for (User user : userList) {
				if(user.getId() == id){
					RecommendedTopicPublisher r = new RecommendedTopicPublisher();
					r.setTopic(treeMap.get(id));
					r.setUser(user);
					list.add(r);
				}
			}
		}
		return list;
	}

	@Override
	//JSONObject ==>{userId:xxx,xunta_username:xxxx,userImgUrl:xxx, address:xxxx, sex:xxx, topicId:xxx,topoicName:xxxxx}
	public JSONArray getJSONArrayFromRecommendedTopicPublisherList(List<RecommendedTopicPublisher> RecommTopicPublisherList) {
		JSONArray arrayJson = new JSONArray();
		for (RecommendedTopicPublisher recommendedTopicPublisher : RecommTopicPublisherList) {
			JSONObject json = new JSONObject();
			Long userId = recommendedTopicPublisher.getUser().getId();
			String userName = recommendedTopicPublisher.getUser().getXunta_username();
			String imgUrl = recommendedTopicPublisher.getUser().getImageUrl();
			String address = recommendedTopicPublisher.getUser().getAddress();
			String sex = recommendedTopicPublisher.getUser().getSex();
			String topicId = recommendedTopicPublisher.getTopic().getTopicId();//原getId,为主键自增id,已改为获取md5生成的id
			String topicName = recommendedTopicPublisher.getTopic().getTopicName();
			try {
				json.put("userId", userId);
				json.put("xunta_username", userName);
				json.put("userImgUrl", imgUrl);
				json.put("address", address==null?"保密":address);
				json.put("sex", sex==null?"保密":sex);
				json.put("topicId", topicId);
				json.put("topicName", topicName);
				//print
				arrayJson.add(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		System.out.print(arrayJson.toString());
		return arrayJson;
	}
}