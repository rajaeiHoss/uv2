package freemarker.ext.dom;

import freemarker.ext.util.WrapperTemplateModel;
import freemarker.log.Logger;
import freemarker.template.AdapterTemplateModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateSequenceModel;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class NodeModel implements TemplateNodeModel, TemplateHashModel, TemplateSequenceModel, AdapterTemplateModel, WrapperTemplateModel {
    private static final Object STATIC_LOCK = new Object();
    static /* synthetic */ Class class$freemarker$ext$dom$XPathSupport;
    private static DocumentBuilderFactory docBuilderFactory;
    private static ErrorHandler errorHandler;
    private static XPathSupport jaxenXPathSupport;
    static final Logger logger = Logger.getLogger("freemarker.dom");
    static Class xpathSupportClass;
    private static final Map xpathSupportMap = Collections.synchronizedMap(new WeakHashMap());
    private TemplateSequenceModel children;
    final Node node;
    private NodeModel parent;

    public final TemplateModel get(int i) {
        if (i == 0) {
            return this;
        }
        return null;
    }

    public final int size() {
        return 1;
    }

    static {
        try {
            useDefaultXPathSupport();
        } catch (Exception unused) {
        }
        if (xpathSupportClass == null) {
            Logger logger2 = logger;
            if (logger2.isWarnEnabled()) {
                logger2.warn("No XPath support is available.");
            }
        }
    }

    public static void setDocumentBuilderFactory(DocumentBuilderFactory documentBuilderFactory) {
        synchronized (STATIC_LOCK) {
            docBuilderFactory = documentBuilderFactory;
        }
    }

    public static DocumentBuilderFactory getDocumentBuilderFactory() {
        DocumentBuilderFactory documentBuilderFactory;
        synchronized (STATIC_LOCK) {
            if (docBuilderFactory == null) {
                DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                newInstance.setNamespaceAware(true);
                newInstance.setIgnoringElementContentWhitespace(true);
                docBuilderFactory = newInstance;
            }
            documentBuilderFactory = docBuilderFactory;
        }
        return documentBuilderFactory;
    }

    public static void setErrorHandler(ErrorHandler errorHandler2) {
        synchronized (STATIC_LOCK) {
            errorHandler = errorHandler2;
        }
    }

    public static ErrorHandler getErrorHandler() {
        ErrorHandler errorHandler2;
        synchronized (STATIC_LOCK) {
            errorHandler2 = errorHandler;
        }
        return errorHandler2;
    }

    public static NodeModel parse(InputSource inputSource, boolean z, boolean z2) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder newDocumentBuilder = getDocumentBuilderFactory().newDocumentBuilder();
        ErrorHandler errorHandler2 = getErrorHandler();
        if (errorHandler2 != null) {
            newDocumentBuilder.setErrorHandler(errorHandler2);
        }
        try {
            Document parse = newDocumentBuilder.parse(inputSource);
            if (!z || !z2) {
                if (z) {
                    removeComments(parse);
                }
                if (z2) {
                    removePIs(parse);
                }
                mergeAdjacentText(parse);
            } else {
                simplify(parse);
            }
            return wrap(parse);
        } catch (MalformedURLException e) {
            if (inputSource.getSystemId() == null && inputSource.getCharacterStream() == null && inputSource.getByteStream() == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("The SAX InputSource has systemId == null && characterStream == null && byteStream == null. This is often because it was created with a null InputStream or Reader, which is often because the XML file it should point to was not found. (The original exception was: ");
                stringBuffer.append(e);
                stringBuffer.append(")");
                throw new MalformedURLException(stringBuffer.toString());
            }
            throw e;
        }
    }

    public static NodeModel parse(InputSource inputSource) throws SAXException, IOException, ParserConfigurationException {
        return parse(inputSource, true, true);
    }

    public static NodeModel parse(File file, boolean z, boolean z2) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilder newDocumentBuilder = getDocumentBuilderFactory().newDocumentBuilder();
        ErrorHandler errorHandler2 = getErrorHandler();
        if (errorHandler2 != null) {
            newDocumentBuilder.setErrorHandler(errorHandler2);
        }
        Document parse = newDocumentBuilder.parse(file);
        if (z) {
            removeComments(parse);
        }
        if (z2) {
            removePIs(parse);
        }
        mergeAdjacentText(parse);
        return wrap(parse);
    }

    public static NodeModel parse(File file) throws SAXException, IOException, ParserConfigurationException {
        return parse(file, true, true);
    }

    protected NodeModel(Node node2) {
        this.node = node2;
    }

    public Node getNode() {
        return this.node;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        if (str.startsWith("@@")) {
            if (str.equals("@@text")) {
                return new SimpleScalar(getText(this.node));
            }
            if (str.equals("@@namespace")) {
                String namespaceURI = this.node.getNamespaceURI();
                if (namespaceURI == null) {
                    return null;
                }
                return new SimpleScalar(namespaceURI);
            } else if (str.equals("@@local_name")) {
                String localName = this.node.getLocalName();
                if (localName == null) {
                    localName = getNodeName();
                }
                return new SimpleScalar(localName);
            } else if (str.equals("@@markup")) {
                StringBuffer stringBuffer = new StringBuffer();
                new NodeOutputter(this.node).outputContent(this.node, stringBuffer);
                return new SimpleScalar(stringBuffer.toString());
            } else if (str.equals("@@nested_markup")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                new NodeOutputter(this.node).outputContent(this.node.getChildNodes(), stringBuffer2);
                return new SimpleScalar(stringBuffer2.toString());
            } else if (str.equals("@@qname")) {
                String qualifiedName = getQualifiedName();
                if (qualifiedName == null) {
                    return null;
                }
                return new SimpleScalar(qualifiedName);
            }
        }
        XPathSupport xPathSupport = getXPathSupport();
        if (xPathSupport != null) {
            return xPathSupport.executeQuery(this.node, str);
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("Can't try to resolve the XML query key, because no XPath support is available. It's either malformed or an XPath expression: ");
        stringBuffer3.append(str);
        throw new TemplateModelException(stringBuffer3.toString());
    }

    public TemplateNodeModel getParentNode() {
        if (this.parent == null) {
            Node parentNode = this.node.getParentNode();
            if (parentNode == null) {
                Node node2 = this.node;
                if (node2 instanceof Attr) {
                    parentNode = ((Attr) node2).getOwnerElement();
                }
            }
            this.parent = wrap(parentNode);
        }
        return this.parent;
    }

    public TemplateSequenceModel getChildNodes() {
        if (this.children == null) {
            this.children = new NodeListModel(this.node.getChildNodes(), this);
        }
        return this.children;
    }

    public final String getNodeType() throws TemplateModelException {
        short nodeType = this.node.getNodeType();
        switch (nodeType) {
            case 1:
                return "element";
            case 2:
                return "attribute";
            case 3:
            case 4:
                return "text";
            case 5:
                return "entity_reference";
            case 6:
                return "entity";
            case 7:
                return "pi";
            case 8:
                return "comment";
            case 9:
                return "document";
            case 10:
                return "document_type";
            case 11:
                return "document_fragment";
            case 12:
                return "notation";
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unknown node type: ");
                stringBuffer.append(nodeType);
                stringBuffer.append(". This should be impossible!");
                throw new TemplateModelException(stringBuffer.toString());
        }
    }

    public TemplateModel exec(List list) throws TemplateModelException {
        if (list.size() == 1) {
            String str = (String) list.get(0);
            XPathSupport xPathSupport = getXPathSupport();
            if (xPathSupport != null) {
                return xPathSupport.executeQuery(this.node, str);
            }
            throw new TemplateModelException("No XPath support available");
        }
        throw new TemplateModelException("Expecting exactly one arguments");
    }

    public String getNodeNamespace() {
        short nodeType = this.node.getNodeType();
        if (nodeType != 2 && nodeType != 1) {
            return null;
        }
        String namespaceURI = this.node.getNamespaceURI();
        if (namespaceURI == null && nodeType == 1) {
            return "";
        }
        if (!"".equals(namespaceURI) || nodeType != 2) {
            return namespaceURI;
        }
        return null;
    }

    public final int hashCode() {
        return this.node.hashCode();
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == getClass() && ((NodeModel) obj).node.equals(this.node);
    }

    public static NodeModel wrap(Node node2) {
        if (node2 == null) {
            return null;
        }
        switch (node2.getNodeType()) {
            case 1:
                return new ElementModel((Element) node2);
            case 2:
                return new AttributeNodeModel((Attr) node2);
            case 3:
            case 4:
            case 8:
                return new CharacterDataNodeModel((CharacterData) node2);
            case 7:
                return new PINodeModel((ProcessingInstruction) node2);
            case 9:
                return new DocumentModel((Document) node2);
            case 10:
                return new DocumentTypeModel((DocumentType) node2);
            default:
                return null;
        }
    }

    public static void removeComments(Node node2) {
        NodeList childNodes = node2.getChildNodes();
        int length = childNodes.getLength();
        int i = 0;
        while (i < length) {
            Node item = childNodes.item(i);
            if (item.hasChildNodes()) {
                removeComments(item);
            } else if (item.getNodeType() == 8) {
                node2.removeChild(item);
                length--;
            }
            i++;
        }
    }

    public static void removePIs(Node node2) {
        NodeList childNodes = node2.getChildNodes();
        int length = childNodes.getLength();
        int i = 0;
        while (i < length) {
            Node item = childNodes.item(i);
            if (item.hasChildNodes()) {
                removePIs(item);
            } else if (item.getNodeType() == 7) {
                node2.removeChild(item);
                length--;
            }
            i++;
        }
    }

    public static void mergeAdjacentText(Node node2) {
        for (Node firstChild = node2.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
            if ((firstChild instanceof Text) || (firstChild instanceof CDATASection)) {
                Node nextSibling = firstChild.getNextSibling();
                if ((nextSibling instanceof Text) || (nextSibling instanceof CDATASection)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(firstChild.getNodeValue());
                    stringBuffer.append(nextSibling.getNodeValue());
                    ((CharacterData) firstChild).setData(stringBuffer.toString());
                    node2.removeChild(nextSibling);
                }
            } else {
                mergeAdjacentText(firstChild);
            }
        }
    }

    public static void simplify(Node node2) {
        NodeList childNodes = node2.getChildNodes();
        int length = childNodes.getLength();
        int i = 0;
        while (true) {
            CharacterData characterData = null;
            while (i < length) {
                Node item = childNodes.item(i);
                if (item.hasChildNodes()) {
                    simplify(item);
                } else {
                    short nodeType = item.getNodeType();
                    if (nodeType == 7) {
                        node2.removeChild(item);
                    } else if (nodeType == 8) {
                        node2.removeChild(item);
                    } else if (nodeType == 3 || nodeType == 4) {
                        if (characterData != null) {
                            CharacterData characterData2 = characterData;
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(characterData2.getNodeValue());
                            stringBuffer.append(item.getNodeValue());
                            characterData2.setData(stringBuffer.toString());
                            node2.removeChild(item);
                        } else {
                            i++;
                            characterData = item;
                        }
                    }
                    length--;
                }
                i++;
            }
            return;
        }
    }

    /* access modifiers changed from: package-private */
    public NodeModel getDocumentNodeModel() {
        Node node2 = this.node;
        if (node2 instanceof Document) {
            return this;
        }
        return wrap(node2.getOwnerDocument());
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0012 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0019 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000b */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x000f A[SYNTHETIC, Splitter:B:10:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0016 A[SYNTHETIC, Splitter:B:15:0x0016] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void useDefaultXPathSupport() {
        /*
            java.lang.Object r0 = STATIC_LOCK
            monitor-enter(r0)
            r1 = 0
            xpathSupportClass = r1     // Catch:{ all -> 0x001b }
            jaxenXPathSupport = r1     // Catch:{ all -> 0x001b }
            useXalanXPathSupport()     // Catch:{ Exception -> 0x000b }
        L_0x000b:
            java.lang.Class r1 = xpathSupportClass     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0012
            useSunInternalXPathSupport()     // Catch:{ Exception -> 0x0012 }
        L_0x0012:
            java.lang.Class r1 = xpathSupportClass     // Catch:{ all -> 0x001b }
            if (r1 != 0) goto L_0x0019
            useJaxenXPathSupport()     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.ext.dom.NodeModel.useDefaultXPathSupport():void");
    }

    public static void useJaxenXPathSupport() throws Exception {
        Class.forName("org.jaxen.dom.DOMXPath");
        Class<?> cls = Class.forName("freemarker.ext.dom.JaxenXPathSupport");
        jaxenXPathSupport = (XPathSupport) cls.newInstance();
        synchronized (STATIC_LOCK) {
            xpathSupportClass = cls;
        }
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Using Jaxen classes for XPath support");
        }
    }

    public static void useXalanXPathSupport() throws Exception {
        Class.forName("org.apache.xpath.XPath");
        Class<?> cls = Class.forName("freemarker.ext.dom.XalanXPathSupport");
        synchronized (STATIC_LOCK) {
            xpathSupportClass = cls;
        }
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Using Xalan classes for XPath support");
        }
    }

    public static void useSunInternalXPathSupport() throws Exception {
        Class.forName("com.sun.org.apache.xpath.internal.XPath");
        Class<?> cls = Class.forName("freemarker.ext.dom.SunInternalXalanXPathSupport");
        synchronized (STATIC_LOCK) {
            xpathSupportClass = cls;
        }
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            logger2.debug("Using Sun's internal Xalan classes for XPath support");
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static void setXPathSupportClass(Class cls) {
        if (cls != null) {
            Class cls2 = class$freemarker$ext$dom$XPathSupport;
            if (cls2 == null) {
                cls2 = class$("freemarker.ext.dom.XPathSupport");
                class$freemarker$ext$dom$XPathSupport = cls2;
            }
            if (!cls2.isAssignableFrom(cls)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Class ");
                stringBuffer.append(cls.getName());
                stringBuffer.append(" does not implement freemarker.ext.dom.XPathSupport");
                throw new RuntimeException(stringBuffer.toString());
            }
        }
        synchronized (STATIC_LOCK) {
            xpathSupportClass = cls;
        }
    }

    public static Class getXPathSupportClass() {
        Class cls;
        synchronized (STATIC_LOCK) {
            cls = xpathSupportClass;
        }
        return cls;
    }

    private static String getText(Node node2) {
        String str = "";
        if ((node2 instanceof Text) || (node2 instanceof CDATASection)) {
            return ((CharacterData) node2).getData();
        }
        if (!(node2 instanceof Element)) {
            return node2 instanceof Document ? getText(((Document) node2).getDocumentElement()) : str;
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

    /* access modifiers changed from: package-private */
    public XPathSupport getXPathSupport() {
        XPathSupport xPathSupport;
        Exception e;
        XPathSupport xPathSupport2 = jaxenXPathSupport;
        if (xPathSupport2 != null) {
            return xPathSupport2;
        }
        XPathSupport xPathSupport3 = null;
        Document ownerDocument = this.node.getOwnerDocument();
        if (ownerDocument == null) {
            ownerDocument = (Document) this.node;
        }
        synchronized (ownerDocument) {
            Map map = xpathSupportMap;
            WeakReference weakReference = (WeakReference) map.get(ownerDocument);
            if (weakReference != null) {
                xPathSupport3 = (XPathSupport) weakReference.get();
            }
            if (xPathSupport3 == null) {
                try {
                    xPathSupport = (XPathSupport) xpathSupportClass.newInstance();
                    try {
                        map.put(ownerDocument, new WeakReference(xPathSupport));
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Exception e3) {
                    xPathSupport = xPathSupport3;
                    e = e3;
                    logger.error("Error instantiating xpathSupport class", e);
                    xPathSupport3 = xPathSupport;
                    return xPathSupport3;
                }
                xPathSupport3 = xPathSupport;
            }
        }
        return xPathSupport3;
    }

    /* access modifiers changed from: package-private */
    public String getQualifiedName() throws TemplateModelException {
        return getNodeName();
    }

    public Object getAdaptedObject(Class cls) {
        return this.node;
    }

    public Object getWrappedObject() {
        return this.node;
    }
}
