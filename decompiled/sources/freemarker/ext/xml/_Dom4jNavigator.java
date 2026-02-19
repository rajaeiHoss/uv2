package freemarker.ext.xml;

import freemarker.ext.xml.Navigator;
import freemarker.template.TemplateModelException;
import java.io.StringWriter;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.tree.DefaultAttribute;
import org.jaxen.Context;
import org.jaxen.NamespaceContext;
import org.jaxen.dom4j.Dom4jXPath;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class _Dom4jNavigator extends Navigator {
    /* access modifiers changed from: package-private */
    public void getAsString(Object obj, StringWriter stringWriter) {
        stringWriter.getBuffer().append(((Node) obj).asXML());
    }

    /* access modifiers changed from: package-private */
    public void getChildren(Object obj, String str, String str2, List list) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str == null) {
                list.addAll(element.elements());
            } else {
                list.addAll(element.elements(element.getQName().getDocumentFactory().createQName(str, "", str2)));
            }
        } else if (obj instanceof Document) {
            Element rootElement = ((Document) obj).getRootElement();
            if (str == null || (equal(rootElement.getName(), str) && equal(rootElement.getNamespaceURI(), str2))) {
                list.add(rootElement);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getAttributes(Object obj, String str, String str2, List list) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str == null) {
                list.addAll(element.attributes());
                return;
            }
            Attribute attribute = element.attribute(element.getQName().getDocumentFactory().createQName(str, "", str2));
            if (attribute != null) {
                list.add(attribute);
            }
        } else if (obj instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
            if ("target".equals(str)) {
                list.add(new DefaultAttribute("target", processingInstruction.getTarget()));
            } else if (DataPacketExtension.ELEMENT_NAME.equals(str)) {
                list.add(new DefaultAttribute(DataPacketExtension.ELEMENT_NAME, processingInstruction.getText()));
            } else {
                list.add(new DefaultAttribute(str, processingInstruction.getValue(str)));
            }
        } else if (obj instanceof DocumentType) {
            DocumentType documentType = (DocumentType) obj;
            if ("publicId".equals(str)) {
                list.add(new DefaultAttribute("publicId", documentType.getPublicID()));
            } else if ("systemId".equals(str)) {
                list.add(new DefaultAttribute("systemId", documentType.getSystemID()));
            } else if ("elementName".equals(str)) {
                list.add(new DefaultAttribute("elementName", documentType.getElementName()));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getDescendants(Object obj, List list) {
        if (obj instanceof Branch) {
            getDescendants((Branch) obj, list);
        }
    }

    private void getDescendants(Branch branch, List list) {
        for (Node node : branch.content()) {
            if (node instanceof Element) {
                list.add(node);
                getDescendants((Object) node, list);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParent(Object obj) {
        return ((Node) obj).getParent();
    }

    /* access modifiers changed from: package-private */
    public Object getDocument(Object obj) {
        return ((Node) obj).getDocument();
    }

    /* access modifiers changed from: package-private */
    public Object getDocumentType(Object obj) {
        if (obj instanceof Document) {
            return ((Document) obj).getDocType();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void getContent(Object obj, List list) {
        if (obj instanceof Branch) {
            list.addAll(((Branch) obj).content());
        }
    }

    /* access modifiers changed from: package-private */
    public String getText(Object obj) {
        return ((Node) obj).getText();
    }

    /* access modifiers changed from: package-private */
    public String getLocalName(Object obj) {
        return ((Node) obj).getName();
    }

    /* access modifiers changed from: package-private */
    public String getNamespacePrefix(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getNamespacePrefix();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getNamespacePrefix();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getNamespaceUri(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getNamespaceURI();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getNamespaceURI();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public String getType(Object obj) {
        switch (((Node) obj).getNodeType()) {
            case 1:
                return "element";
            case 2:
                return "attribute";
            case 3:
                return "text";
            case 4:
                return "cdata";
            case 5:
                return "entityReference";
            case 7:
                return "processingInstruction";
            case 8:
                return "comment";
            case 9:
                return "document";
            case 10:
                return "documentType";
            case 13:
                return "namespace";
            default:
                return "unknown";
        }
    }

    /* access modifiers changed from: package-private */
    public Navigator.XPathEx createXPathEx(String str) throws TemplateModelException {
        try {
            return new Dom4jXPathEx(str);
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }

    private static final class Dom4jXPathEx extends Dom4jXPath implements Navigator.XPathEx {
        Dom4jXPathEx(String str) throws Exception {
            super(str);
        }

        public List selectNodes(Object obj, NamespaceContext namespaceContext) throws TemplateModelException {
            Context context = getContext(obj);
            context.getContextSupport().setNamespaceContext(namespaceContext);
            try {
                return selectNodesForContext(context);
            } catch (Exception e) {
                throw new TemplateModelException(e);
            }
        }
    }
}
