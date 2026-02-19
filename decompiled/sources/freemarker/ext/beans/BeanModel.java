package freemarker.ext.beans;

import freemarker.core.CollectionAndSequence;
import freemarker.ext.util.ModelFactory;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.log.Logger;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanModel implements TemplateHashModelEx, AdapterTemplateModel, WrapperTemplateModel {
    static final ModelFactory FACTORY = new ModelFactory() {
        public TemplateModel create(Object obj, ObjectWrapper objectWrapper) {
            return new BeanModel(obj, (BeansWrapper) objectWrapper);
        }
    };
    static final TemplateModel UNKNOWN = new SimpleScalar("UNKNOWN");
    private static final Logger logger = Logger.getLogger("freemarker.beans");
    private HashMap memberMap;
    protected final Object object;
    protected final BeansWrapper wrapper;

    public BeanModel(Object obj, BeansWrapper beansWrapper) {
        this.object = obj;
        this.wrapper = beansWrapper;
        if (obj != null) {
            beansWrapper.getClassIntrospectionData(obj.getClass());
        }
    }

    public TemplateModel get(String str) throws TemplateModelException {
        TemplateModel templateModel;
        Class<?> cls = this.object.getClass();
        Map classIntrospectionData = this.wrapper.getClassIntrospectionData(cls);
        try {
            if (this.wrapper.isMethodsShadowItems()) {
                Object obj = classIntrospectionData.get(str);
                templateModel = obj != null ? invokeThroughDescriptor(obj, classIntrospectionData) : invokeGenericGet(classIntrospectionData, cls, str);
            } else {
                TemplateModel invokeGenericGet = invokeGenericGet(classIntrospectionData, cls, str);
                TemplateModel wrap = this.wrapper.wrap((Object) null);
                if (invokeGenericGet != wrap && invokeGenericGet != UNKNOWN) {
                    return invokeGenericGet;
                }
                Object obj2 = classIntrospectionData.get(str);
                if (obj2 != null) {
                    TemplateModel invokeThroughDescriptor = invokeThroughDescriptor(obj2, classIntrospectionData);
                    templateModel = (invokeThroughDescriptor == UNKNOWN && invokeGenericGet == wrap) ? wrap : invokeThroughDescriptor;
                } else {
                    templateModel = null;
                }
            }
            if (templateModel != UNKNOWN) {
                return templateModel;
            }
            if (!this.wrapper.isStrict()) {
                if (logger.isDebugEnabled()) {
                    logNoSuchKey(str, classIntrospectionData);
                }
                return this.wrapper.wrap((Object) null);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No such bean property: ");
            stringBuffer.append(str);
            throw new InvalidPropertyException(stringBuffer.toString());
        } catch (TemplateModelException e) {
            throw e;
        } catch (Exception e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("get(");
            stringBuffer2.append(str);
            stringBuffer2.append(") failed on ");
            stringBuffer2.append("instance of ");
            stringBuffer2.append(this.object.getClass().getName());
            stringBuffer2.append(". See cause exception.");
            throw new TemplateModelException(stringBuffer2.toString(), e2);
        }
    }

    private void logNoSuchKey(String str, Map map) {
        Logger logger2 = logger;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Key ");
        stringBuffer.append(StringUtil.jQuoteNoXSS(str));
        stringBuffer.append(" was not found on instance of ");
        stringBuffer.append(this.object.getClass().getName());
        stringBuffer.append(". Introspection information for ");
        stringBuffer.append("the class is: ");
        stringBuffer.append(map);
        logger2.debug(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public boolean hasPlainGetMethod() {
        return this.wrapper.getClassIntrospectionData(this.object.getClass()).get(BeansWrapper.GENERIC_GET_KEY) != null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private freemarker.template.TemplateModel invokeThroughDescriptor(java.lang.Object r5, java.util.Map r6) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException, freemarker.template.TemplateModelException {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.HashMap r0 = r4.memberMap     // Catch:{ all -> 0x0094 }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0094 }
            freemarker.template.TemplateModel r0 = (freemarker.template.TemplateModel) r0     // Catch:{ all -> 0x0094 }
            goto L_0x000e
        L_0x000d:
            r0 = r1
        L_0x000e:
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x0012
            return r0
        L_0x0012:
            freemarker.template.TemplateModel r2 = UNKNOWN
            boolean r3 = r5 instanceof java.beans.IndexedPropertyDescriptor
            if (r3 == 0) goto L_0x002f
            r0 = r5
            java.beans.IndexedPropertyDescriptor r0 = (java.beans.IndexedPropertyDescriptor) r0
            java.lang.reflect.Method r0 = r0.getIndexedReadMethod()
            freemarker.ext.beans.SimpleMethodModel r1 = new freemarker.ext.beans.SimpleMethodModel
            java.lang.Object r2 = r4.object
            java.lang.Class[] r6 = freemarker.ext.beans.BeansWrapper.getArgTypes(r6, r0)
            freemarker.ext.beans.BeansWrapper r3 = r4.wrapper
            r1.<init>(r2, r0, r6, r3)
        L_0x002c:
            r0 = r1
        L_0x002d:
            r2 = r0
            goto L_0x007b
        L_0x002f:
            boolean r3 = r5 instanceof java.beans.PropertyDescriptor
            if (r3 == 0) goto L_0x0043
            r6 = r5
            java.beans.PropertyDescriptor r6 = (java.beans.PropertyDescriptor) r6
            freemarker.ext.beans.BeansWrapper r2 = r4.wrapper
            java.lang.Object r3 = r4.object
            java.lang.reflect.Method r6 = r6.getReadMethod()
            freemarker.template.TemplateModel r2 = r2.invokeMethod(r3, r6, r1)
            goto L_0x007b
        L_0x0043:
            boolean r1 = r5 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x0057
            freemarker.ext.beans.BeansWrapper r6 = r4.wrapper
            r1 = r5
            java.lang.reflect.Field r1 = (java.lang.reflect.Field) r1
            java.lang.Object r2 = r4.object
            java.lang.Object r1 = r1.get(r2)
            freemarker.template.TemplateModel r2 = r6.wrap(r1)
            goto L_0x007b
        L_0x0057:
            boolean r1 = r5 instanceof java.lang.reflect.Method
            if (r1 == 0) goto L_0x006c
            r0 = r5
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            freemarker.ext.beans.SimpleMethodModel r1 = new freemarker.ext.beans.SimpleMethodModel
            java.lang.Object r2 = r4.object
            java.lang.Class[] r6 = freemarker.ext.beans.BeansWrapper.getArgTypes(r6, r0)
            freemarker.ext.beans.BeansWrapper r3 = r4.wrapper
            r1.<init>(r2, r0, r6, r3)
            goto L_0x002c
        L_0x006c:
            boolean r6 = r5 instanceof freemarker.ext.beans.OverloadedMethods
            if (r6 == 0) goto L_0x007b
            freemarker.ext.beans.OverloadedMethodsModel r0 = new freemarker.ext.beans.OverloadedMethodsModel
            java.lang.Object r6 = r4.object
            r1 = r5
            freemarker.ext.beans.OverloadedMethods r1 = (freemarker.ext.beans.OverloadedMethods) r1
            r0.<init>(r6, r1)
            goto L_0x002d
        L_0x007b:
            if (r0 == 0) goto L_0x0093
            monitor-enter(r4)
            java.util.HashMap r6 = r4.memberMap     // Catch:{ all -> 0x0090 }
            if (r6 != 0) goto L_0x0089
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x0090 }
            r6.<init>()     // Catch:{ all -> 0x0090 }
            r4.memberMap = r6     // Catch:{ all -> 0x0090 }
        L_0x0089:
            java.util.HashMap r6 = r4.memberMap     // Catch:{ all -> 0x0090 }
            r6.put(r5, r0)     // Catch:{ all -> 0x0090 }
            monitor-exit(r4)     // Catch:{ all -> 0x0090 }
            goto L_0x0093
        L_0x0090:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0090 }
            throw r5
        L_0x0093:
            return r2
        L_0x0094:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0094 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.BeanModel.invokeThroughDescriptor(java.lang.Object, java.util.Map):freemarker.template.TemplateModel");
    }

    /* access modifiers changed from: protected */
    public TemplateModel invokeGenericGet(Map map, Class cls, String str) throws IllegalAccessException, InvocationTargetException, TemplateModelException {
        Method method = (Method) map.get(BeansWrapper.GENERIC_GET_KEY);
        if (method == null) {
            return UNKNOWN;
        }
        return this.wrapper.invokeMethod(this.object, method, new Object[]{str});
    }

    /* access modifiers changed from: protected */
    public TemplateModel wrap(Object obj) throws TemplateModelException {
        return this.wrapper.getOuterIdentity().wrap(obj);
    }

    /* access modifiers changed from: protected */
    public Object unwrap(TemplateModel templateModel) throws TemplateModelException {
        return this.wrapper.unwrap(templateModel);
    }

    public boolean isEmpty() {
        Object obj = this.object;
        if (obj instanceof String) {
            if (((String) obj).length() == 0) {
                return true;
            }
            return false;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else {
            if (obj instanceof Map) {
                return ((Map) obj).isEmpty();
            }
            if (obj == null || Boolean.FALSE.equals(this.object)) {
                return true;
            }
            return false;
        }
    }

    public Object getAdaptedObject(Class cls) {
        return this.object;
    }

    public Object getWrappedObject() {
        return this.object;
    }

    public int size() {
        return this.wrapper.keyCount(this.object.getClass());
    }

    public TemplateCollectionModel keys() {
        return new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence(keySet(), this.wrapper));
    }

    public TemplateCollectionModel values() throws TemplateModelException {
        ArrayList arrayList = new ArrayList(size());
        TemplateModelIterator it = keys().iterator();
        while (it.hasNext()) {
            arrayList.add(get(((TemplateScalarModel) it.next()).getAsString()));
        }
        return new CollectionAndSequence((TemplateSequenceModel) new SimpleSequence(arrayList, this.wrapper));
    }

    /* access modifiers changed from: package-private */
    public String getAsClassicCompatibleString() {
        Object obj = this.object;
        return obj == null ? "null" : obj.toString();
    }

    public String toString() {
        return this.object.toString();
    }

    /* access modifiers changed from: protected */
    public Set keySet() {
        return this.wrapper.keySet(this.object.getClass());
    }
}
