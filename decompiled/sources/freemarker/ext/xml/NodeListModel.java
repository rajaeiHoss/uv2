package freemarker.ext.xml;

import com.google.android.gms.measurement.AppMeasurement;
import freemarker.log.Logger;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.Collections12;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class NodeListModel implements TemplateHashModel, TemplateMethodModel, TemplateScalarModel, TemplateSequenceModel, TemplateNodeModel {
    private static final Navigator DOM4J_NAVIGATOR = getNavigator("Dom4j");
    private static final Class DOM4J_NODE_CLASS = getClass("org.dom4j.Node");
    private static final Navigator DOM_NAVIGATOR = getNavigator("Dom");
    private static final Class DOM_NODE_CLASS = getClass("org.w3c.dom.Node");
    private static final Navigator JDOM_NAVIGATOR = getNavigator("Jdom");
    private static final Logger logger = Logger.getLogger("freemarker.xml");
    private static volatile boolean useJaxenNamespaces = true;
    private Namespaces namespaces;
    /* access modifiers changed from: private */
    public final Navigator navigator;
    private final List nodes;

    public NodeListModel(Object obj) {
        if (obj instanceof Collection) {
            ArrayList arrayList = new ArrayList((Collection) obj);
            this.nodes = arrayList;
            obj = arrayList.isEmpty() ? null : arrayList.get(0);
        } else if (obj != null) {
            this.nodes = Collections12.singletonList(obj);
        } else {
            throw new IllegalArgumentException("nodes == null");
        }
        Class cls = DOM_NODE_CLASS;
        if (cls == null || !cls.isInstance(obj)) {
            Class cls2 = DOM4J_NODE_CLASS;
            if (cls2 == null || !cls2.isInstance(obj)) {
                this.navigator = JDOM_NAVIGATOR;
            } else {
                this.navigator = DOM4J_NAVIGATOR;
            }
        } else {
            this.navigator = DOM_NAVIGATOR;
        }
        this.namespaces = createNamespaces();
    }

    private Namespaces createNamespaces() {
        if (useJaxenNamespaces) {
            try {
                return (Namespaces) Class.forName("freemarker.ext.xml._JaxenNamespaces").newInstance();
            } catch (Throwable unused) {
                useJaxenNamespaces = false;
            }
        }
        return new Namespaces();
    }

    private NodeListModel(Navigator navigator2, List list, Namespaces namespaces2) {
        this.navigator = navigator2;
        this.nodes = list;
        this.namespaces = namespaces2;
    }

    /* access modifiers changed from: private */
    public NodeListModel deriveModel(List list) {
        this.namespaces.markShared();
        return new NodeListModel(this.navigator, list, this.namespaces);
    }

    public int size() {
        return this.nodes.size();
    }

    public Object exec(List list) throws TemplateModelException {
        if (list.size() == 1) {
            return deriveModel(this.navigator.applyXPath(this.nodes, (String) list.get(0), this.namespaces));
        }
        throw new TemplateModelException("Expecting exactly one argument - an XPath expression");
    }

    public String getAsString() throws TemplateModelException {
        StringWriter stringWriter = new StringWriter(size() * 128);
        for (Object next : this.nodes) {
            if (next instanceof String) {
                stringWriter.write((String) next);
            } else {
                this.navigator.getAsString(next, stringWriter);
            }
        }
        return stringWriter.toString();
    }

    public TemplateModel get(int i) {
        return deriveModel(Collections12.singletonList(this.nodes.get(i)));
    }

    public TemplateModel get(String str) throws TemplateModelException {
        NodeOperator operator = this.navigator.getOperator(str);
        String str2 = null;
        if (operator == null && str.length() > 0 && str.charAt(0) == '_') {
            if (str.equals("_unique")) {
                return deriveModel(removeDuplicates(this.nodes));
            }
            if (str.equals("_filterType") || str.equals("_ftype")) {
                return new FilterByType();
            }
            if (str.equals("_registerNamespace") && this.namespaces.isShared()) {
                this.namespaces = (Namespaces) this.namespaces.clone();
            }
        }
        String str3 = "";
        if (operator == null) {
            int indexOf = str.indexOf(58);
            if (indexOf != -1) {
                String substring = str.substring(indexOf + 1);
                String substring2 = str.substring(0, indexOf);
                str3 = this.namespaces.translateNamespacePrefixToUri(substring2);
                if (str3 != null) {
                    str = substring;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Namespace prefix ");
                    stringBuffer.append(substring2);
                    stringBuffer.append(" is not registered.");
                    throw new TemplateModelException(stringBuffer.toString());
                }
            }
            if (str.charAt(0) == '@') {
                operator = this.navigator.getAttributeOperator();
                str2 = str.substring(1);
            } else {
                operator = this.navigator.getChildrenOperator();
                str2 = str;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Object process : this.nodes) {
            try {
                operator.process(process, str2, str3, arrayList);
            } catch (RuntimeException e) {
                throw new TemplateModelException((Exception) e);
            }
        }
        return deriveModel(arrayList);
    }

    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    public void registerNamespace(String str, String str2) {
        if (this.namespaces.isShared()) {
            this.namespaces = (Namespaces) this.namespaces.clone();
        }
        this.namespaces.registerNamespace(str, str2);
    }

    private class FilterByType implements TemplateMethodModel {
        private FilterByType() {
        }

        public Object exec(List list) {
            ArrayList arrayList = new ArrayList();
            for (Object next : list) {
                if (list.contains(NodeListModel.this.navigator.getType(next))) {
                    arrayList.add(next);
                }
            }
            return NodeListModel.this.deriveModel(arrayList);
        }
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
        return arrayList;
    }

    private static Class getClass(String str) {
        try {
            return ClassUtil.forName(str);
        } catch (Exception e) {
            if (!logger.isDebugEnabled()) {
                return null;
            }
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Couldn't load class ");
            stringBuffer.append(str);
            logger2.debug(stringBuffer.toString(), e);
            return null;
        }
    }

    private static Navigator getNavigator(String str) {
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("freemarker.ext.xml._");
            stringBuffer.append(str);
            stringBuffer.append("Navigator");
            return (Navigator) ClassUtil.forName(stringBuffer.toString()).newInstance();
        } catch (Throwable th) {
            Logger logger2 = logger;
            if (!logger2.isDebugEnabled()) {
                return null;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not load navigator for ");
            stringBuffer2.append(str);
            logger2.debug(stringBuffer2.toString(), th);
            return null;
        }
    }

    public TemplateSequenceModel getChildNodes() throws TemplateModelException {
        return (TemplateSequenceModel) get("_content");
    }

    public String getNodeName() throws TemplateModelException {
        return getUniqueText((NodeListModel) get("_name"), "name");
    }

    public String getNodeNamespace() throws TemplateModelException {
        return getUniqueText((NodeListModel) get("_nsuri"), "namespace");
    }

    public String getNodeType() throws TemplateModelException {
        return getUniqueText((NodeListModel) get("_type"), AppMeasurement.Param.TYPE);
    }

    public TemplateNodeModel getParentNode() throws TemplateModelException {
        return (TemplateNodeModel) get("_parent");
    }

    private String getUniqueText(NodeListModel nodeListModel, String str) throws TemplateModelException {
        HashSet hashSet = null;
        String str2 = null;
        for (String str3 : nodeListModel.nodes) {
            if (str3 != null) {
                if (str2 == null) {
                    str2 = str3;
                } else if (!str2.equals(str3)) {
                    if (hashSet == null) {
                        hashSet = new HashSet();
                        hashSet.add(str2);
                    }
                    hashSet.add(str3);
                }
            }
        }
        if (hashSet == null) {
            return str2;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Value for node ");
        stringBuffer.append(str);
        stringBuffer.append(" is ambiguos: ");
        stringBuffer.append(hashSet);
        throw new TemplateModelException(stringBuffer.toString());
    }
}
