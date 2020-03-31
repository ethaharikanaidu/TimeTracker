/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import controller.Timer;
import db_controller.MouseController;
import db_controller.TrackerController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import view.PnlReport;

public class BarChart_AWT {

    private ChartPanel chartPanel;
    private Timer timer = new Timer();
    private int count;

    public BarChart_AWT() {
//        setChart();
    }

    public void setChart() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Project",
                "Time",
                createDataset(),
                PlotOrientation.VERTICAL,
                false, true, false);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }

    private CategoryDataset createDataset() {
        final String fiat = "FIAT";
//        final String audi = "AUDI";
//        final String ford = "FORD";
//        final String speed = "Speed";
//        final String millage = "Millage";
//        final String userrating = "User Rating";
//        final String safety = "safety";
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, count);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        PnlReport.getInstance().setDate(dateFormat.format(cal.getTime()));
        ArrayList<Object[]> trackersViaDate = TrackerController.getTrackersViaDate(dateFormat.format(cal.getTime()));
        for (Object[] tracker : trackersViaDate) {
            dataset.addValue(Double.valueOf(tracker[2].toString()), fiat, tracker[1].toString());
        }

//        dataset.addValue(3.0, fiat, userrating);
//        dataset.addValue(4.0, fiat, millage);
//        dataset.addValue(4.0, fiat, safety);
//        dataset.addValue(5.0, audi, speed);
//        dataset.addValue(6.0, audi, userrating);
//        dataset.addValue(10.0, audi, millage);
//        dataset.addValue(4.0, audi, safety);
//
//        dataset.addValue(4.0, ford, speed);
//        dataset.addValue(2.0, ford, userrating);
//        dataset.addValue(3.0, ford, millage);
//        dataset.addValue(6.0, ford, safety);
        return dataset;
    }

    public void setTomorrowOrYesterday(int count) {
        this.count = count;
    }
    
      public void setChartToMouse() {
        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Hour",
                "Clicks",
                createDatasetToMouse(),
                PlotOrientation.VERTICAL,
                false, true, false);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
    }
      
        private CategoryDataset createDatasetToMouse() {
        final String fiat = "FIAT";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        ArrayList<Object[]> mouseClicks = MouseController.getMouseClicks();
        for (Object[] tracker : mouseClicks) {
            dataset.addValue(Double.valueOf(tracker[1].toString()), fiat, tracker[0].toString());
        }

        return dataset;
    }

//    public static void main(String[] args) {
//        BarChart_AWT chart = new BarChart_AWT("Car Usage Statistics",
//                "Which car do you like?");
////        chart.pack();
////        RefineryUtilities.centerFrameOnScreen(chart);
////        chart.setVisible(true);
//    }
}
