package fun.gameengine.GameView;

import android.graphics.Color;

import java.util.List;

import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Input;
import fun.gameengine.GameInterface.Screen;
import fun.gameengine.Pool.Assets;

/**
 * Created by acer on 07/01/2018.
 */

public class HighscoreScreen extends Screen {
    String lines[] = new String[5];
    public HighscoreScreen(Game game) {
        super(game);
        for (int i = 0; i < 5; i++) {
//            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 416) {
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.score_bg, g.getWidth(), g.getHeight());
//        g.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);
        int y = 100;
        for (int i = 0; i < 5; i++) {
//            drawText(g, lines[i], 20, y);
            y += 50;
        }
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
            if (character == ' ') {
                x += 20;
                continue;
            }
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
//            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
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
