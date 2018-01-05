package com.example.acer.spacefighter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.acer.spacefighter.activitys.highscores;
import com.example.acer.spacefighter.soundController.Sounds;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //image button
    private ImageButton buttonPlay;
    //high score button
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BittmapContext.getInstance().setContext(this);
        BittmapContext.getInstance().createBitmap();
        Sounds.getInstance().setContext(this);
        // getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);
        // adding a click listener
        buttonPlay.setOnClickListener(this);
        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //starting game activity
        if (view == buttonPlay) {
            startActivity(new Intent(this, GameActivity.class));
        }

        if (view == buttonScore) {
            startActivity(new Intent(this, highscores.class));
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Sounds.getInstance().stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
