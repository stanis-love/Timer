package com.example.timer;

import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class myTimer {
    static myTime time = new myTime();
    protected Timer timer;
    protected Handler handler;

    public myTimer(int hours, int minutes, int seconds){
        minutes += seconds / 60;
        seconds = seconds % 60;

        hours += minutes / 60;
        minutes = minutes % 60;

        time.hours = hours;
        time.minutes = minutes;
        time.seconds = seconds;
    }

    public myTime getTime(){
        return time;
    }

    public void startTimer(){
        this.timer = new Timer();
        this.timer.schedule(new mainTask(), 1000, 1000);
    }

    private boolean ticked(){
        if(time.seconds > 0)time.seconds -= 1;
        if(time.seconds <= 0){
            if(time.minutes <= 0){
                if (time.hours <= 0) return false;
                else{
                    time.hours -= 1;
                    time.minutes = 59;
                    time.seconds = 59;
                    return true;
                }
            }
            else{
                time.minutes -= 1;
                time.seconds = 59;
                return true;
            }
        }
        return true;
    }

    public void pause(){
        time.hours = 0;
        time.minutes = 0;
        time.seconds = 0;
        this.timer.cancel();
    }

    public void resume(){
        this.timer = new Timer();
        this.timer.schedule(new mainTask(), 0, 1000);
    }

    public class mainTask extends TimerTask {
        @Override
        public void run() {
            if (!ticked()) timer.cancel();
        }
    }










}
