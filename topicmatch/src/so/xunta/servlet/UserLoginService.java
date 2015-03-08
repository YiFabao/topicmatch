package so.xunta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fabao.yi
 * History:
 * 		2015/3/8 12:18 first release...
 * 		2015/3/8 ....如有修改照这样写
 */
@WebServlet(description = "用户登录请求入口的小控制器", urlPatterns = { "/UserLoginService" })
public class UserLoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserLoginService() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd==null||"".equals(cmd))
		{
			response.getWriter().write("cmd is null");
			return;
		}
		switch(cmd){
		case "tags":
			System.out.println("获取tag标签,保存到数据库");
			//如果成功 response.getWriter().write("ok");
			//如果保存失败response.getWriter().write("failed");
			response.getWriter().write("收到请求");
			break;
		case "bindlocalaccount":
			System.out.println("绑定用户账号操作");
/*  			userNameR:userNameR,
  			passwordR:passWordR,
  			validateCodeR:validateCodeR*/
			System.out.println(request.getParameter("userNameR"));
			System.out.println(request.getParameter("passwordR"));
			System.out.println(request.getParameter("validateCodeR"));
			response.getWriter().write("收到请求");
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
