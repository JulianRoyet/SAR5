package tp1;
import java.util.HashMap;

public abstract class Context {
    protected HashMap<String, Task> brokers;

    public Context(){};

    public abstract void addTask(Task t) throws Exception;
    public abstract Task get(String name);
}