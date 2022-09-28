package tp2.implementation;

import java.util.HashMap;

public class Context extends tp2.Context{
    Context(){
        super();
        brokers = new HashMap<>();
    }
    @Override
    public synchronized void addTask(tp2.Task t) throws Exception {
        String name = ((Task)t).getBroker().getName();
        if(brokers.containsKey(name)){
            throw new Exception("Task " + name + " already exists");
        }
        brokers.put(name, t);
    }

    @Override
    public synchronized Task get(String name) {
        return (Task)brokers.get(name);
    }
}
