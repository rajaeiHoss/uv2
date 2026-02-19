package freemarker.debug.impl;

import freemarker.cache.CacheStorage;
import freemarker.cache.SoftCacheStorage;
import freemarker.core.Configurable;
import freemarker.core.Environment;
import freemarker.debug.DebuggedEnvironment;
import freemarker.ext.util.IdentityHashMap;
import freemarker.template.Configuration;
import freemarker.template.SimpleCollection;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.UndeclaredThrowableException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class RmiDebuggedEnvironmentImpl extends RmiDebugModelImpl implements DebuggedEnvironment {
    private static final Object idLock = new Object();
    private static long nextId = 1;
    private static Set remotes = new HashSet();
    private static final long serialVersionUID = 1;
    private static final CacheStorage storage = new SoftCacheStorage(new IdentityHashMap());
    private final long id;
    private boolean stopped = false;

    private RmiDebuggedEnvironmentImpl(Environment environment) throws RemoteException {
        super(new DebugEnvironmentModel(environment), 2048);
        synchronized (idLock) {
            long j = nextId;
            nextId = 1 + j;
            this.id = j;
        }
    }

    static synchronized Object getCachedWrapperFor(Object obj) throws RemoteException {
        Object obj2;
        int i;
        synchronized (RmiDebuggedEnvironmentImpl.class) {
            CacheStorage cacheStorage = storage;
            obj2 = cacheStorage.get(obj);
            if (obj2 == null) {
                if (obj instanceof TemplateModel) {
                    if (obj instanceof DebugConfigurationModel) {
                        i = 8192;
                    } else {
                        i = obj instanceof DebugTemplateModel ? 4096 : 0;
                    }
                    obj2 = new RmiDebugModelImpl((TemplateModel) obj, i);
                } else if (obj instanceof Environment) {
                    obj2 = new RmiDebuggedEnvironmentImpl((Environment) obj);
                } else if (obj instanceof Template) {
                    obj2 = new DebugTemplateModel((Template) obj);
                } else if (obj instanceof Configuration) {
                    obj2 = new DebugConfigurationModel((Configuration) obj);
                }
            }
            if (obj2 != null) {
                cacheStorage.put(obj, obj2);
            }
            if (obj2 instanceof Remote) {
                remotes.add(obj2);
            }
        }
        return obj2;
    }

    public void resume() {
        synchronized (this) {
            notify();
        }
    }

    public void stop() {
        this.stopped = true;
        resume();
    }

    public long getId() {
        return this.id;
    }

    /* access modifiers changed from: package-private */
    public boolean isStopped() {
        return this.stopped;
    }

    private static abstract class DebugMapModel implements TemplateHashModelEx {
        /* access modifiers changed from: package-private */
        public abstract Collection keySet();

        private DebugMapModel() {
        }

        public int size() {
            return keySet().size();
        }

        public TemplateCollectionModel keys() {
            return new SimpleCollection(keySet());
        }

        public TemplateCollectionModel values() throws TemplateModelException {
            Collection<String> keySet = keySet();
            ArrayList arrayList = new ArrayList(keySet.size());
            for (String str : keySet) {
                arrayList.add(get(str));
            }
            return new SimpleCollection((Collection) arrayList);
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        static List composeList(Collection collection, Collection collection2) {
            ArrayList arrayList = new ArrayList(collection);
            arrayList.addAll(collection2);
            Collections.sort(arrayList);
            return arrayList;
        }
    }

    private static class DebugConfigurableModel extends DebugMapModel {
        static final List KEYS = Arrays.asList(new String[]{Configurable.ARITHMETIC_ENGINE_KEY, Configurable.BOOLEAN_FORMAT_KEY, Configurable.CLASSIC_COMPATIBLE_KEY, Configurable.LOCALE_KEY, Configurable.NUMBER_FORMAT_KEY, Configurable.OBJECT_WRAPPER_KEY, Configurable.TEMPLATE_EXCEPTION_HANDLER_KEY});
        final Configurable configurable;

        DebugConfigurableModel(Configurable configurable2) {
            super();
            this.configurable = configurable2;
        }

        /* access modifiers changed from: package-private */
        public Collection keySet() {
            return KEYS;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            String setting = this.configurable.getSetting(str);
            if (setting == null) {
                return null;
            }
            return new SimpleScalar(setting);
        }
    }

    private static class DebugConfigurationModel extends DebugConfigurableModel {
        private static final List KEYS = composeList(DebugConfigurableModel.KEYS, Collections.singleton("sharedVariables"));
        private TemplateModel sharedVariables = new DebugMapModel() {
            /* access modifiers changed from: package-private */
            public Collection keySet() {
                return ((Configuration) DebugConfigurationModel.this.configurable).getSharedVariableNames();
            }

            public TemplateModel get(String str) {
                return ((Configuration) DebugConfigurationModel.this.configurable).getSharedVariable(str);
            }
        };

        DebugConfigurationModel(Configuration configuration) {
            super(configuration);
        }

        /* access modifiers changed from: package-private */
        public Collection keySet() {
            return KEYS;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            if ("sharedVariables".equals(str)) {
                return this.sharedVariables;
            }
            return super.get(str);
        }
    }

    private static class DebugTemplateModel extends DebugConfigurableModel {
        private static final List KEYS = composeList(DebugConfigurableModel.KEYS, Arrays.asList(new String[]{"configuration", "name"}));
        private final SimpleScalar name;

        DebugTemplateModel(Template template) {
            super(template);
            this.name = new SimpleScalar(template.getName());
        }

        /* access modifiers changed from: package-private */
        public Collection keySet() {
            return KEYS;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            if ("configuration".equals(str)) {
                try {
                    return (TemplateModel) RmiDebuggedEnvironmentImpl.getCachedWrapperFor(((Template) this.configurable).getConfiguration());
                } catch (RemoteException e) {
                    throw new TemplateModelException((Exception) e);
                }
            } else if ("name".equals(str)) {
                return this.name;
            } else {
                return super.get(str);
            }
        }
    }

    private static class DebugEnvironmentModel extends DebugConfigurableModel {
        private static final List KEYS = composeList(DebugConfigurableModel.KEYS, Arrays.asList(new String[]{"currentNamespace", "dataModel", "globalNamespace", "knownVariables", "mainNamespace", "template"}));
        private TemplateModel knownVariables = new DebugMapModel() {
            /* access modifiers changed from: package-private */
            public Collection keySet() {
                try {
                    return ((Environment) DebugEnvironmentModel.this.configurable).getKnownVariableNames();
                } catch (TemplateModelException e) {
                    throw new UndeclaredThrowableException(e);
                }
            }

            public TemplateModel get(String str) throws TemplateModelException {
                return ((Environment) DebugEnvironmentModel.this.configurable).getVariable(str);
            }
        };

        DebugEnvironmentModel(Environment environment) {
            super(environment);
        }

        /* access modifiers changed from: package-private */
        public Collection keySet() {
            return KEYS;
        }

        public TemplateModel get(String str) throws TemplateModelException {
            if ("currentNamespace".equals(str)) {
                return ((Environment) this.configurable).getCurrentNamespace();
            }
            if ("dataModel".equals(str)) {
                return ((Environment) this.configurable).getDataModel();
            }
            if ("globalNamespace".equals(str)) {
                return ((Environment) this.configurable).getGlobalNamespace();
            }
            if ("knownVariables".equals(str)) {
                return this.knownVariables;
            }
            if ("mainNamespace".equals(str)) {
                return ((Environment) this.configurable).getMainNamespace();
            }
            if (!"template".equals(str)) {
                return super.get(str);
            }
            try {
                return (TemplateModel) RmiDebuggedEnvironmentImpl.getCachedWrapperFor(((Environment) this.configurable).getTemplate());
            } catch (RemoteException e) {
                throw new TemplateModelException((Exception) e);
            }
        }
    }

    public static void cleanup() {
        for (Remote unexportObject : remotes) {
            try {
                UnicastRemoteObject.unexportObject(unexportObject, true);
            } catch (Exception unused) {
            }
        }
    }
}
