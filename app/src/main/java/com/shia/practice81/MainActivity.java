package com.shia.practice81;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button simpleVibrate, patternVibrate;
    Vibrator vibrator;
    long[] vibratePattern = {0, 200, 400, 200, 300, 500, 1000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleVibrate = findViewById(R.id.simpleVibrate);
        patternVibrate = findViewById(R.id.patternVibrate);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        simpleVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    vibrator.vibrate(300);
                }
            }
        });

        patternVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 29) {
                    vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK));
                } else if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createWaveform(vibratePattern, -1));
                } else {
                    vibrator.vibrate(vibratePattern, -1);
                }
            }
        });
    }
}