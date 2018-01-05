package com.example.acer.spacefighter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acer.spacefighter.activitys.highscores;
import com.example.acer.spacefighter.scores.Scores;
import com.example.acer.spacefighter.soundController.Sounds;

import java.util.ArrayList;

/**
 * Created by acer on 27/12/2017.
 */

public class GameView extends SurfaceView implements Runnable{

    // boolean variable to track if the game is playing or not
    volatile boolean playing;

    //the game thread
    private Thread gameThread = null;

    private Player player;
    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private Context context;
    //Adding enemies object array
    private Enemy[] enemies;
    //Adding 3 enemies you may increase the size
    private int enemyCount = 5;
    private int timeDelayReadly = 0;
    private long currentTimeMs = System.currentTimeMillis();

    private ArrayList<Star> stars = new ArrayList<Star>();

    //defining a boom object to display blast
    private Boom boom, playerBoom;
    private Scores score;
    private boolean isGameOver = false;
    //class constructor
    public GameView(Context context, int screenX, int screenY) {
        super(context);
        this.context = context;
        player = new Player(context, screenX, screenY);
        surfaceHolder = getHolder();
        paint = new Paint();
        score = new Scores(context, screenX, screenY);
        int starNum = 100;
        for (int i = 0; i < starNum; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }
        //initializing enemy object array
        enemies = new Enemy[enemyCount];
        for(int i=0; i<enemyCount; i++){
            enemies[i] = new Enemy(context, screenX, screenY);
        }

        //initializing boom object
        boom = new Boom(context);
        playerBoom = new Boom(context);
        Sounds.getInstance().startOnGameSound();
        isGameOver = false;
    }

    @Override
    public void run() {
        while (playing) {
            update();

            draw();

            control();
        }
    }

    public void control() {
        try {
            gameThread.sleep(20);

        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void update() {
        if(isGameOver) {
            return;
        }
        if(player.getY() < 0) {
            player.setX(75);
        }

        Log.d("trace count update: " , String.valueOf(System.currentTimeMillis()));

        player.update();

        //setting boom outside the screen
        boom.setX(-1250);
        boom.setY(-1250);
        playerBoom.setX(-1200);
        playerBoom.setY(-1200);
        for (Star s : stars) {
            s.update(player.getSpeed());
        }

        //updating the enemy coordinate with respect to player speed
        for(int i=0; i<enemyCount; i++){
            enemies[i].update(player.getSpeed());

            //if collision occurrs with player
            if (player.intersectsWithBullets(enemies[i].getDetectCollision())) {
                Sounds.getInstance().startKilledSound();
                boom.setX(enemies[i].getX());
                boom.setY(enemies[i].getY());
                //moving enemy outside the left edge
                score.increaseScore(10);
                enemies[i].setX(-1200);
            }
            if(Rect.intersects(player.getDetectCollision(), enemies[i].getDetectCollision())) {
                playerBoom.setX((enemies[i].getX() + player.getX()) / 2);
                playerBoom.setY((enemies[i].getY() + player.getY()) / 2);
                enemies[i].setX(-1200);
                player.setX(-1200);
                if(score.decreaseHeart() == 0 ) {
                    score.saveScore();
                    isGameOver = true;
                    Sounds.getInstance().stopMusic();
                    Sounds.getInstance().startGameOverSound();
                }


            }
        }
    }

    public void draw() {

        //checking if surface is valid
        if(surfaceHolder.getSurface().isValid())  {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();
            // drawing a background color for canvas

            canvas.drawColor(Color.BLACK);
            paint.setColor(Color.WHITE);

            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }



            //drawing the enemies
            for (int i = 0; i < enemyCount; i++) {
                canvas.drawBitmap(
                        enemies[i].getBitmap(),
                        enemies[i].getX(),
                        enemies[i].getY(),
                        paint
                );

            }

            //drawing boom image
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );

            canvas.drawBitmap(playerBoom.getBitmap(),
                    playerBoom.getX(),
                    playerBoom.getY(),
                    paint);

            player.draw(canvas, paint);

//            Bitmap test = Bitmap.createBitmap(BittmapContext.getInstance().getBitmap("test_bullets"), 500, 500, 100, 100);
//
//            canvas.drawBitmap(test, player.getX() + 100 , player.getY() + 100, paint);

            score.draw(canvas);
            //draw game Over when the game is over
            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over",canvas.getWidth()/2,yPos,paint);
            }

//            if(timeDelayReadly < 3000)
//            {
//                reset();
//                timeDelayReadly += 20;
//                paint.setTextSize(150);
//                paint.setTextAlign(Paint.Align.CENTER);
//
//                int yPosdes=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
//                canvas.drawText(String.valueOf(timeDelayReadly / 1000),canvas.getWidth()/2,yPosdes,paint);
//            }

            //unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }


    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        playing = true;
        isGameOver = false;
        reset();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if(isGameOver){
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                context.startActivity(new Intent(context,MainActivity.class));
            }
        }
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_CANCEL:
                player.onTouchEvent(motionEvent);
                break;
        }

        return true;
    }

    private void reset() {
        score.resetHeart();
        score.resetScrore();
        //reset the enemies
        for (int i = 0; i < enemyCount; i++) {
            enemies[i].setX(-1200);
        }
    }
}


