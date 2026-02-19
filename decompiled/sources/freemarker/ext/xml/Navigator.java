package freemarker.ext.xml;

import freemarker.template.TemplateModelException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.jaxen.NamespaceContext;

abstract class Navigator {
    private final NodeOperator attributeOperator = getOperator("_attributes");
    private final NodeOperator childrenOperator = getOperator("_children");
    private final Map operators = createOperatorMap();
    private final Map xpathCache = new WeakHashMap();

    interface XPathEx {
        List selectNodes(Object obj, NamespaceContext namespaceContext) throws TemplateModelException;
    }

    /* access modifiers changed from: package-private */
    public abstract XPathEx createXPathEx(String str) throws TemplateModelException;

    /* access modifiers changed from: package-private */
    public abstract void getAsString(Object obj, StringWriter stringWriter) throws TemplateModelException;

    /* access modifiers changed from: package-private */
    public abstract void getAttributes(Object obj, String str, String str2, List list);

    /* access modifiers changed from: package-private */
    public abstract void getChildren(Object obj, String str, String str2, List list);

    /* access modifiers changed from: package-private */
    public abstract void getContent(Object obj, List list);

    /* access modifiers changed from: package-private */
    public abstract void getDescendants(Object obj, List list);

    /* access modifiers changed from: package-private */
    public abstract Object getDocument(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object getDocumentType(Object obj);

    /* access modifiers changed from: package-private */
    public abstract String getLocalName(Object obj);

    /* access modifiers changed from: package-private */
    public abstract String getNamespacePrefix(Object obj);

    /* access modifiers changed from: package-private */
    public abstract String getNamespaceUri(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object getParent(Object obj);

    /* access modifiers changed from: package-private */
    public abstract String getText(Object obj);

    /* access modifiers changed from: package-private */
    public abstract String getType(Object obj);

    Navigator() {
    }

    /* access modifiers changed from: package-private */
    public NodeOperator getOperator(String str) {
        return (NodeOperator) this.operators.get(str);
    }

    /* access modifiers changed from: package-private */
    public NodeOperator getAttributeOperator() {
        return this.attributeOperator;
    }

    /* access modifiers changed from: package-private */
    public NodeOperator getChildrenOperator() {
        return this.childrenOperator;
    }

    /* access modifiers changed from: package-private */
    public List applyXPath(List list, String str, Object obj) throws TemplateModelException {
        XPathEx xPathEx;
        try {
            synchronized (this.xpathCache) {
                xPathEx = (XPathEx) this.xpathCache.get(str);
                if (xPathEx == null) {
                    xPathEx = createXPathEx(str);
                    this.xpathCache.put(str, xPathEx);
                }
            }
            return xPathEx.selectNodes(list, (NamespaceContext) obj);
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not evaulate XPath expression ");
            stringBuffer.append(str);
            throw new TemplateModelException(stringBuffer.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public void getAncestors(Object obj, List list) {
        while (true) {
            obj = getParent(obj);
            if (obj != null) {
                list.add(obj);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getQualifiedName(Object obj) {
        String localName = getLocalName(obj);
        if (localName == null) {
            return null;
        }
        String namespacePrefix = getNamespacePrefix(obj);
        if (namespacePrefix == null || namespacePrefix.length() == 0) {
            return localName;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(namespacePrefix);
        stringBuffer.append(":");
        stringBuffer.append(localName);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean equal(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    private Map createOperatorMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("_attributes", new AttributesOp());
        hashMap.put("@*", hashMap.get("_attributes"));
        hashMap.put("_children", new ChildrenOp());
        hashMap.put("*", hashMap.get("_children"));
        hashMap.put("_descendantOrSelf", new DescendantOrSelfOp());
        hashMap.put("_descendant", new DescendantOp());
        hashMap.put("_document", new DocumentOp());
        hashMap.put("_doctype", new DocumentTypeOp());
        hashMap.put("_ancestor", new AncestorOp());
        hashMap.put("_ancestorOrSelf", new AncestorOrSelfOp());
        hashMap.put("_content", new ContentOp());
        hashMap.put("_name", new LocalNameOp());
        hashMap.put("_nsprefix", new NamespacePrefixOp());
        hashMap.put("_nsuri", new NamespaceUriOp());
        hashMap.put("_parent", new ParentOp());
        hashMap.put("_qname", new QualifiedNameOp());
        hashMap.put("_text", new TextOp());
        hashMap.put("_type", new TypeOp());
        return hashMap;
    }

    private class ChildrenOp implements NodeOperator {
        private ChildrenOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Navigator.this.getChildren(obj, str, str2, list);
        }
    }

    private class AttributesOp implements NodeOperator {
        private AttributesOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Navigator.this.getAttributes(obj, str, str2, list);
        }
    }

    private class DescendantOrSelfOp implements NodeOperator {
        private DescendantOrSelfOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            list.add(obj);
            Navigator.this.getDescendants(obj, list);
        }
    }

    private class DescendantOp implements NodeOperator {
        private DescendantOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Navigator.this.getDescendants(obj, list);
        }
    }

    private class AncestorOrSelfOp implements NodeOperator {
        private AncestorOrSelfOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            list.add(obj);
            Navigator.this.getAncestors(obj, list);
        }
    }

    private class AncestorOp implements NodeOperator {
        private AncestorOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Navigator.this.getAncestors(obj, list);
        }
    }

    private class ParentOp implements NodeOperator {
        private ParentOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Object parent = Navigator.this.getParent(obj);
            if (parent != null) {
                list.add(parent);
            }
        }
    }

    private class DocumentOp implements NodeOperator {
        private DocumentOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Object document = Navigator.this.getDocument(obj);
            if (document != null) {
                list.add(document);
            }
        }
    }

    private class DocumentTypeOp implements NodeOperator {
        private DocumentTypeOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Object documentType = Navigator.this.getDocumentType(obj);
            if (documentType != null) {
                list.add(documentType);
            }
        }
    }

    private class ContentOp implements NodeOperator {
        private ContentOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            Navigator.this.getContent(obj, list);
        }
    }

    private class TextOp implements NodeOperator {
        private TextOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            String text = Navigator.this.getText(obj);
            if (text != null) {
                list.add(text);
            }
        }
    }

    private class LocalNameOp implements NodeOperator {
        private LocalNameOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            String localName = Navigator.this.getLocalName(obj);
            if (localName != null) {
                list.add(localName);
            }
        }
    }

    private class QualifiedNameOp implements NodeOperator {
        private QualifiedNameOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            String qualifiedName = Navigator.this.getQualifiedName(obj);
            if (qualifiedName != null) {
                list.add(qualifiedName);
            }
        }
    }

    private class NamespacePrefixOp implements NodeOperator {
        private NamespacePrefixOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            String namespacePrefix = Navigator.this.getNamespacePrefix(obj);
            if (namespacePrefix != null) {
                list.add(namespacePrefix);
            }
        }
    }

    private class NamespaceUriOp implements NodeOperator {
        private NamespaceUriOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            String namespaceUri = Navigator.this.getNamespaceUri(obj);
            if (namespaceUri != null) {
                list.add(namespaceUri);
            }
        }
    }

    private class TypeOp implements NodeOperator {
        private TypeOp() {
        }

        public void process(Object obj, String str, String str2, List list) {
            list.add(Navigator.this.getType(obj));
        }
    }
}
