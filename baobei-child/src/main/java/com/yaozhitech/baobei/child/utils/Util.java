package com.yaozhitech.baobei.child.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	private static Random random = new Random();

	public static String getRadomNum(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append((int) (10 * (Math.random())));
		}
		return sb.toString();
	}
	
	public static String getRandomFromVector(Vector<String> source) {
		// now we select a prefix randomly
		int prefixSetSize = source.size();
		int prefixIndex = (int) (Math.random() * prefixSetSize);
		String prefixString = source.get(prefixIndex);
		return prefixString;
	}

	public static int getRandomIntFromVector(Vector<Integer> source) {
		// now we select a prefix randomly
		int prefixSetSize = source.size();
		int prefixIndex = (int) (Math.random() * prefixSetSize);
		int myInt = source.get(prefixIndex);
		return myInt;
	}

	public static String getRandomFromArray(String[] source) {
		// now we select a prefix randomly
		int prefixSetSize = source.length;
		int prefixIndex = (int) (Math.random() * prefixSetSize);
		String prefixString = source[prefixIndex];
		return prefixString;
	}

	public static int getRandomInt(int start, int end) {
		int rangeSize = end - start;
		return random.nextInt(rangeSize) + start;
	}

	/**
	 * 返回一个随机正数
	 * 
	 * @return
	 */
	public static int getRandomInt() {
		int randomInt = random.nextInt();
		if (randomInt == Integer.MIN_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return Math.abs(randomInt);
		}
	}

	/**
	 * 
	 * @param start
	 *            包含
	 * @param end
	 *            不包含
	 * @return
	 */
	public static int getRandomInteger(int start, int end) {
		int rangeSize = end - start;
		int index = (int) (Math.random() * rangeSize) + start;
		return index;
	}

	public static long getRandomLong(long start, long end) {
		long rangeSize = end - start;
		long index = (long) (Math.random() * rangeSize) + start;
		return index;
	}

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

	// prob has same size as source
	public static Object getRandomObjectByProb(Object[] source, int[] prob) {

		// now we select a prefix randomly
		int sourceSetSize = source.length;
		int sumWeight = 0;
		int i = 0;
		for (i = 0; i < sourceSetSize; i++) {
			sumWeight += prob[i];
			// System.out.println("in " + i + ":" + source[i] + ":" + prob[i]);
		}
		int[] accumulation = new int[sourceSetSize + 1];

		accumulation[0] = 0;
		accumulation[sourceSetSize] = sumWeight;
		int index = 1;
		while (index < sourceSetSize) {
			accumulation[index] = accumulation[index - 1] + prob[index - 1];
			// System.out.println("in2 " + index + ":" + accumulation[index]);
			index++;
		}
		int random100 = (int) (Math.random() * sumWeight);
		// System.out.println("random100 : " + random100);
		index = 0;

		while (index < sourceSetSize) {
			if ((accumulation[index] <= random100)
					&& (random100 < accumulation[index + 1])) {
				return source[index];
			}
			index++;
		}

		return null;
	}

	public static String getRandomDigitString(int length) {
		StringBuilder suffixString = new StringBuilder();
		int i = 0;
		for (i = 0; i < length; i++) {
			suffixString.append((int) (Math.random() * 10));
		}
		return suffixString.toString();
	}

	public static int getRandomIntFromHashMap(HashMap<Integer, Integer> source) {
		int size = source.size();
		Integer[] idArray = new Integer[size];
		int[] valueArray = new int[size];

		Iterator<Integer> iter = source.keySet().iterator();
		int index = 0;
		while (iter.hasNext()) {
			int id = iter.next();
			int value = source.get(id);
			idArray[index] = id;
			valueArray[index] = value;
			// System.out.println(index + ":" + id + ":" + value);
			index++;
		}
		Integer returnInt = (Integer) getRandomObjectByProb(idArray, valueArray);
		return returnInt;
	}

	public static int getAge(String birthDay) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = df.parse(birthDay);
			Date nowDate = new Date();
			return (int) ((nowDate.getTime() - d.getTime()) / ((long) 365 * 24 * 60 * 60 * 1000));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// to replace the parameter like ${1} in the string by value
	public static String replaceParameter(String source, Vector<String> values) {
		String result = source;
		for (int i = 0; i < values.size(); i++) {
			// System.out.println("${"+(i+1)+"}"+ " : " + values.get(i));
			String value = (String) (values.get(i));
			if ((value.startsWith("[")) && (value.endsWith("]"))) {
				value = value.substring(1, value.length() - 1);
			}
			if ((value.startsWith("(")) && (value.endsWith(")"))) {
				value = value.substring(1, value.length() - 1);
			}
			if (value.startsWith("\\")) {
				value = value.substring(1);
			}
			result = result.replaceAll("\\$\\{" + (i + 1) + "\\}", value);
		}
		return result;
	}

	public static String readStringFromFile(String inputFilename) {
		File file = new File(inputFilename);
		BufferedReader reader = null;
		StringBuilder tempString = new StringBuilder();
		try {

			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				tempString.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return tempString.toString();
	}

	public static String getRandomDateByAge(int age) {
		long addDay = (((long) (age - 1)) * 365) + getRandomInt(1, 365);
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date d = df.parse("2012-08-18");
			Date newDate = new Date(d.getTime()
					- (((long) addDay) * 24 * 60 * 60 * 1000));
			//
			return df.format(newDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isLeapYear(int year) {

		GregorianCalendar calendar = new GregorianCalendar();

		return calendar.isLeapYear(year);

	}

	public static void saveToFile(String destUrl, String fileName) {
		try {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			HttpURLConnection httpconn = null;
			URL url = null;
			int BUFFER_SIZE = 4096;

			byte[] buf = new byte[BUFFER_SIZE];
			int size = 0;

			// 建立链接
			url = new URL(destUrl);
			httpconn = (HttpURLConnection) url.openConnection();
			// 连接指定的资源
			httpconn.connect();
			// 获取网络输入流
			bis = new BufferedInputStream(httpconn.getInputStream());
			// 建立文件

			fos = new FileOutputStream(fileName);

			System.out.println("正在获取链接[" + destUrl + "]的内容\n将其保存为文件["
					+ fileName + "]");

			// 保存文件
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			httpconn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sideTrim(String stream, String trimstr) {

		// null或者空字符串的时候不处理
		if (stream == null || stream.length() == 0 || trimstr == null
				|| trimstr.length() == 0) {
			return stream;
		}

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "[" + trimstr + "]*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(stream).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = new StringBuffer(buffer.substring(epos)).reverse()
					.toString();
		}

		// 去掉开头的指定字符
		matcher = pattern.matcher(stream);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			stream = stream.substring(epos);
		}

		// 返回处理后的字符串
		return stream;
	}

	public static int BKDRHash(String str) {

		int seed = 131; // 31 131 1313 13131 131313 etc..
		int hash = 0;

		for (int i = 0; i < str.length(); i++) {

			hash = (hash * seed) + str.charAt(i);
		}
		return (hash & 0x7FFFFFFF);
	}

	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	public static String removeLinkTags(String content) {
		if (content != null && !("").equals(content)) {
			Pattern p = Pattern.compile("<a.*?/a>");
			Matcher m = p.matcher(content);
			while (m.find()) {
				Matcher href = Pattern.compile("href=.*? ").matcher(m.group());
				while (href.find()) {
					String url = href.group().replaceAll("href=|\"|>", "");
					content = content.replaceAll(m.group(), url);
				}
			}
		}
		return content;
	}

	/**
	 * 统计字串在源字符串中出现的次数
	 *
	 * @param origStr
	 *            源字符串
	 * @param subStr
	 *            子串
	 * @return 字串出现的次数
	 */
	public static int countSubStr(String origStr, String subStr) {
		int count = 0;
		if (origStr != null && !("").equals(origStr) && subStr != null
				&& !("").equals(subStr)) {
			int index = 0;
			while ((index = origStr.indexOf(subStr, index)) != -1) {
				count++;
				index += subStr.length();
			}
		}
		return count;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNull(Object argObj) {

		if (argObj == null) {
			return true;
		}

		if (argObj instanceof String) {

			if (((String) argObj).trim().equals("")
					|| ((String) argObj).trim().equals(" ")
					|| ((String) argObj).trim().equals("null")) {
				return true;
			}
		}

		if (argObj instanceof Collection) {

			if (((Collection) argObj).size() == 0) {
				return true;
			}
		}

		if (argObj instanceof Map) {

			if (((Map) argObj).size() == 0) {
				return true;
			}
		}

		return false;
	}

	public static boolean isNotNull(Object argObj) {
		return !Util.isNull(argObj);
	}

	public static Integer[] parseInt(String[] str) {
		Integer temp[] = new Integer[str.length];
		for (int i = 0; i < str.length; i++) {
			temp[i] = Integer.parseInt(str[i].replace("[", "").replace("]", "")
					.trim());
		}
		return temp;
	}

	public static int[] showPage(int page, int lastpage) {
		int k[];
		if (lastpage <= 10) {
			k = new int[lastpage];
			for (int i = 0; i < lastpage; i++) {
				k[i] = i + 1;
			}
		} else if (page + 10 > lastpage) {
			k = new int[10];
			for (int i = 0; i < 10; i++) {
				k[i] = lastpage - 10 + i + 1;
			}
		} else if (page + 10 <= lastpage && page < 7) {
			k = new int[10];
			for (int i = 0; i < 10; i++) {
				k[i] = i + 1;
			}
		} else if (page + 10 <= lastpage && page >= 7) {
			k = new int[10];
			for (int i = 0; i < 10; i++) {
				k[i] = page - 5 + i;
			}
		} else {
			k = new int[10];
		}
		return k;
	}

	// 获取数组不同数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getNoNum(String[] a, String[] b) {
		List list = new ArrayList();
		if (Util.isNull(b)) {
			for (int i = 0; i < a.length; i++) {
				list.add(a[i]);
			}
		} else {
			String[] la = a;
			String[] lb = b;
			for (int i = 0; i < la.length; i++) {
				boolean boo = false;
				for (int j = 0; j < lb.length; j++) {
					if (la[i].equals(lb[j])) {
						boo = true;
						break;
					}
				}
				if (!boo) {
					list.add(la[i]);
				}
			}
		}
		return list;
	}

	/**
	 * 获取客户端IP
	 *
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 1. byte(8位有符号)的取值范围是 -128-127, char(16位无符号)的取值范围是0-65535;<BR>
	 * 2.如果一个byte对应的十六进制数只有一位前面要用0填充，因为MD5是32位的，达到统一的格式<BR>
	 * 3.0x是十六进制的标示，比如：0xFF表示8位的二进制1111 1111;0x00FF表示16位的二进制0000 0000 1111 1111
	 * */
	public static String getSHA_1Mask(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 过滤html标签
	 *
	 * @param inputString
	 * @return
	 */
	public static String html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;
		Pattern p_html1;
		Matcher m_html1;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String regEx_html1 = "<[^>]+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			m_html1 = p_html1.matcher(htmlStr);
			htmlStr = m_html1.replaceAll(""); // 过滤html标签
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * 过滤html标签 保留A标签
	 *
	 * @param inputString
	 * @return
	 */
	public static String filterHtml2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		Pattern p_script;
		Matcher m_script;
		Pattern p_style;
		Matcher m_style;
		Pattern p_html;
		Matcher m_html;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
			String regEx_html = "(<\\/(?![aA])[a-zA-Z]+>)|(<(?![aA])[ ]?[^\\/>]+>)";// (<(?![aA])[
																					// ]?[^\\/>]+>)

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			// p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
			// m_html1 = p_html1.matcher(htmlStr);
			// htmlStr = m_html1.replaceAll(""); // 过滤html标签
			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		return textStr;// 返回文本字符串
	}

	/**
	 * 验证 <\/br>
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isBlank(String content) {
		boolean isBlank = StringUtils.isBlank(content);

		if (!isBlank) {
			content = content.trim();
			isBlank = content.equals("<br>");
		}

		return isBlank;
	}

	/**
	 * 截取省略
	 * 
	 * @param content
	 *            内容
	 * @param len
	 *            长度
	 * @return 截取后的长度
	 */
	public static String cutOmit(String content, int len) {
		if (content != null && !("").equals(content.trim())) {
			if (content.length() > len) {
				String subStr = content.substring(0, len - 1);
				content = subStr.concat("...");
			}
		}
		return content;
	}

	public static String SpecialCharactersFilter(String content) {
		String filterCharacters = "[!@#$%^&*()_+-=,. /?']";
		content = content.replaceAll(filterCharacters, "");
		// http :// www.baidu.com or 192.168.1.1 :8080 a/ x. html ?dd #maodian
		// String scriptString =
		// "(?<!(href=\"|>))((?:http[s]?|ftp)://)([^:\"<\\s]+|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:\\d+)?(?:((?:\\w+)*/)([\\w\\-\\.]+[^><\"#?\\s]+)?(\\?[^\\s#]*)?(#\\w+)?)?";
		// Pattern p_script = Pattern.compile(scriptString,
		// Pattern.CASE_INSENSITIVE);
		// Matcher m_script = p_script.matcher(content);
		//
		// while (m_script.find()) {
		// String f = m_script.group();
		// System.out.println(f);
		// }

		return content;
	}

	// base64加密
	public static String base64_encodeStr(String plainText) {
		byte[] b = plainText.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	// base64解密
	public static String base64_decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}

	/**
	 * 比当今时间大，返回1 比当今时间小，返回-1 跟当今时间相同，返回0
	 * 
	 * @param date
	 * @return
	 */
	public static int compareWithCurrentTime(Date date, int keepTime) {
		// 秒转为毫秒
		keepTime *= 1000;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int i = 0;
		long l = c.getTimeInMillis() + keepTime - System.currentTimeMillis();
		if (l > 0) {
			i = 1;
		} else if (l < 0) {
			i = -1;
		} else {
			i = 0;
		}

		return i;
	}

	public static int getDayCount(int year, int month) {
		int result = 0;
		if (1 == month || 3 == month || 5 == month || 7 == month || 8 == month
				|| 10 == month || 12 == month) {
			result = 31;
		} else if (4 == month || 6 == month || 9 == month || 11 == month) {
			result = 30;
		} else if (2 == month) {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				result = 29;
			} else {
				result = 28;
			}
		}
		return result;
	}

	public static String getSubStrLen(String str, int len) {
		String result = "";
		if (str.length() > len) {
			result = str.substring(0, len) + "...";
		} else {
			result = str;
		}
		return result;
	}

	/**
	 * 判断是否有js、sql注入，存在，返回true 没有js和sql注入，进行html文本去除，若去除结果为空，返回true
	 * 否则返回false，证明内容很正常，尽情的插入吧！！！
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isInputIllegal(String content) {
		boolean isIllegal = false;
		// 允许内容为空
		if (StringUtils.isBlank(content))
			isIllegal = false;
		// 去除html标签后与原内容不相同，说明包含html标签，属于不合法内容
		if (content.length() != html2Text(content).length())
			isIllegal = true;

		return isIllegal;
	}

	/**
	 * Filter the specified string for characters that are sensitive to HTML
	 * interpreters, returning the string with these characters replaced by the
	 * corresponding character entities.
	 * 
	 * @param value
	 *            The string to be filtered and returned
	 */
	public static String filter(String value) {

		if (value == null || value.length() == 0) {
			return value;
		}

		StringBuilder result = null;
		String filtered = null;
		for (int i = 0; i < value.length(); i++) {
			filtered = null;
			switch (value.charAt(i)) {
			case '<':
				filtered = "&lt;";
				break;
			case '>':
				filtered = "&gt;";
				break;
			case '&':
				filtered = "&amp;";
				break;
			case '"':
				filtered = "&quot;";
				break;
			case '\'':
				filtered = "&#39;";
				break;
			}

			if (result == null) {
				if (filtered != null) {
					result = new StringBuilder(value.length() + 50);
					if (i > 0) {
						result.append(value.substring(0, i));
					}
					result.append(filtered);
				}
			} else {
				if (filtered == null) {
					result.append(value.charAt(i));
				} else {
					result.append(filtered);
				}
			}
		}

		return result == null ? value : result.toString();
	}

	public static String regexpFilter(String s) {
		if (s == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '(':
				sb.append("\\(");
				break;
			case ')':
				sb.append("\\)");
				break;
			case '*':
				sb.append("\\*");
				break;
			case '?':
				sb.append("\\?");
				break;
			case '+':
				sb.append("\\+");
				break;
			case '.':
				sb.append("\\.");
				break;
			case '^':
				sb.append("\\^");
				break;
			case '[':
				sb.append("\\[");
				break;
			case ']':
				sb.append("\\]");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '|':
				sb.append("\\|");
				break;
			case '{':
				sb.append("\\{");
				break;
			case '}':
				sb.append("\\}");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String string2Json(String s) {
		if (s == null) {
			return "";
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			// case '/':
			// sb.append("\\/");
			// break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 获得域名 http:// 和 /之间的部分
	 * 
	 * @param baseUrl
	 * @return
	 */
	public static String getDomain(String baseUrl) {
		String domain = "";

		if (!StringUtils.isBlank(baseUrl)) {
			Pattern pattern = Pattern
					.compile("((?:http[s]?):\\/\\/)([^:\\/\\s]+|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:\\d+)?\\/");
			Matcher matcher = pattern.matcher(baseUrl);
			if (matcher.find()) {
				domain = matcher.group(2);
			}
		}

		return domain;
	}

	/**
	* 验证网址Url
	* 
	* @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	*/
	public static boolean isURL(String str) {
		String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		return match(regex, str);
	}
	
	/**
	* @param regex
	* 正则表达式字符串
	* @param str
	* 要匹配的字符串
	* @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	*/
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 获得 http://10.10.12.45:8080
	 * 
	 * @param baseUrl
	 * @return
	 */
	public static String getBaseLink(String baseUrl, String resRoot) {
		String domain = "";

		if (!StringUtils.isBlank(baseUrl)) {
			Pattern pattern = Pattern
					.compile("((?:http[s]?):\\/\\/)([^:\\/\\s]+|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:\\d+)?\\/");
			Matcher matcher = pattern.matcher(baseUrl);
			if (matcher.find()) {
				domain = matcher.group();
			}
		}

		String secondUrl = "";
		if (!StringUtils.isBlank(resRoot)) {
			Pattern pattern = Pattern
					.compile("((?:http[s]?):\\/\\/)([^:\\/\\s]+|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:\\d+)?\\/([^:\\/\\s]+\\/)");
			Matcher matcher = pattern.matcher(resRoot);
			if (matcher.find()) {
				secondUrl = matcher.group(4);
			}
		}

		return domain + secondUrl;
	}

	/**
	 * sql转义
	 * 
	 * @param value
	 *            The string to be filtered and returned
	 */
	public static String sqlEscape(String value) {

		if (Util.isNull(value)) {
			return value;
		}
		return value.replaceAll("\\\\", "").replaceAll("'", "''")
				.replaceAll("/", "//").replaceAll("%", "/%")
				.replaceAll("_", "/_");
	}

	/**
	 * spifix转义
	 * 
	 * @param value
	 *            The string to be filtered and returned
	 */
	public static String spEscape(String value) {

		if (Util.isNull(value)) {
			return value;
		}
		return value.replace("$", "").replace("^", "");
	}

	/**
	 * 计算字数 汉字算1个 英文字算半个 向上取整
	 * 
	 * @param str
	 * @return
	 */
	public static int countWords(String str) {
		if (str == null || str.length() <= 0) {
			return 0;
		}
		float len = 0;
		char c;
		for (int i = str.length() - 1; i >= 0; i--) {
			c = str.charAt(i);
			if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')
					|| (c >= 'A' && c <= 'Z')) {
				// 字母, 数字
				len += 0.5;
			} else {
				if (Character.isLetter(c)) { // 中文
					len++;
				} else { // 符号或控制字符
					len += 0.5;
				}
			}
		}
		return (int) (len + 0.5);//
	}

	/**
	 * 获取字符串的长度，中文占一个字符,英文数字占半个字符
	 * 
	 * @param value
	 *            指定的字符串
	 * @return 字符串的长度
	 */
	public static int realLength(String value) {
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < value.length(); i++) {
			// 获取一个字符
			String temp = value.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为1
				valueLength += 1;
			} else {
				// 其他字符长度为0.5
				valueLength += 0.5;
			}
		}
		// 进位取整
		return (int) Math.floor(valueLength);
	}

	// 取得文件大小
	public static long getFileSizes(File f) throws Exception {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
			fis.close();
		} else {
			System.out.println("文件不存在");
		}
		return s;
	}

	// 取得文件夹大小
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		if (f.exists()) {
			File flist[] = f.listFiles();
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getFileSize(flist[i]);
				} else {
					size = size + flist[i].length();
				}
			}
		}
		return size;
	}

	/**
	 * 字符串数组转字符串
	 * 
	 *            The string to be filtered and returned
	 */
	public static String toString(String mids[]) {

		if (Util.isNull(mids)) {
			return null;
		}
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < mids.length; i++) {
			s.append(mids[i] + ",");
		}
		return s.toString().substring(0, s.toString().length() - 1);
	}

	public static boolean isSame(String array[]) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i].equals(array[j])) {
					return false;
				}
			}
		}
		return true;
	}

	public static String arrayToStr(String array[]) {
		if (Util.isNull(array))
			return null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i] + ",");
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/**
	 * 通过date 和 uid 生成唯一id
	 * 
	 * @param date
	 * @param uid
	 * @return
	 */
	public static String generateId(Date date, String uid) {
		if (null == date) {
			return generateId("", uid);
		}

		return generateId(DateUtil.date2Str(date, "yyyy-MM-dd HH:mm:ss:SSS"),
				uid);
	}
	
	/**
	 * 生成手机的消费嘛
	 * 首位不为0
	 * @return
	 */
	public static String genPhonePassword(){
	    
		String base = "123456789";
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();
	    int number = random.nextInt(base.length());
	    sb.append(base.charAt(number));
	    
	    return sb.append(getRandomMath(7)).toString();
	}
	
	/**
	 * 获取定长位数随机数字
	 * 
	 * @param length
	 *            位数
	 * @return 定长位数随机字符
	 */
	public static String getRandomMath(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 通过date 和 uid 生成唯一id 位数减少到20位
	 * 
	 * @param date
	 * @param uid
	 * @return
	 */
	public static String generateId(String date, String uid) {
		if (StringUtils.isEmpty(date)) {
			Timestamp now = new Timestamp(new Date().getTime());
			return Util.getMD5(DateUtil.timestamp2Str(now)).substring(12);
		}

		String simpleDate = date.replace("-", "");

		return simpleDate.substring(0, 8)
				+ Util.getMD5(date + uid).substring(20);

		// if (StringUtils.isEmpty(date)) {
		// Timestamp now = new Timestamp(new Date().getTime());
		// return Util.getMD5(DateUtil.timestamp2Str(now));
		// }
		//
		// return Util.getMD5(date + uid);
	}

	/**
	 * 1. byte(8位有符号)的取值范围是 -128-127, char(16位无符号)的取值范围是0-65535;<BR>
	 * 2.如果一个byte对应的十六进制数只有一位前面要用0填充，因为MD5是32位的，达到统一的格式<BR>
	 * 3.0x是十六进制的标示，比如：0xFF表示8位的二进制1111 1111;0x00FF表示16位的二进制0000 0000 1111 1111
	 * */
	public static String getMD5(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				else
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
			return md5StrBuff.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 与python join的方法一样
	 * 
	 * @param list
	 * @return
	 */
	public static String join(String[] list) {
		if (Util.isNull(list)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (String string : list) {
			sb.append(",").append(string).append("");
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}

	/**
	 * 获取短链接
	 * 
	 * @param url
	 * @return
	 */
	public static String shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "coNgSurl";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" };
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = getMD5(key + url);
		String hex = sMD5EncryptResult;

		// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
		String sTempSubString = hex.substring(0, 8);

		// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
		// long ，则会越界
		long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
		String outChars = "";
		for (int j = 0; j < 6; j++) {
			// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
			long index = 0x0000003D & lHexLong;
			// 把取得的字符相加
			outChars += chars[(int) index];
			// 每次循环按位右移 5 位
			lHexLong = lHexLong >> 5;
		}
		return outChars;
	}

	/**
	 * 根据类型生成兑换码
	 * 
	 * @param type
	 *            类型 vwxyz 作为类型的源字符
	 * @return 兑换码
	 */
	public static String getCode(String type) {
		/*
		 * 0123456789abcdefghijklmnopqrstu 作为编码的源字符 vwxyz 作为类型的源字符
		 */
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstu");
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		int range = buffer.length();
		for (int i = 0; i < 7; i++) {
			sb.append(buffer.charAt(random.nextInt(range)));
		}
		return type.concat(sb.toString());
	}

	/**
	 * 字符串转16进制
	 * 
	 * @param str
	 * @return
	 */
	public static String str2HexStr(String str) {
		String hexString="0123456789ABCDEF";
		//根据默认编码获取字节数组
		byte[] bytes=str.getBytes();
		StringBuilder sb=new StringBuilder(bytes.length*2);
		//将字节数组中每个字节拆解成2位16进制整数
		for(int i=0;i<bytes.length;i++)
		{
		sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
		sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
		}
		return sb.toString();
	}
	
	/** 
     * 检测邮箱地址是否合法 
     * @param email 
     * @return true合法 false不合法 
     */  
    public static boolean isEmail(String email){  
    	if (null==email || "".equals(email)) return false;    
//    	Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配  
    	Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配  
    	Matcher m = p.matcher(email);  
    	return m.matches();  
	}
    
    /**
     * 判断是否是时间
     * @param time
     * @param pattern		时间格式
     * @return
     */
    public static boolean isTime(String time, String pattern){
    	boolean bool = true;
    	SimpleDateFormat format = new SimpleDateFormat(pattern);
    	try {
    		format.setLenient(false);
            format.parse(time);
		} catch (Exception e) {
			bool = false;
		}
    	return bool;
    }
    
    /**
	 * 计算周岁
	 * 
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) {
		int age = 0;
		try {
			Calendar cal = Calendar.getInstance();

			if (cal.before(birthDay)) {
				return 0;
			}

			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH) + 1;// 注意此处，如果不加1的话计算结果是错误的
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(birthDay);

			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					age--;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return age;
	}
	
	/**
	 * 手机号有效性验证
	 * @param phone
	 * @return
	 */
	public static boolean mobileValidt(String phone){
		
		boolean bool = true;
		final String pattern1 = "^1[34578]\\d{9}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(phone);
		if (!mat.find()) {
			bool = false;
		}
		return bool;
	}
	
	/**
	 * 去除首尾空格
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		if (str != null) {
			return str.trim();
		}
		return str;
	}
	/**
	 * 判断是否是数字
	 * @param str
	 * @param isInteger			是否是整数
	 * @return
	 */
	public static boolean isNumeric(String str, boolean isInteger){ 
		String regex = isInteger ? "\\d+":"(\\d+\\.\\d+)|\\d+";
		Pattern pattern = Pattern.compile(regex); 
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false; 
		} 
		return true; 
	}
	
	/**
	 * 将java.sql.Timestamp对象转化为String字符串
	 * @param time 
	 *          要格式的java.sql.Timestamp对象
	 * @param strFormat 
	 *          输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
	 * @return 表示日期的字符串
	 * */
	public static String dateToStr(Timestamp time, String strFormat) {
	    String result = "";
	    if (Util.isNotNull(time)) {
	        DateFormat df = new SimpleDateFormat(strFormat);
	        result = df.format(time);
	    }
	    return result;
	}
	
	/**
	 * 身高所属区间（例如：135cm 属于"130-140"）
	 * */
	public static String getHeightRange(Integer height) {
        if (height < 130) {
            return "130以下";
        } else if (130 <= height && height < 140) {
            return "130-140";
        } else if (140 <= height && height < 150) {
            return "140-150";
        } else if (150 <= height && height < 160) {
            return "150-160";
        } else if (160 <= height && height < 170) {
            return "160-170";
        } else {
            return "170以上";
        }
	}
	
	/**
	 * 体重所属区间（例如：17km 属于"15-20"）
	 * */
	public static String getWeightRange(Integer weight) {
        if (weight < 15) {
            return "15以下";
        } else if (15 <= weight && weight < 20) {
            return "15-20";
        } else if (20 <= weight && weight < 25) {
            return "20-25";
        } else if (25 <= weight && weight < 30) {
            return "25-30";
        } else if (30 <= weight && weight < 35) {
            return "30-35";
        } else if (35 <= weight && weight < 40) {
            return "35-40";
        } else if (40 <= weight && weight < 45) {
            return "40-45";
        } else if (45 <= weight && weight < 50) {
            return "45-50";
        } else if (50 <= weight && weight < 55) {
            return "50-55";
        } else {
            return "55以上";
        }
	}
	
	/**
	 * 是否是手机号
	 * */
	public static boolean isMobileNO(String mobile) {
	    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
	    Matcher m = p.matcher(mobile);
	    return m.matches();
	}
	
	public static void main(String[] args){
		System.out.println(isNumeric("6.26", false));
	}
}