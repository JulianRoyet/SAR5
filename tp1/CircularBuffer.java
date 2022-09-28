package tp1;

public abstract class CircularBuffer {
    public CircularBuffer(int capacity){}
    public abstract boolean full();
    public abstract boolean empty();
    public abstract void push(byte data);
    public abstract byte pull();
}
