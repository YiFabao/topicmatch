package so.xunta.manager;

import so.xunta.entity.WeiboDynamicInfoContent;
import so.xunta.entity.WeiboUserInfo;

public interface WeiboUserInfoManager {
	//添加weibo的静态基本字段
	public void addStaticWeiBoUserInfo(WeiboUserInfo weiboUserInfo);
	
	//添加qq动态基本字段
	public void addDynamicWeiboInfoContent(WeiboDynamicInfoContent weiboDynamicInfoContent);
	
	//根据openId查询用户最近一条微博
	public String findLatestWeiContentByOpenId(String weibo_uid);
	
	//保存weiboContent 和  weiboUserId
	public void addWeiboContentAndWeiboUserId(WeiboDynamicInfoContent weiboDynamicInfoContent);
}
