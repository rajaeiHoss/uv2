package freemarker.ext.beans;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelAdapter;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.UndeclaredThrowableException;
import java.util.AbstractList;

class SequenceAdapter extends AbstractList implements TemplateModelAdapter {
    private final TemplateSequenceModel model;
    private final BeansWrapper wrapper;

    SequenceAdapter(TemplateSequenceModel templateSequenceModel, BeansWrapper beansWrapper) {
        this.model = templateSequenceModel;
        this.wrapper = beansWrapper;
    }

    public TemplateModel getTemplateModel() {
        return this.model;
    }

    public int size() {
        try {
            return this.model.size();
        } catch (TemplateModelException e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    public Object get(int i) {
        try {
            return this.wrapper.unwrap(this.model.get(i));
        } catch (TemplateModelException e) {
            throw new UndeclaredThrowableException(e);
        }
    }
}
