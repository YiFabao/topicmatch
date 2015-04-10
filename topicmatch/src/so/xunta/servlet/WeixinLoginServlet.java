package so.xunta.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import so.xunta.entity.LoginLog;
import so.xunta.entity.User;
import so.xunta.entity.WeiboDynamicInfoContent;
import so.xunta.entity.WeiboUserInfo;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserInfoManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserInfoManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.utils.DateTimeUtils;

@WebServlet("/WeixinLoginServlet")
public class WeixinLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager userManager = new UserManagerImpl();
	TagsManager tagsManager = new TagsManagerImpl();
	UserInfoManager userInfoManager = new UserInfoManagerImpl();

	public WeixinLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取code
		String weixin_code = request.getParameter("weixin_code");
		// 通过code获取token
		String codeToToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx0ad98a24caca02ca&secret=d967dc101ad34ff81062309e2be96b46&code="
				+ weixin_code + "&grant_type=authorization_code";
		String weiXinInfo = httpclientReq(codeToToken);
		JSONObject weiXinInfoJson = JSONObject.fromObject(weiXinInfo);
		String accessToken = weiXinInfoJson.get("access_token").toString();
		String openid = weiXinInfoJson.get("openid").toString();
		// 通过token 换取 userInfo
		String userInfo = httpclientReq("https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken+ "&openid=" + openid + "");
		JSONObject userInfoJson = JSONObject.fromObject(userInfo);
		String nickname = new String(userInfoJson.get("nickname").toString().getBytes("ISO-8859-1"), "UTF-8");
		String sex = userInfoJson.get("sex").toString();
		String province = userInfoJson.get("province").toString();
		String city = userInfoJson.get("city").toString();
		String country = userInfoJson.get("country").toString();
		//String headImgUrl = userInfoJson.get("headimgurl").toString();
		String headImgUrl = "user-pic2.jpg";
		if (sex.equals("1")) {
			sex = "男";
		} else {
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
//		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().write(
//				"accessToken ==>  " + accessToken + "</br>openid ==>  " + openid + "</br>nickname ==>  " + nickname
//						+ "</br>sex ==>  " + sex + "</br>province ==>  " + province + "</br>city ==>  " + city
//						+ "</br>country ==>  " + country + "</br>headImgUrl ==>  " + headImgUrl);

		// 判断uid是否为空
		if (openid.equals("")) {
			response.getWriter().write("openid为空");
			return;
		}

		// 查询数据库中是否存在uid
		User user = userManager.findUserByWeixinUid(openid);
		if (user == null) {
			System.out.println("数据库中不存在该微博uid");
			// 用户没有绑定账号
			user = new User(nickname,"", "",openid,accessToken,"", "", "", "", new Date(), DateTimeUtils.getCurrentTimeStr(), headImgUrl);
			// 添加用户表
			userManager.addUser(user);

			// 将用户保存到sessoin范围
			request.getSession().setAttribute("user", user);
			//准备第三方账户名显示
			request.getSession().setAttribute("thirdParty", "微信-昵称");
			String weixin_uid = user.getWeixin_uid();
			if(weixin_uid!=null&&!"".equals(weixin_uid.trim()))
			{
				String weixin_name = "昵称";
				//weixin_name = (new WeixinUserInfoManagerImpl()).findWeixinNameByWeixinUid(weixin_uid.trim());
				request.getSession().setAttribute("thirdParty", "微信-"+weixin_name);
			}
			// TODO 判断是否有标签
			if(!tagsManager.checkUserTagIsEmpty(user.id)){//有标签
				System.out.println("有标签");
				//判断是否绑定本地账号
				boolean bindAccount = false;
				LoginLog ll = userInfoManager.findLoginLogByUserId(String.valueOf(user.id));
				if(ll==null)
				{
					System.out.println("没有跳过");
					bindAccount = true;
				}else{
					if(ll.getBind_account_step()==0)
					{
						System.out.println("跳过");
						bindAccount = false;
					}else{
						System.out.println("没有跳过");
						bindAccount = true;
					}
				}
			
				if(bindAccount&&(user.xunta_username==null||"".equals(user.xunta_username.trim())||user.password==null||"".equals(user.password))){
					try {
						System.out.println("用户名没有绑定并且没有绑定账号");
						response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login4.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						System.out.println("用户跳或绑定了账号");
						response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{//没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login3.jsp");
			}
		} else {
			System.out.println("数据库中存在该微博uid");
			// 更新accessToken
			user.setWeibo_accessToken(accessToken);
			userManager.updateUser(user);

			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
			//准备第三方账户名显示
			request.getSession().setAttribute("thirdParty", "微信-昵称");
			String weixin_uid = user.getWeixin_uid();
			if(weixin_uid!=null&&!"".equals(weixin_uid.trim()))
			{
				String weixin_name = "昵称";
				//weixin_name = (new WeixinUserInfoManagerImpl()).findWeixinNameByWeixinUid(weixin_uid.trim());
				request.getSession().setAttribute("thirdParty", "微信-"+weixin_name);
			}
			// TODO 判断是否有标签
			if(!tagsManager.checkUserTagIsEmpty(user.id)){//有标签
				System.out.println("有标签");
				//判断是否绑定本地账号
				boolean bindAccount = false;
				LoginLog ll = userInfoManager.findLoginLogByUserId(String.valueOf(user.id));
				if(ll==null)
				{
					System.out.println("没有跳过");
					bindAccount = true;
				}else{
					if(ll.getBind_account_step()==0)
					{
						System.out.println("跳过");
						bindAccount = false;
					}else{
						System.out.println("没有跳过");
						bindAccount = true;
					}
				}
			
				if(bindAccount&&(user.xunta_username==null||"".equals(user.xunta_username.trim())||user.password==null||"".equals(user.password))){
					try {
						System.out.println("用户名没有绑定并且没有绑定账号");
						response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login4.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					try {
						System.out.println("用户跳或绑定了账号");
						response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}else{//没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/login3.jsp");
			}
		}

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
