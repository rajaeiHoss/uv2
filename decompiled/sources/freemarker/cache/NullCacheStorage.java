package freemarker.cache;

public class NullCacheStorage implements ConcurrentCacheStorage {
    public void clear() {
    }

    public Object get(Object obj) {
        return null;
    }

    public boolean isConcurrent() {
        return true;
    }

    public void put(Object obj, Object obj2) {
    }

    public void remove(Object obj) {
    }
}
