package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.utility.StringUtil;
import java.util.HashMap;
import kotlin.text.Typography;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class NodeOutputter {
    private Element contextNode;
    private String defaultNS;
    private Environment env;
    private boolean explicitDefaultNSPrefix;
    private boolean hasDefaultNS;
    private String namespaceDecl;
    private HashMap namespacesToPrefixLookup = new HashMap();

    NodeOutputter(Node node) {
        if (node instanceof Element) {
            setContext((Element) node);
        } else if (node instanceof Attr) {
            setContext(((Attr) node).getOwnerElement());
        } else if (node instanceof Document) {
            setContext(((Document) node).getDocumentElement());
        }
    }

    private void setContext(Element element) {
        this.contextNode = element;
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        this.env = currentEnvironment;
        String defaultNS2 = currentEnvironment.getDefaultNS();
        this.defaultNS = defaultNS2;
        this.hasDefaultNS = defaultNS2 != null && defaultNS2.length() > 0;
        this.namespacesToPrefixLookup.put((Object) null, "");
        this.namespacesToPrefixLookup.put("", "");
        buildPrefixLookup(element);
        if (!this.explicitDefaultNSPrefix && this.hasDefaultNS) {
            this.namespacesToPrefixLookup.put(this.defaultNS, "");
        }
        constructNamespaceDecl();
    }

    private void buildPrefixLookup(Node node) {
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI != null && namespaceURI.length() > 0) {
            this.namespacesToPrefixLookup.put(namespaceURI, this.env.getPrefixForNamespace(namespaceURI));
        } else if (this.hasDefaultNS && node.getNodeType() == 1) {
            this.namespacesToPrefixLookup.put(this.defaultNS, Template.DEFAULT_NAMESPACE_PREFIX);
            this.explicitDefaultNSPrefix = true;
        } else if (node.getNodeType() == 2 && this.hasDefaultNS && this.defaultNS.equals(namespaceURI)) {
            this.namespacesToPrefixLookup.put(this.defaultNS, Template.DEFAULT_NAMESPACE_PREFIX);
            this.explicitDefaultNSPrefix = true;
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            buildPrefixLookup(childNodes.item(i));
        }
    }

    private void constructNamespaceDecl() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.explicitDefaultNSPrefix) {
            stringBuffer.append(" xmlns=\"");
            stringBuffer.append(this.defaultNS);
            stringBuffer.append("\"");
        }
        for (String str : this.namespacesToPrefixLookup.keySet()) {
            if (!(str == null || str.length() == 0)) {
                String str2 = (String) this.namespacesToPrefixLookup.get(str);
                if (str2 == null) {
                    int i = 0;
                    while (true) {
                        if (i >= 26) {
                            break;
                        }
                        String str3 = new String(new char[]{(char) (i + 97)});
                        if (this.env.getNamespaceForPrefix(str3) == null) {
                            str2 = str3;
                            break;
                        } else {
                            str2 = null;
                            i++;
                        }
                    }
                    if (str2 != null) {
                        this.namespacesToPrefixLookup.put(str, str2);
                    } else {
                        throw new RuntimeException("This will almost never happen!");
                    }
                }
                stringBuffer.append(" xmlns");
                if (str2.length() > 0) {
                    stringBuffer.append(":");
                    stringBuffer.append(str2);
                }
                stringBuffer.append("=\"");
                stringBuffer.append(str);
                stringBuffer.append("\"");
            }
        }
        this.namespaceDecl = stringBuffer.toString();
    }

    private void outputQualifiedName(Node node, StringBuffer stringBuffer) {
        String namespaceURI = node.getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() == 0) {
            stringBuffer.append(node.getNodeName());
            return;
        }
        String str = (String) this.namespacesToPrefixLookup.get(namespaceURI);
        if (str == null) {
            stringBuffer.append(node.getNodeName());
            return;
        }
        if (str.length() > 0) {
            stringBuffer.append(str);
            stringBuffer.append(':');
        }
        stringBuffer.append(node.getLocalName());
    }

    /* access modifiers changed from: package-private */
    public void outputContent(Node node, StringBuffer stringBuffer) {
        switch (node.getNodeType()) {
            case 1:
                stringBuffer.append(Typography.less);
                outputQualifiedName(node, stringBuffer);
                if (node == this.contextNode) {
                    stringBuffer.append(this.namespaceDecl);
                }
                outputContent(node.getAttributes(), stringBuffer);
                if (node.getChildNodes().getLength() == 0) {
                    stringBuffer.append(" />");
                    return;
                }
                stringBuffer.append(Typography.greater);
                outputContent(node.getChildNodes(), stringBuffer);
                stringBuffer.append("</");
                outputQualifiedName(node, stringBuffer);
                stringBuffer.append(Typography.greater);
                return;
            case 2:
                if (((Attr) node).getSpecified()) {
                    stringBuffer.append(' ');
                    outputQualifiedName(node, stringBuffer);
                    stringBuffer.append("=\"");
                    stringBuffer.append(StringUtil.XMLEncQAttr(node.getNodeValue()));
                    stringBuffer.append(Typography.quote);
                    return;
                }
                return;
            case 3:
            case 4:
                stringBuffer.append(StringUtil.XMLEncNQG(node.getNodeValue()));
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
                    stringBuffer.append(" \"");
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

    /* access modifiers changed from: package-private */
    public void outputContent(NodeList nodeList, StringBuffer stringBuffer) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            outputContent(nodeList.item(i), stringBuffer);
        }
    }

    /* access modifiers changed from: package-private */
    public void outputContent(NamedNodeMap namedNodeMap, StringBuffer stringBuffer) {
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node item = namedNodeMap.item(i);
            if (item.getNodeType() != 2 || (!item.getNodeName().startsWith("xmlns:") && !item.getNodeName().equals("xmlns"))) {
                outputContent(item, stringBuffer);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getOpeningTag(Element element) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Typography.less);
        outputQualifiedName(element, stringBuffer);
        stringBuffer.append(this.namespaceDecl);
        outputContent(element.getAttributes(), stringBuffer);
        stringBuffer.append(Typography.greater);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String getClosingTag(Element element) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("</");
        outputQualifiedName(element, stringBuffer);
        stringBuffer.append(Typography.greater);
        return stringBuffer.toString();
    }
}
