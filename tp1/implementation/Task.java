package tp1.implementation;

public abstract class Task extends tp1.Task{

    public Task(Context context, String name) throws Exception {
        super(context, name);
        broker = new Broker(context, name);
        context.addTask(this);
        start();
    }

    public Broker getBroker() {
        return (Broker)broker;
    }
    
    @Override
    public void run(){
        body();
    }

    public abstract void body();
}
