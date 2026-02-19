package freemarker.ext.beans;

import freemarker.core._ConcurrentMapFactory;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class ClassBasedModelFactory implements TemplateHashModel {
    private final Map cache;
    private final Set classIntrospectionsInProgress = new HashSet();
    private final boolean isCacheConcurrentMap;
    private final BeansWrapper wrapper;

    /* access modifiers changed from: protected */
    public abstract TemplateModel createModel(Class cls) throws TemplateModelException;

    public boolean isEmpty() {
        return false;
    }

    protected ClassBasedModelFactory(BeansWrapper beansWrapper) {
        Map newMaybeConcurrentHashMap = _ConcurrentMapFactory.newMaybeConcurrentHashMap();
        this.cache = newMaybeConcurrentHashMap;
        this.isCacheConcurrentMap = _ConcurrentMapFactory.isConcurrent(newMaybeConcurrentHashMap);
        this.wrapper = beansWrapper;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        try {
            return getInternal(str);
        } catch (Exception e) {
            if (e instanceof TemplateModelException) {
                throw ((TemplateModelException) e);
            }
            throw new TemplateModelException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r1 = freemarker.template.utility.ClassUtil.forName(r5);
        r4.wrapper.getClassIntrospectionData(r1);
        r1 = createModel(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0067, code lost:
        if (r1 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0069, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r4.cache.put(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006f, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0074, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r4.classIntrospectionsInProgress.remove(r5);
        r0.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x007d, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007e, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0082, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0083, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r4.classIntrospectionsInProgress.remove(r5);
        r0.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x008d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private freemarker.template.TemplateModel getInternal(java.lang.String r5) throws freemarker.template.TemplateModelException, java.lang.ClassNotFoundException {
        /*
            r4 = this;
            boolean r0 = r4.isCacheConcurrentMap
            if (r0 == 0) goto L_0x000f
            java.util.Map r0 = r4.cache
            java.lang.Object r0 = r0.get(r5)
            freemarker.template.TemplateModel r0 = (freemarker.template.TemplateModel) r0
            if (r0 == 0) goto L_0x000f
            return r0
        L_0x000f:
            freemarker.ext.beans.BeansWrapper r0 = r4.wrapper
            java.lang.Object r0 = r0.getSharedClassIntrospectionCacheLock()
            monitor-enter(r0)
            java.util.Map r1 = r4.cache     // Catch:{ all -> 0x0091 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0091 }
            freemarker.template.TemplateModel r1 = (freemarker.template.TemplateModel) r1     // Catch:{ all -> 0x0091 }
            if (r1 == 0) goto L_0x0022
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            return r1
        L_0x0022:
            if (r1 != 0) goto L_0x0050
            java.util.Set r2 = r4.classIntrospectionsInProgress     // Catch:{ all -> 0x0091 }
            boolean r2 = r2.contains(r5)     // Catch:{ all -> 0x0091 }
            if (r2 == 0) goto L_0x0050
            r0.wait()     // Catch:{ InterruptedException -> 0x0038 }
            java.util.Map r1 = r4.cache     // Catch:{ InterruptedException -> 0x0038 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ InterruptedException -> 0x0038 }
            freemarker.template.TemplateModel r1 = (freemarker.template.TemplateModel) r1     // Catch:{ InterruptedException -> 0x0038 }
            goto L_0x0022
        L_0x0038:
            r5 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0091 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ all -> 0x0091 }
            r2.<init>()     // Catch:{ all -> 0x0091 }
            java.lang.String r3 = "Class inrospection data lookup aborded: "
            r2.append(r3)     // Catch:{ all -> 0x0091 }
            r2.append(r5)     // Catch:{ all -> 0x0091 }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x0091 }
            r1.<init>(r5)     // Catch:{ all -> 0x0091 }
            throw r1     // Catch:{ all -> 0x0091 }
        L_0x0050:
            if (r1 == 0) goto L_0x0054
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            return r1
        L_0x0054:
            java.util.Set r1 = r4.classIntrospectionsInProgress     // Catch:{ all -> 0x0091 }
            r1.add(r5)     // Catch:{ all -> 0x0091 }
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            java.lang.Class r1 = freemarker.template.utility.ClassUtil.forName(r5)     // Catch:{ all -> 0x0082 }
            freemarker.ext.beans.BeansWrapper r2 = r4.wrapper     // Catch:{ all -> 0x0082 }
            r2.getClassIntrospectionData(r1)     // Catch:{ all -> 0x0082 }
            freemarker.template.TemplateModel r1 = r4.createModel(r1)     // Catch:{ all -> 0x0082 }
            if (r1 == 0) goto L_0x0074
            monitor-enter(r0)     // Catch:{ all -> 0x0082 }
            java.util.Map r2 = r4.cache     // Catch:{ all -> 0x0071 }
            r2.put(r5, r1)     // Catch:{ all -> 0x0071 }
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            goto L_0x0074
        L_0x0071:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            throw r1     // Catch:{ all -> 0x0082 }
        L_0x0074:
            monitor-enter(r0)
            java.util.Set r2 = r4.classIntrospectionsInProgress     // Catch:{ all -> 0x007f }
            r2.remove(r5)     // Catch:{ all -> 0x007f }
            r0.notifyAll()     // Catch:{ all -> 0x007f }
            monitor-exit(r0)     // Catch:{ all -> 0x007f }
            return r1
        L_0x007f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x007f }
            throw r5
        L_0x0082:
            r1 = move-exception
            monitor-enter(r0)
            java.util.Set r2 = r4.classIntrospectionsInProgress     // Catch:{ all -> 0x008e }
            r2.remove(r5)     // Catch:{ all -> 0x008e }
            r0.notifyAll()     // Catch:{ all -> 0x008e }
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            throw r1
        L_0x008e:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008e }
            throw r5
        L_0x0091:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0091 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.ClassBasedModelFactory.getInternal(java.lang.String):freemarker.template.TemplateModel");
    }

    /* access modifiers changed from: package-private */
    public void clearCache() {
        synchronized (this.wrapper.getSharedClassIntrospectionCacheLock()) {
            this.cache.clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void removeFromCache(Class cls) {
        synchronized (this.wrapper.getSharedClassIntrospectionCacheLock()) {
            this.cache.remove(cls.getName());
        }
    }

    /* access modifiers changed from: protected */
    public BeansWrapper getWrapper() {
        return this.wrapper;
    }
}
