package freemarker.template.utility;

import com.google.firebase.analytics.FirebaseAnalytics;
import freemarker.core.Macro;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BooleanModel;
import freemarker.ext.beans.CollectionModel;
import freemarker.ext.beans.DateModel;
import freemarker.ext.beans.EnumerationModel;
import freemarker.ext.beans.IteratorModel;
import freemarker.ext.beans.MapModel;
import freemarker.ext.beans.NumberModel;
import freemarker.ext.beans.OverloadedMethodsModel;
import freemarker.ext.beans.SimpleMethodModel;
import freemarker.ext.beans.StringModel;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import java.util.HashSet;
import java.util.Set;
import org.jivesoftware.smackx.FormField;

public class ClassUtil {
    static /* synthetic */ Class class$freemarker$core$Environment$Namespace;
    static /* synthetic */ Class class$freemarker$template$TemplateBooleanModel;
    static /* synthetic */ Class class$freemarker$template$TemplateCollectionModel;
    static /* synthetic */ Class class$freemarker$template$TemplateDateModel;
    static /* synthetic */ Class class$freemarker$template$TemplateDirectiveModel;
    static /* synthetic */ Class class$freemarker$template$TemplateHashModel;
    static /* synthetic */ Class class$freemarker$template$TemplateHashModelEx;
    static /* synthetic */ Class class$freemarker$template$TemplateMethodModel;
    static /* synthetic */ Class class$freemarker$template$TemplateMethodModelEx;
    static /* synthetic */ Class class$freemarker$template$TemplateModelIterator;
    static /* synthetic */ Class class$freemarker$template$TemplateNodeModel;
    static /* synthetic */ Class class$freemarker$template$TemplateNumberModel;
    static /* synthetic */ Class class$freemarker$template$TemplateScalarModel;
    static /* synthetic */ Class class$freemarker$template$TemplateSequenceModel;
    static /* synthetic */ Class class$freemarker$template$TemplateTransformModel;
    static /* synthetic */ Class class$java$lang$Object;

    private ClassUtil() {
    }

    public static Class forName(String str) throws ClassNotFoundException {
        try {
            return Class.forName(str, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException | SecurityException unused) {
            return Class.forName(str);
        }
    }

    public static String getShortClassName(Class cls) {
        return getShortClassName(cls, false);
    }

    public static String getShortClassName(Class cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (cls.isArray()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getShortClassName(cls.getComponentType()));
            stringBuffer.append("[]");
            return stringBuffer.toString();
        }
        String name = cls.getName();
        if (name.startsWith("java.lang.") || name.startsWith("java.util.")) {
            return name.substring(10);
        }
        if (!z) {
            return name;
        }
        if (name.startsWith("freemarker.template.")) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("f.t");
            stringBuffer2.append(name.substring(19));
            return stringBuffer2.toString();
        } else if (name.startsWith("freemarker.ext.beans.")) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("f.e.b");
            stringBuffer3.append(name.substring(20));
            return stringBuffer3.toString();
        } else if (name.startsWith("freemarker.core.")) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("f.c");
            stringBuffer4.append(name.substring(15));
            return stringBuffer4.toString();
        } else if (name.startsWith("freemarker.ext.")) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("f.e");
            stringBuffer5.append(name.substring(14));
            return stringBuffer5.toString();
        } else if (!name.startsWith("freemarker.")) {
            return name;
        } else {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("f");
            stringBuffer6.append(name.substring(10));
            return stringBuffer6.toString();
        }
    }

    public static String getShortClassNameOfObject(Object obj) {
        return getShortClassNameOfObject(obj, false);
    }

    public static String getShortClassNameOfObject(Object obj, boolean z) {
        return obj == null ? "Null" : getShortClassName(obj.getClass(), z);
    }

    private static Class getPrimaryTemplateModelInterface(TemplateModel templateModel) {
        if (!(templateModel instanceof BeanModel)) {
            return null;
        }
        if (templateModel instanceof CollectionModel) {
            Class cls = class$freemarker$template$TemplateSequenceModel;
            if (cls != null) {
                return cls;
            }
            Class class$ = class$("freemarker.template.TemplateSequenceModel");
            class$freemarker$template$TemplateSequenceModel = class$;
            return class$;
        } else if ((templateModel instanceof IteratorModel) || (templateModel instanceof EnumerationModel)) {
            Class cls2 = class$freemarker$template$TemplateCollectionModel;
            if (cls2 != null) {
                return cls2;
            }
            Class class$2 = class$("freemarker.template.TemplateCollectionModel");
            class$freemarker$template$TemplateCollectionModel = class$2;
            return class$2;
        } else if (templateModel instanceof MapModel) {
            Class cls3 = class$freemarker$template$TemplateHashModelEx;
            if (cls3 != null) {
                return cls3;
            }
            Class class$3 = class$("freemarker.template.TemplateHashModelEx");
            class$freemarker$template$TemplateHashModelEx = class$3;
            return class$3;
        } else if (templateModel instanceof NumberModel) {
            Class cls4 = class$freemarker$template$TemplateNumberModel;
            if (cls4 != null) {
                return cls4;
            }
            Class class$4 = class$("freemarker.template.TemplateNumberModel");
            class$freemarker$template$TemplateNumberModel = class$4;
            return class$4;
        } else if (templateModel instanceof BooleanModel) {
            Class cls5 = class$freemarker$template$TemplateBooleanModel;
            if (cls5 != null) {
                return cls5;
            }
            Class class$5 = class$("freemarker.template.TemplateBooleanModel");
            class$freemarker$template$TemplateBooleanModel = class$5;
            return class$5;
        } else if (templateModel instanceof DateModel) {
            Class cls6 = class$freemarker$template$TemplateDateModel;
            if (cls6 != null) {
                return cls6;
            }
            Class class$6 = class$("freemarker.template.TemplateDateModel");
            class$freemarker$template$TemplateDateModel = class$6;
            return class$6;
        } else if ((templateModel instanceof SimpleMethodModel) || (templateModel instanceof OverloadedMethodsModel)) {
            Class cls7 = class$freemarker$template$TemplateMethodModelEx;
            if (cls7 != null) {
                return cls7;
            }
            Class class$7 = class$("freemarker.template.TemplateMethodModelEx");
            class$freemarker$template$TemplateMethodModelEx = class$7;
            return class$7;
        } else if (!(templateModel instanceof StringModel)) {
            return null;
        } else {
            if (((BeanModel) templateModel).getWrappedObject() instanceof String) {
                Class cls8 = class$freemarker$template$TemplateScalarModel;
                if (cls8 != null) {
                    return cls8;
                }
                Class class$8 = class$("freemarker.template.TemplateScalarModel");
                class$freemarker$template$TemplateScalarModel = class$8;
                return class$8;
            } else if (!(templateModel instanceof TemplateHashModelEx)) {
                return null;
            } else {
                Class cls9 = class$freemarker$template$TemplateHashModelEx;
                if (cls9 != null) {
                    return cls9;
                }
                Class class$9 = class$("freemarker.template.TemplateHashModelEx");
                class$freemarker$template$TemplateHashModelEx = class$9;
                return class$9;
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static void appendTemplateModelTypeName(StringBuffer stringBuffer, Set set, Class cls) {
        Class cls2 = class$freemarker$template$TemplateNodeModel;
        if (cls2 == null) {
            cls2 = class$("freemarker.template.TemplateNodeModel");
            class$freemarker$template$TemplateNodeModel = cls2;
        }
        if (cls2.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "node");
        }
        Class cls3 = class$freemarker$template$TemplateDirectiveModel;
        if (cls3 == null) {
            cls3 = class$("freemarker.template.TemplateDirectiveModel");
            class$freemarker$template$TemplateDirectiveModel = cls3;
        }
        if (cls3.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "directive");
        } else {
            Class cls4 = class$freemarker$template$TemplateTransformModel;
            if (cls4 == null) {
                cls4 = class$("freemarker.template.TemplateTransformModel");
                class$freemarker$template$TemplateTransformModel = cls4;
            }
            if (cls4.isAssignableFrom(cls)) {
                appendTypeName(stringBuffer, set, "transform");
            }
        }
        Class cls5 = class$freemarker$template$TemplateSequenceModel;
        if (cls5 == null) {
            cls5 = class$("freemarker.template.TemplateSequenceModel");
            class$freemarker$template$TemplateSequenceModel = cls5;
        }
        if (cls5.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "sequence");
        } else {
            Class cls6 = class$freemarker$template$TemplateCollectionModel;
            if (cls6 == null) {
                cls6 = class$("freemarker.template.TemplateCollectionModel");
                class$freemarker$template$TemplateCollectionModel = cls6;
            }
            if (cls6.isAssignableFrom(cls)) {
                appendTypeName(stringBuffer, set, "collection");
            } else {
                Class cls7 = class$freemarker$template$TemplateModelIterator;
                if (cls7 == null) {
                    cls7 = class$("freemarker.template.TemplateModelIterator");
                    class$freemarker$template$TemplateModelIterator = cls7;
                }
                if (cls7.isAssignableFrom(cls)) {
                    appendTypeName(stringBuffer, set, "iterator");
                }
            }
        }
        Class cls8 = class$freemarker$template$TemplateMethodModel;
        if (cls8 == null) {
            cls8 = class$("freemarker.template.TemplateMethodModel");
            class$freemarker$template$TemplateMethodModel = cls8;
        }
        if (cls8.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, FirebaseAnalytics.Param.METHOD);
        }
        Class cls9 = class$freemarker$core$Environment$Namespace;
        if (cls9 == null) {
            cls9 = class$("freemarker.core.Environment$Namespace");
            class$freemarker$core$Environment$Namespace = cls9;
        }
        if (cls9.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "namespace");
        } else {
            Class cls10 = class$freemarker$template$TemplateHashModelEx;
            if (cls10 == null) {
                cls10 = class$("freemarker.template.TemplateHashModelEx");
                class$freemarker$template$TemplateHashModelEx = cls10;
            }
            if (cls10.isAssignableFrom(cls)) {
                appendTypeName(stringBuffer, set, "extended_hash");
            } else {
                Class cls11 = class$freemarker$template$TemplateHashModel;
                if (cls11 == null) {
                    cls11 = class$("freemarker.template.TemplateHashModel");
                    class$freemarker$template$TemplateHashModel = cls11;
                }
                if (cls11.isAssignableFrom(cls)) {
                    appendTypeName(stringBuffer, set, "hash");
                }
            }
        }
        Class cls12 = class$freemarker$template$TemplateNumberModel;
        if (cls12 == null) {
            cls12 = class$("freemarker.template.TemplateNumberModel");
            class$freemarker$template$TemplateNumberModel = cls12;
        }
        if (cls12.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "number");
        }
        Class cls13 = class$freemarker$template$TemplateDateModel;
        if (cls13 == null) {
            cls13 = class$("freemarker.template.TemplateDateModel");
            class$freemarker$template$TemplateDateModel = cls13;
        }
        if (cls13.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "date");
        }
        Class cls14 = class$freemarker$template$TemplateBooleanModel;
        if (cls14 == null) {
            cls14 = class$("freemarker.template.TemplateBooleanModel");
            class$freemarker$template$TemplateBooleanModel = cls14;
        }
        if (cls14.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, FormField.TYPE_BOOLEAN);
        }
        Class cls15 = class$freemarker$template$TemplateScalarModel;
        if (cls15 == null) {
            cls15 = class$("freemarker.template.TemplateScalarModel");
            class$freemarker$template$TemplateScalarModel = cls15;
        }
        if (cls15.isAssignableFrom(cls)) {
            appendTypeName(stringBuffer, set, "string");
        }
    }

    private static Class getUnwrappedClass(TemplateModel templateModel) {
        Object obj;
        try {
            if (templateModel instanceof WrapperTemplateModel) {
                obj = ((WrapperTemplateModel) templateModel).getWrappedObject();
            } else {
                if (templateModel instanceof AdapterTemplateModel) {
                    AdapterTemplateModel adapterTemplateModel = (AdapterTemplateModel) templateModel;
                    Class cls = class$java$lang$Object;
                    if (cls == null) {
                        cls = class$("java.lang.Object");
                        class$java$lang$Object = cls;
                    }
                    obj = adapterTemplateModel.getAdaptedObject(cls);
                }
                obj = null;
            }
        } catch (Throwable unused) {
        }
        if (obj != null) {
            return obj.getClass();
        }
        return null;
    }

    private static void appendTypeName(StringBuffer stringBuffer, Set set, String str) {
        if (!set.contains(str)) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append("+");
            }
            stringBuffer.append(str);
            set.add(str);
        }
    }

    public static String getFTLTypeDescription(TemplateModel templateModel) {
        if (templateModel == null) {
            return "Null";
        }
        HashSet hashSet = new HashSet();
        StringBuffer stringBuffer = new StringBuffer();
        Class primaryTemplateModelInterface = getPrimaryTemplateModelInterface(templateModel);
        if (primaryTemplateModelInterface != null) {
            appendTemplateModelTypeName(stringBuffer, hashSet, primaryTemplateModelInterface);
        }
        if (templateModel instanceof Macro) {
            appendTypeName(stringBuffer, hashSet, ((Macro) templateModel).isFunction() ? "function" : "macro");
        }
        appendTemplateModelTypeName(stringBuffer, hashSet, templateModel.getClass());
        Class unwrappedClass = getUnwrappedClass(templateModel);
        String shortClassName = unwrappedClass != null ? getShortClassName(unwrappedClass, true) : null;
        stringBuffer.append(" (");
        String shortClassName2 = getShortClassName(templateModel.getClass(), true);
        if (shortClassName == null) {
            stringBuffer.append("wrapper: ");
            stringBuffer.append(shortClassName2);
        } else {
            stringBuffer.append(shortClassName);
            stringBuffer.append(" wrapped into ");
            stringBuffer.append(shortClassName2);
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
