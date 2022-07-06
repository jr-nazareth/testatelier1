package fr.inazareth.test.atelier1.core;

import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public interface RestService<T> extends SimpleRestService<List<T>> {

    public T get(Integer p) throws Exception;

    public T post(T p) throws Exception;

    public T put(T p) throws Exception;

    public T delete(Integer id) throws Exception;

    public Integer parseId(String str) throws Exception;

    public Class<T> type();

}
