package so.xunta.topic.utils;

import java.io.File;

public class ImageUtils {

	// 将文件重新命名
	public static void renameImageName() {
		//String filePath = "C:\\Users\\Thinkpad\\Downloads\\622张男头像";
		String filePath = "C:\\Users\\Thinkpad\\Downloads\\xunta_user_logo";
		File imageFiles = new File(filePath);
		String imageNames[] = imageFiles.list();
		for (int i = 0; i < imageNames.length; i++) {
			String extension = imageNames[i].substring(imageNames[i].indexOf(".")+1);
			System.out.println(extension);
			if(extension!=null&&(extension.equals("jpg")||extension.equals("png"))||extension.equals("gif")){
			}else{
				continue;
			}
			File f = new File(filePath+File.separator+imageNames[i]);
			System.out.println(f);
			File mm = new File(filePath+ File.separator + "xunta_user_logo_" + (i+1) + ".jpg");
			if (f.renameTo(mm)) {
				System.out.println("修改成功!");
			} else {
				System.out.println("修改失败");
			}
		}
	}

	public static void main(String[] args) {
		renameImageName();
	}
}
