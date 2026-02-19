package freemarker.ext.jsp;

import freemarker.log.Logger;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.el.ELContext;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class _FreeMarkerPageContext21 extends FreeMarkerPageContext {
    static /* synthetic */ Class class$javax$servlet$jsp$JspContext;
    private static final Logger logger;
    private ELContext elContext;

    static {
        Logger logger2 = Logger.getLogger("freemarker.jsp");
        logger = logger2;
        if (JspFactory.getDefaultFactory() == null) {
            JspFactory.setDefaultFactory(new FreeMarkerJspFactory21());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Using JspFactory implementation class ");
        stringBuffer.append(JspFactory.getDefaultFactory().getClass().getName());
        logger2.debug(stringBuffer.toString());
    }

    public ExpressionEvaluator getExpressionEvaluator() {
        try {
            return ((ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return Thread.currentThread().getContextClassLoader();
                }
            })).loadClass("org.apache.commons.el.ExpressionEvaluatorImpl").newInstance();
        } catch (Exception unused) {
            throw new UnsupportedOperationException("In order for the getExpressionEvaluator() method to work, you must have downloaded the apache commons-el jar and made it available in the classpath.");
        }
    }

    public VariableResolver getVariableResolver() {
        return new VariableResolver() {
            public Object resolveVariable(String str) throws ELException {
                return this.findAttribute(str);
            }
        };
    }

    public ELContext getELContext() {
        if (this.elContext == null) {
            FreeMarkerJspApplicationContext jspApplicationContext = JspFactory.getDefaultFactory().getJspApplicationContext(getServletContext());
            if (jspApplicationContext instanceof FreeMarkerJspApplicationContext) {
                ELContext createNewELContext = jspApplicationContext.createNewELContext(this);
                this.elContext = createNewELContext;
                Class cls = class$javax$servlet$jsp$JspContext;
                if (cls == null) {
                    cls = class$("javax.servlet.jsp.JspContext");
                    class$javax$servlet$jsp$JspContext = cls;
                }
                createNewELContext.putContext(cls, this);
            } else {
                throw new UnsupportedOperationException("Can not create an ELContext using a foreign JspApplicationContext\nConsider dropping a private instance of JSP 2.1 API JAR file in\nyour WEB-INF/lib directory and then try again.");
            }
        }
        return this.elContext;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
