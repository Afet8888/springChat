package main.registery;

import java.util.Map;

public interface Clientregistery <T> {
    void addClient(String id, T t);
    T getClient(String id);
    void remove (String id);
    Map<String, T> getAll();
    void setAll(Map<String, T> clients);
    void removeAll();
}
