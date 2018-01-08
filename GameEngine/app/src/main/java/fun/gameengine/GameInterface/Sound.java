package fun.gameengine.GameInterface;

/**
 * Created by acer on 07/01/2018.
 */

public interface Sound {
    public void play(float volume);
    public void stop();
    public void pause();
    public void setLooping(boolean looping);
    public void setVolume(float volume);
    public boolean isPlaying();
    public boolean isStopped();
    public boolean isLooping();
    public void dispose();
}
