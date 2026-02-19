package freemarker.core;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.utility.ClassUtil;

public interface TemplateClassResolver {
    public static final TemplateClassResolver ALLOWS_NOTHING_RESOLVER = new TemplateClassResolver() {
        public Class resolve(String str, Environment environment, Template template) throws TemplateException {
            throw MessageUtil.newInstantiatingClassNotAllowedException(str, environment);
        }
    };
    public static final TemplateClassResolver SAFER_RESOLVER = new TemplateClassResolver() {
        public Class resolve(String str, Environment environment, Template template) throws TemplateException {
            Class cls;
            Class cls2;
            if (AnonymousClass4.class$freemarker$template$utility$ObjectConstructor == null) {
                cls = AnonymousClass4.class$("freemarker.template.utility.ObjectConstructor");
                AnonymousClass4.class$freemarker$template$utility$ObjectConstructor = cls;
            } else {
                cls = AnonymousClass4.class$freemarker$template$utility$ObjectConstructor;
            }
            if (!str.equals(cls.getName())) {
                if (AnonymousClass4.class$freemarker$template$utility$Execute == null) {
                    cls2 = AnonymousClass4.class$("freemarker.template.utility.Execute");
                    AnonymousClass4.class$freemarker$template$utility$Execute = cls2;
                } else {
                    cls2 = AnonymousClass4.class$freemarker$template$utility$Execute;
                }
                if (!str.equals(cls2.getName()) && !str.equals("freemarker.template.utility.JythonRuntime")) {
                    try {
                        return ClassUtil.forName(str);
                    } catch (ClassNotFoundException e) {
                        throw new _MiscTemplateException((Throwable) e, environment);
                    }
                }
            }
            throw MessageUtil.newInstantiatingClassNotAllowedException(str, environment);
        }
    };
    public static final TemplateClassResolver UNRESTRICTED_RESOLVER = new TemplateClassResolver() {
        public Class resolve(String str, Environment environment, Template template) throws TemplateException {
            try {
                return ClassUtil.forName(str);
            } catch (ClassNotFoundException e) {
                throw new _MiscTemplateException((Throwable) e, environment);
            }
        }
    };

    Class resolve(String str, Environment environment, Template template) throws TemplateException;

    /* renamed from: freemarker.core.TemplateClassResolver$4  reason: invalid class name */
    /* synthetic */ class AnonymousClass4 {
        static /* synthetic */ Class class$freemarker$template$utility$Execute;
        static /* synthetic */ Class class$freemarker$template$utility$ObjectConstructor;

        static /* synthetic */ Class class$(String str) {
            try {
                return Class.forName(str);
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
    }
}
