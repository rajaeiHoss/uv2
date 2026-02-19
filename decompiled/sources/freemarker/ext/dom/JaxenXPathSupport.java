package freemarker.ext.dom;

import freemarker.cache.TemplateCache;
import freemarker.core.CustomAttribute;
import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.utility.Collections12;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.jaxen.BaseXPath;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.FunctionContext;
import org.jaxen.JaxenException;
import org.jaxen.NamespaceContext;
import org.jaxen.Navigator;
import org.jaxen.UnresolvableException;
import org.jaxen.VariableContext;
import org.jaxen.XPathFunctionContext;
import org.jaxen.dom.DocumentNavigator;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class JaxenXPathSupport implements XPathSupport {
    private static final ArrayList EMPTY_ARRAYLIST = new ArrayList();
    private static final CustomAttribute cache = new CustomAttribute(1) {
        /* access modifiers changed from: protected */
        public Object create() {
            return new HashMap();
        }
    };
    /* access modifiers changed from: private */
    public static final CustomAttribute cachedTree = new CustomAttribute(1);
    private static final NamespaceContext customNamespaceContext = new NamespaceContext() {
        public String translateNamespacePrefixToUri(String str) {
            if (str.equals(Template.DEFAULT_NAMESPACE_PREFIX)) {
                return Environment.getCurrentEnvironment().getDefaultNS();
            }
            return Environment.getCurrentEnvironment().getNamespaceForPrefix(str);
        }
    };
    private static final Navigator fmDomNavigator = new DocumentNavigator() {
        public Object getDocument(String str) throws FunctionCallException {
            try {
                Template template = JaxenXPathSupport.getTemplate(str);
                Document document = (Document) JaxenXPathSupport.cachedTree.get(template);
                if (document == null) {
                    DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
                    newInstance.setNamespaceAware(true);
                    DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
                    FmEntityResolver fmEntityResolver = new FmEntityResolver();
                    newDocumentBuilder.setEntityResolver(fmEntityResolver);
                    document = newDocumentBuilder.parse(JaxenXPathSupport.createInputSource((String) null, template));
                    if (fmEntityResolver.getCallCount() == 0) {
                        JaxenXPathSupport.cachedTree.set(document, template);
                    }
                }
                return document;
            } catch (Exception e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Failed to parse document for URI: ");
                stringBuffer.append(str);
                throw new FunctionCallException(stringBuffer.toString(), e);
            }
        }
    };
    private static final FunctionContext fmFunctionContext = new XPathFunctionContext() {
        public Function getFunction(String str, String str2, String str3) throws UnresolvableException {
            try {
                return JaxenXPathSupport.super.getFunction(str, str2, str3);
            } catch (UnresolvableException unused) {
                return JaxenXPathSupport.super.getFunction((String) null, (String) null, str3);
            }
        }
    };
    private static final VariableContext fmVariableContext = new VariableContext() {
        public Object getVariableValue(String str, String str2, String str3) throws UnresolvableException {
            try {
                TemplateModel variable = Environment.getCurrentEnvironment().getVariable(str3);
                if (variable == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Variable ");
                    stringBuffer.append(str3);
                    stringBuffer.append(" not found.");
                    throw new UnresolvableException(stringBuffer.toString());
                } else if (variable instanceof TemplateScalarModel) {
                    return ((TemplateScalarModel) variable).getAsString();
                } else {
                    if (variable instanceof TemplateNumberModel) {
                        return ((TemplateNumberModel) variable).getAsNumber();
                    }
                    if (variable instanceof TemplateDateModel) {
                        return ((TemplateDateModel) variable).getAsDate();
                    }
                    if (variable instanceof TemplateBooleanModel) {
                        return ((TemplateBooleanModel) variable).getAsBoolean() ? Boolean.TRUE : Boolean.FALSE;
                    }
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Variable ");
                    stringBuffer2.append(str3);
                    stringBuffer2.append(" is not a string, number, date, or boolean");
                    throw new UnresolvableException(stringBuffer2.toString());
                }
            } catch (TemplateModelException e) {
                throw new UndeclaredThrowableException(e);
            }
        }
    };

    JaxenXPathSupport() {
    }

    public TemplateModel executeQuery(Object obj, String str) throws TemplateModelException {
        BaseXPath baseXPath;
        try {
            Map map = (Map) cache.get();
            synchronized (map) {
                baseXPath = (BaseXPath) map.get(str);
                if (baseXPath == null) {
                    baseXPath = new BaseXPath(str, fmDomNavigator);
                    baseXPath.setNamespaceContext(customNamespaceContext);
                    baseXPath.setFunctionContext(fmFunctionContext);
                    baseXPath.setVariableContext(fmVariableContext);
                    map.put(str, baseXPath);
                }
            }
            if (obj == null) {
                obj = EMPTY_ARRAYLIST;
            }
            List selectNodes = baseXPath.selectNodes(obj);
            if (selectNodes.size() == 1) {
                return ObjectWrapper.DEFAULT_WRAPPER.wrap(selectNodes.get(0));
            }
            NodeListModel nodeListModel = new NodeListModel(selectNodes, (NodeModel) null);
            nodeListModel.xpathSupport = this;
            return nodeListModel;
        } catch (UndeclaredThrowableException e) {
            Throwable undeclaredThrowable = e.getUndeclaredThrowable();
            if (undeclaredThrowable instanceof TemplateModelException) {
                throw ((TemplateModelException) undeclaredThrowable);
            }
            throw e;
        } catch (JaxenException e2) {
            throw new TemplateModelException((Exception) e2);
        }
    }

    static Template getTemplate(String str) throws IOException {
        String str2;
        Environment currentEnvironment = Environment.getCurrentEnvironment();
        String encoding = currentEnvironment.getTemplate().getEncoding();
        if (encoding == null) {
            encoding = currentEnvironment.getConfiguration().getEncoding(currentEnvironment.getLocale());
        }
        String name = currentEnvironment.getTemplate().getName();
        int lastIndexOf = name.lastIndexOf(47);
        if (lastIndexOf == -1) {
            str2 = "";
        } else {
            str2 = name.substring(0, lastIndexOf + 1);
        }
        return currentEnvironment.getConfiguration().getTemplate(TemplateCache.getFullTemplatePath(currentEnvironment, str2, str), currentEnvironment.getLocale(), encoding, false);
    }

    /* access modifiers changed from: private */
    public static InputSource createInputSource(String str, Template template) throws IOException, SAXException {
        StringWriter stringWriter = new StringWriter();
        try {
            template.process(Collections12.EMPTY_MAP, stringWriter);
            InputSource inputSource = new InputSource();
            inputSource.setPublicId(str);
            inputSource.setSystemId(template.getName());
            inputSource.setCharacterStream(new StringReader(stringWriter.toString()));
            return inputSource;
        } catch (TemplateException e) {
            throw new SAXException(e);
        }
    }

    private static class FmEntityResolver implements EntityResolver {
        private int callCount;

        private FmEntityResolver() {
            this.callCount = 0;
        }

        public InputSource resolveEntity(String str, String str2) throws SAXException, IOException {
            this.callCount++;
            return JaxenXPathSupport.createInputSource(str, JaxenXPathSupport.getTemplate(str2));
        }

        /* access modifiers changed from: package-private */
        public int getCallCount() {
            return this.callCount;
        }
    }
}
