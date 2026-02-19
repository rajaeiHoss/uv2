package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateSequenceModel;
import java.lang.reflect.Array;

public class ArrayModel extends BeanModel implements TemplateCollectionModel, TemplateSequenceModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new ArrayModel(obj, (BeansWrapper) objectWrapper);
        }
    };
    /* access modifiers changed from: private */
    public int length;

    public ArrayModel(Object obj, BeansWrapper beansWrapper) {
        super(obj, beansWrapper);
        if (obj.getClass().isArray()) {
            this.length = Array.getLength(obj);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Object is not an array, it's ");
        stringBuffer.append(obj.getClass().getName());
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public TemplateModelIterator iterator() {
        return new Iterator();
    }

    public TemplateModel get(int i) throws TemplateModelException {
        try {
            return wrap(Array.get(this.object, i));
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    private class Iterator implements TemplateSequenceModel, TemplateModelIterator {
        private int position;

        private Iterator() {
            this.position = 0;
        }

        public boolean hasNext() {
            return this.position < ArrayModel.this.length;
        }

        public TemplateModel get(int i) throws TemplateModelException {
            return ArrayModel.this.get(i);
        }

        public TemplateModel next() throws TemplateModelException {
            if (this.position >= ArrayModel.this.length) {
                return null;
            }
            int i = this.position;
            this.position = i + 1;
            return get(i);
        }

        public int size() {
            return ArrayModel.this.size();
        }
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
}
