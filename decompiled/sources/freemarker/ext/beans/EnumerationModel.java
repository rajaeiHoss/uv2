package freemarker.ext.beans;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class EnumerationModel extends BeanModel implements TemplateModelIterator, TemplateCollectionModel {
    private boolean accessed = false;

    public EnumerationModel(Enumeration enumeration, BeansWrapper beansWrapper) {
        super(enumeration, beansWrapper);
    }

    public TemplateModelIterator iterator() throws TemplateModelException {
        synchronized (this) {
            if (!this.accessed) {
                this.accessed = true;
            } else {
                throw new TemplateModelException("This collection is stateful and can not be iterated over the second time.");
            }
        }
        return this;
    }

    public boolean hasNext() {
        return ((Enumeration) this.object).hasMoreElements();
    }

    public TemplateModel next() throws TemplateModelException {
        try {
            return wrap(((Enumeration) this.object).nextElement());
        } catch (NoSuchElementException unused) {
            throw new TemplateModelException("No more elements in the enumeration.");
        }
    }

    public boolean getAsBoolean() {
        return hasNext();
    }
}
