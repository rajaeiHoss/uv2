package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.Template;
import java.util.List;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPathContext;
import org.w3c.dom.Node;

class XalanXPathSupport implements XPathSupport {
    private static final String ERRMSG_EMPTY_NODE_SET = "Cannot perform an XPath query against an empty node set.";
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

    XalanXPathSupport() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a8, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bb, code lost:
        return r9.bool() ? freemarker.template.TemplateBooleanModel.TRUE : freemarker.template.TemplateBooleanModel.FALSE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized freemarker.template.TemplateModel executeQuery(java.lang.Object r8, java.lang.String r9) throws freemarker.template.TemplateModelException {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r8 instanceof org.w3c.dom.Node     // Catch:{ all -> 0x010e }
            if (r0 != 0) goto L_0x0065
            if (r8 == 0) goto L_0x005d
            boolean r9 = isNodeList(r8)     // Catch:{ all -> 0x010e }
            if (r9 == 0) goto L_0x0039
            java.util.List r8 = (java.util.List) r8     // Catch:{ all -> 0x010e }
            int r8 = r8.size()     // Catch:{ all -> 0x010e }
            if (r8 == 0) goto L_0x0031
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x010e }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ all -> 0x010e }
            r0.<init>()     // Catch:{ all -> 0x010e }
            java.lang.String r1 = "Cannot perform an XPath query against a node set of "
            r0.append(r1)     // Catch:{ all -> 0x010e }
            r0.append(r8)     // Catch:{ all -> 0x010e }
            java.lang.String r8 = " nodes. Expecting a single node."
            r0.append(r8)     // Catch:{ all -> 0x010e }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x010e }
            r9.<init>((java.lang.String) r8)     // Catch:{ all -> 0x010e }
            throw r9     // Catch:{ all -> 0x010e }
        L_0x0031:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x010e }
            java.lang.String r9 = "Cannot perform an XPath query against an empty node set."
            r8.<init>((java.lang.String) r9)     // Catch:{ all -> 0x010e }
            throw r8     // Catch:{ all -> 0x010e }
        L_0x0039:
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x010e }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ all -> 0x010e }
            r0.<init>()     // Catch:{ all -> 0x010e }
            java.lang.String r1 = "Cannot perform an XPath query against a "
            r0.append(r1)     // Catch:{ all -> 0x010e }
            java.lang.Class r8 = r8.getClass()     // Catch:{ all -> 0x010e }
            java.lang.String r8 = r8.getName()     // Catch:{ all -> 0x010e }
            r0.append(r8)     // Catch:{ all -> 0x010e }
            java.lang.String r8 = ". Expecting a single org.w3c.dom.Node."
            r0.append(r8)     // Catch:{ all -> 0x010e }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x010e }
            r9.<init>((java.lang.String) r8)     // Catch:{ all -> 0x010e }
            throw r9     // Catch:{ all -> 0x010e }
        L_0x005d:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x010e }
            java.lang.String r9 = "Cannot perform an XPath query against an empty node set."
            r8.<init>((java.lang.String) r9)     // Catch:{ all -> 0x010e }
            throw r8     // Catch:{ all -> 0x010e }
        L_0x0065:
            org.w3c.dom.Node r8 = (org.w3c.dom.Node) r8     // Catch:{ all -> 0x010e }
            org.apache.xpath.XPath r6 = new org.apache.xpath.XPath     // Catch:{ TransformerException -> 0x0107 }
            r2 = 0
            org.apache.xml.utils.PrefixResolver r3 = customPrefixResolver     // Catch:{ TransformerException -> 0x0107 }
            r4 = 0
            r5 = 0
            r0 = r6
            r1 = r9
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ TransformerException -> 0x0107 }
            org.apache.xpath.XPathContext r9 = r7.xpathContext     // Catch:{ TransformerException -> 0x0107 }
            int r9 = r9.getDTMHandleFromNode(r8)     // Catch:{ TransformerException -> 0x0107 }
            org.apache.xpath.XPathContext r0 = r7.xpathContext     // Catch:{ TransformerException -> 0x0107 }
            org.apache.xml.utils.PrefixResolver r1 = customPrefixResolver     // Catch:{ TransformerException -> 0x0107 }
            org.apache.xpath.objects.XObject r9 = r6.execute(r0, r9, r1)     // Catch:{ TransformerException -> 0x0107 }
            boolean r0 = r9 instanceof org.apache.xpath.objects.XNodeSet     // Catch:{ TransformerException -> 0x0107 }
            if (r0 == 0) goto L_0x00a9
            freemarker.ext.dom.NodeListModel r0 = new freemarker.ext.dom.NodeListModel     // Catch:{ TransformerException -> 0x0107 }
            r0.<init>((org.w3c.dom.Node) r8)     // Catch:{ TransformerException -> 0x0107 }
            r0.xpathSupport = r7     // Catch:{ TransformerException -> 0x0107 }
            org.w3c.dom.traversal.NodeIterator r8 = r9.nodeset()     // Catch:{ TransformerException -> 0x0107 }
        L_0x0090:
            org.w3c.dom.Node r9 = r8.nextNode()     // Catch:{ TransformerException -> 0x0107 }
            if (r9 == 0) goto L_0x0099
            r0.add((java.lang.Object) r9)     // Catch:{ TransformerException -> 0x0107 }
        L_0x0099:
            if (r9 != 0) goto L_0x0090
            int r8 = r0.size()     // Catch:{ TransformerException -> 0x0107 }
            r9 = 1
            if (r8 != r9) goto L_0x00a7
            r8 = 0
            freemarker.template.TemplateModel r0 = r0.get(r8)     // Catch:{ TransformerException -> 0x0107 }
        L_0x00a7:
            monitor-exit(r7)
            return r0
        L_0x00a9:
            boolean r8 = r9 instanceof org.apache.xpath.objects.XBoolean     // Catch:{ TransformerException -> 0x0107 }
            if (r8 == 0) goto L_0x00bc
            org.apache.xpath.objects.XBoolean r9 = (org.apache.xpath.objects.XBoolean) r9     // Catch:{ TransformerException -> 0x0107 }
            boolean r8 = r9.bool()     // Catch:{ TransformerException -> 0x0107 }
            if (r8 == 0) goto L_0x00b8
            freemarker.template.TemplateBooleanModel r8 = freemarker.template.TemplateBooleanModel.TRUE     // Catch:{ TransformerException -> 0x0107 }
            goto L_0x00ba
        L_0x00b8:
            freemarker.template.TemplateBooleanModel r8 = freemarker.template.TemplateBooleanModel.FALSE     // Catch:{ TransformerException -> 0x0107 }
        L_0x00ba:
            monitor-exit(r7)
            return r8
        L_0x00bc:
            boolean r8 = r9 instanceof org.apache.xpath.objects.XNull     // Catch:{ TransformerException -> 0x0107 }
            if (r8 == 0) goto L_0x00c3
            r8 = 0
            monitor-exit(r7)
            return r8
        L_0x00c3:
            boolean r8 = r9 instanceof org.apache.xpath.objects.XString     // Catch:{ TransformerException -> 0x0107 }
            if (r8 == 0) goto L_0x00d2
            freemarker.template.SimpleScalar r8 = new freemarker.template.SimpleScalar     // Catch:{ TransformerException -> 0x0107 }
            java.lang.String r9 = r9.toString()     // Catch:{ TransformerException -> 0x0107 }
            r8.<init>(r9)     // Catch:{ TransformerException -> 0x0107 }
            monitor-exit(r7)
            return r8
        L_0x00d2:
            boolean r8 = r9 instanceof org.apache.xpath.objects.XNumber     // Catch:{ TransformerException -> 0x0107 }
            if (r8 == 0) goto L_0x00e8
            freemarker.template.SimpleNumber r8 = new freemarker.template.SimpleNumber     // Catch:{ TransformerException -> 0x0107 }
            java.lang.Double r0 = new java.lang.Double     // Catch:{ TransformerException -> 0x0107 }
            org.apache.xpath.objects.XNumber r9 = (org.apache.xpath.objects.XNumber) r9     // Catch:{ TransformerException -> 0x0107 }
            double r1 = r9.num()     // Catch:{ TransformerException -> 0x0107 }
            r0.<init>(r1)     // Catch:{ TransformerException -> 0x0107 }
            r8.<init>((java.lang.Number) r0)     // Catch:{ TransformerException -> 0x0107 }
            monitor-exit(r7)
            return r8
        L_0x00e8:
            freemarker.template.TemplateModelException r8 = new freemarker.template.TemplateModelException     // Catch:{ TransformerException -> 0x0107 }
            java.lang.StringBuffer r0 = new java.lang.StringBuffer     // Catch:{ TransformerException -> 0x0107 }
            r0.<init>()     // Catch:{ TransformerException -> 0x0107 }
            java.lang.String r1 = "Cannot deal with type: "
            r0.append(r1)     // Catch:{ TransformerException -> 0x0107 }
            java.lang.Class r9 = r9.getClass()     // Catch:{ TransformerException -> 0x0107 }
            java.lang.String r9 = r9.getName()     // Catch:{ TransformerException -> 0x0107 }
            r0.append(r9)     // Catch:{ TransformerException -> 0x0107 }
            java.lang.String r9 = r0.toString()     // Catch:{ TransformerException -> 0x0107 }
            r8.<init>((java.lang.String) r9)     // Catch:{ TransformerException -> 0x0107 }
            throw r8     // Catch:{ TransformerException -> 0x0107 }
        L_0x0107:
            r8 = move-exception
            freemarker.template.TemplateModelException r9 = new freemarker.template.TemplateModelException     // Catch:{ all -> 0x010e }
            r9.<init>((java.lang.Exception) r8)     // Catch:{ all -> 0x010e }
            throw r9     // Catch:{ all -> 0x010e }
        L_0x010e:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.dom.XalanXPathSupport.executeQuery(java.lang.Object, java.lang.String):freemarker.template.TemplateModel");
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
