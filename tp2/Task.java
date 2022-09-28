package tp2;

public abstract class Task extends Thread{
    protected Context context;
    protected QueueBroker broker;
    
    public Task(Context context, String name) throws Exception{}

}
