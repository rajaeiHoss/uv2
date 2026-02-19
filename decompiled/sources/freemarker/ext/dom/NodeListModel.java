package freemarker.ext.dom;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleScalar;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class NodeListModel extends SimpleSequence implements TemplateHashModel {
    private static ObjectWrapper nodeWrapper = new ObjectWrapper() {
        public TemplateModel wrap(Object obj) {
            if (obj instanceof NodeModel) {
                return (NodeModel) obj;
            }
            return NodeModel.wrap((Node) obj);
        }
    };
    NodeModel contextNode;
    XPathSupport xpathSupport;

    NodeListModel(Node node) {
        this(NodeModel.wrap(node));
    }

    NodeListModel(NodeModel nodeModel) {
        super(nodeWrapper);
        this.contextNode = nodeModel;
    }

    NodeListModel(NodeList nodeList, NodeModel nodeModel) {
        super(nodeWrapper);
        for (int i = 0; i < nodeList.getLength(); i++) {
            this.list.add(nodeList.item(i));
        }
        this.contextNode = nodeModel;
    }

    NodeListModel(NamedNodeMap namedNodeMap, NodeModel nodeModel) {
        super(nodeWrapper);
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            this.list.add(namedNodeMap.item(i));
        }
        this.contextNode = nodeModel;
    }

    NodeListModel(List list, NodeModel nodeModel) {
        super(list, nodeWrapper);
        this.contextNode = nodeModel;
    }

    /* access modifiers changed from: package-private */
    public NodeListModel filterByName(String str) throws TemplateModelException {
        NodeListModel nodeListModel = new NodeListModel(this.contextNode);
        int size = size();
        if (size == 0) {
            return nodeListModel;
        }
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        for (int i = 0; i < size; i++) {
            NodeModel nodeModel = (NodeModel) get(i);
            if ((nodeModel instanceof ElementModel) && ((ElementModel) nodeModel).matchesName(str, currentEnvironment)) {
                nodeListModel.add((Object) nodeModel);
            }
        }
        return nodeListModel;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        int i;
        if (size() == 1) {
            return ((NodeModel) get(0)).get(str);
        }
        if (str.equals("@@markup") || str.equals("@@nested_markup") || str.equals("@@text")) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < size(); i2++) {
                stringBuffer.append(((TemplateScalarModel) ((NodeModel) get(i2)).get(str)).getAsString());
            }
            return new SimpleScalar(stringBuffer.toString());
        } else if (StringUtil.isXMLID(str) || ((str.startsWith("@") && StringUtil.isXMLID(str.substring(1))) || str.equals("*") || str.equals("**") || str.equals("@@") || str.equals("@*"))) {
            NodeListModel nodeListModel = new NodeListModel(this.contextNode);
            for (int i3 = 0; i3 < size(); i3++) {
                NodeModel nodeModel = (NodeModel) get(i3);
                if (nodeModel instanceof ElementModel) {
                    TemplateSequenceModel templateSequenceModel = (TemplateSequenceModel) ((ElementModel) nodeModel).get(str);
                    if (templateSequenceModel == null) {
                        i = 0;
                    } else {
                        i = templateSequenceModel.size();
                    }
                    for (int i4 = 0; i4 < i; i4++) {
                        nodeListModel.add((Object) templateSequenceModel.get(i4));
                    }
                }
            }
            if (nodeListModel.size() == 1) {
                return nodeListModel.get(0);
            }
            return nodeListModel;
        } else {
            XPathSupport xPathSupport = getXPathSupport();
            if (xPathSupport != null) {
                return xPathSupport.executeQuery(size() == 0 ? null : rawNodeList(), str);
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Key: '");
            stringBuffer2.append(str);
            stringBuffer2.append("' is not legal for a node sequence (");
            stringBuffer2.append(getClass().getName());
            stringBuffer2.append("). This node sequence contains ");
            stringBuffer2.append(size());
            stringBuffer2.append(" node(s). ");
            stringBuffer2.append("Some keys are valid only for node sequences of size 1. ");
            stringBuffer2.append("If you use Xalan (instead of Jaxen), XPath expression keys work only with ");
            stringBuffer2.append("node lists of size 1.");
            throw new TemplateModelException(stringBuffer2.toString());
        }
    }

    private List rawNodeList() throws TemplateModelException {
        int size = size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(((NodeModel) get(i)).node);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public XPathSupport getXPathSupport() throws TemplateModelException {
        if (this.xpathSupport == null) {
            NodeModel nodeModel = this.contextNode;
            if (nodeModel != null) {
                this.xpathSupport = nodeModel.getXPathSupport();
            } else if (size() > 0) {
                this.xpathSupport = ((NodeModel) get(0)).getXPathSupport();
            }
        }
        return this.xpathSupport;
    }
}
