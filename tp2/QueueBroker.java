package tp2;

public abstract class QueueBroker{
    public QueueBroker(String name){};
    public abstract String getName();
    public abstract MessageQueue accept(int port) throws InterruptedException;
    public abstract MessageQueue connect(String name, int port) throws Exception;
}