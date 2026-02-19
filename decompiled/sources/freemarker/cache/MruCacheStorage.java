package freemarker.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class MruCacheStorage implements CacheStorage {
    private final Map map = new HashMap();
    private final int maxSoftSize;
    private final int maxStrongSize;
    private final ReferenceQueue refQueue = new ReferenceQueue();
    private final MruEntry softHead;
    private int softSize = 0;
    private final MruEntry strongHead;
    private int strongSize = 0;

    public MruCacheStorage(int i, int i2) {
        MruEntry mruEntry = new MruEntry();
        this.strongHead = mruEntry;
        MruEntry mruEntry2 = new MruEntry();
        this.softHead = mruEntry2;
        mruEntry2.linkAfter(mruEntry);
        if (i < 0) {
            throw new IllegalArgumentException("maxStrongSize < 0");
        } else if (i2 >= 0) {
            this.maxStrongSize = i;
            this.maxSoftSize = i2;
        } else {
            throw new IllegalArgumentException("maxSoftSize < 0");
        }
    }

    public Object get(Object obj) {
        removeClearedReferences();
        MruEntry mruEntry = (MruEntry) this.map.get(obj);
        if (mruEntry == null) {
            return null;
        }
        relinkEntryAfterStrongHead(mruEntry, (Object) null);
        Object value = mruEntry.getValue();
        return value instanceof MruReference ? ((MruReference) value).get() : value;
    }

    public void put(Object obj, Object obj2) {
        removeClearedReferences();
        MruEntry mruEntry = (MruEntry) this.map.get(obj);
        if (mruEntry == null) {
            MruEntry mruEntry2 = new MruEntry(obj, obj2);
            this.map.put(obj, mruEntry2);
            linkAfterStrongHead(mruEntry2);
            return;
        }
        relinkEntryAfterStrongHead(mruEntry, obj2);
    }

    public void remove(Object obj) {
        removeClearedReferences();
        removeInternal(obj);
    }

    private void removeInternal(Object obj) {
        MruEntry mruEntry = (MruEntry) this.map.remove(obj);
        if (mruEntry != null) {
            unlinkEntryAndInspectIfSoft(mruEntry);
        }
    }

    public void clear() {
        this.strongHead.makeHead();
        this.softHead.linkAfter(this.strongHead);
        this.map.clear();
        this.softSize = 0;
        this.strongSize = 0;
        do {
        } while (this.refQueue.poll() != null);
    }

    private void relinkEntryAfterStrongHead(MruEntry mruEntry, Object obj) {
        if (!unlinkEntryAndInspectIfSoft(mruEntry) || obj != null) {
            if (obj != null) {
                mruEntry.setValue(obj);
            }
            linkAfterStrongHead(mruEntry);
            return;
        }
        MruReference mruReference = (MruReference) mruEntry.getValue();
        Object obj2 = mruReference.get();
        if (obj2 != null) {
            mruEntry.setValue(obj2);
            linkAfterStrongHead(mruEntry);
            return;
        }
        this.map.remove(mruReference.getKey());
    }

    private void linkAfterStrongHead(MruEntry mruEntry) {
        mruEntry.linkAfter(this.strongHead);
        int i = this.strongSize;
        if (i == this.maxStrongSize) {
            MruEntry previous = this.softHead.getPrevious();
            if (previous != this.strongHead) {
                previous.unlink();
                if (this.maxSoftSize > 0) {
                    previous.linkAfter(this.softHead);
                    previous.setValue(new MruReference(previous, this.refQueue));
                    int i2 = this.softSize;
                    if (i2 == this.maxSoftSize) {
                        MruEntry previous2 = this.strongHead.getPrevious();
                        previous2.unlink();
                        this.map.remove(previous2.getKey());
                        return;
                    }
                    this.softSize = i2 + 1;
                    return;
                }
                this.map.remove(previous.getKey());
                return;
            }
            return;
        }
        this.strongSize = i + 1;
    }

    private boolean unlinkEntryAndInspectIfSoft(MruEntry mruEntry) {
        mruEntry.unlink();
        if (mruEntry.getValue() instanceof MruReference) {
            this.softSize--;
            return true;
        }
        this.strongSize--;
        return false;
    }

    private void removeClearedReferences() {
        while (true) {
            MruReference mruReference = (MruReference) this.refQueue.poll();
            if (mruReference != null) {
                removeInternal(mruReference.getKey());
            } else {
                return;
            }
        }
    }

    private static final class MruEntry {
        private final Object key;
        private MruEntry next;
        private MruEntry prev;
        private Object value;

        MruEntry() {
            makeHead();
            this.value = null;
            this.key = null;
        }

        MruEntry(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this.key;
        }

        /* access modifiers changed from: package-private */
        public Object getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(Object obj) {
            this.value = obj;
        }

        /* access modifiers changed from: package-private */
        public MruEntry getPrevious() {
            return this.prev;
        }

        /* access modifiers changed from: package-private */
        public void linkAfter(MruEntry mruEntry) {
            this.next = mruEntry.next;
            mruEntry.next = this;
            this.prev = mruEntry;
            this.next.prev = this;
        }

        /* access modifiers changed from: package-private */
        public void unlink() {
            MruEntry mruEntry = this.next;
            mruEntry.prev = this.prev;
            this.prev.next = mruEntry;
            this.prev = null;
            this.next = null;
        }

        /* access modifiers changed from: package-private */
        public void makeHead() {
            this.next = this;
            this.prev = this;
        }
    }

    private static class MruReference extends SoftReference {
        private final Object key;

        MruReference(MruEntry mruEntry, ReferenceQueue referenceQueue) {
            super(mruEntry.getValue(), referenceQueue);
            this.key = mruEntry.getKey();
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this.key;
        }
    }
}
