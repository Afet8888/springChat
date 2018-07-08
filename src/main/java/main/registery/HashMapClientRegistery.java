package main.registery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HashMapClientRegistery<T> implements Clientregistery<T> {

    private Map<String,T> registery;
    public HashMapClientRegistery() {
        registery = new HashMap<>();
    }

    @Override
    public void addClient(String id, T t) {
        registery.put(id,t);

    }

    @Override
    public T getClient(String id) {
        return registery.get(id);
    }

    @Override
    public void remove(String id) {
        registery.remove(id);
    }

    @Override
    public Map<String, T> getAll() {
        return registery;
    }

    @Override
    public void setAll(Map<String, T> clients) {
         this.registery = clients;
    }

    @Override
    public void removeAll() {
        this.registery = new HashMap<>();

    }
}
