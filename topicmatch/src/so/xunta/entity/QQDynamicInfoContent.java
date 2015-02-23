package so.xunta.entity;

public class QQDynamicInfoContent {
	
	public int id;
	public String qq_openId;//唯一标识
	public String content;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getQq_openId() {
		return qq_openId;
	}
	
	public void setQq_openId(String qq_openId) {
		this.qq_openId = qq_openId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public QQDynamicInfoContent() {
		super();
	}
	
	public QQDynamicInfoContent(String qq_openId, String content) {
		super();
		this.qq_openId = qq_openId;
		this.content = content;
	}
	
	
}
