package freemarker.template;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class SimpleCollection extends WrappingTemplateModel implements TemplateCollectionModel, Serializable {
    private Collection collection;
    private Iterator iterator;
    /* access modifiers changed from: private */
    public boolean iteratorDirty;

    public SimpleCollection(Iterator it) {
        this.iterator = it;
    }

    public SimpleCollection(Collection collection2) {
        this.collection = collection2;
    }

    public SimpleCollection(Iterator it, ObjectWrapper objectWrapper) {
        super(objectWrapper);
        this.iterator = it;
    }

    public SimpleCollection(Collection collection2, ObjectWrapper objectWrapper) {
        super(objectWrapper);
        this.collection = collection2;
    }

    public TemplateModelIterator iterator() {
        SimpleTemplateModelIterator simpleTemplateModelIterator;
        Iterator it = this.iterator;
        if (it != null) {
            return new SimpleTemplateModelIterator(it, true);
        }
        synchronized (this.collection) {
            simpleTemplateModelIterator = new SimpleTemplateModelIterator(this.collection.iterator(), false);
        }
        return simpleTemplateModelIterator;
    }

    private class SimpleTemplateModelIterator implements TemplateModelIterator {
        private Iterator iterator;
        private boolean iteratorShared;

        SimpleTemplateModelIterator(Iterator it, boolean z) {
            this.iterator = it;
            this.iteratorShared = z;
        }

        public TemplateModel next() throws TemplateModelException {
            if (this.iteratorShared) {
                makeIteratorDirty();
            }
            if (this.iterator.hasNext()) {
                Object next = this.iterator.next();
                if (next instanceof TemplateModel) {
                    return (TemplateModel) next;
                }
                return SimpleCollection.this.wrap(next);
            }
            throw new TemplateModelException("The collection has no more elements.");
        }

        public boolean hasNext() throws TemplateModelException {
            if (this.iteratorShared) {
                makeIteratorDirty();
            }
            return this.iterator.hasNext();
        }

        private void makeIteratorDirty() throws TemplateModelException {
            synchronized (SimpleCollection.this) {
                if (!SimpleCollection.this.iteratorDirty) {
                    boolean unused = SimpleCollection.this.iteratorDirty = true;
                    this.iteratorShared = false;
                } else {
                    throw new TemplateModelException("This collection variable wraps a java.util.Iterator, thus it can be <list>-ed or <foreach>-ed only once");
                }
            }
        }
    }
}
