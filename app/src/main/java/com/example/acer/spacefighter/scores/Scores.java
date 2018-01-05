package com.example.acer.spacefighter.scores;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.acer.spacefighter.BittmapContext;

/**
 * Created by acer on 04/01/2018.
 */

public class Scores {
    private int score = 0;
    //the high Scores Holder
    int highScore[] = new int[4];

    private int heart = 3;
    private int posHeart = 0;
    //Shared Prefernces to store the High Scores
    SharedPreferences sharedPreferences;
    private Paint paint;
    public Scores(Context context, int screenX, int screenY) {
        //setting the score to 0 initially
        score = 0;
        posHeart = screenX - 250;
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);

        //initializing the array high scores with the previous values
        highScore[0] = sharedPreferences.getInt("score1",0);
        highScore[1] = sharedPreferences.getInt("score2",0);
        highScore[2] = sharedPreferences.getInt("score3",0);
        highScore[3] = sharedPreferences.getInt("score4",0);
    }

    public void draw(Canvas canvas) {
        //drawing the score on the game screen
        canvas.drawText("Score:"+score,100,50,paint);

        for (int i = 0; i < heart; i++){
            canvas.drawBitmap(BittmapContext.getInstance().getBitmap("heart_a"),
                    posHeart + 70*i,
                    30,
                    paint);
        }
    }

    public int decreaseHeart() {
        heart--;
        if (heart < 0)
            heart = 0;
        return heart;
    }

    public int increaseHeart(int _heart) {
        heart += _heart;
        return heart;
    }

    public int resetHeart() {
        heart = 3;
        return heart;
    }

    public void resetScrore() {
        score = 0;
    }

    public void increaseScore(int rate) {
        score += rate;
    }

    public void saveScore() {
        //Assigning the scores to the highscore integer array
        for(int i=0;i < 4;i++){
            if(highScore[i] < score){

                final int finalI = i;
                highScore[i] = score;
                break;
            }
        }

        //storing the scores through shared Preferences
        SharedPreferences.Editor e = sharedPreferences.edit();
        for(int i=0;i < 4;i++){
            int j = i+1;
            e.putInt("score"+j,highScore[i]);
        }
        e.apply();
    }
}
