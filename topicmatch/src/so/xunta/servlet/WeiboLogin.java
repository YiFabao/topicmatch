package so.xunta.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import so.xunta.entity.LoginLog;
import so.xunta.entity.User;
import so.xunta.entity.WeiboDynamicInfoContent;
import so.xunta.entity.WeiboUserInfo;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserInfoManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.WeiboUserInfoManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserInfoManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.manager.impl.WeiboUserInfoManagerImpl;
import so.xunta.utils.DateTimeUtils;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.aaron.sinaUserInfo;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class WeiboLogin extends HttpServlet {
	UserManager userManager=new UserManagerImpl();
	TagsManager tagsManager = new TagsManagerImpl();
	UserInfoManager userInfoManager = new UserInfoManagerImpl();
	/**
	 * Constructor of the object.
	 */
	public WeiboLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WeiboUserInfoManager weibouserManager = new WeiboUserInfoManagerImpl();
		//1.获取code
		//2.调用彬彬的获取用户微博信数据
		//3.查询wei_uid是否存在
		//4.如果存在，则保存到session,跳转到首页
		//5.如果不存在,保存用户基本信息,跳转到首页
		String code=request.getParameter("code");
		System.out.println("code:"+code);
		if(code==null)
		{
			response.getWriter().write("code为空");
			return;
		}
		
		//调用彬彬的接口获取用户微博的基本信息
		String nickname="";
		String gender="";
		String location="";
		String description="";
		String verified_reason="";
		String tags="";
		String content="";
		String uid="";
		String token="";
		String image="";
		try {
			JSONObject json = sinaUserInfo.getInfo(code);
			nickname =(String) json.get("nickname");
			gender=(String)json.get("gender");
			location=(String)json.get("location");
			description=(String)json.get("description");
			verified_reason=(String)json.get("verified_reason");
			tags=(String)json.get("tags");
			token=(String)json.get("token");
			image=(String)json.get("image");
			uid=(String) json.get("userId");
			
			List<String> contentList = sinaUserInfo.getContent(token);
			for (String string : contentList) {
				weibouserManager.addWeiboContentAndWeiboUserId(new WeiboDynamicInfoContent(uid, string));
			}
			System.out.println("nickname:"+nickname);
			System.out.println("uid:"+uid);
			System.out.println("token:"+token);
			System.out.println();
		} catch (WeiboException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//判断uid是否为空
		if(uid.equals(""))
		{
			response.getWriter().write("uid为空");
			return;
		}
		//查询数据库中是否存在uid
		User user = userManager.findUserByWeiboUid(uid);
		if(user==null){
			System.out.println("数据库中不存在该微博uid");
			//用户没有绑定账号
			user = new User(nickname,"", "","","","","",uid,token,new Date(),DateTimeUtils.getCurrentTimeStr(),image);
			//添加用户表
			userManager.addUser(user);
			
			//保存微博用户的基本信息
			WeiboUserInfo weiboUserInfo =new WeiboUserInfo(nickname, gender, location, description, verified_reason, tags, uid);
			weibouserManager.addStaticWeiBoUserInfo(weiboUserInfo);
			//保存微博的动态信息
			WeiboDynamicInfoContent weiboDynamicInfoContent = new WeiboDynamicInfoContent(uid, content);
			System.out.println("微博内容："+content);
			//weibouserManager.addDynamicWeiboInfoContent(weiboDynamicInfoContent);
			
			//将用户保存到sessoin范围
			request.getSession().setAttribute("user", user);
			//TODO 判断是否有标签
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
			
		}else{
			System.out.println("数据库中存在该微博uid");
			//更新accessToken
			user.setWeibo_accessToken(token);
			userManager.updateUser(user);
			
			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
			//TODO 判断是否有标签
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

		/*response.setContentType("text/html");
		Oauth oauth = new Oauth();
		AccessToken accessToken=null;
		String code=request.getParameter("code");
		Users um = new Users();
		weibo4j.model.User u=null;
		String weibo_acceesToken="";
		String weibo_uid="";
		User user=null;
		System.out.println("获取code:"+code);
		try {
			accessToken =oauth.getAccessTokenByCode(code);
			//通过accessToken获取用户信息
			um.client.setToken(accessToken.getAccessToken());
			 weibo_acceesToken=accessToken.getAccessToken();
			 weibo_uid=accessToken.getUid();
			user=userManager.findUserByWeiboUid(weibo_uid);
		} catch (WeiboException e) {
			System.out.println("获取accessToken对象失败："+e.getMessage());
		}

		try {
			u=um.showUserById(weibo_uid);
			System.out.println("微博名称："+u.getScreenName());
	
		} catch (WeiboException e) {
			System.out.println(e.getMessage());
		}

		if(user==null)
		{
			//用户没有绑定账号
			user=new User();
			user.setWeibo_accessToken(weibo_acceesToken);
			user.setWeibo_uid(weibo_uid);
			user.setCreateTime(new Date());
			userManager.addUser(user);

			System.out.println(u.getScreenName()+"登录");
			user.setXunta_username(u.getScreenName());
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
		}
		else
		{
			if(user.getXunta_username()==null||"".equals(user.getXunta_username()))
			{
				user.setXunta_username("新浪微博_"+u.getScreenName());
			}
			//登录成功
			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
		}*/
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
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
	
	/*public static void main(String[] args) {
		String code="b832d17325388648ec23dd05096724e5";
		weibo4j.model.User u=null;
		Oauth oauth = new Oauth();
		AccessToken accessToken=null;
		String weibo_acceesToken="";
		String weibo_uid="";
		User user=null;
		try {
			accessToken =oauth.getAccessTokenByCode(code);
			//通过accessToken获取用户信息
			String aa=weibo_acceesToken=accessToken.getAccessToken();
			Users um = new Users();
			um.client.setToken(accessToken.getAccessToken());
			 weibo_acceesToken=accessToken.getAccessToken();
			 weibo_uid=accessToken.getUid();
			 System.out.println("weibo_uid:"+weibo_uid);
			System.out.println(aa);

		} catch (WeiboException e) {
			System.out.println("获取accessToken对象失败："+e.getMessage());
		}
	}*/
	
	

}
