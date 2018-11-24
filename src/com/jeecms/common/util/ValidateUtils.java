package com.jeecms.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	
	public static boolean isMobileNO(String mobiles){       
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");       
        Matcher m = p.matcher(mobiles);       
        return m.matches();       
    }   
     
    public static boolean isEmail(String email){       
     String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";  
        Pattern p = Pattern.compile(str);       
        Matcher m = p.matcher(email);       
        return m.matches();       
    }   
    
    
    public static boolean isEmpty(String str){
       	if(str==null||"".equals(str)){
    		return true;
    	}
    	return false;
    }
    
	public static void main(String args[]) {

		System.out.println(isMobileNO("18223986517"));
	}

}
