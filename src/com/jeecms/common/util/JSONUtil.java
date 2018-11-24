package com.jeecms.common.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;


import org.codehaus.jackson.map.ObjectMapper;




public class JSONUtil {

	public static <T> T jsonToObject(String json, Class<T> c){  
	    T o = null;  
	    try{  
	        o = new ObjectMapper().readValue(json, c);  
	    } catch (Exception e){  
	        e.printStackTrace();
	    }  
	    return o;  
	}  
	
	public static String objectToJson(Object o){  
        ObjectMapper om = new ObjectMapper();  
        Writer w = new StringWriter();  
        String json = null;  
        try {  
        om.writeValue(w, o);  
            json = w.toString();  
            w.close();  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return json;  
    }  
}
