package com.jeecms.core.web;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class HtmlParserUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println(getPlainTextFromHtml(""));
		
	}

	
	/**
	 * 从html中抽取纯文
	 * @param sourceHtml
	 * @return
	 */
	public static String getPlainTextFromHtml(String sourceHtml){
		
		if(sourceHtml==null||sourceHtml.equals("")){
			return "";
		}
		String retVal="";
		try {
			Source sources=new Source(sourceHtml);
			retVal=sources.getTextExtractor().toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public static List<String> getImgSrcFromString(String s)
	{
		List<String> retVal=new ArrayList<String>();
		Source source;
		try {
			source = new Source(getStringInputStream((s==null?"":s)));
			if(source!=null){
				List<Element> imgs=source.getAllElements(HTMLElementName.IMG);
				for(Element img:imgs){
					retVal.add(img.getAttributeValue("src"));
				}
			}
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return retVal;
		
	}

	
	/**
	 * 字符串转换成输入流
	 * @param s
	 * @return
	 */
	public static InputStream getStringInputStream(String s) {
	    if (s != null) {
	        try {
	            ByteArrayInputStream stringInputStream = new ByteArrayInputStream(s.getBytes());
	            return stringInputStream;
	        } catch (Exception e) {

	            e.printStackTrace();
	        }
	    }
	    return null;
	}
}
