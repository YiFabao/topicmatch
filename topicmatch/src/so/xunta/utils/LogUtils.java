package so.xunta.utils;

import javax.servlet.http.HttpServletRequest;

import so.xunta.entity.UserTrackLog;
import so.xunta.manager.LogManager;
import so.xunta.manager.impl.LogManagerImpl;
import so.xunta.topic.utils.IpUtils;

public class LogUtils {
	public void traceLog(HttpServletRequest request,String action){
		String ipaAddress = request.getRemoteAddr();
		String country = IpUtils.getInstance().getCountryByIdAddress(ipaAddress);
		LogManager logManager = new LogManagerImpl();
		UserTrackLog trackLog = new UserTrackLog(ipaAddress,country,action,DateTimeUtils.getCurrentTimeStr());
		logManager.tracklog(trackLog);
	}
}
