package fun.gameengine;

import android.app.Activity;
import android.os.Bundle;

import fun.gameengine.GameInterface.Audio;
import fun.gameengine.GameInterface.Screen;
import fun.gameengine.GameController.AndroidGame;
import fun.gameengine.GameView.LoadingScreen;

public class SpaceFighterGame extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

    @Override
    public Audio getAudio() {
        return null;
    }
}
