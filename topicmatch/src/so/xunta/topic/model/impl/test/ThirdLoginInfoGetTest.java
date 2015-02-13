package so.xunta.topic.model.impl.test;


import java.util.Iterator;

import org.junit.Test;

import so.xunta.user.info.tencentUserInfo;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class ThirdLoginInfoGetTest {
	@Test
	public void testQQthirdLoginInfoGet(){
		JSONObject json = tencentUserInfo.get("72EBF9A675C1ABAA6D9BE2EEF09500D3");
		Iterator it =json.keys();
		while(it.hasNext())
		{
			String var =(String) it.next();
			try {
				String value = (String) json.get(var);
				System.out.println("key:"+var+"   ==>value:"+value);
				if(var=="content")
				{
					Object obj =json.get("content");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//JSONObject json1 = sinaUserInfo.get(String token, String userId);
	}
}
