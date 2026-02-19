package freemarker.ext.jython;

import freemarker.ext.util.ModelCache;

class JythonModelCache extends ModelCache {
    private final JythonWrapper wrapper;

    /* access modifiers changed from: protected */
    public boolean isCacheable(Object obj) {
        return true;
    }

    JythonModelCache(JythonWrapper jythonWrapper) {
        this.wrapper = jythonWrapper;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0083  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel create(java.lang.Object r5) {
        /*
            r4 = this;
            freemarker.ext.jython.JythonVersionAdapter r0 = freemarker.ext.jython.JythonVersionAdapterHolder.INSTANCE
            boolean r1 = r0.isPyInstance(r5)
            r2 = 0
            if (r1 == 0) goto L_0x003a
            java.lang.Object r0 = r0.pyInstanceToJava(r5)
            boolean r1 = r0 instanceof freemarker.template.TemplateModel
            if (r1 == 0) goto L_0x0014
            freemarker.template.TemplateModel r0 = (freemarker.template.TemplateModel) r0
            return r0
        L_0x0014:
            boolean r1 = r0 instanceof java.util.Map
            boolean r3 = r0 instanceof java.util.Date
            if (r3 == 0) goto L_0x0026
            freemarker.ext.beans.DateModel r5 = new freemarker.ext.beans.DateModel
            java.util.Date r0 = (java.util.Date) r0
            freemarker.ext.beans.BeansWrapper r1 = freemarker.ext.beans.BeansWrapper.getDefaultInstance()
            r5.<init>(r0, r1)
            return r5
        L_0x0026:
            boolean r3 = r0 instanceof java.util.Collection
            if (r3 == 0) goto L_0x0039
            r2 = 1
            boolean r3 = r0 instanceof java.util.List
            if (r3 != 0) goto L_0x0036
            java.util.ArrayList r5 = new java.util.ArrayList
            java.util.Collection r0 = (java.util.Collection) r0
            r5.<init>(r0)
        L_0x0036:
            r2 = r1
            r0 = 1
            goto L_0x003b
        L_0x0039:
            r2 = r1
        L_0x003a:
            r0 = 0
        L_0x003b:
            boolean r1 = r5 instanceof org.python.core.PyObject
            if (r1 != 0) goto L_0x0043
            org.python.core.PyObject r5 = org.python.core.Py.java2py(r5)
        L_0x0043:
            if (r2 != 0) goto L_0x0083
            boolean r1 = r5 instanceof org.python.core.PyDictionary
            if (r1 != 0) goto L_0x0083
            boolean r1 = r5 instanceof org.python.core.PyStringMap
            if (r1 == 0) goto L_0x004e
            goto L_0x0083
        L_0x004e:
            if (r0 != 0) goto L_0x007a
            boolean r0 = r5 instanceof org.python.core.PySequence
            if (r0 == 0) goto L_0x0055
            goto L_0x007a
        L_0x0055:
            boolean r0 = r5 instanceof org.python.core.PyInteger
            if (r0 != 0) goto L_0x0071
            boolean r0 = r5 instanceof org.python.core.PyLong
            if (r0 != 0) goto L_0x0071
            boolean r0 = r5 instanceof org.python.core.PyFloat
            if (r0 == 0) goto L_0x0062
            goto L_0x0071
        L_0x0062:
            boolean r0 = r5 instanceof org.python.core.PyNone
            if (r0 == 0) goto L_0x0068
            r5 = 0
            return r5
        L_0x0068:
            freemarker.ext.util.ModelFactory r0 = freemarker.ext.jython.JythonModel.FACTORY
            freemarker.ext.jython.JythonWrapper r1 = r4.wrapper
            freemarker.template.TemplateModel r5 = r0.create(r5, r1)
            return r5
        L_0x0071:
            freemarker.ext.util.ModelFactory r0 = freemarker.ext.jython.JythonNumberModel.FACTORY
            freemarker.ext.jython.JythonWrapper r1 = r4.wrapper
            freemarker.template.TemplateModel r5 = r0.create(r5, r1)
            return r5
        L_0x007a:
            freemarker.ext.util.ModelFactory r0 = freemarker.ext.jython.JythonSequenceModel.FACTORY
            freemarker.ext.jython.JythonWrapper r1 = r4.wrapper
            freemarker.template.TemplateModel r5 = r0.create(r5, r1)
            return r5
        L_0x0083:
            freemarker.ext.util.ModelFactory r0 = freemarker.ext.jython.JythonHashModel.FACTORY
            freemarker.ext.jython.JythonWrapper r1 = r4.wrapper
            freemarker.template.TemplateModel r5 = r0.create(r5, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.jython.JythonModelCache.create(java.lang.Object):freemarker.template.TemplateModel");
    }
}
