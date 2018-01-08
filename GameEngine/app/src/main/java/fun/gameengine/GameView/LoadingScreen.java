package fun.gameengine.GameView;

import android.graphics.Color;
import android.util.Log;

import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Screen;
import fun.gameengine.Pool.Assets;
import fun.gameengine.Pool.Settings;

/**
 * Created by acer on 07/01/2018.
 */

public class LoadingScreen extends Screen {

    private boolean isLoaded = false;
    public LoadingScreen(Game game) {
        super(game);
    }
    public void update(float deltaTime) {

        Graphics g = game.getGraphics();
        Assets.menu_bg = g.newPixmap("bg.png", Graphics.PixmapFormat.RGB565);
        Assets.menu_btn_play = g.newPixmap("play.png", Graphics.PixmapFormat.ARGB8888);
        Assets.menu_btn_option = g.newPixmap("option.png", Graphics.PixmapFormat.ARGB8888);
        Assets.menu_btn_exit = g.newPixmap("exit.png", Graphics.PixmapFormat.ARGB8888);
        Assets.menu_btn_top_score = g.newPixmap("top_scrore.png", Graphics.PixmapFormat.ARGB8888);
        Assets.score_bg = g.newPixmap("score_bg.png", Graphics.PixmapFormat.RGB565);
        Settings.load(game.getFileIO());
        if(!isLoaded) {
            Log("start -------------------->");
            isLoaded = true;
        }
        game.setScreen(new MainMenuScreen(game));

    }

    public void present(float deltaTime) {
        Log("Present() !!! ");
        Graphics g = game.getGraphics();
        g.drawRect(0,0,g.getWidth(), g.getHeight(), Color.GREEN);
    }
    public void pause() {
    }
    public void resume() {
        Graphics g = game.getGraphics();
    }
    public void dispose() {
    }

    public void Log(String str) {
        Log.d(this.getClass().getName(), str);
    }

}
