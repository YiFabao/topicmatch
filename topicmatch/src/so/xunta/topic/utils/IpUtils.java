package so.xunta.topic.utils;

import java.util.Random;
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
		if (ipAddress == null) {
			return null;
		}
		// 判断是否是一个点分ip
		Pattern pattern = Pattern
				.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
		Matcher matcher = pattern.matcher(ipAddress);
		if (matcher.matches()) {
			//System.out.println("ip地址库文件位置：" + LocalContext.getIPFileInstallFolder());
			IPSeeker ips = new IPSeeker("QQWry.Dat", LocalContext.getIPFileInstallFolder());
			return ips.getCountry(ipAddress);
		} else {
			System.out.println("获取的ip地址格式不正确:" + ipAddress);
			return null;
		}
	}

	public static void main(String[] args) {
		String aa = IpUtils.getInstance().getCountryByIdAddress("222.73.202.253");
		System.out.println(aa);
	}
	/**
	 * 随机生成国内地址
	 * @return
	 */
	public String getRandomCountryAddress(){
		return IpUtils.getInstance().getCountryByIdAddress(IpUtils.getRandomIp());
	}

	/*
	 * 随机生成国内IP地址
	 */
	public static String getRandomIp() {

		// ip范围
		int[][] range = { { 607649792, 608174079 },// 36.56.0.0-36.63.255.255
				{ 1038614528, 1039007743 },// 61.232.0.0-61.237.255.255
				{ 1783627776, 1784676351 },// 106.80.0.0-106.95.255.255
				{ 2035023872, 2035154943 },// 121.76.0.0-121.77.255.255
				{ 2078801920, 2079064063 },// 123.232.0.0-123.235.255.255
				{ -1950089216, -1948778497 },// 139.196.0.0-139.215.255.255
				{ -1425539072, -1425014785 },// 171.8.0.0-171.15.255.255
				{ -1236271104, -1235419137 },// 182.80.0.0-182.92.255.255
				{ -770113536, -768606209 },// 210.25.0.0-210.47.255.255
				{ -569376768, -564133889 }, // 222.16.0.0-222.95.255.255
		};

		Random rdint = new Random();
		int index = rdint.nextInt(10);
		String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
		return ip;
	}

	/*
	 * 将十进制转换成ip地址
	 */
	public static String num2ip(int ip) {
		int[] b = new int[4];
		String x = "";

		b[0] = (int) ((ip >> 24) & 0xff);
		b[1] = (int) ((ip >> 16) & 0xff);
		b[2] = (int) ((ip >> 8) & 0xff);
		b[3] = (int) (ip & 0xff);
		x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

		return x;
	}
}
