package tp2.implementation;

public abstract class Task extends tp2.Task{

    public Task(Context context, String name) throws Exception {
        super(context, name);
        broker = new QueueBroker(context, name);
        context.addTask(this);
        start();
    }

    public QueueBroker getBroker() {
        return (QueueBroker)broker;
    }
    
    @Override
    public void run(){
        body();
    }

    public abstract void body();
}
