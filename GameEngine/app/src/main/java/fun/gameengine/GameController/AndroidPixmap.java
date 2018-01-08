package fun.gameengine.GameController;

import android.graphics.Bitmap;

import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Pixmap;

/**
 * Created by acer on 07/01/2018.
 */

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    Graphics.PixmapFormat format;
    public AndroidPixmap(Bitmap bitmap, Graphics.PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }
    public int getWidth() {
        return bitmap.getWidth();
    }
    public int getHeight() {
        return bitmap.getHeight();
    }
    public Graphics.PixmapFormat getFormat() {
        return format;
    }
    public void dispose() {
        bitmap.recycle();
    }
}
