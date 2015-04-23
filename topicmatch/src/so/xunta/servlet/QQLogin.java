package so.xunta.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import so.xunta.entity.LoginLog;
import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;
import so.xunta.entity.User;
import so.xunta.localcontext.LocalContext;
import so.xunta.manager.QQUserInfoManager;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserInfoManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.QQUserInfoManagerImpl;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserInfoManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.utils.IpUtils;
import so.xunta.user.info.tencentUserInfo;
import so.xunta.utils.DateTimeUtils;
import so.xunta.utils.Io;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class QQLogin extends HttpServlet {
	UserManager userManager = new UserManagerImpl();
	QQUserInfoManager qquerinfomanager = new QQUserInfoManagerImpl();
	TagsManager tagsManager = new TagsManagerImpl();
	UserInfoManager userInfoManager = new UserInfoManagerImpl();

	/**
	 * Constructor of the object.
	 */
	public QQLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		String accessToken = request.getParameter("access_token");// 获取参数accessToken
		System.out.println("获取参数accessToken:" + accessToken);
		// 非空判断
		if (accessToken == null && "".equals(accessToken)) {
			System.out.println("获取参数accessToken为空:" + accessToken);
			response.getWriter().write("登录失败,请重新登录");
			response.flushBuffer();
			return;
		}
		// 获取用户的基本信息　
		QQUserInfo qquserInfo = null;// 用户的基本信息 昵称,性别,所在地,描述,认证原因,标签
		QQDynamicInfoContent qqdynamicContent = null;// 动态内容,就是用户的微博　
		System.out.println("开始调用彬彬的获取qq用户的信息..");
		JSONObject json = tencentUserInfo.get(accessToken);
		String openId = "";
		String imageUrl = "";
		String nickname = "";
		String gender = "";
		String location = "";
		String description= "";
		String verified_reason= "";
		String tags= "";
		try {
			nickname = (String) json.get("nickname");
			gender = (String) json.get("gender");
			location = (String) json.get("location");
			description = (String) json.get("description");
			verified_reason = (String) json.get("verified_reason");
			tags = (String) json.get("tags");
			openId = (String) json.get("userId");
			System.out.println("openId:" + openId);
			//获取QQ头像并保存本地
			imageUrl = (String) json.get("image");
			System.out.println("imageUrl ====>  " + imageUrl);
			URL url = new URL(imageUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			String path =LocalContext.getPicPath();
			String newImageName="QQuser_"+openId+"_"+(new Date().getTime())+".jpg";
			FileUtils.copyInputStreamToFile(conn.getInputStream(), new File(path + "/" + newImageName));
			imageUrl = newImageName;
			//获取QQ头像并保存本地
			qquserInfo = new QQUserInfo(openId, nickname, gender, location, description, verified_reason, tags);
			String content = (String) json.get("content");
			qqdynamicContent = new QQDynamicInfoContent(openId, content);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 1.获取token
		// 2.调用接口，获取基本信息
		// 3.查询基本信息是否存在，不存在就添加基本信息，添加动态内容; 存在 就查询动态内容跟前一次的动态内容是否相同，不同则添加动态内容
		// 4.将用户保存在session范围
		// 5.跳转到首页
		catch (Exception e) {
			e.printStackTrace();
		}

		// 查询数据库中是否存在openid
		User user = userManager.findUserbyQQOpenId(openId);
		System.out.println(qquserInfo.getNickname() + "登录");
		

		System.out.println("查询用户是否为空:" + user);
		String ipaAddress = request.getRemoteAddr();
		System.out.println("ip地址:"+ipaAddress+"  城市:"+IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
		if (user == null)// 基本信息不存在
		{
			// 用户没有绑定账号
			user = new User("", gender, nickname, "", "", "", "", openId, accessToken, "", "", "", "", new Date(), DateTimeUtils.getCurrentTimeStr(), imageUrl);
			// 获取ip
			//String ipaAddress = request.getRemoteAddr();
			System.out.println("ip地址:"+ipaAddress+"  城市:"+IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
			user.setAddress(IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
			// 添加用户表
			userManager.addUser(user);
			// 添加qq用户的基本信息
			qquerinfomanager.addStaticQQUserInfo(qquserInfo);
			// 添加qq用户动态内容信息　
			qquerinfomanager.addDynamicQQInfoContent(qqdynamicContent);
			// 将服户保存到sessoin范围
			request.getSession().setAttribute("user", user);
			//准备第三方账户名显示
			request.getSession().setAttribute("thirdParty", "QQ-昵称");
			String qq_openid = user.getQq_openId();
			if(qq_openid!=null&&!"".equals(qq_openid.trim()))
			{
				String qq_name = "昵称";
				qq_name = qquerinfomanager.findQQNameByOpenid(qq_openid.trim());
				request.getSession().setAttribute("thirdParty", "QQ-"+qq_name);
			}
			// TODO 判断是否有标签
			if (!tagsManager.checkUserTagIsEmpty(user.id)) {// 有标签
				System.out.println("有标签");
				boolean bindAccount = false;
				LoginLog ll = userInfoManager.findLoginLogByUserId(String.valueOf(user.id));
				if (ll == null) {
					System.out.println("没有跳过");
					bindAccount = true;
				} else {
					if (ll.getBind_account_step() == 0) {
						System.out.println("跳过");
						bindAccount = false;
					} else {
						System.out.println("没有跳过");
						bindAccount = true;
					}
				}

				if (bindAccount
						&& (user.xunta_username == null || "".equals(user.xunta_username.trim())
								|| user.password == null || "".equals(user.password))) {
					try {
						System.out.println("用户名没有绑定并且没有绑定账号");
						response.sendRedirect(request.getContextPath() + "/jsp/xunta_user/login4.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						System.out.println("用户跳或绑定了账号");
						response.sendRedirect(request.getContextPath() + "/jsp/topic/index.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {// 没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath() + "/jsp/xunta_user/login3.jsp");
			}
		} else// 用户基本信息存在
		{
			// 登录成功
			System.out.println(user.nickname+"登录成功");
			//由于ip地址读取错误,需要修改，但又不方便直接后台修改,因此临时通过程序修改
			if(user.address!=null&&"IP地址库文件错误".equals(user.address)){
				user.setAddress(IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
				userManager.updateUser(user);
			}
			request.getSession().setAttribute("user", user);
			//准备第三方账户名显示
			request.getSession().setAttribute("thirdParty", "QQ-昵称");
			String qq_openid = user.getQq_openId();
			if(qq_openid!=null&&!"".equals(qq_openid.trim()))
			{
				String qq_name = "昵称";
				qq_name = qquerinfomanager.findQQNameByOpenid(qq_openid.trim());
				request.getSession().setAttribute("thirdParty", "QQ-"+qq_name);
			}
			// TODO 判断是否有标签
			if (!tagsManager.checkUserTagIsEmpty(user.id)) {// 有标签
				System.out.println("有标签");
				// 判断是否绑定本地账号
				boolean bindAccount = false;
				LoginLog ll = userInfoManager.findLoginLogByUserId(String.valueOf(user.id));
				if (ll == null) {
					System.out.println("没有跳过");
					bindAccount = true;
				} else {
					if (ll.getBind_account_step() == 0) {
						System.out.println("跳过");
						bindAccount = false;
					} else {
						System.out.println("没有跳过");
						bindAccount = true;
					}
				}

				if (bindAccount
						&& (user.xunta_username == null || "".equals(user.xunta_username.trim())
								|| user.password == null || "".equals(user.password))) {
					try {
						System.out.println("用户名没有绑定并且没有绑定账号");
						response.sendRedirect(request.getContextPath() + "/jsp/xunta_user/login4.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						System.out.println("用户跳或绑定了账号");
						response.sendRedirect(request.getContextPath() + "/jsp/topic/index.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {// 没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath() + "/jsp/xunta_user/login3.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
