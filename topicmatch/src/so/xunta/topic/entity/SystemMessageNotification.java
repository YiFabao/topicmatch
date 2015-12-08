package so.xunta.topic.entity;

public class SystemMessageNotification {
	public int id;
	public String status;
	public String userId;
	public String message;
	public SystemMessageNotification(String status, String userId, String message) {
		super();
		this.status = status;
		this.userId = userId;
		this.message = message;
	}
	public SystemMessageNotification() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}