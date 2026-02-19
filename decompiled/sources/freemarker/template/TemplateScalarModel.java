package freemarker.template;

public interface TemplateScalarModel extends TemplateModel {
    public static final TemplateModel EMPTY_STRING = new SimpleScalar("");

    String getAsString() throws TemplateModelException;
}
