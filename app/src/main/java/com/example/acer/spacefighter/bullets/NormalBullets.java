package com.example.acer.spacefighter.bullets;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by acer on 03/01/2018.
 */

public class NormalBullets {
    private int mTypeBullets = 0;
    private int mSpeed = 1;
    private Rect detectCollision;
    private Point mPos = new Point(0,0);
    private Bitmap bitmap;
    private Paint paint;

    public NormalBullets(int type, Bitmap bit) {
        mTypeBullets = type;
        paint = new Paint();
        mSpeed = 10;
        bitmap = bit;
        detectCollision = new Rect(mPos.x, mPos.y, 10, 10);
        if(type == 0) {
            paint.setStrokeWidth(10);
            paint.setColor(Color.YELLOW);

        }
    }

    public void update() {
        mPos.x += mSpeed;
        detectCollision.left = mPos.x;
        detectCollision.top = mPos.y;
        detectCollision.right = mPos.x + 10;
        detectCollision.bottom = mPos.y + 10;
        Log.d("normalBullets update:", String.valueOf(mPos.x));
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, mPos.x, mPos.y, paint);
        Log.d("normalBullets: draw", String.valueOf(mPos.x));
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Point getmPos() {
        return mPos;
    }

    public void setmPos(Point mPos) {
        this.mPos = mPos;
    }

    public boolean isExistInScreen(int screenX, int screeny) {
        return  (screenX > (mPos.x - 300));

    }

    public boolean intersects(Rect rect) {
        return Rect.intersects(rect, detectCollision);
    }
}
