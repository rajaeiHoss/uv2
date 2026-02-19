package freemarker.ext.servlet;

import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AllHttpScopesHashModel extends SimpleHash {
    private final ServletContext context;
    private final HttpServletRequest request;
    private final Map unlistedModels = new HashMap();

    public AllHttpScopesHashModel(ObjectWrapper objectWrapper, ServletContext servletContext, HttpServletRequest httpServletRequest) {
        setObjectWrapper(objectWrapper);
        this.context = servletContext;
        this.request = httpServletRequest;
    }

    public void putUnlistedModel(String str, TemplateModel templateModel) {
        this.unlistedModels.put(str, templateModel);
    }

    public TemplateModel get(String str) throws TemplateModelException {
        Object attribute;
        TemplateModel templateModel = super.get(str);
        if (templateModel != null) {
            return templateModel;
        }
        TemplateModel templateModel2 = (TemplateModel) this.unlistedModels.get(str);
        if (templateModel2 != null) {
            return templateModel2;
        }
        Object attribute2 = this.request.getAttribute(str);
        if (attribute2 != null) {
            return wrap(attribute2);
        }
        HttpSession session = this.request.getSession(false);
        if (session != null && (attribute = session.getAttribute(str)) != null) {
            return wrap(attribute);
        }
        Object attribute3 = this.context.getAttribute(str);
        if (attribute3 != null) {
            return wrap(attribute3);
        }
        return wrap((Object) null);
    }
}
