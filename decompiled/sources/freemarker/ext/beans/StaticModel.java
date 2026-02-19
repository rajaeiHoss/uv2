package freemarker.ext.beans;

import freemarker.log.Logger;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

final class StaticModel implements TemplateHashModelEx {
    private static final Logger logger = Logger.getLogger("freemarker.beans");
    private final Class clazz;
    private final Map map = new HashMap();
    private final BeansWrapper wrapper;

    StaticModel(Class cls, BeansWrapper beansWrapper) throws TemplateModelException {
        this.clazz = cls;
        this.wrapper = beansWrapper;
        populate();
    }

    public TemplateModel get(String str) throws TemplateModelException {
        Object obj = this.map.get(str);
        if (obj instanceof TemplateModel) {
            return (TemplateModel) obj;
        }
        if (obj instanceof Field) {
            try {
                return this.wrapper.getOuterIdentity().wrap(((Field) obj).get((Object) null));
            } catch (IllegalAccessException unused) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Illegal access for field ");
                stringBuffer.append(str);
                stringBuffer.append(" of class ");
                stringBuffer.append(this.clazz.getName());
                throw new TemplateModelException(stringBuffer.toString());
            }
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("No such key: ");
            stringBuffer2.append(str);
            stringBuffer2.append(" in class ");
            stringBuffer2.append(this.clazz.getName());
            throw new TemplateModelException(stringBuffer2.toString());
        }
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public int size() {
        return this.map.size();
    }

    public TemplateCollectionModel keys() throws TemplateModelException {
        return (TemplateCollectionModel) this.wrapper.getOuterIdentity().wrap(this.map.keySet());
    }

    public TemplateCollectionModel values() throws TemplateModelException {
        return (TemplateCollectionModel) this.wrapper.getOuterIdentity().wrap(this.map.values());
    }

    private void populate() throws TemplateModelException {
        if (!Modifier.isPublic(this.clazz.getModifiers())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Can't wrap the non-public class ");
            stringBuffer.append(this.clazz.getName());
            throw new TemplateModelException(stringBuffer.toString());
        } else if (this.wrapper.getExposureLevel() != 3) {
            Field[] fields = this.clazz.getFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                    if (Modifier.isFinal(modifiers)) {
                        try {
                            this.map.put(field.getName(), this.wrapper.getOuterIdentity().wrap(field.get((Object) null)));
                        } catch (IllegalAccessException unused) {
                        }
                    } else {
                        this.map.put(field.getName(), field);
                    }
                }
            }
            if (this.wrapper.getExposureLevel() < 2) {
                Method[] methods = this.clazz.getMethods();
                for (Method method : methods) {
                    int modifiers2 = method.getModifiers();
                    if (Modifier.isPublic(modifiers2) && Modifier.isStatic(modifiers2) && this.wrapper.isSafeMethod(method)) {
                        String name = method.getName();
                        Object obj = this.map.get(name);
                        if (obj instanceof Method) {
                            OverloadedMethods overloadedMethods = new OverloadedMethods(this.wrapper);
                            overloadedMethods.addMember((Method) obj);
                            overloadedMethods.addMember(method);
                            this.map.put(name, overloadedMethods);
                        } else if (obj instanceof OverloadedMethods) {
                            ((OverloadedMethods) obj).addMember(method);
                        } else {
                            if (obj != null) {
                                Logger logger2 = logger;
                                if (logger2.isInfoEnabled()) {
                                    StringBuffer stringBuffer2 = new StringBuffer();
                                    stringBuffer2.append("Overwriting value [");
                                    stringBuffer2.append(obj);
                                    stringBuffer2.append("] for ");
                                    stringBuffer2.append(" key '");
                                    stringBuffer2.append(name);
                                    stringBuffer2.append("' with [");
                                    stringBuffer2.append(method);
                                    stringBuffer2.append("] in static model for ");
                                    stringBuffer2.append(this.clazz.getName());
                                    logger2.info(stringBuffer2.toString());
                                }
                            }
                            this.map.put(name, method);
                        }
                    }
                }
                for (Map.Entry entry : this.map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof Method) {
                        Method method2 = (Method) value;
                        entry.setValue(new SimpleMethodModel((Object) null, method2, method2.getParameterTypes(), this.wrapper));
                    } else if (value instanceof OverloadedMethods) {
                        entry.setValue(new OverloadedMethodsModel((Object) null, (OverloadedMethods) value));
                    }
                }
            }
        }
    }
}
