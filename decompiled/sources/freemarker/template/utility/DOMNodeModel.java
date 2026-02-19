package freemarker.template.utility;

import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateSequenceModel;
import java.util.HashMap;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOMNodeModel implements TemplateHashModel {
    private static HashMap equivalenceTable;
    private HashMap cache = new HashMap();
    /* access modifiers changed from: private */
    public Node node;

    public boolean isEmpty() {
        return false;
    }

    static {
        HashMap hashMap = new HashMap();
        equivalenceTable = hashMap;
        hashMap.put("*", "children");
        equivalenceTable.put("@*", "attributes");
    }

    public DOMNodeModel(Node node2) {
        this.node = node2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: freemarker.template.SimpleHash} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: freemarker.template.SimpleHash} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: freemarker.template.SimpleHash} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: freemarker.template.SimpleHash} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v44, resolved type: freemarker.template.SimpleHash} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: freemarker.template.SimpleScalar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v46, resolved type: freemarker.template.utility.DOMNodeModel$AncestorByName} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: freemarker.template.utility.DOMNodeModel$NodeListTM} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: freemarker.template.SimpleScalar} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v49, resolved type: freemarker.template.TemplateBooleanModel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v50, resolved type: freemarker.template.TemplateBooleanModel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: freemarker.template.TemplateBooleanModel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v52, resolved type: freemarker.template.TemplateBooleanModel} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v53, resolved type: freemarker.template.SimpleHash} */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel get(java.lang.String r6) throws freemarker.template.TemplateModelException {
        /*
            r5 = this;
            java.util.HashMap r0 = equivalenceTable
            boolean r0 = r0.containsKey(r6)
            if (r0 == 0) goto L_0x0010
            java.util.HashMap r0 = equivalenceTable
            java.lang.Object r6 = r0.get(r6)
            java.lang.String r6 = (java.lang.String) r6
        L_0x0010:
            java.util.HashMap r0 = r5.cache
            boolean r0 = r0.containsKey(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0022
            java.util.HashMap r0 = r5.cache
            java.lang.Object r0 = r0.get(r6)
            freemarker.template.TemplateModel r0 = (freemarker.template.TemplateModel) r0
            goto L_0x0023
        L_0x0022:
            r0 = r1
        L_0x0023:
            if (r0 != 0) goto L_0x01a8
            java.lang.String r2 = "attributes"
            boolean r2 = r2.equals(r6)
            r3 = 0
            if (r2 == 0) goto L_0x0055
            org.w3c.dom.Node r1 = r5.node
            org.w3c.dom.NamedNodeMap r1 = r1.getAttributes()
            if (r1 == 0) goto L_0x01a3
            freemarker.template.SimpleHash r0 = new freemarker.template.SimpleHash
            r0.<init>()
        L_0x003b:
            int r2 = r1.getLength()
            if (r3 >= r2) goto L_0x01a3
            org.w3c.dom.Node r2 = r1.item(r3)
            org.w3c.dom.Attr r2 = (org.w3c.dom.Attr) r2
            java.lang.String r4 = r2.getName()
            java.lang.String r2 = r2.getValue()
            r0.put((java.lang.String) r4, (java.lang.Object) r2)
            int r3 = r3 + 1
            goto L_0x003b
        L_0x0055:
            char r2 = r6.charAt(r3)
            r3 = 64
            if (r2 != r3) goto L_0x007e
            org.w3c.dom.Node r0 = r5.node
            boolean r1 = r0 instanceof org.w3c.dom.Element
            if (r1 == 0) goto L_0x0076
            org.w3c.dom.Element r0 = (org.w3c.dom.Element) r0
            r1 = 1
            java.lang.String r1 = r6.substring(r1)
            java.lang.String r0 = r0.getAttribute(r1)
            freemarker.template.SimpleScalar r1 = new freemarker.template.SimpleScalar
            r1.<init>(r0)
        L_0x0073:
            r0 = r1
            goto L_0x01a3
        L_0x0076:
            freemarker.template.TemplateModelException r6 = new freemarker.template.TemplateModelException
            java.lang.String r0 = "Trying to get an attribute value for a non-element node"
            r6.<init>((java.lang.String) r0)
            throw r6
        L_0x007e:
            java.lang.String r2 = "is_element"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0094
            org.w3c.dom.Node r0 = r5.node
            boolean r0 = r0 instanceof org.w3c.dom.Element
            if (r0 == 0) goto L_0x0090
            freemarker.template.TemplateBooleanModel r0 = freemarker.template.TemplateBooleanModel.TRUE
            goto L_0x01a3
        L_0x0090:
            freemarker.template.TemplateBooleanModel r0 = freemarker.template.TemplateBooleanModel.FALSE
            goto L_0x01a3
        L_0x0094:
            java.lang.String r2 = "is_text"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00aa
            org.w3c.dom.Node r0 = r5.node
            boolean r0 = r0 instanceof org.w3c.dom.Text
            if (r0 == 0) goto L_0x00a6
            freemarker.template.TemplateBooleanModel r0 = freemarker.template.TemplateBooleanModel.TRUE
            goto L_0x01a3
        L_0x00a6:
            freemarker.template.TemplateBooleanModel r0 = freemarker.template.TemplateBooleanModel.FALSE
            goto L_0x01a3
        L_0x00aa:
            java.lang.String r2 = "name"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00bf
            freemarker.template.SimpleScalar r0 = new freemarker.template.SimpleScalar
            org.w3c.dom.Node r1 = r5.node
            java.lang.String r1 = r1.getNodeName()
            r0.<init>(r1)
            goto L_0x01a3
        L_0x00bf:
            java.lang.String r2 = "children"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00d4
            freemarker.template.utility.DOMNodeModel$NodeListTM r0 = new freemarker.template.utility.DOMNodeModel$NodeListTM
            org.w3c.dom.Node r1 = r5.node
            org.w3c.dom.NodeList r1 = r1.getChildNodes()
            r0.<init>(r1)
            goto L_0x01a3
        L_0x00d4:
            java.lang.String r2 = "parent"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00eb
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Node r0 = r0.getParentNode()
            if (r0 != 0) goto L_0x00e5
            goto L_0x0073
        L_0x00e5:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x00eb:
            java.lang.String r2 = "ancestorByName"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x00fa
            freemarker.template.utility.DOMNodeModel$AncestorByName r0 = new freemarker.template.utility.DOMNodeModel$AncestorByName
            r0.<init>()
            goto L_0x01a3
        L_0x00fa:
            java.lang.String r2 = "nextSibling"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0113
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Node r0 = r0.getNextSibling()
            if (r0 != 0) goto L_0x010c
            goto L_0x0073
        L_0x010c:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x0113:
            java.lang.String r2 = "previousSibling"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x012c
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Node r0 = r0.getPreviousSibling()
            if (r0 != 0) goto L_0x0125
            goto L_0x0073
        L_0x0125:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x012c:
            java.lang.String r2 = "nextSiblingElement"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0145
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Element r0 = nextSiblingElement(r0)
            if (r0 != 0) goto L_0x013e
            goto L_0x0073
        L_0x013e:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x0145:
            java.lang.String r2 = "previousSiblingElement"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x015e
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Element r0 = previousSiblingElement(r0)
            if (r0 != 0) goto L_0x0157
            goto L_0x0073
        L_0x0157:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x015e:
            java.lang.String r2 = "nextElement"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0177
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Element r0 = nextElement(r0)
            if (r0 != 0) goto L_0x0170
            goto L_0x0073
        L_0x0170:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x0177:
            java.lang.String r2 = "previousElement"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0190
            org.w3c.dom.Node r0 = r5.node
            org.w3c.dom.Element r0 = previousElement(r0)
            if (r0 != 0) goto L_0x0189
            goto L_0x0073
        L_0x0189:
            freemarker.template.utility.DOMNodeModel r1 = new freemarker.template.utility.DOMNodeModel
            r1.<init>(r0)
            goto L_0x0073
        L_0x0190:
            java.lang.String r1 = "text"
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L_0x01a3
            freemarker.template.SimpleScalar r0 = new freemarker.template.SimpleScalar
            org.w3c.dom.Node r1 = r5.node
            java.lang.String r1 = getText(r1)
            r0.<init>(r1)
        L_0x01a3:
            java.util.HashMap r1 = r5.cache
            r1.put(r6, r0)
        L_0x01a8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.utility.DOMNodeModel.get(java.lang.String):freemarker.template.TemplateModel");
    }

    private static String getText(Node node2) {
        String str = "";
        if (node2 instanceof Text) {
            return ((Text) node2).getData();
        }
        if (!(node2 instanceof Element)) {
            return str;
        }
        NodeList childNodes = node2.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append(getText(childNodes.item(i)));
            str = stringBuffer.toString();
        }
        return str;
    }

    private static Element nextSiblingElement(Node node2) {
        while (node2 != null) {
            node2 = node2.getNextSibling();
            if (node2 instanceof Element) {
                return (Element) node2;
            }
        }
        return null;
    }

    private static Element previousSiblingElement(Node node2) {
        while (node2 != null) {
            node2 = node2.getPreviousSibling();
            if (node2 instanceof Element) {
                return (Element) node2;
            }
        }
        return null;
    }

    private static Element nextElement(Node node2) {
        if (node2.hasChildNodes()) {
            NodeList childNodes = node2.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item instanceof Element) {
                    return (Element) item;
                }
            }
        }
        Element nextSiblingElement = nextSiblingElement(node2);
        if (nextSiblingElement != null) {
            return nextSiblingElement;
        }
        for (Node parentNode = node2.getParentNode(); parentNode instanceof Element; parentNode = parentNode.getParentNode()) {
            Element nextSiblingElement2 = nextSiblingElement(parentNode);
            if (nextSiblingElement2 != null) {
                return nextSiblingElement2;
            }
        }
        return null;
    }

    private static Element previousElement(Node node2) {
        Element previousSiblingElement = previousSiblingElement(node2);
        if (previousSiblingElement != null) {
            return previousSiblingElement;
        }
        Node parentNode = node2.getParentNode();
        if (parentNode instanceof Element) {
            return (Element) parentNode;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void setParent(DOMNodeModel dOMNodeModel) {
        if (dOMNodeModel != null) {
            this.cache.put("parent", dOMNodeModel);
        }
    }

    /* access modifiers changed from: package-private */
    public String getNodeName() {
        return this.node.getNodeName();
    }

    class AncestorByName implements TemplateMethodModel {
        AncestorByName() {
        }

        public Object exec(List list) throws TemplateModelException {
            if (list.size() == 1) {
                String str = (String) list.get(0);
                for (DOMNodeModel dOMNodeModel = (DOMNodeModel) DOMNodeModel.this.get("parent"); dOMNodeModel != null; dOMNodeModel = (DOMNodeModel) dOMNodeModel.get("parent")) {
                    if (str.equals(dOMNodeModel.getNodeName())) {
                        return dOMNodeModel;
                    }
                }
                return null;
            }
            throw new TemplateModelException("Expecting exactly one string argument here");
        }
    }

    class NodeListTM implements TemplateSequenceModel, TemplateMethodModel {
        private NodeList nodeList;
        private TemplateModel[] nodes;

        NodeListTM(NodeList nodeList2) {
            this.nodeList = nodeList2;
            this.nodes = new TemplateModel[nodeList2.getLength()];
        }

        public TemplateModel get(int i) {
            DOMNodeModel dOMNodeModel = (DOMNodeModel) this.nodes[i];
            if (dOMNodeModel != null) {
                return dOMNodeModel;
            }
            DOMNodeModel dOMNodeModel2 = new DOMNodeModel(this.nodeList.item(i));
            this.nodes[i] = dOMNodeModel2;
            dOMNodeModel2.setParent(DOMNodeModel.this);
            return dOMNodeModel2;
        }

        public int size() {
            return this.nodes.length;
        }

        public Object exec(List list) throws TemplateModelException {
            if (list.size() != 1) {
                throw new TemplateModelException("Expecting exactly one string argument here");
            } else if (DOMNodeModel.this.node instanceof Element) {
                return new NodeListTM(((Element) DOMNodeModel.this.node).getElementsByTagName((String) list.get(0)));
            } else {
                throw new TemplateModelException("Expecting element here.");
            }
        }
    }
}
