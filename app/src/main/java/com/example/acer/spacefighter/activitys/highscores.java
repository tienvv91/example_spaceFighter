package com.example.acer.spacefighter.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.acer.spacefighter.R;

public class highscores extends Activity {
    TextView textView,textView2,textView3,textView4;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);


        //initializing the textViews
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);

        sharedPreferences  = getSharedPreferences("SHAR_PREF_NAME", Context.MODE_PRIVATE);

        //setting the values to the textViews
        textView.setText("1."+sharedPreferences.getInt("score1",0));
        textView2.setText("2."+sharedPreferences.getInt("score2",0));
        textView3.setText("3."+sharedPreferences.getInt("score3",0));
        textView4.setText("4."+sharedPreferences.getInt("score4",0));
    }
}
