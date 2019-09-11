package com.example.timer;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startUp();
    }


    public void startUp(){
        final Button fire = findViewById(R.id.fire);
        fire.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, NotificationService.class);
                startService(intent);

                final View.OnClickListener outer = this;

                final EditText hours = (EditText) findViewById(R.id.hours);
                int h = Integer.parseInt(hours.getText().toString());

                final EditText minutes = (EditText) findViewById(R.id.minutes);
                int m = Integer.parseInt(minutes.getText().toString());

                final EditText seconds = (EditText) findViewById(R.id.seconds);
                int s = Integer.parseInt(seconds.getText().toString());

                final myTimer timer = new myTimer(h, m, s);
                timer.startTimer();

                hours.setTag(hours.getKeyListener());
                minutes.setTag(minutes.getKeyListener());
                seconds.setTag(seconds.getKeyListener());

                convertToNotEditable(hours);
                convertToNotEditable(minutes);
                convertToNotEditable(seconds);


                final Timer tick = new Timer();
                tick.schedule(new TimerTask() {
                    myTime current;
                    @Override
                    public void run() {
                        current = timer.getTime();
                        hours.setText(String.valueOf(current.hours));
                        minutes.setText(String.valueOf(current.minutes));
                        seconds.setText(String.valueOf(current.seconds));
                        if(current.hours == 0 && current.minutes == 0 && current.seconds == 0) {
                            convertToEditable(hours);
                            convertToEditable(minutes);
                            convertToEditable(seconds);
                            tick.cancel();
                            Sound.beep();
                            fire.setText("Start");
                            fire.setOnClickListener(outer);
                        }
                    }
                }, 1000, 1000);

                fire.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View v){
                        tick.cancel();
                        timer.pause();
                        hours.setText("0");
                        minutes.setText("0");
                        seconds.setText("0");
                        fire.setOnClickListener(outer);
                        fire.setText("Start");
                        convertToEditable(hours);
                        convertToEditable(minutes);
                        convertToEditable(seconds);
                        return true;
                    }
                });

                fire.setText("Pause");
                fire.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final View.OnClickListener inner = this;
                        timer.pause();
                        fire.setText("Resume");
                        fire.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                timer.resume();
                                fire.setText("Pause");
                                fire.setOnClickListener(inner);
                            }
                        });
                    }
                });
            }
        });

    }

    public void convertToNotEditable(EditText text){
        text.setKeyListener(null);

    }
    private void convertToEditable(EditText text){
        text.setKeyListener((KeyListener) text.getTag());
    }
}
