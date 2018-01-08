package fun.gameengine.GameInterface;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by acer on 07/01/2018.
 */

public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;
    public InputStream readFile(String fileName) throws IOException;
    public OutputStream writeFile(String fileName) throws IOException;
}

