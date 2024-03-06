package com.verificer.utils;

import com.mysql.cj.log.Log;
import org.springframework.data.redis.connection.ReactiveHashCommands;

import java.math.BigInteger;
import java.util.*;


/**
 * 自定义字符串工具
 */
public class SStringUtils {

	public static final String SPLIT_COMMA = ",";


	/**
	 * Prefix for system property placeholders: "${"
	 */
	public static final String PLACEHOLDER_PREFIX = "${";
	/**
	 * Suffix for system property placeholders: "}"
	 */
	public static final String PLACEHOLDER_SUFFIX = "}";

	/**
	 * 解释字符串中的占位符,index从0开始
	 * @param text
	 * @param objects
	 * @return
	 */
	public static String resolvePlaceholders(String text, Object... objects){
		if(objects == null || objects.length == 0){
			return text;
		}

		Map<String,String> params = new HashMap<>();
		for(int i = 0 ; i < objects.length; i++){
			params.put(i+"",objects[i].toString());
		}
		return resolvePlaceholders(text,params);
	}

	/**
	 * 解释字符串中的占位符
	 * @param text
	 * @param parameter
	 * @return
	 */
	public static String resolvePlaceholders(String text, Map<String, String> parameter) {
		if (parameter == null || parameter.isEmpty()) {
			return text;
		}
		StringBuffer buf = new StringBuffer(text);
		int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
		while (startIndex != -1) {
			int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
				int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
				try {
					String propVal = parameter.get(placeholder);
					if (propVal != null) {
						buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
						nextIndex = startIndex + propVal.length();
					} else {
						throw new IllegalArgumentException("Could not resolve placeholder '" + placeholder + "' in [" + text + "] ");
					}
				} catch (Exception ex) {
					throw new IllegalArgumentException("Could not resolve placeholder '" + placeholder + "' in [" + text + "]: " + ex);
				}
				startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
			} else {
				startIndex = -1;
			}
		}
		return buf.toString();
	}


	public static boolean isAnyEmpty(String... strings){
		for(String str : strings){
			if(isEmpty(str)){
				return true;
			}
		}
		return false;
	}

	public static boolean isEmpty(String string){
		return (string == null || string.length() == 0 ||  "".equals(string.trim()));
	}

	public static boolean isEmptyRedisValue(String string){
		return (string == null || string.length() == 0 ||  "".equals(string.trim()) || "nil".equals(string.trim()));
	}


	public static String replaceBlank(String str) {
		String dest = "";
		if (!isEmpty(str)) {
//            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
//            Matcher m = p.matcher(str);
//            dest = m.replaceAll("");
			str = str.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll("\\t", "").replaceAll("\"", "'");
			return str;
		}
		return dest;
	}

	public static String idCardDeal(String idCard) {
		if (isEmpty(idCard)) {
			return null;
		}
		int flag = 3;
		if(idCard.length()<6){
			flag = idCard.length()/2;
		}

		String start = idCard.substring(0, flag);
		String end = idCard.substring(idCard.length() - flag);
		return start + "****" + end;
	}

	public static String bankCardDeal(String bankCard) {
		if (isEmpty(bankCard)) {
			return null;
		}
		String end = bankCard.substring(bankCard.length() - 4);
		return end;
	}

	/**
	 * 根据一个或者多个空格分割（包括tab等）
	 * @param str
	 * @return
	 */
	public static List<String> splitByNotShowChar(String str){
		return SStringUtils.split(str,"\\s+");

	}

	public static List<String> split(String str, String pattern) {

		if (isEmpty(str)) {
			return null;
		}

		String[] strArray = str.split(pattern);

		if (str.isEmpty()) {
			return null;
		}
		List<String> result = new ArrayList<String>();
		for (String string : strArray) {
			result.add(string);
		}
		return result;
	}

	/**
	 * @param src
	 * @param jiancount    从后面数第几位 开始银行卡替换为星号
	 * @param replacecount 后面留 4位
	 * @return
	 */
	public static String replaceCard(String src, int jiancount, int replacecount) {
		if (!isEmpty(src)) {
			StringBuilder resultBuilder = new StringBuilder();
			if (src.length() <= jiancount) {
				return src;
			}
			String part1 = src.substring(0, src.length() - jiancount);
			String part2 = src.substring(src.length() - replacecount, src.length());
			resultBuilder.append(part1);
			resultBuilder.append("****");
			resultBuilder.append(part2);
			return resultBuilder.toString();
		} else {
			return "";
		}

	}

	/**
	 * @param src
	 * @param jiancount    从后面数第几位 开始替换手机号为星号
	 * @param replacecount 替换后面留 3位
	 * @return
	 */
	public static String replaceMobile(String src, int jiancount, int replacecount) {

//        return src;
		if (!isEmpty(src)) {
			if (src.length() <= jiancount) {
				return src;
			}
			StringBuilder resultBuilder = new StringBuilder();
			String part1 = src.substring(0, src.length() - jiancount);
			String part2 = src.substring(src.length() - replacecount, src.length());
			resultBuilder.append(part1);
			resultBuilder.append("*****");
			resultBuilder.append(part2);
			return resultBuilder.toString();
		} else {
			return "";
		}
	}


	public static String replaceMobile(String mobile) {
		if (isEmpty(mobile)){
			return null;
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * @param src
	 * @param jiancount    从后面数第几位 开始替换身份证号为星号
	 * @param replacecount 替换后面留 3位
	 * @return
	 */
	public static String replaceIdCard(String src, int jiancount, int replacecount) {
		if (!isEmpty(src)) {

			if (src.length() <= jiancount) {
				return src;
			}
			StringBuilder resultBuilder = new StringBuilder();
			String part1 = src.substring(0, src.length() - jiancount);
			String part2 = src.substring(src.length() - replacecount, src.length());
			resultBuilder.append(part1);
			resultBuilder.append("*******");
			resultBuilder.append(part2);
			return resultBuilder.toString();
		} else {
			return "";
		}

	}

	public static String replaceIdCard(String idCard){
		return idCard.replaceAll("(\\d{4})\\d{10}(\\d{4})","$1****$2");
	}



	public static String replaceName(String name) {
		if (!isEmpty(name) && name.length() > 1) {
			name = "*" + name.substring(1, name.length());
		}
		return name;
	}

	/**
	 * 判断数组是否有重复元素
	 *
	 * @param array
	 * @return
	 */
	public static boolean checkRepeatArray(String[] array) {
		Set<String> set = new HashSet<String>();
		for (String str : array) {
			set.add(str);
		}
		if (set.size() != array.length) {
			return false;//有重复
		} else {
			return true;//不重复
		}
	}

	/**
	 * 判断数组是否有重复元素
	 *
	 * @param array
	 * @return
	 */
	public static String[] iniStringArray(String[] array, String value) {
		for (int i = 0; i < array.length; i++) {
			array[i] = value;
		}
		return array;
	}


	public static String mobileFormartAnonymous3t8(String mobile) {

		if (null == mobile) {
			return null;
		}

		if (!SStringUtils.isEmpty(mobile)) {
			String prefix = mobile.substring(0, 3);
			String suffix = mobile.substring(8);
			return prefix + "*****" + suffix;
		}

		return "*****";
	}


	public static String[] stringsPingPrefix(String[] arr, String prefix) {

		String[] strings = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			strings[i] = prefix + arr[i];
		}

		return strings;
	}

	// 替换邀请码和娃娃名
	public static String replaceInviteCodeAndName(String content, String code, String toyName) {
		String temp = content.replaceAll("\\{inviteCode\\}", code);
		temp = temp.replaceAll("\\{toyName\\}", toyName);
		return temp;
	}


	public static String replaceEmail(String email) {
		if (isEmpty(email)){
			return null;
		}
		//只显示@前面的首位和末位
		return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");

	}

	public static List<Integer> parseInts(String str){
		return parseInts(str,",");
	}

	public static List<Long> parseLongs(String str, String spliter){
		List<Long> ids = new ArrayList<>();
		if(str == null || str.trim().equals("")){
			return ids;
		}

		String[] strs = str.trim().split(spliter);
		for(String strElem : strs){
			ids.add(Long.parseLong(strElem));
		}

		return ids;
	}

	public static List<Integer> parseInts(String str, String spliter){
		List<Integer> ids = new ArrayList<>();
		if(str == null || str.trim().equals("")){
			return ids;
		}

		String[] strs = str.trim().split(spliter);
		for(String strElem : strs){
			ids.add(Integer.parseInt(strElem));
		}

		return ids;
	}

	public static int[] parseOriginIntArray(String str){
		return parseOriginIntArray(str,",");
	}

	public static int[] parseOriginIntArray(String str,String spliter){
		List<Integer> integerList = parseInts(str,spliter);
		int[] result = new int[integerList.size()];
		for(int i = 0 ; i < integerList.size(); i++){
			result[i] = integerList.get(i);
		}
		return result;
	}

	public static String join(Object[] array, String separator) {
		return array == null?null:join(array, separator, 0, array.length);
	}

	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if(array == null) {
			return null;
		} else {
			if(separator == null) {
				separator = "";
			}

			int noOfItems = endIndex - startIndex;
			if(noOfItems <= 0) {
				return "";
			} else {
				StringBuilder buf = new StringBuilder(noOfItems * 16);

				for(int i = startIndex; i < endIndex; ++i) {
					if(i > startIndex) {
						buf.append(separator);
					}

					if(array[i] != null) {
						buf.append(array[i]);
					}
				}

				return buf.toString();
			}
		}
	}


	public static String firstCharUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 生成指定长度的数字序列
	 * @param length
	 * @return
	 */
	public static String generateRandomNumSequence(int length){
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < length;i++){
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstChar(String str){
		if(isEmpty(str))
			return str;
		char[] cs= str.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String lowerCaseFirstChar(String str){
		if(isEmpty(str))
			return str;
		char[] cs= str.toCharArray();
		cs[0]+=32;
		return String.valueOf(cs);
	}

	/**
	 * 如果字符串str的长度不够,则以指定的字符在前面填充
	 * @param str
	 * @param length
	 * @param c
	 */
	public static String fillAtFrontIfNotEnoughLength(String str, Integer length,char c) {
		if(str == null)
			return null;
		if(str.length() >= length)
			return str;

		StringBuffer sb = new StringBuffer();
		for(int i = str.length() - length; i > 0 ;i--){
			sb.append(c);
		}
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 根据转换表转换字符串中的字符
	 * @param str
	 * @param tranMap
	 * @return
	 */
	public static final String transferChar(String str,Map<Character,Character> tranMap){
		if(SStringUtils.isEmpty(str))
			return str;

		if(tranMap == null)
			throw new IllegalArgumentException("parameter tranMap can not be null");


		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < str.length(); i++){
			char c = str.charAt(i);
			if(tranMap.containsKey(c))
				sb.append(tranMap.get(c));
			else
				sb.append(c);
		}
		return sb.toString();
	}

    public static String formatBigInteger(BigInteger chainOrderId) {
		if(chainOrderId == null)
			return null;
		return chainOrderId.toString();
    }

	/**
	 * 将数组合成一个字符串，如{1,2,3,4}和成"1,2,3,4"
	 * @param array
	 * @param split
	 * @return
	 */
	public static String composite(int[] array,String split) {

		Integer[] ints = new Integer[array.length];
		for(int i = 0 ; i < ints.length; i++)
			ints[i] = array[i];

		return composite(ints,split);
	}

	/**
	 * 将数组合成一个字符串，如{1,2,3,4}和成"1,2,3,4"
	 * @param array
	 * @param split
	 * @return
	 */
	public static String compositeLongList(List<Long> array, String split) {

		if(array == null || array.size() == 0)
			return "";


		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < array.size();i++){
			if(i == array.size()-1){
				sb.append(array.get(i));
			}else {
				sb.append(array.get(i)).append(split);
			}
		}

		return sb.toString();
	}

	/**
	 * 将数组合成一个字符串，如{1,2,3,4}和成"1,2,3,4"
	 * @param array
	 * @param split
	 * @return
	 */
	public static String compositeList(List<?> array, String split) {

		if(array == null || array.size() == 0)
			return "";


		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < array.size();i++){
			if(i == array.size()-1){
				sb.append(array.get(i));
			}else {
				sb.append(array.get(i)).append(split);
			}
		}

		return sb.toString();
	}

	/**
	 * 将数组合成一个字符串，如{1,2,3,4}和成"1,2,3,4"
	 * @param array
	 * @param split
	 * @return
	 */
	public static String composite(Integer[] array,String split) {

		if(array == null || array.length == 0)
			return "";


		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < array.length;i++){
			if(i == array.length-1){
				sb.append(array[i]);
			}else {
				sb.append(array[i]).append(split);
			}
		}

		return sb.toString();
	}

	public static void main(String args[]){
		String idsStr = "919,2130,2180,2330,2581,2968,3179,3243,3610";
		List<Integer> ids = SStringUtils.parseInts(idsStr);
		Set<Integer> idSet = new HashSet<>();

		idSet.addAll(ids);
		System.out.println(idSet.size());

		List<Integer> rst = new LinkedList<>();
		rst.addAll(idSet);
		System.out.println(SStringUtils.compositeList(rst,"@"));

		System.out.println("0x27DC17573cfE99974Ae0B0Bbac42B8cAC07A7E1d".toLowerCase());

//		String mappingStr = "557->17315,1224->17316,1398->17317,1416->17318,1602->17319,1679->17320,1727->17321,1755->17322,1914->17323,2096->17324,2231->17325,2331->17326,2545->17327,2546->17328,2558->17329,2629->17330,2669->17331,2853->17332,2864->17333,3113->17334,3118->17335,3141->17336,3147->17337,3239->17338,3451->17368,3660->17369,3870->17370,4022->17371,4065->17372,4122->17373,4219->17374,4249->17375,4285->17376,4482->17377,4551->17378,4800->17379,4831->17380,4991->17381,5026->17382,5187->17443,5241->17444,5275->17445,5495->17446,5608->17447,5765->17448,5912->17449,5981->17450,6203->17451,6351->17452,6960->17461,7794->17462,7863->17463,8546->17465,9136->17466";
//		List<String> origList = new LinkedList<>();
//		List<String> newList = new LinkedList<>();
//		List<String> ms = SStringUtils.split(mappingStr,",");
//		for(String mapping : ms){
//			List<String> values = SStringUtils.split(mapping,"->");
//			origList.add(values.get(0));
//			newList.add(values.get(1));
//		}
//
//		Set<String> origSet = new HashSet<>();
//		Set<String> newSet = new HashSet<>();
//		origSet.addAll(origList);
//		newSet.addAll(newList);
//		if(origList.size() != origSet.size())
//			throw new RuntimeException("orig包含重复ID");
//		if(newList.size() != newSet.size())
//			throw new RuntimeException("new包含重复ID");
//		if(origList.size() != newList.size())
//			throw new RuntimeException("Mapping解释错误");
//		System.out.println(SStringUtils.compositeList(origList,","));
//		System.out.println(SStringUtils.compositeList(newList,","));


	}
}
