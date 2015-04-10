package so.xunta.entity;

import java.util.Date;

public class User {
	public long id;//用户全局标识；主键
	public String xunta_username;
	public String sex;//m：男　 w:女
	public String nickname;
	public String password;
	public String address;
	public String birthday;
	public String email;
	public String qq_openId;
	public String qq_accessToken;
	public String weibo_uid;
	public String weibo_accessToken;
	public String weixin_uid;
	public String weixin_accessToken;
	public Date createTime; //寻他账号创建时间
	public String latestLoginTime;//最后一次登录时间
	public String imageUrl;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getXunta_username() {
		return xunta_username;
	}
	public void setXunta_username(String xuntaUsername) {
		xunta_username = xuntaUsername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq_openId() {
		return qq_openId;
	}
	public void setQq_openId(String qqOpenId) {
		qq_openId = qqOpenId;
	}
	public String getQq_accessToken() {
		return qq_accessToken;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setQq_accessToken(String qqAccessToken) {
		qq_accessToken = qqAccessToken;
	}
	

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWeibo_accessToken() {
		return weibo_accessToken;
	}
	public void setWeibo_accessToken(String weiboAccessToken) {
		weibo_accessToken = weiboAccessToken;
	}
	
	public String getWeibo_uid() {
		return weibo_uid;
	}
	public void setWeibo_uid(String weiboUid) {
		weibo_uid = weiboUid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLatestLoginTime() {
		return latestLoginTime;
	}
	public void setLatestLoginTime(String latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}
	public String getWeixin_uid() {
		return weixin_uid;
	}
	public void setWeixin_uid(String weixin_uid) {
		this.weixin_uid = weixin_uid;
	}
	public String getWeixin_accessToken() {
		return weixin_accessToken;
	}
	public void setWeixin_accessToken(String weixin_accessToken) {
		this.weixin_accessToken = weixin_accessToken;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String xunta_username, String sex, String nickname, String password, String address,
			String birthday, String email, String qq_openId, String qq_accessToken, String weibo_uid,
			String weibo_accessToken, String weixin_uid, String weixin_accessToken, Date createTime,
			String latestLoginTime, String imageUrl) {
		super();
		System.out.println("==>  "+weixin_uid);
		System.out.println("==>  "+weixin_accessToken);
		System.out.println("==>  "+weibo_uid);
		System.out.println("==>  "+weibo_accessToken);
		this.xunta_username = xunta_username;
		this.sex = sex;
		this.nickname = nickname;
		this.password = password;
		this.address = address;
		this.birthday = birthday;
		this.email = email;
		this.qq_openId = qq_openId;
		this.qq_accessToken = qq_accessToken;
		this.weibo_uid = weibo_uid;
		this.weibo_accessToken = weibo_accessToken;
		this.weixin_uid = weixin_uid;
		this.weixin_accessToken = weixin_accessToken;
		this.createTime = createTime;
		this.latestLoginTime = latestLoginTime;
		this.imageUrl = imageUrl;
	}
	public User(String xunta_username,String nickname,String address,String birthday ,String password, String email, String weixin_openId, String weixin_accessToken, String qq_openId, String qq_accessToken, String weibo_uid, String weibo_accessToken, Date createTime,
			String latestLoginTime,String imageUrl) {
		super();
		new User(xunta_username, xunta_username, nickname, password, address, birthday, email, qq_openId, qq_accessToken, weibo_uid, weibo_accessToken, weixin_openId, weixin_accessToken, createTime, latestLoginTime, imageUrl);
		this.setNickname(nickname);
		this.setAddress(address);
		this.setBirthday(birthday);
		
	}
}