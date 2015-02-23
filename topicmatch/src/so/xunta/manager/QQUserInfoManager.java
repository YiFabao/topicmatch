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
}
