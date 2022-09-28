package tp1;

public abstract class Broker{
    public Broker(String name){};
    public abstract String getName();
    public abstract Channel accept(int port) throws InterruptedException;
    public abstract Channel connect(String name, int port) throws Exception;
}