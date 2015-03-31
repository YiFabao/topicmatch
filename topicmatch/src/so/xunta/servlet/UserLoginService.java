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
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import so.xunta.entity.Tag;
import so.xunta.entity.User;
import so.xunta.localcontext.LocalContext;
import so.xunta.manager.TagsManager;
import so.xunta.manager.UserManager;
import so.xunta.manager.impl.TagsManagerImpl;
import so.xunta.manager.impl.UserManagerImpl;
import so.xunta.topic.utils.SecurityUtil;
import so.xunta.utils.DateTimeUtils;
import so.xunta.utils.HtmlRegexpUtil;
import so.xunta.utils.ImageUtil;

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
	TagsManager tagManager = new TagsManagerImpl();
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
		case "getUserInfo":
			method_getUserInfo(request,response);
			break;
		case "tag":
			System.out.println("获取tag标签,保存到数据库");
			method_tags(request,response);
			break;
		case "bindlocalaccount":
			methoc_bindlocalaccount(request,response);
			break;
		case "complete_reg":
			method_complete_reg(request,response);
			break;
		case "checkHasTag":
			checkHasTag(request,response);
			break;
		case "imgCheck":
			imgCheck(request, response);
			break;
		default:
			break;
		}
	}


	private void method_getUserInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String userId = request.getParameter("userId").trim();//用户id
		User user = userManager.findUserById(Integer.parseInt(userId));
		List<Tag> tagList = tagManager.findAllTagsByUserId(Long.parseLong(userId));
		if(user==null)
		{
			request.setAttribute("user",null);
		}
		else
		{
			request.setAttribute("user",user);
			String birthday = user.getBirthday();
			String year = null;
			String month = null;
			String day = null;
			if(birthday!=null&&!"".equals(birthday))
			{
				String[] birth = birthday.split("-");
				year = birth[0];
				month = birth[1];
				day = birth[2];
			}
			request.setAttribute("year", year);	
			request.setAttribute("month", month);	
			request.setAttribute("day", day);	
			if(tagList!=null)
				request.setAttribute("tags", tagList);
			else
				request.setAttribute("tags", null);
		}
		try {
			request.getRequestDispatcher("/jsp/topic/include/account_settings.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void checkHasTag(HttpServletRequest request, HttpServletResponse response) {
		String userIdStr = request.getParameter("userId");
		if(userIdStr==null||"".equals(userIdStr))
		{
			System.out.println("userId为空:"+userIdStr);
			return ;
		}
		boolean flag = tagManager.checkUserTagIsEmpty(Long.parseLong(userIdStr));
		if(!flag){
			try {
				System.out.println("用户存在标签");
				response.getWriter().write("exist");//用户存在标签
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				System.out.println("用户不存在标签");
				response.getWriter().write("none");//用户没有标签
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void method_complete_reg(HttpServletRequest request, HttpServletResponse response) {
		//uploadImage(request,response);
		userInfoModify(request,response);
	}
	
	//上传图片
	private void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);//检查输入请求是否为multipart表单数据。
		System.out.println("图片上传");
		if (isMultipart == true) {
			DiskFileItemFactory f = new DiskFileItemFactory();// 磁盘对象
			f.setRepository(new File(LocalContext.getTempFilePath()));// 设置临时目录
			f.setSizeThreshold(1024 * 8);// 8k的缓冲区,文件大于8K则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);// 声明解析request的对象
			upload.setHeaderEncoding("UTF-8");// 处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 1);// 设置每个文件最大为1M
			upload.setSizeMax(1024 * 1024 * 1);// 一共最多能上传1M
			String path =LocalContext.getPicPath();
			try {
				List<FileItem> list = upload.parseRequest(request);// 解析
				String nickname="";
				String year="";
				String month="";
				String day="";
				String address="";
				String email="";
				char sex;
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
						case "sex":
							System.out.println("sex:"+ds);
							if(ds!=null&&!"".equals(ds)){
								user.setSex(ds);
							}						
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
						if(filename==null){
							continue;
						}
						String contentType=ff.getContentType();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);// 解析文件名
						System.out.println("上传文件名为："+filename);
						String extension=filename.substring(filename.lastIndexOf("."));
						String imgname=filename.substring(0,filename.lastIndexOf("."));
						//重新构造文件名 　　　实际文件名_用户id_时间戳
						String newImageName="user_"+user.id+"_"+(new Date().getTime())+extension;
	
						user.setImageUrl(newImageName);
						//IO
						FileUtils.copyInputStreamToFile(ff.getInputStream(), new File(path + "/" + newImageName));// 直接使用commons.io.FileUtils
						//压缩图片
						 File originalImage =new File(path + "/" + newImageName);
						 if(originalImage.exists()){
							 System.out.println("压缩图片时 上传图片失败");
							 ImageUtil.resize(originalImage,new File(path + "/" + newImageName),100, 0.7f);
						 }
						 response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
					}
				}
				if(!"".equals(year)&&!"".equals(month)&&!"".equals(day))
				{
					Calendar c =Calendar.getInstance();
					c.clear();
					System.out.println("year:"+ year+" month:"+(month)+" day:"+day);
					user.setBirthday(year+"-"+month+"-"+day);
					System.out.println("nickname:"+nickname+"  "+"address:"+user.address+"  birthday:"+(year+"-"+month+"-"+day));
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
	
	
	/**
	 * @author Yanyu Li
	 * @date 2015年3月27日
	 * @param request
	 * @param response 
	 * @return void
	 * 提交用户信息修改
	 */
	private void userInfoModify(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);//检查输入请求是否为multipart表单数据。
		System.out.println("图片上传");
		if (isMultipart == true) {
			DiskFileItemFactory f = new DiskFileItemFactory();// 磁盘对象
			f.setRepository(new File(LocalContext.getTempFilePath()));// 设置临时目录
			f.setSizeThreshold(1024 * 8);// 8k的缓冲区,文件大于8K则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);// 声明解析request的对象
			upload.setHeaderEncoding("UTF-8");// 处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 1);// 设置每个文件最大为1M
			upload.setSizeMax(1024 * 1024 * 1);// 一共最多能上传1M
			String path =LocalContext.getPicPath();
			try {
				List<FileItem> list = upload.parseRequest(request);// 解析
				String nickname="";
				String password="";
				String newTags="";
				String year="";
				String month="";
				String day="";
				String address="";
				String email="";
				String imgName="";
				char sex;
				System.out.println(list.size());

				//完善用户资料
				User user= (User) request.getSession().getAttribute("user");
				if(user==null)
				{
					response.getWriter().write("illegal login,user in session is null...");
					return ;
				}

				for (FileItem ff : list) {
					if (ff.isFormField()) {//如果为真，则为表单输入域
						String ds = ff.getString("UTF-8");// 处理中文
						//System.err.println(ff.getFieldName()+":" + ds);
						String filedname = ff.getFieldName().trim();
						System.out.println(filedname);
						switch(filedname){
						case "nickname":
							nickname=ds;
							if(nickname!=null&&!"".equals(nickname.trim())){
								user.setNickname(nickname);
							}
							System.out.println("nickname:"+nickname);
							break;
						case "sex":
							System.out.println("sex:"+ds);
							if(ds!=null&&!"".equals(ds)){
								user.setSex(ds);
							}						
							break;
						case "newTags":
							newTags = ds;
							System.out.println("newTags:"+newTags);
							method_Modifytags(user.getId(),newTags);
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
							if(address!=null&&!"".equals(address.trim())){
								user.setAddress(address);
							}
							break;
						case "email":
							email=ds;
							if(email!=null&&!"".equals(email.trim())){
								user.setEmail(email);
							}
							break;
						case "password":
							password=ds;
							if(password!=null&&!"".equals(password.trim())){
								user.setPassword(password);
							}
							break;
						case "imgName":
							imgName=ds;
							if(imgName!=null&&!"".equals(imgName.trim()))
							{
								user.setImageUrl(imgName);
							}
							break;
						default:
							break;
						}
					} else {//否则为文件上传域
						/*String filename = ff.getName();
						if(filename==null||"".equals(filename)){
							continue;
						}
						String contentType=ff.getContentType();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);// 解析文件名
						System.out.println("上传文件名为："+filename);
						String extension=filename.substring(filename.lastIndexOf("."));
						String imgname=filename.substring(0,filename.lastIndexOf("."));
						//重新构造文件名 　　　实际文件名_用户id_时间戳
						String newImageName="user_"+user.id+"_"+(new Date().getTime())+extension;

						user.setImageUrl(newImageName);
						//IO
						FileUtils.copyInputStreamToFile(ff.getInputStream(), new File(path + "/" + newImageName));// 直接使用commons.io.FileUtils
						//压缩图片
						File originalImage =new File(path + "/" + newImageName);
						ImageUtil.resize(originalImage,new File(path + "/" + newImageName),100, 0.7f);
						//response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
*/
					}
				}
				if(!"".equals(year)&&!"".equals(month)&&!"".equals(day))
				{
					Calendar c =Calendar.getInstance();
					c.clear();
					System.out.println("year:"+ year+" month:"+(month)+" day:"+day);
					user.setBirthday(year+"-"+month+"-"+day);
					System.out.println("nickname:"+nickname+"  "+"address:"+user.address+"  birthday:"+(year+"-"+month+"-"+day));
				}
				userManager.updateUser(user);
				response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
			} catch(FileUploadException e1){
				System.out.println("SizeLimitExceededException捕捉");
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

	private void imgCheck(HttpServletRequest request, HttpServletResponse response) {

			System.out.println("收到请求");
			
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);//检查输入请求是否为multipart表单数据。
		System.out.println("图片上传");
		if (isMultipart == true) {
			DiskFileItemFactory f = new DiskFileItemFactory();// 磁盘对象
			f.setRepository(new File(LocalContext.getTempFilePath()));// 设置临时目录
			f.setSizeThreshold(1024 * 8);// 8k的缓冲区,文件大于8K则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);// 声明解析request的对象
			upload.setHeaderEncoding("UTF-8");// 处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 1);// 设置每个文件最大为1M
			upload.setSizeMax(1024 * 1024 * 1);// 一共最多能上传1M
			String path =LocalContext.getPicPath();
			try {
				List<FileItem> list = upload.parseRequest(request);// 解析
				
				System.out.println(list.size());

				//完善用户资料
				User user= (User) request.getSession().getAttribute("user");
				if(user==null)
				{
					response.getWriter().write("illegal login");
					return ;
				}

				for (FileItem ff : list) {
					if (ff.isFormField()) {//如果为真，则为表单输入域
						String ds = ff.getString("UTF-8");// 处理中文
						//System.err.println(ff.getFieldName()+":" + ds);
						String filedname = ff.getFieldName().trim();
						System.out.println(filedname);
						
					} else {//否则为文件上传域
						String filename = ff.getName();
						if(filename==null||"".equals(filename)){
							continue;
						}
						String contentType=ff.getContentType();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);// 解析文件名
						System.out.println("上传文件名为："+filename);
						String extension=filename.substring(filename.lastIndexOf("."));
						String imgname=filename.substring(0,filename.lastIndexOf("."));
						//重新构造文件名 　　　实际文件名_用户id_时间戳
						String tempImageName="user_"+user.id+"_"+(new Date().getTime())+extension;

						//user.setImageUrl(newImageName);
						//IO
						FileUtils.copyInputStreamToFile(ff.getInputStream(), new File(path + "/" + tempImageName));// 直接使用commons.io.FileUtils
						
						//压缩图片
						File originalImage =new File(path + "/" + tempImageName);
						ImageUtil.resize(originalImage,new File(path + "/" + tempImageName),100, 0.7f);
						response.getWriter().write(tempImageName);
						//response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
					}
				}
				//response.sendRedirect(request.getContextPath()+"/jsp/topic/index.jsp");
				
			} catch(FileUploadException e1){
				try {
					response.getWriter().write("exceed");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
			}
		}else{
			try {
				response.getWriter().write("not multipart");
			} catch (IOException e) {
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
				if(tagArray[i]==null||"".equals(tagArray[i].trim()))
					continue;
				String tag = tagArray[i];
				System.out.println("将标签存入数据库  ==>   userId : "+userId+"  --   tag : "+tag);
				list.add(new Tag(userId, tag, SecurityUtil.strToMD5(userId+tag)));
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

	private void method_Modifytags(Long userId, String newTags) {
		TagsManager tagsManager = new TagsManagerImpl();
		try{
			//先删除原有tags
			tagsManager.deleteTagsByUserId(userId);
			String tagArray[] = HtmlRegexpUtil.filterHtml(newTags).replaceAll("", "").split(",");
			List<Tag> list = new ArrayList<Tag>();
			for (int i = 0; i < tagArray.length; i++) {
				if(tagArray[i]==null||"".equals(tagArray[i].trim()))
					continue;
				String tag = tagArray[i];
				System.out.println("将标签存入数据库  ==>   userId : "+userId+"  --   tag : "+tag);
				list.add(new Tag(userId, tag, SecurityUtil.strToMD5(userId+tag)));
			}
			tagsManager.addTags(list);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
