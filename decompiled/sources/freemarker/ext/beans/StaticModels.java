package freemarker.ext.beans;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

class StaticModels extends ClassBasedModelFactory {
    StaticModels(BeansWrapper beansWrapper) {
        super(beansWrapper);
    }

    /* access modifiers changed from: protected */
    public TemplateModel createModel(Class cls) throws TemplateModelException {
        return new StaticModel(cls, getWrapper());
    }
}
