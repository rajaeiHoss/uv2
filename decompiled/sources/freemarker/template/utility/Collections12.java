package freemarker.template.utility;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collections12 {
    public static final Map EMPTY_MAP = new EmptyMap();

    private Collections12() {
    }

    private static final class EmptyMap extends AbstractMap implements Serializable {
        public boolean containsKey(Object obj) {
            return false;
        }

        public boolean containsValue(Object obj) {
            return false;
        }

        public Object get(Object obj) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public boolean isEmpty() {
            return true;
        }

        public int size() {
            return 0;
        }

        private EmptyMap() {
        }

        public Set keySet() {
            return Collections.EMPTY_SET;
        }

        public Collection values() {
            return Collections.EMPTY_SET;
        }

        public Set entrySet() {
            return Collections.EMPTY_SET;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Map) && ((Map) obj).size() == 0;
        }
    }

    public static Map singletonMap(Object obj, Object obj2) {
        return new SingletonMap(obj, obj2);
    }

    private static class SingletonMap extends AbstractMap implements Serializable {
        private transient Set entrySet = null;
        private final Object k;
        private transient Set keySet = null;
        private final Object v;
        private transient Collection values = null;

        public boolean isEmpty() {
            return false;
        }

        public int size() {
            return 1;
        }

        SingletonMap(Object obj, Object obj2) {
            this.k = obj;
            this.v = obj2;
        }

        public boolean containsKey(Object obj) {
            return Collections12.eq(obj, this.k);
        }

        public boolean containsValue(Object obj) {
            return Collections12.eq(obj, this.v);
        }

        public Object get(Object obj) {
            if (Collections12.eq(obj, this.k)) {
                return this.v;
            }
            return null;
        }

        public Set keySet() {
            if (this.keySet == null) {
                this.keySet = Collections.singleton(this.k);
            }
            return this.keySet;
        }

        public Set entrySet() {
            if (this.entrySet == null) {
                this.entrySet = Collections.singleton(new ImmutableEntry(this.k, this.v));
            }
            return this.entrySet;
        }

        public Collection values() {
            if (this.values == null) {
                this.values = Collections.singleton(this.v);
            }
            return this.values;
        }

        private static class ImmutableEntry implements Map.Entry {
            final Object k;
            final Object v;

            ImmutableEntry(Object obj, Object obj2) {
                this.k = obj;
                this.v = obj2;
            }

            public Object getKey() {
                return this.k;
            }

            public Object getValue() {
                return this.v;
            }

            public Object setValue(Object obj) {
                throw new UnsupportedOperationException();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!Collections12.eq(entry.getKey(), this.k) || !Collections12.eq(entry.getValue(), this.v)) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                Object obj = this.k;
                int i = 0;
                int hashCode = obj == null ? 0 : obj.hashCode();
                Object obj2 = this.v;
                if (obj2 != null) {
                    i = obj2.hashCode();
                }
                return hashCode ^ i;
            }

            public String toString() {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(this.k);
                stringBuffer.append("=");
                stringBuffer.append(this.v);
                return stringBuffer.toString();
            }
        }
    }

    public static List singletonList(Object obj) {
        return new SingletonList(obj);
    }

    private static class SingletonList extends AbstractList implements Serializable {
        private final Object element;

        public int size() {
            return 1;
        }

        SingletonList(Object obj) {
            this.element = obj;
        }

        public boolean contains(Object obj) {
            return Collections12.eq(obj, this.element);
        }

        public Object get(int i) {
            if (i == 0) {
                return this.element;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Index: ");
            stringBuffer.append(i);
            stringBuffer.append(", Size: 1");
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        }
    }

    /* access modifiers changed from: private */
    public static boolean eq(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
