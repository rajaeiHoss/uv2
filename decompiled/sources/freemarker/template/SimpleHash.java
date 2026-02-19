package freemarker.template;

import freemarker.ext.beans.BeansWrapper;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SimpleHash extends WrappingTemplateModel implements TemplateHashModelEx, Serializable {
    private Map map;
    private boolean putFailed;
    private Map unwrappedMap;

    public SimpleHash() {
        this((ObjectWrapper) null);
    }

    public SimpleHash(Map map2) {
        this(map2, (ObjectWrapper) null);
    }

    public SimpleHash(ObjectWrapper objectWrapper) {
        super(objectWrapper);
        this.map = new HashMap();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SimpleHash(java.util.Map r3, freemarker.template.ObjectWrapper r4) {
        /*
            r2 = this;
            r2.<init>(r4)
            java.util.Map r4 = r2.copyMap(r3)     // Catch:{ ConcurrentModificationException -> 0x000a }
            r2.map = r4     // Catch:{ ConcurrentModificationException -> 0x000a }
            goto L_0x0017
        L_0x000a:
            r0 = 5
            java.lang.Thread.sleep(r0)     // Catch:{ InterruptedException -> 0x000f }
        L_0x000f:
            monitor-enter(r3)
            java.util.Map r4 = r2.copyMap(r3)     // Catch:{ all -> 0x0018 }
            r2.map = r4     // Catch:{ all -> 0x0018 }
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
        L_0x0017:
            return
        L_0x0018:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.SimpleHash.<init>(java.util.Map, freemarker.template.ObjectWrapper):void");
    }

    /* access modifiers changed from: protected */
    public Map copyMap(Map map2) {
        if (map2 instanceof HashMap) {
            return (Map) ((HashMap) map2).clone();
        }
        if (!(map2 instanceof SortedMap)) {
            return new HashMap(map2);
        }
        if (map2 instanceof TreeMap) {
            return (Map) ((TreeMap) map2).clone();
        }
        return new TreeMap((SortedMap) map2);
    }

    public void put(String str, Object obj) {
        this.map.put(str, obj);
        this.unwrappedMap = null;
    }

    public void put(String str, boolean z) {
        put(str, (Object) z ? TemplateBooleanModel.TRUE : TemplateBooleanModel.FALSE);
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.lang.Character, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel get(java.lang.String r6) throws freemarker.template.TemplateModelException {
        /*
            r5 = this;
            java.util.Map r0 = r5.map
            java.lang.Object r0 = r0.get(r6)
            r1 = 1
            if (r0 != 0) goto L_0x003f
            int r2 = r6.length()
            r3 = 0
            if (r2 != r1) goto L_0x0036
            java.lang.Character r0 = new java.lang.Character
            r2 = 0
            char r2 = r6.charAt(r2)
            r0.<init>(r2)
            java.util.Map r2 = r5.map
            java.lang.Object r2 = r2.get(r0)
            if (r2 != 0) goto L_0x0033
            java.util.Map r4 = r5.map
            boolean r6 = r4.containsKey(r6)
            if (r6 != 0) goto L_0x0033
            java.util.Map r6 = r5.map
            boolean r6 = r6.containsKey(r0)
            if (r6 != 0) goto L_0x0033
            return r3
        L_0x0033:
            r6 = r0
            r0 = r2
            goto L_0x003f
        L_0x0036:
            java.util.Map r2 = r5.map
            boolean r2 = r2.containsKey(r6)
            if (r2 != 0) goto L_0x003f
            return r3
        L_0x003f:
            boolean r2 = r0 instanceof freemarker.template.TemplateModel
            if (r2 == 0) goto L_0x0046
            freemarker.template.TemplateModel r0 = (freemarker.template.TemplateModel) r0
            return r0
        L_0x0046:
            freemarker.template.TemplateModel r0 = r5.wrap(r0)
            boolean r2 = r5.putFailed
            if (r2 != 0) goto L_0x0056
            java.util.Map r2 = r5.map     // Catch:{ Exception -> 0x0054 }
            r2.put(r6, r0)     // Catch:{ Exception -> 0x0054 }
            goto L_0x0056
        L_0x0054:
            r5.putFailed = r1
        L_0x0056:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.SimpleHash.get(java.lang.String):freemarker.template.TemplateModel");
    }

    public boolean containsKey(String str) {
        return this.map.containsKey(str);
    }

    public void remove(String str) {
        this.map.remove(str);
    }

    public void putAll(Map map2) {
        for (Map.Entry entry : map2.entrySet()) {
            put((String) entry.getKey(), entry.getValue());
        }
    }

    public Map toMap() throws TemplateModelException {
        if (this.unwrappedMap == null) {
            Class<?> cls = this.map.getClass();
            try {
                Map map2 = (Map) cls.newInstance();
                BeansWrapper defaultInstance = BeansWrapper.getDefaultInstance();
                for (Map.Entry entry : this.map.entrySet()) {
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof TemplateModel) {
                        value = defaultInstance.unwrap((TemplateModel) value);
                    }
                    map2.put(key, value);
                }
                this.unwrappedMap = map2;
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error instantiating map of type ");
                stringBuffer.append(cls.getName());
                stringBuffer.append("\n");
                stringBuffer.append(e.getMessage());
                throw new TemplateModelException(stringBuffer.toString());
            }
        }
        return this.unwrappedMap;
    }

    public String toString() {
        return this.map.toString();
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        Map map2 = this.map;
        return map2 == null || map2.isEmpty();
    }

    public TemplateCollectionModel keys() {
        return new SimpleCollection((Collection) this.map.keySet(), getObjectWrapper());
    }

    public TemplateCollectionModel values() {
        return new SimpleCollection(this.map.values(), getObjectWrapper());
    }

    public SimpleHash synchronizedWrapper() {
        return new SynchronizedHash();
    }

    private class SynchronizedHash extends SimpleHash {
        private SynchronizedHash() {
        }

        public boolean isEmpty() {
            boolean isEmpty;
            synchronized (SimpleHash.this) {
                isEmpty = SimpleHash.this.isEmpty();
            }
            return isEmpty;
        }

        public void put(String str, Object obj) {
            synchronized (SimpleHash.this) {
                SimpleHash.this.put(str, obj);
            }
        }

        public TemplateModel get(String str) throws TemplateModelException {
            TemplateModel templateModel;
            synchronized (SimpleHash.this) {
                templateModel = SimpleHash.this.get(str);
            }
            return templateModel;
        }

        public void remove(String str) {
            synchronized (SimpleHash.this) {
                SimpleHash.this.remove(str);
            }
        }

        public int size() {
            int size;
            synchronized (SimpleHash.this) {
                size = SimpleHash.this.size();
            }
            return size;
        }

        public TemplateCollectionModel keys() {
            TemplateCollectionModel keys;
            synchronized (SimpleHash.this) {
                keys = SimpleHash.this.keys();
            }
            return keys;
        }

        public TemplateCollectionModel values() {
            TemplateCollectionModel values;
            synchronized (SimpleHash.this) {
                values = SimpleHash.this.values();
            }
            return values;
        }

        public Map toMap() throws TemplateModelException {
            Map map;
            synchronized (SimpleHash.this) {
                map = SimpleHash.this.toMap();
            }
            return map;
        }
    }
}
