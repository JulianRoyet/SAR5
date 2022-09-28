package tp2.implementation.tasks;

import java.nio.charset.StandardCharsets;

import tp2.implementation.Context;
import tp2.MessageQueue;

public class Ping extends tp2.implementation.Task{

    public static final String msg = "ping";
    private int count = 0;

    public Ping(Context context, String name) throws Exception {
        super(context, name);
    }

    @Override
    public void body() {
        MessageQueue pong = null;
        try {
            
            try {
                System.out.println("Ping: connecting to pong...");
                pong = broker.connect("Pong", 123);
                
            } catch (Exception e) {
                System.out.println("Pong not found");
                e.printStackTrace();
                return;
            }
            
            System.out.println("Ping: connected to pong");
            
            while(!pong.closed() && count < 10){
                String toSend = msg + " " + count;
                System.out.println("Ping: writing " + count + "...");
                
                pong.send(toSend.getBytes(StandardCharsets.UTF_8), 0, toSend.length());
                count++;
            }
            pong.close();
            System.out.println("Ping: over");
    

        } catch (InterruptedException e) {
            System.out.println("Interruped");
            return;
        }
    }
    
}
