package com.esser.circle_timer;

import android.content.Intent;
import android.media.MediaPlayer;
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
    private TextView tV_Rounds;
    private MediaPlayer beeper;
    private int rounds=1, workinterval=1, pauseinterval=1;
    private boolean pause=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_quick);

        //Apply Variables
        beeper = MediaPlayer.create(this, R.raw.beep);
        tV_CountDown=(TextView)findViewById(R.id.tV_CountDown);
        tV_Rounds=(TextView)findViewById(R.id.tV_Rounds);

        //Get Data from MainActivity
        Intent main_Intent = getIntent();
        Bundle main_Bundle = main_Intent.getExtras();
        rounds= main_Bundle.getInt("QUICK_ROUNDS");
        workinterval=main_Bundle.getInt("QUICK_WORKINTERVAL");
        pauseinterval=main_Bundle.getInt("QUICK_PAUSEINTERVAL");

        //Set Round Time
        tV_Rounds.setText(String.valueOf(rounds));

        //Start Timer
        Timer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tV_CountDown.setText("" + ((millisUntilFinished / 1000)));
                if(millisUntilFinished<4000)
                {
                    beeper.start();
                }
            }

            @Override
            public void onFinish()
            {
                tV_CountDown.setText("0");
                working_out();
            }
        }.start();
    }

    public void working_out()
    {
        //Apply Variables
        tV_Message=(TextView)findViewById(R.id.tV_Message);
        tV_Message.setText(getResources().getString(R.string.tV_Message_Go));
        Timer=new CountDownTimer((workinterval*1000)+1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tV_CountDown.setText("" + millisUntilFinished / 1000);
                if(millisUntilFinished<4000)
                {
                    beeper.start();
                }
            }

            @Override
            public void onFinish() {
                if(rounds>1)
                {
                    tV_Message.setText(getResources().getString(R.string.tV_Message_Rest));
                    tV_CountDown.setText(""+pauseinterval);
                    timer(pauseinterval*1000);
                }
                else
                {
                    tV_Message.setText(getResources().getString(R.string.tV_Message_Finish));
                    tV_Rounds.setText("0");
                    tV_CountDown.setText("0");
                }
            }
        }.start();
    }

    public void timer(int time)
    {
        Timer=new CountDownTimer(time+1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tV_CountDown.setText("" + millisUntilFinished / 1000);
                if(millisUntilFinished<4000)
                {
                    beeper.start();
                }
            }

            @Override
            public void onFinish() {
                if(rounds>1)
                {
                    if(pause==false)
                    {
                        pause=true;
                        tV_Message.setText(getResources().getString(R.string.tV_Message_Rest));
                        tV_CountDown.setText(""+pauseinterval);
                        timer(pauseinterval*1000);
                    }
                    else
                    {
                        pause=false;
                        rounds-=1;
                        tV_Rounds.setText(String.valueOf(rounds));
                        tV_Message.setText(getResources().getString(R.string.tV_Message_Go));
                        tV_CountDown.setText(""+workinterval);
                        timer(workinterval*1000);
                    }
                }
                else
                {
                    tV_Message.setText(getResources().getString(R.string.tV_Message_Finish));
                    tV_Rounds.setText("0");
                    tV_CountDown.setText("0");
                }
            }
        }.start();
    }
}
