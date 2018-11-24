/*
	* Copyright (c)  2013, Honbang
	* All rights reserved. 
	*
	* $id: CharacterEncoding.java 9552 2013-1-30 下午10:09:36 WangLijun$
	*/
	package com.jeecms.point.web.util; 

import java.io.Serializable;
	/**
 * <p>
 * Title: 编码格式定义
 * </p>
 * <p>
 * Description: 编码格式定义
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: Honbang
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class ContantsEncode implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -3271870336551949204L;
	
		/**GKB*/
		public  static final String GBK_ENCODE="GBK";
		
		/**UTF-8*/
		public  static final String UTF8_ENCODE="UTF-8";
		
		
		/**default encoding:UTF-8*/
		public static final String DEFAULT_ENCODE=UTF8_ENCODE;
	
}

	