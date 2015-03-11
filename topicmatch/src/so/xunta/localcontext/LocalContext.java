package so.xunta.localcontext;

public class LocalContext {
	//话题索引位置
	public static String indexFilePath="d://topicIndex";
	//public static String indexFilePath="/mnt/data/topicIndex";
//	public static String indexFilePath="F://topicIndex";
	public static String getIPFilePath(){
		String root=System.getProperty("user.dir") ; 
		return root+"\\QQWry.dat";
	}
	public static String getIPFileInstallFolder(){
		return System.getProperty("user.dir");
	}
    
}
