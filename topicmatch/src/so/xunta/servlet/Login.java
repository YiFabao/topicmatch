package so.xunta.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.UserManagerImpl;

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
			response.getWriter().write("success");
		}
		else{
			User user=userManager.checkRegisterUserExist(username, password);
			if(user==null)
			{
				System.out.println("用户名或密码错误");
				request.setAttribute("xunta_username",username);
				request.setAttribute("errorMsg","用户名或密码错误");
				response.getWriter().write("failure");
			}
			else
			{
				System.out.println(user.getXunta_username()+"登录成功");
				//将user保存到session中
				request.getSession().setAttribute("user",user);
	
				Cookie[] xunta_cookies=request.getCookies();
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
				response.getWriter().write("success");
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
