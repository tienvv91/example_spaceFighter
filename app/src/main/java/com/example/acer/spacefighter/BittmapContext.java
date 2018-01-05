package com.example.acer.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

/**
 * Created by acer on 04/01/2018.
 */

public class BittmapContext {
    private static BittmapContext instance = null;
    private Context context = null;
    private HashMap<String, Bitmap> bitmaps = new HashMap<>();

    public static BittmapContext getInstance() {
        if(null == instance)
            instance = new BittmapContext();
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void createBitmap() {
        if (context != null) {
            bitmaps.put("player", BitmapFactory.decodeResource(context.getResources(), R.drawable.player));
//            bitmaps.put("test_bullets", BitmapFactory.decodeResource(context.getResources(), R.drawable.test_bullets));
            bitmaps.put("heart_a", BitmapFactory.decodeResource(context.getResources(), R.drawable.heart_a));
            bitmaps.put("blueships1", BitmapFactory.decodeResource(context.getResources(), R.drawable.blueships1));
        }
    }

    public Bitmap getBitmap(String bitmap){
        Bitmap _bitmap = bitmaps.get(bitmap);//BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        if (_bitmap != null)
            return _bitmap;
        else
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

    }
}
