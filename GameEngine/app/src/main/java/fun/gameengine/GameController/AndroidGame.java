package fun.gameengine.GameController;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Window;
import android.view.WindowManager;

import fun.gameengine.GameInterface.FileIO;
import fun.gameengine.GameInterface.Game;
import fun.gameengine.GameInterface.Graphics;
import fun.gameengine.GameInterface.Input;
import fun.gameengine.GameInterface.Screen;
import fun.gameengine.GameView.AndroidFastRenderView;
import fun.gameengine.GameView.MainMenuScreen;

/**
 * Created by acer on 07/01/2018.
 */

public abstract class AndroidGame extends Activity implements Game {
    AndroidFastRenderView renderView;
    Graphics graphics;
//    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
//    WakeLock wakeLock;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        boolean isLandscape = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 800 : 480;
        int frameBufferHeight = isLandscape ? 480 : 800;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);
        float scaleX = (float) frameBufferWidth
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
                / getWindowManager().getDefaultDisplay().getHeight();
        renderView = new AndroidFastRenderView(this, frameBuffer);
        graphics = new AndroidGraphics(getAssets(), frameBuffer, scaleX, scaleY);
        fileIO = new AndroidFileIO(this);
//        audio = new AndroidAudio(this);
        input = (Input) new AndroidInput(this, renderView, scaleX, scaleY);
        screen = getStartScreen();
        setContentView(renderView);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
    }
    @Override
    public void onResume() {
        super.onResume();
//        wakeLock.acquire();
        screen.resume();
        renderView.resume();
    }
    @Override
    public void onPause() {
        super.onPause();
//        wakeLock.release();
        renderView.pause();
        screen.pause();
        if (isFinishing())
            screen.dispose();
    }
    public Input getInput() {
        return input;
    }
    public FileIO getFileIO() {
        return fileIO;
    }
    public Graphics getGraphics() {
        return graphics;
    }
//    public Audio getAudio() {
//        return audio;
//    }

    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }
    public Screen getCurrentScreen() {
        return screen;
    }

    @Override
    public void onBackPressed()
    {
        setScreen(new MainMenuScreen(this));
        // code here to show dialog
//        super.onBackPressed();  // optional depending on your needs
    }
}
