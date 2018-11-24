/*
 * Copyright (c)  2014,  
 * All rights reserved. 
 *
 * $id: JsonHttpMessageConverter.java 9552 2014-2-15 下午09:11:06 WangLijun$
 */
package com.jeecms.point.web.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.jeecms.point.web.util.ContantsEncode;

/**
 * <p>
 * Title: JSON转换字符串
 * </p>
 * <p>
 * Description:JSON转换字符串
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class JsonHttpMessageConverter extends
		AbstractHttpMessageConverter<Object> {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(super.getClass());

	public final static Charset UTF8 = Charset.forName(ContantsEncode.UTF8_ENCODE);

	private Charset charset = UTF8;
	 

 

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream in = inputMessage.getBody();

		byte[] buf = new byte[1024];
		for (;;) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}

			if (len > 0) {
				baos.write(buf, 0, len);
			}
		}

		byte[] bytes = baos.toByteArray();
		if (charset == UTF8) {
			return JSON.parseObject(bytes, clazz);
		} else {
			return JSON.parseObject(bytes, 0, bytes.length,
					charset.newDecoder(), clazz);
		}
	}

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		OutputStream out = outputMessage.getBody();
		byte[] bytes;

		if (obj instanceof String) {
			bytes = ((String) obj).getBytes("GBK");
			out.write(bytes);
		}
	}
}
