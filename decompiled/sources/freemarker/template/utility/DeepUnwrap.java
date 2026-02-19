package freemarker.template.utility;

import freemarker.core.Environment;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import java.util.ArrayList;
import java.util.HashMap;

public class DeepUnwrap {
    private static final Class OBJECT_CLASS;
    static /* synthetic */ Class class$java$lang$Object;

    static {
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        OBJECT_CLASS = cls;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static Object unwrap(TemplateModel templateModel) throws TemplateModelException {
        return unwrap(templateModel, false);
    }

    public static Object permissiveUnwrap(TemplateModel templateModel) throws TemplateModelException {
        return unwrap(templateModel, true);
    }

    public static Object premissiveUnwrap(TemplateModel templateModel) throws TemplateModelException {
        return unwrap(templateModel, true);
    }

    private static Object unwrap(TemplateModel templateModel, boolean z) throws TemplateModelException {
        ObjectWrapper objectWrapper;
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        TemplateModel templateModel2 = null;
        if (!(currentEnvironment == null || (objectWrapper = currentEnvironment.getObjectWrapper()) == null)) {
            templateModel2 = objectWrapper.wrap((Object) null);
        }
        return unwrap(templateModel, templateModel2, z);
    }

    private static Object unwrap(TemplateModel templateModel, TemplateModel templateModel2, boolean z) throws TemplateModelException {
        if (templateModel instanceof AdapterTemplateModel) {
            return ((AdapterTemplateModel) templateModel).getAdaptedObject(OBJECT_CLASS);
        }
        if (templateModel instanceof WrapperTemplateModel) {
            return ((WrapperTemplateModel) templateModel).getWrappedObject();
        }
        if (templateModel == templateModel2) {
            return null;
        }
        if (templateModel instanceof TemplateScalarModel) {
            return ((TemplateScalarModel) templateModel).getAsString();
        }
        if (templateModel instanceof TemplateNumberModel) {
            return ((TemplateNumberModel) templateModel).getAsNumber();
        }
        if (templateModel instanceof TemplateDateModel) {
            return ((TemplateDateModel) templateModel).getAsDate();
        }
        if (templateModel instanceof TemplateBooleanModel) {
            return ((TemplateBooleanModel) templateModel).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE;
        }
        if (templateModel instanceof TemplateSequenceModel) {
            TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) templateModel;
            ArrayList arrayList = new ArrayList(templateSequenceModel.size());
            for (int i = 0; i < templateSequenceModel.size(); i++) {
                arrayList.add(unwrap(templateSequenceModel.get(i), templateModel2, z));
            }
            return arrayList;
        } else if (templateModel instanceof TemplateCollectionModel) {
            ArrayList arrayList2 = new ArrayList();
            TemplateModelIterator it = ((TemplateCollectionModel) templateModel).iterator();
            while (it.hasNext()) {
                arrayList2.add(unwrap(it.next(), templateModel2, z));
            }
            return arrayList2;
        } else if (templateModel instanceof TemplateHashModelEx) {
            TemplateHashModelEx templateHashModelEx = (TemplateHashModelEx) templateModel;
            HashMap hashMap = new HashMap();
            TemplateModelIterator it2 = templateHashModelEx.keys().iterator();
            while (it2.hasNext()) {
                String str = (String) unwrap(it2.next(), templateModel2, z);
                hashMap.put(str, unwrap(templateHashModelEx.get(str), templateModel2, z));
            }
            return hashMap;
        } else if (z) {
            return templateModel;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Cannot deep-unwrap model of type ");
            stringBuffer.append(templateModel.getClass().getName());
            throw new TemplateModelException(stringBuffer.toString());
        }
    }
}
