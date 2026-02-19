package freemarker.ext.util;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelAdapter;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Map;

public abstract class ModelCache {
    private Map modelCache = null;
    private ReferenceQueue refQueue = null;
    private boolean useCache = false;

    /* access modifiers changed from: protected */
    public abstract TemplateModel create(Object obj);

    /* access modifiers changed from: protected */
    public abstract boolean isCacheable(Object obj);

    protected ModelCache() {
    }

    public synchronized void setUseCache(boolean z) {
        this.useCache = z;
        if (z) {
            this.modelCache = new IdentityHashMap();
            this.refQueue = new ReferenceQueue();
        } else {
            this.modelCache = null;
            this.refQueue = null;
        }
    }

    public TemplateModel getInstance(Object obj) {
        if (obj instanceof TemplateModel) {
            return (TemplateModel) obj;
        }
        if (obj instanceof TemplateModelAdapter) {
            return ((TemplateModelAdapter) obj).getTemplateModel();
        }
        if (!this.useCache || !isCacheable(obj)) {
            return create(obj);
        }
        TemplateModel lookup = lookup(obj);
        if (lookup != null) {
            return lookup;
        }
        TemplateModel create = create(obj);
        register(create, obj);
        return create;
    }

    public void clearCache() {
        Map map = this.modelCache;
        if (map != null) {
            synchronized (map) {
                this.modelCache.clear();
            }
        }
    }

    private final TemplateModel lookup(Object obj) {
        ModelReference modelReference;
        synchronized (this.modelCache) {
            modelReference = (ModelReference) this.modelCache.get(obj);
        }
        if (modelReference != null) {
            return modelReference.getModel();
        }
        return null;
    }

    private final void register(TemplateModel templateModel, Object obj) {
        synchronized (this.modelCache) {
            while (true) {
                ModelReference modelReference = (ModelReference) this.refQueue.poll();
                if (modelReference == null) {
                    this.modelCache.put(obj, new ModelReference(templateModel, obj, this.refQueue));
                } else {
                    this.modelCache.remove(modelReference.object);
                }
            }
        }
    }

    private static final class ModelReference extends SoftReference {
        Object object;

        ModelReference(TemplateModel templateModel, Object obj, ReferenceQueue referenceQueue) {
            super(templateModel, referenceQueue);
            this.object = obj;
        }

        /* access modifiers changed from: package-private */
        public TemplateModel getModel() {
            return (TemplateModel) get();
        }
    }
}
