package tp2.implementation;

import java.util.Arrays;
import java.util.LinkedList;

public class MessageQueue extends tp2.MessageQueue{
    private boolean open = false;

    public MessageQueue(){
        super();
        queue = new LinkedList<>();
    }
    
    @Override
    public synchronized byte[] receive() throws InterruptedException {
        
        while(queue.isEmpty() && !closed()){
            wait();
        }
        if(closed() && queue.isEmpty())
            return null;
        
        return queue.removeFirst();
    }

    @Override
    public void send(byte[] bytes, int offset, int length) throws InterruptedException {
        synchronized(other){
            MessageQueue oc = (MessageQueue)other;
            if(!other.closed())
                oc.queue.add(Arrays.copyOfRange(bytes, offset, offset + length - 1));
                other.notify();
        }
    }

    public void _close(){
        open = false;
    }

    @Override
    public void close() {

        synchronized(other){
            ((MessageQueue)other)._close();
            other.notify();
        };
        synchronized(this){
            _close();
        }

    }

    @Override
    public synchronized boolean closed() {
        return open == false;
    }

    protected synchronized void setPair(MessageQueue pair){
        other = pair;
    }

    public static void pair(MessageQueue a, MessageQueue b){
        b.setPair(a);
        a.setPair(b);
        a.open = true;
        b.open = true;

    }
    
}
