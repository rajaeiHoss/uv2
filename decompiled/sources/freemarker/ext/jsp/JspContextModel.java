package freemarker.ext.jsp;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import javax.servlet.jsp.PageContext;

class JspContextModel implements TemplateHashModel {
    public static final int ANY_SCOPE = -1;
    public static final int APPLICATION_SCOPE = 4;
    public static final int PAGE_SCOPE = 1;
    public static final int REQUEST_SCOPE = 2;
    public static final int SESSION_SCOPE = 3;
    private final PageContext pageContext;
    private final int scope;

    public boolean isEmpty() {
        return false;
    }

    public JspContextModel(PageContext pageContext2, int i) {
        this.pageContext = pageContext2;
        this.scope = i;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        int i = this.scope;
        return BeansWrapper.getDefaultInstance().wrap(i == -1 ? this.pageContext.findAttribute(str) : this.pageContext.getAttribute(str, i));
    }
}
