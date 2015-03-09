package so.xunta.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(getDateAndTime(date));
		System.out.println(getDate(date));
		System.out.println(getTime(date));
	}
	
	public static String getDateAndTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
	public static int getDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(format.format(date));
	}
	public static int getTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		return Integer.parseInt(format.format(date));
	}
}
