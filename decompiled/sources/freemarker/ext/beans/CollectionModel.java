package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateSequenceModel;
import java.util.Collection;
import java.util.List;

public class CollectionModel extends StringModel implements TemplateCollectionModel, TemplateSequenceModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new CollectionModel((Collection) obj, (BeansWrapper) objectWrapper);
        }
    };

    public CollectionModel(Collection collection, BeansWrapper beansWrapper) {
        super(collection, beansWrapper);
    }

    public TemplateModel get(int i) throws TemplateModelException {
        if (this.object instanceof List) {
            try {
                return wrap(((List) this.object).get(i));
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Underlying collection is not a list, it's ");
            stringBuffer.append(this.object.getClass().getName());
            throw new TemplateModelException(stringBuffer.toString());
        }
    }

    public boolean getSupportsIndexedAccess() {
        return this.object instanceof List;
    }

    public TemplateModelIterator iterator() {
        return new IteratorModel(((Collection) this.object).iterator(), this.wrapper);
    }

    public int size() {
        return ((Collection) this.object).size();
    }
}
