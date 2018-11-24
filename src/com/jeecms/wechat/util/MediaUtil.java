package com.jeecms.wechat.util;

import com.jeecms.common.util.HttpClientUtil;
import com.jeecms.common.util.PropUtils;





public class MediaUtil {

		public static String qy_media_upload_url="https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=MEDIA_TYPE";
		
		public static String qy_media_download_url="https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		
        public static String media_upload_url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=MEDIA_TYPE";
		
		public static String media_download_url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		
    	public static String downloadMedia(String accessToken,String savePath,String mediaId){
				String url = media_download_url.replace("ACCESS_TOKEN",accessToken).replace("MEDIA_ID",mediaId);
				String retVal = HttpConnectionUtil.downloadMedia( url, mediaId, savePath);
				return retVal;
		}
    	
    	public static String  downloadMedia(String savePath,String mediaId){
    		String accessToken=HttpClientUtil.get(PropUtils.getPropertyValue("wechat.properties","get_access_token_url"));
    		return downloadMedia(accessToken,savePath, mediaId);
    	}
}
