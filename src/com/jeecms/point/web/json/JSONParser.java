/*
 * Copyright (c)  2013,  
 * All rights reserved. 
 *
 * $id: JSONParser.java 9552 2013-3-25 下午9:43:46 WangLijun$
 */
package com.jeecms.point.web.json;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.jeecms.common.page.Pagination;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company:  
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class JSONParser implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -4897117665103949997L;

	private static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}
	
	/**
	 * 根据
	 * @param datePattern  日期格式
	 * */
	private static SerializeConfig config(String datePattern) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer(datePattern));
		return mapping;
	}
	
		
	
	/**
	 * 根据解析JSON字符
	 * @param object
	 * */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
	
	
	/***
	 * 将对象解析为JSON
	 * @param object 
	 * @param datePattern
	 * @return String
	 * */
	public static String toJSONString(Object object,String datePattern){
		return JSON.toJSONString(object,config(datePattern));
	}
	
	/***
	 * 
	 * @param properties
	 * @param includeProperties
	 * @return PropertyFilter
	 */
	private static  PropertyPreFilter configPropertyFilter(Set<String> properties,boolean includeProperties){
		
		 SimplePropertyPreFilter simplePropertyPreFilter= new SimplePropertyPreFilter();
		 
		 if(includeProperties){
			 simplePropertyPreFilter.getIncludes().addAll(properties);
		 }else{
			 simplePropertyPreFilter.getExcludes().addAll(properties);
		 }
		 return  simplePropertyPreFilter;
	}
	
	/***
	 * 将对象解析为JSON
	 * @param object JSON解析对象
	 * @param datePattern 日期格式
	 * @param properties 字段
	 * @param isIncludeProperties  true 包含字段，False 则排除字段
	 * @return String 返回JSON字符串
	 */
	public static String toJSONString(Object object,String datePattern,Set<String> properties,boolean isIncludeProperties){
		//设置
		SerializerFeature[] features={SerializerFeature.QuoteFieldNames,SerializerFeature.DisableCircularReferenceDetect};
		SerializeWriter out = new SerializeWriter(features);
		JSONSerializer serializer = new JSONSerializer(out,config(datePattern));
		serializer.getPropertyPreFilters().add(configPropertyFilter(properties,isIncludeProperties));
		serializer.write(object);
		return out.toString();
	}
	
	/***
	 * datePattern默认格式为：yyyy-MM-dd
	 * @param objectJSON解析对象
	 * @param properties 字段
	 * @param isIncludeProperties true 包含字段，False 则排除字段
	 * @return String JSON字符串
	 */
	public static String toJSONString(Object object,Set<String> properties,boolean isIncludeProperties){
		return JSONParser.toJSONString(object,"yyyy-MM-dd", properties, isIncludeProperties);
	}
	
	/***
	 * isIncludeProperties 默认为:TRUE
	 * @param object
	 * @param properties
	 * @return
	 */
	public static String toJSONString(Object object,Set<String> properties){
		return JSONParser.toJSONString(object, properties,Boolean.TRUE);
	}
	/***
	 * 产生分页字符
	 * @param pagination 分页对象
	 * @return JSON字符串
	 */
	public static String toDataGridString(Pagination pagination){
		
		StringBuffer buffer=new StringBuffer("{\"total\":");
		if(pagination==null){
			buffer.append("0,");
			buffer.append("\"rows\":[{}]");
		}else{
			buffer.append(pagination.getTotalCount());
			buffer.append(",\"rows\":");
			buffer.append(toJSONString(pagination.getList()));
		}
		buffer.append("}");
		return buffer.toString();
	}
	
	/***
	 * 产生分页字符
	 * @param pagination 分页对象
	 * @return JSON字符串
	 * add by Jovi
	 */
	public static String toDataGridString2(Pagination pagination){
		
		StringBuffer buffer=new StringBuffer("{\"total\":");
		if(pagination==null){
			buffer.append("0,");
			buffer.append("\"rows\":[{}]");
		}else{
			buffer.append(pagination.getTotalCount());
			buffer.append(",\"rows\":");
			buffer.append(JSON.toJSONString(pagination.getList(),SerializerFeature.DisableCircularReferenceDetect));
		}
		buffer.append("}");
		return buffer.toString();
	}
	
	/***
	 * 产生分页字符
	 * @param pagination 分页对象
	 * @return JSON字符串
	 */
	public static String toDataGridString(Pagination pagination,String datePattern){
		
		StringBuffer buffer=new StringBuffer("{\"total\":");
		if(pagination==null){
			buffer.append("0,");
			buffer.append("\"rows\":[{}]");
		}else{
			buffer.append(pagination.getTotalCount());
			buffer.append(",\"rows\":");
			buffer.append(toJSONString(pagination.getList(),datePattern));
		}
		buffer.append("}");
		return buffer.toString();
	}
}
