/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dBConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("org.sqlite.JDBC");//--Load JDBC driver from library(mysql connector)
            System.out.println(System.getProperty("user.dir"));
//            System.out.println(this.getClass().getClassLoader().getResource("db/appmonitor.db").getPath());
//            connection = DriverManager.getConnection("jdbc:sqlite:" + this.getClass().getClassLoader().getResource("db/appmonitor.db").getPath());//--Establish the database connection via db url, db username and db password
            connection = DriverManager.getConnection("jdbc:sqlite:" + "\\" + System.getProperty("user.dir") + "\\sqlite\\appmonitor.db");//--Establish the database connection via db url, db username and db password
//            connection=DriverManager.getConnection("jdbc:sqlite:appmonitor.db"); 
        } catch (SQLException e) {//--Catch if any sql exception occurred
            e.printStackTrace();
        } catch (ClassNotFoundException e) {//--Catch if driver is not loaded or cannot be found
            e.printStackTrace();
        }
    }

    //---------------------------------------Return DBConnection object-------------------------------------------------
    public static DBConnection getDBConnection() {
        if (dBConnection == null) {
            dBConnection = new DBConnection();//--Creates DBConnection object to retrieve database connection
        }
        return dBConnection;
    }

    //-----------------------------------------Return database connection-----------------------------------------------
    public Connection getConnection() {
        return connection;
    }
}
