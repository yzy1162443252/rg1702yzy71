package action;

import com.opensymphony.xwork2.ActionContext;
import dao.db_connection;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class cart_action {
    private car car_run=new car();
    private Connection conn;
    private List<car> car_list=new ArrayList <car>();
    ActionContext actionContext=ActionContext.getContext();
    Map session=actionContext.getSession();
    String shan;  //传递各种参数
    String shan2;
    String no=session.get("no").toString();
    public  String read() throws SQLException {
        String sql = "select * from cart where user_id = '"+no+"'";
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        int all=0;
        int k=0;
        while(rs.next())
        {
            car car_run=new car();
            String car_id=rs.getString("car_id");
            car_run.setNum(rs.getString("car_num"));
            Statement ro=conn.createStatement();
            sql = "select * from car_information where id = '"+car_id+"'";
            ResultSet rs2=ro.executeQuery(sql);
            while(rs2.next())
                 {
                  car_run.setId(rs2.getString("id"));
                  car_run.setName(rs2.getString("name"));
                  car_run.setPrice(rs2.getString("price"));
                  car_run.setShop(rs2.getString("shop"));
                  car_run.setPic(rs2.getString("pic"));
                  car_run.setCredit(rs2.getString("credit"));
                  car_run.setInventory(rs2.getString("inventory"));
                  k=Integer.valueOf(car_run.getPrice());
                  car_list.add(car_run);
                 }
            all+=k*Integer.valueOf(car_run.getNum());
        }
        session.put("cart_list",car_list);
        session.put("all",all+"");
        return "cart_show";
    }
    public  String chan() throws SQLException {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        int k;
        if(shan2.equals("1"))
            k=1;
        else
            k=-1;
        String sql = "select * from cart where user_id = '"+no+"' && car_id = '"+shan+"'";
        ResultSet rs=stmt.executeQuery(sql);
        rs=stmt.executeQuery(sql);
        String kn = null;
        while(rs.next())
        {
            kn=rs.getString("cart_id");
            int nu=rs.getInt("car_num");
            nu=nu+k;k=nu;
            String w=nu+"";
            String chang="UPDATE cart SET car_num = '"+w+
                    "' WHERE cart_id = '"+kn+"'";
            Statement ro=conn.createStatement();
            ro.execute(chang);
        }
        if (k==0)
        {
            String del="DELETE FROM cart WHERE cart_id = '"+kn+"'";
            stmt.executeUpdate (del);
        }
        read();
        return "cart_show";
    }
    public  String add() throws SQLException
    {
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        String sql = "select * from cart where user_id = '"+no+"' && car_id = '"+shan+"'";
        ResultSet rs=stmt.executeQuery(sql);
        if (!rs.next())
            {
            String add="insert into cart(car_id,user_id,car_num)values('"+
                +Integer.parseInt(shan)+"','"+Integer.parseInt(no)+"','"+1+
                "')";
            stmt.executeUpdate (add);
            }
        else
            {
                rs=stmt.executeQuery(sql);
                while(rs.next())
              {
                String k=rs.getString("cart_id");
                int nu=rs.getInt("car_num");
                nu=nu+1;
                String w=nu+"";
                String chang="UPDATE cart SET car_num = '"+w+
                        "' WHERE cart_id = '"+k+"'";
                Statement ro=conn.createStatement();
                ro.execute(chang);
              }
            }
        read();
        return "car_show";
    }
    public  String buy() throws SQLException {


        int all=0;
        int k=0;
        String chang="";
        String sql = "select * from cart where user_id = '"+no+"'";
        conn= db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            car car_run=new car();
            String cart_id=rs.getString("cart_id");
            String car_id=rs.getString("car_id");
            car_run.setNum(rs.getString("car_num"));
            Statement ro=conn.createStatement();
            sql = "select * from car_information where id = '"+car_id+"'";
            ResultSet rs2=ro.executeQuery(sql);
            while(rs2.next())
            {
                car_run.setId(rs2.getString("id"));
                car_run.setName(rs2.getString("name"));
                car_run.setPrice(rs2.getString("price"));
                car_run.setShop(rs2.getString("shop"));
                car_run.setPic(rs2.getString("pic"));
                car_run.setCredit(rs2.getString("credit"));
                car_run.setInventory(rs2.getString("inventory"));
                int want_buy=Integer.valueOf(car_run.getNum());
                int have=Integer.valueOf(car_run.getInventory());
                session.put("want",want_buy);
                session.put("have",have);
                Statement nei=conn.createStatement();
                if (want_buy<=have)
                    {
                        have=have-want_buy;
                        k=Integer.valueOf(car_run.getPrice());
                        String del="DELETE FROM cart WHERE cart_id = '"+cart_id+"'";
                        nei.executeUpdate (del);
                        chang="UPDATE car_information SET inventory = '"+have+
                            "' WHERE id = '"+car_run.getId()+"'";
                        nei.executeUpdate(chang);
                    }
                else
                    {
                        want_buy=want_buy-have;
                        chang="UPDATE cart SET car_num = '"+want_buy+
                                "' WHERE cart_id = '"+cart_id+"'";
                        nei.executeUpdate(chang);
                        chang="UPDATE car_information SET inventory = '"+0+
                                "' WHERE id = '"+car_id+"'";
                        nei.executeUpdate(chang);
                        car_list.add(car_run);
                    }
            }
            all+=k*Integer.valueOf(car_run.getNum());
        }
        session.put("cart_list",car_list);
        session.put("all",all+"");
        return "cart_show";
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

    public String getShan2() {
        return shan2;
    }

    public void setShan2(String shan2) {
        this.shan2 = shan2;
    }
/*public static void main(String[] args)
     {
        System.out.print(no);
     }*/
}
