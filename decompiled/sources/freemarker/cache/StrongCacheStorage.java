package freemarker.cache;

import freemarker.core._ConcurrentMapFactory;
import java.util.Map;

public class StrongCacheStorage implements ConcurrentCacheStorage {
    private final Map map = _ConcurrentMapFactory.newMaybeConcurrentHashMap();

    public boolean isConcurrent() {
        return _ConcurrentMapFactory.isConcurrent(this.map);
    }

    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public void put(Object obj, Object obj2) {
        this.map.put(obj, obj2);
    }

    public void remove(Object obj) {
        this.map.remove(obj);
    }

    public void clear() {
        this.map.clear();
    }
}
