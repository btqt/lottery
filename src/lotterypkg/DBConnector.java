package lotterypkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PHIVH
 */
public class DBConnector {

    public DBConnector() {}
    
    public static DBConnector getInstance() {
        if(instance == null)
            instance = new DBConnector();
        return instance;
    }
    
    public Connection connectDB() {
        try {
            Class.forName(className).newInstance();
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        try {            
            if(connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static DBConnector instance;    
    private Connection connection;
    
    private final String className = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/lottery";
    private final String user = "root";
    private final String pwd = "";
    
    public static void main(String[] args) {
        DBConnector.getInstance().connectDB();
    }
}
