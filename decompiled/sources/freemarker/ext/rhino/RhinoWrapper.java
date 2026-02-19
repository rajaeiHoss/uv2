package freemarker.ext.rhino;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.util.ModelFactory;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.UndeclaredThrowableException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import org.mozilla.javascript.UniqueTag;
import org.mozilla.javascript.Wrapper;

public class RhinoWrapper extends BeansWrapper {
    private static final Object UNDEFINED_INSTANCE;
    static /* synthetic */ Class class$org$mozilla$javascript$Scriptable;
    static /* synthetic */ Class class$org$mozilla$javascript$Undefined;

    static {
        try {
            UNDEFINED_INSTANCE = AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws Exception {
                    Class cls;
                    if (RhinoWrapper.class$org$mozilla$javascript$Undefined == null) {
                        cls = RhinoWrapper.class$("org.mozilla.javascript.Undefined");
                        RhinoWrapper.class$org$mozilla$javascript$Undefined = cls;
                    } else {
                        cls = RhinoWrapper.class$org$mozilla$javascript$Undefined;
                    }
                    return cls.getField("instance").get((Object) null);
                }
            });
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new UndeclaredThrowableException(e2);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public TemplateModel wrap(Object obj) throws TemplateModelException {
        if (obj == UNDEFINED_INSTANCE || obj == UniqueTag.NOT_FOUND) {
            return null;
        }
        if (obj == UniqueTag.NULL_VALUE) {
            return super.wrap((Object) null);
        }
        if (obj instanceof Wrapper) {
            obj = ((Wrapper) obj).unwrap();
        }
        return super.wrap(obj);
    }

    /* access modifiers changed from: protected */
    public ModelFactory getModelFactory(Class cls) {
        Class cls2 = class$org$mozilla$javascript$Scriptable;
        if (cls2 == null) {
            cls2 = class$("org.mozilla.javascript.Scriptable");
            class$org$mozilla$javascript$Scriptable = cls2;
        }
        if (cls2.isAssignableFrom(cls)) {
            return RhinoScriptableModel.FACTORY;
        }
        return super.getModelFactory(cls);
    }
}
