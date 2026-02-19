package freemarker.ext.jsp;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;

class FreeMarkerJspFactory21 extends FreeMarkerJspFactory {
    private static final String JSPCTX_KEY;
    static /* synthetic */ Class class$freemarker$ext$jsp$FreeMarkerJspFactory21;

    /* access modifiers changed from: protected */
    public String getSpecificationVersion() {
        return "2.1";
    }

    FreeMarkerJspFactory21() {
    }

    static {
        StringBuffer stringBuffer = new StringBuffer();
        Class cls = class$freemarker$ext$jsp$FreeMarkerJspFactory21;
        if (cls == null) {
            cls = class$("freemarker.ext.jsp.FreeMarkerJspFactory21");
            class$freemarker$ext$jsp$FreeMarkerJspFactory21 = cls;
        }
        stringBuffer.append(cls.getName());
        stringBuffer.append("#jspAppContext");
        JSPCTX_KEY = stringBuffer.toString();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public JspApplicationContext getJspApplicationContext(ServletContext servletContext) {
        String str = JSPCTX_KEY;
        FreeMarkerJspApplicationContext freeMarkerJspApplicationContext = (JspApplicationContext) servletContext.getAttribute(str);
        if (freeMarkerJspApplicationContext == null) {
            synchronized (servletContext) {
                freeMarkerJspApplicationContext = (JspApplicationContext) servletContext.getAttribute(str);
                if (freeMarkerJspApplicationContext == null) {
                    freeMarkerJspApplicationContext = new FreeMarkerJspApplicationContext();
                    servletContext.setAttribute(str, freeMarkerJspApplicationContext);
                }
            }
        }
        return freeMarkerJspApplicationContext;
    }
}
