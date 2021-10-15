package me.peak;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateSnippet {
	public static void main(String[] args) throws InterruptedException {
//		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar.get(Calendar.YEAR));
//		System.out.println(calendar.get(Calendar.MONTH));
//		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		//calendar 与 date 类型转换
//		calendar.setTime(new Date());
//		Thread.sleep(2000);
//		Calendar nowCalendar = Calendar.getInstance();
//		System.out.println(nowCalendar.compareTo(calendar));
//		Date date = calendar.getTime();
		//calendar
//		Date date = new Date();
//		System.out.println(getDate(date));
//		System.out.println(getTime(date));
//		System.out.println(isNowGreatThanTime("19:00"));

		System.out.println(getThePastFewDay(19));
		System.out.println(getFirstDayOfMonth());
	}

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static long diffDays(LocalDateTime ldt, LocalDateTime ldt1) {
		return ChronoUnit.DAYS.between(ldt, ldt1);
	}

	//获取几天前的日期
	public static String getThePastFewDay(int num){
		LocalDateTime ldt = LocalDateTime.now().minusDays(num);
		return ldt.format(formatter);
	}

	//获取本月第一天的日期
	public static String getFirstDayOfMonth(){
		LocalDateTime ldt = LocalDateTime.now().withDayOfMonth(1);
		return ldt.format(formatter);
	}

	private static int getDate(Date date) {
		if (date == null) {
			return 0;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dateStr = df.format(date);
		return Integer.valueOf(dateStr);
	}

	private static int getTime(Date date) {
		if (date == null){
			return 0;
		}
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");
		String dateStr = df.format(date);
		return Integer.valueOf(dateStr);
	}

	private static boolean isNowGreatThanTime(String time) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		String now = df.format(new Date());
		return now.compareTo(time) > 0 ;
	}
}
