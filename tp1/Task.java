package tp1;

public abstract class Task extends Thread{
    protected Context context;
    protected Broker broker;
    
    public Task(Context context, String name) throws Exception{} //broker = new broker(name)

}
