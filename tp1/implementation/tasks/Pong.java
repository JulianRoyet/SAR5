package tp1.implementation.tasks;

import java.nio.charset.StandardCharsets;

import tp1.Channel;
import tp1.implementation.Context;

public class Pong extends tp1.implementation.Task{
    public static final String msg = "pong";
    private int count=0;
    public Pong(Context context, String name) throws Exception {
        super(context, name);
    }

    @Override
    public void body() {
        Channel ping = null;
        try {
            System.out.println("Pong: accepting on port 123");
            ping = broker.accept(123);
            System.out.println("Pong: ping connected");
            
            while(true){
                String toReceive = Ping.msg + " 0";
                
                int len = toReceive.length();
                byte[] received = new byte[len];

                System.out.println("Pong: reading " + count + "... ");
                
                
                if(ping.read(received, 0, len) == len){
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
