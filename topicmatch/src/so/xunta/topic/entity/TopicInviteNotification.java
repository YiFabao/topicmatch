package so.xunta.topic.entity;

public class TopicInviteNotification {
	public int id;
	public String topicId;
	public String status;
	public String fromUserName;
	public String topicName;
	public String time;
	public String toUserId;
	public String fromUserId;
	public String toUserName;
	public TopicInviteNotification(String topicId, String status, String fromUserName, String topicName, String time,
			String toUserId, String fromUserId, String toUserName) {
		super();
		this.topicId = topicId;
		this.status = status;
		this.fromUserName = fromUserName;
		this.topicName = topicName;
		this.time = time;
		this.toUserId = toUserId;
		this.fromUserId = fromUserId;
		this.toUserName = toUserName;
	}
	public TopicInviteNotification() {
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
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
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
	public String getToUserId() {
		return toUserId;
	}
	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}
	public String getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
}