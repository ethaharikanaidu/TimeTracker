/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import chart.BarChart_AWT;
import controller.MouseClickMonitor;
import db_controller.MouseController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class PnlActivity extends javax.swing.JPanel {

    private static PnlActivity pnlActivity;
    private int count;
    private DateFormat dateFormat=new SimpleDateFormat("H");
    private BarChart_AWT chart;

    /**
     * Creates new form PnlActivity
     */
    public PnlActivity() {
        initComponents();

//        setSize(1700, 750);
        setBounds(0, 0, 1920, 830);
        setVisible(true);

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
//            System.exit(1);
        }

        // Construct the example object.
        MouseClickMonitor example = new MouseClickMonitor();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(example);
        GlobalScreen.addNativeMouseMotionListener(example);
        
        createChart();
    }
    
      public void createChart() {
        chart = new BarChart_AWT();
        chart.setChartToMouse();
        chart.getChartPanel().setBounds(0, 0, 1700, 700);
        jPanel1.removeAll();
        jPanel1.add(chart.getChartPanel());
        jPanel1.repaint();
    }

    public void setCount() {
//        count++;
        
        Object[] date = MouseController.getDate();
        if(!date[0].toString().equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
            MouseController.resetMouse();
        }
        MouseController.updateMouse(dateFormat.format(new Date()));
        
        createChart();
        
        lblDate.setText("Mouse activity for - "+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    public static PnlActivity getInstance() {
        if (pnlActivity == null) {
            pnlActivity = new PnlActivity();
        }
        return pnlActivity;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);
        add(jPanel1);
        jPanel1.setBounds(90, 60, 1830, 820);

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("jLabel1");
        lblDate.setFocusable(false);
        lblDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(lblDate);
        lblDate.setBounds(0, 10, 1920, 44);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDate;
    // End of variables declaration//GEN-END:variables
}
