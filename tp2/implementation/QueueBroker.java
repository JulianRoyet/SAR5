package tp2.implementation;

public class QueueBroker extends tp2.QueueBroker{
    private Context context;
    private String name;
    private MessageQueue waitingChannel;
    private int waitingPort = 0;

    public QueueBroker(Context ctx, String name) {
        super(name);
        this.name = name;   
        context = ctx; 
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized MessageQueue accept(int port) throws InterruptedException {
        waitingChannel = new MessageQueue();
        waitingPort = port;

        wait();

        return waitingChannel;
    }

    @Override
    public synchronized MessageQueue connect(String name, int port) throws Exception {
        MessageQueue connecting = new MessageQueue();
        
        QueueBroker other = ((Task)context.get(name)).getBroker();
        synchronized(other){
            MessageQueue wc = other.waitingChannel;

            if(port != other.waitingPort){
                throw new Exception(name + " is not accepting on port " + port);
            }
            
            MessageQueue.pair(connecting, wc);
            other.notify();
        }

        return connecting;
    
    }
    
}
