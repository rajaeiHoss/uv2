package freemarker.ext.jsp;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.StringUtil;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class JspTagModelBase {
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;
    private final Method dynaSetter;
    private final Map propertySetters = new HashMap();
    private final Class tagClass;

    protected JspTagModelBase(Class cls) throws IntrospectionException {
        Method method;
        this.tagClass = cls;
        PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(cls).getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null) {
                this.propertySetters.put(propertyDescriptor.getName(), writeMethod);
            }
        }
        try {
            Class[] clsArr = new Class[3];
            Class cls2 = class$java$lang$String;
            if (cls2 == null) {
                cls2 = class$("java.lang.String");
                class$java$lang$String = cls2;
            }
            clsArr[0] = cls2;
            Class cls3 = class$java$lang$String;
            if (cls3 == null) {
                cls3 = class$("java.lang.String");
                class$java$lang$String = cls3;
            }
            clsArr[1] = cls3;
            Class cls4 = class$java$lang$Object;
            if (cls4 == null) {
                cls4 = class$("java.lang.Object");
                class$java$lang$Object = cls4;
            }
            clsArr[2] = cls4;
            method = cls.getMethod("setDynamicAttribute", clsArr);
        } catch (NoSuchMethodException unused) {
            method = null;
        }
        this.dynaSetter = method;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public Object getTagInstance() throws IllegalAccessException, InstantiationException {
        return this.tagClass.newInstance();
    }

    /* access modifiers changed from: package-private */
    public void setupTag(Object obj, Map map, ObjectWrapper objectWrapper) throws TemplateModelException, InvocationTargetException, IllegalAccessException {
        BeansWrapper defaultInstance = objectWrapper instanceof BeansWrapper ? (BeansWrapper) objectWrapper : BeansWrapper.getDefaultInstance();
        if (map != null && !map.isEmpty()) {
            Object[] objArr = new Object[1];
            for (Map.Entry entry : map.entrySet()) {
                Object unwrap = defaultInstance.unwrap((TemplateModel) entry.getValue());
                objArr[0] = unwrap;
                Method method = (Method) this.propertySetters.get(entry.getKey());
                if (method == null) {
                    Method method2 = this.dynaSetter;
                    if (method2 != null) {
                        method2.invoke(obj, new Object[]{null, entry.getKey(), objArr[0]});
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unknown property ");
                        stringBuffer.append(StringUtil.jQuote(entry.getKey().toString()));
                        stringBuffer.append(" on instance of ");
                        stringBuffer.append(this.tagClass.getName());
                        throw new TemplateModelException(stringBuffer.toString());
                    }
                } else {
                    if (unwrap instanceof BigDecimal) {
                        objArr[0] = BeansWrapper.coerceBigDecimal((BigDecimal) unwrap, method.getParameterTypes()[0]);
                    }
                    method.invoke(obj, objArr);
                }
            }
        }
    }
}
