package so.xunta.manager;

import so.xunta.entity.LoginLog;

public interface UserInfoManager {
	//添加跳过记录
	public void addLoginLog(LoginLog loginLog);
	//查询记录
	public LoginLog findLoginLogByUserId(String userId);
}
