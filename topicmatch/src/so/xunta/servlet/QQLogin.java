package so.xunta.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.entity.QQDynamicInfoContent;
import so.xunta.entity.QQUserInfo;
import so.xunta.entity.User;
import so.xunta.manager.QQUserInfoManager;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.QQUserInfoManagerImpl;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.utils.IpUtils;
import so.xunta.user.info.tencentUserInfo;
import so.xunta.utils.DateTimeUtils;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

public class QQLogin extends HttpServlet {
	UserManager userManager=new UserManagerImpl();
	QQUserInfoManager qquerinfomanager = new QQUserInfoManagerImpl();
	TagsManager tagsManager = new TagsManagerImpl();
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
		
		System.out.println("获取参数accessToken:"+accessToken);
		//非空判断
		if(accessToken==null&&"".equals(accessToken)){
			System.out.println("获取参数accessToken为空:"+accessToken);
			response.getWriter().write("登录失败,请重新登录");
			response.flushBuffer();
			return;
		}
		//获取用户的基本信息　
		QQUserInfo qquserInfo = null;//用户的基本信息 昵称,性别,所在地,描述,认证原因,标签
		QQDynamicInfoContent qqdynamicContent = null;//动态内容,就是用户的微博　
		System.out.println("开始调用彬彬的获取qq用户的信息..");
		JSONObject json = tencentUserInfo.get(accessToken);
		String openId="";
		String imageUrl = "" ;
		try {
			String nickname = (String) json.get("nickname");
			String gender = (String) json.get("gender");
			String location = (String) json.get("location");
			String description = (String) json.get("description");
			String verified_reason = (String) json.get("verified_reason");
			String tags = (String) json.get("tags");
			imageUrl = (String) json.get("image");
			openId=(String)json.get("userId");
			System.out.println("openId:"+openId);
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
	
		System.out.println("查询用户是否为空:"+user);
		if(user==null)//基本信息不存在
		{
			//用户没有绑定账号
			user = new User(qquserInfo.getNickname(),"", "","","", openId, accessToken,"","",new Date(),DateTimeUtils.getCurrentTimeStr(),imageUrl);
			//获取ip
			String ipaAddress = request.getRemoteAddr();
			user.setAddress(IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
			//添加用户表
			userManager.addUser(user);
			//添加qq用户的基本信息
			qquerinfomanager.addStaticQQUserInfo(qquserInfo);
			//添加qq用户动态内容信息　
			qquerinfomanager.addDynamicQQInfoContent(qqdynamicContent);
			//将服户保存到sessoin范围
			request.getSession().setAttribute("user", user);
			//TODO 判断是否有标签
			if(!tagsManager.checkUserTagIsEmpty(user.id)){//有标签
				System.out.println("有标签");
				//判断是否绑定本地账号
				if(user.xunta_username!=null&&user.password!=null&&!"".equals(user.xunta_username)&&!"".equals(user.password)){
					System.out.println("绑定过本地账号");
					response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");//跳转到首页
				}else{
					System.out.println("没有绑定本地账号");
					response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/fillinfo.jsp?#&Reg");
				}
			}else{//没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/fillinfo.jsp?#&FillInfo");
			}
		}
		else//用户基本信息存在
		{
			//登录成功
			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
			//TODO 判断是否有标签
			if(!tagsManager.checkUserTagIsEmpty(user.id)){//有标签
				System.out.println("有标签");
				//判断是否绑定本地账号
				if(user.xunta_username!=null&&user.password!=null&&!"".equals(user.xunta_username)&&!"".equals(user.password)){
					System.out.println("绑定过本地账号");
					response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
				}else{
					System.out.println("没有绑定本地账号");
					response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/fillinfo.jsp?#&Reg");
				}
			}else{//没有标签
				System.out.println("没有标签");
				response.sendRedirect(request.getContextPath()+"/jsp/xunta_user/fillinfo.jsp?#&FillInfo");
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
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
