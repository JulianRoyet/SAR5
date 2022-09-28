package tp2.implementation.tasks;

import java.nio.charset.StandardCharsets;

import tp2.MessageQueue;
import tp2.implementation.Context;

public class Pong extends tp2.implementation.Task{
    public static final String msg = "pong";
    private int count=0;
    public Pong(Context context, String name) throws Exception {
        super(context, name);
    }

    @Override
    public void body() {
        MessageQueue ping = null;
        try {
            System.out.println("Pong: accepting on port 123");
            ping = broker.accept(123);
            System.out.println("Pong: ping connected");
            
            while(true){
                System.out.println("Pong: reading " + count + "... ");
                
                byte[] received = ping.receive();
                if(received != null){
                    String str = new String(received, StandardCharsets.UTF_8);
                    System.out.println("Pong: received " + str);
                    //ping.write(msg.getBytes(StandardCharsets.UTF_8), 0, msg.length());
                    count++;
                }
                else break;
            }

            System.out.println("Pong: ping disconnected");


        } catch (InterruptedException e) {
            System.out.println("Interruped");
            return;
        }
        
    }
    
}
