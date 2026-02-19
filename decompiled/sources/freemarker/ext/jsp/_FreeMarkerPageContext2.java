package freemarker.ext.jsp;

import freemarker.log.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;

public class _FreeMarkerPageContext2 extends FreeMarkerPageContext {
    private static final Logger logger;

    static {
        Logger logger2 = Logger.getLogger("freemarker.jsp");
        logger = logger2;
        if (JspFactory.getDefaultFactory() == null) {
            JspFactory.setDefaultFactory(new FreeMarkerJspFactory2());
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Using JspFactory implementation class ");
        stringBuffer.append(JspFactory.getDefaultFactory().getClass().getName());
        logger2.debug(stringBuffer.toString());
    }

    public ExpressionEvaluator getExpressionEvaluator() {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass("org.apache.commons.el.ExpressionEvaluatorImpl").newInstance();
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

    public void include(String str, boolean z) throws IOException, ServletException {
        super.include(str);
    }
}
