package so.xunta.topic.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import so.xunta.ipseeker.IPSeeker;
import so.xunta.localcontext.LocalContext;

public class IpUtils {
	private IpUtils() {
	};

	private static IpUtils instance = null;

	public static IpUtils getInstance() {
		if (instance == null) {
			instance = new IpUtils();
		}
		return instance;
	}

	// 获取IP所在country
	public String getCountryByIdAddress(String ipAddress) {
		if(ipAddress==null){
			return null;
		}
		//判断是否是一个点分ip
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ipAddress);
		if(matcher.matches()){
			System.out.println("ip地址库文件位置："+LocalContext.getIPFileInstallFolder());
			IPSeeker ips = new IPSeeker("QQWry.Dat", LocalContext.getIPFileInstallFolder());
			return ips.getCountry(ipAddress);
		}
		else{
			System.out.println("获取的ip地址格式不正确:"+ipAddress);
			return null;
		}
	}
	public static void main(String[] args) {
		String aa = IpUtils.getInstance().getCountryByIdAddress("222.73.202.253");
		System.out.println(aa);
	}
}
