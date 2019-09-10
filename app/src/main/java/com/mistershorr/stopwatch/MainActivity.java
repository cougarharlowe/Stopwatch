package com.mistershorr.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometerTime;
    private Button buttonStartstop;
    private Button buttonReset;
    private long Base;
    private boolean isRunning = false;
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
                if(isRunning) {

                    chronometerTime.setBase(Base);
                    chronometerTime.start();
                    isRunning = false;

                } else {

                    chronometerTime.stop();
                    Base = chronometerTime.getBase();
                    isRunning = true;

                }



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
}
