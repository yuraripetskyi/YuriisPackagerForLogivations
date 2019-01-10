package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SConnection {
    private static Connection conn;
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "root");
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/packager", properties);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){

        return conn;
    }
}
