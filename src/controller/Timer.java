package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Timer {

    private Thread timer;
    private int time = 0;
    private String displayTime = "";
    private boolean running;

    public Timer() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }).start();
    }

    public String runTime() {
        if (time == 86400) {
            time = 0;
        }

        if (running) {
            time++;
        }

        return convertTime(time);

    }

    public void startTimer() {
        running = true;
    }

    public void stopTimer() {
        running = false;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public boolean isRunning() {
        return running;
    }

    public String convertTime(int time) {
        String displayTime = "";
        if (time / 3600 % 60 < 10) {
            displayTime += "0" + time / 3600 % 60;
        } else {
            displayTime += time / 3600 % 60;
        }

        if (time / 60 % 60 < 10) {
            displayTime += ":0" + time / 60 % 60;
        } else {
            displayTime += ":" + time / 60 % 60;
        }

        if (time % 60 < 10) {
            displayTime += ":0" + time % 60;
        } else {
            displayTime += ":" + time % 60;
        }

        return displayTime;
    }

//    public static void main(String[] args) {
//        new Timer().startTimer();
//      
//    }
}
