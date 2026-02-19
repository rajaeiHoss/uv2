package freemarker.ext.jsp;

import freemarker.core.Environment;
import freemarker.ext.jsp.TagTransformModel;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.ext.util.WrapperTemplateModel;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

abstract class FreeMarkerPageContext extends PageContext implements TemplateModel {
    private static final Class OBJECT_CLASS;
    static /* synthetic */ Class class$freemarker$ext$servlet$HttpRequestHashModel;
    static /* synthetic */ Class class$freemarker$ext$servlet$ServletContextHashModel;
    static /* synthetic */ Class class$java$lang$Object;
    private final Environment environment;
    private JspWriter jspOut;
    private List outs = new ArrayList();
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final GenericServlet servlet;
    private HttpSession session;
    private List tags = new ArrayList();
    private final ObjectWrapper wrapper;

    public void release() {
    }

    static {
        Class cls = class$java$lang$Object;
        if (cls == null) {
            cls = class$("java.lang.Object");
            class$java$lang$Object = cls;
        }
        OBJECT_CLASS = cls;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected FreeMarkerPageContext() throws TemplateModelException {
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        this.environment = currentEnvironment;
        TemplateModel globalVariable = currentEnvironment.getGlobalVariable(FreemarkerServlet.KEY_APPLICATION_PRIVATE);
        globalVariable = !(globalVariable instanceof ServletContextHashModel) ? currentEnvironment.getGlobalVariable(FreemarkerServlet.KEY_APPLICATION) : globalVariable;
        if (globalVariable instanceof ServletContextHashModel) {
            GenericServlet servlet2 = ((ServletContextHashModel) globalVariable).getServlet();
            this.servlet = servlet2;
            TemplateModel globalVariable2 = currentEnvironment.getGlobalVariable(FreemarkerServlet.KEY_REQUEST_PRIVATE);
            globalVariable2 = !(globalVariable2 instanceof HttpRequestHashModel) ? currentEnvironment.getGlobalVariable(FreemarkerServlet.KEY_REQUEST) : globalVariable2;
            if (globalVariable2 instanceof HttpRequestHashModel) {
                HttpRequestHashModel httpRequestHashModel = (HttpRequestHashModel) globalVariable2;
                HttpServletRequest request2 = httpRequestHashModel.getRequest();
                this.request = request2;
                this.session = request2.getSession(false);
                HttpServletResponse response2 = httpRequestHashModel.getResponse();
                this.response = response2;
                this.wrapper = httpRequestHashModel.getObjectWrapper();
                setAttribute("javax.servlet.jsp.jspRequest", request2);
                setAttribute("javax.servlet.jsp.jspResponse", response2);
                HttpSession httpSession = this.session;
                if (httpSession != null) {
                    setAttribute("javax.servlet.jsp.jspSession", httpSession);
                }
                setAttribute("javax.servlet.jsp.jspPage", servlet2);
                setAttribute("javax.servlet.jsp.jspConfig", servlet2.getServletConfig());
                setAttribute("javax.servlet.jsp.jspPageContext", this);
                setAttribute("javax.servlet.jsp.jspApplication", servlet2.getServletContext());
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not find an instance of ");
            Class cls = class$freemarker$ext$servlet$HttpRequestHashModel;
            if (cls == null) {
                cls = class$("freemarker.ext.servlet.HttpRequestHashModel");
                class$freemarker$ext$servlet$HttpRequestHashModel = cls;
            }
            stringBuffer.append(cls.getName());
            stringBuffer.append(" in the data model under either the name ");
            stringBuffer.append(FreemarkerServlet.KEY_REQUEST_PRIVATE);
            stringBuffer.append(" or ");
            stringBuffer.append(FreemarkerServlet.KEY_REQUEST);
            throw new TemplateModelException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Could not find an instance of ");
        Class cls2 = class$freemarker$ext$servlet$ServletContextHashModel;
        if (cls2 == null) {
            cls2 = class$("freemarker.ext.servlet.ServletContextHashModel");
            class$freemarker$ext$servlet$ServletContextHashModel = cls2;
        }
        stringBuffer2.append(cls2.getName());
        stringBuffer2.append(" in the data model under either the name ");
        stringBuffer2.append(FreemarkerServlet.KEY_APPLICATION_PRIVATE);
        stringBuffer2.append(" or ");
        stringBuffer2.append(FreemarkerServlet.KEY_APPLICATION);
        throw new TemplateModelException(stringBuffer2.toString());
    }

    /* access modifiers changed from: package-private */
    public ObjectWrapper getObjectWrapper() {
        return this.wrapper;
    }

    public void initialize(Servlet servlet2, ServletRequest servletRequest, ServletResponse servletResponse, String str, boolean z, int i, boolean z2) {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(String str, Object obj) {
        setAttribute(str, obj, 1);
    }

    public void setAttribute(String str, Object obj, int i) {
        if (i == 1) {
            try {
                this.environment.setGlobalVariable(str, this.wrapper.wrap(obj));
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        } else if (i == 2) {
            getRequest().setAttribute(str, obj);
        } else if (i == 3) {
            getSession(true).setAttribute(str, obj);
        } else if (i == 4) {
            getServletContext().setAttribute(str, obj);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid scope ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public Object getAttribute(String str) {
        return getAttribute(str, 1);
    }

    public Object getAttribute(String str, int i) {
        if (i == 1) {
            try {
                TemplateModel templateModel = this.environment.getGlobalNamespace().get(str);
                if (templateModel instanceof AdapterTemplateModel) {
                    return ((AdapterTemplateModel) templateModel).getAdaptedObject(OBJECT_CLASS);
                }
                if (templateModel instanceof WrapperTemplateModel) {
                    return ((WrapperTemplateModel) templateModel).getWrappedObject();
                }
                if (templateModel instanceof TemplateScalarModel) {
                    return ((TemplateScalarModel) templateModel).getAsString();
                }
                if (templateModel instanceof TemplateNumberModel) {
                    return ((TemplateNumberModel) templateModel).getAsNumber();
                }
                if (templateModel instanceof TemplateBooleanModel) {
                    return ((TemplateBooleanModel) templateModel).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE;
                }
                return templateModel;
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        } else if (i == 2) {
            return getRequest().getAttribute(str);
        } else {
            if (i == 3) {
                HttpSession session2 = getSession(false);
                if (session2 == null) {
                    return null;
                }
                return session2.getAttribute(str);
            } else if (i == 4) {
                return getServletContext().getAttribute(str);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid scope ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    public Object findAttribute(String str) {
        Object attribute = getAttribute(str, 1);
        if (attribute != null) {
            return attribute;
        }
        Object attribute2 = getAttribute(str, 2);
        if (attribute2 != null) {
            return attribute2;
        }
        Object attribute3 = getAttribute(str, 3);
        if (attribute3 != null) {
            return attribute3;
        }
        return getAttribute(str, 4);
    }

    public void removeAttribute(String str) {
        removeAttribute(str, 1);
        removeAttribute(str, 2);
        removeAttribute(str, 3);
        removeAttribute(str, 4);
    }

    public void removeAttribute(String str, int i) {
        if (i == 1) {
            this.environment.getGlobalNamespace().remove(str);
        } else if (i == 2) {
            getRequest().removeAttribute(str);
        } else if (i == 3) {
            HttpSession session2 = getSession(false);
            if (session2 != null) {
                session2.removeAttribute(str);
            }
        } else if (i == 4) {
            getServletContext().removeAttribute(str);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid scope: ");
            stringBuffer.append(i);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public int getAttributesScope(String str) {
        if (getAttribute(str, 1) != null) {
            return 1;
        }
        if (getAttribute(str, 2) != null) {
            return 2;
        }
        if (getAttribute(str, 3) != null) {
            return 3;
        }
        if (getAttribute(str, 4) != null) {
            return 4;
        }
        return 0;
    }

    public Enumeration getAttributeNamesInScope(int i) {
        if (i == 1) {
            try {
                return new TemplateHashModelExEnumeration(this.environment.getGlobalNamespace());
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        } else if (i == 2) {
            return getRequest().getAttributeNames();
        } else {
            if (i == 3) {
                HttpSession session2 = getSession(false);
                if (session2 != null) {
                    return session2.getAttributeNames();
                }
                return Collections.enumeration(Collections.EMPTY_SET);
            } else if (i == 4) {
                return getServletContext().getAttributeNames();
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Invalid scope ");
                stringBuffer.append(i);
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        }
    }

    public JspWriter getOut() {
        return this.jspOut;
    }

    private HttpSession getSession(boolean z) {
        if (this.session == null) {
            HttpSession session2 = this.request.getSession(z);
            this.session = session2;
            if (session2 != null) {
                setAttribute("javax.servlet.jsp.jspSession", session2);
            }
        }
        return this.session;
    }

    public HttpSession getSession() {
        return getSession(false);
    }

    public Object getPage() {
        return this.servlet;
    }

    public ServletRequest getRequest() {
        return this.request;
    }

    public ServletResponse getResponse() {
        return this.response;
    }

    public Exception getException() {
        throw new UnsupportedOperationException();
    }

    public ServletConfig getServletConfig() {
        return this.servlet.getServletConfig();
    }

    public ServletContext getServletContext() {
        return this.servlet.getServletContext();
    }

    public void forward(String str) throws ServletException, IOException {
        this.request.getRequestDispatcher(str).forward(this.request, this.response);
    }

    public void include(String str) throws ServletException, IOException {
        this.jspOut.flush();
        this.request.getRequestDispatcher(str).include(this.request, this.response);
    }

    public void include(String str, boolean z) throws ServletException, IOException {
        if (z) {
            this.jspOut.flush();
        }
        final PrintWriter printWriter = new PrintWriter(this.jspOut);
        this.request.getRequestDispatcher(str).include(this.request, new HttpServletResponseWrapper(this, this.response) {
            private final /* synthetic */ FreeMarkerPageContext this$0;

            {
                this.this$0 = r1;
            }

            public PrintWriter getWriter() {
                return printWriter;
            }

            public ServletOutputStream getOutputStream() {
                throw new UnsupportedOperationException("JSP-included resource must use getWriter()");
            }
        });
        printWriter.flush();
    }

    public void handlePageException(Exception exc) {
        throw new UnsupportedOperationException();
    }

    public void handlePageException(Throwable th) {
        throw new UnsupportedOperationException();
    }

    public BodyContent pushBody() {
        return pushWriter(new TagTransformModel.BodyContentImpl(getOut(), true));
    }

    public JspWriter pushBody(Writer writer) {
        return pushWriter(new JspWriterAdapter(writer));
    }

    public JspWriter popBody() {
        popWriter();
        return (JspWriter) getAttribute("javax.servlet.jsp.jspOut");
    }

    /* access modifiers changed from: package-private */
    public Object peekTopTag(Class cls) {
        List list = this.tags;
        ListIterator listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            Object previous = listIterator.previous();
            if (cls.isInstance(previous)) {
                return previous;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void popTopTag() {
        List list = this.tags;
        list.remove(list.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void popWriter() {
        List list = this.outs;
        JspWriter jspWriter = (JspWriter) list.remove(list.size() - 1);
        this.jspOut = jspWriter;
        setAttribute("javax.servlet.jsp.jspOut", jspWriter);
    }

    /* access modifiers changed from: package-private */
    public void pushTopTag(Object obj) {
        this.tags.add(obj);
    }

    /* access modifiers changed from: package-private */
    public JspWriter pushWriter(JspWriter jspWriter) {
        this.outs.add(this.jspOut);
        this.jspOut = jspWriter;
        setAttribute("javax.servlet.jsp.jspOut", jspWriter);
        return jspWriter;
    }

    private static class TemplateHashModelExEnumeration implements Enumeration {
        private final TemplateModelIterator it;

        private TemplateHashModelExEnumeration(TemplateHashModelEx templateHashModelEx) throws TemplateModelException {
            this.it = templateHashModelEx.keys().iterator();
        }

        public boolean hasMoreElements() {
            try {
                return this.it.hasNext();
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        }

        public Object nextElement() {
            try {
                return ((TemplateScalarModel) this.it.next()).getAsString();
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    }
}
