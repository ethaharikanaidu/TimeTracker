/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_controller;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppController {

    public static void updateApp(int time) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "update app set appTime=?,appDate=? where appId=1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, time);
            preparedStatement.setObject(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object[] getDateAndTime() {
        Object tracker[] = new Object[4];
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select appTime,appDate from app where appId=1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                tracker[0] = rst.getInt(1);
                tracker[1] = rst.getString(2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tracker;
    }
}
