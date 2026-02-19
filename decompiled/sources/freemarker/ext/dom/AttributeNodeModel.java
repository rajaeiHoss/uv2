package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateScalarModel;
import org.w3c.dom.Attr;

class AttributeNodeModel extends NodeModel implements TemplateScalarModel {
    public boolean isEmpty() {
        return true;
    }

    public AttributeNodeModel(Attr attr) {
        super(attr);
    }

    public String getAsString() {
        return ((Attr) this.node).getValue();
    }

    public String getNodeName() {
        String localName = this.node.getLocalName();
        return (localName == null || localName.equals("")) ? this.node.getNodeName() : localName;
    }

    /* access modifiers changed from: package-private */
    public String getQualifiedName() {
        String str;
        String namespaceURI = this.node.getNamespaceURI();
        if (namespaceURI == null || namespaceURI.equals("")) {
            return this.node.getNodeName();
        }
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        if (namespaceURI.equals(currentEnvironment.getDefaultNS())) {
            str = Template.DEFAULT_NAMESPACE_PREFIX;
        } else {
            str = currentEnvironment.getPrefixForNamespace(namespaceURI);
        }
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(":");
        stringBuffer.append(this.node.getLocalName());
        return stringBuffer.toString();
    }
}
