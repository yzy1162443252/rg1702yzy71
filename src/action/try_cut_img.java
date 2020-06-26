package action;

import jdk.internal.org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.xml.transform.ErrorListener;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import com.opensymphony.xwork2.ActionContext;
import model.*;
import dao.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

public class try_cut_img extends ActionSupport implements ServletContextAware {
    private ServletContext context;
    public void setServletContext(ServletContext context) {
        this.context = context;

    }
    private File upload;//定义一个File ,变量名要与jsp中的input标签的name一致
    private static String uploadContentType;//上传文件的mimeType类型
    private static String uploadFileName;//上传文件的名称
    private InputStream inputStream;
    String filename;
    private String filePath;
    String shan;

    static String  path = "null";
    static String yuan=uploadFileName;
    static String guo="guo.jpg";
    static String cun=null;
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();

    public static int color_range = 210;


    public String file() throws SQLException{
        try {

            String realPath=context.getRealPath("sculpture");
            session.put("k",realPath);
            String savePath = realPath + "/" + uploadFileName;
            cun=session.get("no")+".jpg";
            yuan=uploadFileName;
            path=realPath;
            File dest = new File(realPath,yuan);
            FileUtils.copyFile(upload, dest);
            save();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "car_show";
    }

    public  void save() throws IOException{
        try {
            // 读取图片//

            BufferedImage bi1 = ImageIO.read(new File(path+"/"+uploadFileName));

            BufferedImage bi2 = new BufferedImage(Math.min(bi1.getHeight(),bi1.getWidth()), Math.min(bi1.getHeight(),bi1.getWidth()),
                    BufferedImage.TYPE_INT_RGB);
            Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, Math.min(bi1.getHeight(),bi1.getWidth()), Math.min(bi1.getHeight(),bi1.getWidth()));
            Graphics2D g2 = bi2.createGraphics();
            g2.setBackground(Color.WHITE);
            g2.fill(new Rectangle(bi2.getWidth(), bi2.getHeight()));
            g2.setClip(shape);
            //设置抗锯齿
            g2.drawImage(bi1, 0, 0, null);
            g2.dispose();

            ImageIO.write(bi2, "jpg", new File(path+"/"+guo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedImage image = ImageIO.read(new File(path+"/"+guo));
        // 高度和宽度
        int height = image.getHeight();
        int width = image.getWidth();

        // 生产背景透明和内容透明的图片
        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics(); // 获取画笔
        g2D.drawImage(imageIcon.getImage(), 0, 0, null); // 绘制Image的图片

        int alpha = 0; // 图片透明度
        // 外层遍历是Y轴的像素
        for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
            // 内层遍历是X轴的像素
            for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                // 对当前颜色判断是否在指定区间内
                if (colorInRange(rgb)) {
                    alpha = 0;
                } else {
                    // 设置为不透明
                    alpha = 255;
                }
                // #AARRGGBB 最前两位为透明度
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        // 绘制设置了RGB的新图片
        g2D.drawImage(bufferedImage, 0, 0, null);

        // 生成图片为PNG
        ImageIO.write(bufferedImage, "png", new File(path+"/"+cun));
        //session.put("k","E:/upload/"+cun);
        }


    public static boolean colorInRange(int color) {
        int red = (color & 0xff0000) >> 16;// 获取color(RGB)中R位
        int green = (color & 0x00ff00) >> 8;// 获取color(RGB)中G位
        int blue = (color & 0x0000ff);// 获取color(RGB)中B位
        // 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
        if (red >= color_range && green >= color_range && blue >= color_range) {
            return true;
        };
        return false;
    }


    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}

