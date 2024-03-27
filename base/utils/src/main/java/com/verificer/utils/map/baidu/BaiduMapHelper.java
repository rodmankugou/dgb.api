package com.verificer.utils.map.baidu;

import java.math.BigDecimal;

public class BaiduMapHelper {

	private static final double DEF_PI = 3.14159265359; // PI
	private static final double DEF_2PI = 6.28318530712; // 2*PI
	private static final double DEF_PI180 = 0.01745329252; // PI/180.0
	private static final double DEF_R = 6370693.5; // radius of earth

	/**
	 * 计算百度地图上两点间的距离（适用于近距离，远距离需要考虑球面弧度）
	 * 
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 * @author wuqd
	 */
	public static void main(String[] args){

		/**
		 * 福田中心 至 世界之窗
		 */
		Double distance = BaiduMapHelper.GetShortDistance(22.555332,114.064193,22.53664,113.97853);

//		Double distance = BaiduMapHelper.GetShortDistance(22.57234791169033,113.8654120353389,22.587613,113.866383);
//            d.setDistance(LocationUtils.getDistance(latitude,longitude,d.getLatitude().doubleValue(),d.getLongitude().doubleValue()));
		System.out.println(distance.intValue());

	}

	public static Long getDistance(BigDecimal lon1, BigDecimal lat1, BigDecimal lon2, BigDecimal lat2){

		if(lon1 == null || lat1 == null || lon2 == null || lat2 == null)
			return null;
		Double dLon1 = lon1.stripTrailingZeros().doubleValue();
		Double dLat1 = lat1.stripTrailingZeros().doubleValue();
		Double dLon2 = lon2.stripTrailingZeros().doubleValue();
		Double dLat2 = lat2.stripTrailingZeros().doubleValue();
		double distance = GetShortDistance(dLon1,dLat1,dLon2,dLat2);
		if(distance < 10000)
			return new BigDecimal(distance).longValue();

		return new BigDecimal(GetLongDistance(dLon1,dLat1,dLon2,dLat2)).longValue();

	}

	/**
	 * 单位为米
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2) {
		// 角度转换为弧度
		double ew1 = lon1 * DEF_PI180;
		double ns1 = lat1 * DEF_PI180;
		double ew2 = lon2 * DEF_PI180;
		double ns2 = lat2 * DEF_PI180;
		// 经度差
		double dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > DEF_PI)
			dew = DEF_2PI - dew; 
		else if (dew < -DEF_PI)
			dew = DEF_2PI + dew;
		double dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		double dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		double distance = Math.sqrt(dx * dx + dy * dy);


		return distance;
	}
	
	/**
	 *  计算百度地图上两点间的距离（适用于远距离，需要考虑球面弧度），单位为米
	 *  
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 * @author wuqd
	 */
	public static double GetLongDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * DEF_PI180;
		ns1 = lat1 * DEF_PI180;
		ew2 = lon2 * DEF_PI180;
		ns2 = lat2 * DEF_PI180;
		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;
		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;
	}


}
