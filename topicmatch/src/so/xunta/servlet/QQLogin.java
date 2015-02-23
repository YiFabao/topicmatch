package so.xunta.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;
import so.xunta.entity.User;
import so.xunta.manager.QQUserInfoManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.QQUserInfoManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.user.info.tencentUserInfo;
import so.xunta.utils.DateTimeUtils;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class QQLogin extends HttpServlet {
	UserManager userManager=new UserManagerImpl();
	QQUserInfoManager qquerinfomanager = new QQUserInfoManagerImpl();
	
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
		String accessToken=request.getParameter("access_token");//获取参数accessToken
		//非空判断
		if(accessToken==null&&"".equals(accessToken)){
			System.out.println("获取参数accessToken为空:"+accessToken);
			return;
		}
		
		//获取用户的openId
		OpenID o = new OpenID(accessToken);
		String openId="";
		UserInfoBean userInfo=null;

		try {
			openId = o.getUserOpenID();
		} catch (QQConnectException e1) {
			e1.printStackTrace();
		}
		
		if(openId==null){
			System.out.println("获取openId为空:"+openId);
			return;
		}
		
		//获取用户的基本信息　
		QQUserInfo qquserInfo = null;//用户的基本信息 昵称,性别,所在地,描述,认证原因,标签
		QQDynamicInfoContent qqdynamicContent = null;//动态内容,就是用户的微博　
		
		JSONObject json = tencentUserInfo.get(accessToken);
		try {
			String nickname = (String) json.get("nickname");
			String gender = (String) json.get("gender");
			String location = (String) json.get("location");
			String description = (String) json.get("description");
			String verified_reason = (String) json.get("verified_reason");
			String tags = (String) json.get("tags");
			qquserInfo=new QQUserInfo(openId, nickname, gender, location, description, verified_reason, tags);
			
			String content = (String) json.get("content");
			qqdynamicContent=new QQDynamicInfoContent(openId, content);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//1.获取token
		//2.调用接口，获取基本信息
		//3.查询基本信息是否存在，不存在就添加基本信息，添加动态内容;  存在 就查询动态内容跟前一次的动态内容是否相同，不同则添加动态内容
		//4.将用户保存在session范围
		//5.跳转到首页
		
		//查询数据库中是否存在openid
		User user=userManager.findUserbyQQOpenId(openId);
		System.out.println(qquserInfo.getNickname()+"登录");
	
		if(user==null)//基本信息不存在
		{
			//用户没有绑定账号
			user = new User(qquserInfo.getNickname(),"", "", openId, accessToken,"","",new Date(),DateTimeUtils.getCurrentTimeStr());
			
			//添加用户表
			userManager.addUser(user);

			//添加qq用户的基本信息
			qquerinfomanager.addStaticQQUserInfo(qquserInfo);
			//添加qq用户动态内容信息　
			qquerinfomanager.addDynamicQQInfoContent(qqdynamicContent);
			
			//将服户保存到sessoin范围
			request.getSession().setAttribute("user", user);
			//跳转到首页
			response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
		}
		else//用户基本信息存在
		{
			//查询最近一次的用户发的动态内容 
			if(user.getXunta_username()==null||"".equals(user.getXunta_username()))
			{
				user.setXunta_username("QQ_"+userInfo.getNickname());
			}
			//登录成功
			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
