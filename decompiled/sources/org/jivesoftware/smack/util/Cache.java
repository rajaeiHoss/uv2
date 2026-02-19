package org.jivesoftware.smack.util;

import java.io.PrintStream;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.util.collections.AbstractMapEntry;

public class Cache<K, V> implements Map<K, V> {
    protected LinkedList ageList;
    protected long cacheHits;
    protected long cacheMisses = 0;
    protected LinkedList lastAccessedList;
    protected Map<K, CacheObject<V>> map;
    protected int maxCacheSize;
    protected long maxLifetime;

    public Cache(int i, long j) {
        if (i != 0) {
            this.maxCacheSize = i;
            this.maxLifetime = j;
            this.map = new HashMap(103);
            this.lastAccessedList = new LinkedList();
            this.ageList = new LinkedList();
            return;
        }
        throw new IllegalArgumentException("Max cache size cannot be 0.");
    }

    public synchronized V put(K k, V v) {
        V v2;
        v2 = null;
        if (this.map.containsKey(k)) {
            v2 = remove(k, true);
        }
        CacheObject cacheObject = new CacheObject(v);
        this.map.put(k, cacheObject);
        cacheObject.lastAccessedListNode = this.lastAccessedList.addFirst((Object) k);
        LinkedListNode addFirst = this.ageList.addFirst((Object) k);
        addFirst.timestamp = System.currentTimeMillis();
        cacheObject.ageListNode = addFirst;
        cullCache();
        return v2;
    }

    public synchronized V get(Object obj) {
        deleteExpiredEntries();
        CacheObject<V> cacheObject = this.map.get(obj);
        if (cacheObject == null) {
            this.cacheMisses++;
            return null;
        }
        cacheObject.lastAccessedListNode.remove();
        this.lastAccessedList.addFirst(cacheObject.lastAccessedListNode);
        this.cacheHits++;
        cacheObject.readCount++;
        return cacheObject.object;
    }

    public synchronized V remove(Object obj) {
        return remove(obj, false);
    }

    public synchronized V remove(Object obj, boolean z) {
        CacheObject<V> remove = this.map.remove(obj);
        if (remove == null) {
            return null;
        }
        remove.lastAccessedListNode.remove();
        remove.ageListNode.remove();
        remove.ageListNode = null;
        remove.lastAccessedListNode = null;
        return remove.object;
    }

    public synchronized void clear() {
        for (Object remove : this.map.keySet().toArray()) {
            remove(remove);
        }
        this.map.clear();
        this.lastAccessedList.clear();
        this.ageList.clear();
        this.cacheHits = 0;
        this.cacheMisses = 0;
    }

    public synchronized int size() {
        deleteExpiredEntries();
        return this.map.size();
    }

    public synchronized boolean isEmpty() {
        deleteExpiredEntries();
        return this.map.isEmpty();
    }

    public synchronized Collection<V> values() {
        deleteExpiredEntries();
        return Collections.unmodifiableCollection(new AbstractCollection<V>() {
            public Iterator<V> iterator() {
                final Iterator<CacheObject<V>> values = Cache.this.map.values().iterator();
                return new Iterator<V>() {
                    public boolean hasNext() {
                        return values.hasNext();
                    }

                    public V next() {
                        return values.next().object;
                    }

                    public void remove() {
                        values.remove();
                    }
                };
            }

            public int size() {
                return Cache.this.map.values().size();
            }
        });
    }

    public synchronized boolean containsKey(Object obj) {
        deleteExpiredEntries();
        return this.map.containsKey(obj);
    }

    public void putAll(Map<? extends K, ? extends V> map2) {
        for (Map.Entry<? extends K, ? extends V> next : map2.entrySet()) {
            V value = next.getValue();
            if (value instanceof CacheObject) {
                value = ((CacheObject<V>) value).object;
            }
            put(next.getKey(), value);
        }
    }

    public synchronized boolean containsValue(Object obj) {
        deleteExpiredEntries();
        return this.map.containsValue(new CacheObject(obj));
    }

    public synchronized Set<Map.Entry<K, V>> entrySet() {
        deleteExpiredEntries();
        return new AbstractSet<Map.Entry<K, V>>() {
            public Iterator<Map.Entry<K, V>> iterator() {
                final Iterator<Map.Entry<K, CacheObject<V>>> set = Cache.this.map.entrySet().iterator();
                return new Iterator<Map.Entry<K, V>>() {
                    public boolean hasNext() {
                        return set.hasNext();
                    }

                    public Map.Entry<K, V> next() {
                        Map.Entry<K, CacheObject<V>> next = set.next();
                        return new AbstractMapEntry<K, V>(next.getKey(), next.getValue().object) {
                            public V setValue(V v) {
                                throw new UnsupportedOperationException("Cannot set");
                            }
                        };
                    }

                    public void remove() {
                        set.remove();
                    }
                };
            }

            public int size() {
                return Cache.this.map.entrySet().size();
            }
        };
    }

    public synchronized Set<K> keySet() {
        deleteExpiredEntries();
        return Collections.unmodifiableSet(this.map.keySet());
    }

    public long getCacheHits() {
        return this.cacheHits;
    }

    public long getCacheMisses() {
        return this.cacheMisses;
    }

    public int getMaxCacheSize() {
        return this.maxCacheSize;
    }

    public synchronized void setMaxCacheSize(int i) {
        this.maxCacheSize = i;
        cullCache();
    }

    public long getMaxLifetime() {
        return this.maxLifetime;
    }

    public void setMaxLifetime(long j) {
        this.maxLifetime = j;
    }

    /* access modifiers changed from: protected */
    public synchronized void deleteExpiredEntries() {
        if (this.maxLifetime > 0) {
            LinkedListNode last = this.ageList.getLast();
            if (last != null) {
                long currentTimeMillis = System.currentTimeMillis() - this.maxLifetime;
                while (currentTimeMillis > last.timestamp) {
                    if (remove(last.object, true) == null) {
                        PrintStream printStream = System.err;
                        printStream.println("Error attempting to remove(" + last.object.toString() + ") - cacheObject not found in cache!");
                        last.remove();
                    }
                    last = this.ageList.getLast();
                    if (last == null) {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void cullCache() {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r5.maxCacheSize     // Catch:{ all -> 0x0070 }
            if (r0 >= 0) goto L_0x0007
            monitor-exit(r5)
            return
        L_0x0007:
            java.util.Map<K, org.jivesoftware.smack.util.Cache$CacheObject<V>> r0 = r5.map     // Catch:{ all -> 0x0070 }
            int r0 = r0.size()     // Catch:{ all -> 0x0070 }
            int r1 = r5.maxCacheSize     // Catch:{ all -> 0x0070 }
            if (r0 <= r1) goto L_0x006e
            r5.deleteExpiredEntries()     // Catch:{ all -> 0x0070 }
            int r0 = r5.maxCacheSize     // Catch:{ all -> 0x0070 }
            double r0 = (double) r0     // Catch:{ all -> 0x0070 }
            r2 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            double r0 = r0 * r2
            int r0 = (int) r0     // Catch:{ all -> 0x0070 }
            java.util.Map<K, org.jivesoftware.smack.util.Cache$CacheObject<V>> r1 = r5.map     // Catch:{ all -> 0x0070 }
            int r1 = r1.size()     // Catch:{ all -> 0x0070 }
        L_0x0025:
            if (r1 <= r0) goto L_0x006e
            org.jivesoftware.smack.util.Cache$LinkedList r2 = r5.lastAccessedList     // Catch:{ all -> 0x0070 }
            org.jivesoftware.smack.util.Cache$LinkedListNode r2 = r2.getLast()     // Catch:{ all -> 0x0070 }
            java.lang.Object r2 = r2.object     // Catch:{ all -> 0x0070 }
            r3 = 1
            java.lang.Object r2 = r5.remove(r2, r3)     // Catch:{ all -> 0x0070 }
            if (r2 != 0) goto L_0x006b
            java.io.PrintStream r2 = java.lang.System.err     // Catch:{ all -> 0x0070 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0070 }
            r3.<init>()     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = "Error attempting to cullCache with remove("
            r3.append(r4)     // Catch:{ all -> 0x0070 }
            org.jivesoftware.smack.util.Cache$LinkedList r4 = r5.lastAccessedList     // Catch:{ all -> 0x0070 }
            org.jivesoftware.smack.util.Cache$LinkedListNode r4 = r4.getLast()     // Catch:{ all -> 0x0070 }
            java.lang.Object r4 = r4.object     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0070 }
            r3.append(r4)     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = ") - "
            r3.append(r4)     // Catch:{ all -> 0x0070 }
            java.lang.String r4 = "cacheObject not found in cache!"
            r3.append(r4)     // Catch:{ all -> 0x0070 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0070 }
            r2.println(r3)     // Catch:{ all -> 0x0070 }
            org.jivesoftware.smack.util.Cache$LinkedList r2 = r5.lastAccessedList     // Catch:{ all -> 0x0070 }
            org.jivesoftware.smack.util.Cache$LinkedListNode r2 = r2.getLast()     // Catch:{ all -> 0x0070 }
            r2.remove()     // Catch:{ all -> 0x0070 }
        L_0x006b:
            int r1 = r1 + -1
            goto L_0x0025
        L_0x006e:
            monitor-exit(r5)
            return
        L_0x0070:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.Cache.cullCache():void");
    }

    private static class CacheObject<V> {
        public LinkedListNode ageListNode;
        public LinkedListNode lastAccessedListNode;
        public V object;
        public int readCount = 0;

        public CacheObject(V v) {
            this.object = v;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CacheObject)) {
                return false;
            }
            return this.object.equals(((CacheObject) obj).object);
        }

        public int hashCode() {
            return this.object.hashCode();
        }
    }

    private static class LinkedList {
        private LinkedListNode head;

        public LinkedList() {
            LinkedListNode linkedListNode = new LinkedListNode("head", (LinkedListNode) null, (LinkedListNode) null);
            this.head = linkedListNode;
            linkedListNode.previous = linkedListNode;
            linkedListNode.next = linkedListNode;
        }

        public LinkedListNode getFirst() {
            LinkedListNode linkedListNode = this.head.next;
            if (linkedListNode == this.head) {
                return null;
            }
            return linkedListNode;
        }

        public LinkedListNode getLast() {
            LinkedListNode linkedListNode = this.head.previous;
            if (linkedListNode == this.head) {
                return null;
            }
            return linkedListNode;
        }

        public LinkedListNode addFirst(LinkedListNode linkedListNode) {
            linkedListNode.next = this.head.next;
            linkedListNode.previous = this.head;
            linkedListNode.previous.next = linkedListNode;
            linkedListNode.next.previous = linkedListNode;
            return linkedListNode;
        }

        public LinkedListNode addFirst(Object obj) {
            LinkedListNode linkedListNode = new LinkedListNode(obj, this.head.next, this.head);
            linkedListNode.previous.next = linkedListNode;
            linkedListNode.next.previous = linkedListNode;
            return linkedListNode;
        }

        public LinkedListNode addLast(Object obj) {
            LinkedListNode linkedListNode = this.head;
            LinkedListNode linkedListNode2 = new LinkedListNode(obj, linkedListNode, linkedListNode.previous);
            linkedListNode2.previous.next = linkedListNode2;
            linkedListNode2.next.previous = linkedListNode2;
            return linkedListNode2;
        }

        public void clear() {
            LinkedListNode last = getLast();
            while (last != null) {
                last.remove();
                last = getLast();
            }
            LinkedListNode linkedListNode = this.head;
            linkedListNode.previous = linkedListNode;
            linkedListNode.next = linkedListNode;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (LinkedListNode linkedListNode = this.head.next; linkedListNode != this.head; linkedListNode = linkedListNode.next) {
                sb.append(linkedListNode.toString());
                sb.append(", ");
            }
            return sb.toString();
        }
    }

    private static class LinkedListNode {
        public LinkedListNode next;
        public Object object;
        public LinkedListNode previous;
        public long timestamp;

        public LinkedListNode(Object obj, LinkedListNode linkedListNode, LinkedListNode linkedListNode2) {
            this.object = obj;
            this.next = linkedListNode;
            this.previous = linkedListNode2;
        }

        public void remove() {
            LinkedListNode linkedListNode = this.previous;
            linkedListNode.next = this.next;
            this.next.previous = linkedListNode;
        }

        public String toString() {
            return this.object.toString();
        }
    }
}
