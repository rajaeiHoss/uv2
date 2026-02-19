package freemarker.ext.jsp;

import freemarker.template.SimpleHash;
import freemarker.template.Template;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

public class FreemarkerTag implements BodyTag {
    private BodyContent bodyContent;
    private boolean caching = true;
    private String name = "";
    private PageContext pageContext;
    private Tag parent;
    private SimpleHash root;
    private Template template;

    public int doAfterBody() {
        return 0;
    }

    public void doInitBody() {
    }

    public int doStartTag() {
        return 2;
    }

    public boolean getCaching() {
        return this.caching;
    }

    public void setCaching(boolean z) {
        this.caching = z;
    }

    public void setName(String str) {
        if (str == null) {
            str = "";
        }
        this.name = str;
    }

    public Tag getParent() {
        return this.parent;
    }

    public void setParent(Tag tag) {
        this.parent = tag;
    }

    public void setBodyContent(BodyContent bodyContent2) {
        this.bodyContent = bodyContent2;
    }

    public void setPageContext(PageContext pageContext2) {
        this.pageContext = pageContext2;
        this.root = null;
    }

    public void release() {
        this.root = null;
        this.template = null;
        this.name = "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007b, code lost:
        if (r7.caching == false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0088, code lost:
        if (r7.caching != false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008a, code lost:
        r7.template = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x008c, code lost:
        return 6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int doEndTag() throws javax.servlet.jsp.JspException {
        /*
            r7 = this;
            javax.servlet.jsp.tagext.BodyContent r0 = r7.bodyContent
            r1 = 6
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            r2 = 0
            freemarker.template.Template r3 = r7.template     // Catch:{ Exception -> 0x0080 }
            if (r3 != 0) goto L_0x0018
            freemarker.template.Template r3 = new freemarker.template.Template     // Catch:{ Exception -> 0x0080 }
            java.lang.String r4 = r7.name     // Catch:{ Exception -> 0x0080 }
            java.io.Reader r0 = r0.getReader()     // Catch:{ Exception -> 0x0080 }
            r3.<init>((java.lang.String) r4, (java.io.Reader) r0)     // Catch:{ Exception -> 0x0080 }
            r7.template = r3     // Catch:{ Exception -> 0x0080 }
        L_0x0018:
            freemarker.template.SimpleHash r0 = r7.root     // Catch:{ Exception -> 0x0080 }
            if (r0 != 0) goto L_0x006c
            freemarker.template.SimpleHash r0 = new freemarker.template.SimpleHash     // Catch:{ Exception -> 0x0080 }
            r0.<init>()     // Catch:{ Exception -> 0x0080 }
            r7.root = r0     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "page"
            freemarker.ext.jsp.JspContextModel r4 = new freemarker.ext.jsp.JspContextModel     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r5 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            r6 = 1
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0080 }
            r0.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0080 }
            freemarker.template.SimpleHash r0 = r7.root     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "request"
            freemarker.ext.jsp.JspContextModel r4 = new freemarker.ext.jsp.JspContextModel     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r5 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            r6 = 2
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0080 }
            r0.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0080 }
            freemarker.template.SimpleHash r0 = r7.root     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "session"
            freemarker.ext.jsp.JspContextModel r4 = new freemarker.ext.jsp.JspContextModel     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r5 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            r6 = 3
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0080 }
            r0.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0080 }
            freemarker.template.SimpleHash r0 = r7.root     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "application"
            freemarker.ext.jsp.JspContextModel r4 = new freemarker.ext.jsp.JspContextModel     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r5 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            r6 = 4
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0080 }
            r0.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0080 }
            freemarker.template.SimpleHash r0 = r7.root     // Catch:{ Exception -> 0x0080 }
            java.lang.String r3 = "any"
            freemarker.ext.jsp.JspContextModel r4 = new freemarker.ext.jsp.JspContextModel     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r5 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            r6 = -1
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0080 }
            r0.put((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0080 }
        L_0x006c:
            freemarker.template.Template r0 = r7.template     // Catch:{ Exception -> 0x0080 }
            freemarker.template.SimpleHash r3 = r7.root     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.PageContext r4 = r7.pageContext     // Catch:{ Exception -> 0x0080 }
            javax.servlet.jsp.JspWriter r4 = r4.getOut()     // Catch:{ Exception -> 0x0080 }
            r0.process(r3, r4)     // Catch:{ Exception -> 0x0080 }
            boolean r0 = r7.caching
            if (r0 != 0) goto L_0x008c
            goto L_0x008a
        L_0x007e:
            r0 = move-exception
            goto L_0x00a3
        L_0x0080:
            r0 = move-exception
            javax.servlet.jsp.PageContext r3 = r7.pageContext     // Catch:{ ServletException -> 0x0098, IOException -> 0x008d }
            r3.handlePageException(r0)     // Catch:{ ServletException -> 0x0098, IOException -> 0x008d }
            boolean r0 = r7.caching
            if (r0 != 0) goto L_0x008c
        L_0x008a:
            r7.template = r2
        L_0x008c:
            return r1
        L_0x008d:
            r0 = move-exception
            javax.servlet.jsp.JspException r1 = new javax.servlet.jsp.JspException     // Catch:{ all -> 0x007e }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x007e }
            r1.<init>(r0)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x0098:
            r0 = move-exception
            javax.servlet.jsp.JspException r1 = new javax.servlet.jsp.JspException     // Catch:{ all -> 0x007e }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x007e }
            r1.<init>(r0)     // Catch:{ all -> 0x007e }
            throw r1     // Catch:{ all -> 0x007e }
        L_0x00a3:
            boolean r1 = r7.caching
            if (r1 != 0) goto L_0x00a9
            r7.template = r2
        L_0x00a9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.jsp.FreemarkerTag.doEndTag():int");
    }
}
