package so.xunta.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import so.xunta.entity.Tag;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.utils.HtmlRegexpUtil;

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
		case "tag":
			System.out.println("获取tag标签,保存到数据库");
			metchod_tags(request,response);
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

	private void metchod_tags(HttpServletRequest request, HttpServletResponse response) {
		try{
			Long userId = Long.parseLong(new String(request.getParameter("userId").getBytes("ISO8859-1"),"UTF-8")); 
			String tagArray[] = HtmlRegexpUtil.filterHtml(request.getParameter("tags")).replaceAll("", "").split(",");
			List<Tag> list = new ArrayList<Tag>();
			for (int i = 0; i < tagArray.length; i++) {
				String tag = tagArray[i];
				System.out.println("将标签存入数据库  ==>   userId : "+userId+"  --   tag : "+tag);
				list.add(new Tag(userId, tag));
			}
			new TagsManagerImpl().addTags(list);
			response.getWriter().write("ok");
		}catch (Exception e){
			try {
				response.getWriter().write(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
