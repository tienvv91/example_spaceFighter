package fun.gameengine.GameView;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Input;
import fun.gameengine.GameInterface.Screen;
import fun.gameengine.Pool.Assets;

/**
 * Created by acer on 07/01/2018.
 */

public class MainMenuScreen extends Screen {
    public final int BTN_PLAY_TOP_SCREEN;
    public final int BTN_SCORE_TOP_SCREEN;
    public final int BTN_OPTION_TOP_SCREEN;
    public final int BTN_EXIT_TOP_SCREEN;
    public final int BTN_LEFT_SCREEN;
    public final int BTN_TOP_BTN;
    public final int BTN_HEIGHT;
    public final int BTN_WITDH;


    public MainMenuScreen(Game game) {
        super(game);
        Log("Create ---------------------->");
        BTN_LEFT_SCREEN = (int)(460 * game.getGraphics().getScaleX());
        BTN_TOP_BTN = (int)(15 * game.getGraphics().getScaleY());
        BTN_PLAY_TOP_SCREEN = (int)(90 * game.getGraphics().getScaleY());
        BTN_HEIGHT = (int)(60 * game.getGraphics().getScaleY());
        BTN_WITDH = (int)(150 * game.getGraphics().getScaleX());
        BTN_SCORE_TOP_SCREEN = BTN_PLAY_TOP_SCREEN + BTN_HEIGHT + BTN_TOP_BTN;
        BTN_OPTION_TOP_SCREEN = BTN_SCORE_TOP_SCREEN + BTN_HEIGHT + BTN_TOP_BTN;
        BTN_EXIT_TOP_SCREEN = BTN_OPTION_TOP_SCREEN + BTN_HEIGHT +BTN_TOP_BTN;
    }

    public void Log(String str) {
        Log.d(this.getClass().getName(), str);
    }

    @Override
    public void update(float deltaTime) {
        Log("update ---------------------->");
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(inBounds(event, this.BTN_LEFT_SCREEN, this.BTN_PLAY_TOP_SCREEN, this.BTN_WITDH, this.BTN_HEIGHT)) {
                    game.setScreen(new GameScreen(game));
//                    Settings.soundEnabled = !Settings.soundEnabled;
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);

                }
                if(inBounds(event, this.BTN_LEFT_SCREEN, this.BTN_SCORE_TOP_SCREEN, this.BTN_WITDH, this.BTN_HEIGHT) ) {
                    game.setScreen(new HighscoreScreen(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
//                    return;
                }
                if(inBounds(event, this.BTN_LEFT_SCREEN, this.BTN_OPTION_TOP_SCREEN, this.BTN_WITDH, this.BTN_HEIGHT) ) {
                    game.setScreen(new OptionScreen(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
//                    return;
                }
                if(inBounds(event,this.BTN_LEFT_SCREEN, this.BTN_EXIT_TOP_SCREEN, this.BTN_WITDH, this.BTN_HEIGHT) ) {
                    game.setScreen(new HelpScreen(game));
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
//                    return;
                }
            }
        }
    }
    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }
    @Override
    public void present(float deltaTime) {
        Log("update ---------------------->");
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.menu_bg, 0, 0,g.getWidth(), g.getHeight());
        g.drawPixmap(Assets.menu_btn_play, this.BTN_LEFT_SCREEN, this.BTN_PLAY_TOP_SCREEN);
        g.drawPixmap(Assets.menu_btn_top_score, this.BTN_LEFT_SCREEN, this.BTN_SCORE_TOP_SCREEN);
        g.drawPixmap(Assets.menu_btn_option, this.BTN_LEFT_SCREEN, this.BTN_OPTION_TOP_SCREEN);
        g.drawPixmap(Assets.menu_btn_exit, this.BTN_LEFT_SCREEN, this.BTN_EXIT_TOP_SCREEN);
//        if(Settings.soundEnabled)
//            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
//        else
//            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
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
