package so.xunta.manager;

import so.xunta.entity.WeixinUserInfo;

public interface WeixinUserInfoManager {
	// 添加微信信息
	public void addStaticWeiBoUserInfo(WeixinUserInfo weixinUserInfo);
	
	//根据user表里的weixin_uid到WeixinUserInfo表里查用户微信名
	public String findWeixinNameByWeixinUid(String weixin_uid);
}
