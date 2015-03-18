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
	public static String getDate_ta_pc(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	public static String getTime_ta_pc(Date date){
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}
	public static String getDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date);
	}
	public static String getTime(Date date){
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		return format.format(date);
	}
	
	public static String getMonth(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MM");
		return format.format(date);
	}
}
