package com.example.acer.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.acer.spacefighter.bullets.NormalBullets;

import java.util.ArrayList;

/**
 * Created by acer on 27/12/2017.
 */



public class Player {
    private Bitmap bitmap, bitmapBullets;
    private int x;
    private int y;
    private int speed = 0;
    private float count = 0;

    private final int GRAVITY = -10;


    private int milisecond = 0;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect detectCollision;

    private Point posPlayer;
    private ArrayList<NormalBullets> Bullets = new ArrayList<>();
    private int maxScreenX;
    private int maxScreenY;

    private long currentTimeMs = System.currentTimeMillis();
    private int touchPosX;
    private int touchPosY;
    // Constructor
    public Player(Context context, int screenX, int screenY) {

        posPlayer = new Point(75,screenY / 2);
        speed = 10;
        touchPosX = posPlayer.x;
        touchPosY = posPlayer.y;

        // get bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.blueships1);
        bitmapBullets = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_yellow);


        maxScreenX = screenX;
        maxScreenY = screenY;
        //initializing rect object
        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

    }


    private void addBullets() {
        NormalBullets bullet = new NormalBullets(0, bitmapBullets);
//            bullet.setmPos(new Point((getX() + bitmap.getWidth()), (getY() + bitmap.getHeight()/2 )));
        bullet.setmPos(new Point((getX() + bitmap.getWidth()), (getY() + 19 )));
        Bullets.add(bullet);
    }

    public void update() {

        count++;
        Log.d("trace count: " , String.valueOf(System.currentTimeMillis()));
        milisecond++;
        if(milisecond >= 20){
            milisecond = 0;
            addBullets();
            Log.d("player update:", String.valueOf(Bullets.size()));
        }




        for (int i = 0; i < Bullets.size(); i++)
        {
            Bullets.get(i).update();
            if(!Bullets.get(i).isExistInScreen(maxScreenX, maxScreenY)) {
                Bullets.remove(i);
            }
        }

        if(posPlayer.x < touchPosX) {
            posPlayer.x += speed;
        }else if(posPlayer.x > touchPosX) {
            posPlayer.x -= speed;
        }


        if(posPlayer.y < touchPosY) {
            posPlayer.y += speed;
        }else if(posPlayer.y > touchPosY) {
            posPlayer.y -= speed;
        }

        //adding top, left, bottom and right to the rect object
        detectCollision.left = posPlayer.x;
        detectCollision.top = posPlayer.y + bitmap.getHeight() / 4;
        detectCollision.right = posPlayer.x + bitmap.getWidth();
        detectCollision.bottom = posPlayer.y + 3* (bitmap.getHeight() / 4 );
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(
                this.getBitmap(),
                this.getX(),
                this.getY(),
                paint
        );

        for (NormalBullets b: Bullets) {
            b.draw(canvas);
        }

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return posPlayer.x;
    }

    public void setX(int x) {
        posPlayer.x = x;
    }

    public int getY() {
        return posPlayer.y;
    }

    public int getSpeed() {
        return speed;
    }


    public void onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    speed = 10;
                    touchPosX = posPlayer.x;
                    touchPosY = posPlayer.y;
                    Log.d("ostopBoostingnf", "ACTION_UP");
                    break;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_DOWN:
                    speed += 2;
                    touchPosX = (int) motionEvent.getX();
                    touchPosY = (int) motionEvent.getY();
                    Log.d("ostopBoostingnf", "ACTION_DOWN");
                default:
                    break;
            }
//        }
    }

    public boolean intersectsWithBullets(Rect rect) {
        for (NormalBullets b: Bullets) {
            if(b.intersects(rect)) {
                return true;
            }
        }
        return false;
    }
}
