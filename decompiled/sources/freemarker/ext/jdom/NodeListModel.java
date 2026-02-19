package freemarker.ext.jdom;

import freemarker.template.SimpleHash;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.Collections12;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.CDATA;
import org.jdom.Comment;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.EntityRef;
import org.jdom.Namespace;
import org.jdom.ProcessingInstruction;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class NodeListModel implements TemplateHashModel, TemplateMethodModel, TemplateCollectionModel, TemplateSequenceModel, TemplateScalarModel {
    private static final NodeOperator ALL_ATTRIBUTES_OP = new AllAttributesOp();
    private static final NodeOperator ALL_CHILDREN_OP = new AllChildrenOp();
    private static final NodeListModel EMPTY = new NodeListModel((List) null, false);
    private static final NamedNodeOperator NAMED_ATTRIBUTE_OP = new NamedAttributeOp();
    private static final NamedNodeOperator NAMED_CHILDREN_OP = new NamedChildrenOp();
    private static final Map OPERATIONS = createOperations();
    private static final AttributeXMLOutputter OUTPUT = new AttributeXMLOutputter();
    private static final Map SPECIAL_OPERATIONS = createSpecialOperations();
    private static final int SPECIAL_OPERATION_COPY = 0;
    private static final int SPECIAL_OPERATION_FILTER_NAME = 2;
    private static final int SPECIAL_OPERATION_FILTER_TYPE = 3;
    private static final int SPECIAL_OPERATION_PLAINTEXT = 6;
    private static final int SPECIAL_OPERATION_QUERY_TYPE = 4;
    private static final int SPECIAL_OPERATION_REGISTER_NAMESPACE = 5;
    private static final int SPECIAL_OPERATION_UNIQUE = 1;
    private static final Map XPATH_CACHE = new WeakHashMap();
    /* access modifiers changed from: private */
    public final Map namespaces;
    /* access modifiers changed from: private */
    public final List nodes;

    private interface NamedNodeOperator {
        List operate(Object obj, String str, Namespace namespace) throws TemplateModelException;
    }

    private interface NodeOperator {
        List operate(Object obj) throws TemplateModelException;
    }

    public NodeListModel(Document document) {
        this.nodes = document == null ? Collections.EMPTY_LIST : Collections12.singletonList(document);
        this.namespaces = new HashMap();
    }

    public NodeListModel(Element element) {
        this.nodes = element == null ? Collections.EMPTY_LIST : Collections12.singletonList(element);
        this.namespaces = new HashMap();
    }

    private NodeListModel(Object obj, Map map) {
        this.nodes = obj == null ? Collections.EMPTY_LIST : Collections12.singletonList(obj);
        this.namespaces = map;
    }

    public NodeListModel(List list) {
        this(list, true);
    }

    public NodeListModel(List list, boolean z) {
        if (z && list != null) {
            list = new ArrayList(list);
        } else if (list == null) {
            list = Collections.EMPTY_LIST;
        }
        this.nodes = list;
        this.namespaces = new HashMap();
    }

    private NodeListModel(List list, Map map) {
        this.nodes = list == null ? Collections.EMPTY_LIST : list;
        this.namespaces = map;
    }

    /* access modifiers changed from: private */
    public static final NodeListModel createNodeListModel(List list, Map map) {
        if (list == null || list.isEmpty()) {
            if (map.isEmpty()) {
                return EMPTY;
            }
            return new NodeListModel(Collections.EMPTY_LIST, map);
        } else if (list.size() == 1) {
            return new NodeListModel(list.get(0), map);
        } else {
            return new NodeListModel(list, map);
        }
    }

    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    public String getAsString() throws TemplateModelException {
        if (isEmpty()) {
            return "";
        }
        StringWriter stringWriter = new StringWriter(this.nodes.size() * 128);
        try {
            for (Object next : this.nodes) {
                if (next instanceof Element) {
                    OUTPUT.output((Element) next, stringWriter);
                } else if (next instanceof Attribute) {
                    OUTPUT.output((Attribute) next, stringWriter);
                } else if (next instanceof String) {
                    stringWriter.write(OUTPUT.escapeElementEntities(next.toString()));
                } else if (next instanceof Text) {
                    OUTPUT.output((Text) next, stringWriter);
                } else if (next instanceof Document) {
                    OUTPUT.output((Document) next, stringWriter);
                } else if (next instanceof ProcessingInstruction) {
                    OUTPUT.output((ProcessingInstruction) next, stringWriter);
                } else if (next instanceof Comment) {
                    OUTPUT.output((Comment) next, stringWriter);
                } else if (next instanceof CDATA) {
                    OUTPUT.output((CDATA) next, stringWriter);
                } else if (next instanceof DocType) {
                    OUTPUT.output((DocType) next, stringWriter);
                } else if (next instanceof EntityRef) {
                    OUTPUT.output((EntityRef) next, stringWriter);
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(next.getClass().getName());
                    stringBuffer.append(" is not a core JDOM class");
                    throw new TemplateModelException(stringBuffer.toString());
                }
            }
            return stringWriter.toString();
        } catch (IOException e) {
            throw new TemplateModelException(e.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b2 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public freemarker.template.TemplateModel get(java.lang.String r8) throws freemarker.template.TemplateModelException {
        /*
            r7 = this;
            boolean r0 = r7.isEmpty()
            if (r0 == 0) goto L_0x0009
            freemarker.ext.jdom.NodeListModel r8 = EMPTY
            return r8
        L_0x0009:
            if (r8 == 0) goto L_0x013d
            int r0 = r8.length()
            if (r0 == 0) goto L_0x013d
            r0 = 0
            char r1 = r8.charAt(r0)
            r2 = 42
            r3 = 1
            r4 = 0
            if (r1 == r2) goto L_0x00a7
            r5 = 64
            if (r1 == r5) goto L_0x008f
            r2 = 95
            if (r1 == r2) goto L_0x002c
            r2 = 120(0x78, float:1.68E-43)
            if (r1 == r2) goto L_0x002c
            r1 = r4
            r2 = r1
            goto L_0x00b0
        L_0x002c:
            java.util.Map r1 = OPERATIONS
            java.lang.Object r1 = r1.get(r8)
            freemarker.ext.jdom.NodeListModel$NodeOperator r1 = (freemarker.ext.jdom.NodeListModel.NodeOperator) r1
            if (r1 != 0) goto L_0x008b
            java.util.Map r2 = SPECIAL_OPERATIONS
            java.lang.Object r2 = r2.get(r8)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L_0x008b
            int r2 = r2.intValue()
            switch(r2) {
                case 0: goto L_0x0072;
                case 1: goto L_0x0064;
                case 2: goto L_0x005e;
                case 3: goto L_0x0058;
                case 4: goto L_0x0053;
                case 5: goto L_0x004d;
                case 6: goto L_0x0048;
                default: goto L_0x0047;
            }
        L_0x0047:
            goto L_0x008b
        L_0x0048:
            freemarker.template.SimpleScalar r8 = r7.getPlainText()
            return r8
        L_0x004d:
            freemarker.ext.jdom.NodeListModel$RegisterNamespace r8 = new freemarker.ext.jdom.NodeListModel$RegisterNamespace
            r8.<init>()
            return r8
        L_0x0053:
            freemarker.template.TemplateModel r8 = r7.getType()
            return r8
        L_0x0058:
            freemarker.ext.jdom.NodeListModel$TypeFilter r8 = new freemarker.ext.jdom.NodeListModel$TypeFilter
            r8.<init>()
            return r8
        L_0x005e:
            freemarker.ext.jdom.NodeListModel$NameFilter r8 = new freemarker.ext.jdom.NodeListModel$NameFilter
            r8.<init>()
            return r8
        L_0x0064:
            freemarker.ext.jdom.NodeListModel r8 = new freemarker.ext.jdom.NodeListModel
            java.util.List r0 = r7.nodes
            java.util.List r0 = removeDuplicates(r0)
            java.util.Map r1 = r7.namespaces
            r8.<init>((java.util.List) r0, (java.util.Map) r1)
            return r8
        L_0x0072:
            java.util.Map r2 = r7.namespaces
            monitor-enter(r2)
            freemarker.ext.jdom.NodeListModel r8 = new freemarker.ext.jdom.NodeListModel     // Catch:{ all -> 0x0088 }
            java.util.List r0 = r7.nodes     // Catch:{ all -> 0x0088 }
            java.util.Map r1 = r7.namespaces     // Catch:{ all -> 0x0088 }
            java.util.HashMap r1 = (java.util.HashMap) r1     // Catch:{ all -> 0x0088 }
            java.lang.Object r1 = r1.clone()     // Catch:{ all -> 0x0088 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x0088 }
            r8.<init>((java.util.List) r0, (java.util.Map) r1)     // Catch:{ all -> 0x0088 }
            monitor-exit(r2)     // Catch:{ all -> 0x0088 }
            return r8
        L_0x0088:
            r8 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0088 }
            throw r8
        L_0x008b:
            r2 = r4
            r4 = r1
            r1 = r2
            goto L_0x00b0
        L_0x008f:
            int r1 = r8.length()
            r5 = 2
            if (r1 != r5) goto L_0x00a0
            char r1 = r8.charAt(r3)
            if (r1 == r2) goto L_0x009d
            goto L_0x00a0
        L_0x009d:
            freemarker.ext.jdom.NodeListModel$NodeOperator r1 = ALL_ATTRIBUTES_OP
            goto L_0x008b
        L_0x00a0:
            freemarker.ext.jdom.NodeListModel$NamedNodeOperator r1 = NAMED_ATTRIBUTE_OP
            java.lang.String r2 = r8.substring(r3)
            goto L_0x00b0
        L_0x00a7:
            int r1 = r8.length()
            if (r1 != r3) goto L_0x0121
            freemarker.ext.jdom.NodeListModel$NodeOperator r1 = ALL_CHILDREN_OP
            goto L_0x008b
        L_0x00b0:
            if (r4 != 0) goto L_0x00b7
            if (r1 != 0) goto L_0x00b7
            freemarker.ext.jdom.NodeListModel$NamedNodeOperator r1 = NAMED_CHILDREN_OP
            goto L_0x00b8
        L_0x00b7:
            r8 = r2
        L_0x00b8:
            if (r4 == 0) goto L_0x00c1
            java.util.List r8 = r7.nodes
            java.util.List r8 = evaluateElementOperation(r4, r8)
            goto L_0x011a
        L_0x00c1:
            org.jdom.Namespace r2 = org.jdom.Namespace.NO_NAMESPACE
            r3 = 58
            int r3 = r8.indexOf(r3)
            r4 = -1
            if (r3 == r4) goto L_0x0114
            int r2 = r3 + 1
            java.lang.String r2 = r8.substring(r2)
            java.lang.String r8 = r8.substring(r0, r3)
            java.util.Map r0 = r7.namespaces
            monitor-enter(r0)
            java.util.Map r3 = r7.namespaces     // Catch:{ all -> 0x0111 }
            java.lang.Object r3 = r3.get(r8)     // Catch:{ all -> 0x0111 }
            org.jdom.Namespace r3 = (org.jdom.Namespace) r3     // Catch:{ all -> 0x0111 }
            monitor-exit(r0)     // Catch:{ all -> 0x0111 }
            if (r3 != 0) goto L_0x010e
            java.lang.String r0 = "xml"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x00f2
            org.jdom.Namespace r8 = org.jdom.Namespace.XML_NAMESPACE
            r6 = r2
            r2 = r8
            r8 = r6
            goto L_0x0114
        L_0x00f2:
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Unregistered namespace prefix '"
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = "'"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>((java.lang.String) r8)
            throw r0
        L_0x010e:
            r8 = r2
            r2 = r3
            goto L_0x0114
        L_0x0111:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0111 }
            throw r8
        L_0x0114:
            java.util.List r0 = r7.nodes
            java.util.List r8 = evaluateNamedElementOperation(r1, r8, r2, r0)
        L_0x011a:
            java.util.Map r0 = r7.namespaces
            freemarker.ext.jdom.NodeListModel r8 = createNodeListModel(r8, r0)
            return r8
        L_0x0121:
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Invalid key ["
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = "]"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>((java.lang.String) r8)
            throw r0
        L_0x013d:
            freemarker.template.TemplateModelException r0 = new freemarker.template.TemplateModelException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Invalid key ["
            r1.append(r2)
            r1.append(r8)
            java.lang.String r8 = "]"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>((java.lang.String) r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.jdom.NodeListModel.get(java.lang.String):freemarker.template.TemplateModel");
    }

    private TemplateModel getType() {
        char c;
        if (this.nodes.size() == 0) {
            return new SimpleScalar("");
        }
        Object obj = this.nodes.get(0);
        if (obj instanceof Element) {
            c = 'e';
        } else if ((obj instanceof Text) || (obj instanceof String)) {
            c = 'x';
        } else if (obj instanceof Attribute) {
            c = 'a';
        } else if (obj instanceof EntityRef) {
            c = 'n';
        } else if (obj instanceof Document) {
            c = 'd';
        } else if (obj instanceof DocType) {
            c = 't';
        } else {
            c = obj instanceof Comment ? 'c' : obj instanceof ProcessingInstruction ? 'p' : '?';
        }
        return new SimpleScalar(new String(new char[]{c}));
    }

    private SimpleScalar getPlainText() throws TemplateModelException {
        List<Object> evaluateElementOperation = evaluateElementOperation((TextOp) OPERATIONS.get("_text"), this.nodes);
        StringBuffer stringBuffer = new StringBuffer();
        for (Object append : evaluateElementOperation) {
            stringBuffer.append(append);
        }
        return new SimpleScalar(stringBuffer.toString());
    }

    public TemplateModelIterator iterator() {
        return new TemplateModelIterator() {
            private final Iterator it;

            {
                this.it = NodeListModel.this.nodes.iterator();
            }

            public TemplateModel next() {
                if (this.it.hasNext()) {
                    return new NodeListModel(this.it.next(), NodeListModel.this.namespaces);
                }
                return null;
            }

            public boolean hasNext() {
                return this.it.hasNext();
            }
        };
    }

    public TemplateModel get(int i) throws TemplateModelException {
        try {
            return new NodeListModel(this.nodes.get(i), this.namespaces);
        } catch (IndexOutOfBoundsException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Index out of bounds: ");
            stringBuffer.append(e.getMessage());
            throw new TemplateModelException(stringBuffer.toString());
        }
    }

    public int size() {
        return this.nodes.size();
    }

    public Object exec(List list) throws TemplateModelException {
        JDOMXPathEx jDOMXPathEx;
        if (list == null || list.size() != 1) {
            throw new TemplateModelException("Exactly one argument required for execute() on NodeTemplate");
        }
        String str = (String) list.get(0);
        try {
            Map map = XPATH_CACHE;
            synchronized (map) {
                jDOMXPathEx = (JDOMXPathEx) map.get(str);
                if (jDOMXPathEx == null) {
                    jDOMXPathEx = new JDOMXPathEx(str);
                    map.put(str, jDOMXPathEx);
                }
            }
            return createNodeListModel(jDOMXPathEx.selectNodes(this.nodes, this.namespaces), this.namespaces);
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Could not evaulate XPath expression ");
            stringBuffer.append(str);
            throw new TemplateModelException(stringBuffer.toString(), e);
        }
    }

    public void registerNamespace(String str, String str2) {
        synchronized (this.namespaces) {
            this.namespaces.put(str, Namespace.getNamespace(str, str2));
        }
    }

    private static final class AllChildrenOp implements NodeOperator {
        private AllChildrenOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return ((Element) obj).getChildren();
            }
            if (!(obj instanceof Document)) {
                return null;
            }
            Element rootElement = ((Document) obj).getRootElement();
            return rootElement == null ? Collections.EMPTY_LIST : Collections12.singletonList(rootElement);
        }
    }

    private static final class NamedChildrenOp implements NamedNodeOperator {
        private NamedChildrenOp() {
        }

        public List operate(Object obj, String str, Namespace namespace) {
            if (obj instanceof Element) {
                return ((Element) obj).getChildren(str, namespace);
            }
            if (!(obj instanceof Document)) {
                return null;
            }
            Element rootElement = ((Document) obj).getRootElement();
            if (rootElement == null || !rootElement.getName().equals(str) || !rootElement.getNamespaceURI().equals(namespace.getURI())) {
                return Collections.EMPTY_LIST;
            }
            return Collections12.singletonList(rootElement);
        }
    }

    private static final class AllAttributesOp implements NodeOperator {
        private AllAttributesOp() {
        }

        public List operate(Object obj) {
            if (!(obj instanceof Element)) {
                return null;
            }
            return ((Element) obj).getAttributes();
        }
    }

    private static final class NamedAttributeOp implements NamedNodeOperator {
        private NamedAttributeOp() {
        }

        public List operate(Object obj, String str, Namespace namespace) {
            Attribute attribute;
            Attribute attribute2;
            if (obj instanceof Element) {
                attribute = ((Element) obj).getAttribute(str, namespace);
            } else {
                if (obj instanceof ProcessingInstruction) {
                    ProcessingInstruction processingInstruction = (ProcessingInstruction) obj;
                    if ("target".equals(str)) {
                        attribute2 = new Attribute("target", processingInstruction.getTarget());
                    } else if (DataPacketExtension.ELEMENT_NAME.equals(str)) {
                        attribute2 = new Attribute(DataPacketExtension.ELEMENT_NAME, processingInstruction.getData());
                    } else {
                        attribute = new Attribute(str, processingInstruction.getValue(str));
                    }
                } else if (!(obj instanceof DocType)) {
                    return null;
                } else {
                    DocType docType = (DocType) obj;
                    if ("publicId".equals(str)) {
                        attribute2 = new Attribute("publicId", docType.getPublicID());
                    } else if ("systemId".equals(str)) {
                        attribute2 = new Attribute("systemId", docType.getSystemID());
                    } else if ("elementName".equals(str)) {
                        attribute2 = new Attribute("elementName", docType.getElementName());
                    } else {
                        attribute = null;
                    }
                }
                attribute = attribute2;
            }
            return attribute == null ? Collections.EMPTY_LIST : Collections12.singletonList(attribute);
        }
    }

    private static final class NameOp implements NodeOperator {
        private NameOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return Collections12.singletonList(((Element) obj).getName());
            }
            if (obj instanceof Attribute) {
                return Collections12.singletonList(((Attribute) obj).getName());
            }
            if (obj instanceof EntityRef) {
                return Collections12.singletonList(((EntityRef) obj).getName());
            }
            if (obj instanceof ProcessingInstruction) {
                return Collections12.singletonList(((ProcessingInstruction) obj).getTarget());
            }
            if (obj instanceof DocType) {
                return Collections12.singletonList(((DocType) obj).getPublicID());
            }
            return null;
        }
    }

    private static final class QNameOp implements NodeOperator {
        private QNameOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return Collections12.singletonList(((Element) obj).getQualifiedName());
            }
            if (obj instanceof Attribute) {
                return Collections12.singletonList(((Attribute) obj).getQualifiedName());
            }
            return null;
        }
    }

    private static final class NamespaceUriOp implements NodeOperator {
        private NamespaceUriOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return Collections12.singletonList(((Element) obj).getNamespace().getURI());
            }
            if (obj instanceof Attribute) {
                return Collections12.singletonList(((Attribute) obj).getNamespace().getURI());
            }
            return null;
        }
    }

    private static final class NamespacePrefixOp implements NodeOperator {
        private NamespacePrefixOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return Collections12.singletonList(((Element) obj).getNamespace().getPrefix());
            }
            if (obj instanceof Attribute) {
                return Collections12.singletonList(((Attribute) obj).getNamespace().getPrefix());
            }
            return null;
        }
    }

    private static final class CanonicalNameOp implements NodeOperator {
        private CanonicalNameOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                Element element = (Element) obj;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(element.getNamespace().getURI());
                stringBuffer.append(element.getName());
                return Collections12.singletonList(stringBuffer.toString());
            } else if (!(obj instanceof Attribute)) {
                return null;
            } else {
                Attribute attribute = (Attribute) obj;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(attribute.getNamespace().getURI());
                stringBuffer2.append(attribute.getName());
                return Collections12.singletonList(stringBuffer2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public static final Element getParent(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getParent();
        }
        if (obj instanceof Attribute) {
            return ((Attribute) obj).getParent();
        }
        if (obj instanceof Text) {
            return ((Text) obj).getParent();
        }
        if (obj instanceof ProcessingInstruction) {
            return ((ProcessingInstruction) obj).getParent();
        }
        if (obj instanceof Comment) {
            return ((Comment) obj).getParent();
        }
        if (obj instanceof EntityRef) {
            return ((EntityRef) obj).getParent();
        }
        return null;
    }

    private static final class ParentOp implements NodeOperator {
        private ParentOp() {
        }

        public List operate(Object obj) {
            Element access$1100 = NodeListModel.getParent(obj);
            return access$1100 == null ? Collections.EMPTY_LIST : Collections12.singletonList(access$1100);
        }
    }

    private static final class AncestorOp implements NodeOperator {
        private AncestorOp() {
        }

        public List operate(Object obj) {
            Element access$1100 = NodeListModel.getParent(obj);
            if (access$1100 == null) {
                return Collections.EMPTY_LIST;
            }
            LinkedList linkedList = new LinkedList();
            do {
                linkedList.addFirst(access$1100);
                access$1100 = access$1100.getParent();
            } while (access$1100 != null);
            return linkedList;
        }
    }

    private static final class AncestorOrSelfOp implements NodeOperator {
        private AncestorOrSelfOp() {
        }

        public List operate(Object obj) {
            Element access$1100 = NodeListModel.getParent(obj);
            if (access$1100 == null) {
                return Collections12.singletonList(obj);
            }
            LinkedList linkedList = new LinkedList();
            linkedList.addFirst(obj);
            do {
                linkedList.addFirst(access$1100);
                access$1100 = access$1100.getParent();
            } while (access$1100 != null);
            return linkedList;
        }
    }

    private static class DescendantOp implements NodeOperator {
        private DescendantOp() {
        }

        public List operate(Object obj) {
            LinkedList linkedList = new LinkedList();
            if (obj instanceof Element) {
                addChildren((Element) obj, linkedList);
            } else if (!(obj instanceof Document)) {
                return null;
            } else {
                Element rootElement = ((Document) obj).getRootElement();
                linkedList.add(rootElement);
                addChildren(rootElement, linkedList);
            }
            return linkedList;
        }

        private void addChildren(Element element, List list) {
            for (Element element2 : element.getChildren()) {
                list.add(element2);
                addChildren(element2, list);
            }
        }
    }

    private static final class DescendantOrSelfOp extends DescendantOp {
        private DescendantOrSelfOp() {
            super();
        }

        public List operate(Object obj) {
            LinkedList linkedList = (LinkedList) super.operate(obj);
            linkedList.addFirst(obj);
            return linkedList;
        }
    }

    private static final class DocumentOp implements NodeOperator {
        private DocumentOp() {
        }

        public List operate(Object obj) {
            Document document;
            if (obj instanceof Element) {
                document = ((Element) obj).getDocument();
            } else {
                if (obj instanceof Attribute) {
                    Element parent = ((Attribute) obj).getParent();
                    if (parent != null) {
                        document = parent.getDocument();
                    }
                } else if (obj instanceof Text) {
                    Element parent2 = ((Text) obj).getParent();
                    if (parent2 != null) {
                        document = parent2.getDocument();
                    }
                } else if (obj instanceof Document) {
                    document = (Document) obj;
                } else if (obj instanceof ProcessingInstruction) {
                    document = ((ProcessingInstruction) obj).getDocument();
                } else if (obj instanceof EntityRef) {
                    document = ((EntityRef) obj).getDocument();
                } else if (!(obj instanceof Comment)) {
                    return null;
                } else {
                    document = ((Comment) obj).getDocument();
                }
                document = null;
            }
            return document == null ? Collections.EMPTY_LIST : Collections12.singletonList(document);
        }
    }

    private static final class DocTypeOp implements NodeOperator {
        private DocTypeOp() {
        }

        public List operate(Object obj) {
            if (!(obj instanceof Document)) {
                return null;
            }
            DocType docType = ((Document) obj).getDocType();
            return docType == null ? Collections.EMPTY_LIST : Collections12.singletonList(docType);
        }
    }

    private static final class ContentOp implements NodeOperator {
        private ContentOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return ((Element) obj).getContent();
            }
            if (obj instanceof Document) {
                return ((Document) obj).getContent();
            }
            return null;
        }
    }

    private static final class TextOp implements NodeOperator {
        private TextOp() {
        }

        public List operate(Object obj) {
            if (obj instanceof Element) {
                return Collections12.singletonList(((Element) obj).getTextTrim());
            }
            if (obj instanceof Attribute) {
                return Collections12.singletonList(((Attribute) obj).getValue());
            }
            if (obj instanceof CDATA) {
                return Collections12.singletonList(((CDATA) obj).getText());
            }
            if (obj instanceof Comment) {
                return Collections12.singletonList(((Comment) obj).getText());
            }
            if (obj instanceof ProcessingInstruction) {
                return Collections12.singletonList(((ProcessingInstruction) obj).getData());
            }
            return null;
        }
    }

    private static final List evaluateElementOperation(NodeOperator nodeOperator, List list) throws TemplateModelException {
        int size = list.size();
        List[] listArr = new List[size];
        int i = 0;
        int i2 = 0;
        for (Object operate : list) {
            List operate2 = nodeOperator.operate(operate);
            if (operate2 != null) {
                listArr[i2] = operate2;
                i += operate2.size();
                i2++;
            }
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < size; i3++) {
            if (listArr[i3] != null) {
                arrayList.addAll(listArr[i3]);
            }
        }
        return arrayList;
    }

    private static final List evaluateNamedElementOperation(NamedNodeOperator namedNodeOperator, String str, Namespace namespace, List list) throws TemplateModelException {
        int size = list.size();
        List[] listArr = new List[size];
        int i = 0;
        int i2 = 0;
        for (Object operate : list) {
            List operate2 = namedNodeOperator.operate(operate, str, namespace);
            listArr[i2] = operate2;
            i += operate2.size();
            i2++;
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.addAll(listArr[i3]);
        }
        return arrayList;
    }

    private static final List removeDuplicates(List list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        HashSet hashSet = new HashSet((size * 4) / 3, 0.75f);
        for (Object next : list) {
            if (hashSet.add(next)) {
                arrayList.add(next);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    private static final Map createOperations() {
        HashMap hashMap = new HashMap();
        hashMap.put("_ancestor", new AncestorOp());
        hashMap.put("_ancestorOrSelf", new AncestorOrSelfOp());
        hashMap.put("_attributes", ALL_ATTRIBUTES_OP);
        hashMap.put("_children", ALL_CHILDREN_OP);
        hashMap.put("_cname", new CanonicalNameOp());
        hashMap.put("_content", new ContentOp());
        hashMap.put("_descendant", new DescendantOp());
        hashMap.put("_descendantOrSelf", new DescendantOrSelfOp());
        hashMap.put("_document", new DocumentOp());
        hashMap.put("_doctype", new DocTypeOp());
        hashMap.put("_name", new NameOp());
        hashMap.put("_nsprefix", new NamespacePrefixOp());
        hashMap.put("_nsuri", new NamespaceUriOp());
        hashMap.put("_parent", new ParentOp());
        hashMap.put("_qname", new QNameOp());
        hashMap.put("_text", new TextOp());
        return hashMap;
    }

    private static final Map createSpecialOperations() {
        HashMap hashMap = new HashMap();
        Integer num = new Integer(0);
        Integer num2 = new Integer(1);
        Integer num3 = new Integer(2);
        Integer num4 = new Integer(3);
        Integer num5 = new Integer(4);
        Integer num6 = new Integer(5);
        Integer num7 = new Integer(6);
        hashMap.put("_copy", num);
        hashMap.put("_unique", num2);
        hashMap.put("_fname", num3);
        hashMap.put("_ftype", num4);
        hashMap.put("_type", num5);
        hashMap.put("_registerNamespace", num6);
        hashMap.put("_plaintext", num7);
        hashMap.put("x_copy", num);
        hashMap.put("x_unique", num2);
        hashMap.put("x_fname", num3);
        hashMap.put("x_ftype", num4);
        hashMap.put("x_type", num5);
        return hashMap;
    }

    private final class RegisterNamespace implements TemplateMethodModel {
        public boolean isEmpty() {
            return false;
        }

        private RegisterNamespace() {
        }

        public Object exec(List list) throws TemplateModelException {
            if (list.size() == 2) {
                NodeListModel.this.registerNamespace((String) list.get(0), (String) list.get(1));
                return TemplateScalarModel.EMPTY_STRING;
            }
            throw new TemplateModelException("_registerNamespace(prefix, uri) requires two arguments");
        }
    }

    private final class NameFilter implements TemplateMethodModel {
        public boolean isEmpty() {
            return false;
        }

        private NameFilter() {
        }

        public Object exec(List list) {
            HashSet hashSet = new HashSet(list);
            LinkedList linkedList = new LinkedList(NodeListModel.this.nodes);
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                String str = null;
                if (next instanceof Element) {
                    str = ((Element) next).getName();
                } else if (next instanceof Attribute) {
                    str = ((Attribute) next).getName();
                } else if (next instanceof ProcessingInstruction) {
                    str = ((ProcessingInstruction) next).getTarget();
                } else if (next instanceof EntityRef) {
                    str = ((EntityRef) next).getName();
                } else if (next instanceof DocType) {
                    str = ((DocType) next).getPublicID();
                }
                if (str == null || !hashSet.contains(str)) {
                    it.remove();
                }
            }
            return NodeListModel.createNodeListModel(linkedList, NodeListModel.this.namespaces);
        }
    }

    private final class TypeFilter implements TemplateMethodModel {
        public boolean isEmpty() {
            return false;
        }

        private TypeFilter() {
        }

        public Object exec(List list) throws TemplateModelException {
            if (list == null || list.size() == 0) {
                throw new TemplateModelException("_type expects exactly one argument");
            }
            boolean z = false;
            String str = (String) list.get(0);
            boolean z2 = str.indexOf(33) != -1;
            boolean z3 = z2 != (str.indexOf(97) == -1);
            boolean z4 = z2 != (str.indexOf(99) == -1);
            boolean z5 = z2 != (str.indexOf(100) == -1);
            boolean z6 = z2 != (str.indexOf(101) == -1);
            boolean z7 = z2 != (str.indexOf(110) == -1);
            boolean z8 = z2 != (str.indexOf(112) == -1);
            boolean z9 = z2 != (str.indexOf(116) == -1);
            if (z2 != (str.indexOf(120) == -1)) {
                z = true;
            }
            LinkedList linkedList = new LinkedList(NodeListModel.this.nodes);
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (((next instanceof Element) && z6) || (((next instanceof Attribute) && z3) || (((next instanceof String) && z) || (((next instanceof Text) && z) || (((next instanceof ProcessingInstruction) && z8) || (((next instanceof Comment) && z4) || (((next instanceof EntityRef) && z7) || (((next instanceof Document) && z5) || ((next instanceof DocType) && z9))))))))) {
                    it.remove();
                }
            }
            return NodeListModel.createNodeListModel(linkedList, NodeListModel.this.namespaces);
        }
    }

    public static void main(String[] strArr) throws Exception {
        Document build = new SAXBuilder().build(System.in);
        SimpleHash simpleHash = new SimpleHash();
        simpleHash.put("document", (Object) new NodeListModel(build));
        Template template = new Template(strArr[0], (Reader) new FileReader(strArr[0]));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        template.process(simpleHash, outputStreamWriter);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    private static final class AttributeXMLOutputter extends XMLOutputter {
        private AttributeXMLOutputter() {
        }

        public void output(Attribute attribute, Writer writer) throws IOException {
            writer.write(" ");
            writer.write(attribute.getQualifiedName());
            writer.write("=");
            writer.write("\"");
            writer.write(escapeAttributeEntities(attribute.getValue()));
            writer.write("\"");
        }
    }

    private static final class JDOMXPathEx extends JDOMXPath {
        JDOMXPathEx(String str) throws JaxenException {
            super(str);
        }

        public List selectNodes(Object obj, Map map) throws JaxenException {
            Context context = getContext(obj);
            context.getContextSupport().setNamespaceContext(new NamespaceContextImpl(map));
            return selectNodesForContext(context);
        }

        private static final class NamespaceContextImpl implements NamespaceContext {
            private final Map namespaces;

            NamespaceContextImpl(Map map) {
                this.namespaces = map;
            }

            public String translateNamespacePrefixToUri(String str) {
                String str2;
                if (str.length() == 0) {
                    return str;
                }
                synchronized (this.namespaces) {
                    Namespace namespace = (Namespace) this.namespaces.get(str);
                    if (namespace == null) {
                        str2 = null;
                    } else {
                        str2 = namespace.getURI();
                    }
                }
                return str2;
            }
        }
    }
}
