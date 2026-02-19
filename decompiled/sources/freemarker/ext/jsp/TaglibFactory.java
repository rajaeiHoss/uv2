package freemarker.ext.jsp;

import freemarker.core.Environment;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.log.Logger;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.ClassUtil;
import freemarker.template.utility.StringUtil;
import java.beans.IntrospectionException;
import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class TaglibFactory implements TemplateHashModel {
    private static final int ABS_URI = 0;
    private static final String DEFAULT_JAR_TLD = "META-INF/taglib.tld";
    private static final int LOOKUP_NONE = 0;
    private static final int LOOKUP_WEB_APP = 2;
    private static final int LOOKUP_WEB_XML = 1;
    private static final int NOROOT_REL_URI = 2;
    private static final int ROOT_REL_URI = 1;
    static /* synthetic */ Class class$freemarker$ext$jsp$EventForwarding;
    static /* synthetic */ Class class$javax$servlet$jsp$tagext$Tag;
    /* access modifiers changed from: private */
    public static final Map dtds;
    private static final Logger logger = Logger.getLogger("freemarker.jsp");
    private final ServletContext ctx;
    private final Map locations = new HashMap();
    private int lookupPhase = 0;
    private final Map taglibs = new HashMap();

    public boolean isEmpty() {
        return false;
    }

    static {
        HashMap hashMap = new HashMap();
        dtds = hashMap;
        hashMap.put("http://java.sun.com/xml/ns/jee/web-jsptaglibrary_2_1.xsd", "web-jsptaglibrary_2_1.xsd");
        hashMap.put("http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd", "web-jsptaglibrary_2_0.xsd");
        hashMap.put("-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN", "web-jsptaglibrary_1_2.dtd");
        hashMap.put("http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd", "web-jsptaglibrary_1_2.dtd");
        hashMap.put("-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN", "web-jsptaglibrary_1_1.dtd");
        hashMap.put("http://java.sun.com/j2ee/dtds/web-jsptaglibrary_1_1.dtd", "web-jsptaglibrary_1_1.dtd");
        hashMap.put("http://java.sun.com/xml/ns/jee/web-app_2_5.xsd", "web-app_2_5.xsd");
        hashMap.put("http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd", "web-app_2_4.xsd");
        hashMap.put("-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN", "web-app_2_3.dtd");
        hashMap.put("http://java.sun.com/dtd/web-app_2_3.dtd", "web-app_2_3.dtd");
        hashMap.put("-//Sun Microsystems, Inc.//DTD Web Application 2.2//EN", "web-app_2_2.dtd");
        hashMap.put("http://java.sun.com/j2ee/dtds/web-app_2_2.dtd", "web-app_2_2.dtd");
    }

    public TaglibFactory(ServletContext servletContext) {
        this.ctx = servletContext;
    }

    public TemplateModel get(String str) throws TemplateModelException {
        synchronized (this.taglibs) {
            Taglib taglib = (Taglib) this.taglibs.get(str);
            if (taglib != null) {
                return taglib;
            }
            try {
                if (this.lookupPhase == 0) {
                    addLocationsFromWebXml();
                    this.lookupPhase = 1;
                }
                TldPath tldPath = (TldPath) this.locations.get(str);
                if (tldPath != null) {
                    TemplateModel loadTaglib = loadTaglib(tldPath, str);
                    return loadTaglib;
                }
                if (this.lookupPhase == 1) {
                    addLocationsFromWebApp();
                    this.lookupPhase = 2;
                    TldPath tldPath2 = (TldPath) this.locations.get(str);
                    if (tldPath2 != null) {
                        TemplateModel loadTaglib2 = loadTaglib(tldPath2, str);
                        return loadTaglib2;
                    }
                }
                int uriType = getUriType(str);
                if (uriType != 0) {
                    if (uriType != 1) {
                        if (uriType == 2) {
                            str = resolveRelativeUri(str);
                        } else {
                            throw new RuntimeException("Cannot happen");
                        }
                    }
                    if (!str.endsWith(".jar")) {
                        if (!str.endsWith(".zip")) {
                            TemplateModel loadTaglib3 = loadTaglib(new TldPath(str), str);
                            return loadTaglib3;
                        }
                    }
                    TemplateModel loadTaglib4 = loadTaglib(new TldPath(str, DEFAULT_JAR_TLD), str);
                    return loadTaglib4;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("No mapping defined for ");
                stringBuffer.append(str);
                throw new TemplateModelException(stringBuffer.toString());
            } catch (TemplateModelException e) {
                throw e;
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception e3) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Could not load taglib information for ");
                stringBuffer2.append(str);
                throw new TemplateModelException(stringBuffer2.toString(), e3);
            }
        }
    }

    private static class TldPath {
        final String filePath;
        final String jarItemPath;

        TldPath(String str) {
            this(str, (String) null);
        }

        TldPath(String str, String str2) {
            this.filePath = str;
            this.jarItemPath = str2;
        }

        public String toString() {
            if (this.jarItemPath == null) {
                return this.filePath;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.filePath);
            stringBuffer.append("!");
            stringBuffer.append(this.jarItemPath);
            return stringBuffer.toString();
        }
    }

    private TemplateModel loadTaglib(TldPath tldPath, String str) throws Exception {
        Logger logger2 = logger;
        if (logger2.isDebugEnabled()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Loading taglib ");
            stringBuffer.append(StringUtil.jQuoteNoXSS(str));
            stringBuffer.append(" from location ");
            stringBuffer.append(StringUtil.jQuoteNoXSS((Object) tldPath));
            logger2.debug(stringBuffer.toString());
        }
        Taglib taglib = new Taglib(this.ctx, tldPath, str);
        this.taglibs.put(str, taglib);
        this.locations.remove(str);
        return taglib;
    }

    /* access modifiers changed from: private */
    public static int getUriType(String str) throws TemplateModelException {
        int indexOf;
        if (str == null) {
            throw new TemplateModelException("null is not a valid URI");
        } else if (str.length() != 0) {
            char charAt = str.charAt(0);
            if (charAt == '/') {
                return 1;
            }
            if (charAt < 'a' || charAt > 'z' || (indexOf = str.indexOf(58)) == -1) {
                return 2;
            }
            for (int i = 1; i < indexOf; i++) {
                char charAt2 = str.charAt(i);
                if ((charAt2 < 'a' || charAt2 > 'z') && ((charAt2 < '0' || charAt2 > '9') && charAt2 != '+' && charAt2 != '-' && charAt2 != '.')) {
                    return 2;
                }
            }
            return 0;
        } else {
            throw new TemplateModelException("empty string is not a valid URI");
        }
    }

    private void addLocationsFromWebXml() throws Exception {
        WebXmlParser webXmlParser = new WebXmlParser();
        InputStream resourceAsStream = this.ctx.getResourceAsStream("/WEB-INF/web.xml");
        if (resourceAsStream != null) {
            try {
                parseXml(resourceAsStream, this.ctx.getResource("/WEB-INF/web.xml").toExternalForm(), webXmlParser);
            } finally {
                resourceAsStream.close();
            }
        }
    }

    private class WebXmlParser extends DefaultHandler {
        private StringBuffer buf;
        private String location;
        private Locator locator;
        private String uri;

        private WebXmlParser() {
        }

        public void setDocumentLocator(Locator locator2) {
            this.locator = locator2;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if ("taglib-uri".equals(str3) || "taglib-location".equals(str3)) {
                this.buf = new StringBuffer();
            }
        }

        public void characters(char[] cArr, int i, int i2) {
            StringBuffer stringBuffer = this.buf;
            if (stringBuffer != null) {
                stringBuffer.append(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXParseException {
            String str4 = null;
            if ("taglib-uri".equals(str3)) {
                this.uri = this.buf.toString().trim();
                this.buf = null;
            } else if ("taglib-location".equals(str3)) {
                String trim = this.buf.toString().trim();
                this.location = trim;
                try {
                    if (TaglibFactory.getUriType(trim) == 2) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("/WEB-INF/");
                        stringBuffer.append(this.location);
                        this.location = stringBuffer.toString();
                    }
                    this.buf = null;
                } catch (TemplateModelException e) {
                    throw new SAXParseException(e.getMessage(), this.locator, e);
                }
            } else if ("taglib".equals(str3)) {
                if (this.location.endsWith(".jar") || this.location.endsWith(".zip")) {
                    str4 = TaglibFactory.DEFAULT_JAR_TLD;
                }
                TaglibFactory.this.addLocation("web.xml", this.location, str4, this.uri);
            }
        }
    }

    private void addLocationsFromWebApp() throws Exception {
        Set<String> resourcePaths = this.ctx.getResourcePaths("/WEB-INF/lib");
        if (resourcePaths != null) {
            for (String str : resourcePaths) {
                if (str.endsWith(".jar") || str.endsWith(".zip")) {
                    addLocationsFromJarFile(str);
                } else if (str.endsWith(".tld")) {
                    addLocationFromTldFile(str);
                }
            }
        }
        Set<String> resourcePaths2 = this.ctx.getResourcePaths("/WEB-INF");
        if (resourcePaths2 != null) {
            for (String str2 : resourcePaths2) {
                if (str2.endsWith(".tld")) {
                    addLocationFromTldFile(str2);
                }
            }
        }
    }

    private void addLocationsFromJarFile(String str) throws Exception {
        ZipInputStream zipInputStream = new ZipInputStream(this.ctx.getResourceAsStream(str));
        AnonymousClass1 r1 = new FilterInputStream(this, zipInputStream) {
            private final /* synthetic */ TaglibFactory this$0;

            public void close() {
            }

            {
                this.this$0 = r1;
            }
        };
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry != null) {
                    String name = nextEntry.getName();
                    if (name.startsWith("META-INF/") && name.endsWith(".tld")) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("jar:");
                        stringBuffer.append(this.ctx.getResource(str).toExternalForm());
                        stringBuffer.append("!");
                        stringBuffer.append(name);
                        addLocationFromTldResource(r1, str, name, stringBuffer.toString());
                    }
                } else {
                    return;
                }
            } finally {
                zipInputStream.close();
            }
        }
    }

    private void addLocationFromTldFile(String str) throws Exception {
        InputStream resourceAsStream = this.ctx.getResourceAsStream(str);
        try {
            addLocationFromTldResource(resourceAsStream, str, (String) null, this.ctx.getResource(str).toExternalForm());
        } finally {
            resourceAsStream.close();
        }
    }

    private void addLocationFromTldResource(InputStream inputStream, String str, String str2, String str3) throws Exception {
        String tldUri = getTldUri(inputStream, str3);
        if (tldUri != null) {
            addLocation(str2 == null ? "tld file" : "jar file", str, str2, tldUri);
        }
    }

    /* access modifiers changed from: private */
    public void addLocation(String str, String str2, String str3, String str4) {
        TldPath tldPath = new TldPath(str2, str3);
        if (this.locations.containsKey(str4)) {
            Logger logger2 = logger;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Ignored duplicate URI ");
            stringBuffer.append(StringUtil.jQuoteNoXSS(str4));
            stringBuffer.append(" in ");
            stringBuffer.append(str);
            stringBuffer.append(" ");
            stringBuffer.append(StringUtil.jQuoteNoXSS((Object) tldPath));
            logger2.debug(stringBuffer.toString());
            return;
        }
        this.locations.put(str4, tldPath);
        Logger logger3 = logger;
        if (logger3.isDebugEnabled()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(str);
            stringBuffer2.append(" assigned URI ");
            stringBuffer2.append(StringUtil.jQuoteNoXSS(str4));
            stringBuffer2.append(" to location ");
            stringBuffer2.append(StringUtil.jQuoteNoXSS((Object) tldPath));
            logger3.debug(stringBuffer2.toString());
        }
    }

    private String getTldUri(InputStream inputStream, String str) throws Exception {
        TldUriReader tldUriReader = new TldUriReader();
        parseXml(inputStream, str, tldUriReader);
        return tldUriReader.getUri();
    }

    private static class TldUriReader extends DefaultHandler {
        private StringBuffer buf;
        private String uri;

        TldUriReader() {
        }

        /* access modifiers changed from: package-private */
        public String getUri() {
            return this.uri;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if ("uri".equals(str3)) {
                this.buf = new StringBuffer();
            }
        }

        public void characters(char[] cArr, int i, int i2) {
            StringBuffer stringBuffer = this.buf;
            if (stringBuffer != null) {
                stringBuffer.append(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) {
            if ("uri".equals(str3)) {
                this.uri = this.buf.toString().trim();
                this.buf = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void parseXml(InputStream inputStream, String str, DefaultHandler defaultHandler) throws Exception {
        InputSource inputSource = new InputSource();
        inputSource.setByteStream(inputStream);
        inputSource.setSystemId(str);
        SAXParserFactory newInstance = SAXParserFactory.newInstance();
        newInstance.setNamespaceAware(false);
        newInstance.setValidating(false);
        XMLReader xMLReader = newInstance.newSAXParser().getXMLReader();
        xMLReader.setEntityResolver(new LocalTaglibDtds());
        xMLReader.setContentHandler(defaultHandler);
        xMLReader.setErrorHandler(defaultHandler);
        xMLReader.parse(inputSource);
    }

    private static final class Taglib implements TemplateHashModel {
        private final Map tags;

        Taglib(ServletContext servletContext, TldPath tldPath, String str) throws Exception {
            this.tags = loadTaglib(servletContext, tldPath, str);
        }

        public TemplateModel get(String str) {
            return (TemplateModel) this.tags.get(str);
        }

        public boolean isEmpty() {
            return this.tags.isEmpty();
        }

        /* JADX INFO: finally extract failed */
        private static final Map loadTaglib(ServletContext servletContext, TldPath tldPath, String str) throws Exception {
            Class cls;
            Class cls2;
            String name;
            TldParser tldParser = new TldParser();
            String str2 = tldPath.filePath;
            InputStream resourceAsStream = servletContext.getResourceAsStream(str2);
            if (resourceAsStream != null) {
                String externalForm = servletContext.getResource(str2).toExternalForm();
                try {
                    String str3 = tldPath.jarItemPath;
                    if (str3 != null) {
                        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream);
                        do {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry != null) {
                                name = nextEntry.getName();
                            } else {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append("Could not find JAR entry ");
                                stringBuffer.append(str3);
                                stringBuffer.append(" inside webapp resource ");
                                stringBuffer.append(str2);
                                stringBuffer.append(" for URI ");
                                stringBuffer.append(str);
                                throw new TemplateModelException(stringBuffer.toString());
                            }
                        } while (!name.equals(str3));
                        StringBuffer stringBuffer2 = new StringBuffer();
                        stringBuffer2.append("jar:");
                        stringBuffer2.append(externalForm);
                        stringBuffer2.append("!");
                        stringBuffer2.append(name);
                        TaglibFactory.parseXml(zipInputStream, stringBuffer2.toString(), tldParser);
                    } else {
                        TaglibFactory.parseXml(resourceAsStream, externalForm, tldParser);
                    }
                    resourceAsStream.close();
                    EventForwarding instance = EventForwarding.getInstance(servletContext);
                    if (instance != null) {
                        instance.addListeners(tldParser.getListeners());
                    } else if (tldParser.getListeners().size() > 0) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Event listeners specified in the TLD could not be  registered since the web application doesn't have a listener of class ");
                        if (TaglibFactory.class$freemarker$ext$jsp$EventForwarding == null) {
                            cls = TaglibFactory.class$("freemarker.ext.jsp.EventForwarding");
                            TaglibFactory.class$freemarker$ext$jsp$EventForwarding = cls;
                        } else {
                            cls = TaglibFactory.class$freemarker$ext$jsp$EventForwarding;
                        }
                        stringBuffer3.append(cls.getName());
                        stringBuffer3.append(". To remedy this, add this element to web.xml:\n");
                        stringBuffer3.append("| <listener>\n");
                        stringBuffer3.append("|   <listener-class>");
                        if (TaglibFactory.class$freemarker$ext$jsp$EventForwarding == null) {
                            cls2 = TaglibFactory.class$("freemarker.ext.jsp.EventForwarding");
                            TaglibFactory.class$freemarker$ext$jsp$EventForwarding = cls2;
                        } else {
                            cls2 = TaglibFactory.class$freemarker$ext$jsp$EventForwarding;
                        }
                        stringBuffer3.append(cls2.getName());
                        stringBuffer3.append("</listener-class>\n");
                        stringBuffer3.append("| </listener>");
                        throw new TemplateModelException(stringBuffer3.toString());
                    }
                    return tldParser.getTags();
                } catch (Throwable th) {
                    resourceAsStream.close();
                    throw th;
                }
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("Could not find webapp resource ");
                stringBuffer4.append(str2);
                stringBuffer4.append(" for URI ");
                stringBuffer4.append(str);
                throw new TemplateModelException(stringBuffer4.toString());
            }
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static String resolveRelativeUri(String str) throws TemplateModelException {
        TemplateModel variable = Environment.getCurrentEnvironment().getVariable(FreemarkerServlet.KEY_REQUEST_PRIVATE);
        if (variable instanceof HttpRequestHashModel) {
            HttpServletRequest request = ((HttpRequestHashModel) variable).getRequest();
            String pathInfo = request.getPathInfo();
            String servletPath = request.getServletPath();
            if (servletPath == null) {
                servletPath = "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(servletPath);
            if (pathInfo == null) {
                pathInfo = "";
            }
            stringBuffer.append(pathInfo);
            String stringBuffer2 = stringBuffer.toString();
            int lastIndexOf = stringBuffer2.lastIndexOf(47);
            if (lastIndexOf != -1) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append(stringBuffer2.substring(0, lastIndexOf + 1));
                stringBuffer3.append(str);
                return stringBuffer3.toString();
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append('/');
            stringBuffer4.append(str);
            return stringBuffer4.toString();
        }
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("Can't resolve relative URI ");
        stringBuffer5.append(str);
        stringBuffer5.append(" as request URL information is unavailable.");
        throw new TemplateModelException(stringBuffer5.toString());
    }

    private static final class TldParser extends DefaultHandler {
        private StringBuffer buf;
        private final List listeners;
        private Locator locator;
        private String tagClassName;
        private String tagName;
        private final Map tags;

        private TldParser() {
            this.tags = new HashMap();
            this.listeners = new ArrayList();
        }

        /* access modifiers changed from: package-private */
        public Map getTags() {
            return this.tags;
        }

        /* access modifiers changed from: package-private */
        public List getListeners() {
            return this.listeners;
        }

        public void setDocumentLocator(Locator locator2) {
            this.locator = locator2;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) {
            if ("name".equals(str3) || "tagclass".equals(str3) || "tag-class".equals(str3) || "listener-class".equals(str3)) {
                this.buf = new StringBuffer();
            }
        }

        public void characters(char[] cArr, int i, int i2) {
            StringBuffer stringBuffer = this.buf;
            if (stringBuffer != null) {
                stringBuffer.append(cArr, i, i2);
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXParseException {
            Class cls;
            Object obj;
            if ("name".equals(str3)) {
                if (this.tagName == null) {
                    this.tagName = this.buf.toString().trim();
                }
                this.buf = null;
            } else if ("tagclass".equals(str3) || "tag-class".equals(str3)) {
                this.tagClassName = this.buf.toString().trim();
                this.buf = null;
            } else if ("tag".equals(str3)) {
                try {
                    Class forName = ClassUtil.forName(this.tagClassName);
                    if (TaglibFactory.class$javax$servlet$jsp$tagext$Tag == null) {
                        cls = TaglibFactory.class$("javax.servlet.jsp.tagext.Tag");
                        TaglibFactory.class$javax$servlet$jsp$tagext$Tag = cls;
                    } else {
                        cls = TaglibFactory.class$javax$servlet$jsp$tagext$Tag;
                    }
                    if (cls.isAssignableFrom(forName)) {
                        obj = new TagTransformModel(forName);
                    } else {
                        obj = new SimpleTagDirectiveModel(forName);
                    }
                    this.tags.put(this.tagName, obj);
                    this.tagName = null;
                    this.tagClassName = null;
                } catch (IntrospectionException e) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Can't introspect tag class ");
                    stringBuffer.append(this.tagClassName);
                    throw new SAXParseException(stringBuffer.toString(), this.locator, e);
                } catch (ClassNotFoundException e2) {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Can't find tag class ");
                    stringBuffer2.append(this.tagClassName);
                    throw new SAXParseException(stringBuffer2.toString(), this.locator, e2);
                }
            } else if ("listener-class".equals(str3)) {
                String trim = this.buf.toString().trim();
                this.buf = null;
                try {
                    this.listeners.add(ClassUtil.forName(trim).newInstance());
                } catch (Exception e3) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("Can't instantiate listener class ");
                    stringBuffer3.append(trim);
                    throw new SAXParseException(stringBuffer3.toString(), this.locator, e3);
                }
            }
        }
    }

    private static final class LocalTaglibDtds implements EntityResolver {
        private LocalTaglibDtds() {
        }

        public InputSource resolveEntity(String str, String str2) {
            InputStream inputStream;
            String str3 = (String) TaglibFactory.dtds.get(str);
            if (str3 == null) {
                str3 = (String) TaglibFactory.dtds.get(str2);
            }
            if (str3 != null) {
                inputStream = getClass().getResourceAsStream(str3);
            } else {
                inputStream = new ByteArrayInputStream(new byte[0]);
            }
            InputSource inputSource = new InputSource();
            inputSource.setPublicId(str);
            inputSource.setSystemId(str2);
            inputSource.setByteStream(inputStream);
            return inputSource;
        }
    }
}
