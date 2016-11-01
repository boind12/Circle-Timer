package com.esser.circle_timer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class workout_quick extends AppCompatActivity {

    //Declare Variables
    private CountDownTimer Timer;
    private TextView tV_CountDown;
    private TextView tV_Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_quick);

        //Apply Variables
        tV_CountDown=(TextView)findViewById(R.id.tV_CountDown);

        //Get Data from MainActivity
        int rounds=1, workinterval=1, pauseinterval=1;
        Intent main_Intent = getIntent();
        Bundle main_Bundle = main_Intent.getExtras();
        rounds= main_Bundle.getInt("QUICK_ROUNDS");
        workinterval=main_Bundle.getInt("QUICK_WORKINTERVAL");
        pauseinterval=main_Bundle.getInt("QUICK_PAUSEINTERVAL");

        //Start Timer
        Timer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tV_CountDown.setText("" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                working_out();
            }
        }.start();
    }

    public void working_out()
    {
        //Apply Variables
        tV_Message=(TextView)findViewById(R.id.tV_Rounds);
        tV_Message.setText(getResources().getString(R.string.tV_Message_Go));
    }
}
