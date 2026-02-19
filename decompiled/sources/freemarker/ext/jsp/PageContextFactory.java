package freemarker.ext.jsp;

import freemarker.core.Environment;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.UndeclaredThrowableException;

class PageContextFactory {
    static /* synthetic */ Class class$javax$servlet$jsp$PageContext;
    private static final Class pageContextImpl = getPageContextImpl();

    PageContextFactory() {
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        if (r2 == null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r2 = class$("javax.servlet.jsp.PageContext");
        class$javax$servlet$jsp$PageContext = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r2.getMethod("getExpressionEvaluator", (java.lang.Class[]) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        return java.lang.Class.forName("freemarker.ext.jsp._FreeMarkerPageContext2");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        return java.lang.Class.forName("freemarker.ext.jsp._FreeMarkerPageContext1");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        throw new java.lang.NoClassDefFoundError(r0.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r2 = class$javax$servlet$jsp$PageContext;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Class getPageContextImpl() {
        /*
            java.lang.String r0 = "javax.servlet.jsp.PageContext"
            r1 = 0
            java.lang.Class r2 = class$javax$servlet$jsp$PageContext     // Catch:{ NoSuchMethodException -> 0x001e }
            if (r2 != 0) goto L_0x000d
            java.lang.Class r2 = class$(r0)     // Catch:{ NoSuchMethodException -> 0x001e }
            class$javax$servlet$jsp$PageContext = r2     // Catch:{ NoSuchMethodException -> 0x001e }
        L_0x000d:
            java.lang.String r3 = "getELContext"
            r4 = r1
            java.lang.Class[] r4 = (java.lang.Class[]) r4     // Catch:{ NoSuchMethodException -> 0x001e }
            r2.getMethod(r3, r4)     // Catch:{ NoSuchMethodException -> 0x001e }
            java.lang.String r2 = "freemarker.ext.jsp._FreeMarkerPageContext21"
            java.lang.Class r0 = java.lang.Class.forName(r2)     // Catch:{ NoSuchMethodException -> 0x001e }
            return r0
        L_0x001c:
            r0 = move-exception
            goto L_0x003d
        L_0x001e:
            java.lang.Class r2 = class$javax$servlet$jsp$PageContext     // Catch:{ NoSuchMethodException -> 0x0036 }
            if (r2 != 0) goto L_0x0028
            java.lang.Class r2 = class$(r0)     // Catch:{ NoSuchMethodException -> 0x0036 }
            class$javax$servlet$jsp$PageContext = r2     // Catch:{ NoSuchMethodException -> 0x0036 }
        L_0x0028:
            java.lang.String r0 = "getExpressionEvaluator"
            java.lang.Class[] r1 = (java.lang.Class[]) r1     // Catch:{ NoSuchMethodException -> 0x0036 }
            r2.getMethod(r0, r1)     // Catch:{ NoSuchMethodException -> 0x0036 }
            java.lang.String r0 = "freemarker.ext.jsp._FreeMarkerPageContext2"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ NoSuchMethodException -> 0x0036 }
            return r0
        L_0x0036:
            java.lang.String r0 = "freemarker.ext.jsp._FreeMarkerPageContext1"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x001c }
            return r0
        L_0x003d:
            java.lang.NoClassDefFoundError r1 = new java.lang.NoClassDefFoundError
            java.lang.String r0 = r0.getMessage()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.jsp.PageContextFactory.getPageContextImpl():java.lang.Class");
    }

    static FreeMarkerPageContext getCurrentPageContext() throws TemplateModelException {
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        TemplateModel globalVariable = currentEnvironment.getGlobalVariable("javax.servlet.jsp.jspPageContext");
        if (globalVariable instanceof FreeMarkerPageContext) {
            return (FreeMarkerPageContext) globalVariable;
        }
        try {
            FreeMarkerPageContext freeMarkerPageContext = (FreeMarkerPageContext) pageContextImpl.newInstance();
            currentEnvironment.setGlobalVariable("javax.servlet.jsp.jspPageContext", freeMarkerPageContext);
            return freeMarkerPageContext;
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (InstantiationException e2) {
            throw new UndeclaredThrowableException(e2);
        }
    }
}
