package freemarker.ext.beans;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.LinkedHashMap;

public class _EnumModels extends ClassBasedModelFactory {
    public /* bridge */ /* synthetic */ TemplateModel get(String str) throws TemplateModelException {
        return super.get(str);
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public _EnumModels(BeansWrapper beansWrapper) {
        super(beansWrapper);
    }

    /* access modifiers changed from: protected */
    public TemplateModel createModel(Class cls) {
        Object[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : enumConstants) {
            Enum enumR = (Enum) obj;
            linkedHashMap.put(enumR.name(), enumR);
        }
        return new SimpleMapModel(linkedHashMap, getWrapper());
    }
}
