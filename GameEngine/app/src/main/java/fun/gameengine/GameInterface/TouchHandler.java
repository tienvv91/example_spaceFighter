package fun.gameengine.GameInterface;

import android.view.View;

import java.util.List;


/**
 * Created by acer on 07/01/2018.
 */

public interface TouchHandler extends View.OnTouchListener {
    public boolean isTouchDown(int pointer);
    public int getTouchX(int pointer);
    public int getTouchY(int pointer);
    public List<Input.TouchEvent> getTouchEvents();

}
