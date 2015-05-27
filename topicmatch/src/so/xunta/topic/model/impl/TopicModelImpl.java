package so.xunta.topic.model.impl;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.catalina.websocket.WsOutbound;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

import so.xunta.entity.Tag;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.entity.MatchedPeopleDetail;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.Topic;
import so.xunta.topic.entity.TopicGroup;
import so.xunta.topic.entity.TopicHistory;
import so.xunta.topic.model.TopicManager;
import so.xunta.topic.model.TopicModel;
import so.xunta.topic.utils.HighlightUtils;
import so.xunta.utils.DateTimeUtils;
import so.xunta.utils.LogUtils;
import so.xunta.websocket.WSMessageControl;
import so.xunta.websocket.WSSessionConnectControl;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class TopicModelImpl implements TopicModel{
	
	TopicManager topicManager = new TopicManagerImpl();
	UserManager userManager = new UserManagerImpl();
	LogUtils logutil = new LogUtils();
	
	@Override
	public void joinTopic(HttpServletRequest request, HttpServletResponse response,String userId, String topicId) {
		//根据topicId 查询出Topic
		Topic topic = topicManager.findTopicByTopicId(topicId);
		
		//判断topicId是否为导入的话题,如果是则发送消息到指定id的用户
		net.sf.json.JSONObject jo = new net.sf.json.JSONObject();
		jo.put("sys_info",topic.userName);
		try {
			WsOutbound ws = WSSessionConnectControl.getWindowConnect(Integer.valueOf(403));
			Set<Integer> keys = WSSessionConnectControl.sessionConnectControl.keySet();
			for(Integer i :keys)
			{
				System.out.println(i);
			}
			if(ws!=null){
				ws.writeTextMessage(CharBuffer.wrap(jo.toString()));
			}else{
				System.out.println("ws　is null");
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		logutil.traceLog(request, "参与话题:"+topic.getTopicName());
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
			//System.out.println(memUser.nickname);
		}
		
		//将　发起人　Topic 及　用户列表 保存到request范围
/*		request.setAttribute("topic",topic);
		request.setAttribute("publisher",publisher);
		request.setAttribute("memberList",memberList);*/
		
		JSONObject user_p = new JSONObject();
		try {
			user_p.put("userId",userId);
			user_p.put("userName",publisher.nickname);
			user_p.put("imageUrl",publisher.imageUrl);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		JSONObject topic_json=new JSONObject();
		try {
			topic_json.put("topicId",topicId);
			topic_json.put("topicTitle",topic.topicName);
			topic_json.put("topicContent",topic.topicContent);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		JSONArray memberList_json = new JSONArray();
		for(User u:memberList){
			try {
				JSONObject json = new JSONObject();
				json.put("userId",u.id);
				json.put("userName", u.nickname);
				json.put("imageUrl",u.imageUrl);
				memberList_json.add(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		//System.out.println(memberList_json.toString());
		
		JSONObject all = new JSONObject();
		try {
			all.put("user_p",user_p);
			all.put("topic",topic_json);
			all.put("memberList",memberList_json);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		//System.out.println(all.toString());
		try {
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(all.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
			//System.out.println("话题成员id:"+key);
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
		TopicModel t = new TopicModelImpl();
/*			
		List<RecommendedTopicPublisher> l = t.getRecommendedTopicPUblisher("2");
		for(RecommendedTopicPublisher r:l)
		{
			System.out.println(r.userId+":"+r.topic.topicName);
		}*/
		List<String> topicIdList = new ArrayList<String>();
		
		topicIdList.add("8803B6DD681B682664EAF5544B8A5DF8");
		topicIdList.add("02CB369B6322BB7330EDACC78F855F9B");
		topicIdList.add("2DD4860016608FCA97CC8B7FD30B6963");
		List<RecommendedTopicPublisher> l = t.getRecommendedTopicPUblisher(topicIdList);
		for(RecommendedTopicPublisher r:l)
		{
			//System.out.println(r.topicId+"===="+r.userId);
		}
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
		return this.getRecommendedTopicPUblisherByTopicList(topicList);
		
	}
	


	@Override
	public List<RecommendedTopicPublisher> getRecommendedTopicPUblisher(List<String> topicIdList) {
		if(topicIdList==null||topicIdList.size()==0){
			return null;
		}
		List<Topic> topicList = topicManager.getTopicListByTopicIdList(topicIdList);
		
		//System.out.println(topicList.size());

		List<RecommendedTopicPublisher> rtbl = getRecommendedTopicPUblisherByTopicList(topicList);

		return rtbl;
	}

	
	@Override
	public List<RecommendedTopicPublisher> getRecommendedTopicPUblisherByTopicList(List<Topic> topicList) {
		
		/*TreeMap<Long,Topic> treeMap = new TreeMap<Long,Topic>();
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
		}*/
		//如果一个用户对应多个相关话题，保留最新的 userId==>最新的相关话题 by fabaoyi
		//<userId,topicId> 用于关联Topic和User,这个是有序的
		class SortedKeyValue{String key;String value;public SortedKeyValue(String key, String value) {this.key = key;this.value = value;}};
		List<SortedKeyValue> topicId_userId_list = new ArrayList<SortedKeyValue>();
		//<userId,User>
		Map<Long,User> userId_user_map = new HashMap<Long, User>();
		//<topicId,Topic>
		Map<String,Topic> topicId_topic_map = new HashMap<String,Topic>();
		
		
		//List<userId> 
		List<Long> userIdList = new ArrayList<Long>();
		for(Topic t:topicList)
		{
			userIdList.add(Long.parseLong(t.userId));
			topicId_userId_list.add(new SortedKeyValue(t.topicId,t.userId));
			topicId_topic_map.put(t.topicId,t);
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
		
		for(SortedKeyValue s:topicId_userId_list){
			RecommendedTopicPublisher rtp =new RecommendedTopicPublisher();
			rtp.setUser(userId_user_map.get(Long.parseLong(s.value)));
			rtp.setTopic(topicId_topic_map.get(s.key));
			rtpList.add(rtp);
		}
		return rtpList;
	}

	@Override
	//JSONObject ==>{userId:xxx,xunta_username:xxxx,userImgUrl:xxx, address:xxxx, sex:xxx, topicId:xxx,topoicName:xxxxx}
	public JSONArray getJSONArrayFromRecommendedTopicPublisherList(List<RecommendedTopicPublisher> RecommTopicPublisherList,long uid) {
		JSONArray arrayJson = new JSONArray();
		/**
		 * 徐永
		 *发宝: 在红色矩形框的地方, 把最新回复的内容加上去, 用""括上. 比如: 这到底是干嘛的?, 
		 *我回复了"这是一个兴趣匹配社交工具". 那就在   这到底是干嘛的?  下面把 "这是一个兴趣匹配社交工具" 附在下面.
		 */
		//在话题表添加一个lastResMsg字段
		//查询用户标签
		List<Tag> tagsList = new TagsManagerImpl().findAllTagsByUserId(uid);
		for (RecommendedTopicPublisher recommendedTopicPublisher : RecommTopicPublisherList) {
			JSONObject json = new JSONObject();
			Long userId = recommendedTopicPublisher.getUser().getId();
			String nickname = recommendedTopicPublisher.getUser().nickname;
			String imgUrl = recommendedTopicPublisher.getUser().getImageUrl();
			String address = recommendedTopicPublisher.getUser().getAddress();
			String sex = recommendedTopicPublisher.getUser().getSex();
			String topicId = recommendedTopicPublisher.getTopic().getTopicId();//原getId,为主键自增id,已改为获取md5生成的id
			String topicName = recommendedTopicPublisher.getTopic().getTopicName();
			String lastResMsg = recommendedTopicPublisher.getTopic().getLastResMsg();
			
			try {
				String topicName_t= HighlightUtils.getHighlightContentByInput(tagsList, topicName);
				if(topicName_t!=null){
					topicName = topicName_t;
				}
			} catch (IOException | InvalidTokenOffsetsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//System.out.println("高亮的topicName:"+topicName);
			try {
				json.put("userId", userId);
				json.put("nickname", nickname);
				json.put("userImgUrl", imgUrl);
				json.put("address", address==null?"保密":address);
				json.put("sex", sex==null?"保密":sex);
				json.put("topicId", topicId);
				//json.put("topicName", topicName);//话题要高亮显示,其中包含匹配时包含用户标签的词
			
				json.put("topicName",topicName);
				json.put("lastResMsg",lastResMsg);
				
		
				//System.out.println(json.get("topicName"));
				//System.out.println("json:"+json.toString());
				arrayJson.add(json.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		}
		//System.out.print(arrayJson.toString());
		return arrayJson;
	}
}