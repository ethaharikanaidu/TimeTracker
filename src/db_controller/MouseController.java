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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseController {

    public static void updateMouse(String time) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "update mouse set clickCount=clickCount+1 where mouseId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, time);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object[] getDate() {
        Object tracker[] = new Object[1];
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select clickDate from mouse where mouseId=0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                tracker[0] = rst.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tracker;
    }

    public static void resetMouse() {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "update mouse set clickCount=0,clickDate=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public static ArrayList<Object[]> getMouseClicks() {
        ArrayList<Object[]> trackers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select mouseId,clickCount from mouse";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Object tracker[] = new Object[4];
                tracker[0] = rst.getInt(1);
                tracker[1] = rst.getInt(2);
                trackers.add(tracker);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trackers;
    }
}
