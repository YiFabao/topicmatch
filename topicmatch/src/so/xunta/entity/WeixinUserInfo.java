package so.xunta.entity;

/**
 * @author fangjiayu
 *
 */
public class WeixinUserInfo {
	public int id;
	public String nickname;
	public String gender;
	public String location;
	public String description;
	public String verified_reason;
	public String tags;
	public String weixin_uid;

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
		return weixin_uid;
	}
	public void setWeibo_uid(String weibo_uid) {
		this.weixin_uid = weibo_uid;
	}
	public WeixinUserInfo(String nickname, String gender, String location,
			String description, String verified_reason, String tags,
			String weixin_uid) {
		super();
		this.nickname = nickname;
		this.gender = gender;
		this.location = location;
		this.description = description;
		this.verified_reason = verified_reason;
		this.tags = tags;
		this.weixin_uid = weixin_uid;
	}

	
	
}
