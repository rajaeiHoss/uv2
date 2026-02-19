package freemarker.ext.servlet;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class HttpSessionHashModel implements TemplateHashModel, Serializable {
    private static final long serialVersionUID = 1;
    private final transient HttpServletRequest request;
    private final transient HttpServletResponse response;
    private final transient FreemarkerServlet servlet;
    private transient HttpSession session;
    private final transient ObjectWrapper wrapper;

    public HttpSessionHashModel(HttpSession httpSession, ObjectWrapper objectWrapper) {
        this.session = httpSession;
        this.wrapper = objectWrapper;
        this.servlet = null;
        this.request = null;
        this.response = null;
    }

    public HttpSessionHashModel(FreemarkerServlet freemarkerServlet, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ObjectWrapper objectWrapper) {
        this.wrapper = objectWrapper;
        this.servlet = freemarkerServlet;
        this.request = httpServletRequest;
        this.response = httpServletResponse;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        checkSessionExistence();
        ObjectWrapper objectWrapper = this.wrapper;
        HttpSession httpSession = this.session;
        return objectWrapper.wrap(httpSession != null ? httpSession.getAttribute(str) : null);
    }

    private void checkSessionExistence() throws TemplateModelException {
        HttpServletRequest httpServletRequest;
        FreemarkerServlet freemarkerServlet;
        if (this.session == null && (httpServletRequest = this.request) != null) {
            HttpSession session2 = httpServletRequest.getSession(false);
            this.session = session2;
            if (session2 != null && (freemarkerServlet = this.servlet) != null) {
                try {
                    freemarkerServlet.initializeSessionAndInstallModel(this.request, this.response, this, session2);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new TemplateModelException(e2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isOrphaned(HttpSession httpSession) {
        HttpSession httpSession2 = this.session;
        return !(httpSession2 == null || httpSession2 == httpSession) || (httpSession2 == null && this.request == null);
    }

    public boolean isEmpty() throws TemplateModelException {
        checkSessionExistence();
        HttpSession httpSession = this.session;
        return httpSession == null || !httpSession.getAttributeNames().hasMoreElements();
    }
}
