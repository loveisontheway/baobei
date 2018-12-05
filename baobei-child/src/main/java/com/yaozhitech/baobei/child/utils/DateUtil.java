package com.yaozhitech.baobei.child.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	// 格式 HH 必须大写，否则按照12小时制
	private static String shortPattern = "yyyy-MM-dd";
	private static String longPattern = "yyyy-MM-dd HH:mm:ss";
	// private static String longPattern_CHN = "yyyy年MM月dd日 HH:mm:ss";
	private static Logger logger = Logger.getLogger(DateUtil.class);
	private static SimpleDateFormat longDateFormat = new SimpleDateFormat(
			longPattern);
	// private static SimpleDateFormat longDateFormat_CHN = new
	// SimpleDateFormat(
	// longPattern_CHN);
	private static SimpleDateFormat shortDateFormat = new SimpleDateFormat(
			shortPattern);

	/**
	 * 日期转换str->date
	 * 
	 * @param dateStr
	 *            default:yyyy-MM-dd
	 * @param pattern
	 * @return
	 */
	public static String  add2StrDate(int t){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dt =new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.DAY_OF_YEAR,t);
		Date dt1=rightNow.getTime();
        String reStr = sdf.format(dt1);
		return reStr;
	}
	public static Date str2Date(String dateStr, String pattern) {
		if (Util.isNull(dateStr)) {
			return null;
		}
		Date date = null;
		SimpleDateFormat format = null;
		if (pattern == null || ("").equals(pattern)) {
			format = shortDateFormat;
		} else {
			format = new SimpleDateFormat(pattern, Locale.CHINESE);
		}
		try {
			synchronized (format) {
				// SimpleDateFormat is not thread safe
				date = format.parse(dateStr);
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return date;
	}

	/**
	 * 日期转换 ts->str
	 * 
	 * @param ts
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String timestamp2Str(Timestamp ts) {
		if (ts != null) {
			synchronized (longDateFormat) {
				return longDateFormat.format(ts);
			}
		}
		return null;
	}

	/**
	 * 日期转换 date->str
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2Str(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 根据出生日期算几岁
	 * 
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

	public static String getTimestampYmdhms() {

		Calendar cal = Calendar.getInstance();

		String year = Integer.toString(cal.get(Calendar.YEAR));
		String month = Integer.toString(cal.get(Calendar.MONTH) + 1);
		String day = Integer.toString(cal.get(Calendar.DATE));
		String hour = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		String minute = Integer.toString(cal.get(Calendar.MINUTE));
		String second = Integer.toString(cal.get(Calendar.SECOND));
		String m = Integer.toString(cal.get(Calendar.MILLISECOND));

		StringBuffer ret = new StringBuffer();
		ret.append(year).append("-");
		ret.append(month.length() == 1 ? "0" + month : month).append("-");
		ret.append(day.length() == 1 ? "0" + day : day).append(" ");
		ret.append(hour.length() == 1 ? "0" + hour : hour).append(":");
		ret.append(minute.length() == 1 ? "0" + minute : minute).append(":");
		ret.append(second.length() == 1 ? "0" + second : second);
		ret.append(":" + m);
		return ret.toString();
	}

	public static String toYMDHMS(String argYmdHMS) {

		if (argYmdHMS == null || argYmdHMS.length() == 0) {
			return null;
		}
		String strTmpDate = argYmdHMS.replaceAll("-", "");
		strTmpDate = strTmpDate.replaceAll("/", "");
		strTmpDate = strTmpDate.replaceAll(":", "");
		strTmpDate = strTmpDate.replaceAll(" ", "");
		return strTmpDate;
	}

	/**
	 * 得到类似微博的时间格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getShortTime(Date date) {
		String shortstring = null;

		if (date == null)
			return shortstring;

		long now = Calendar.getInstance().getTimeInMillis();
		long deltime = (now - date.getTime()) / 1000;
		if (deltime > 2 * 24 * 60 * 60) {
			shortstring = date2Str(date, "MM月dd日 HH:mm");
		} else if (deltime > 24 * 60 * 60) {
			shortstring = "昨天 " + date2Str(date, "HH:mm");
		} else if (deltime > 60 * 60) {
			shortstring = (int) (deltime / (60 * 60)) + "小时前";
		} else if (deltime > 60) {
			shortstring = (int) (deltime / (60)) + "分钟前";
		} else if (deltime > 1) {
			shortstring = deltime + "秒前";
		} else {
			shortstring = "刚刚";
		}
		return shortstring;
	}

	/**
	 * 获取当前日期的第一天
	 * 
	 * @param date
	 *            当前日期
	 * @return 当前日期的第一天
	 */
	public static Date getFirstDayByDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 日期提前或者推迟N天
	 * 
	 * @param date
	 *            日期
	 * @param amount
	 *            计量数
	 * @return 处理后的日期
	 */
	public static Date addDate(Date date, int amount) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 求2个日期之间的差值 a-b
	 * 
	 * @param aDate
	 * @param bDate
	 * @return
	 */
	public static long subDates(Date aDate, Date bDate) {
		long sub = 0;

		if (aDate != null && bDate != null) {
			sub = aDate.getTime() - bDate.getTime();
		}

		return sub;
	}

	public static Date string2sdate(String input) throws ParseException {
		Date result = null;

		if (!StringUtils.isBlank(input)) {
			Calendar c = Calendar.getInstance();
			c.setTime(longDateFormat.parse(input));
			c.set(Calendar.HOUR, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			result = c.getTime();
		}

		return result;
	}

	public static Date string2edate(String input) throws ParseException {
		Date result = null;

		if (!StringUtils.isBlank(input)) {
			Calendar c = Calendar.getInstance();
			c.setTime(longDateFormat.parse(input));
			c.set(Calendar.HOUR, 23);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			result = c.getTime();
		}

		return result;
	}

	/**
	 * 获得时间段内的随机日期
	 * 
	 * @param beginDateString
	 * @param endDateString
	 * @return
	 */
	public static String getRandomDate(String beginDateString,
			String endDateString) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Date beginDate = sdf.parse(beginDateString);
			Date endDate = sdf.parse(endDateString);

			long timeRange = (endDate.getTime() - beginDate.getTime());

			long millisecondAdd = (long) (Math.random() * timeRange);

			Date randomDate = new java.sql.Date(beginDate.getTime()
					+ millisecondAdd);

			return randomDate.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 闰年判断
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {

		GregorianCalendar calendar = new GregorianCalendar();

		return calendar.isLeapYear(year);

	}

	/**
	 * 
	 * @param date
	 *            default format(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static String toString(Date date) {
		if (date != null) {
			synchronized (longDateFormat) {
				return longDateFormat.format(date);
			}
		}
		return null;
	}

	public static String toString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat shortDateFormat = new SimpleDateFormat(pattern);
			return shortDateFormat.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return str2Date(str.toString(), "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(
				" 00:00:00");
		return str2Date(str.toString(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 当周第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		String dateStr = date2Str(cal.getTime(), "yyyy-MM-dd") + " 00:00:00";
		return str2Date(dateStr, "yyyy-MM-dd HH:mm:ss");

	}

	/**
	 * 当周最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		String dateStr = date2Str(cal.getTime(), "yyyy-MM-dd") + " 23:59:59";
		return str2Date(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 只保留日期，去掉时分秒等
	 * @param date 日期
	 * @return 日期
	 */
	public static Date getShortDate(Date date){
		String dateStr = shortDateFormat.format(date);
		return DateUtil.str2Date(dateStr, shortPattern);
	}
	
	/**
	 * 获取提前的日期
	 * @param date 时间日期
	 * @param beforeDays 提前天数
	 * @return 提前的日期
	 */
	public static Date getDate(Date date, int beforeDays){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR , dayOfYear - beforeDays);
		return cal.getTime();
	}
	
	/**
	 * 根据出生日期算几岁
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static String getAgeString(Date birthDay){		
		if (birthDay == null) {
			return "";
		}
		
		Calendar cal = Calendar.getInstance();

//		if (cal.before(birthDay)) {
//			return "备孕中";
//		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;
		int month = monthNow - monthBirth + 1;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow<monthBirth
				age--;
				month += 12;
			}
		} else {
			// monthNow>monthBirth
			//month += 12;
		}
		
		
		if (age == 0) {
			return "宝宝" + month + "个月";
		}else if(age < 0){
			return "备孕中";
		}

		return "宝宝" + age + "岁";
	}
	
	/**
	 * 计算两个时间的间隔天数
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return 间隔天数
	 */
	public static int getIntervalDays(Date beginDate, Date endDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginDate);
        long beginTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        Long intervalDays = new Long(0);
        if(endTime >= beginTime){
        	intervalDays = (endTime - beginTime)/(1000*3600*24) + 1;
        }else{
        	intervalDays = (endTime - beginTime)/(1000*3600*24) - 1;
        }
        return intervalDays.intValue();
	}
	
	/**
	 * 判断是否为日期
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static boolean isDate(String date, String pattern){
		try {
			SimpleDateFormat format=new SimpleDateFormat(pattern);
			format.parse(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断是否为日期
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date)
    {
        /**
         * 判断日期格式和范围
         */
        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
         
        Pattern pat = Pattern.compile(rexp);  
         
        Matcher mat = pat.matcher(date);  
         
        boolean dateType = mat.matches();

        return dateType;
    }
	
	public static void main(String[] args) {
		
		System.out.println(getIntervalDays(str2Date("2017-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss"), str2Date("2017-01-02 00:00:00", "yyyy-MM-dd HH:mm:ss")));
	}
}
