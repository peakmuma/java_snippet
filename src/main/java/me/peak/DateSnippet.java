package me.peak;

import org.apache.logging.log4j.util.Strings;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.*;

public class DateSnippet {

	static WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);

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

//		System.out.println(getThePastFewDay(19));
//		System.out.println(getFirstDayOfMonth());

//		System.out.println(getThePastFewDay(1, "yyyyMMdd"));
		LocalDate date = LocalDate.of(2020, 2, 28);
		LocalDate sameWeek = getSameWeekLastYear(date);
		System.out.println(date.get(weekFields.weekOfYear()));
		System.out.println(date.get(weekFields.dayOfWeek()));
		System.out.println(sameWeek.get(weekFields.weekOfYear()));
		System.out.println(sameWeek.get(weekFields.dayOfWeek()));
		System.out.println(sameWeek.format(DateTimeFormatter.ISO_LOCAL_DATE));



//		LocalDate date = LocalDate.now().minusDays(1);
//		testCase(date);
//		testCase(LocalDate.of(2022,7,30));
//		testCase(date.minusWeeks(1));
//		testCase(date.minusMonths(1));
//		testCase(LocalDate.of(2021,2,5));

	}

	static LocalDate getSameWeekLastYear(LocalDate localDate) {
		int weekOfYear = localDate.get(weekFields.weekOfYear());
		int dayOfWeek = localDate.getDayOfWeek().getValue();
		return localDate.minusYears(1).with(weekFields.weekOfYear(), weekOfYear).with(weekFields.dayOfWeek(), dayOfWeek);
	}


	public static void testCase(LocalDate date) {
		System.out.println(date.format(formatter));
		System.out.println(getDuration("week.current".split("\\."), date));
		System.out.println(getDuration("week.before.1".split("\\."), date));
		System.out.println(getDuration("week.before.2".split("\\."), date));
		System.out.println(getDuration("week.lastTotalWeek.1".split("\\."), date));
		System.out.println(getDuration("week.lastTotalWeek.2".split("\\."), date));
		System.out.println(getDuration("wtd.current".split("\\."), date));
		System.out.println(getDuration("wtd.before.1".split("\\."), date));
		System.out.println(getDuration("wtd.before.2".split("\\."), date));
		System.out.println(getDuration("weekstart.current".split("\\."), date));
		System.out.println(getDuration("weekstart.before.1".split("\\."), date));
		System.out.println(getDuration("weekstart.before.2".split("\\."), date));
		System.out.println(getDuration("weekstart.lastTotalWeek.1".split("\\."), date));
		System.out.println(getDuration("weekstart.lastTotalWeek.2".split("\\."), date));
		System.out.println(getDuration("weekend.current".split("\\."), date));
		System.out.println(getDuration("weekend.before.1".split("\\."), date));
		System.out.println(getDuration("weekend.before.2".split("\\."), date));
		System.out.println(getDuration("weekend.lastTotalWeek.1".split("\\."), date));
		System.out.println(getDuration("weekend.lastTotalWeek.2".split("\\."), date));
		System.out.println(getDuration("month.current".split("\\."), date));
		System.out.println(getDuration("month.before.1".split("\\."), date));
		System.out.println(getDuration("month.before.2".split("\\."), date));
		System.out.println(getDuration("mtd.current".split("\\."), date));
		System.out.println(getDuration("mtd.before.1".split("\\."), date));
		System.out.println(getDuration("mtd.before.2".split("\\."), date));
		System.out.println(getDuration("monthTarget.current".split("\\."), date));
		System.out.println(getDuration("monthTarget.before.1".split("\\."), date));
		System.out.println(getDuration("monthTarget.before.2".split("\\."), date));
		System.out.println();
	}


	public static String getDuration(String[] expressionArray, LocalDate date) {
		System.out.print(String.join(".", expressionArray) + " ");
		LocalDate beginDate = date, endDate = date, today = LocalDate.now(), yesterday = today.minusDays(1);

		//开始时间，结束的初始值
		if ("wtd".equals(expressionArray[0])) {
			//先判断入参是否小于当前的周一
			LocalDate currentMonday = today.with(ChronoField.DAY_OF_WEEK, 1);
			if (date.isBefore(currentMonday)) {
				return null;
			}
			beginDate = date.with(ChronoField.DAY_OF_WEEK, 1);
			endDate = yesterday;
		} else if ("week".equals(expressionArray[0])) {
			beginDate = date.with(ChronoField.DAY_OF_WEEK, 1);
			endDate = date.with(ChronoField.DAY_OF_WEEK, 7);
		} else if ("weekstart".equals(expressionArray[0])) {
			beginDate = date.with(ChronoField.DAY_OF_WEEK, 1);
			endDate = date.with(ChronoField.DAY_OF_WEEK, 5);
		} else if ("weekend".equals(expressionArray[0])) {
			beginDate = date.with(ChronoField.DAY_OF_WEEK, 6);
			endDate = date.with(ChronoField.DAY_OF_WEEK, 7);
		} else if ("mtd".equals(expressionArray[0]) || "monthTarget".equals(expressionArray[0])) {
			beginDate = date.with(ChronoField.DAY_OF_MONTH, 1);
			endDate = date.with(ChronoField.DAY_OF_MONTH, date.lengthOfMonth());
			if (endDate.isAfter(yesterday)) {
				endDate = yesterday;
			}
		} else if ("month".equals(expressionArray[0])) {
			beginDate = date.with(ChronoField.DAY_OF_MONTH, 1);
			endDate = date.with(ChronoField.DAY_OF_MONTH, date.lengthOfMonth());
		}

		//将开始时间和结束向前推n周或者n月
		if ("before".equals(expressionArray[1])) {
			int count = Integer.parseInt(expressionArray[2]);
			if ("wtd".equals(expressionArray[0])
					|| "week".equals(expressionArray[0])
					|| "weekstart".equals(expressionArray[0])
					|| "weekend".equals(expressionArray[0])) {
				beginDate = beginDate.minusWeeks(count);
				endDate = endDate.minusWeeks(count);
			} else if ("month".equals(expressionArray[0])) {
				beginDate = beginDate.minusMonths(count);
				endDate = endDate.minusMonths(count);
				endDate = endDate.with(ChronoField.DAY_OF_MONTH, endDate.lengthOfMonth());
			} else if ("mtd".equals(expressionArray[0]) || "monthTarget".equals(expressionArray[0])) {
				boolean isMtdCurrentEnd = (endDate.getDayOfMonth() == endDate.lengthOfMonth());
				beginDate = beginDate.minusMonths(count);
				endDate = endDate.minusMonths(count);
				if (isMtdCurrentEnd) {
					endDate = endDate.with(ChronoField.DAY_OF_MONTH, endDate.lengthOfMonth());
				}
			}
		} else if ("lastTotalWeek".equals(expressionArray[1])) {
			int count = Integer.parseInt(expressionArray[2]);
			LocalDate currentMonday = today.with(ChronoField.DAY_OF_WEEK, 1);
			if (date.isBefore(currentMonday)) {
				count--;
			}
			beginDate = beginDate.minusWeeks(count);
			endDate = endDate.minusWeeks(count);
		}

		//如果开始时间大于昨天，返回空
		if (beginDate.isAfter(yesterday)) {
			return null;
		}
		//如果结束日期大于昨天，将结束日期设置为昨天
		if (endDate.isAfter(yesterday)) {
			endDate = yesterday;
		}
		if (beginDate.isAfter(endDate)) {
			return null;
		}
		return getDuration(beginDate, endDate);
	}

	public static String getDuration(LocalDate beginDate, LocalDate endDate) {
		if (beginDate == null || endDate == null) {
			return null;
		}
		if (beginDate.isAfter(endDate)) {
			return null;
		}
		return beginDate.format(formatter) + "-" + endDate.format(formatter);
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

	public static String getThePastFewDay(int num, String format) {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.DATE, 32);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(now.getTime());
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
