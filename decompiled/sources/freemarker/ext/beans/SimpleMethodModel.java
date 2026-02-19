package freemarker.ext.beans;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.Collections12;
import java.lang.reflect.Method;

public final class SimpleMethodModel extends SimpleMemberModel implements TemplateMethodModelEx, TemplateSequenceModel {
    private final Object object;
    private final BeansWrapper wrapper;

    SimpleMethodModel(Object obj, Method method, Class[] clsArr, BeansWrapper beansWrapper) {
        super(method, clsArr);
        this.object = obj;
        this.wrapper = beansWrapper;
    }

    /* JADX WARNING: type inference failed for: r0v8, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object exec(java.util.List r5) throws freemarker.template.TemplateModelException {
        /*
            r4 = this;
            freemarker.ext.beans.BeansWrapper r0 = r4.wrapper     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            java.lang.Object r1 = r4.object     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            java.lang.reflect.Member r2 = r4.getMember()     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            java.lang.reflect.Method r2 = (java.lang.reflect.Method) r2     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            freemarker.ext.beans.BeansWrapper r3 = r4.wrapper     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            java.lang.Object[] r5 = r4.unwrapArguments(r5, r3)     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            freemarker.template.TemplateModel r5 = r0.invokeMethod(r1, r2, r5)     // Catch:{ TemplateModelException -> 0x009f, Exception -> 0x0015 }
            return r5
        L_0x0015:
            r5 = move-exception
        L_0x0016:
            boolean r0 = r5 instanceof java.lang.reflect.InvocationTargetException
            if (r0 == 0) goto L_0x0029
            r0 = r5
            java.lang.reflect.InvocationTargetException r0 = (java.lang.reflect.InvocationTargetException) r0
            java.lang.Throwable r0 = r0.getTargetException()
            boolean r1 = r0 instanceof java.lang.Exception
            if (r1 == 0) goto L_0x0029
            r5 = r0
            java.lang.Exception r5 = (java.lang.Exception) r5
            goto L_0x0016
        L_0x0029:
            java.lang.reflect.Member r0 = r4.getMember()
            int r0 = r0.getModifiers()
            r0 = r0 & 8
            java.lang.String r1 = "Method "
            if (r0 == 0) goto L_0x0059
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            java.lang.reflect.Member r1 = r4.getMember()
            java.lang.String r1 = freemarker.template.utility.StringUtil.jQuote((java.lang.Object) r1)
            r2.append(r1)
            java.lang.String r1 = " threw an exception; see cause exception"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>((java.lang.String) r1, (java.lang.Exception) r5)
            throw r0
        L_0x0059:
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            java.lang.reflect.Member r1 = r4.getMember()
            java.lang.String r1 = freemarker.template.utility.StringUtil.jQuote((java.lang.Object) r1)
            r2.append(r1)
            java.lang.String r1 = " threw an exception when invoked on "
            r2.append(r1)
            java.lang.Object r1 = r4.object
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r2.append(r1)
            java.lang.String r1 = " object "
            r2.append(r1)
            java.lang.Object r1 = r4.object
            java.lang.String r1 = freemarker.template.utility.StringUtil.tryToString(r1)
            java.lang.String r1 = freemarker.template.utility.StringUtil.jQuote((java.lang.String) r1)
            r2.append(r1)
            java.lang.String r1 = ". See cause exception."
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>((java.lang.String) r1, (java.lang.Exception) r5)
            throw r0
        L_0x009f:
            r5 = move-exception
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.SimpleMethodModel.exec(java.util.List):java.lang.Object");
    }

    public TemplateModel get(int i) throws TemplateModelException {
        return (TemplateModel) exec(Collections12.singletonList(new SimpleNumber((Number) new Integer(i))));
    }

    public int size() throws TemplateModelException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Getting the number of items or enumerating the items is not supported on this ");
        stringBuffer.append(ClassUtil.getFTLTypeDescription(this));
        stringBuffer.append(" value.\n");
        stringBuffer.append("(");
        stringBuffer.append("Hint 1: Maybe you wanted to call this method first and then do something with its return value. ");
        stringBuffer.append("Hint 2: Getting items by intex possibly works, hence it's a \"+sequence\".");
        stringBuffer.append(")");
        throw new TemplateModelException(stringBuffer.toString());
    }

    public String toString() {
        return getMember().toString();
    }
}
