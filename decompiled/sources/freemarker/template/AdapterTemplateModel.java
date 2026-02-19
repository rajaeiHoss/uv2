package freemarker.template;

public interface AdapterTemplateModel extends TemplateModel {
    Object getAdaptedObject(Class cls);
}
