/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Timer;
import db_controller.TrackerController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class PnlTracker extends javax.swing.JPanel {

    private int id;
    private static PnlTracker tracker;
    private Timer timer = new Timer();

    /**
     * Creates new form Tracker
     */
    private PnlTracker() {
        initComponents();

//        setSize(1700, 880);
        setBounds(0, 0, 1920, 830);
        customizeTables();
        setTableData();
        setVisible(true);

        btnAddProject.setBackground(Color.WHITE);
        btnStart.setBackground(new Color(115, 185, 123));
        btnStart.setForeground(new Color(3, 72, 11));
        
        Component[] components = pnlTimer.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setContentAreaFilled(false);
                button.setOpaque(true);
            }
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (timer.isRunning()) {
                        lblTime.setText(timer.runTime());
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    public static PnlTracker getInstance() {
        if (tracker == null) {
            tracker = new PnlTracker();
        }
        return tracker;
    }

    public void setProjectText(String text) {
        try {
            id = Integer.parseInt(text);
            Object[] trackerObj = TrackerController.getTracker(Integer.parseInt(text));
            btnAddProject.setText(trackerObj[1].toString());
            timer.setTime(Integer.parseInt(trackerObj[2].toString()));
            lblTime.setText(timer.convertTime(Integer.parseInt(trackerObj[2].toString())));
        } catch (Exception e) {
            id = 0;
            btnAddProject.setText(text);
        }
    }

    public void setTime(String time) {
        lblTime.setText(time);
    }

    public void setProjectIdToZero() {
        id = 0;
    }

    private void customizeTables() {

        class CustomizeTable extends DefaultTableCellRenderer {

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component;
                component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(new Font("Consolas", 0, 20));
//                component.setForeground(Color.white);
//                component.setBackground(Color.white);
                if (isSelected) {
//                    component.setBackground(new Color(229, 148, 0));
                }
                return component;
            }
        }

        tblHistory.setDefaultRenderer(Object.class, new CustomizeTable());
        tblHistory.setTableHeader(null);
//        tblHistory.getTableHeader().setDefaultRenderer(new CustomizeTableHeader());
        tblHistory.setRowHeight(45);
        tblHistory.setShowGrid(false);
        tblHistory.setShowHorizontalLines(true);
        tblHistory.setOpaque(false);
        scrlHistory.getViewport().setBackground(Color.WHITE);
        scrlHistory.setBorder(null);

        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
        centerRenderer2.setHorizontalAlignment(JLabel.CENTER);
        centerRenderer2.setForeground(Color.red);
        tblHistory.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1);
        tblHistory.getColumnModel().getColumn(2).setCellRenderer(centerRenderer2);

        tblHistory.setFont(new Font("Consolas", 0, 22));

        tblHistory.getColumnModel().getColumn(0).setPreferredWidth(800);
        tblHistory.getColumnModel().getColumn(1).setPreferredWidth(20);
        tblHistory.getColumnModel().getColumn(2).setPreferredWidth(10);

        TableColumnModel tcm = tblHistory.getColumnModel();
        tcm.removeColumn(tcm.getColumn(3));
    }

    public void setTableData() {
        DefaultTableModel name = (DefaultTableModel) tblHistory.getModel();
        name.setRowCount(0);
        ArrayList<Object[]> trackers = TrackerController.getTrackers();
        for (Object[] tracker : trackers) {
            Object ob[] = {tracker[1], timer.convertTime(Integer.parseInt(tracker[2].toString())), "Delete", tracker[0]};
            name.addRow(ob);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTimer = new javax.swing.JPanel();
        lblTime = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();
        btnAddProject = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        scrlHistory = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        pnlTimer.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlTimer.setLayout(null);

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("00:00:00");
        pnlTimer.add(lblTime);
        lblTime.setBounds(1100, 20, 390, 50);

        btnStart.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        pnlTimer.add(btnStart);
        btnStart.setBounds(1600, 20, 280, 50);

        btnAddProject.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        btnAddProject.setText("Add Project");
        btnAddProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProjectActionPerformed(evt);
            }
        });
        pnlTimer.add(btnAddProject);
        btnAddProject.setBounds(70, 20, 650, 50);

        add(pnlTimer);
        pnlTimer.setBounds(10, 10, 1900, 90);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("History");
        add(jLabel1);
        jLabel1.setBounds(10, 114, 330, 60);

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Delete", "Title 4"
            }
        ));
        tblHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistoryMouseClicked(evt);
            }
        });
        scrlHistory.setViewportView(tblHistory);

        add(scrlHistory);
        scrlHistory.setBounds(10, 180, 1900, 650);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

        if (btnStart.getText().equals("Start")) {
            if (btnAddProject.getText().equals("Add Project")) {
                new SelectProject();
            } else {
                btnStart.setBackground(new Color(229, 126, 108));
                btnStart.setForeground(new Color(72, 14, 3));
                timer.startTimer();
                btnStart.setText("Stop");
            }
        } else if (btnStart.getText().equals("Stop")) {
            btnStart.setBackground(new Color(115, 185, 123));
            btnStart.setForeground(new Color(3, 72, 11));
            timer.stopTimer();
            btnStart.setText("Start");
            if (btnAddProject.getText().equals("Add Project")) {
                TrackerController.addTracker("New Project", timer.getTime());
            } else {
                if (id == 0) {
                    TrackerController.addTracker(btnAddProject.getText(), timer.getTime());
                } else {
                    TrackerController.updateTracker(timer.getTime(), id);
                }
            }
            timer.setTime(0);
            lblTime.setText(timer.convertTime(0));
            btnAddProject.setText("Add Project");
            setTableData();
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnAddProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProjectActionPerformed
        new SelectProject();
    }//GEN-LAST:event_btnAddProjectActionPerformed

    private void tblHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistoryMouseClicked
        if (tblHistory.getSelectedRow() > -1 && tblHistory.getSelectedColumn() == 2) {
            TrackerController.deleteTracker(Integer.parseInt(tblHistory.getModel().getValueAt(tblHistory.getSelectedRow(), 3).toString()));
            PnlTracker.getInstance().setTableData();
            PnlTracker.getInstance().setProjectIdToZero();
        }
    }//GEN-LAST:event_tblHistoryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProject;
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlTimer;
    private javax.swing.JScrollPane scrlHistory;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables
}
