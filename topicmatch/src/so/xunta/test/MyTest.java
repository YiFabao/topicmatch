package so.xunta.test;

import java.util.Calendar;
import java.util.Date;

public class MyTest {
	public static void main(String[] args) {
	Calendar c =Calendar.getInstance();
	c.set(2015, 2, 15);
	Date d = c.getTime();
	System.out.println(d);
	}
}
