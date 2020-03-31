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

public class TrackerController {

    public static void addTracker(String name, int time) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "INSERT INTO project (projectName,projectTime,projectDate) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, time);
            preparedStatement.setObject(3, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void updateTracker(int time, int id) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "update project set projectTime=?,projectDate=? where projectId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, time);
            preparedStatement.setObject(2, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            preparedStatement.setObject(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Object[]> getTrackers() {
        ArrayList<Object[]> trackers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select projectId,projectName,projectTime,projectDate from project";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Object tracker[] = new Object[4];
                tracker[0] = rst.getInt(1);
                tracker[1] = rst.getString(2);
                tracker[2] = rst.getInt(3);
                tracker[3] = rst.getString(4);
                trackers.add(tracker);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trackers;
    }
    
     public static ArrayList<Object[]> getTrackersViaDate(String date) {
        ArrayList<Object[]> trackers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select projectId,projectName,projectTime,projectDate from project where projectDate=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, date);
            ResultSet rst = preparedStatement.executeQuery();
            while (rst.next()) {
                Object tracker[] = new Object[4];
                tracker[0] = rst.getInt(1);
                tracker[1] = rst.getString(2);
                tracker[2] = rst.getInt(3);
                tracker[3] = rst.getString(4);
                trackers.add(tracker);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trackers;
    }

    public static Object[] getTracker(int id) {
        Object tracker[] = new Object[4];
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "select projectId,projectName,projectTime,projectDate from project where projectId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            ResultSet rst = preparedStatement.executeQuery();
            if (rst.next()) {
                tracker[0] = rst.getInt(1);
                tracker[1] = rst.getString(2);
                tracker[2] = rst.getInt(3);
                tracker[3] = rst.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tracker;
    }

    public static void deleteTracker(int id) {
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            String sql = "delete from project where projectId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TrackerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
