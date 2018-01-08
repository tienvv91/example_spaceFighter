package fun.gameengine.GameController;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Pixmap;

/**
 * Created by acer on 07/01/2018.
 */

public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    public final float scaleX;
    public final float scaleY;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer, float scaleX, float scaleY) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }
    public Pixmap newPixmap(String fileName, Graphics.PixmapFormat format) {
        Bitmap.Config config = null;
        if (format == Graphics.PixmapFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == Graphics.PixmapFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        if (bitmap.getConfig() == Bitmap.Config.RGB_565)
            format = Graphics.PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Bitmap.Config.ARGB_4444)
            format = Graphics.PixmapFormat.ARGB4444;
        else
            format = Graphics.PixmapFormat.ARGB8888;

        int newWidth = (int)(bitmap.getWidth() * scaleX);
        int newHeight = (int)(bitmap.getHeight() * scaleY);
        Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        return new AndroidPixmap(resizeBitmap, format);
    }

    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));
    }

    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
    }

    @Override
    public void drawText(String text, float x, float y, Paint paint) {
//        this.paint.setColor(Color.RED);
//        this.paint.setTextSize(30);
//        this.paint.setStyle(Paint.Style.FILL);
//        canvas.drawText(text, x, y, this.paint);
    }


    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }

    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int width, int height) {
        dstRect.set(x, y, x + width,y + height);
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, null, dstRect, null);
    }

    public int getWidth() {
        return frameBuffer.getWidth();
    }
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    @Override
    public float getScaleX() {
        return this.scaleX;
    }

    @Override
    public float getScaleY() {
        return this.scaleY;
    }


}
