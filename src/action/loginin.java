package action;
import com.opensymphony.xwork2.ActionContext;
import model.*;
import dao.*;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.*;
import java.util.Map;

public class loginin  extends ActionSupport {
     private user userin;
     private Connection conn;
    private Map<String, Object> mysession;

    public String execute() throws Exception
    {
        String sql = "select * from user ";
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        ActionContext actionContext=ActionContext.getContext();
        Map session=actionContext.getSession();
        session.put("username",userin.getUsername());
        session.put("password",userin.getPassword());
        int i=0;
        while (rs.next ())
        {
            String name2=rs.getString (2);
            String name3=rs.getString(3);
            if ( name2.equals(userin.getUsername())
                    && name3.equals(userin.getPassword()))
            {
                i=1;
                session.put("birthday",rs.getString("birthday"));
                session.put("sex",rs.getString("sex"));
                session.put("address",rs.getString("address"));
                session.put("no",rs.getString("id"));
            }
        }

       if ( i == 1){
            return "success";
       }
        else
            {

                sql = "select * from manager ";
                conn=db_connection.getConnection();
                stmt=conn.createStatement();
                rs=stmt.executeQuery(sql);
                while (rs.next ())
                {
                    String name2=rs.getString (2);
                    String name3=rs.getString(3);
                    if ( name2.equals(userin.getUsername())
                            && name3.equals(userin.getPassword()))
                    {   session.put("username","管理员");
                        i=1;
                        session.put("no",rs.getString("id"));
                    }
                }

                if(i==1) {return "success";}
                else {return "error";}

            }
    }
    public String register() throws Exception
    {

        if ( userin.getUsername().equals("") || userin.getPassword().equals(""))
            return "reg_error";
        ActionContext actionContext=ActionContext.getContext();
        Map session=actionContext.getSession();
        session.put("username",this.userin.getUsername());
        session.put("password",this.userin.getPassword());
        session.put("birthday",userin.getBirthday());
        session.put("sex",userin.getSex());
        session.put("address",userin.getAddress());
        String add="insert into user(username,password,birthday,sex,address)values("
                +"'"+userin.getUsername()+"'"+","+"'"+userin.getPassword()+"'"+","+"'"+userin.getBirthday()+"'"+","
                +"'"+userin.getSex()+"'"+","+"'"+userin.getAddress()+"'"+ ")";
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        int rs=stmt.executeUpdate (add);
        return "reg_success";
    }
    public String change() throws Exception
    {
        if ( userin.getUsername().equals("") || userin.getPassword().equals(""))
            return "reg_error";
        ActionContext actionContext=ActionContext.getContext();
        Map session=actionContext.getSession();
        session.put("username",this.userin.getUsername());
        session.put("password",this.userin.getPassword());
        session.put("birthday",userin.getBirthday());
        session.put("sex",userin.getSex());
        session.put("address",userin.getAddress());
        Object ru=session.get("no");
        String chang="UPDATE user SET age = '"+userin.getAge()+
                "', sex = '"+userin.getSex()+"', password = '"+userin.getPassword()+
                "', address = '"+userin.getAddress()+ "', birthday = '"+userin.getBirthday()+
                "' WHERE id = '"+ru.toString()+"'";
        conn=db_connection.getConnection();
        Statement stmt=conn.createStatement();
        int rs=stmt.executeUpdate (chang);
        return "change_success";
    }
    public Map<String, Object> getMysession() {
        return mysession;
    }

    public void setMysession(Map<String, Object> mysession) {
        this.mysession = mysession;
    }

    public user getUserin() {
        return userin;
    }

    public void setUserin(user userin) {
        this.userin = userin;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public user getModel()
    {return userin;}

}
