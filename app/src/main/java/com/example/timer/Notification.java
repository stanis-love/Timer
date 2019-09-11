package com.example.timer;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import static androidx.core.content.ContextCompat.getSystemService;

public class Notification {
    private final String CHANNEL_ID = "notification_channel";
    private final int NOTIFICATION_ID = 001;
    private Context context;
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat manager;

    public Notification(int smallIcon, String textTitle, String textContent, Context context){
        this.context = context;
        this.builder = new NotificationCompat.Builder(this.context, CHANNEL_ID)
                .setSmallIcon(smallIcon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        builder.setOngoing(true);
        createNotificationChannel();
        this.manager = NotificationManagerCompat.from(this.context);

    }

    public void display(){
        this.manager.notify(NOTIFICATION_ID, builder.build());
    }

    public void delete(){
        this.manager.cancel(NOTIFICATION_ID);
    }

    public void updateTextContent(String textContent){
        this.builder.setContentText(textContent);
        this.manager.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Channel";
            String description = "Notification connected to the timer";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(context, NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
