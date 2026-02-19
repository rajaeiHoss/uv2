package freemarker.template;

import freemarker.core.Configurable;
import freemarker.core.Environment;
import freemarker.core.FMParser;
import freemarker.core.LibraryLoad;
import freemarker.core.Macro;
import freemarker.core.ParseException;
import freemarker.core.TemplateElement;
import freemarker.core.TextBlock;
import freemarker.core.TokenMgrError;
import freemarker.debug.impl.DebuggerService;
import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.tree.TreePath;

public class Template extends Configurable {
    public static final String DEFAULT_NAMESPACE_PREFIX = "D";
    public static final String NO_NS_PREFIX = "N";
    private int actualTagSyntax;
    private String defaultNS;
    private String encoding;
    private List imports;
    /* access modifiers changed from: private */
    public final ArrayList lines;
    private Map macros;
    private final String name;
    private Map namespaceURIToPrefixLookup;
    private transient FMParser parser;
    private Map prefixToNamespaceURILookup;
    private TemplateElement rootElement;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private Template(String str, Configuration configuration) {
        super(configuration == null ? Configuration.getDefaultConfiguration() : configuration);
        this.macros = new HashMap();
        this.imports = new Vector();
        this.lines = new ArrayList();
        this.prefixToNamespaceURILookup = new HashMap();
        this.namespaceURIToPrefixLookup = new HashMap();
        this.name = str;
    }

    public Template(String str, Reader reader, Configuration configuration) throws IOException {
        this(str, reader, configuration, (String) null);
    }

    public Template(String str, String str2, Configuration configuration) throws IOException {
        this(str, new StringReader(str2), configuration, (String) null);
    }

    public Template(String str, LineTableBuilder lineTableBuilder, Configuration configuration, String str2) throws IOException {
        this(str, configuration);
        this.encoding = str2;
        try {
            LineTableBuilder lineTableBuilder2 = new LineTableBuilder(this, !(lineTableBuilder instanceof BufferedReader) ? new BufferedReader(lineTableBuilder, 4096) : lineTableBuilder);
            try {
                FMParser fMParser = new FMParser(this, lineTableBuilder2, getConfiguration().getStrictSyntaxMode(), getConfiguration().getWhitespaceStripping(), getConfiguration().getTagSyntax(), getConfiguration().getIncompatibleImprovements().intValue());
                this.parser = fMParser;
                this.rootElement = fMParser.Root();
                this.actualTagSyntax = this.parser._getLastTagSyntax();
                this.parser = null;
                lineTableBuilder2.close();
                DebuggerService.registerTemplate(this);
                this.namespaceURIToPrefixLookup = Collections.unmodifiableMap(this.namespaceURIToPrefixLookup);
                this.prefixToNamespaceURILookup = Collections.unmodifiableMap(this.prefixToNamespaceURILookup);
            } catch (TokenMgrError e) {
                throw e.toParseException(this);
            } catch (ParseException e2) {
                LineTableBuilder lineTableBuilder3 = lineTableBuilder2;
                e = e2;
                lineTableBuilder = lineTableBuilder3;
                try {
                    e.setTemplateName(str);
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    lineTableBuilder.close();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                lineTableBuilder = lineTableBuilder2;
                lineTableBuilder.close();
                throw th;
            }
        } catch (ParseException e3) {
            e = e3;
            e.setTemplateName(str);
            throw e;
        }
    }

    public Template(String str, Reader reader) throws IOException {
        this(str, reader, (Configuration) null);
    }

    Template(String str, TemplateElement templateElement, Configuration configuration) {
        this(str, configuration);
        this.rootElement = templateElement;
        DebuggerService.registerTemplate(this);
    }

    public static Template getPlainTextTemplate(String str, String str2, Configuration configuration) {
        Template template = new Template(str, configuration);
        template.rootElement = new TextBlock(str2);
        DebuggerService.registerTemplate(template);
        return template;
    }

    public void process(Object obj, Writer writer) throws TemplateException, IOException {
        createProcessingEnvironment(obj, writer, (ObjectWrapper) null).process();
    }

    public void process(Object obj, Writer writer, ObjectWrapper objectWrapper, TemplateNodeModel templateNodeModel) throws TemplateException, IOException {
        Environment createProcessingEnvironment = createProcessingEnvironment(obj, writer, objectWrapper);
        if (templateNodeModel != null) {
            createProcessingEnvironment.setCurrentVisitorNode(templateNodeModel);
        }
        createProcessingEnvironment.process();
    }

    public void process(Object obj, Writer writer, ObjectWrapper objectWrapper) throws TemplateException, IOException {
        createProcessingEnvironment(obj, writer, objectWrapper).process();
    }

    public Environment createProcessingEnvironment(Object obj, Writer writer, ObjectWrapper objectWrapper) throws TemplateException, IOException {
        TemplateHashModel templateHashModel;
        if (obj instanceof TemplateHashModel) {
            templateHashModel = (TemplateHashModel) obj;
        } else {
            if (objectWrapper == null) {
                objectWrapper = getObjectWrapper();
            }
            if (obj == null) {
                templateHashModel = new SimpleHash(objectWrapper);
            } else {
                Object wrap = objectWrapper.wrap(obj);
                if (wrap instanceof TemplateHashModel) {
                    templateHashModel = (TemplateHashModel) wrap;
                } else if (wrap == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(objectWrapper.getClass().getName());
                    stringBuffer.append(" converted ");
                    stringBuffer.append(obj.getClass().getName());
                    stringBuffer.append(" to null.");
                    throw new IllegalArgumentException(stringBuffer.toString());
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(objectWrapper.getClass().getName());
                    stringBuffer2.append(" didn't convert ");
                    stringBuffer2.append(obj.getClass().getName());
                    stringBuffer2.append(" to a TemplateHashModel. Generally, you want to use a Map<String, Object> or a ");
                    stringBuffer2.append("JavaBean as the root-map (aka. data-model) parameter. The Map key-s or JavaBean ");
                    stringBuffer2.append("property names will be the variable names in the template.");
                    throw new IllegalArgumentException(stringBuffer2.toString());
                }
            }
        }
        return new Environment(this, templateHashModel, writer);
    }

    public Environment createProcessingEnvironment(Object obj, Writer writer) throws TemplateException, IOException {
        return createProcessingEnvironment(obj, writer, (ObjectWrapper) null);
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        try {
            dump((Writer) stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String getName() {
        return this.name;
    }

    public Configuration getConfiguration() {
        return (Configuration) getParent();
    }

    public void setEncoding(String str) {
        this.encoding = str;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public int getActualTagSyntax() {
        return this.actualTagSyntax;
    }

    public void dump(PrintStream printStream) {
        printStream.print(this.rootElement.getCanonicalForm());
    }

    public void dump(Writer writer) throws IOException {
        writer.write(this.rootElement.getCanonicalForm());
    }

    public void addMacro(Macro macro) {
        this.macros.put(macro.getName(), macro);
    }

    public void addImport(LibraryLoad libraryLoad) {
        this.imports.add(libraryLoad);
    }

    public String getSource(int i, int i2, int i3, int i4) {
        if (i2 < 1 || i4 < 1) {
            return null;
        }
        int i5 = i - 1;
        int i6 = i3 - 1;
        int i7 = i4 - 1;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i8 = i2 - 1; i8 <= i7; i8++) {
            if (i8 < this.lines.size()) {
                stringBuffer.append(this.lines.get(i8));
            }
        }
        stringBuffer.delete(0, i5);
        stringBuffer.delete(stringBuffer.length() - ((this.lines.get(i7).toString().length() - i6) - 1), stringBuffer.length());
        return stringBuffer.toString();
    }

    private class LineTableBuilder extends FilterReader {
        int lastChar;
        StringBuffer lineBuf = new StringBuffer();
        private final /* synthetic */ Template this$0;

        LineTableBuilder(Template template, Reader reader) {
            super(reader);
            this.this$0 = template;
        }

        public int read() throws IOException {
            int read = this.in.read();
            handleChar(read);
            return read;
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            int read = this.in.read(cArr, i, i2);
            for (int i3 = i; i3 < i + read; i3++) {
                handleChar(cArr[i3]);
            }
            return read;
        }

        public void close() throws IOException {
            if (this.lineBuf.length() > 0) {
                this.this$0.lines.add(this.lineBuf.toString());
                this.lineBuf.setLength(0);
            }
            super.close();
        }

        private void handleChar(int i) {
            if (i == 10 || i == 13) {
                if (this.lastChar == 13 && i == 10) {
                    int size = this.this$0.lines.size() - 1;
                    ArrayList access$000 = this.this$0.lines;
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append((String) this.this$0.lines.get(size));
                    stringBuffer.append(10);
                    access$000.set(size, stringBuffer.toString());
                } else {
                    this.lineBuf.append((char) i);
                    this.this$0.lines.add(this.lineBuf.toString());
                    this.lineBuf.setLength(0);
                }
            } else if (i == 9) {
                int length = 8 - (this.lineBuf.length() % 8);
                for (int i2 = 0; i2 < length; i2++) {
                    this.lineBuf.append(' ');
                }
            } else {
                this.lineBuf.append((char) i);
            }
            this.lastChar = i;
        }
    }

    public TemplateElement getRootTreeNode() {
        return this.rootElement;
    }

    public Map getMacros() {
        return this.macros;
    }

    public List getImports() {
        return this.imports;
    }

    public void addPrefixNSMapping(String str, String str2) {
        if (str2.length() == 0) {
            throw new IllegalArgumentException("Cannot map empty string URI");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("Cannot map empty string prefix");
        } else if (str.equals(NO_NS_PREFIX)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("The prefix: ");
            stringBuffer.append(str);
            stringBuffer.append(" cannot be registered, it's reserved for special internal use.");
            throw new IllegalArgumentException(stringBuffer.toString());
        } else if (this.prefixToNamespaceURILookup.containsKey(str)) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("The prefix: '");
            stringBuffer2.append(str);
            stringBuffer2.append("' was repeated. This is illegal.");
            throw new IllegalArgumentException(stringBuffer2.toString());
        } else if (this.namespaceURIToPrefixLookup.containsKey(str2)) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("The namespace URI: ");
            stringBuffer3.append(str2);
            stringBuffer3.append(" cannot be mapped to 2 different prefixes.");
            throw new IllegalArgumentException(stringBuffer3.toString());
        } else if (str.equals(DEFAULT_NAMESPACE_PREFIX)) {
            this.defaultNS = str2;
        } else {
            this.prefixToNamespaceURILookup.put(str, str2);
            this.namespaceURIToPrefixLookup.put(str2, str);
        }
    }

    public String getDefaultNS() {
        return this.defaultNS;
    }

    public String getNamespaceForPrefix(String str) {
        if (!str.equals("")) {
            return (String) this.prefixToNamespaceURILookup.get(str);
        }
        String str2 = this.defaultNS;
        if (str2 == null) {
            return "";
        }
        return str2;
    }

    public String getPrefixForNamespace(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            if (this.defaultNS == null) {
                return "";
            }
            return NO_NS_PREFIX;
        } else if (str.equals(this.defaultNS)) {
            return "";
        } else {
            return (String) this.namespaceURIToPrefixLookup.get(str);
        }
    }

    public String getPrefixedName(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            if (this.defaultNS == null) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("N:");
            stringBuffer.append(str);
            return stringBuffer.toString();
        } else if (str2.equals(this.defaultNS)) {
            return str;
        } else {
            String prefixForNamespace = getPrefixForNamespace(str2);
            if (prefixForNamespace == null) {
                return null;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(prefixForNamespace);
            stringBuffer2.append(":");
            stringBuffer2.append(str);
            return stringBuffer2.toString();
        }
    }

    public TreePath containingElements(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        TemplateElement templateElement = this.rootElement;
        loop0:
        while (templateElement.contains(i, i2)) {
            arrayList.add(templateElement);
            Enumeration children = templateElement.children();
            while (children.hasMoreElements()) {
                TemplateElement templateElement2 = (TemplateElement) children.nextElement();
                if (templateElement2.contains(i, i2)) {
                    templateElement = templateElement2;
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new TreePath(arrayList.toArray());
    }

    public static class WrongEncodingException extends ParseException {
        public String specifiedEncoding;

        public WrongEncodingException(String str) {
            this.specifiedEncoding = str;
        }
    }
}
