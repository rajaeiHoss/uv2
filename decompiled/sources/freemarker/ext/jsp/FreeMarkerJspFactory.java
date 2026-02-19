package freemarker.ext.jsp;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

abstract class FreeMarkerJspFactory extends JspFactory {
    /* access modifiers changed from: protected */
    public abstract String getSpecificationVersion();

    FreeMarkerJspFactory() {
    }

    public JspEngineInfo getEngineInfo() {
        return new JspEngineInfo() {
            public String getSpecificationVersion() {
                return FreeMarkerJspFactory.this.getSpecificationVersion();
            }
        };
    }

    public PageContext getPageContext(Servlet servlet, ServletRequest servletRequest, ServletResponse servletResponse, String str, boolean z, int i, boolean z2) {
        throw new UnsupportedOperationException();
    }

    public void releasePageContext(PageContext pageContext) {
        throw new UnsupportedOperationException();
    }
}
