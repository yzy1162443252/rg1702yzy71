package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class show_action {
    private car car_run=new car();
    private Connection conn;
    private String back;
    private List<car> car_list=new ArrayList <car>();
    car order[]=new car[80];
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;
    String no=session.get("no").toString();
    public  String read() throws SQLException
    {
        String sql = "select * from car_information where shop != '"+no+"'"+"&& inventory != 0";
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
        session.put("list",car_list);
        return "car_show";
    }
    public  String find() throws SQLException {

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
        session.put("k","back");
        session.put("list",car_list);
        return "car_show";
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

/* public static void main(String[] args)
     {
         pl.read();
         while()
     }*/
}
