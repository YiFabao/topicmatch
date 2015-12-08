package so.xunta.servlet;

import so.xunta.user.info.tencentUserInfo;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.weibo.UserInfoBean;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class TestGetOpenId {
	public static void main(String[] args) {
		String accessToken="72EBF9A675C1ABAA6D9BE2EEF09500D3";
		JSONObject json = tencentUserInfo.get(accessToken);
		
		try {
			System.out.println("openId:"+json.get("nickname"));
			System.out.println("openId:"+json.get("openId"));
			System.out.println(json.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		OpenID o = new OpenID(accessToken);
		String openId="";
		UserInfoBean userInfo=null;

		try {
			openId = o.getUserOpenID();
			System.out.println(openId);
		} catch (QQConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
