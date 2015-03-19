package so.xunta.websocket.entity;

public class HistoryMessage {
	public long id;//主键id
	public int status;
	public String topicId;
	public Long messageId;
	public int senderId;
	public String accepterId;
	public String content;
	public String dateAndTime;
	public String nickname;
	public int date;
	public int time;
	
	public HistoryMessage(int status, String topicId, Long messageId, int senderId, String accepterId,
			String content, String dateAndTime, String nickname, int date, int time) {
		super();
		this.status = status;
		this.topicId = topicId;
		this.messageId = messageId;
		this.senderId = senderId;
		this.accepterId = accepterId;
		this.content = content;
		this.dateAndTime = dateAndTime;
		this.nickname = nickname;
		this.date = date;
		this.time = time;
	}
	
	public HistoryMessage(){
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getAccepterId() {
		return accepterId;
	}

	public void setAccepterId(String accepterId) {
		this.accepterId = accepterId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}
