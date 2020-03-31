/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Timer;
import db_controller.AppController;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PnlMore extends javax.swing.JPanel {

    private static PnlMore more;
    private Timer timer = new Timer();

    /**
     * Creates new form More
     */
    private PnlMore() {
        initComponents();
        setBounds(0, 0, 1920, 830);
        setVisible(true);

        Object[] dateAndTime = AppController.getDateAndTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (!dateAndTime[1].toString().equals(simpleDateFormat.format(new Date()))) {
            AppController.updateApp(0);
            timer.setTime(0);
        } else {
            timer.setTime(Integer.parseInt(dateAndTime[0].toString()));
        }

        chkBrowserUsage();
        usageTimeWithNotificationTray();
        slackMessagesCount();

    }

    private void chkBrowserUsage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String line;
                        int count = 0;
                        Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((line = input.readLine()) != null) {
                            if (line.contains("chrome.exe")) {
                                count++;
                            }
                        }

                        if (count > 0) {
                            timer.startTimer();
                        } else {
                            timer.stopTimer();
                        }

                        input.close();
                        Thread.sleep(1000);
                    } catch (IOException ex) {
                        Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    private void usageTimeWithNotificationTray() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm:ss");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                while (true) {
                    if (timer.isRunning()) {
                        lblTime.setText(timer.runTime());
                    }

                    lblDate.setText("(" + simpleDateFormat2.format(new Date()) + ")");

                    if (simpleDateFormat1.format(new Date()).equals("00:00:00")) {
                        AppController.updateApp(0);
                        timer.setTime(0);
                    } else {
                        AppController.updateApp(timer.getTime());
                    }

                    if (timer.getTime() == 3600) {
                        SystemTray tray = SystemTray.getSystemTray();

                        //If the icon is a file
                        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

                        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
                        //Let the system resize the image if needed
                        trayIcon.setImageAutoSize(true);
                        //Set tooltip text for the tray icon
                        trayIcon.setToolTip("System tray icon demo");
                        try {
                            tray.add(trayIcon);
                        } catch (AWTException ex) {
                            Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        trayIcon.displayMessage("You are using google chrome more than 1 hour", "", TrayIcon.MessageType.INFO);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    private void slackMessagesCount() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileReader inputStream = null;
                try {
                    Properties properties = new Properties();
                    File file = new File("settings/Slack.properties");
                    inputStream = new FileReader(file.getAbsolutePath());
                    properties.load(inputStream);
                    while (true) {
                        HttpURLConnection connection = null;
                        try {
                            //Create connection
                            URL url = new URL("https://slack.com/api/conversations.info?token=xoxp-977744374882-980445447185-985850828722-cd8d126029f0b5cde9150890e958a1b0&channel=" + properties.getProperty("id"));
                            connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setRequestProperty("Content-Type",
                                    "application/x-www-form-urlencoded");

                            connection.setUseCaches(false);
                            connection.setDoOutput(true);

                            //Send request
                            DataOutputStream wr = new DataOutputStream(
                                    connection.getOutputStream());

                            wr.close();

                            //Get Response
                            InputStream is = connection.getInputStream();
                            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                            String line;
                            while ((line = rd.readLine()) != null) {
                                response.append(line);
                                response.append('\r');
                            }
                            rd.close();

                            JSONParser jsonParser = new JSONParser();
                            JSONObject parse = (JSONObject) jsonParser.parse(response.toString());
                            JSONObject parse2 = (JSONObject) parse.get("channel");

                            lblSlack.setText(parse2.get("unread_count").toString());
//                        System.out.println(parse2.get("unread_count"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (connection != null) {
                                connection.disconnect();
                            }
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(PnlMore.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();
    }

    public static PnlMore getInstance() {
        if (more == null) {
            more = new PnlMore();
        }
        return more;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSlack = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblSlack.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblSlack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSlack.setText("0");
        add(lblSlack);
        lblSlack.setBounds(1080, 310, 690, 60);

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("00:00:00");
        add(lblTime);
        lblTime.setBounds(1080, 140, 690, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Google Chrome Usage");
        add(jLabel3);
        jLabel3.setBounds(210, 140, 360, 60);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setText("Number of Slack messages (Unread messages)");
        add(jLabel2);
        jLabel2.setBounds(210, 310, 800, 60);

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDate.setText("jLabel1");
        add(lblDate);
        lblDate.setBounds(580, 140, 250, 60);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblSlack;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
