package freemarker.template;

public class SimpleObjectWrapper extends DefaultObjectWrapper {
    static final SimpleObjectWrapper instance = new SimpleObjectWrapper();

    /* access modifiers changed from: protected */
    public TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Don't know how to present an object of this type to a template: ");
        stringBuffer.append(obj.getClass().getName());
        throw new TemplateModelException(stringBuffer.toString());
    }
}
