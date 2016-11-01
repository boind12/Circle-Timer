package com.esser.circle_timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class workout_quick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_quick);

        //Get Data from MainActivity
        int rounds=1, workinterval=1, pauseinterval=1;
        Intent main_Intent = new Intent();
        Bundle main_Bundle = main_Intent.getExtras();
        rounds= main_Bundle.getInt("QUICK_ROUNDS");
        workinterval=main_Bundle.getInt("QUICK_WORKINTERVAL");
        pauseinterval=main_Bundle.getInt("QUICK_PAUSEINTERVAL");
    }
}
