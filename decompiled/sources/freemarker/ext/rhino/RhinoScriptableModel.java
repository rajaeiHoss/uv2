package freemarker.ext.rhino;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.util.ModelFactory;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class RhinoScriptableModel implements TemplateHashModelEx, TemplateSequenceModel, AdapterTemplateModel, TemplateScalarModel, TemplateBooleanModel, TemplateNumberModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new RhinoScriptableModel((Scriptable) obj, (BeansWrapper) objectWrapper);
        }
    };
    static /* synthetic */ Class class$java$lang$Object;
    private final Scriptable scriptable;
    private final BeansWrapper wrapper;

    public RhinoScriptableModel(Scriptable scriptable2, BeansWrapper beansWrapper) {
        this.scriptable = scriptable2;
        this.wrapper = beansWrapper;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        Object property = ScriptableObject.getProperty(this.scriptable, str);
        if (property instanceof Function) {
            return new RhinoFunctionModel((Function) property, this.scriptable, this.wrapper);
        }
        return this.wrapper.wrap(property);
    }

    public TemplateModel get(int i) throws TemplateModelException {
        Object property = ScriptableObject.getProperty(this.scriptable, i);
        if (property instanceof Function) {
            return new RhinoFunctionModel((Function) property, this.scriptable, this.wrapper);
        }
        return this.wrapper.wrap(property);
    }

    public boolean isEmpty() {
        return this.scriptable.getIds().length == 0;
    }

    public TemplateCollectionModel keys() throws TemplateModelException {
        return (TemplateCollectionModel) this.wrapper.wrap(this.scriptable.getIds());
    }

    public int size() {
        return this.scriptable.getIds().length;
    }

    public TemplateCollectionModel values() throws TemplateModelException {
        Object[] ids = this.scriptable.getIds();
        int length = ids.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            Object obj = ids[i];
            if (obj instanceof Number) {
                objArr[i] = ScriptableObject.getProperty(this.scriptable, ((Number) obj).intValue());
            } else {
                objArr[i] = ScriptableObject.getProperty(this.scriptable, String.valueOf(obj));
            }
        }
        return (TemplateCollectionModel) this.wrapper.wrap(objArr);
    }

    public boolean getAsBoolean() {
        return Context.toBoolean(this.scriptable);
    }

    public Number getAsNumber() {
        return new Double(Context.toNumber(this.scriptable));
    }

    public String getAsString() {
        return Context.toString(this.scriptable);
    }

    /* access modifiers changed from: package-private */
    public Scriptable getScriptable() {
        return this.scriptable;
    }

    /* access modifiers changed from: package-private */
    public BeansWrapper getWrapper() {
        return this.wrapper;
    }

    public Object getAdaptedObject(Class cls) {
        try {
            return NativeJavaObject.coerceType(cls, this.scriptable);
        } catch (EvaluatorException unused) {
            Class cls2 = class$java$lang$Object;
            if (cls2 == null) {
                cls2 = class$("java.lang.Object");
                class$java$lang$Object = cls2;
            }
            return NativeJavaObject.coerceType(cls2, this.scriptable);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
