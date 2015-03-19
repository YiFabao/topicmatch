package so.xunta.topic.entity;

public class Notification {
	public int id;
	public String topicId;
	public String status;
	public String userName;
	public String topicName;
	public String time;
	public String userId;
	public Notification(String topicId,String status, String userName, String topicName, String time, String userId) {
		super();
		this.status = status;
		this.topicId = topicId;
		this.userName = userName;
		this.topicName = topicName;
		this.time = time;
		this.userId = userId;
	}
	public Notification() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}