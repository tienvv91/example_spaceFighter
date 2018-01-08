package fun.gameengine.GameView;

import android.graphics.Color;

import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Screen;

/**
 * Created by acer on 08/01/2018.
 */

public class OptionScreen extends Screen {

    public OptionScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawRect(0,0,g.getWidth(), g.getHeight(), Color.RED);
        g.drawText("Option Screen", 0,0, null);
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
