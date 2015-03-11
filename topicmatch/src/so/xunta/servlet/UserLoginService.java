package so.xunta.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import so.xunta.entity.Tag;
import so.xunta.entity.User;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.utils.DateTimeUtils;
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
	UserManager userManager=new UserManagerImpl();
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
			method_tags(request,response);
			break;
		case "bindlocalaccount":
			methoc_bindlocalaccount(request,response);
			break;
		case "complete_reg":
			method_complete_reg(request,response);
		default:
			break;
		}
	}


	private void method_complete_reg(HttpServletRequest request, HttpServletResponse response) {
		uploadImage(request,response);
	}
	
	//上传图片
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);//检查输入请求是否为multipart表单数据。
		System.out.println("图片上传");
		if (isMultipart == true) {
			DiskFileItemFactory f = new DiskFileItemFactory();// 磁盘对象
			f.setRepository(new File("/root/temp"));// 设置临时目录
			f.setSizeThreshold(1024 * 8);// 8k的缓冲区,文件大于8K则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);// 声明解析request的对象
			upload.setHeaderEncoding("UTF-8");// 处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 5);// 设置每个文件最大为5M
			upload.setSizeMax(1024 * 1024 * 10);// 一共最多能上传10M
			String path = this.getServletContext().getRealPath("/imgs");// 获取文件要保存的目录
			try {
				List<FileItem> list = upload.parseRequest(request);// 解析
				String nickname="";
				String year="";
				String month="";
				String day="";
				String address="";
				String email="";
				System.out.println(list.size());
				
				//完善用户资料
				User user= (User) request.getSession().getAttribute("user");
				if(user==null)
				{
					response.getWriter().write("illegal login,user in session is null...");
					return ;
				}
				
				for (FileItem ff : list) {
					if (ff.isFormField()) {
						String ds = ff.getString("UTF-8");// 处理中文
						//System.err.println(ff.getFieldName()+":" + ds);
						String filedname = ff.getFieldName().trim();
						System.out.println(filedname);
						switch(filedname){
						case "nickname":
							nickname=ds;
							if(nickname!=null&&!"".equals(nickname)){
								user.setNickname(nickname);
							}
							System.out.println("nickname:"+nickname);
							break;
						case "year":
							year=ds;
							break;
						case "month":
							month=ds;
							break;
						case "day":
							day=ds;
							break;
						case "address":
							address=ds;
							if(address!=null&&!"".equals(address)){
								user.setAddress(address);
							}
							break;
						case "email":
							email=ds;
							if(email!=null&&!"".equals(email)){
								user.setEmail(email);
							}
							break;
						default:
							break;
						}
					} else {
						String filename = ff.getName();
						String contentType=ff.getContentType();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);// 解析文件名
						System.out.println("上传文件名为："+filename);
						String extension=filename.substring(filename.lastIndexOf("."));
						String imgname=filename.substring(0,filename.lastIndexOf("."));
						System.out.println("图片后缀为："+extension);
						System.out.println("图片名称为："+imgname);
						//重新构造文件名 　　　实际文件名_用户id_时间戳
						String newImageName=imgname+"_"+user.id+"_"+(new Date().getTime())+extension;
						System.out.println("文件类型为："+contentType);
						System.out.println("保存的路径为："+new File(path + "/" + newImageName).getAbsolutePath());
						if(contentType.equals("image/jpeg")){
							System.out.println("上传的是图片格式");
						}
						user.setImageUrl(request.getContextPath()+"/imgs/"+newImageName);
						//IO
						FileUtils.copyInputStreamToFile(ff.getInputStream(), new File(path + "/" + newImageName));// 直接使用commons.io.FileUtils
					
						response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
					}
				}
				if(!"".equals(year)&&!"".equals(month)&&!"".equals(day))
				{
					Calendar c =Calendar.getInstance();
					c.clear();
					System.out.println("year:"+ year+" month:"+(month)+" day:"+day);
					user.setBirthday(year+"-"+month+"_"+day);
					System.out.println("nickname:"+nickname+"  "+"address:"+user.address+"  birthday:"+(year+"-"+month+"_"+day));
				}
				userManager.updateUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				

			}
		}else{
			try {
				response.getWriter().write("the enctype must be multipart/form-data");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void methoc_bindlocalaccount(HttpServletRequest request, HttpServletResponse response) {
		try{
			System.out.println("绑定用户账号操作");
			String userNameR = request.getParameter("userNameR");
			String password = request.getParameter("passwordR");
			String validateCodeR = request.getParameter("validateCodeR");
			System.out.println("用户填写的用户名====>userNameR:"+userNameR);
			System.out.println("用户填写的密码:====>password:"+password);
			System.out.println("用户填写验证码:====>validateCodeR:"+validateCodeR);
			if(validateCodeR!=null)
			{
				validateCodeR = validateCodeR.toUpperCase();
			}
			
			//验证用户输入的验证码是否正确
			String _code=(String) request.getSession().getAttribute("code");
			if(_code!=null)
			{
				_code = _code.toUpperCase();
			}
			if(_code!=null&&validateCodeR!=null&&_code.equals(validateCodeR))
			{
				System.out.println("用户验证码输入正确");
				//获取userId
				User user = (User) request.getSession().getAttribute("user");
				if(user==null){
					System.out.println("user in session is null");
					response.getWriter().write("illegal login");
					return ;
				}
				System.out.println("获取第三方用户id:"+user.getId());
				user.setXunta_username(userNameR);
				user.setPassword(password);
				userManager.updateUser(user);
				System.out.println("绑定用户数据成功");
			}
			else{
				System.out.println("用户验证码输入不正确");
				response.getWriter().write("code input error");
				return;
			}
			response.getWriter().write("ok");
			
		}catch(Exception e){
			try {
				response.getWriter().write(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void method_tags(HttpServletRequest request, HttpServletResponse response) {
		try{
			Long userId = Long.parseLong(request.getParameter("userId")); 
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
