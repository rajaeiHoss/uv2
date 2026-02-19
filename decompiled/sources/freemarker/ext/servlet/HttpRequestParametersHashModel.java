package freemarker.ext.servlet;

import freemarker.template.SimpleCollection;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class HttpRequestParametersHashModel implements TemplateHashModelEx {
    private List keys;
    /* access modifiers changed from: private */
    public final HttpServletRequest request;

    /* access modifiers changed from: protected */
    public String transcode(String str) {
        return str;
    }

    public HttpRequestParametersHashModel(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    public TemplateModel get(String str) {
        String parameter = this.request.getParameter(str);
        if (parameter == null) {
            return null;
        }
        return new SimpleScalar(parameter);
    }

    public boolean isEmpty() {
        return !this.request.getParameterNames().hasMoreElements();
    }

    public int size() {
        return getKeys().size();
    }

    public TemplateCollectionModel keys() {
        return new SimpleCollection(getKeys().iterator());
    }

    public TemplateCollectionModel values() {
        final Iterator it = getKeys().iterator();
        return new SimpleCollection((Iterator) new Iterator() {
            public boolean hasNext() {
                return it.hasNext();
            }

            public Object next() {
                return HttpRequestParametersHashModel.this.request.getParameter((String) it.next());
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        });
    }

    private synchronized List getKeys() {
        if (this.keys == null) {
            this.keys = new ArrayList();
            Enumeration parameterNames = this.request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                this.keys.add(parameterNames.nextElement());
            }
        }
        return this.keys;
    }
}
