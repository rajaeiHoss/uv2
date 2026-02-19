package freemarker.ext.servlet;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HttpRequestHashModel implements TemplateHashModelEx {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ObjectWrapper wrapper;

    public HttpRequestHashModel(HttpServletRequest httpServletRequest, ObjectWrapper objectWrapper) {
        this(httpServletRequest, (HttpServletResponse) null, objectWrapper);
    }

    public HttpRequestHashModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, ObjectWrapper objectWrapper) {
        this.request = httpServletRequest;
        this.response = httpServletResponse;
        this.wrapper = objectWrapper;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        return this.wrapper.wrap(this.request.getAttribute(str));
    }

    public boolean isEmpty() {
        return !this.request.getAttributeNames().hasMoreElements();
    }

    public int size() {
        Enumeration attributeNames = this.request.getAttributeNames();
        int i = 0;
        while (attributeNames.hasMoreElements()) {
            attributeNames.nextElement();
            i++;
        }
        return i;
    }

    public TemplateCollectionModel keys() {
        ArrayList arrayList = new ArrayList();
        Enumeration attributeNames = this.request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            arrayList.add(attributeNames.nextElement());
        }
        return new SimpleCollection(arrayList.iterator());
    }

    public TemplateCollectionModel values() {
        ArrayList arrayList = new ArrayList();
        Enumeration attributeNames = this.request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            arrayList.add(this.request.getAttribute((String) attributeNames.nextElement()));
        }
        return new SimpleCollection(arrayList.iterator(), this.wrapper);
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public ObjectWrapper getObjectWrapper() {
        return this.wrapper;
    }
}
