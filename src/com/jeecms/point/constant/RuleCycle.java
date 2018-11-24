package com.jeecms.point.constant;

/**
 * 积分规则周期性定义
 * 
 * @author wanglijun
 *
 */
public final class RuleCycle {
	/**
	 * 一次性 0 0
	 * */ 
	public static  final int TIME_ONE=0;
	/**
	 * 按每天计算次数 1
	 * */
	public static  final int TIME_DAY=1;
	/**
	 * 按每周计算次数 4
	 * */
	public static  final int TIME_WEEK=2;
	
	/**
	 * 按每月计算次数 3
	 * */
	public static  final int TIME_MONTH=3;
	/***
	 * 按每季度计算次数 4
	 */
	public static final int TIME_QUARTER=4;
	/**
	 * 按每年计算次数 5
	 * */
	public static  final int TIME_YEAR=5;
	/***
	 * 无限次数 9
	 */
	public static  final int TIME_NO_LIMIT=9;
}
