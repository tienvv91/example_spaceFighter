package fun.gameengine.GameView;

import android.graphics.Color;

import java.util.List;

import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Input;
import fun.gameengine.GameInterface.Screen;

/**
 * Created by acer on 07/01/2018.
 */

public class HelpScreen extends Screen {
    public HelpScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x > 256 && event.y > 416 ) {
//                    game.setScreen(new HelpScreen2(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0,0,g.getWidth(), g.getHeight(), Color.YELLOW);
        g.drawText("Help Screen", 0,0, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
