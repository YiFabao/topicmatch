package so.xunta.entity;

public class QQUserInfo {
	public int id;
	public String qq_openId;//唯一标识
	public String nickname;//昵 称
	public String gender;//性别
	public String location;//用户所在地
	public String description;//描述
	public String verified_reason;//认证原因
	public String tags;//标签
	
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVerified_reason() {
		return verified_reason;
	}
	public void setVerified_reason(String verified_reason) {
		this.verified_reason = verified_reason;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public QQUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QQUserInfo(String qq_openId, String nickname, String gender, String location, String description, String verified_reason, String tags) {
		super();
		this.qq_openId = qq_openId;
		this.nickname = nickname;
		this.gender = gender;
		this.location = location;
		this.description = description;
		this.verified_reason = verified_reason;
		this.tags = tags;
	}
	
	
}
