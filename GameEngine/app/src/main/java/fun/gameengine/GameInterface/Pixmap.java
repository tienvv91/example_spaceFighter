package fun.gameengine.GameInterface;

/**
 * Created by acer on 07/01/2018.
 */

public interface Pixmap {
    public int getWidth();
    public int getHeight();
    public Graphics.PixmapFormat getFormat();
    public void dispose();
}
