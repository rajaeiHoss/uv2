package freemarker.template;

public abstract class WrappingTemplateModel {
    private static ObjectWrapper defaultObjectWrapper = DefaultObjectWrapper.instance;
    private ObjectWrapper objectWrapper;

    public static void setDefaultObjectWrapper(ObjectWrapper objectWrapper2) {
        defaultObjectWrapper = objectWrapper2;
    }

    public static ObjectWrapper getDefaultObjectWrapper() {
        return defaultObjectWrapper;
    }

    protected WrappingTemplateModel() {
        this(defaultObjectWrapper);
    }

    protected WrappingTemplateModel(ObjectWrapper objectWrapper2) {
        objectWrapper2 = objectWrapper2 == null ? defaultObjectWrapper : objectWrapper2;
        this.objectWrapper = objectWrapper2;
        if (objectWrapper2 == null) {
            DefaultObjectWrapper defaultObjectWrapper2 = new DefaultObjectWrapper();
            defaultObjectWrapper = defaultObjectWrapper2;
            this.objectWrapper = defaultObjectWrapper2;
        }
    }

    public ObjectWrapper getObjectWrapper() {
        return this.objectWrapper;
    }

    public void setObjectWrapper(ObjectWrapper objectWrapper2) {
        this.objectWrapper = objectWrapper2;
    }

    /* access modifiers changed from: protected */
    public final TemplateModel wrap(Object obj) throws TemplateModelException {
        return this.objectWrapper.wrap(obj);
    }
}
