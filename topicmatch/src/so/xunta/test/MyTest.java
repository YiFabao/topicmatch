package so.xunta.test;

import java.util.Calendar;
import java.util.Date;

public class MyTest {
	public static void main(String[] args) {
		String osname = System.getProperty("os.name");
		if(osname==null)
		{
			System.out.println("os name is null");
			return;
		}
		if(osname.toUpperCase().indexOf("WINDOWS")!=-1)
		{
			System.out.println("windows系统");
		}
	
	}
}
