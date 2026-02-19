package freemarker.template;

import freemarker.ext.beans.BeansWrapper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleSequence extends WrappingTemplateModel implements TemplateSequenceModel, Serializable {
    protected final List list;
    private List unwrappedList;

    public SimpleSequence() {
        this((ObjectWrapper) null);
    }

    public SimpleSequence(int i) {
        this.list = new ArrayList(i);
    }

    public SimpleSequence(Collection collection) {
        this(collection, (ObjectWrapper) null);
    }

    public SimpleSequence(TemplateCollectionModel templateCollectionModel) throws TemplateModelException {
        ArrayList arrayList = new ArrayList();
        TemplateModelIterator it = templateCollectionModel.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        arrayList.trimToSize();
        this.list = arrayList;
    }

    public SimpleSequence(ObjectWrapper objectWrapper) {
        super(objectWrapper);
        this.list = new ArrayList();
    }

    public SimpleSequence(Collection collection, ObjectWrapper objectWrapper) {
        super(objectWrapper);
        this.list = new ArrayList(collection);
    }

    public void add(Object obj) {
        this.list.add(obj);
        this.unwrappedList = null;
    }

    public void add(boolean z) {
        if (z) {
            add((Object) TemplateBooleanModel.TRUE);
        } else {
            add((Object) TemplateBooleanModel.FALSE);
        }
    }

    public List toList() throws TemplateModelException {
        if (this.unwrappedList == null) {
            Class<?> cls = this.list.getClass();
            try {
                List list2 = (List) cls.newInstance();
                BeansWrapper defaultInstance = BeansWrapper.getDefaultInstance();
                for (int i = 0; i < this.list.size(); i++) {
                    Object obj = this.list.get(i);
                    if (obj instanceof TemplateModel) {
                        obj = defaultInstance.unwrap((TemplateModel) obj);
                    }
                    list2.add(obj);
                }
                this.unwrappedList = list2;
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error instantiating an object of type ");
                stringBuffer.append(cls.getName());
                stringBuffer.append("\n");
                stringBuffer.append(e.getMessage());
                throw new TemplateModelException(stringBuffer.toString());
            }
        }
        return this.unwrappedList;
    }

    public TemplateModel get(int i) throws TemplateModelException {
        try {
            Object obj = this.list.get(i);
            if (obj instanceof TemplateModel) {
                return (TemplateModel) obj;
            }
            TemplateModel wrap = wrap(obj);
            this.list.set(i, wrap);
            return wrap;
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }

    public int size() {
        return this.list.size();
    }

    public SimpleSequence synchronizedWrapper() {
        return new SynchronizedSequence();
    }

    public String toString() {
        return this.list.toString();
    }

    private class SynchronizedSequence extends SimpleSequence {
        private SynchronizedSequence() {
        }

        public void add(Object obj) {
            synchronized (SimpleSequence.this) {
                SimpleSequence.this.add(obj);
            }
        }

        public TemplateModel get(int i) throws TemplateModelException {
            TemplateModel templateModel;
            synchronized (SimpleSequence.this) {
                templateModel = SimpleSequence.this.get(i);
            }
            return templateModel;
        }

        public int size() {
            int size;
            synchronized (SimpleSequence.this) {
                size = SimpleSequence.this.size();
            }
            return size;
        }

        public List toList() throws TemplateModelException {
            List list;
            synchronized (SimpleSequence.this) {
                list = SimpleSequence.this.toList();
            }
            return list;
        }
    }
}
