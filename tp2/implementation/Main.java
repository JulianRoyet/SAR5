package tp2.implementation;

import tp2.implementation.tasks.Ping;
import tp2.implementation.tasks.Pong;

public class Main {
    public static void main(String[] args) {
        Context ctx = new Context();
        try {
            Pong pong = new Pong(ctx, "Pong");
            Ping ping = new Ping(ctx, "Ping");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
