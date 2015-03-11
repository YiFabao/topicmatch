package so.xunta.topic.entity;

import so.xunta.entity.User;

public class RecommendedTopicPublisher {
	public String userId;
	public String topicId;
	public User user;
	public Topic topic;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		if(user!=null){
			this.userId=String.valueOf(user.id);
		}
		this.user = user;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		if(topic!=null)
		{
			this.topicId= topic.topicId;
		}
		this.topic = topic;
	}
	
	
}
