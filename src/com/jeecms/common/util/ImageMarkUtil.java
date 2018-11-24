package com.jeecms.common.util;

import java.awt.AlphaComposite;   
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;   
import java.awt.Image;   
import java.awt.RenderingHints;   
import java.awt.image.BufferedImage;   
import java.io.File;   
import java.io.FileOutputStream;   
import javax.imageio.ImageIO;   
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
  

  
  
public class ImageMarkUtil {   
  
    /**  
     * @param args  
     */  
    public static void main(String[] args) {   
        String srcImgPath = "/Users/xuwenfeng/Desktop/App/83.jpg";   
        String iconPath = "/Users/xuwenfeng/Desktop/App/easy_oa_76@2.png";   
        markImage(iconPath, srcImgPath,null);
  
    }   
  
  
    
    
    /**
     * 把图片印刷到图片上
     * 
     * @param markImageSrc --
     *            水印文件
     * @param targetImageSrc --
     *            目标文件
     * @param x
     *            --x坐标
     * @param y
     *            --y坐标
     */
    public final static void markImage(String markImageSrc, String targetImageSrc, Integer degree) {
        try {
            //目标文件
            File targetFile = new File(targetImageSrc);
            Image src = ImageIO.read(targetFile);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            // 设置对线段的锯齿状边缘处理   
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);   
            
            g.drawImage(src, 0, 0, width, height, null);
 
            //水印文件
            File markImageFile = new File(markImageSrc);
            Image markImage = ImageIO.read(markImageFile);
            int markImageWidth = markImage.getWidth(null);
            int markImageHeight = markImage.getHeight(null);
           // g.drawImage(markImage, (width - markImageWidth) / 2,(height - markImageHeight) / 2, markImageWidth, markImageHeight, null);
            
            float alpha = 0.5f; // 透明度   
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,   
                    alpha)); 
            
            BufferedImage buffImg = new BufferedImage(src.getWidth(null),   
            		src.getHeight(null), BufferedImage.TYPE_INT_RGB);   
            
          
            if (null != degree) {   
                // 设置水印旋转   
                g.rotate(Math.toRadians(degree),(double) buffImg.getWidth() / 2, (double) buffImg.getHeight() / 2);   
            } 
            g.drawImage(markImage,(width-markImageWidth),(height-markImageHeight),markImageWidth, markImageHeight, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));   
            
         
            //水印文件结束
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImageSrc);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 打印文字水印图片
     * 
     * @param pressText
     *            --文字
     * @param targetImg --
     *            目标图片
     * @param fontName --
     *            字体名
     * @param fontStyle --
     *            字体样式
     * @param color --
     *            字体颜色
     * @param fontSize --
     *            字体大小
     * @param x --
     *            偏移量
     * @param y
     */
 
    public static void markText(String pressText, String targetImg,
            String fontName, int fontStyle, int color, int fontSize, int x,
            int y) {
        try {
            File _file = new File(targetImg);
            Image src = ImageIO.read(_file);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
             
            g.setColor(Color.RED);
            g.setFont(new Font(fontName, fontStyle, fontSize));
 
            g.drawString(pressText, wideth - fontSize - x, height - fontSize
                    / 2 - y);
            g.dispose();
            FileOutputStream out = new FileOutputStream(targetImg);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(image);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}  
