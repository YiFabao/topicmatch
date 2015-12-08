package so.xunta.manager;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;

public interface QQUserInfoManager {
	//添加qq的静态基本字段
	public void addStaticQQUserInfo(QQUserInfo qquserInfo);
	
	//添加qq动态基本字段
	public void addDynamicQQInfoContent(QQDynamicInfoContent qqdynamicInfoContent);
	
	//根据openId查询用户最近一条微博
	public String findLatestQQContentByOpenId(String openId);
	
	//根据user表中获取的qq_openid到qquser_info表中找qq昵称
	public String findQQNameByOpenid(String qq_openid);
	
}
