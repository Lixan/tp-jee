package Logger;

public class Logger implements ILogger {

    private Level level = Level.INFO;

    public void log(String message) {
        System.out.println("["+ level +"] "+ message);
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
    }
}
