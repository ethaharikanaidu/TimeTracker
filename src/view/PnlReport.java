/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import chart.BarChart_AWT;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;

public class PnlReport extends javax.swing.JPanel {

    private static PnlReport pnlReport;
    private BarChart_AWT chart;
    private int count;

    /**
     * Creates new form PnlReport
     */
    private PnlReport() {
        initComponents();

//        setSize(1700, 750);
        setBounds(0, 0, 1920, 830);
        setVisible(true);
        
            Component[] components = getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setForeground(new Color(249, 171, 102));
                button.setBackground(Color.WHITE);
                button.setContentAreaFilled(false);
                button.setOpaque(true);
            }
        }

    }

    public void createChart() {
        chart = new BarChart_AWT();
        chart.setTomorrowOrYesterday(count);
        chart.setChart();
        chart.getChartPanel().setBounds(0, 0, 1700, 700);
        jPanel1.removeAll();
        jPanel1.add(chart.getChartPanel());
        jPanel1.repaint();
    }

    public void setDate(String date) {
        lblDate.setText(date);
    }

    public static PnlReport getInstance() {
        if (pnlReport == null) {
            pnlReport = new PnlReport();
        }
        return pnlReport;
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
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);
        add(jPanel1);
        jPanel1.setBounds(90, 130, 1830, 720);

        btnPrev.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        add(btnPrev);
        btnPrev.setBounds(20, 20, 200, 100);

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        add(btnNext);
        btnNext.setBounds(1700, 20, 200, 100);

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDate.setText("jLabel1");
        add(lblDate);
        lblDate.setBounds(160, 30, 1610, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        count--;
        createChart();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        count++;
        createChart();
    }//GEN-LAST:event_btnNextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDate;
    // End of variables declaration//GEN-END:variables
}
