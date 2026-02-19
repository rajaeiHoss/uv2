package freemarker.template;

public interface TemplateHashModelEx extends TemplateHashModel {
    TemplateCollectionModel keys() throws TemplateModelException;

    int size() throws TemplateModelException;

    TemplateCollectionModel values() throws TemplateModelException;
}
