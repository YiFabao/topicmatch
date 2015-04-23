package so.xunta.localcontext;

import java.io.File;

public class LocalContext {
	//话题索引位置
	//public static String indexFilePath="d://topicIndex";
	
	public static String getIndexFilePath(){
		//判断操作系统
		String osname = System.getProperty("os.name");
		if(osname==null)
		{
			System.out.println("os name is null");
			return null;
		}
		if(osname.toUpperCase().indexOf("WINDOWS")!=-1)
		{
			System.out.println("windows系统");
			return "d://topicIndex";
		}else{
			System.out.println("linux系统");
			return "/mnt/data/topicIndex";
		}
	}
	
	//public static String indexFilePath="/mnt/data/topicIndex";
//	public static String indexFilePath="F://topicIndex";
	public static String getIPFilePath(){
		String root=System.getProperty("user.dir") ; 
		return root+"\\QQWry.dat";
	}
	public static String getIPFileInstallFolder(){
		return System.getProperty("user.dir");
	}

	public static String getPicPath(){
		//判断操作系统
		String osname = System.getProperty("os.name");
		if(osname==null)
		{
			System.out.println("os name is null");
			return null;
		}
		if(osname.toUpperCase().indexOf("WINDOWS")!=-1)
		{
			System.out.println("windows系统");
			File f=new File("d://images");
			if(!f.exists()){
				f.mkdir();
			}
			return "d://images";
		}else{
			System.out.println("linux系统");
			File f=new File("/mnt/data/images");
			if(!f.exists()){
				f.mkdirs();
			}
			return "/mnt/data/images";
		}
	}
	
	public static String getTempFilePath(){
		//判断操作系统
		String osname = System.getProperty("os.name");
		if(osname==null)
		{
			System.out.println("os name is null");
			return null;
		}
		if(osname.toUpperCase().indexOf("WINDOWS")!=-1)
		{
			System.out.println("windows系统");
			File f=new File("d://temp");
			if(!f.exists()){
				f.mkdir();
			}
			return "d://temp";
		}else{
			System.out.println("linux系统");
			File f=new File("/mnt/data/temp");
			if(!f.exists()){
				f.mkdirs();
			}
			return "/mnt/data/temp";
		}
	}
    
}
