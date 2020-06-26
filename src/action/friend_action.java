package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class friend_action {
    private user user_run=new user();
    private Connection conn;
    private String back;
    private List<user> user_list=new ArrayList <user>();
    car order[]=new car[80];
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;
    String shan2;
    String no=session.get("no").toString();
    public  String read() throws SQLException
    {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from user where username like'%"+back+"%' && id != '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        session.put("k",sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setId(rs.getString("id"));
            user_run.setUsername(rs.getString("username"));
            user_list.add(user_run);
        }
        //session.put("k","back");
        session.put("user_list",user_list);
        return "car_show";
    }
    public  String add_friend() throws SQLException
    {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String add="insert into apply_info(send_id,receive_id,send_name)values('"+no+ "','"+shan+ "','"+shan2+"')";
        stmt.executeUpdate(add);
        return "find_success";
    }
    public  String find() throws SQLException {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from user where username like'%"+back+"%' && id != '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setId(rs.getString("id"));
            user_run.setUsername(rs.getString("username"));
            user_run.setAddress(rs.getString("address"));
            user_list.add(user_run);
        }
        session.put("k","back");
        session.put("user_list",user_list);
        return "find_success";
    }

    public String getShan() {
        return shan;
    }

    public void setShan(String shan) {
        this.shan = shan;
    }

    public user getUser_run() {
        return user_run;
    }

    public void setUser_run(user user_run) {
        this.user_run = user_run;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public String getShan2() {
        return shan2;
    }

    public void setShan2(String shan2) {
        this.shan2 = shan2;
    }
}
