package com.example.timer;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends IntentService {
    private Notification notification;
    private String time;

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        notification = new Notification(R.drawable.notification, "Timer", myTimer.time.toString(), this);
        notification.display();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                time = myTimer.time.toString();
                notification.updateTextContent(time);
                if(time.equals("00:00")) {
                    notification.delete();
                    timer.cancel();
                    stopSelf();
                }

            }
        }, 1000, 1000);
    }

    public NotificationService(){
        super("my_intent_thread");
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        return super.onStartCommand(intent, flag, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void finish(){
        notification.delete();
        stopSelf();
    }
}
