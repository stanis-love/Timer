package com.example.timer;

import android.media.AudioManager;
import android.media.ToneGenerator;

public class Sound {
    public static void beep(){
        ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
    }
}
