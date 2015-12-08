package so.xunta.entity;

/**
 * @author yifabao
 *
 */
public class WeiboUserInfo {
	public int id;
	public String nickname;
	public String gender;
	public String location;
	public String description;
	public String verified_reason;
	public String tags;
	public String weibo_uid;

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getWeibo_uid() {
		return weibo_uid;
	}
	public void setWeibo_uid(String weibo_uid) {
		this.weibo_uid = weibo_uid;
	}
	public WeiboUserInfo(String nickname, String gender, String location,
			String description, String verified_reason, String tags,
			String weibo_uid) {
		super();
		this.nickname = nickname;
		this.gender = gender;
		this.location = location;
		this.description = description;
		this.verified_reason = verified_reason;
		this.tags = tags;
		this.weibo_uid = weibo_uid;
	}

	
	
}
