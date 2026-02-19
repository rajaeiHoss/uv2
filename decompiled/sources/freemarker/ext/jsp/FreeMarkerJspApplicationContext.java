package freemarker.ext.jsp;

import freemarker.log.Logger;
import freemarker.template.utility.ClassUtil;
import java.util.Iterator;
import java.util.LinkedList;
import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELContextEvent;
import javax.el.ELContextListener;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ResourceBundleELResolver;
import javax.el.ValueExpression;
import javax.el.VariableMapper;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.el.ImplicitObjectELResolver;
import javax.servlet.jsp.el.ScopedAttributeELResolver;

class FreeMarkerJspApplicationContext implements JspApplicationContext {
    static /* synthetic */ Class class$javax$el$ExpressionFactory;
    /* access modifiers changed from: private */
    public static final ExpressionFactory expressionFactoryImpl = findExpressionFactoryImplementation();
    private static final Logger logger = Logger.getLogger("freemarker.jsp");
    private final CompositeELResolver additionalResolvers;
    /* access modifiers changed from: private */
    public final CompositeELResolver elResolver;
    private final LinkedList listeners = new LinkedList();

    FreeMarkerJspApplicationContext() {
        CompositeELResolver compositeELResolver = new CompositeELResolver();
        this.elResolver = compositeELResolver;
        CompositeELResolver compositeELResolver2 = new CompositeELResolver();
        this.additionalResolvers = compositeELResolver2;
        compositeELResolver.add(new ImplicitObjectELResolver());
        compositeELResolver.add(compositeELResolver2);
        compositeELResolver.add(new MapELResolver());
        compositeELResolver.add(new ResourceBundleELResolver());
        compositeELResolver.add(new ListELResolver());
        compositeELResolver.add(new ArrayELResolver());
        compositeELResolver.add(new BeanELResolver());
        compositeELResolver.add(new ScopedAttributeELResolver());
    }

    public void addELContextListener(ELContextListener eLContextListener) {
        synchronized (this.listeners) {
            this.listeners.addLast(eLContextListener);
        }
    }

    private static ExpressionFactory findExpressionFactoryImplementation() {
        ExpressionFactory tryExpressionFactoryImplementation = tryExpressionFactoryImplementation("com.sun");
        if (tryExpressionFactoryImplementation == null && (tryExpressionFactoryImplementation = tryExpressionFactoryImplementation("org.apache")) == null) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find any implementation for ");
            Class cls = class$javax$el$ExpressionFactory;
            if (cls == null) {
                cls = class$("javax.el.ExpressionFactory");
                class$javax$el$ExpressionFactory = cls;
            }
            stringBuffer.append(cls.getName());
            logger2.warn(stringBuffer.toString());
        }
        return tryExpressionFactoryImplementation;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static ExpressionFactory tryExpressionFactoryImplementation(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(".el.ExpressionFactoryImpl");
        String stringBuffer2 = stringBuffer.toString();
        try {
            Class forName = ClassUtil.forName(stringBuffer2);
            Class cls = class$javax$el$ExpressionFactory;
            if (cls == null) {
                cls = class$("javax.el.ExpressionFactory");
                class$javax$el$ExpressionFactory = cls;
            }
            if (cls.isAssignableFrom(forName)) {
                Logger logger2 = logger;
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Using ");
                stringBuffer3.append(stringBuffer2);
                stringBuffer3.append(" as implementation of ");
                Class cls2 = class$javax$el$ExpressionFactory;
                if (cls2 == null) {
                    cls2 = class$("javax.el.ExpressionFactory");
                    class$javax$el$ExpressionFactory = cls2;
                }
                stringBuffer3.append(cls2.getName());
                logger2.info(stringBuffer3.toString());
                return (ExpressionFactory) forName.newInstance();
            }
            Logger logger3 = logger;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("Class ");
            stringBuffer4.append(stringBuffer2);
            stringBuffer4.append(" does not implement ");
            Class cls3 = class$javax$el$ExpressionFactory;
            if (cls3 == null) {
                cls3 = class$("javax.el.ExpressionFactory");
                class$javax$el$ExpressionFactory = cls3;
            }
            stringBuffer4.append(cls3.getName());
            logger3.warn(stringBuffer4.toString());
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            Logger logger4 = logger;
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("Failed to instantiate ");
            stringBuffer5.append(stringBuffer2);
            logger4.error(stringBuffer5.toString(), e);
            return null;
        }
    }

    public void addELResolver(ELResolver eLResolver) {
        this.additionalResolvers.add(eLResolver);
    }

    public ExpressionFactory getExpressionFactory() {
        return expressionFactoryImpl;
    }

    /* access modifiers changed from: package-private */
    public ELContext createNewELContext(FreeMarkerPageContext freeMarkerPageContext) {
        FreeMarkerELContext freeMarkerELContext = new FreeMarkerELContext(freeMarkerPageContext);
        ELContextEvent eLContextEvent = new ELContextEvent(freeMarkerELContext);
        synchronized (this.listeners) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((ELContextListener) it.next()).contextCreated(eLContextEvent);
            }
        }
        return freeMarkerELContext;
    }

    private class FreeMarkerELContext extends ELContext {
        /* access modifiers changed from: private */
        public final FreeMarkerPageContext pageCtx;

        public FunctionMapper getFunctionMapper() {
            return null;
        }

        FreeMarkerELContext(FreeMarkerPageContext freeMarkerPageContext) {
            this.pageCtx = freeMarkerPageContext;
        }

        public ELResolver getELResolver() {
            return FreeMarkerJspApplicationContext.this.elResolver;
        }

        public VariableMapper getVariableMapper() {
            return new VariableMapper() {
                public ValueExpression resolveVariable(String str) {
                    Object findAttribute = FreeMarkerELContext.this.pageCtx.findAttribute(str);
                    if (findAttribute == null) {
                        return null;
                    }
                    return FreeMarkerJspApplicationContext.expressionFactoryImpl.createValueExpression(findAttribute, findAttribute.getClass());
                }

                public ValueExpression setVariable(String str, ValueExpression valueExpression) {
                    ValueExpression resolveVariable = resolveVariable(str);
                    FreeMarkerELContext.this.pageCtx.setAttribute(str, valueExpression.getValue(FreeMarkerELContext.this));
                    return resolveVariable;
                }
            };
        }
    }
}
