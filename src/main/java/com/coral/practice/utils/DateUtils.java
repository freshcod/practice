package com.coral.practice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * @author hb.li
 * @version 创建时间：2016年4月12日 上午10:40:56 
 * 类说明  日期帮助类
 */
public class DateUtils {
	public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public final static String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_YYYYMMDD = "yyyyMMdd";

	public final static String FORMAT_YYYYMM = "yyyyMM";

	public final static String FORMAT_YYYY = "yyyy";

	public final static String FORMAT_YYYYMMDDHHMISS = "yyyyMMdd HH:mm:ss";

	public final static String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public final static String FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_YYYY_MM = "yyyy-MM";

	public final static String FORMAT_DD_HH_MM_SS = "dd HH:mm:ss";

	public final static String FORMAT_HH_MM_SS = "HH:mm:ss";

	public static final int USE_YEAR = 1;

	public static final int USE_MONTH = 2;

	public static final int USE_DAY = 3;

	/**
	 * 获得具体的日期格式对象
	 * @param pattern 格式字符串
	 * @return
	 */
	public static DateFormat getDateFormat(final String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	public static final int getCurrTime() {
		Date d = new Date();
		String dstr = baseFormatDateTime(d, "HHmmss");
		return Integer.parseInt(dstr);
	}
	
	public static final String baseFormatDateTime(Date date, String format) {
		SimpleDateFormat mydate = new SimpleDateFormat(format);
		return mydate.format(date);
	}
	
	public static final String baseFormatDateTime(long millis, String format) {
		return baseFormatDateTime(new Date(millis), format);
	}
	/**
	 * 获得当前年月,格式为YYYY-MM
	 * @return
	 */
	public static String getCurrentMonthYYYY_MM() {
		Date date = new Date();
		return getDateFormat(FORMAT_YYYY_MM).format(date);
	}

	/**
	 * 获得当前年月,格式为YYYYMM
	 * @return
	 */
	public static String getCurrentMonthYYYYMM() {
		Date date = new Date();
		return getDateFormat(FORMAT_YYYYMM).format(date);
	}
	
	/**
	 * 获取当前日期，格式YYYYMMDDHHmmss
	 * @return
	 */
	public static String getDateStr(){
		Date date = new Date();
		return getDateFormat(FORMAT_YYYYMMDDHHMMSS).format(date);
	}
	
	/**
	 * 获得上一个月,格式为YYYY-MM
	 * @return
	 */
	public static String getPreviousMonthYYYY_MM() {
		Calendar calendar = new GregorianCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth - 1 < 1) {
			currentYear--;
			currentMonth = 12 + currentMonth - 1;
			return currentYear + "-" + currentMonth;
		} else {
			currentMonth--;
			if (currentMonth < 10) {
				return currentYear + "-0" + currentMonth;
			} else {
				return currentYear + "-" + currentMonth;
			}

		}
	}

	/**
	 * 获得上一个月,格式为YYYYMM
	 * @return
	 */
	public static String getPreviousMonthYYYYMM() {
		Calendar calendar = new GregorianCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth - 1 < 1) {
			currentYear--;
			currentMonth = 12 + currentMonth - 1;
			return currentYear + "" + currentMonth;
		} else {
			currentMonth--;
			if (currentMonth < 10) {
				return currentYear + "0" + currentMonth;
			} else {
				return currentYear + "" + currentMonth;
			}

		}
	}

	/**
	 * 得到当前日期前或后的N天的日期
	 * @param days 天数
	 * @return
	 */
	public static Date getPreviousOrNextDaysOfNow(int days) {
		return getPreviousOrNextDaysOfDate(new Date(), days);
	}

	/**
	 * 得到当前时间,格式为yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate() {
		Date now = new Date();
		return getDateFormat(FORMAT_YYYY_MM_DD).format(now);
	}

	/**
	 * 得到当前时间,格式为yyyy-MM-dd HH:MM:SS
	 * @return
	 */
	public static String getCurrentDateTime() {
		Date now = new Date();
		return getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS).format(now);
	}
	
	public static Date getCurrentDates(){
		String date = getCurrentDateTime();
		try {
			return getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date formartDate(String formate,String dateStr){
		try {
			return getDateFormat(formate).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 得到本月的第一天(如：得到是2007-04-01 今天是2007-04-28)
	 */
	public static Date getFirstDayOfThisMonth() {
		Calendar nowday = Calendar.getInstance();
		nowday.set(Calendar.DAY_OF_MONTH, 1);
		nowday.set(Calendar.HOUR_OF_DAY, 0);
		nowday.set(Calendar.MINUTE, 0);
		nowday.set(Calendar.SECOND, 0);
		nowday.set(Calendar.MILLISECOND, 0);
		return nowday.getTime();
	}

	/**
	 * 得到本月的前一个月的第一天(如：得到是2007-03-01 今天是2007-04-28)
	 */
	public static Date getLastMonth() {
		Calendar nowday = Calendar.getInstance();
		nowday.setTime(new Date());
		nowday.set(Calendar.DAY_OF_MONTH, 1);
		nowday.add(Calendar.MONTH, -1);
		return nowday.getTime();
	}

	/**
	 * 得到此日期的最后一天
	 * @param calendar
	 * @return
	 * @throws Exception
	 */
	public static Date getLastDayByCalendar(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * 清除日期中的"-"
	 * @param dateStr （如：2007-04-28）
	 * @return 20070428
	 */
	public static String cleanDate(String dateStr) {
		String newDate = "";
		String[] dateStrs = dateStr.split("-");
		if (dateStrs.length >= 2) {
			StringBuffer my_date_temp = new StringBuffer();
			for (int i = 0; i < dateStrs.length; i++) {
				my_date_temp.append(dateStrs[i]);
			}
			newDate = my_date_temp.toString();
		}
		return newDate;
	}

	/**
	 * 时间是否在<code>days</code>内
	 * @param old
	 * @param days
	 * @return
	 */
	public static boolean isDaysInterval(Date old, int days) {
		Calendar now = Calendar.getInstance();
		return (now.getTimeInMillis() - old.getTime()) <= days * 24 * 3600 * 1000;
	}

	/**
	 * 得到中文的当前星期几(如：monday = 星期一)
	 * @return
	 */
	public static String getCurrentWeekOfChinese() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 E");
		return dateFormat.format(new Date());
	}

	/**
	 * 得到<coed>calendar<code>中当前月的第一天
	 * @param calendar
	 * @return
	 */
	public static Date getFirstDayOfTheMonth(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 得到当前日期前或后N天的日期(days为正数为后n天，为负数表示前n天)
	 * @param days 天数
	 * @return
	 */
	public static Date getPreviousOrNextDaysOfDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	public static Date getPreviousOrNextMonthsOfDate(Date date, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public static Date getPreviousOrNextYearsOfDate(Date date, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * @return 当天的开始时间
	 */
	public static Date getBeginOfCurrentDay() {
		return getBeginOfTheDate(new Date());
	}
	
	/**
	 * @return 当天的开始时间
	 */
	public static String getBeginOfCurrentDayStr() {
		return new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD_HH_MM_SS).format(getBeginOfTheDate(new Date())) ;
	}

	/**
	 * @return 当天结束的时间
	 */
	public static Date getEndOfCurrentDay() {
		return getEndOfTheDate(new Date());
	}

	/**
	 * 得到两个日期之间的天数差，包括开始和结束日期(如：beginCalender=2007-10-01，endCalendar=2007-10-20
	 * 结果为：20)
	 * @param beginCalender 开始日期(小的)
	 * @param endCalendar 结束日期(大的)
	 * @return
	 */
	public static long getDifferenceDays(Calendar beginCalender, Calendar endCalendar) {
		long days = (endCalendar.getTimeInMillis() - beginCalender.getTimeInMillis()) / (24 * 60 * 60 * 1000);
		days = days + 1;
		return days;
	}

	/**
	 * 获取一年的开始时间
	 * @return
	 */
	public static Date getBeginOfCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getPreviousOrNextMinutesOfDate(Date date, int minute) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.MINUTE, minute);
		return now.getTime();
	}

	public static String getDateIntervalOfDate(Date old) {
		Date current = new Date();
		long interval = current.getTime() - old.getTime();
		interval = interval / (1000 * 60);// 分钟
		String unit = null;
		if (interval < 60) {
			if (interval <= 0) {
				interval = 1;
			}
			unit = "分钟";
		} else if (interval < (24 * 60)) {
			interval = interval / 60;
			unit = "小时";
		} else if (interval < (31 * 24 * 60)) {
			interval = interval / (24 * 60);
			unit = "天";
			if (interval >= 28) {
				Calendar oldCalendar = Calendar.getInstance();
				oldCalendar.setTime(old);
				// 大小月及润月
				if (oldCalendar.getActualMaximum(Calendar.DAY_OF_MONTH) <= interval) {
					interval = 1;
					unit = "月";
				}
			}

		} else if (interval < (366 * 24 * 60)) {
			interval = interval / (30 * 24 * 60);
			unit = "月";
		} else {
			interval = interval / (366 * 24 * 60);
			unit = "年";
		}
		return interval + unit + "前";
	}

	/**
	 * 得到当月的最后时间
	 */
	public static Date getLastDateOfTheMonth(Date date) {
		Calendar newday = Calendar.getInstance();
		newday.setTime(date);
		newday.set(Calendar.DAY_OF_MONTH, newday.getActualMaximum(Calendar.DAY_OF_MONTH));
		newday.set(Calendar.HOUR_OF_DAY, 23);
		newday.set(Calendar.MINUTE, 59);
		newday.set(Calendar.SECOND, 59);
		newday.set(Calendar.MILLISECOND, 999);
		return newday.getTime();
	}

	/**
	 * 得到当天的开始
	 */
	public static Date getBeginOfTheDate(Date date) {
		Calendar nowday = Calendar.getInstance();
		nowday.setTime(date);
		nowday.set(Calendar.HOUR_OF_DAY, 0);
		nowday.set(Calendar.MINUTE, 0);
		nowday.set(Calendar.SECOND, 0);
		nowday.set(Calendar.MILLISECOND, 0);
		return nowday.getTime();
	}

	/**
	 * 指定日的最后时间
	 */
	public static Date getEndOfTheDate(Date date) {
		Calendar nowday = Calendar.getInstance();
		nowday.setTime(date);
		nowday.set(Calendar.HOUR_OF_DAY, 23);
		nowday.set(Calendar.MINUTE, 59);
		nowday.set(Calendar.SECOND, 59);
		nowday.set(Calendar.MILLISECOND, 998);//Sql Server BUG
		return nowday.getTime();
	}

	public static List<String> getAreaTime(String beginDate, String endDate) throws Exception {
		List<String> timeList = new ArrayList<String>();
		if (endDate == null || "".equals(endDate)) {
			timeList.add(beginDate);
			return timeList;
		}
		// 日
		if (beginDate.length() <= 7) {
			return getAreaMonthTime(beginDate, endDate);
			// 月
		} else {
			return getAreaDayTime(beginDate, endDate);
		}
	}

	private static List<String> getAreaMonthTime(String beginMonth, String endMonth) throws Exception {
		Date parsedBeginMonth = getDateFormat(FORMAT_YYYY_MM).parse(beginMonth);
		String tempStr = "";
		List<String> timeList = new ArrayList<String>();
		int months = 0;
		while (!endMonth.equals(tempStr)) {
			tempStr = getDateFormat(FORMAT_YYYY_MM).format(getPreviousOrNextMonthsOfDate(parsedBeginMonth, months));
			timeList.add(tempStr);
			months++;
		}
		return timeList;
	}

	public static List<String> getAreaDayTime(String beginDate, String endDate) throws Exception {
		Date parsedBeginMonth = getDateFormat(FORMAT_YYYY_MM_DD).parse(beginDate);
		String tempStr = "";
		List<String> timeList = new ArrayList<String>();
		int months = 0;
		while (!endDate.equals(tempStr)) {
			tempStr = getDateFormat(FORMAT_YYYY_MM_DD).format(getPreviousOrNextDaysOfDate(parsedBeginMonth, months));
			timeList.add(tempStr);
			months++;
		}
		return timeList;
	}

	/**
	 * 得到前一天 如：得到是20070426 今天是20070427)
	 */
	public static Long getPreviousDay() {
		Date date = getPreviousOrNextDaysOfDate(new Date(), -1);
		Long time = new Long(getDateFormat(FORMAT_YYYYMMDD).format(date));
		return time;
	}

	/**
	 * 获得某月前后几个月的月份,格式为YYYYMM
	 * @return
	 */
	public static String getPreviousOrNextMonthsOfTheMonth(String date, int n) throws Exception {
		Calendar calendar = new GregorianCalendar();
		DateFormat dateFormat = getDateFormat(FORMAT_YYYYMM);
		calendar.setTime(dateFormat.parse(date));
		calendar.add(Calendar.MONTH, n);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 获得某日前后几天的日期,格式为YYYYMMDD
	 * @return
	 */
	public static String getPreviousOrNextDaysOfTheDay(String date, int days) throws Exception {
		Calendar calendar = new GregorianCalendar();
		DateFormat dateFormat = getDateFormat(FORMAT_YYYYMMDD);
		calendar.setTime(dateFormat.parse(date));
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * 得到当月第一个天 yyyyMMdd
	 * @return
	 */
	public static Long getFirstDayOfCurrentMonth() {
		Date date = getFirstDayOfThisMonth();
		String dateStr = getDateFormat(FORMAT_YYYYMMDD).format(date);
		return new Long(dateStr);
	}

	/**
	 * 得到当年的第一个月yyyyMM
	 * @return
	 */
	public static Long getFirstMonthOfNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, 0);
		Long time = new Long(getDateFormat(FORMAT_YYYYMM).format(calendar.getTime()));
		return time;
	}

	/**
	 * 获得当前日
	 * @return
	 */
	public static Integer getCurrentDay() {
		Date date = new Date();
		return Integer.parseInt(getDateFormat(FORMAT_YYYYMMDD).format(date));
	}

	/**
	 * 获得当前月
	 * @return
	 */
	public static Integer getCurrentMonth() {
		Date date = new Date();
		return Integer.parseInt(getDateFormat(FORMAT_YYYYMM).format(date));
	}

	/**
	 * 获得当前年
	 * @return
	 */
	public static Integer getCurrentYear() {
		Date date = new Date();
		return Integer.parseInt(getDateFormat(FORMAT_YYYY).format(date));
	}

	/**
	 *
	 * @param day
	 * @return
	 */
	public static Integer getKpiDbAnyOffsetDayOfNow(int day) {
		Calendar nowday = Calendar.getInstance();
		nowday.set(Calendar.DAY_OF_YEAR, nowday.get(Calendar.DAY_OF_YEAR) + day);
		return Integer.parseInt(getDateFormat(FORMAT_YYYYMMDD).format(nowday.getTime()));
	}

	/**
	 * 得到下年的当月得上一个月 格式yyyyMM
	 */
	public static Long getPreviousMonthOfNextYear() {
		Calendar nowday = Calendar.getInstance();
		nowday.setTime(new Date());
		nowday.set(Calendar.DAY_OF_MONTH, 1);
		nowday.add(Calendar.YEAR, +1);
		nowday.add(Calendar.MONTH, -1);
		Long time = Long.valueOf(getDateFormat(FORMAT_YYYYMM).format(nowday.getTime()));
		return time;
	}

	/**
	 * 得到下月当日 ，格式 yyyyMMdd
	 * @return
	 */
	public static Long getTheSameDayOfTheNextMonth(Date date) {
		Date next = getPreviousOrNextMonthsOfDate(date, 1);
		Long time = Long.valueOf(getDateFormat(FORMAT_YYYYMMDD).format(next));
		return time;
	}

	/**
	 * 得到下月前一天 ，格式 yyyyMMdd
	 * @return
	 */
	public static Long getTheSameDayOfNextMonth() {
		return getTheSameDayOfTheNextMonth(new Date());
	}

	/**
	 * 得到上年 格式yyyy
	 */
	public static Long getPreviousYear() {
		Calendar nowday = Calendar.getInstance();
		nowday.setTime(new Date());
		nowday.add(Calendar.YEAR, -1);
		Long time = new Long(getDateFormat(FORMAT_YYYY).format(nowday.getTime()));
		return time;
	}

	/**
	 * 通过指定类型找出是对日，还是月或年的日期集合。
	 * @param paramDate
	 * @param paramter
	 * @param type
	 * @return
	 */
	public static List<String> getTimeListByParameter(Date paramDate, int paramter, int type) {
		if (type == USE_YEAR) {
			Date newDate = getPreviousOfNextYearOfDate(paramDate, -paramter);
			return getDaysBetweenDate(paramDate, newDate);
		} else if (type == USE_MONTH) {
			Date newDate = getPreviousOrNextMonthsOfDate(paramDate, paramter);
			return getDaysBetweenDate(paramDate, newDate);
		} else {
			return getDaysByBeginDate(paramDate, paramter);
		}
	}

	private static Date getPreviousOfNextYearOfDate(Date date, int year) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.add(Calendar.YEAR,year);
		return now.getTime();
	}

	private static List<String> getDaysBetweenDate(Date paramDate, Date newDate) {
		DateFormat dateFormat = getDateFormat(FORMAT_YYYYMMDD);
		String formatNewDate = dateFormat.format(newDate);
		String tempStr = "";
		List<String> timeList = new ArrayList<String>();
		int days = 0;
		while (!formatNewDate.equals(tempStr)) {
			tempStr = dateFormat.format(getPreviousOrNextDaysOfDate(paramDate, -days));
			timeList.add(tempStr);
			days++;
		}
		return timeList;
	}
	
	 public static int daysBetween(String smdate,String bdate) {  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        long between_days = 0;
        try {
			cal.setTime(sdf.parse(smdate));
			long time1 = cal.getTimeInMillis();                 
	        cal.setTime(sdf.parse(bdate));    
	        long time2 = cal.getTimeInMillis();         
	        between_days=(time2-time1)/(1000*3600*24);  
		} catch (ParseException e) {
			e.printStackTrace();
		}    
       
       return Integer.parseInt(String.valueOf(between_days));     
    }
	
	 
	public static String getDateAfter(String date,int days){
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		try {
			rightNow.setTime(sim.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

        rightNow.add(Calendar.DAY_OF_MONTH, days);
        String endDate = sim.format(rightNow.getTime()); 
        return endDate;
	}

	private static List<String> getDaysByBeginDate(Date paramDate, int paramter) {
		List<String> timeList = new ArrayList<String>();
		for (int i = 1; i <= paramter; i++) {
			timeList.add(getDateFormat(FORMAT_YYYYMMDD).format(getPreviousOrNextDaysOfDate(paramDate, -i)));
		}
		return timeList;
	}

	/**
	 * 得到当年的第一个月 yyyyMM
	 */
	public static Long getFirstMonthOfCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,0);
		Long time = new Long(getDateFormat(FORMAT_YYYYMM).format(calendar.getTime()));
		return time;
	}

	/**
	 * 得到当前日期前或后N月的日期(months为正数为后n月，为负数表示前n月)
	 * @param months 月数
	 * @return
	 */
	public static Date getPreviousOrNextMonthsOfDateTime(Date date, int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	/**
	 * 获得某一月的上一个月,格式为YYYY-MM
	 * @return
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static String getPreviousMonthYYYY_MM(String yyyy_MM) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date date = new SimpleDateFormat(FORMAT_YYYY_MM).parse(yyyy_MM);
		calendar.setTime(date);
		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		if (currentMonth - 1 < 1) {
			currentYear--;
			currentMonth = 12 + currentMonth - 1;
			return currentYear + "" + currentMonth;
		} else {
			currentMonth--;
			if (currentMonth < 10) {
				return currentYear + "-0" + currentMonth;
			} else {
				return currentYear + "-" + currentMonth;
			}

		}
	}

	/**
	 * 得到某日期前或后的N天的日期 格式为YYYY-MM-DD
	 * @param days 天数
	 * @return
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static String  getPreviousOrNextDaysOfNow(String yyyy_MM_DD,int days) throws ParseException {
		Date date = getDateFormat(FORMAT_YYYY_MM_DD).parse(yyyy_MM_DD);
		Date result = getPreviousOrNextDaysOfDate(date, days);
		return getDateFormat(FORMAT_YYYY_MM_DD).format(result);
	}

	public static Date getBeginOfThePreviousOrNextMonths(int month){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 得到当前日期前或后N天的工作日(days为正数为后n天，为负数表示前n天)
	 * 只去掉周六、周日
	 * @param days 天数
	 * @return
	 */
	public static Date getPreviousOrNextWorkDaysOfDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int step = 0;
		if(days>0){
			step = 1;
		}else{
			step = -1;
		}
		for(int i = 0; i < Math.abs(days) ; ){
			calendar.add(Calendar.DAY_OF_YEAR, step);
			if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
				continue;
			}
			i++;
		}
		return calendar.getTime();
	}

	public static Date parseStringToDate(String timeStr, String format) {
		DateFormat sdf = getDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static boolean isVaildDate(String timeStr){
		boolean isVaildDate = true;
		DateFormat sdf = getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		try {
			sdf.parse(timeStr);
		} catch (ParseException e) {
			sdf = getDateFormat(FORMAT_YYYY_MM_DD);
			try {
				sdf.parse(timeStr);
			} catch (ParseException e1) {
				isVaildDate = false;
			}
		}
		return isVaildDate;
	}
	
	public static void main(String[] args) {
		System.out.println(isVaildDate("2015-10-12 11:22:00"));
	}
	
	public static long getTimeOfDate(String timeStr){
		DateFormat sdf = getDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		Date date = null;
		try {
			date = sdf.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}
	/**
	 * 转换日期格式
	 * @param inDate 日期
	 * @param beforeFormate 之前日期格式化
	 * @param formate 格式化
	 * @return 转换后的日期
	 */
	public static String convertDate(String inDate,String beforeFormate,String formate){
		SimpleDateFormat sdf = new SimpleDateFormat(beforeFormate);
		try {
			Date date = sdf.parse(inDate);
			sdf = new SimpleDateFormat(formate);
			inDate = sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return inDate;
	}
	
	
}
