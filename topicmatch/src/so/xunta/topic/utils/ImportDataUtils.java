package so.xunta.topic.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImportDataUtils {
	private ImportDataUtils(){};
	
	private static boolean hasThread = false;
	
	private static ImportDataUtils instance = new ImportDataUtils();
	
	public static ImportDataUtils getInstance(){
		return instance;
	}
	
	//添加用户
	public void importData(HttpServletRequest request,HttpServletResponse response){
		if(!hasThread){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					//定义导入数据的范围

					//定义导入数据的范围
					//int init = 22735353;
					int init = 23739696;
					int max = 1000000;
					for(int i =init;i<init+max;i+=201){
						try {
							so.xunta.utils.ImportDataUtils.addUser_1(i,200);
							so.xunta.utils.ImportDataUtils.addTopicxx_2(i,200);
							so.xunta.utils.ImportDataUtils.addHistoryMessage(i,200);
						} catch (Exception e) {
							System.out.println("第"+i+"条 记录异常");
						}
					}
					
				}
			}).start();
			hasThread=true;
			try {
				response.getWriter().write("ok");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				response.getWriter().write("already running!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
