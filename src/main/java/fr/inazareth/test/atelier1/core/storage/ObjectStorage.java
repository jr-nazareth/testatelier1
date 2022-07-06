package fr.inazareth.test.atelier1.core.storage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ObjectStorage extends ConcurrentHashMap<Class, CopyOnWriteArrayList> {

    public <T> CopyOnWriteArrayList<T> getOrCreate(Class<T> c) {
        if (!contains(c)) {
            CopyOnWriteArrayList<T> ls = new CopyOnWriteArrayList<>();
            put(c, ls);
        }
        return get(c);
    }
}
