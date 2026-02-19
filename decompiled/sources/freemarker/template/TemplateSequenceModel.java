package freemarker.template;

public interface TemplateSequenceModel extends TemplateModel {
    TemplateModel get(int i) throws TemplateModelException;

    int size() throws TemplateModelException;
}
