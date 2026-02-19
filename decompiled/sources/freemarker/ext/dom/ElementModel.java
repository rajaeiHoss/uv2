package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.StringUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ElementModel extends NodeModel implements TemplateScalarModel {
    public boolean isEmpty() {
        return false;
    }

    public ElementModel(Element element) {
        super(element);
    }

    public TemplateModel get(String str) throws TemplateModelException {
        if (str.equals("*")) {
            NodeListModel nodeListModel = new NodeListModel((NodeModel) this);
            TemplateSequenceModel childNodes = getChildNodes();
            for (int i = 0; i < childNodes.size(); i++) {
                NodeModel nodeModel = (NodeModel) childNodes.get(i);
                if (nodeModel.node.getNodeType() == 1) {
                    nodeListModel.add((Object) nodeModel);
                }
            }
            return nodeListModel;
        } else if (str.equals("**")) {
            return new NodeListModel(((Element) this.node).getElementsByTagName("*"), (NodeModel) this);
        } else {
            if (str.startsWith("@")) {
                if (str.equals("@@") || str.equals("@*")) {
                    return new NodeListModel(this.node.getAttributes(), (NodeModel) this);
                }
                if (str.equals("@@start_tag")) {
                    return new SimpleScalar(new NodeOutputter(this.node).getOpeningTag((Element) this.node));
                }
                if (str.equals("@@end_tag")) {
                    return new SimpleScalar(new NodeOutputter(this.node).getClosingTag((Element) this.node));
                }
                if (str.equals("@@attributes_markup")) {
                    StringBuffer stringBuffer = new StringBuffer();
                    new NodeOutputter(this.node).outputContent(this.node.getAttributes(), stringBuffer);
                    return new SimpleScalar(stringBuffer.toString().trim());
                } else if (StringUtil.isXMLID(str.substring(1))) {
                    Attr attribute = getAttribute(str.substring(1));
                    if (attribute == null) {
                        return new NodeListModel((NodeModel) this);
                    }
                    return wrap(attribute);
                }
            }
            if (!StringUtil.isXMLID(str)) {
                return super.get(str);
            }
            NodeListModel filterByName = ((NodeListModel) getChildNodes()).filterByName(str);
            return filterByName.size() == 1 ? filterByName.get(0) : filterByName;
        }
    }

    public String getAsString() throws TemplateModelException {
        NodeList childNodes = this.node.getChildNodes();
        String str = "";
        int i = 0;
        while (i < childNodes.getLength()) {
            Node item = childNodes.item(i);
            short nodeType = item.getNodeType();
            if (nodeType != 1) {
                if (nodeType == 3 || nodeType == 4) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(str);
                    stringBuffer.append(item.getNodeValue());
                    str = stringBuffer.toString();
                }
                i++;
            } else {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Only elements with no child elements can be processed as text.\nThis element with name \"");
                stringBuffer2.append(this.node.getNodeName());
                stringBuffer2.append("\" has a child element named: ");
                stringBuffer2.append(item.getNodeName());
                throw new TemplateModelException(stringBuffer2.toString());
            }
        }
        return str;
    }

    public String getNodeName() {
        String localName = this.node.getLocalName();
        return (localName == null || localName.equals("")) ? this.node.getNodeName() : localName;
    }

    /* access modifiers changed from: package-private */
    public String getQualifiedName() {
        String str;
        String nodeName = getNodeName();
        String nodeNamespace = getNodeNamespace();
        if (nodeNamespace == null || nodeNamespace.length() == 0) {
            return nodeName;
        }
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        String defaultNS = currentEnvironment.getDefaultNS();
        if (defaultNS == null || !defaultNS.equals(nodeNamespace)) {
            str = currentEnvironment.getPrefixForNamespace(nodeNamespace);
        } else {
            str = Template.DEFAULT_NAMESPACE_PREFIX;
        }
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(":");
            str = stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(str);
        stringBuffer2.append(nodeName);
        return stringBuffer2.toString();
    }

    private Attr getAttribute(String str) {
        int indexOf;
        String str2;
        Element element = (Element) this.node;
        Attr attributeNode = element.getAttributeNode(str);
        if (attributeNode != null || (indexOf = str.indexOf(58)) <= 0) {
            return attributeNode;
        }
        String substring = str.substring(0, indexOf);
        if (substring.equals(Template.DEFAULT_NAMESPACE_PREFIX)) {
            str2 = Environment.getCurrentEnvironment().getDefaultNS();
        } else {
            str2 = Environment.getCurrentEnvironment().getNamespaceForPrefix(substring);
        }
        return str2 != null ? element.getAttributeNodeNS(str2, str.substring(indexOf + 1)) : attributeNode;
    }

    /* access modifiers changed from: package-private */
    public boolean matchesName(String str, Environment environment) {
        return StringUtil.matchesName(str, getNodeName(), getNodeNamespace(), environment);
    }
}
