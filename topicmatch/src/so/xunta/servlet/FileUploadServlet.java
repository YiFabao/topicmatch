package so.xunta.servlet;

import java.io.File;
import java.io.IOException;
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

/**
 * @author fabao.yi
 * History :
 * 		2015/3/5 fabao.yi first release
 */
@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("utf-8");
		
		uploadImage(request,response);
	}

	private void uploadImage(HttpServletRequest request, HttpServletResponse response) {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);//检查输入请求是否为multipart表单数据。
		System.out.println("图片上传");
		if (isMultipart == true) {
			DiskFileItemFactory f = new DiskFileItemFactory();// 磁盘对象
			f.setRepository(new File("d:/a"));// 设置临时目录
			f.setSizeThreshold(1024 * 8);// 8k的缓冲区,文件大于8K则保存到临时目录
			ServletFileUpload upload = new ServletFileUpload(f);// 声明解析request的对象
			upload.setHeaderEncoding("UTF-8");// 处理文件名中文
			upload.setFileSizeMax(1024 * 1024 * 5);// 设置每个文件最大为5M
			upload.setSizeMax(1024 * 1024 * 10);// 一共最多能上传10M
			String path = this.getServletContext().getRealPath("/imgs");// 获取文件要保存的目录
			try {
				List<FileItem> list = upload.parseRequest(request);// 解析
				System.out.println(list.size());
				for (FileItem ff : list) {
					if (ff.isFormField()) {
						String ds = ff.getString("UTF-8");// 处理中文
						System.err.println(ff.getFieldName()+":" + ds);
						
						
					} else {
						String filename = ff.getName();
						String contentType=ff.getContentType();
						filename = filename.substring(filename.lastIndexOf("\\") + 1);// 解析文件名
						System.out.println("上传文件名为："+filename);
						System.out.println("文件类型为："+contentType);
						System.out.println("保存的路径为："+new File(path + "/" + filename).getAbsolutePath());
						if(contentType.equals("image/jpeg")){
							System.out.println("上传的是图片格式");
						}
						FileUtils.copyInputStreamToFile(ff.getInputStream(), new File(path + "/" + filename));// 直接使用commons.io.FileUtils
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}
