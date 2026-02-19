package freemarker.template;

public interface TemplateModelIterator {
    boolean hasNext() throws TemplateModelException;

    TemplateModel next() throws TemplateModelException;
}
