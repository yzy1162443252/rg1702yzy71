package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class manager_action {
    private car car_run=new car();
    private Connection conn;
    private String back;
    private List<car> car_list=new ArrayList <car>();
    private List<user> user_list=new ArrayList <user>();
    car order[]=new car[80];
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;String shan2;
    String no=session.get("no").toString();
    public  String read_car() throws SQLException
    {

        String sql = "select * from car_information";
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
        }
        //Collections.sort(car_list);
        if ("price_up".equals(back))
            Collections.sort(car_list, new Comparator<car>() {public int compare(car u1, car u2) { int diff = Integer.valueOf(u1.getPrice()) - Integer.valueOf(u2.getPrice());if (diff > 0) { return 1; }else if (diff < 0) { return -1; }return 0; }});
        if ("price_down".equals(back))
            Collections.sort(car_list, new Comparator<car>() {public int compare(car u1, car u2) { int diff = Integer.valueOf(u1.getPrice()) - Integer.valueOf(u2.getPrice());if (diff > 0) { return -1; }else if (diff < 0) { return 1; }return 0; }});
        if ("credit_up".equals(back))
            Collections.sort(car_list, new Comparator<car>() {public int compare(car u1, car u2) { int diff = Integer.valueOf(u1.getCredit()) - Integer.valueOf(u2.getCredit());if (diff > 0) { return 1; }else if (diff < 0) { return -1; }return 0; }});
        session.put("manager_car_list",car_list);
        return "manager_car_show";
    }
    public  String read_user() throws SQLException
    {
        String sql = "select * from user";
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setName(rs.getString("username"));
            user_run.setSex(rs.getString("sex"));
            user_run.setAddress(rs.getString("address"));
            user_run.setBirthday(rs.getString("birthday"));
            user_run.setId(rs.getString("id"));
            user_list.add(user_run);
        }
        //Collections.sort(car_list);
        session.put("manager_user_list",user_list);
        return "manager_user_show";
    }
    public  String find_car() throws SQLException {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from car_information where name like'%"+back+"%'";
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
        session.put("manager_car_list",car_list);
        return "manager_car_show";
    }
    public  String find_user() throws SQLException {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from user where username like'%"+back+"%'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setName(rs.getString("username"));
            user_run.setSex(rs.getString("sex"));
            user_run.setAddress(rs.getString("address"));
            user_run.setBirthday(rs.getString("birthday"));
            user_run.setId(rs.getString("id"));
            user_list.add(user_run);
        }
        session.put("manager_user_list",user_list);
        return "manager_user_show";
    }
    public  String delete_car() throws SQLException {

        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String del="DELETE FROM car_photo WHERE car_id = '"+shan+"'";
        stmt.executeUpdate (del);
        del="DELETE FROM car_information WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        del="delete from cart where car_id = '"+shan+"'";
        stmt.executeUpdate (del);
        String sql = "select * from car_information where shop = '"+shan+"'";
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
        session.put("manager_car_list",car_list);
        return "manager_car_show";
    }
    public  String delete_user() throws SQLException {

        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql="select * from car_information where shop = '"+shan+"'";
        ResultSet rs=stmt.executeQuery(sql);
        Statement st2=conn.createStatement();
        while(rs.next())
        {
            String del="DELETE FROM cart WHERE car_id = '"+rs.getString("id")+"'";
            st2.executeUpdate (del);
            del="DELETE FROM car_photo WHERE car_id = '"+rs.getString("id")+"'";
            st2.executeUpdate (del);
        }
        String del="DELETE FROM user WHERE id = '"+shan+"'";
        stmt.executeUpdate (del);
        del="delete from car_information where shop = '"+shan+"'";
        stmt.executeUpdate (del);
        del="delete from friend where friend_id = '"+shan+"' || own_id ='"+shan+"'";
        stmt.executeUpdate (del);
        del="delete from apply_info where send_id = '"+shan+"' || receive_id ='"+shan+"'";
        stmt.executeUpdate (del);
        sql = "select * from user ";
        rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            user user_run=new user();
            user_run.setName(rs.getString("username"));
            user_run.setSex(rs.getString("sex"));
            user_run.setAddress(rs.getString("address"));
            user_run.setBirthday(rs.getString("birthday"));
            user_run.setId(rs.getString("id"));
            user_list.add(user_run);
        }
        session.put("manager_user_list",user_list);
        return "manager_user_show";
    }
    public String getShan() {
        return shan;
    }

    public void setShan(String shan) {
        this.shan = shan;
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

    public String getShan2() {
        return shan2;
    }

    public void setShan2(String shan2) {
        this.shan2 = shan2;
    }
/* public static void main(String[] args)
     {
         pl.read();
         while()
     }*/
}
