package tp1.implementation;

public class Broker extends tp1.Broker{
    private Context context;
    private String name;
    private Channel waitingChannel;
    private int waitingPort = 0;

    public Broker(Context ctx, String name) {
        super(name);
        this.name = name;   
        context = ctx; 
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized Channel accept(int port) throws InterruptedException {
        waitingChannel = new Channel();
        waitingPort = port;

        wait();

        return waitingChannel;
    }

    @Override
    public synchronized Channel connect(String name, int port) throws Exception {
        Channel connecting = new Channel();
        
        Broker other = ((Task)context.get(name)).getBroker();
        synchronized(other){
            Channel wc = other.waitingChannel;

            if(port != other.waitingPort){
                throw new Exception(name + " is not accepting on port " + port);
            }
            
            Channel.pair(connecting, wc);
            other.notify();
        }

        return connecting;
    
    }
    
}
