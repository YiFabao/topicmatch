package so.xunta.topic.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.topic.entity.MatchedPeopleDetail;
import so.xunta.topic.entity.RecommendedPeople;
import so.xunta.topic.entity.RecommendedTopicPublisher;
import so.xunta.topic.entity.Topic;

public interface TopicModel {
	//用户参与话题
	public void joinTopic(HttpServletRequest request, HttpServletResponse response,String userId,String topicId);

	//获取匹配的话题对应的人的聚合后的数据List<MatchedPeopleDetail> 
	//userId,该人发了多外相关的话题，参与了多个相关话题善
	public List<MatchedPeopleDetail> matchedPeopleDetaiList(List<Topic> topicList);
	
	//传给一个登录用户的id
	//返回给推荐的话题给该用户 具体信息封装成了RecommendedPeople　这个包括与话题相关的发起人和参与人
	public List<RecommendedPeople> getRecommendedPeople(String userId);
	
	//userId ==>　List<RecommendedTopicPublisher>
	List<RecommendedTopicPublisher> getRecommendedTopicPUblisher(String userId);
	
	List<RecommendedTopicPublisher> getRecommendedTopicPUblisher(List<String> topicIdList);
	List<RecommendedTopicPublisher> getRecommendedTopicPUblisherByTopicList(List<Topic> topicList);
	
	/**
	 * List<RecommendedTopicPublisher> ==> JSONArray<JSONObject> ;JSONObject ==>{userId:xxx,xunta_username:xxxx,address:xxxx,}
	 */
	
	
}
