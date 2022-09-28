package tp2;

import java.util.LinkedList;

public abstract class MessageQueue {
    protected LinkedList<byte[]> queue;
    protected MessageQueue other;
    
    public MessageQueue(){};

    public abstract byte[] receive() throws InterruptedException;
    public abstract void send(byte[] bytes, int offset, int length) throws InterruptedException;
    public abstract void close();
    public abstract boolean closed();
}
