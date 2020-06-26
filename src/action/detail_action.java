package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.db_connection;
import model.*;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class detail_action extends ActionSupport implements ServletContextAware {
    private ServletContext context;
    public void setServletContext(ServletContext context) {
        this.context = context;
    }

    private List<car> car_list=new ArrayList <car>();
    private File upload;//定义一个File ,变量名要与jsp中的input标签的name一致
    private static String uploadContentType;//上传文件的mimeType类型
    private  String uploadFileName;//上传文件的名称
    private car car_run=new car();
    private photo ph_info=new photo();
    private Connection conn;
    private String back;
    //private List<String> src_list=new ArrayList <String>();
    private List<photo> ph_list=new ArrayList <photo>();
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;  //传递各种参数
    String shan2;
    String no=session.get("no").toString();
    public  String read() throws SQLException {

        String sql = "select * from car_photo where car_id = '"+shan+"'";
        //session.put("k",sql);
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            photo ph_info=new photo();
            ph_info.setSrc(rs.getString("src"));
            ph_info.setId(rs.getString("id"));
            ph_info.setCar_id(rs.getString("car_id"));
            ph_list.add(ph_info);
        }
        session.put("pho_list",ph_list);
        sql="select * from car_information where id = '"+shan+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next()) {
            car car_run = new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_run.setDescribe(rs.getString("describe"));
            session.put("info",car_run);
        }
        session.put("back",back);
        if("shop".equals(back)) return "shop";
        else return "customer";
    }
    public  String chan() throws SQLException {

        String sql = "select * from car_photo where car_id = '"+shan+"'";
        //session.put("k",sql);
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            photo ph_info=new photo();
            ph_info.setSrc(rs.getString("src"));
            ph_info.setId(rs.getString("id"));
            ph_info.setCar_id(rs.getString("car_id"));
            ph_list.add(ph_info);
        }
        session.put("pho_list",ph_list);
        sql="select * from car_information where id = '"+shan+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next()) {
            car car_run = new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_run.setDescribe(rs.getString("describe"));
            session.put("info",car_run);
        }
        return "success";
    }
    public  String dele() throws SQLException {

        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String del="DELETE FROM car_photo WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        String sql = "select * from car_photo where car_id = '"+shan2+"'";
        //session.put("k",sql);
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            photo ph_info=new photo();
            ph_info.setSrc(rs.getString("src"));
            ph_info.setId(rs.getString("id"));
            ph_info.setCar_id(rs.getString("car_id"));
            ph_list.add(ph_info);
        }
        session.put("pho_list",ph_list);
        sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        rs=stmt.executeQuery(sql);
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
            car_run.setDescribe(rs.getString("describe"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        return "change_car_detail";
    }
    public  String add() throws SQLException {
        //if (uploadFileName==null) uploadFileName=shan;
        session.put("k",uploadFileName+"66");
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String add="insert into car_photo(car_id,src)values('"+shan+
                "','"+uploadFileName+
                "')";
        String realPath=context.getRealPath("car_pic");

        try {
            File dest = new File(realPath,uploadFileName);
            FileUtils.copyFile(upload, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stmt.executeUpdate (add);
        String sql = "select * from car_photo where car_id = '"+shan+"'";
        //session.put("k",sql);
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            photo ph_info=new photo();
            ph_info.setSrc(rs.getString("src"));
            ph_info.setId(rs.getString("id"));
            ph_info.setCar_id(rs.getString("car_id"));
            ph_list.add(ph_info);
        }
        session.put("pho_list",ph_list);
        sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        rs=stmt.executeQuery(sql);
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
            car_run.setDescribe(rs.getString("describe"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        return "change_car_detail";
    }
    public  String add_describe() throws SQLException {
        //if (uploadFileName==null) uploadFileName=shan;
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String add="UPDATE car_information SET `describe` = '"+back+ "' WHERE id = '"+shan+"'";
        stmt.executeUpdate (add);
        //session.put("k",add);
        String sql = "select * from car_photo where car_id = '"+shan+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            photo ph_info=new photo();
            ph_info.setSrc(rs.getString("src"));
            ph_info.setId(rs.getString("id"));
            ph_info.setCar_id(rs.getString("car_id"));
            ph_list.add(ph_info);
        }
        session.put("pho_list",ph_list);
        sql = "select * from car_information where shop = '"+no+"'";
        conn= db_connection.getConnection();
        rs=stmt.executeQuery(sql);
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
            car_run.setDescribe(rs.getString("describe"));
            car_list.add(car_run);
        }
        session.put("list_own",car_list);
        sql="select * from car_information where id = '"+shan+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next()) {
            car car_run = new car();
            car_run.setId(rs.getString("id"));
            car_run.setName(rs.getString("name"));
            car_run.setPrice(rs.getString("price"));
            car_run.setShop(rs.getString("shop"));
            car_run.setPic(rs.getString("pic"));
            car_run.setCredit(rs.getString("credit"));
            car_run.setInventory(rs.getString("inventory"));
            car_run.setDescribe(rs.getString("describe"));
            session.put("info",car_run);
        }
        return "change_car_detail";
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
        detail_action.uploadContentType = uploadContentType;
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

    public String getShan() {
        return shan;
    }

    public void setShan(String shan) {
        this.shan = shan;
    }

    public String getShan2() {
        return shan2;
    }

    public void setShan2(String shan2) {
        this.shan2 = shan2;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public photo getPh_info() {
        return ph_info;
    }

    public void setPh_info(photo ph_info) {
        this.ph_info = ph_info;
    }

}
