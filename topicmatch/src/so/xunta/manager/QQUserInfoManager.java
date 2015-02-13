package so.xunta.manager;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;

public interface QQUserInfoManager {
	//添加qq的静态基本字段
	public void addStaticQQUserInfo(QQUserInfo qquserInfo);
	
	//添加qq动态基本字段
	public void addDynamicQQInfoContent(QQDynamicInfoContent qqdynamicInfoContent);
}
