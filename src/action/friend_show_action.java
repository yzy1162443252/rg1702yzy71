package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.apply;
import model.car;
import model.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class friend_show_action {
    private user user_run=new user();
    private Connection conn;
    private String back;
    private List<user> user_list=new ArrayList<user>();
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;String shan2;
    String no=session.get("no").toString();
    public  String read() throws SQLException
    {
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from friend where own_id = '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setId(rs.getString("friend_id"));
            Statement ro=conn.createStatement();
            sql = "select * from user where id = '"+user_run.getId()+"'";
            ResultSet rs2=ro.executeQuery(sql);
            while(rs2.next())
            {
                user_run.setName(rs2.getString("username"));
                user_run.setSex(rs2.getString("sex"));
                user_run.setAddress(rs2.getString("address"));
                user_run.setBirthday(rs2.getString("birthday"));
            }
            user_list.add(user_run);
        }
        session.put("friend_list",user_list);
        return "friend_show";
    }
    public  String delete() throws SQLException
    {
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String del="DELETE FROM friend WHERE own_id = '"+no+"' && friend_id = '"+shan+"'";
        stmt.executeUpdate (del);
        del="DELETE FROM friend WHERE own_id = '"+shan+"' && friend_id = '"+no+"'";
        stmt.executeUpdate (del);
        String sql="select * from friend where own_id = '"+no+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setId(rs.getString("friend_id"));
            Statement ro=conn.createStatement();
            sql = "select * from user where id = '"+user_run.getId()+"'";
            ResultSet rs2=ro.executeQuery(sql);
            while(rs2.next())
            {
                user_run.setName(rs2.getString("username"));
                user_run.setSex(rs2.getString("sex"));
                user_run.setAddress(rs2.getString("address"));
                user_run.setBirthday(rs2.getString("birthday"));
            }
            user_list.add(user_run);
        }
        session.put("friend_list",user_list);
        return "friend_show";
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
