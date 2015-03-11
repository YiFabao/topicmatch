package so.xunta.test;

import java.io.File;

public class MyTest {
	public static void main(String[] args) {
		String aa=System.getProperty("user.dir") ; 
		File f=new File(aa);
		if(f.isDirectory())
		{
			File[] arrayF=f.listFiles();
			for(int i=0;i<arrayF.length;i++)
			{
				System.out.println(arrayF[i].getName());
			}
		}
		System.out.println(aa);
	}
}
