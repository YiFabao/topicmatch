package so.xunta.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class TextFang {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String codeToToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0ad98a24caca02ca&secret=d967dc101ad34ff81062309e2be96b46&code=031a24012420cbddecdff62f0f09296a&grant_type=authorization_code";
		
		String weiXinInfo = httpclientReq(codeToToken);
		
		JSONObject weiXinInfoJson = JSONObject.fromObject(weiXinInfo);
		String accessToken = weiXinInfoJson.get("access_token").toString();
		String openid = weiXinInfoJson.get("openid").toString();
		
		String userInfo = httpclientReq("https://api.weixin.qq.com/sns/userinfo?access_token=OezXcEiiBSKSxW0eoylIeD0qC1Eaj3Bh6McXMaKR7rr8gyXzdi27Srr8DJxzQQcghZXOI_0p1cgktpkC2T7VrV0Dg5U4cIOJBt_lMEaLjTd85MbraukCzMP9MQKAmoU7OfF2K6G8XQZ29PtWG-TvXw&openid=oE_4kuN7YO8y94YNGWl2D6k0_e4k");
		
		JSONObject userInfoJson = JSONObject.fromObject(userInfo);
		String nickname = new String(userInfoJson.get("nickname").toString().getBytes("ISO-8859-1"), "UTF-8");
		String sex = userInfoJson.get("sex").toString();
		String province = userInfoJson.get("province").toString();
		String city = userInfoJson.get("city").toString();
		String country = userInfoJson.get("country").toString();
		String headImgUrl = userInfoJson.get("headimgurl").toString();
		
		System.out.println("accessToken  :  "+accessToken);
		System.out.println("openid  : "+openid);
		System.out.println("nickname  :  "+nickname);
		System.out.println("sex  : "+sex);
		System.out.println("province : "+ province);
		System.out.println("city : "+city);
		System.out.println("country  ：  " + country);
		System.out.println("headImgUrl  :  "+headImgUrl);
		
	}
	
	public static String httpclientReq(String url){
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url); // 创建GET方法的实
		String response = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod); // 执行getMethod
			System.out.println("statusCode  :  "+statusCode);
			response = getMethod.getResponseBodyAsString(); // 读取服务器返回的页面代码，这里用的是字符读法
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!  发生致命的异常，可能是协议不对或者返回的内容有问题"); // 发生致命的异常，可能是协议不对或者返回的内容有问题
			e.printStackTrace();
		} catch (IOException e) { // 发生网络异常
			e.printStackTrace();
		} finally { // 释放连接
			getMethod.releaseConnection();
		}
		return response;
	}
}
