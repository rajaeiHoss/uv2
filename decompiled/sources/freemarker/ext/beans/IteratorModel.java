package freemarker.ext.beans;

import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorModel extends BeanModel implements TemplateModelIterator, TemplateCollectionModel {
    private boolean accessed = false;

    public IteratorModel(Iterator it, BeansWrapper beansWrapper) {
        super(it, beansWrapper);
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
        return ((Iterator) this.object).hasNext();
    }

    public TemplateModel next() throws TemplateModelException {
        try {
            return wrap(((Iterator) this.object).next());
        } catch (NoSuchElementException e) {
            throw new TemplateModelException("No more elements in the iterator.", (Exception) e);
        }
    }

    public boolean getAsBoolean() {
        return hasNext();
    }
}
