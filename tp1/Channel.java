package tp1;

public abstract class Channel {
    protected CircularBuffer received;
    protected Channel other;
    
    public Channel(){};

    public abstract int read(byte[] bytes, int offset, int length) throws InterruptedException;
    public abstract int write(byte[] bytes, int offset, int length) throws InterruptedException;
    public abstract void disconnect();
    public abstract boolean disconnected();
}
