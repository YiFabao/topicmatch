package so.xunta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.QQUserInfoManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.manager.impl.WeiboUserInfoManagerImpl;
import so.xunta.manager.impl.WeixinUserInfoManagerImpl;
import so.xunta.topic.utils.IpUtils;
import so.xunta.utils.DateTimeUtils;

public class Login extends HttpServlet {

	UserManager userManager = new UserManagerImpl();
	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username=request.getParameter("xunta_username");
		String password=request.getParameter("password");
		String checkbox=request.getParameter("checkbox");
		
		//检查用户是否已经登录，避免重复登录
		User user_to_check=(User) request.getSession().getAttribute("user");
		if(user_to_check!=null&&user_to_check.xunta_username.equals(username)){
			System.out.println("已经登录过，不必重复验证登录");
/*			JSONObject ret=new JSONObject();
			ret.put("state","success");
			ret.put("userId",user_to_check.id);
			response.setContentType("text/json");
			response.getWriter().write(ret.toString());*/
			String url ="/servlet/userLoginService?cmd=checkHasTag&userId="+user_to_check.id;
			request.getRequestDispatcher(url).forward(request, response);
			//response.sendRedirect(request.getContextPath()+"jsp/topic/index.jsp");
		}
		else{
			User user=userManager.checkRegisterUserExist(username, password);
			if(user==null)
			{
				System.out.println("用户名或密码错误");
				request.setAttribute("errorMsg","用户名或密码错误");
				request.getRequestDispatcher(request.getParameter("from")).forward(request, response);
			}
			else
			{
				System.out.println(user.getXunta_username()+"登录成功");
				String ipaAddress = request.getRemoteAddr();
				System.out.println("ip地址:"+ipaAddress+"  城市:"+IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
				if(user.address!=null&&"IP地址库文件错误".equals(user.address)){
					user.setAddress(IpUtils.getInstance().getCountryByIdAddress(ipaAddress));
				}
				user.setLatestLoginTime(DateTimeUtils.getCurrentTimeStr());
				userManager.updateUser(user);
				//将user保存到session中
				request.getSession().setAttribute("user",user);
				
				request.getSession().setAttribute("thirdParty", "第三方-昵称");
			 	String weibo_uid = user.getWeibo_uid();
				String qq_openid = user.getQq_openId();
				String weixin_uid = user.getWeixin_uid();
				if(weibo_uid!=null&&!"".equals(weibo_uid.trim()))
				{	
					String weibo_name = "昵称";
					weibo_name = (new WeiboUserInfoManagerImpl()).findWeiboNameByWeiboUid(weibo_uid.trim());
					request.getSession().setAttribute("thirdParty", "微博-"+weibo_name);
				}
				else if(qq_openid!=null&&!"".equals(qq_openid.trim()))
				{
					String qq_name = "昵称";
					qq_name = (new QQUserInfoManagerImpl()).findQQNameByOpenid(qq_openid.trim());
					request.getSession().setAttribute("thirdParty", "QQ-"+qq_name);
				}
				else if(weixin_uid!=null&&!"".equals(weixin_uid.trim()))
				{
					String weixin_name = "昵称";
					weixin_name = (new WeixinUserInfoManagerImpl()).findWeixinNameByWeixinUid(weixin_uid.trim());
					request.getSession().setAttribute("thirdParty", "微信-"+weixin_name);
				}
				
				//跳转到://servlet/userLoginService
/*				var parameters = {
					cmd:"checkHasTag",
					userId:res.userId
				};*/
				String url ="/servlet/userLoginService?cmd=checkHasTag&userId="+user.id;
				request.getRequestDispatcher(url).forward(request, response);
	
			/*	Cookie[] xunta_cookies=request.getCookies();
				boolean hasAigine_login_state=true;
				for(Cookie cookie:xunta_cookies)
				{
					System.out.println(cookie.getName()+":"+cookie.getValue());
					if(cookie.getName().equals("aigine_login_state")){
						hasAigine_login_state=false;
					}
				}
				System.out.println("cookie长度:"+xunta_cookies.length);
				
				if(hasAigine_login_state){
					System.out.println("添加cookie");
					Cookie cookie = new Cookie("aigine_login_state",java.net.URLEncoder.encode("hasLogged","utf-8"));
					cookie.setMaxAge(3600*24*300);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				JSONObject ret=new JSONObject();
				ret.put("state","success");
				ret.put("userId",user.id);
				response.setContentType("text/json");
				response.getWriter().write(ret.toString());*/
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

	public void init() throws ServletException {

	}

}
