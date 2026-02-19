package freemarker.ext.xml;

import freemarker.ext.xml.Navigator;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.StringUtil;
import java.io.StringWriter;
import java.util.List;
import kotlin.text.Typography;
import org.jaxen.Context;
import org.jaxen.NamespaceContext;
import org.jaxen.dom.DOMXPath;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class _DomNavigator extends Navigator {
    /* access modifiers changed from: package-private */
    public void getAsString(Object obj, StringWriter stringWriter) {
        outputContent((Node) obj, stringWriter.getBuffer());
    }

    private void outputContent(Node node, StringBuffer stringBuffer) {
        switch (node.getNodeType()) {
            case 1:
                stringBuffer.append(Typography.less);
                stringBuffer.append(getQualifiedName(node));
                outputContent(node.getAttributes(), stringBuffer);
                stringBuffer.append(Typography.greater);
                outputContent(node.getChildNodes(), stringBuffer);
                stringBuffer.append("</");
                stringBuffer.append(getQualifiedName(node));
                stringBuffer.append(Typography.greater);
                return;
            case 2:
                stringBuffer.append(' ');
                stringBuffer.append(getQualifiedName(node));
                stringBuffer.append("=\"");
                stringBuffer.append(StringUtil.XMLEncNA(node.getNodeValue()));
                stringBuffer.append(Typography.quote);
                return;
            case 3:
                stringBuffer.append(StringUtil.XMLEncNQG(node.getNodeValue()));
                return;
            case 4:
                stringBuffer.append("<![CDATA[");
                stringBuffer.append(node.getNodeValue());
                stringBuffer.append("]]>");
                return;
            case 5:
                stringBuffer.append(Typography.amp);
                stringBuffer.append(node.getNodeName());
                stringBuffer.append(';');
                return;
            case 6:
                outputContent(node.getChildNodes(), stringBuffer);
                return;
            case 7:
                stringBuffer.append("<?");
                stringBuffer.append(node.getNodeName());
                stringBuffer.append(' ');
                stringBuffer.append(node.getNodeValue());
                stringBuffer.append("?>");
                return;
            case 8:
                stringBuffer.append("<!--");
                stringBuffer.append(node.getNodeValue());
                stringBuffer.append("-->");
                return;
            case 9:
                outputContent(node.getChildNodes(), stringBuffer);
                return;
            case 10:
                stringBuffer.append("<!DOCTYPE ");
                stringBuffer.append(node.getNodeName());
                DocumentType documentType = (DocumentType) node;
                if (documentType.getPublicId() != null) {
                    stringBuffer.append(" PUBLIC \"");
                    stringBuffer.append(documentType.getPublicId());
                    stringBuffer.append(Typography.quote);
                }
                if (documentType.getSystemId() != null) {
                    stringBuffer.append(Typography.quote);
                    stringBuffer.append(documentType.getSystemId());
                    stringBuffer.append(Typography.quote);
                }
                if (documentType.getInternalSubset() != null) {
                    stringBuffer.append(" [");
                    stringBuffer.append(documentType.getInternalSubset());
                    stringBuffer.append(']');
                }
                stringBuffer.append(Typography.greater);
                return;
            default:
                return;
        }
    }

    private void outputContent(NodeList nodeList, StringBuffer stringBuffer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            outputContent(nodeList.item(i), stringBuffer);
        }
    }

    private void outputContent(NamedNodeMap namedNodeMap, StringBuffer stringBuffer) {
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            outputContent(namedNodeMap.item(i), stringBuffer);
        }
    }

    /* access modifiers changed from: package-private */
    public void getChildren(Object obj, String str, String str2, List list) {
        if ("".equals(str2)) {
            str2 = null;
        }
        NodeList childNodes = ((Node) obj).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if ((item.getNodeType() == 1 || item.getNodeType() == 3) && (str == null || (equal(item.getNodeName(), str) && equal(item.getNamespaceURI(), str2)))) {
                list.add(item);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void getAttributes(Object obj, String str, String str2, List list) {
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (str == null) {
                NamedNodeMap attributes = element.getAttributes();
                for (int i = 0; i < attributes.getLength(); i++) {
                    list.add(attributes.item(i));
                }
                return;
            }
            if ("".equals(str2)) {
                str2 = null;
            }
            Attr attributeNodeNS = element.getAttributeNodeNS(str2, str);
            if (attributeNodeNS != null) {
                list.add(attributeNodeNS);
            }
        } else if (obj instanceof ProcessingInstruction) {
            ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
            if ("target".equals(str)) {
                list.add(createAttribute(processingInstruction, "target", processingInstruction.getTarget()));
            } else if (DataPacketExtension.ELEMENT_NAME.equals(str)) {
                list.add(createAttribute(processingInstruction, DataPacketExtension.ELEMENT_NAME, processingInstruction.getData()));
            }
        } else if (obj instanceof DocumentType) {
            DocumentType documentType = (DocumentType) obj;
            if ("publicId".equals(str)) {
                list.add(createAttribute(documentType, "publicId", documentType.getPublicId()));
            } else if ("systemId".equals(str)) {
                list.add(createAttribute(documentType, "systemId", documentType.getSystemId()));
            } else if ("elementName".equals(str)) {
                list.add(createAttribute(documentType, "elementName", documentType.getNodeName()));
            }
        }
    }

    private Attr createAttribute(Node node, String str, String str2) {
        Attr createAttribute = node.getOwnerDocument().createAttribute(str);
        createAttribute.setNodeValue(str2);
        return createAttribute;
    }

    /* access modifiers changed from: package-private */
    public void getDescendants(Object obj, List list) {
        NodeList childNodes = ((Node) obj).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1) {
                list.add(item);
                getDescendants(item, list);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object getParent(Object obj) {
        return ((Node) obj).getParentNode();
    }

    /* access modifiers changed from: package-private */
    public Object getDocument(Object obj) {
        return ((Node) obj).getOwnerDocument();
    }

    /* access modifiers changed from: package-private */
    public Object getDocumentType(Object obj) {
        if (obj instanceof Document) {
            return ((Document) obj).getDoctype();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void getContent(Object obj, List list) {
        NodeList childNodes = ((Node) obj).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            list.add(childNodes.item(i));
        }
    }

    /* access modifiers changed from: package-private */
    public String getText(Object obj) {
        StringBuffer stringBuffer = new StringBuffer();
        if (!(obj instanceof Element)) {
            return ((Node) obj).getNodeValue();
        }
        NodeList childNodes = ((Node) obj).getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            if (item instanceof Text) {
                stringBuffer.append(item.getNodeValue());
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getLocalName(Object obj) {
        return ((Node) obj).getNodeName();
    }

    /* access modifiers changed from: package-private */
    public String getNamespacePrefix(Object obj) {
        return ((Node) obj).getPrefix();
    }

    /* access modifiers changed from: package-private */
    public String getNamespaceUri(Object obj) {
        return ((Node) obj).getNamespaceURI();
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
            case 6:
                return "entity";
            case 7:
                return "processingInstruction";
            case 8:
                return "comment";
            case 9:
                return "document";
            case 10:
                return "documentType";
            default:
                return "unknown";
        }
    }

    /* access modifiers changed from: package-private */
    public Navigator.XPathEx createXPathEx(String str) throws TemplateModelException {
        try {
            return new DomXPathEx(str);
        } catch (Exception e) {
            throw new TemplateModelException(e);
        }
    }

    private static final class DomXPathEx extends DOMXPath implements Navigator.XPathEx {
        DomXPathEx(String str) throws Exception {
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
