package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class apply_action {
    private user user_run=new user();
    private Connection conn;
    private String back;
    private List<apply> apply_list=new ArrayList <apply>();
    car order[]=new car[80];
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;String shan2;
    String no=session.get("no").toString();
    public  String read() throws SQLException
    {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from apply_info where receive_id = '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            apply apply_run=new apply();
            apply_run.setId(rs.getString("id"));
            apply_run.setReceive_id(rs.getString("receive_id"));
            apply_run.setSend_id(rs.getString("send_id"));
            apply_run.setSend_name(rs.getString("send_name"));
            apply_list.add(apply_run);
        }
        session.put("apply_list",apply_list);
        return "find_success";
    }
    public  String add_friend() throws SQLException
    {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from friend where own_id = '"+no+"' && friend_id = '"+shan2+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if(!rs.next())
        {
            String add="insert into friend(friend_id,own_id)values('"+shan2+ "','"+no+ "')";
            stmt.executeUpdate(add);
            add="insert into friend(friend_id,own_id)values('"+no+ "','"+shan2+ "')";
            stmt.executeUpdate(add);
        }
        String del="DELETE FROM apply_info WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        sql="select * from apply_info where receive_id = '"+no+"'";
        rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            apply apply_run=new apply();
            apply_run.setId(rs.getString("id"));
            apply_run.setReceive_id(rs.getString("receive_id"));
            apply_run.setSend_id(rs.getString("send_id"));
            apply_run.setSend_name(rs.getString("send_name"));
            apply_list.add(apply_run);
        }
        session.put("apply_list",apply_list);
        return "find_success";
    }
    public  String refuse_friend() throws SQLException {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String del="DELETE FROM apply_info WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        String sql="select * from apply_info where receive_id = '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            apply apply_run=new apply();
            apply_run.setId(rs.getString("id"));
            apply_run.setReceive_id(rs.getString("receive_id"));
            apply_run.setSend_id(rs.getString("send_id"));
            apply_run.setSend_name(rs.getString("send_name"));
            apply_list.add(apply_run);
        }
        session.put("apply_list",apply_list);
        return "find_success";
    }

    public String getShan2() {
        return shan2;
    }

    public void setShan2(String shan2) {
        this.shan2 = shan2;
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

}
