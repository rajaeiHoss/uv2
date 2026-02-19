package freemarker.ext.dom;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xpath.internal.XPathContext;
import freemarker.core.Environment;
import freemarker.template.Template;
import java.util.List;
import org.w3c.dom.Node;

class SunInternalXalanXPathSupport implements XPathSupport {
    private static final String ERRMSG_EMPTY_NODE_SET = "Cannot perform an XPath query against an empty node set.(Note that there is no such restriction if you configure FreeMarker to use Jaxen instead of Xalan.)";
    private static final String ERRMSG_RECOMMEND_JAXEN = "(Note that there is no such restriction if you configure FreeMarker to use Jaxen instead of Xalan.)";
    private static PrefixResolver customPrefixResolver = new PrefixResolver() {
        public String getBaseIdentifier() {
            return null;
        }

        public boolean handlesNullPrefixes() {
            return false;
        }

        public String getNamespaceForPrefix(String str, Node node) {
            return getNamespaceForPrefix(str);
        }

        public String getNamespaceForPrefix(String str) {
            if (str.equals(Template.DEFAULT_NAMESPACE_PREFIX)) {
                return Environment.getCurrentEnvironment().getDefaultNS();
            }
            return Environment.getCurrentEnvironment().getNamespaceForPrefix(str);
        }
    };
    private XPathContext xpathContext = new XPathContext();

    SunInternalXalanXPathSupport() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ad, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c0, code lost:
        return r9.bool() ? freemarker.template.TemplateBooleanModel.TRUE : freemarker.template.TemplateBooleanModel.FALSE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized freemarker.template.TemplateModel executeQuery(java.lang.Object r8, java.lang.String r9) throws freemarker.template.TemplateModelException {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r8 instanceof org.w3c.dom.Node     // Catch:{ all -> 0x0113 }
            if (r0 != 0) goto L_0x006a
            if (r8 == 0) goto L_0x0062
            boolean r9 = isNodeList(r8)     // Catch:{ all -> 0x0113 }
            if (r9 == 0) goto L_0x003e
            java.util.List r8 = (java.util.List) r8     // Catch:{ all -> 0x0113 }
            int r8 = r8.size()     // Catch:{ all -> 0x0113 }
            if (r8 == 0) goto L_0x0036
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r0.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = "Cannot perform an XPath query against a node set of "
            r0.append(r1)     // Catch:{ all -> 0x0113 }
            r0.append(r8)     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = " nodes. Expecting a single node."
            r0.append(r8)     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = "(Note that there is no such restriction if you configure FreeMarker to use Jaxen instead of Xalan.)"
            r0.append(r8)     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0113 }
            r9.<init>((java.lang.String) r8)     // Catch:{ all -> 0x0113 }
            throw r9     // Catch:{ all -> 0x0113 }
        L_0x0036:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = "Cannot perform an XPath query against an empty node set.(Note that there is no such restriction if you configure FreeMarker to use Jaxen instead of Xalan.)"
            r8.<init>((java.lang.String) r9)     // Catch:{ all -> 0x0113 }
            throw r8     // Catch:{ all -> 0x0113 }
        L_0x003e:
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x0113 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0113 }
            r0.<init>()     // Catch:{ all -> 0x0113 }
            java.lang.String r1 = "Cannot perform an XPath query against a "
            r0.append(r1)     // Catch:{ all -> 0x0113 }
            java.lang.Class r8 = r8.getClass()     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x0113 }
            r0.append(r8)     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = ". Expecting a single org.w3c.dom.Node."
            r0.append(r8)     // Catch:{ all -> 0x0113 }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x0113 }
            r9.<init>((java.lang.String) r8)     // Catch:{ all -> 0x0113 }
            throw r9     // Catch:{ all -> 0x0113 }
        L_0x0062:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x0113 }
            java.lang.String r9 = "Cannot perform an XPath query against an empty node set.(Note that there is no such restriction if you configure FreeMarker to use Jaxen instead of Xalan.)"
            r8.<init>((java.lang.String) r9)     // Catch:{ all -> 0x0113 }
            throw r8     // Catch:{ all -> 0x0113 }
        L_0x006a:
            org.w3c.dom.Node r8 = (org.w3c.dom.Node) r8     // Catch:{ all -> 0x0113 }
            com.sun.org.apache.xpath.internal.XPath r6 = new com.sun.org.apache.xpath.internal.XPath     // Catch:{ TransformerException -> 0x010c }
            r2 = 0
            com.sun.org.apache.xml.internal.utils.PrefixResolver r3 = customPrefixResolver     // Catch:{ TransformerException -> 0x010c }
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r9
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ TransformerException -> 0x010c }
            com.sun.org.apache.xpath.internal.XPathContext r9 = r7.xpathContext     // Catch:{ TransformerException -> 0x010c }
            int r9 = r9.getDTMHandleFromNode(r8)     // Catch:{ TransformerException -> 0x010c }
            com.sun.org.apache.xpath.internal.XPathContext r0 = r7.xpathContext     // Catch:{ TransformerException -> 0x010c }
            com.sun.org.apache.xml.internal.utils.PrefixResolver r1 = customPrefixResolver     // Catch:{ TransformerException -> 0x010c }
            com.sun.org.apache.xpath.internal.objects.XObject r9 = r6.execute(r0, r9, r1)     // Catch:{ TransformerException -> 0x010c }
            boolean r0 = r9 instanceof com.sun.org.apache.xpath.internal.objects.XNodeSet     // Catch:{ TransformerException -> 0x010c }
            if (r0 == 0) goto L_0x00ae
            freemarker.ext.dom.NodeListModel r0 = new freemarker.ext.dom.NodeListModel     // Catch:{ TransformerException -> 0x010c }
            r0.<init>((org.w3c.dom.Node) r8)     // Catch:{ TransformerException -> 0x010c }
            r0.xpathSupport = r7     // Catch:{ TransformerException -> 0x010c }
            org.w3c.dom.traversal.NodeIterator r8 = r9.nodeset()     // Catch:{ TransformerException -> 0x010c }
        L_0x0095:
            org.w3c.dom.Node r9 = r8.nextNode()     // Catch:{ TransformerException -> 0x010c }
            if (r9 == 0) goto L_0x009e
            r0.add((java.lang.Object) r9)     // Catch:{ TransformerException -> 0x010c }
        L_0x009e:
            if (r9 != 0) goto L_0x0095
            int r8 = r0.size()     // Catch:{ TransformerException -> 0x010c }
            r9 = 1
            if (r8 != r9) goto L_0x00ac
            r8 = 0
            freemarker.template.TemplateModel r0 = r0.get(r8)     // Catch:{ TransformerException -> 0x010c }
        L_0x00ac:
            monitor-exit(r7)
            return r0
        L_0x00ae:
            boolean r8 = r9 instanceof com.sun.org.apache.xpath.internal.objects.XBoolean     // Catch:{ TransformerException -> 0x010c }
            if (r8 == 0) goto L_0x00c1
            com.sun.org.apache.xpath.internal.objects.XBoolean r9 = (com.sun.org.apache.xpath.internal.objects.XBoolean) r9     // Catch:{ TransformerException -> 0x010c }
            boolean r8 = r9.bool()     // Catch:{ TransformerException -> 0x010c }
            if (r8 == 0) goto L_0x00bd
            freemarker.template.TemplateBooleanModel r8 = freemarker.template.TemplateBooleanModel.TRUE     // Catch:{ TransformerException -> 0x010c }
            goto L_0x00bf
        L_0x00bd:
            freemarker.template.TemplateBooleanModel r8 = freemarker.template.TemplateBooleanModel.FALSE     // Catch:{ TransformerException -> 0x010c }
        L_0x00bf:
            monitor-exit(r7)
            return r8
        L_0x00c1:
            boolean r8 = r9 instanceof com.sun.org.apache.xpath.internal.objects.XNull     // Catch:{ TransformerException -> 0x010c }
            if (r8 == 0) goto L_0x00c8
            r8 = 0
            monitor-exit(r7)
            return r8
        L_0x00c8:
            boolean r8 = r9 instanceof com.sun.org.apache.xpath.internal.objects.XString     // Catch:{ TransformerException -> 0x010c }
            if (r8 == 0) goto L_0x00d7
            freemarker.template.SimpleScalar r8 = new freemarker.template.SimpleScalar     // Catch:{ TransformerException -> 0x010c }
            java.lang.String r9 = r9.toString()     // Catch:{ TransformerException -> 0x010c }
            r8.<init>(r9)     // Catch:{ TransformerException -> 0x010c }
            monitor-exit(r7)
            return r8
        L_0x00d7:
            boolean r8 = r9 instanceof com.sun.org.apache.xpath.internal.objects.XNumber     // Catch:{ TransformerException -> 0x010c }
            if (r8 == 0) goto L_0x00ed
            freemarker.template.SimpleNumber r8 = new freemarker.template.SimpleNumber     // Catch:{ TransformerException -> 0x010c }
            java.lang.Double r0 = new java.lang.Double     // Catch:{ TransformerException -> 0x010c }
            com.sun.org.apache.xpath.internal.objects.XNumber r9 = (com.sun.org.apache.xpath.internal.objects.XNumber) r9     // Catch:{ TransformerException -> 0x010c }
            double r1 = r9.num()     // Catch:{ TransformerException -> 0x010c }
            r0.<init>(r1)     // Catch:{ TransformerException -> 0x010c }
            r8.<init>((java.lang.Number) r0)     // Catch:{ TransformerException -> 0x010c }
            monitor-exit(r7)
            return r8
        L_0x00ed:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ TransformerException -> 0x010c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ TransformerException -> 0x010c }
            r0.<init>()     // Catch:{ TransformerException -> 0x010c }
            java.lang.String r1 = "Cannot deal with type: "
            r0.append(r1)     // Catch:{ TransformerException -> 0x010c }
            java.lang.Class r9 = r9.getClass()     // Catch:{ TransformerException -> 0x010c }
            java.lang.String r9 = r9.getName()     // Catch:{ TransformerException -> 0x010c }
            r0.append(r9)     // Catch:{ TransformerException -> 0x010c }
            java.lang.String r9 = r0.toString()     // Catch:{ TransformerException -> 0x010c }
            r8.<init>((java.lang.String) r9)     // Catch:{ TransformerException -> 0x010c }
            throw r8     // Catch:{ TransformerException -> 0x010c }
        L_0x010c:
            r8 = move-exception
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x0113 }
            r9.<init>((java.lang.Exception) r8)     // Catch:{ all -> 0x0113 }
            throw r9     // Catch:{ all -> 0x0113 }
        L_0x0113:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.dom.SunInternalXalanXPathSupport.executeQuery(java.lang.Object, java.lang.String):freemarker.template.TemplateModel");
    }

    private static boolean isNodeList(Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!(list.get(i) instanceof Node)) {
                return false;
            }
        }
        return true;
    }
}
