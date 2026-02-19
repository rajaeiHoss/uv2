package freemarker.template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

final class GeneralPurposeNothing implements TemplateBooleanModel, TemplateScalarModel, TemplateSequenceModel, TemplateHashModelEx, TemplateMethodModelEx {
    private static final TemplateCollectionModel EMPTY_COLLECTION = new SimpleCollection((Collection) new ArrayList(0));
    private static final TemplateModel instance = new GeneralPurposeNothing();

    public Object exec(List list) {
        return null;
    }

    public TemplateModel get(String str) {
        return null;
    }

    public boolean getAsBoolean() {
        return false;
    }

    public String getAsString() {
        return "";
    }

    public boolean isEmpty() {
        return true;
    }

    public int size() {
        return 0;
    }

    private GeneralPurposeNothing() {
    }

    static TemplateModel getInstance() {
        return instance;
    }

    public TemplateModel get(int i) throws TemplateModelException {
        throw new TemplateModelException("Empty list");
    }

    public TemplateCollectionModel keys() {
        return EMPTY_COLLECTION;
    }

    public TemplateCollectionModel values() {
        return EMPTY_COLLECTION;
    }
}
