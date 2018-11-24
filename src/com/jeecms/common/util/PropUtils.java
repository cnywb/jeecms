package com.jeecms.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

public class PropUtils {
    /** true if the Properties has been initialized. */
    private static String DEFAULT_PATH = "imgScaling.properties";
    private static Properties properties = null;
    private PropUtils() {
    }

    private static synchronized void initialize(String prop) {
        // We flag as initialized right away because if anything goes wrong
        // We still consider it initialized. TODO Is this OK?
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(prop);
        if (is == null) {
            return;
        }
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            try{
                if(is!=null)
                    is.close();
            }catch(Exception ex){
                throw new RuntimeException(ex);
            }
        }
    }

    public static synchronized Properties getProperties(String prop)
            throws RuntimeException {
        initialize(prop);
        return properties;
    }
    
    public static String getFormatString(String value, Object[] params) {
        if (params != null) {
            MessageFormat mf = new MessageFormat(value);
            value = mf.format(params);
        }
        return value;
    }
    public static synchronized String getPropertyValue(String key){
        Properties prop = getProperties(DEFAULT_PATH);
        return prop.getProperty(key);
    }
    
    public static synchronized String getPropertyValue(String fileName,String key){
        Properties prop = getProperties(fileName);
        return prop.getProperty(key);
    }
}
