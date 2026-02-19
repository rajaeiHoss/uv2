package org.jivesoftware.smack.util.collections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import org.jivesoftware.smack.util.collections.AbstractHashedMap;

public abstract class AbstractReferenceMap<K, V> extends AbstractHashedMap<K, V> {
    public static final int HARD = 0;
    public static final int SOFT = 1;
    public static final int WEAK = 2;
    protected int keyType;
    protected boolean purgeValues;
    /* access modifiers changed from: private */
    public transient ReferenceQueue queue;
    protected int valueType;

    protected AbstractReferenceMap() {
    }

    protected AbstractReferenceMap(int i, int i2, int i3, float f, boolean z) {
        super(i3, f);
        verify("keyType", i);
        verify("valueType", i2);
        this.keyType = i;
        this.valueType = i2;
        this.purgeValues = z;
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.queue = new ReferenceQueue();
    }

    private static void verify(String str, int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException(str + " must be HARD, SOFT, WEAK.");
        }
    }

    public int size() {
        purgeBeforeRead();
        return super.size();
    }

    public boolean isEmpty() {
        purgeBeforeRead();
        return super.isEmpty();
    }

    public boolean containsKey(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> entry = getEntry(obj);
        if (entry == null || entry.getValue() == null) {
            return false;
        }
        return true;
    }

    public boolean containsValue(Object obj) {
        purgeBeforeRead();
        if (obj == null) {
            return false;
        }
        return super.containsValue(obj);
    }

    public V get(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }

    public V put(K k, V v) {
        Objects.requireNonNull(k, "null keys not allowed");
        Objects.requireNonNull(v, "null values not allowed");
        purgeBeforeWrite();
        return super.put(k, v);
    }

    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        purgeBeforeWrite();
        return super.remove(obj);
    }

    public void clear() {
        super.clear();
        do {
        } while (this.queue.poll() != null);
    }

    public MapIterator<K, V> mapIterator() {
        return new ReferenceMapIterator(this);
    }

    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new ReferenceEntrySet(this);
        }
        return this.entrySet;
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new ReferenceKeySet(this);
        }
        return this.keySet;
    }

    public Collection<V> values() {
        if (this.values == null) {
            this.values = new ReferenceValues(this);
        }
        return this.values;
    }

    /* access modifiers changed from: protected */
    public void purgeBeforeRead() {
        purge();
    }

    /* access modifiers changed from: protected */
    public void purgeBeforeWrite() {
        purge();
    }

    /* access modifiers changed from: protected */
    public void purge() {
        Reference poll = this.queue.poll();
        while (poll != null) {
            purge(poll);
            poll = this.queue.poll();
        }
    }

    /* access modifiers changed from: protected */
    public void purge(Reference reference) {
        int hashIndex = hashIndex(reference.hashCode(), this.data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = null;
        for (AbstractHashedMap.HashEntry<K, V> hashEntry2 = this.data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (((ReferenceEntry) hashEntry2).purge(reference)) {
                if (hashEntry == null) {
                    this.data[hashIndex] = hashEntry2.next;
                } else {
                    hashEntry.next = hashEntry2.next;
                }
                this.size--;
                return;
            }
            hashEntry = hashEntry2;
        }
    }

    /* access modifiers changed from: protected */
    public AbstractHashedMap.HashEntry<K, V> getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        return super.getEntry(obj);
    }

    /* access modifiers changed from: protected */
    public int hashEntry(Object obj, Object obj2) {
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    /* access modifiers changed from: protected */
    public boolean isEqualKey(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    public AbstractHashedMap.HashEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new ReferenceEntry(this, (ReferenceEntry) hashEntry, i, k, v);
    }

    /* access modifiers changed from: protected */
    public Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        return new ReferenceEntrySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<K> createKeySetIterator() {
        return new ReferenceKeySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<V> createValuesIterator() {
        return new ReferenceValuesIterator(this);
    }

    static class ReferenceEntrySet<K, V> extends AbstractHashedMap.EntrySet<K, V> {
        protected ReferenceEntrySet(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList<T> arrayList = new ArrayList<>();
            Iterator it = iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                arrayList.add((T) new DefaultMapEntry(entry.getKey(), entry.getValue()));
            }
            return arrayList.toArray(tArr);
        }
    }

    static class ReferenceKeySet<K, V> extends AbstractHashedMap.KeySet<K, V> {
        protected ReferenceKeySet(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList<T> arrayList = new ArrayList<>(this.parent.size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add((T) it.next());
            }
            return arrayList.toArray(tArr);
        }
    }

    static class ReferenceValues<K, V> extends AbstractHashedMap.Values<K, V> {
        protected ReferenceValues(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        public Object[] toArray() {
            return toArray(new Object[0]);
        }

        public <T> T[] toArray(T[] tArr) {
            ArrayList<T> arrayList = new ArrayList<>(this.parent.size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add((T) it.next());
            }
            return arrayList.toArray(tArr);
        }
    }

    protected static class ReferenceEntry<K, V> extends AbstractHashedMap.HashEntry<K, V> {
        protected final AbstractReferenceMap<K, V> parent;
        protected Reference<K> refKey;
        protected Reference<V> refValue;

        public ReferenceEntry(AbstractReferenceMap<K, V> abstractReferenceMap, ReferenceEntry<K, V> referenceEntry, int i, K k, V v) {
            super(referenceEntry, i, null, null);
            this.parent = abstractReferenceMap;
            if (abstractReferenceMap.keyType != 0) {
                this.refKey = toReference(abstractReferenceMap.keyType, k, i);
            } else {
                setKey(k);
            }
            if (abstractReferenceMap.valueType != 0) {
                this.refValue = toReference(abstractReferenceMap.valueType, v, i);
            } else {
                setValue(v);
            }
        }

        public K getKey() {
            return this.parent.keyType > 0 ? this.refKey.get() : super.getKey();
        }

        public V getValue() {
            return this.parent.valueType > 0 ? this.refValue.get() : super.getValue();
        }

        public V setValue(V v) {
            V value = getValue();
            if (this.parent.valueType > 0) {
                this.refValue.clear();
                this.refValue = toReference(this.parent.valueType, v, this.hashCode);
            } else {
                super.setValue(v);
            }
            return value;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || value == null || !this.parent.isEqualKey(key, getKey()) || !this.parent.isEqualValue(value, getValue())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.parent.hashEntry(getKey(), getValue());
        }

        /* access modifiers changed from: protected */
        public <T> Reference<T> toReference(int i, T t, int i2) {
            if (i == 1) {
                return new SoftRef(i2, t, this.parent.queue);
            }
            if (i == 2) {
                return new WeakRef(i2, t, this.parent.queue);
            }
            throw new Error("Attempt to create hard reference in ReferenceMap!");
        }

        /* access modifiers changed from: package-private */
        public boolean purge(Reference reference) {
            boolean z = true;
            if (!(this.parent.keyType > 0 && this.refKey == reference) && (this.parent.valueType <= 0 || this.refValue != reference)) {
                z = false;
            }
            if (z) {
                if (this.parent.keyType > 0) {
                    this.refKey.clear();
                }
                if (this.parent.valueType > 0) {
                    this.refValue.clear();
                } else if (this.parent.purgeValues) {
                    setValue(null);
                }
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> next() {
            return (ReferenceEntry) this.next;
        }
    }

    static class ReferenceIteratorBase<K, V> {
        K currentKey;
        V currentValue;
        ReferenceEntry<K, V> entry;
        int expectedModCount;
        int index;
        K nextKey;
        V nextValue;
        final AbstractReferenceMap<K, V> parent;
        ReferenceEntry<K, V> previous;

        public ReferenceIteratorBase(AbstractReferenceMap<K, V> abstractReferenceMap) {
            this.parent = abstractReferenceMap;
            this.index = abstractReferenceMap.size() != 0 ? abstractReferenceMap.data.length : 0;
            this.expectedModCount = abstractReferenceMap.modCount;
        }

        public boolean hasNext() {
            checkMod();
            while (nextNull()) {
                ReferenceEntry<K, V> referenceEntry = this.entry;
                int i = this.index;
                while (referenceEntry == null && i > 0) {
                    i--;
                    referenceEntry = (ReferenceEntry) this.parent.data[i];
                }
                this.entry = referenceEntry;
                this.index = i;
                if (referenceEntry == null) {
                    this.currentKey = null;
                    this.currentValue = null;
                    return false;
                }
                this.nextKey = referenceEntry.getKey();
                this.nextValue = referenceEntry.getValue();
                if (nextNull()) {
                    this.entry = this.entry.next();
                }
            }
            return true;
        }

        private void checkMod() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean nextNull() {
            return this.nextKey == null || this.nextValue == null;
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> nextEntry() {
            checkMod();
            if (!nextNull() || hasNext()) {
                ReferenceEntry<K, V> referenceEntry = this.entry;
                this.previous = referenceEntry;
                this.entry = referenceEntry.next();
                this.currentKey = this.nextKey;
                this.currentValue = this.nextValue;
                this.nextKey = null;
                this.nextValue = null;
                return this.previous;
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: protected */
        public ReferenceEntry<K, V> currentEntry() {
            checkMod();
            return this.previous;
        }

        public ReferenceEntry<K, V> superNext() {
            return nextEntry();
        }

        public void remove() {
            checkMod();
            if (this.previous != null) {
                this.parent.remove(this.currentKey);
                this.previous = null;
                this.currentKey = null;
                this.currentValue = null;
                this.expectedModCount = this.parent.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    static class ReferenceEntrySetIterator<K, V> extends ReferenceIteratorBase<K, V> implements Iterator<Map.Entry<K, V>> {
        public ReferenceEntrySetIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public ReferenceEntry<K, V> next() {
            return superNext();
        }
    }

    static class ReferenceKeySetIterator<K, V> extends ReferenceIteratorBase<K, V> implements Iterator<K> {
        ReferenceKeySetIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    static class ReferenceValuesIterator<K, V> extends ReferenceIteratorBase<K, V> implements Iterator<V> {
        ReferenceValuesIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    static class ReferenceMapIterator<K, V> extends ReferenceIteratorBase<K, V> implements MapIterator<K, V> {
        protected ReferenceMapIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        public K next() {
            return nextEntry().getKey();
        }

        public K getKey() {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }
    }

    static class SoftRef<T> extends SoftReference<T> {
        private int hash;

        public SoftRef(int i, T t, ReferenceQueue referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    static class WeakRef<T> extends WeakReference<T> {
        private int hash;

        public WeakRef(int i, T t, ReferenceQueue referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.keyType);
        objectOutputStream.writeInt(this.valueType);
        objectOutputStream.writeBoolean(this.purgeValues);
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        MapIterator mapIterator = mapIterator();
        while (mapIterator.hasNext()) {
            objectOutputStream.writeObject(mapIterator.next());
            objectOutputStream.writeObject(mapIterator.getValue());
        }
        objectOutputStream.writeObject((Object) null);
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.keyType = objectInputStream.readInt();
        this.valueType = objectInputStream.readInt();
        this.purgeValues = objectInputStream.readBoolean();
        this.loadFactor = objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        init();
        this.data = new AbstractHashedMap.HashEntry[readInt];
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                this.threshold = calculateThreshold(this.data.length, this.loadFactor);
                return;
            }
            put((K) readObject, (V) objectInputStream.readObject());
        }
    }
}
