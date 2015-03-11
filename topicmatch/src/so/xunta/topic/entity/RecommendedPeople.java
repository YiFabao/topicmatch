package so.xunta.topic.entity;

import java.text.ParseException;
import java.util.Date;

import so.xunta.entity.User;
import so.xunta.utils.DateTimeUtils;

public class RecommendedPeople {
	public String userId;
	public String topicHistoryId;
	public User user;
	public Topic topic;
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public TopicHistory latestTopicHistory;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTopicHistoryId() {
		return topicHistoryId;
	}
	public void setTopicHistoryId(String topicHistoryId) {
		this.topicHistoryId = topicHistoryId;
	}
	public TopicHistory getLatestTopicHistory() {
		return latestTopicHistory;
	}
	public void setLatestTopicHistory(TopicHistory latestTopicHistory) {
		if(this.latestTopicHistory==null){
			this.latestTopicHistory = latestTopicHistory;
		}
		else{
			Long timestap1 = 0L;
			try {
				timestap1 = DateTimeUtils.getCurrentDateTimeObj(latestTopicHistory.getDatetime()).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Long timestap2 = 0L;
			try {
				timestap2 = DateTimeUtils.getCurrentDateTimeObj(this.latestTopicHistory.getDatetime()).getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(timestap1 >= timestap2){
				this.latestTopicHistory = latestTopicHistory;
				setTopicHistoryId(String.valueOf(latestTopicHistory.id));
			}
		}
	}

	
	
}
