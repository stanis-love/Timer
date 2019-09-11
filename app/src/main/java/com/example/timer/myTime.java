package com.example.timer;

import androidx.annotation.NonNull;

public class myTime {
    protected int hours;
    protected int minutes;
    protected int seconds;

    @NonNull
    @Override
    public String toString() {
        String h = Integer.toString(hours);
        if(h.length() < 2)h = "0"+h;

        String m = Integer.toString(minutes);
        if(m.length() < 2)m = "0"+m;

        String s = Integer.toString(seconds);
        if(s.length() < 2)s = "0"+s;

        if(h.equals("00"))return m+":"+s;
        return h+":"+m+":"+s;
    }
}
