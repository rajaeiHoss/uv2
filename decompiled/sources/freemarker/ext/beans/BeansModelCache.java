package freemarker.ext.beans;

import freemarker.core._ConcurrentMapFactory;
import freemarker.ext.util.ModelCache;
import freemarker.ext.util.ModelFactory;
import freemarker.template.TemplateModel;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeansModelCache extends ModelCache {
    static /* synthetic */ Class class$java$lang$Boolean;
    private final Map classToFactory;
    private final boolean classToFactoryIsConcurrent;
    private final Set mappedClassNames = new HashSet();
    private final BeansWrapper wrapper;

    BeansModelCache(BeansWrapper beansWrapper) {
        Map newMaybeConcurrentHashMap = _ConcurrentMapFactory.newMaybeConcurrentHashMap();
        this.classToFactory = newMaybeConcurrentHashMap;
        this.classToFactoryIsConcurrent = _ConcurrentMapFactory.isConcurrent(newMaybeConcurrentHashMap);
        this.wrapper = beansWrapper;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isCacheable(Object obj) {
        Class<?> cls = obj.getClass();
        Class<?> cls2 = class$java$lang$Boolean;
        if (cls2 == null) {
            cls2 = class$("java.lang.Boolean");
            class$java$lang$Boolean = cls2;
        }
        return cls != cls2;
    }

    /* access modifiers changed from: protected */
    public TemplateModel create(Object obj) {
        Class<?> cls = obj.getClass();
        ModelFactory modelFactory = this.classToFactoryIsConcurrent ? (ModelFactory) this.classToFactory.get(cls) : null;
        if (modelFactory == null) {
            synchronized (this.classToFactory) {
                ModelFactory modelFactory2 = (ModelFactory) this.classToFactory.get(cls);
                if (modelFactory2 == null) {
                    String name = cls.getName();
                    if (!this.mappedClassNames.add(name)) {
                        this.classToFactory.clear();
                        this.mappedClassNames.clear();
                        this.mappedClassNames.add(name);
                    }
                    modelFactory2 = this.wrapper.getModelFactory(cls);
                    this.classToFactory.put(cls, modelFactory2);
                }
            }
        }
        return modelFactory.create(obj, this.wrapper);
    }
}
