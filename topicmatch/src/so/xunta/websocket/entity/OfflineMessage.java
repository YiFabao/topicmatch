package so.xunta.websocket.entity;

public class OfflineMessage {
	public Long id;
	public String topicId;
	public Long accepterId;
	public Long count;
	public OfflineMessage(String topicId, Long accepterId, Long count) {
		
		this.topicId = topicId;
		this.accepterId = accepterId;
		this.count = count;
	}
	public OfflineMessage(){
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public Long getAccepterId() {
		return accepterId;
	}
	public void setAccepterId(Long accepterId) {
		this.accepterId = accepterId;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
}