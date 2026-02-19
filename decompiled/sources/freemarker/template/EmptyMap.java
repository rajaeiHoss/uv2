package freemarker.template;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class EmptyMap implements Map, Cloneable {
    public static final EmptyMap instance = new EmptyMap();

    public void clear() {
    }

    public boolean containsKey(Object obj) {
        return false;
    }

    public boolean containsValue(Object obj) {
        return false;
    }

    public Object get(Object obj) {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public Object remove(Object obj) {
        return null;
    }

    public int size() {
        return 0;
    }

    public Set entrySet() {
        return Collections.EMPTY_SET;
    }

    public Set keySet() {
        return Collections.EMPTY_SET;
    }

    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("This Map is read-only.");
    }

    public void putAll(Map map) {
        if (map.entrySet().iterator().hasNext()) {
            throw new UnsupportedOperationException("This Map is read-only.");
        }
    }

    public Collection values() {
        return Collections.EMPTY_LIST;
    }
}
