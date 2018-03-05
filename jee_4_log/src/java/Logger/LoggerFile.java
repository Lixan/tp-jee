package Logger;

import java.io.*;

public class LoggerFile implements ILogger {

    private Level level;

    @Override
    public void log(String message) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("log.txt", true), "utf-8"));
            writer.write("["+ level +"] "+message);
        } catch (IOException ex) {
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }
}
