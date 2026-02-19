package freemarker.ext.beans;

import freemarker.ext.util.ModelFactory;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapModel extends StringModel implements TemplateMethodModelEx {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new MapModel((Map) obj, (BeansWrapper) objectWrapper);
        }
    };

    public MapModel(Map map, BeansWrapper beansWrapper) {
        super(map, beansWrapper);
    }

    public Object exec(List list) throws TemplateModelException {
        return wrap(((Map) this.object).get(unwrap((TemplateModel) list.get(0))));
    }

    /* access modifiers changed from: protected */
    public TemplateModel invokeGenericGet(Map map, Class cls, String str) throws TemplateModelException {
        Map map2 = (Map) this.object;
        Object obj = map2.get(str);
        if (obj == null) {
            if (str.length() == 1) {
                Character ch = new Character(str.charAt(0));
                Object obj2 = map2.get(ch);
                if (obj2 == null && !map2.containsKey(str) && !map2.containsKey(ch)) {
                    return UNKNOWN;
                }
                obj = obj2;
            } else if (!map2.containsKey(str)) {
                return UNKNOWN;
            }
        }
        return wrap(obj);
    }

    public boolean isEmpty() {
        return ((Map) this.object).isEmpty() && super.isEmpty();
    }

    public int size() {
        return keySet().size();
    }

    /* access modifiers changed from: protected */
    public Set keySet() {
        Set keySet = super.keySet();
        keySet.addAll(((Map) this.object).keySet());
        return keySet;
    }
}
