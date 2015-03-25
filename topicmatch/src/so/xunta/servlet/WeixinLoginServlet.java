package so.xunta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

@WebServlet("/WeixinLoginServlet")
public class WeixinLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WeixinLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取code
		String weixin_code = request.getParameter("weixin_code");
		//通过code获取token
		String codeToToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0ad98a24caca02ca&secret=d967dc101ad34ff81062309e2be96b46&code="+weixin_code+"&grant_type=authorization_code";
		String weiXinInfo = httpclientReq(codeToToken);
		JSONObject weiXinInfoJson = JSONObject.fromObject(weiXinInfo);
		String accessToken = weiXinInfoJson.get("access_token").toString();
		String openid = weiXinInfoJson.get("openid").toString();
		//通过token 换取 userInfo
		String userInfo = httpclientReq("https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid+"");
		JSONObject userInfoJson = JSONObject.fromObject(userInfo);
		String nickname = new String(userInfoJson.get("nickname").toString().getBytes("ISO-8859-1"), "UTF-8");
		String sex = userInfoJson.get("sex").toString();
		String province = userInfoJson.get("province").toString();
		String city = userInfoJson.get("city").toString();
		String country = userInfoJson.get("country").toString();
		String headImgUrl = userInfoJson.get("headimgurl").toString();
		if(sex.equals("1")){
			sex = "男";
		}else{
			sex = "女";
		}
		System.out.println("accessToken  :  " + accessToken);
		System.out.println("openid  : " + openid);
		System.out.println("nickname  :  " + nickname);
		System.out.println("sex  : " + sex);
		System.out.println("province : " + province);
		System.out.println("city : " + city);
		System.out.println("country  ：  " + country);
		System.out.println("headImgUrl  :  " + headImgUrl);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write( "accessToken" + accessToken +
									"</br>openid" + openid +
									"</br>nickname" + nickname +
									"</br>sex" + sex +
									"</br>province" + province +
									"</br>city" + city +
									"</br>country" + country +
									"</br>headImgUrl" + headImgUrl);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doGet(request, response);
	}

	public static String httpclientReq(String url) {
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url); // 创建GET方法的实
		String response = null;
		try {
			int statusCode = httpClient.executeMethod(getMethod); // 执行getMethod
			System.out.println("statusCode  :  " + statusCode);
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
