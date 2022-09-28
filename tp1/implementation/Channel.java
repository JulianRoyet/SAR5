package tp1.implementation;

public class Channel extends tp1.Channel{
    static final int CAPACITY = 15;
    private boolean connected = false;

    public Channel(){
        super();
        received = new CircularBuffer(CAPACITY);
    }
    
    @Override
    public synchronized int read(byte[] bytes, int offset, int length) throws InterruptedException {
        
        int read = 0;
        while(read != length && !(disconnected() && received.empty()) ){
            while(!received.empty() && read < length){
                bytes[offset + read] = received.pull();
                read++;
                notify();
            }
    
            if(read != length && received.empty() && !disconnected()){
                System.out.println("enter read wait");
                wait();
                System.out.println("leave read wait");
            }            

        }
        
        return read;
    }

    @Override
    public int write(byte[] bytes, int offset, int length) throws InterruptedException {
        synchronized(other){
            int written = 0;
            Channel oc = (Channel)other;
            while(written != length && !disconnected()){
                while(!oc._full() && written < length){
                    oc._push(bytes[offset + written]);
                    written++;
                    other.notify();
                }

                if(oc._full()){
                    System.out.println("enter write wait");
                    other.wait();
                    System.out.println("leave write wait");

                }
            }
            return written;
        }
    }

    public void _push(byte b){
        received.push(b);
    }

    public boolean _full(){
        return received.full();
    }
    
    public void _disconnect(){
        connected = false;
    }

    @Override
    public void disconnect() {

        synchronized(other){
            ((Channel)other)._disconnect();
            other.notify();
        };
        synchronized(this){
            _disconnect();
        }

    }

    @Override
    public synchronized boolean disconnected() {
        return connected == false;
    }

    protected synchronized void setPair(Channel pair){
        other = pair;
    }

    public static void pair(Channel a, Channel b){
        b.setPair(a);
        a.setPair(b);
        a.connected = true;
        b.connected = true;

    }
    
}
