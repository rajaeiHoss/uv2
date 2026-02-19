package freemarker.core;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.List;

class NewBI extends BuiltIn {
    static final Class BEAN_MODEL_CLASS;
    static Class JYTHON_MODEL_CLASS;
    static /* synthetic */ Class class$freemarker$ext$beans$BeanModel;
    static /* synthetic */ Class class$freemarker$template$TemplateModel;

    NewBI() {
    }

    static {
        Class cls = class$freemarker$ext$beans$BeanModel;
        if (cls == null) {
            cls = class$("freemarker.ext.beans.BeanModel");
            class$freemarker$ext$beans$BeanModel = cls;
        }
        BEAN_MODEL_CLASS = cls;
        try {
            JYTHON_MODEL_CLASS = Class.forName("freemarker.ext.jython.JythonModel");
        } catch (Throwable unused) {
            JYTHON_MODEL_CLASS = null;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public TemplateModel _eval(Environment environment) throws TemplateException {
        return new ConstructorFunction(this.target.evalAndCoerceToString(environment), environment, this.target.getTemplate());
    }

    class ConstructorFunction implements TemplateMethodModelEx {
        private final Class cl;
        private final Environment env;

        public ConstructorFunction(String str, Environment environment, Template template) throws TemplateException {
            Class cls;
            this.env = environment;
            Class resolve = environment.getNewBuiltinClassResolver().resolve(str, environment, template);
            this.cl = resolve;
            if (NewBI.class$freemarker$template$TemplateModel == null) {
                cls = NewBI.class$("freemarker.template.TemplateModel");
                NewBI.class$freemarker$template$TemplateModel = cls;
            } else {
                cls = NewBI.class$freemarker$template$TemplateModel;
            }
            if (!cls.isAssignableFrom(resolve)) {
                throw new _MiscTemplateException((Expression) NewBI.this, environment, new Object[]{"Class ", resolve.getName(), " does not implement freemarker.template.TemplateModel"});
            } else if (NewBI.BEAN_MODEL_CLASS.isAssignableFrom(resolve)) {
                throw new _MiscTemplateException((Expression) NewBI.this, environment, new Object[]{"Bean Models cannot be instantiated using the ?", NewBI.this.key, " built-in"});
            } else if (NewBI.JYTHON_MODEL_CLASS != null && NewBI.JYTHON_MODEL_CLASS.isAssignableFrom(resolve)) {
                throw new _MiscTemplateException((Expression) NewBI.this, environment, new Object[]{"Jython Models cannot be instantiated using the ?", NewBI.this.key, " built-in"});
            }
        }

        public Object exec(List list) throws TemplateModelException {
            ObjectWrapper objectWrapper = this.env.getObjectWrapper();
            return (objectWrapper instanceof BeansWrapper ? (BeansWrapper) objectWrapper : BeansWrapper.getDefaultInstance()).newInstance(this.cl, list);
        }
    }
}
