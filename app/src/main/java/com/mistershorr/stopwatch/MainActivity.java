package com.mistershorr.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyboardShortcutGroup;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometerTime;
    private Button buttonStartstop;
    private Button buttonReset;
    private long pausedTime = 0;
    private long base = 0;
    private boolean isRunning = false;
    public static final String KEY_SAVED_BASE = "chronometer base";
    public static final String KEY_SAVED_PAUSEDTIME = "saved passed time";
    public static final String KEY_SAVED_CURRENTTIME = "display time";
    public static final String KEY_SAVED_ISRUNNING = "boolean";
    public static final String TAG = MainActivity.class.getSimpleName();

    // Look up the Log class for Android.
    // list all the levels of logging and when they're used
    // order them from lowest priority to highest priority
    // verbose Log.v
    // ....

    // launched app--> onCreate, onStart, onResume
    // rotate --> onPause, onStop, onDestroy
    // hit the square button --> onPause, onStart
    // click back on the app from the square button --> onStart, onResume
    // hit the circle button --> onPause, onStop
    // relaunch the app from the phone navigation (not play button) onStart, onResume
    // hit the back button --> onPause, onStart, onDestroy


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        wireWidgets();
        setListeners();
        //chronometerTime();
        if( savedInstanceState != null) {
            chronometerTime.setBase(savedInstanceState.getLong(KEY_SAVED_BASE));
            isRunning = savedInstanceState.getBoolean(KEY_SAVED_ISRUNNING);
            pausedTime = savedInstanceState.getLong(KEY_SAVED_PAUSEDTIME);
            //chronometerTime.start();
        }

    }


    /*private void chronometerTime() {
        new Chronometer()
    }*/


    private void wireWidgets() {
    buttonStartstop = findViewById(R.id.button_main_start_stop);
    buttonReset = findViewById(R.id.button_main_reset);
    chronometerTime = findViewById(R.id.chronometer_main_time);
    }

    private void setListeners() {



        buttonStartstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isRunning) {
                    // base should be old base + any paused time
                    // paused time is the diff between when you stopped it and now
                    // current time SystemClock.elapsedRealtime()
                    pausedTime = pausedTime - SystemClock.elapsedRealtime();
                    if(base != 0) {
                        base = base - pausedTime;
                        chronometerTime.setBase(base);
                    } else {
                        chronometerTime.setBase(SystemClock.elapsedRealtime()); }
                    chronometerTime.start();
                    isRunning = true;
                    buttonStartstop.setText("Stop");

                } else {

                    chronometerTime.stop();
                    base = chronometerTime.getBase();
                    pausedTime = SystemClock.elapsedRealtime();
                    isRunning = false;
                    buttonStartstop.setText("Start");

                }



            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometerTime.setBase(SystemClock.elapsedRealtime());
                base = 0;
                isRunning = false;
                chronometerTime.stop();
                buttonStartstop.setText("Start");
            }
        });
    }

    //boolean





    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    // activity is now running

    // another activity is covering part of this activity
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    // this is activity is completely hidden
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    // when activity is finished
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_SAVED_BASE, chronometerTime.getBase());
        outState.putLong(KEY_SAVED_PAUSEDTIME, pausedTime);
        outState.putLong(KEY_SAVED_CURRENTTIME, SystemClock.elapsedRealtime());
        outState.putBoolean(KEY_SAVED_ISRUNNING, isRunning);
    }
}

