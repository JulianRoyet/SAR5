package tp1.implementation;

import java.util.HashMap;

public class Context extends tp1.Context{
    Context(){
        super();
        brokers = new HashMap<>();
    }
    @Override
    public synchronized void addTask(tp1.Task t) throws Exception {
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
