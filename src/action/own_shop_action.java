package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.db_connection;
import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class own_shop_action extends ActionSupport implements ServletContextAware{
    private ServletContext context;
    public void setServletContext(ServletContext context) {
        this.context = context;

    }
    private File upload;//定义一个File ,变量名要与jsp中的input标签的name一致
    private static String uploadContentType;//上传文件的mimeType类型
    private  String uploadFileName;//上传文件的名称
    private InputStream inputStream;
    private car car_run=new car();
    private Connection conn;
    private String back;
    private List<car> car_list=new ArrayList <car>();
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;
    String no=session.get("no").toString();
    public  String read() throws SQLException {
        String sql = "select * from car_information where shop = '"+shan+"'";
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_list.add(car_run);
        }session.put("k",sql);
        if(shan.equals(no)) {
             session.put("list_own",car_list);return "own_shop";}

        else
            {session.put("list",car_list);return "other_shop";}
    }
    public  String dele() throws SQLException {

        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String del="DELETE FROM car_information WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        del="delete from cart where car_id = '"+shan+"'";
        stmt.executeUpdate (del);
        String sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        return "own_shop";
    }
    public  String chan() throws SQLException {
        if (upload==null) uploadFileName=shan;
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String chang="UPDATE car_information SET name = '"+car_run.getName()+"', price = '"+ car_run.getPrice()+
                "', shop = '"+ no+"', pic = '"+ uploadFileName+
                "' WHERE id = '"+car_run.getId()+"'";
        try {
            if (!uploadFileName.equals(shan)){String realPath=context.getRealPath("car_pic");
            File dest = new File(realPath,uploadFileName);
            FileUtils.copyFile(upload, dest);}
        } catch (IOException e) {
            e.printStackTrace();
        }
        stmt.executeUpdate (chang);
        //session.put("k",chang);
        String sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        return "own_shop";
    }
    public  String add() throws SQLException {
        if (upload==null) uploadFileName="failed.jpg";
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String add="insert into car_information(name,price,shop,pic,inventory)values('"+car_run.getName()+
                "','"+car_run.getPrice()+"','"+no+"','"+uploadFileName+"','"+car_run.getInventory()+
                "')";
        String realPath=context.getRealPath("car_pic");
        try {
            File dest = new File(realPath,uploadFileName);
            FileUtils.copyFile(upload, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stmt.executeUpdate (add);
        String sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        return "own_shop";
    }
    public  String find() throws SQLException {

        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from car_information where name like'%"+shan+"%'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_list.add(car_run);
        }
        session.put("list",car_list);
        return "own_shop";
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public static String getUploadContentType() {
        return uploadContentType;
    }

    public static void setUploadContentType(String uploadContentType) {
        own_shop_action.uploadContentType = uploadContentType;
    }


    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public car getCar_run() {
        return car_run;
    }

    public void setCar_run(car car_run) {
        this.car_run = car_run;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getShan() {
        return shan;
    }

    public void setShan(String shan) {
        this.shan = shan;
    }

/* public static void main(String[] args)
     {
         pl.read();
         while()
     }*/

}



