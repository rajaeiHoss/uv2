package freemarker.cache;

public interface CacheStorage {
    void clear();

    Object get(Object obj);

    void put(Object obj, Object obj2);

    void remove(Object obj);
}
