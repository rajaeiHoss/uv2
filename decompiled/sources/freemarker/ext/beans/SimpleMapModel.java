package freemarker.ext.beans;

import freemarker.core.CollectionAndSequence;
import freemarker.ext.util.ModelFactory;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.WrappingTemplateModel;
import java.util.List;
import java.util.Map;

public class SimpleMapModel extends WrappingTemplateModel implements TemplateHashModelEx, TemplateMethodModelEx, AdapterTemplateModel, WrapperTemplateModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new SimpleMapModel((Map) obj, (BeansWrapper) objectWrapper);
        }
    };
    private final Map map;

    public SimpleMapModel(Map map2, BeansWrapper beansWrapper) {
        super(beansWrapper);
        this.map = map2;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        Object obj = this.map.get(str);
        if (obj == null) {
            if (str.length() == 1) {
                Character ch = new Character(str.charAt(0));
                Object obj2 = this.map.get(ch);
                if (obj2 == null && !this.map.containsKey(str) && !this.map.containsKey(ch)) {
                    return null;
                }
                obj = obj2;
            } else if (!this.map.containsKey(str)) {
                return null;
            }
        }
        return wrap(obj);
    }

    public Object exec(List list) throws TemplateModelException {
        Object unwrap = ((BeansWrapper) getObjectWrapper()).unwrap((TemplateModel) list.get(0));
        Object obj = this.map.get(unwrap);
        if (obj != null || this.map.containsKey(unwrap)) {
            return wrap(obj);
        }
        return null;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public int size() {
        return this.map.size();
    }

    public TemplateCollectionModel keys() {
        return new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence(this.map.keySet(), getObjectWrapper()));
    }

    public TemplateCollectionModel values() {
        return new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence(this.map.values(), getObjectWrapper()));
    }

    public Object getAdaptedObject(Class cls) {
        return this.map;
    }

    public Object getWrappedObject() {
        return this.map;
    }
}
