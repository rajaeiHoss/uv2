package freemarker.ext.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IdentityHashMap extends AbstractMap implements Map, Cloneable, Serializable {
    private static final int ENTRIES = 2;
    private static final int KEYS = 0;
    private static final int VALUES = 1;
    private static EmptyHashIterator emptyHashIterator = new EmptyHashIterator();
    public static final long serialVersionUID = 362498820763181265L;
    /* access modifiers changed from: private */
    public transient int count;
    private transient Set entrySet;
    private transient Set keySet;
    private float loadFactor;
    /* access modifiers changed from: private */
    public transient int modCount;
    /* access modifiers changed from: private */
    public transient Entry[] table;
    private int threshold;
    private transient Collection values;

    static /* synthetic */ int access$110(IdentityHashMap identityHashMap) {
        int i = identityHashMap.count;
        identityHashMap.count = i - 1;
        return i;
    }

    static /* synthetic */ int access$308(IdentityHashMap identityHashMap) {
        int i = identityHashMap.modCount;
        identityHashMap.modCount = i + 1;
        return i;
    }

    public IdentityHashMap(int i, float f) {
        this.modCount = 0;
        this.keySet = null;
        this.entrySet = null;
        this.values = null;
        if (i < 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Illegal Initial Capacity: ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        } else if (f <= 0.0f || Float.isNaN(f)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Illegal Load factor: ");
            stringBuffer2.append(f);
            throw new IllegalArgumentException(stringBuffer2.toString());
        } else {
            i = i == 0 ? 1 : i;
            this.loadFactor = f;
            this.table = new Entry[i];
            this.threshold = (int) (((float) i) * f);
        }
    }

    public IdentityHashMap(int i) {
        this(i, 0.75f);
    }

    public IdentityHashMap() {
        this(11, 0.75f);
    }

    public IdentityHashMap(Map map) {
        this(Math.max(map.size() * 2, 11), 0.75f);
        putAll(map);
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean containsValue(Object obj) {
        Entry[] entryArr = this.table;
        if (obj == null) {
            int length = entryArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    return false;
                }
                for (Entry entry = entryArr[i]; entry != null; entry = entry.next) {
                    if (entry.value == null) {
                        return true;
                    }
                }
                length = i;
            }
        } else {
            int length2 = entryArr.length;
            while (true) {
                int i2 = length2 - 1;
                if (length2 <= 0) {
                    return false;
                }
                for (Entry entry2 = entryArr[i2]; entry2 != null; entry2 = entry2.next) {
                    if (obj.equals(entry2.value)) {
                        return true;
                    }
                }
                length2 = i2;
            }
        }
    }

    public boolean containsKey(Object obj) {
        Entry[] entryArr = this.table;
        if (obj != null) {
            int identityHashCode = System.identityHashCode(obj);
            for (Entry entry = entryArr[(Integer.MAX_VALUE & identityHashCode) % entryArr.length]; entry != null; entry = entry.next) {
                if (entry.hash == identityHashCode && obj == entry.key) {
                    return true;
                }
            }
        } else {
            for (Entry entry2 = entryArr[0]; entry2 != null; entry2 = entry2.next) {
                if (entry2.key == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object get(Object obj) {
        Entry[] entryArr = this.table;
        if (obj != null) {
            int identityHashCode = System.identityHashCode(obj);
            for (Entry entry = entryArr[(Integer.MAX_VALUE & identityHashCode) % entryArr.length]; entry != null; entry = entry.next) {
                if (entry.hash == identityHashCode && obj == entry.key) {
                    return entry.value;
                }
            }
            return null;
        }
        for (Entry entry2 = entryArr[0]; entry2 != null; entry2 = entry2.next) {
            if (entry2.key == null) {
                return entry2.value;
            }
        }
        return null;
    }

    private void rehash() {
        Entry[] entryArr = this.table;
        int length = entryArr.length;
        int i = (length * 2) + 1;
        Entry[] entryArr2 = new Entry[i];
        this.modCount++;
        this.threshold = (int) (((float) i) * this.loadFactor);
        this.table = entryArr2;
        while (true) {
            int i2 = length - 1;
            if (length > 0) {
                Entry entry = entryArr[i2];
                while (entry != null) {
                    Entry entry2 = entry.next;
                    int i3 = (entry.hash & Integer.MAX_VALUE) % i;
                    entry.next = entryArr2[i3];
                    entryArr2[i3] = entry;
                    entry = entry2;
                }
                length = i2;
            } else {
                return;
            }
        }
    }

    public Object put(Object obj, Object obj2) {
        int i;
        Entry[] entryArr = this.table;
        int i2 = 0;
        if (obj != null) {
            i2 = System.identityHashCode(obj);
            i = (i2 & Integer.MAX_VALUE) % entryArr.length;
            for (Entry entry = entryArr[i]; entry != null; entry = entry.next) {
                if (entry.hash == i2 && obj == entry.key) {
                    Object obj3 = entry.value;
                    entry.value = obj2;
                    return obj3;
                }
            }
        } else {
            for (Entry entry2 = entryArr[0]; entry2 != null; entry2 = entry2.next) {
                if (entry2.key == null) {
                    Object obj4 = entry2.value;
                    entry2.value = obj2;
                    return obj4;
                }
            }
            i = 0;
        }
        this.modCount++;
        if (this.count >= this.threshold) {
            rehash();
            entryArr = this.table;
            i = (Integer.MAX_VALUE & i2) % entryArr.length;
        }
        entryArr[i] = new Entry(i2, obj, obj2, entryArr[i]);
        this.count++;
        return null;
    }

    public Object remove(Object obj) {
        Entry[] entryArr = this.table;
        if (obj != null) {
            int identityHashCode = System.identityHashCode(obj);
            int length = (Integer.MAX_VALUE & identityHashCode) % entryArr.length;
            Entry entry = null;
            for (Entry entry2 = entryArr[length]; entry2 != null; entry2 = entry2.next) {
                if (entry2.hash == identityHashCode && obj == entry2.key) {
                    this.modCount++;
                    if (entry != null) {
                        entry.next = entry2.next;
                    } else {
                        entryArr[length] = entry2.next;
                    }
                    this.count--;
                    Object obj2 = entry2.value;
                    entry2.value = null;
                    return obj2;
                }
                entry = entry2;
            }
        } else {
            Entry entry3 = null;
            for (Entry entry4 = entryArr[0]; entry4 != null; entry4 = entry4.next) {
                if (entry4.key == null) {
                    this.modCount++;
                    if (entry3 != null) {
                        entry3.next = entry4.next;
                    } else {
                        entryArr[0] = entry4.next;
                    }
                    this.count--;
                    Object obj3 = entry4.value;
                    entry4.value = null;
                    return obj3;
                }
                entry3 = entry4;
            }
        }
        return null;
    }

    public void putAll(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        Entry[] entryArr = this.table;
        this.modCount++;
        int length = entryArr.length;
        while (true) {
            length--;
            if (length >= 0) {
                entryArr[length] = null;
            } else {
                this.count = 0;
                return;
            }
        }
    }

    public Object clone() {
        try {
            IdentityHashMap identityHashMap = (IdentityHashMap) super.clone();
            identityHashMap.table = new Entry[this.table.length];
            int length = this.table.length;
            while (true) {
                int i = length - 1;
                Entry entry = null;
                if (length > 0) {
                    Entry[] entryArr = identityHashMap.table;
                    Entry[] entryArr2 = this.table;
                    if (entryArr2[i] != null) {
                        entry = (Entry) entryArr2[i].clone();
                    }
                    entryArr[i] = entry;
                    length = i;
                } else {
                    identityHashMap.keySet = null;
                    identityHashMap.entrySet = null;
                    identityHashMap.values = null;
                    identityHashMap.modCount = 0;
                    return identityHashMap;
                }
            }
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public Set keySet() {
        if (this.keySet == null) {
            this.keySet = new AbstractSet() {
                public Iterator iterator() {
                    return IdentityHashMap.this.getHashIterator(0);
                }

                public int size() {
                    return IdentityHashMap.this.count;
                }

                public boolean contains(Object obj) {
                    return IdentityHashMap.this.containsKey(obj);
                }

                public boolean remove(Object obj) {
                    int access$100 = IdentityHashMap.this.count;
                    IdentityHashMap.this.remove(obj);
                    return IdentityHashMap.this.count != access$100;
                }

                public void clear() {
                    IdentityHashMap.this.clear();
                }
            };
        }
        return this.keySet;
    }

    public Collection values() {
        if (this.values == null) {
            this.values = new AbstractCollection() {
                public Iterator iterator() {
                    return IdentityHashMap.this.getHashIterator(1);
                }

                public int size() {
                    return IdentityHashMap.this.count;
                }

                public boolean contains(Object obj) {
                    return IdentityHashMap.this.containsValue(obj);
                }

                public void clear() {
                    IdentityHashMap.this.clear();
                }
            };
        }
        return this.values;
    }

    public Set entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new AbstractSet() {
                public Iterator iterator() {
                    return IdentityHashMap.this.getHashIterator(2);
                }

                public boolean contains(Object obj) {
                    int i;
                    if (!(obj instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    Entry[] access$200 = IdentityHashMap.this.table;
                    if (key == null) {
                        i = 0;
                    } else {
                        i = System.identityHashCode(key);
                    }
                    for (Entry entry2 = access$200[(Integer.MAX_VALUE & i) % access$200.length]; entry2 != null; entry2 = entry2.next) {
                        if (entry2.hash == i && entry2.equals(entry)) {
                            return true;
                        }
                    }
                    return false;
                }

                public boolean remove(Object obj) {
                    int i;
                    if (!(obj instanceof Map.Entry)) {
                        return false;
                    }
                    Map.Entry entry = (Map.Entry) obj;
                    Object key = entry.getKey();
                    Entry[] access$200 = IdentityHashMap.this.table;
                    if (key == null) {
                        i = 0;
                    } else {
                        i = System.identityHashCode(key);
                    }
                    int length = (Integer.MAX_VALUE & i) % access$200.length;
                    Entry entry2 = access$200[length];
                    Entry entry3 = null;
                    while (entry2 != null) {
                        if (entry2.hash != i || !entry2.equals(entry)) {
                            entry3 = entry2;
                            entry2 = entry2.next;
                        } else {
                            IdentityHashMap.access$308(IdentityHashMap.this);
                            if (entry3 != null) {
                                entry3.next = entry2.next;
                            } else {
                                access$200[length] = entry2.next;
                            }
                            IdentityHashMap.access$110(IdentityHashMap.this);
                            entry2.value = null;
                            return true;
                        }
                    }
                    return false;
                }

                public int size() {
                    return IdentityHashMap.this.count;
                }

                public void clear() {
                    IdentityHashMap.this.clear();
                }
            };
        }
        return this.entrySet;
    }

    /* access modifiers changed from: private */
    public Iterator getHashIterator(int i) {
        if (this.count == 0) {
            return emptyHashIterator;
        }
        return new HashIterator(i);
    }

    private static class Entry implements Map.Entry {
        int hash;
        Object key;
        Entry next;
        Object value;

        Entry(int i, Object obj, Object obj2, Entry entry) {
            this.hash = i;
            this.key = obj;
            this.value = obj2;
            this.next = entry;
        }

        /* access modifiers changed from: protected */
        public Object clone() {
            int i = this.hash;
            Object obj = this.key;
            Object obj2 = this.value;
            Entry entry = this.next;
            return new Entry(i, obj, obj2, entry == null ? null : (Entry) entry.clone());
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object obj) {
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x0021 A[ORIG_RETURN, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof java.util.Map.Entry
                r1 = 0
                if (r0 != 0) goto L_0x0006
                return r1
            L_0x0006:
                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                java.lang.Object r0 = r3.key
                java.lang.Object r2 = r4.getKey()
                if (r0 != r2) goto L_0x0022
                java.lang.Object r0 = r3.value
                java.lang.Object r4 = r4.getValue()
                if (r0 != 0) goto L_0x001b
                if (r4 != 0) goto L_0x0022
                goto L_0x0021
            L_0x001b:
                boolean r4 = r0.equals(r4)
                if (r4 == 0) goto L_0x0022
            L_0x0021:
                r1 = 1
            L_0x0022:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.util.IdentityHashMap.Entry.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            int i = this.hash;
            Object obj = this.value;
            return i ^ (obj == null ? 0 : obj.hashCode());
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.key);
            stringBuffer.append("=");
            stringBuffer.append(this.value);
            return stringBuffer.toString();
        }
    }

    private static class EmptyHashIterator implements Iterator {
        public boolean hasNext() {
            return false;
        }

        EmptyHashIterator() {
        }

        public Object next() {
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new IllegalStateException();
        }
    }

    private class HashIterator implements Iterator {
        Entry entry = null;
        private int expectedModCount;
        int index;
        Entry lastReturned = null;
        Entry[] table;
        int type;

        HashIterator(int i) {
            Entry[] access$200 = IdentityHashMap.this.table;
            this.table = access$200;
            this.index = access$200.length;
            this.expectedModCount = IdentityHashMap.this.modCount;
            this.type = i;
        }

        public boolean hasNext() {
            Entry entry2 = this.entry;
            int i = this.index;
            Entry[] entryArr = this.table;
            while (entry2 == null && i > 0) {
                i--;
                entry2 = entryArr[i];
            }
            this.entry = entry2;
            this.index = i;
            return entry2 != null;
        }

        public Object next() {
            if (IdentityHashMap.this.modCount == this.expectedModCount) {
                Entry entry2 = this.entry;
                int i = this.index;
                Entry[] entryArr = this.table;
                while (entry2 == null && i > 0) {
                    i--;
                    entry2 = entryArr[i];
                }
                this.entry = entry2;
                this.index = i;
                if (entry2 != null) {
                    this.lastReturned = entry2;
                    this.entry = entry2.next;
                    int i2 = this.type;
                    if (i2 == 0) {
                        return entry2.key;
                    }
                    return i2 == 1 ? entry2.value : entry2;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            } else if (IdentityHashMap.this.modCount == this.expectedModCount) {
                Entry[] access$200 = IdentityHashMap.this.table;
                int length = (this.lastReturned.hash & Integer.MAX_VALUE) % access$200.length;
                Entry entry2 = null;
                for (Entry entry3 = access$200[length]; entry3 != null; entry3 = entry3.next) {
                    if (entry3 == this.lastReturned) {
                        IdentityHashMap.access$308(IdentityHashMap.this);
                        this.expectedModCount++;
                        if (entry2 == null) {
                            access$200[length] = entry3.next;
                        } else {
                            entry2.next = entry3.next;
                        }
                        IdentityHashMap.access$110(IdentityHashMap.this);
                        this.lastReturned = null;
                        return;
                    }
                    entry2 = entry3;
                }
                throw new ConcurrentModificationException();
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.table.length);
        objectOutputStream.writeInt(this.count);
        for (int length = this.table.length - 1; length >= 0; length--) {
            for (Entry entry = this.table[length]; entry != null; entry = entry.next) {
                objectOutputStream.writeObject(entry.key);
                objectOutputStream.writeObject(entry.value);
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.table = new Entry[objectInputStream.readInt()];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* access modifiers changed from: package-private */
    public int capacity() {
        return this.table.length;
    }

    /* access modifiers changed from: package-private */
    public float loadFactor() {
        return this.loadFactor;
    }
}
