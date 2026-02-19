package freemarker.cache;

import freemarker.core._ConcurrentMapFactory;
import freemarker.template.utility.UndeclaredThrowableException;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class SoftCacheStorage implements ConcurrentCacheStorage {
    private static final Method atomicRemove = getAtomicRemoveMethod();
    static /* synthetic */ Class class$java$lang$Object;
    private final boolean concurrent;
    private final Map map;
    private final ReferenceQueue queue;

    public SoftCacheStorage() {
        this(_ConcurrentMapFactory.newMaybeConcurrentHashMap());
    }

    public boolean isConcurrent() {
        return this.concurrent;
    }

    public SoftCacheStorage(Map map2) {
        this.queue = new ReferenceQueue();
        this.map = map2;
        this.concurrent = _ConcurrentMapFactory.isConcurrent(map2);
    }

    public Object get(Object obj) {
        processQueue();
        Reference reference = (Reference) this.map.get(obj);
        if (reference == null) {
            return null;
        }
        return reference.get();
    }

    public void put(Object obj, Object obj2) {
        processQueue();
        this.map.put(obj, new SoftValueReference(obj, obj2, this.queue));
    }

    public void remove(Object obj) {
        processQueue();
        this.map.remove(obj);
    }

    public void clear() {
        this.map.clear();
        processQueue();
    }

    private void processQueue() {
        while (true) {
            SoftValueReference softValueReference = (SoftValueReference) this.queue.poll();
            if (softValueReference != null) {
                Object key = softValueReference.getKey();
                if (this.concurrent) {
                    try {
                        atomicRemove.invoke(this.map, new Object[]{key, softValueReference});
                    } catch (IllegalAccessException e) {
                        throw new UndeclaredThrowableException(e);
                    } catch (InvocationTargetException e2) {
                        throw new UndeclaredThrowableException(e2);
                    }
                } else if (this.map.get(key) == softValueReference) {
                    this.map.remove(key);
                }
            } else {
                return;
            }
        }
    }

    private static final class SoftValueReference extends SoftReference {
        private final Object key;

        SoftValueReference(Object obj, Object obj2, ReferenceQueue referenceQueue) {
            super(obj2, referenceQueue);
            this.key = obj;
        }

        /* access modifiers changed from: package-private */
        public Object getKey() {
            return this.key;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static Method getAtomicRemoveMethod() {
        try {
            Class<?> cls = Class.forName("java.util.concurrent.ConcurrentMap");
            Class[] clsArr = new Class[2];
            Class cls2 = class$java$lang$Object;
            if (cls2 == null) {
                cls2 = class$("java.lang.Object");
                class$java$lang$Object = cls2;
            }
            clsArr[0] = cls2;
            Class cls3 = class$java$lang$Object;
            if (cls3 == null) {
                cls3 = class$("java.lang.Object");
                class$java$lang$Object = cls3;
            }
            clsArr[1] = cls3;
            return cls.getMethod("remove", clsArr);
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
