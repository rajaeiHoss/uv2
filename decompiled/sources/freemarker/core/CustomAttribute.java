package freemarker.core;

import freemarker.template.Template;

public class CustomAttribute {
    public static final int SCOPE_CONFIGURATION = 2;
    public static final int SCOPE_ENVIRONMENT = 0;
    public static final int SCOPE_TEMPLATE = 1;
    private final Object key = new Object();
    private final int scope;

    /* access modifiers changed from: protected */
    public Object create() {
        return null;
    }

    public CustomAttribute(int i) {
        if (i == 0 || i == 1 || i == 2) {
            this.scope = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public final Object get() {
        return getScopeConfigurable().getCustomAttribute(this.key, this);
    }

    public final Object get(Template template) {
        if (this.scope == 1) {
            return template.getCustomAttribute(this.key, this);
        }
        throw new UnsupportedOperationException("This is not a template-scope attribute");
    }

    public final void set(Object obj) {
        getScopeConfigurable().setCustomAttribute(this.key, obj);
    }

    public final void set(Object obj, Template template) {
        if (this.scope == 1) {
            template.setCustomAttribute(this.key, obj);
            return;
        }
        throw new UnsupportedOperationException("This is not a template-scope attribute");
    }

    private Configurable getScopeConfigurable() {
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        if (currentEnvironment != null) {
            int i = this.scope;
            if (i == 0) {
                return currentEnvironment;
            }
            if (i == 1) {
                return currentEnvironment.getParent();
            }
            if (i == 2) {
                return currentEnvironment.getParent().getParent();
            }
            throw new Error();
        }
        throw new IllegalStateException("No current environment");
    }
}
