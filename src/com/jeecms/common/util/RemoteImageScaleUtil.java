package com.jeecms.common.util;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.MultiStepRescaleOp;


public class RemoteImageScaleUtil {

	    public static int WIDTH_DEFAULT = 50;

	    public static int HEIGHT_DEFAULT = 18;

	    private static String default_image_path = "images/default_img.jpg";

	    private static final String DEFAULT_KEY = "backupImageURL";

	    private static final String WIDTH_DEFAULT_KEY = "width";

	    private static final String HEIGHT_DEFAULT_KEY = "height";
	
	    protected static Log log = LogFactory.getLog(RemoteImageScaleUtil.class);
	    /**
		 * 给定原始图片的长宽和定制长宽
		 * 返回优化后的长宽
		 * @author wenfeng.xu
		 * @param cusW
		 * @param cusH
		 * @param srcW
		 * @param srcH
		 * @return
		 */
		public static Integer[] filterSize(Integer cusW, Integer cusH, Integer srcW,Integer srcH) {// cusW目标图片宽,cusH目标图片高,srcW源图片宽,srcH源图片高
			Integer outW=0;
			Integer outH=0;

			if (cusH / cusW == srcH / srcW) {// 当订制图象高宽比与原图的高宽比相同，则直接按订制高宽比来缩放图象
				outW = cusW;
				outH = cusH;
			} else {// 以下是订制高宽比与原始图象不同的情况

				if (cusH / cusW == 1) {// 订制图象为正方形

					if (srcH / srcW > 1) {// 原始图象为直立长方形
						outH = cusH;// 输出图象的高为客户订制的高
						outW = (cusH * srcW) / srcH;// 输出图象的宽按比例算出
					} else if (srcH / srcW < 1) {// 原始图象为水平长方形
						outW = cusW;// 输出图象的宽就是用户输入的宽
						outH = (cusW * srcH) / srcW;// 输出图象的高按比例算出
					} else if (srcH / srcW == 1) {// 原始图象为正方形
						outW = cusW;
						outH = cusW;
					}

				} else if (cusH / cusW > 1) {// 订制图象为直立长方形
					if (cusH / cusW > srcH / srcW) {// 如果订制图象的正切值大于原始图象正切值
						outW = cusW;// 输出图象的宽就是用户输入的宽
						outH = (cusW * srcH) / srcW;// 输出图象的高按比例算出
					} else if (cusH / cusW < srcH / srcW) {// 如果订制图象的正切值小于原始图象正切值
						outH = cusH;// 输出图象的高为客户订制的高
						outW = (cusH * srcW) / srcH;// 输出图象的宽按比例算出
					} else if (srcH / srcW == 1) {// 原始图象为正方形
						outW = cusW;
						outH = srcH;
					}
				} else if (cusH / cusW < 1) {// 订制图象为水平长方形
					if (cusH / cusW > srcH / srcW) {// 如果订制图象的正切值大于原始图象正切值
						outW = cusW;
						outH = (cusW * srcH) / srcW;
					} else if (cusH / cusW < srcH / srcW) {// 如果订制图象的正切值小于原始图象正切值
						outH = cusH;
						outW = (cusH * srcW) / srcH;

					} else if (srcH / srcW == 1) {// 原始图象为正方形
						outW = cusW;
						outH = srcH;
					}

				}
			}
			Integer[] retVal={outW,outH};
	      return retVal;
		}// end filterSize method
		
		
		
		public static byte[] imageScaling(HttpServletResponse response,HttpServletRequest request){
		   	 default_image_path = isValidImageFile(PropUtils.getPropertyValue(DEFAULT_KEY)) ? PropUtils.getPropertyValue(DEFAULT_KEY) : default_image_path;
	         String widthPro = PropUtils.getPropertyValue(WIDTH_DEFAULT_KEY);
	         String heightPro = PropUtils.getPropertyValue(HEIGHT_DEFAULT_KEY);
	         WIDTH_DEFAULT = isValidInteger(widthPro) ? Integer
	                 .parseInt(widthPro) : WIDTH_DEFAULT;
	         HEIGHT_DEFAULT = isValidInteger(heightPro) ? Integer
	                 .parseInt(heightPro) : HEIGHT_DEFAULT;

	         String imgsrc = request.getParameter("url");
	         String widthPar = request.getParameter("width");
	         String heightPar = request.getParameter("height");
	         String queryString = request.getQueryString();
	         if (imgsrc == null || !isValidImageFile(imgsrc)) {
	             log.info("The url attribute is invalid!");
	             String path = request.getContextPath();
	             String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	             imgsrc = basePath+default_image_path;
	         }
	         if (queryString.length() == 0) {
	             log.info("The url attribute is invalid!");
	             imgsrc = default_image_path;
	         }
	         boolean widthIsValid = isValidInteger(widthPar);
	         boolean heightIsValid = isValidInteger(heightPar);
	         int width = WIDTH_DEFAULT;
	         int height = HEIGHT_DEFAULT;
	         if (widthIsValid && heightIsValid) {
	             width = Integer.parseInt(widthPar);
	             height = Integer.parseInt(heightPar);
	         } else {
	             log.info("The width or height attribute is invalid!");
	         }
	         log.info("with width= " + width + " and height=" + height + "");
	         log.info("The image file is "+imgsrc);
	         String imageExt = getImageExtension(imgsrc);
	         String contentType = getContentTypeByExt(imageExt);
	         log.info("The image file end with "+imageExt);
	         log.info("The image file content type is "+contentType);
	         response.setContentType(contentType);
	         response.setHeader("Pragma", "No-cache");
	         response.setHeader("Cache-Control", "no-cache");
	         response.setDateHeader("Expires", 0);
	         ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	         BufferedImage image = doMultiStepRescaling(request, imgsrc, width,
	                 height);
	         if (image != null)
				try {
					ImageIO.write((BufferedImage) image, imageExt,
					         byteArrayOutputStream);
			         byteArrayOutputStream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	         byte abyte[] = byteArrayOutputStream.toByteArray();
	         return abyte;
		}
		   private static BufferedImage scalingImageSize(AdvancedResizeOp op,
		            HttpServletRequest request, String imgsrc,BufferedImage src,int widthdist,
		            int heightdist) {
		        long startTime = System.currentTimeMillis();
		        BufferedImage tag = null;
		        tag = op.filter(src, null);
		        long endTime = System.currentTimeMillis();
		        log.info("Scaling image need speed time is :" + (endTime - startTime) / 1000);
		        return tag;
		    }

		private static BufferedImage getImageFromLocalByURL(HttpServletRequest request,
		        String imgsrc) throws MalformedURLException, IOException {
		    URL imgURL;
		    String url;
		    url = request.getRequestURL().toString();
		    try {
		        url = url.substring(0, url.lastIndexOf("/"));
		        imgsrc = getURL(url, imgsrc);
		        imgURL = new URL(imgsrc);
		    } catch (Exception ex) {
		        imgURL = new URL(getURL(url, null));
		    }
		    BufferedImage src =null ;
		    src=ImageIO.read(imgURL);
		        
		   
		    return src;
		}

		
		    private static BufferedImage doMultiStepRescaling(HttpServletRequest request,
		            String imgsrc, int widthdist, int heightdist) {
		    	
		    	
		         Integer[] outputSize=null;
		        BufferedImage temimg=null;
		        try {
		            temimg=getImageFromLocalByURL(request, imgsrc);//
		            outputSize=filterSize(widthdist, heightdist, temimg.getWidth(), temimg.getHeight());//设置参数
		              
		        } catch (MalformedURLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		        
		        
		        MultiStepRescaleOp mrop = new MultiStepRescaleOp(outputSize[0],outputSize[1],RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		     
		        return scalingImageSize(mrop, request, imgsrc,temimg,widthdist, heightdist);
		    }

		    /**
		     * @param url
		     * @param queryString
		     * @return
		     */
		    private static String getURL(String url, String queryString) {
		        StringBuffer buf = new StringBuffer();
		        if (queryString == null) {
		            if (!url.matches(".*[\\\\/]$")) {
		                return buf.append(url).append("/").append(default_image_path)
		                        .toString();
		            } else {
		                return buf.append(url).append(default_image_path).toString();
		            }
		        }
		        String tempQueryString = queryString;
		        if (tempQueryString.matches("^(ht|f)tp(s?):.*")) {
		            return tempQueryString;
		        }
		        return (!url.matches(".*[\\\\/]$")) ? (tempQueryString
		                .matches("^[\\\\/].*") ? buf.append(url)
		                .append(tempQueryString).toString() : buf.append(url).append(
		                "/").append(tempQueryString).toString()) : (tempQueryString
		                .matches("^[\\\\/].*") ? buf.append(
		                url.substring(0, url.lastIndexOf("/"))).append(tempQueryString)
		                .toString() : buf.append(url).append(tempQueryString)
		                .toString());
		    }

		    /**
		     * @param value
		     * @return Boolean.false if no,true if yes
		     */
		    private static boolean isValidInteger(String value) {
		        String regex = "^[0-9]*[1-9][0-9]*$";
		        if (value == null)
		            return false;
		        return value.matches(regex);
		    }

		    /**
		     * @param url
		     * @return valid if true,invalid if false
		     */
		    private boolean isValidURL(String url) {
		        // Now convert string we've built up into a real regex object
		        String SubDomain = "(?i:[a-z0-9]|[a-z0-9][-a-z0-9]*[a-z0-9])";
		        String TopDomains = "(?x-i:com\\b        \n" + "     |edu\\b        \n"
		                + "     |biz\\b        \n" + "     |in(?:t|fo)\\b \n"
		                + "     |mil\\b        \n" + "     |net\\b        \n"
		                + "     |org\\b        \n" + "     |[a-z][a-z]\\b \n" + // country
		                // codes
		                ")                   \n";
		        String Hostname = "(?:" + SubDomain + "\\.)+" + TopDomains;

		        String NOT_IN = ";\"'<>()\\[\\]{}\\s\\x7F-\\xFF";
		        String NOT_END = "!.,?";
		        String ANYWHERE = "[^" + NOT_IN + NOT_END + "]";
		        String EMBEDDED = "[" + NOT_END + "]";
		        String UrlPath = "/" + ANYWHERE + "*(" + EMBEDDED + "+" + ANYWHERE
		                + "+)*";
		        String UrlRegexp = "(?x:                                                \n"
		                + "  \\b                                               \n"
		                + "  # match the hostname part                        \n"
		                + "  (                                                 \n"
		                + "    (?: ftp | http s? )://[-\\w]+(\\.\\w[-\\w]*)*   \n"
		                + "   |                                                \n"
		                + "    "
		                + Hostname
		                + "                                \n"
		                + "  )                                                 \n"
		                + "  # allow optional port                             \n"
		                + "  (?: :\\d+ )?                                      \n"
		                + "                                                    \n"
		                + "  # rest of url is optional, and begins with /      \n"
		                + "  (?: "
		                + UrlPath
		                + ")?                             \n"
		                + " )";
		        return Pattern.compile(UrlRegexp).matcher(url).matches();
		    }

		    /**
		     * Image File Extension Regular Expression Pattern
		     * 
		     * @param image
		     * @return
		     */
		    private static boolean isValidImageFile(final String image) {
		        /*String regex = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp|wbmp))$)";
		        if (image == null)
		            return false;
		        return Pattern.compile(regex).matcher(image).matches();*/
		    	return true;
		    }

		    /**
		     * @param image
		     * @return
		     */
		    private static String getImageExtension(String image) {
		        String regex = "(?i)(jpg|jpeg|png|gif|bmp|wbmp)$";
		        Pattern p = Pattern.compile(regex);
		        Matcher m = p.matcher(image);
		        String imgExt = "JPG";
		        while (m.find()) {
		            imgExt = isValidImageFile(image) ? m.group(1) : "JPG";
		            break;
		        }
		        return imgExt;
		    }

		    /**
		     * @param imageExt
		     * @return
		     */
		    private static String getContentTypeByExt(String imageExt) {
		        String[] writeFormatNames = ImageIO.getReaderMIMETypes();
		        String contentType = "image/jpeg";
		        if(imageExt == null)return contentType;
		        for (String s : writeFormatNames) {
		            if(s.toLowerCase().endsWith(imageExt.toLowerCase())){
		                contentType = s;
		                break;
		            }
		        }
		        return contentType;
		    }
		    
		    
		    
		    
		   
}
