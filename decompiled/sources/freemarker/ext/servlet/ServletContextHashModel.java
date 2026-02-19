package freemarker.ext.servlet;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;

public final class ServletContextHashModel implements TemplateHashModel {
    private final GenericServlet servlet;
    private final ServletContext servletctx;
    private final ObjectWrapper wrapper;

    public ServletContextHashModel(GenericServlet genericServlet, ObjectWrapper objectWrapper) {
        this.servlet = genericServlet;
        this.servletctx = genericServlet.getServletContext();
        this.wrapper = objectWrapper;
    }

    public ServletContextHashModel(ServletContext servletContext, ObjectWrapper objectWrapper) {
        this.servlet = null;
        this.servletctx = servletContext;
        this.wrapper = objectWrapper;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        return this.wrapper.wrap(this.servletctx.getAttribute(str));
    }

    public boolean isEmpty() {
        return !this.servletctx.getAttributeNames().hasMoreElements();
    }

    public GenericServlet getServlet() {
        return this.servlet;
    }
}
