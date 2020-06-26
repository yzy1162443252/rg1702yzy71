package dao;

import java.sql.*;

public class db_connection {
    private  static final String url="jdbc:mysql://localhost:3306/666?"+"useSSL=false&serverTimezone=UTC&useUnicode=true&charactererEncoding=utf-8";
    private  static String user="root";
    private static String password="111111";
    private static Connection conn=null;
    private Statement stmt = null;
    private ResultSet rs;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException
    {
        if(conn==null)
            conn= DriverManager.getConnection(url,user,password);
        return conn;
    }
}

