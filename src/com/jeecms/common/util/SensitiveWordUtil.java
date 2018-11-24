package com.jeecms.common.util;

import org.apache.commons.lang.StringUtils;

public class SensitiveWordUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
	
	System.out.println(replaceSensitiveWord("我有好货可以批发给你，土制手雷，见面交易"));
				
	}
	
	
	public static String replaceSensitiveWord(String sentence){
		if(StringUtils.isEmpty(sentence)){
			return sentence;
		}
	    String words=PropUtils.getPropertyValue("sensitive_word.properties","words")+","+PropUtils.getPropertyValue("sensitive_word.properties","words2");
		String[] wordsArray=words.split(",");
		for(String word:wordsArray){
			sentence=sentence.replace(word,"*");
		}
		return sentence;
	}
	

}
