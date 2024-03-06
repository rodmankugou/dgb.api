package com.verificer.utils.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 */
public class CheckUtil {

	/**
	 * 是否是邮箱
	 *
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码，11位数字，1开通，第二位数必须是3456789这些数字之一 *
	 *
	 * @param mobileNumber
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			// Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
//			Pattern regex = Pattern.compile("^1[3456789]\\d{9}$");
			Pattern regex = Pattern.compile("^[+]\\d{6,24}$");
//			String s = "^(\\+?213|0)?(5|6|7)\\d{8}$|^(!?(\\+?963)|0)?9\\d{8}$|^(!?(\\+?966)|0)?5\\d{8}$|^(\\+?1)?[2-9]\\d{2}[2-9](?!11)\\d{6}$|^(\\+?420)?[1-9][0-9]{2}?[0-9]{3}?[0-9]{3}$|"

			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;

		}
		return flag;
	}

	/**
	 * 8-24位数字密码组合
	 *
	 * @param loginPassword
	 * @return
	 */
	public static boolean checkLoginPassword(String loginPassword) {
//		String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_+])).{8,24}$";
		String regex = "^(?:(?=.*[A-Za-z])(?=.*[0-9])).{8,24}$";
		return loginPassword.matches(regex);
	}

	/**
	 * 8位数字字母组合
	 *
	 * @param loginPassword
	 * @return
	 */
	public static boolean checkMT5Password(String loginPassword) {
//		String regex = "^(?:(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[~!@#$%^&*_+])).{8}$";
		String regex = "^(?:(?=.*[A-Za-z])(?=.*[0-9])).{8}$";
		return loginPassword.matches(regex);
	}

	/**
	 * 6位纯数字
	 * @param payPassword
	 * @return
	 */
	public static boolean checkPayPassword(String payPassword){
		String regex = "^\\d{6}$";
		return payPassword.matches(regex);
	}

	/**
	 * 6位纯数字
	 * @param payPassword
	 * @return
	 */
	public static boolean checkPayPassword2(String payPassword){
		String regex = "^\\d{6}$";
		if(!payPassword.matches(regex))
			return false;

		//检验是否所有位重复
		boolean isRepeat =  true;
		for(int i = 1; i < payPassword.length(); i++){
			if(payPassword.charAt(i) != payPassword.charAt(0)){
				isRepeat =  false;
				break;
			}
		}
		if(isRepeat)
			return false;

		//检查是否简单递增
		boolean isEquDif = true;
		if(payPassword.length() > 3){
			int period = payPassword.charAt(1) - payPassword.charAt(0);

			for(int i = 2 ; i < payPassword.length(); i++){
				if(payPassword.charAt(i) - payPassword.charAt(i-1) != period){
					isEquDif = false;
					break;
				}
			}
		}
		if(isEquDif)
			return false;

		return true;

	}


	public static void main(String[] args) {

		System.out.println(checkLoginPassword("Aa123456?"));
		System.out.println(checkLoginPassword("gA1%534145"));
		Double rst = 0d;
		Double rst2 = 0d;

		rst = 0.0015 * 50000 + 0.0035*30000 + 0.009 * 20000 + 5200*0.097 + 2.5*0.25 + 1 * 0.179 + 0.5*0.21 + 0.25 * 0.25;
		rst2 = 0.0008 * 100000 + 0.0025*30000 + 0.006 * 20000 + 5200*0.105 + 5*0.115 + 2.5 * 0.177 + 1*0.2137 + 0.38 * 0.5;
		System.out.println(rst);
		System.out.println(rst2);
	}
}
