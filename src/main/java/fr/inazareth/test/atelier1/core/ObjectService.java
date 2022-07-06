package fr.inazareth.test.atelier1.core;

import fr.inazareth.test.atelier1.Application;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Jean-Raffi Nazareth
 * @param <T> Element de base
 */
public abstract class ObjectService<T> {

    protected Application application;
    protected CopyOnWriteArrayList<T> container;

    protected Class<T> type;
    
    public ObjectService(Application application, Class<T> type) {
        this.application = application;
        this.container = application.storage().get(type);
        this.type = type;
    }

    public Class<T> type(){
        return type;
    }

    protected CopyOnWriteArrayList<T> container() {
        return container;
    }

    public Application application() {
        return application;
    }
}
