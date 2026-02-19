package freemarker.ext.beans;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.Collections12;

public class OverloadedMethodsModel implements TemplateMethodModelEx, TemplateSequenceModel {
    private final Object object;
    private final OverloadedMethods overloadedMethods;

    OverloadedMethodsModel(Object obj, OverloadedMethods overloadedMethods2) {
        this.object = obj;
        this.overloadedMethods = overloadedMethods2;
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [java.lang.Throwable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object exec(java.util.List r7) throws freemarker.template.TemplateModelException {
        /*
            r6 = this;
            freemarker.ext.beans.OverloadedMethods r0 = r6.overloadedMethods
            freemarker.ext.beans.MemberAndArguments r7 = r0.getMemberAndArguments(r7)
            java.lang.reflect.Member r0 = r7.getMember()
            java.lang.reflect.Method r0 = (java.lang.reflect.Method) r0
            freemarker.ext.beans.OverloadedMethods r1 = r6.overloadedMethods     // Catch:{ Exception -> 0x001d }
            freemarker.ext.beans.BeansWrapper r1 = r1.getWrapper()     // Catch:{ Exception -> 0x001d }
            java.lang.Object r2 = r6.object     // Catch:{ Exception -> 0x001d }
            java.lang.Object[] r3 = r7.getArgs()     // Catch:{ Exception -> 0x001d }
            freemarker.template.TemplateModel r7 = r1.invokeMethod(r2, r0, r3)     // Catch:{ Exception -> 0x001d }
            return r7
        L_0x001d:
            r1 = move-exception
        L_0x001e:
            boolean r2 = r1 instanceof java.lang.reflect.InvocationTargetException
            if (r2 == 0) goto L_0x0031
            r2 = r1
            java.lang.reflect.InvocationTargetException r2 = (java.lang.reflect.InvocationTargetException) r2
            java.lang.Throwable r2 = r2.getTargetException()
            boolean r3 = r2 instanceof java.lang.Exception
            if (r3 == 0) goto L_0x0031
            r1 = r2
            java.lang.Exception r1 = (java.lang.Exception) r1
            goto L_0x001e
        L_0x0031:
            int r2 = r0.getModifiers()
            r2 = r2 & 8
            java.lang.String r3 = "Method "
            if (r2 != 0) goto L_0x00a8
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.Object[] r7 = r7.getArgs()
            r4 = 0
        L_0x0045:
            int r5 = r7.length
            if (r4 >= r5) goto L_0x0062
            r5 = r7[r4]
            if (r5 != 0) goto L_0x004f
            java.lang.String r5 = "null"
            goto L_0x0057
        L_0x004f:
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
        L_0x0057:
            r2.append(r5)
            r5 = 44
            r2.append(r5)
            int r4 = r4 + 1
            goto L_0x0045
        L_0x0062:
            freemarker.template.TemplateModelException r7 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            r4.append(r3)
            r4.append(r0)
            java.lang.String r0 = " threw an exception when invoked on "
            r4.append(r0)
            java.lang.Object r0 = r6.object
            java.lang.Class r0 = r0.getClass()
            java.lang.String r0 = r0.getName()
            r4.append(r0)
            java.lang.String r0 = " object "
            r4.append(r0)
            java.lang.Object r0 = r6.object
            java.lang.String r0 = freemarker.template.utility.StringUtil.tryToString(r0)
            java.lang.String r0 = freemarker.template.utility.StringUtil.jQuote((java.lang.String) r0)
            r4.append(r0)
            java.lang.String r0 = " with arguments of types ["
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = "]. See cause exception."
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            r7.<init>((java.lang.String) r0, (java.lang.Exception) r1)
            throw r7
        L_0x00a8:
            freemarker.template.TemplateModelException r7 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " threw an exception. See cause exception."
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r7.<init>((java.lang.String) r0, (java.lang.Exception) r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.beans.OverloadedMethodsModel.exec(java.util.List):java.lang.Object");
    }

    public TemplateModel get(int i) throws TemplateModelException {
        return (TemplateModel) exec(Collections12.singletonList(new SimpleNumber((Number) new Integer(i))));
    }

    public int size() throws TemplateModelException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("?size is unsupported for ");
        stringBuffer.append(getClass().getName());
        throw new TemplateModelException(stringBuffer.toString());
    }
}
