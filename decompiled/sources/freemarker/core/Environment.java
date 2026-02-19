package freemarker.core;

import com.google.firebase.analytics.FirebaseAnalytics;
import freemarker.core.BodyInstruction;
import freemarker.core.BreakInstruction;
import freemarker.core.IteratorBlock;
import freemarker.core.Macro;
import freemarker.core.ReturnInstruction;
import freemarker.ext.beans.BeansWrapper;
import freemarker.log.Logger;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.SimpleSequence;
import freemarker.template.Template;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;
import freemarker.template.TemplateNodeModel;
import freemarker.template.TemplateScalarModel;
import freemarker.template.TemplateSequenceModel;
import freemarker.template.TemplateTransformModel;
import freemarker.template.TransformControl;
import freemarker.template.utility.DateUtil;
import freemarker.template.utility.NullWriter;
import freemarker.template.utility.StringUtil;
import freemarker.template.utility.UndeclaredThrowableException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.Collator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

public final class Environment extends Configurable {
    private static final DecimalFormat C_NUMBER_FORMAT;
    private static final Writer EMPTY_BODY_WRITER = new Writer() {
        public void close() {
        }

        public void flush() {
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            if (i2 > 0) {
                throw new IOException("This transform does not allow nested content.");
            }
        }
    };
    private static final TemplateModel[] NO_OUT_ARGS = new TemplateModel[0];
    static final String STACK_SECTION_SEPARATOR = "----------";
    private static final Logger attemptLogger = Logger.getLogger("freemarker.runtime.attempt");
    private static final Map localizedDateFormats = new HashMap();
    private static final Map localizedNumberFormats = new HashMap();
    private static final Logger logger = Logger.getLogger("freemarker.runtime");
    private static final ThreadLocal threadEnv = new ThreadLocal();
    private NumberFormat cNumberFormat;
    private String cachedURLEscapingCharset;
    private Collator collator;
    private Macro.Context currentMacroContext;
    private Namespace currentNamespace;
    private String currentNodeNS;
    private String currentNodeName;
    private TemplateNodeModel currentVisitorNode;
    private DateFormat dateFormat;
    private Map[] dateFormats;
    private DateFormat dateTimeFormat;
    private boolean fastInvalidReferenceExceptions;
    /* access modifiers changed from: private */
    public Namespace globalNamespace = new Namespace((Template) null);
    private boolean inAttemptBlock;
    private final ArrayList instructionStack = new ArrayList();
    private DateUtil.DateToISO8601CalendarFactory isoBuiltInCalendarFactory;
    private TemplateModel lastReturnValue;
    private Throwable lastThrowable;
    private HashMap loadedLibs;
    private ArrayList localContextStack;
    private HashMap macroToNamespaceLookup = new HashMap();
    private Namespace mainNamespace;
    private int nodeNamespaceIndex;
    private TemplateSequenceModel nodeNamespaces;
    private NumberFormat numberFormat;
    private Map numberFormats;
    /* access modifiers changed from: private */
    public Writer out;
    private final ArrayList recoveredErrorStack = new ArrayList();
    /* access modifiers changed from: private */
    public final TemplateHashModel rootDataModel;
    private DateFormat timeFormat;
    private boolean urlEscapingCharsetCached;

    static {
        DecimalFormat decimalFormat = new DecimalFormat("0.################", new DecimalFormatSymbols(Locale.US));
        C_NUMBER_FORMAT = decimalFormat;
        decimalFormat.setGroupingUsed(false);
        decimalFormat.setDecimalSeparatorAlwaysShown(false);
    }

    public static Environment getCurrentEnvironment() {
        return (Environment) threadEnv.get();
    }

    public Environment(Template template, TemplateHashModel templateHashModel, Writer writer) {
        super(template);
        Namespace namespace = new Namespace(template);
        this.mainNamespace = namespace;
        this.currentNamespace = namespace;
        this.out = writer;
        this.rootDataModel = templateHashModel;
        importMacros(template);
    }

    public Template getTemplate() {
        return (Template) getParent();
    }

    private void clearCachedValues() {
        this.numberFormats = null;
        this.numberFormat = null;
        this.dateFormats = null;
        this.collator = null;
        this.cachedURLEscapingCharset = null;
        this.urlEscapingCharsetCached = false;
    }

    public void process() throws TemplateException, IOException {
        ThreadLocal threadLocal = threadEnv;
        Object obj = threadLocal.get();
        threadLocal.set(this);
        try {
            clearCachedValues();
            doAutoImportsAndIncludes(this);
            visit(getTemplate().getRootTreeNode());
            if (getAutoFlush()) {
                this.out.flush();
            }
            clearCachedValues();
            threadLocal.set(obj);
        } catch (Throwable th) {
            threadEnv.set(obj);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void visit(TemplateElement templateElement) throws TemplateException, IOException {
        pushElement(templateElement);
        try {
            templateElement.accept(this);
        } catch (TemplateException e) {
            handleTemplateException(e);
        } catch (Throwable th) {
            popElement();
            throw th;
        }
        popElement();
    }

    /* access modifiers changed from: package-private */
    public void visitByHiddingParent(TemplateElement templateElement) throws TemplateException, IOException {
        TemplateElement replaceTopElement = replaceTopElement(templateElement);
        try {
            templateElement.accept(this);
        } catch (TemplateException e) {
            handleTemplateException(e);
        } catch (Throwable th) {
            replaceTopElement(replaceTopElement);
            throw th;
        }
        replaceTopElement(replaceTopElement);
    }

    private TemplateElement replaceTopElement(TemplateElement templateElement) {
        ArrayList arrayList = this.instructionStack;
        return (TemplateElement) arrayList.set(arrayList.size() - 1, templateElement);
    }

    public void visit(final TemplateElement templateElement, TemplateDirectiveModel templateDirectiveModel, Map map, final List list) throws TemplateException, IOException {
        AnonymousClass1 r3;
        final TemplateModel[] templateModelArr;
        if (templateElement == null) {
            r3 = null;
        } else {
            r3 = new TemplateDirectiveBody() {
                public void render(Writer writer) throws TemplateException, IOException {
                    Writer access$000 = Environment.this.out;
                    Writer unused = Environment.this.out = writer;
                    try {
                        Environment.this.visit(templateElement);
                    } finally {
                        Writer unused2 = Environment.this.out = access$000;
                    }
                }
            };
        }
        if (list == null || list.isEmpty()) {
            templateModelArr = NO_OUT_ARGS;
        } else {
            templateModelArr = new TemplateModel[list.size()];
        }
        if (templateModelArr.length > 0) {
            pushLocalContext(new LocalContext() {
                public TemplateModel getLocalVariable(String str) {
                    int indexOf = list.indexOf(str);
                    if (indexOf != -1) {
                        return templateModelArr[indexOf];
                    }
                    return null;
                }

                public Collection getLocalVariableNames() {
                    return list;
                }
            });
        }
        try {
            templateDirectiveModel.execute(this, map, templateModelArr, r3);
        } finally {
            if (templateModelArr.length > 0) {
                popLocalContext();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitAndTransform(TemplateElement templateElement, TemplateTransformModel templateTransformModel, Map map) throws TemplateException, IOException {
        try {
            Writer writer = templateTransformModel.getWriter(this.out, map);
            if (writer == null) {
                writer = EMPTY_BODY_WRITER;
            }
            TransformControl transformControl = writer instanceof TransformControl ? (TransformControl) writer : null;
            Writer writer2 = this.out;
            this.out = writer;
            if (transformControl != null) {
                try {
                    if (transformControl.onStart() != 0) {
                    }
                    this.out = writer2;
                } catch (TemplateException e) {
                    throw e;
                } catch (IOException e2) {
                    throw e2;
                } catch (RuntimeException e3) {
                    throw e3;
                } catch (Error e4) {
                    throw e4;
                } catch (Throwable th) {
                    try {
                        throw new UndeclaredThrowableException(th);
                    } catch (Throwable th2) {
                        this.out = writer2;
                        writer.close();
                        throw th2;
                    }
                }
                writer.close();
            }
            do {
                if (templateElement != null) {
                    visitByHiddingParent(templateElement);
                }
                if (transformControl == null) {
                    break;
                }
            } while (transformControl.afterBody() != 0);
            this.out = writer2;
            writer.close();
        } catch (TemplateException e5) {
            handleTemplateException(e5);
        }
    }

    /* access modifiers changed from: package-private */
    public void visitAttemptRecover(TemplateElement templateElement, RecoveryBlock recoveryBlock) throws TemplateException, IOException {
        TemplateException templateException;
        Writer writer = this.out;
        StringWriter stringWriter = new StringWriter();
        this.out = stringWriter;
        boolean fastInvalidReferenceExceptions2 = setFastInvalidReferenceExceptions(false);
        boolean z = this.inAttemptBlock;
        try {
            this.inAttemptBlock = true;
            visitByHiddingParent(templateElement);
            this.inAttemptBlock = z;
            setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions2);
            this.out = writer;
            templateException = null;
        } catch (TemplateException e) {
            this.inAttemptBlock = z;
            setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions2);
            this.out = writer;
            templateException = e;
        } catch (Throwable th) {
            this.inAttemptBlock = z;
            setFastInvalidReferenceExceptions(fastInvalidReferenceExceptions2);
            this.out = writer;
            throw th;
        }
        if (templateException != null) {
            Logger logger2 = attemptLogger;
            if (logger2.isDebugEnabled()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Error in attempt block ");
                stringBuffer.append(templateElement.getStartLocationQuoted());
                logger2.debug(stringBuffer.toString(), templateException);
            }
            try {
                this.recoveredErrorStack.add(templateException);
                visit((TemplateElement) recoveryBlock);
            } finally {
                ArrayList arrayList = this.recoveredErrorStack;
                arrayList.remove(arrayList.size() - 1);
            }
        } else {
            this.out.write(stringWriter.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public String getCurrentRecoveredErrorMessage() throws TemplateException {
        if (!this.recoveredErrorStack.isEmpty()) {
            ArrayList arrayList = this.recoveredErrorStack;
            return ((Throwable) arrayList.get(arrayList.size() - 1)).getMessage();
        }
        throw new _MiscTemplateException(this, ".error is not available outside of a #recover block");
    }

    public boolean isInAttemptBlock() {
        return this.inAttemptBlock;
    }

    /* access modifiers changed from: package-private */
    public void visit(BodyInstruction.Context context) throws TemplateException, IOException {
        Macro.Context currentMacroContext2 = getCurrentMacroContext();
        ArrayList arrayList = this.localContextStack;
        TemplateElement templateElement = currentMacroContext2.body;
        if (templateElement != null) {
            this.currentMacroContext = currentMacroContext2.prevMacroContext;
            this.currentNamespace = currentMacroContext2.bodyNamespace;
            Configurable parent = getParent();
            setParent(this.currentNamespace.getTemplate());
            this.localContextStack = currentMacroContext2.prevLocalContextStack;
            if (currentMacroContext2.bodyParameterNames != null) {
                pushLocalContext(context);
            }
            try {
                visit(templateElement);
            } finally {
                if (currentMacroContext2.bodyParameterNames != null) {
                    popLocalContext();
                }
                this.currentMacroContext = currentMacroContext2;
                this.currentNamespace = getMacroNamespace(currentMacroContext2.getMacro());
                setParent(parent);
                this.localContextStack = arrayList;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitIteratorBlock(IteratorBlock.Context context) throws TemplateException, IOException {
        pushLocalContext(context);
        try {
            context.runLoop(this);
        } catch (BreakInstruction.Break unused) {
        } catch (TemplateException e) {
            handleTemplateException(e);
        } catch (Throwable th) {
            popLocalContext();
            throw th;
        }
        popLocalContext();
    }

    /* access modifiers changed from: package-private */
    public void visit(TemplateNodeModel templateNodeModel, TemplateSequenceModel templateSequenceModel) throws TemplateException, IOException {
        if (this.nodeNamespaces == null) {
            SimpleSequence simpleSequence = new SimpleSequence(1);
            simpleSequence.add((Object) this.currentNamespace);
            this.nodeNamespaces = simpleSequence;
        }
        int i = this.nodeNamespaceIndex;
        String str = this.currentNodeName;
        String str2 = this.currentNodeNS;
        TemplateSequenceModel templateSequenceModel2 = this.nodeNamespaces;
        TemplateNodeModel templateNodeModel2 = this.currentVisitorNode;
        this.currentVisitorNode = templateNodeModel;
        if (templateSequenceModel != null) {
            this.nodeNamespaces = templateSequenceModel;
        }
        try {
            TemplateModel nodeProcessor = getNodeProcessor(templateNodeModel);
            if (nodeProcessor instanceof Macro) {
                visit((Macro) nodeProcessor, (Map) null, (List) null, (List) null, (TemplateElement) null);
            } else if (nodeProcessor instanceof TemplateTransformModel) {
                visitAndTransform((TemplateElement) null, (TemplateTransformModel) nodeProcessor, (Map) null);
            } else {
                String nodeType = templateNodeModel.getNodeType();
                if (nodeType == null) {
                    throw new _MiscTemplateException(this, noNodeHandlerDefinedDescription(templateNodeModel, templateNodeModel.getNodeNamespace(), "default"));
                } else if (nodeType.equals("text") && (templateNodeModel instanceof TemplateScalarModel)) {
                    this.out.write(((TemplateScalarModel) templateNodeModel).getAsString());
                } else if (nodeType.equals("document")) {
                    recurse(templateNodeModel, templateSequenceModel);
                } else if (!nodeType.equals("pi") && !nodeType.equals("comment")) {
                    if (!nodeType.equals("document_type")) {
                        throw new _MiscTemplateException(this, noNodeHandlerDefinedDescription(templateNodeModel, templateNodeModel.getNodeNamespace(), nodeType));
                    }
                }
            }
        } finally {
            this.currentVisitorNode = templateNodeModel2;
            this.nodeNamespaceIndex = i;
            this.currentNodeName = str;
            this.currentNodeNS = str2;
            this.nodeNamespaces = templateSequenceModel2;
        }
    }

    private Object[] noNodeHandlerDefinedDescription(TemplateNodeModel templateNodeModel, String str, String str2) throws TemplateModelException {
        String str3 = "";
        if (str != null) {
            str3 = str.length() > 0 ? " and namespace " : " and no namespace";
        } else {
            str = str3;
        }
        return new Object[]{"No macro or directive is defined for node named ", new _DelayedJQuote(templateNodeModel.getNodeName()), str3, str, ", and there is no fallback handler called @", str2, " either."};
    }

    /* access modifiers changed from: package-private */
    public void fallback() throws TemplateException, IOException {
        TemplateModel nodeProcessor = getNodeProcessor(this.currentNodeName, this.currentNodeNS, this.nodeNamespaceIndex);
        if (nodeProcessor instanceof Macro) {
            visit((Macro) nodeProcessor, (Map) null, (List) null, (List) null, (TemplateElement) null);
        } else if (nodeProcessor instanceof TemplateTransformModel) {
            visitAndTransform((TemplateElement) null, (TemplateTransformModel) nodeProcessor, (Map) null);
        }
    }

    /* access modifiers changed from: package-private */
    public void visit(Macro macro, Map map, List list, List list2, TemplateElement templateElement) throws TemplateException, IOException {
        Macro.Context context;
        SimpleHash simpleHash;
        ArrayList arrayList;
        Namespace namespace;
        Configurable parent;
        if (macro != Macro.DO_NOTHING_MACRO) {
            pushElement(macro);
            try {
                context = this.currentMacroContext;
                macro.getClass();
                Macro.Context context2 = new Macro.Context(this, templateElement, list2);
                String catchAll = macro.getCatchAll();
                if (map != null) {
                    simpleHash = catchAll != null ? new SimpleHash() : null;
                    for (Map.Entry entry : map.entrySet()) {
                        String str = (String) entry.getKey();
                        boolean hasArgNamed = macro.hasArgNamed(str);
                        if (!hasArgNamed) {
                            if (catchAll == null) {
                                throw new _MiscTemplateException(this, new Object[]{"Macro ", new _DelayedJQuote(macro.getName()), " has no such argument: ", str});
                            }
                        }
                        TemplateModel eval = ((Expression) entry.getValue()).eval(this);
                        if (hasArgNamed) {
                            context2.setLocalVar(str, eval);
                        } else {
                            simpleHash.put(str, (Object) eval);
                        }
                    }
                } else if (list != null) {
                    SimpleSequence simpleSequence = catchAll != null ? new SimpleSequence() : null;
                    String[] argumentNamesInternal = macro.getArgumentNamesInternal();
                    int size = list.size();
                    if (argumentNamesInternal.length < size) {
                        if (catchAll == null) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Macro ");
                            stringBuffer.append(StringUtil.jQuote(macro.getName()));
                            stringBuffer.append(" only accepts ");
                            stringBuffer.append(argumentNamesInternal.length);
                            stringBuffer.append(" parameters.");
                            throw new _MiscTemplateException(this, new Object[]{stringBuffer.toString()});
                        }
                    }
                    for (int i = 0; i < size; i++) {
                        TemplateModel eval2 = ((Expression) list.get(i)).eval(this);
                        if (i < argumentNamesInternal.length) {
                            context2.setLocalVar(argumentNamesInternal[i], eval2);
                        } else {
                            simpleSequence.add((Object) eval2);
                        }
                    }
                    simpleHash = simpleSequence;
                } else {
                    simpleHash = null;
                }
                if (catchAll != null) {
                    context2.setLocalVar(catchAll, simpleHash);
                }
                arrayList = this.localContextStack;
                this.localContextStack = null;
                namespace = this.currentNamespace;
                parent = getParent();
                this.currentNamespace = (Namespace) this.macroToNamespaceLookup.get(macro);
                this.currentMacroContext = context2;
                try {
                    context2.runMacro(this);
                    this.currentMacroContext = context;
                    this.localContextStack = arrayList;
                    this.currentNamespace = namespace;
                } catch (ReturnInstruction.Return unused) {
                    this.currentMacroContext = context;
                    this.localContextStack = arrayList;
                    this.currentNamespace = namespace;
                } catch (TemplateException e) {
                    handleTemplateException(e);
                    this.currentMacroContext = context;
                    this.localContextStack = arrayList;
                    this.currentNamespace = namespace;
                }
                setParent(parent);
                popElement();
            } catch (RuntimeException e2) {
                throw new _MiscTemplateException((Throwable) e2, this);
            } catch (Throwable th) {
                popElement();
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitMacroDef(Macro macro) {
        this.macroToNamespaceLookup.put(macro, this.currentNamespace);
        this.currentNamespace.put(macro.getName(), (Object) macro);
    }

    /* access modifiers changed from: package-private */
    public Namespace getMacroNamespace(Macro macro) {
        return (Namespace) this.macroToNamespaceLookup.get(macro);
    }

    /* access modifiers changed from: package-private */
    public void recurse(TemplateNodeModel templateNodeModel, TemplateSequenceModel templateSequenceModel) throws TemplateException, IOException {
        if (templateNodeModel == null && (templateNodeModel = getCurrentVisitorNode()) == null) {
            throw new _TemplateModelException("The target node of recursion is missing or null.");
        }
        TemplateSequenceModel childNodes = templateNodeModel.getChildNodes();
        if (childNodes != null) {
            for (int i = 0; i < childNodes.size(); i++) {
                TemplateNodeModel templateNodeModel2 = (TemplateNodeModel) childNodes.get(i);
                if (templateNodeModel2 != null) {
                    visit(templateNodeModel2, templateSequenceModel);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Macro.Context getCurrentMacroContext() {
        return this.currentMacroContext;
    }

    private void handleTemplateException(TemplateException templateException) throws TemplateException {
        if (this.lastThrowable != templateException) {
            this.lastThrowable = templateException;
            Logger logger2 = logger;
            if (logger2.isErrorEnabled()) {
                logger2.error("Error executing FreeMarker template", templateException);
            }
            if (!(templateException instanceof StopException)) {
                getTemplateExceptionHandler().handleTemplateException(templateException, this, this.out);
                return;
            }
            throw templateException;
        }
        throw templateException;
    }

    public void setTemplateExceptionHandler(TemplateExceptionHandler templateExceptionHandler) {
        super.setTemplateExceptionHandler(templateExceptionHandler);
        this.lastThrowable = null;
    }

    public void setLocale(Locale locale) {
        super.setLocale(locale);
        this.numberFormats = null;
        this.numberFormat = null;
        this.dateFormats = null;
        this.dateTimeFormat = null;
        this.dateFormat = null;
        this.timeFormat = null;
        this.collator = null;
    }

    public void setTimeZone(TimeZone timeZone) {
        super.setTimeZone(timeZone);
        this.dateFormats = null;
        this.dateTimeFormat = null;
        this.dateFormat = null;
        this.timeFormat = null;
    }

    public void setURLEscapingCharset(String str) {
        this.urlEscapingCharsetCached = false;
        super.setURLEscapingCharset(str);
    }

    public void setOutputEncoding(String str) {
        this.urlEscapingCharsetCached = false;
        super.setOutputEncoding(str);
    }

    /* access modifiers changed from: package-private */
    public String getEffectiveURLEscapingCharset() {
        if (!this.urlEscapingCharsetCached) {
            String uRLEscapingCharset = getURLEscapingCharset();
            this.cachedURLEscapingCharset = uRLEscapingCharset;
            if (uRLEscapingCharset == null) {
                this.cachedURLEscapingCharset = getOutputEncoding();
            }
            this.urlEscapingCharsetCached = true;
        }
        return this.cachedURLEscapingCharset;
    }

    /* access modifiers changed from: package-private */
    public Collator getCollator() {
        if (this.collator == null) {
            this.collator = Collator.getInstance(getLocale());
        }
        return this.collator;
    }

    public boolean applyEqualsOperator(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compare(templateModel, 1, templateModel2, this);
    }

    public boolean applyEqualsOperatorLenient(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compareLenient(templateModel, 1, templateModel2, this);
    }

    public boolean applyLessThanOperator(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compare(templateModel, 3, templateModel2, this);
    }

    public boolean applyLessThanOrEqualsOperator(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compare(templateModel, 5, templateModel2, this);
    }

    public boolean applyGreaterThanOperator(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compare(templateModel, 4, templateModel2, this);
    }

    public boolean applyWithGreaterThanOrEqualsOperator(TemplateModel templateModel, TemplateModel templateModel2) throws TemplateException {
        return EvalUtil.compare(templateModel, 6, templateModel2, this);
    }

    public void setOut(Writer writer) {
        this.out = writer;
    }

    public Writer getOut() {
        return this.out;
    }

    /* access modifiers changed from: package-private */
    public String formatNumber(Number number) {
        if (this.numberFormat == null) {
            this.numberFormat = getNumberFormatObject(getNumberFormat());
        }
        return this.numberFormat.format(number);
    }

    public void setNumberFormat(String str) {
        super.setNumberFormat(str);
        this.numberFormat = null;
    }

    /* access modifiers changed from: package-private */
    public String formatDate(Date date, int i) throws TemplateModelException {
        DateFormat dateFormatObject = getDateFormatObject(i);
        if (dateFormatObject != null) {
            return dateFormatObject.format(date);
        }
        throw new _TemplateModelException(new _ErrorDescriptionBuilder("Can't convert the date to string, because it's not known which parts of the date variable are in use.").tips((Object[]) MessageUtil.UNKNOWN_DATE_TYPE_ERROR_TIPS));
    }

    public void setTimeFormat(String str) {
        super.setTimeFormat(str);
        this.timeFormat = null;
    }

    public void setDateFormat(String str) {
        super.setDateFormat(str);
        this.dateFormat = null;
    }

    public void setDateTimeFormat(String str) {
        super.setDateTimeFormat(str);
        this.dateTimeFormat = null;
    }

    public Configuration getConfiguration() {
        return getTemplate().getConfiguration();
    }

    /* access modifiers changed from: package-private */
    public TemplateModel getLastReturnValue() {
        return this.lastReturnValue;
    }

    /* access modifiers changed from: package-private */
    public void setLastReturnValue(TemplateModel templateModel) {
        this.lastReturnValue = templateModel;
    }

    /* access modifiers changed from: package-private */
    public void clearLastReturnValue() {
        this.lastReturnValue = null;
    }

    /* access modifiers changed from: package-private */
    public NumberFormat getNumberFormatObject(String str) {
        NumberFormat numberFormat2;
        NumberFormat decimalFormat;
        if (this.numberFormats == null) {
            this.numberFormats = new HashMap();
        }
        NumberFormat numberFormat3 = (NumberFormat) this.numberFormats.get(str);
        if (numberFormat3 != null) {
            return numberFormat3;
        }
        Map map = localizedNumberFormats;
        synchronized (map) {
            Locale locale = getLocale();
            NumberFormatKey numberFormatKey = new NumberFormatKey(str, locale);
            numberFormat2 = (NumberFormat) map.get(numberFormatKey);
            if (numberFormat2 == null) {
                if ("number".equals(str)) {
                    decimalFormat = NumberFormat.getNumberInstance(locale);
                } else if (FirebaseAnalytics.Param.CURRENCY.equals(str)) {
                    decimalFormat = NumberFormat.getCurrencyInstance(locale);
                } else if ("percent".equals(str)) {
                    decimalFormat = NumberFormat.getPercentInstance(locale);
                } else if ("computer".equals(str)) {
                    decimalFormat = getCNumberFormat();
                } else {
                    decimalFormat = new DecimalFormat(str, new DecimalFormatSymbols(getLocale()));
                }
                numberFormat2 = decimalFormat;
                map.put(numberFormatKey, numberFormat2);
            }
        }
        NumberFormat numberFormat4 = (NumberFormat) numberFormat2.clone();
        this.numberFormats.put(str, numberFormat4);
        return numberFormat4;
    }

    /* access modifiers changed from: package-private */
    public DateFormat getDateFormatObject(int i) throws TemplateModelException {
        if (i == 0) {
            return null;
        }
        if (i == 1) {
            if (this.timeFormat == null) {
                this.timeFormat = getDateFormatObject(i, getTimeFormat());
            }
            return this.timeFormat;
        } else if (i == 2) {
            if (this.dateFormat == null) {
                this.dateFormat = getDateFormatObject(i, getDateFormat());
            }
            return this.dateFormat;
        } else if (i == 3) {
            if (this.dateTimeFormat == null) {
                this.dateTimeFormat = getDateFormatObject(i, getDateTimeFormat());
            }
            return this.dateTimeFormat;
        } else {
            throw new _TemplateModelException(new Object[]{"Unrecognized date type: ", new Integer(i)});
        }
    }

    /* access modifiers changed from: package-private */
    public DateFormat getDateFormatObject(int i, String str) throws TemplateModelException {
        DateFormat dateFormat2;
        int i2 = i;
        String str2 = str;
        if (this.dateFormats == null) {
            Map[] mapArr = new Map[4];
            this.dateFormats = mapArr;
            mapArr[0] = new HashMap();
            this.dateFormats[1] = new HashMap();
            this.dateFormats[2] = new HashMap();
            this.dateFormats[3] = new HashMap();
        }
        Map map = this.dateFormats[i2];
        DateFormat dateFormat3 = (DateFormat) map.get(str2);
        if (dateFormat3 != null) {
            return dateFormat3;
        }
        Map map2 = localizedDateFormats;
        synchronized (map2) {
            Locale locale = getLocale();
            TimeZone timeZone = getTimeZone();
            DateFormatKey dateFormatKey = new DateFormatKey(i2, str2, locale, timeZone);
            dateFormat2 = (DateFormat) map2.get(dateFormatKey);
            if (dateFormat2 == null) {
                StringTokenizer stringTokenizer = new StringTokenizer(str2, "_");
                int parseDateStyleToken = stringTokenizer.hasMoreTokens() ? parseDateStyleToken(stringTokenizer.nextToken()) : 2;
                if (parseDateStyleToken != -1) {
                    if (i2 == 0) {
                        throw new _TemplateModelException(new _ErrorDescriptionBuilder("Can't convert the date to string using a built-in format because it's not known which parts of the date are in use.").tips((Object[]) MessageUtil.UNKNOWN_DATE_TO_STRING_TIPS));
                    } else if (i2 == 1) {
                        dateFormat2 = DateFormat.getTimeInstance(parseDateStyleToken, locale);
                    } else if (i2 == 2) {
                        dateFormat2 = DateFormat.getDateInstance(parseDateStyleToken, locale);
                    } else if (i2 == 3) {
                        int parseDateStyleToken2 = stringTokenizer.hasMoreTokens() ? parseDateStyleToken(stringTokenizer.nextToken()) : parseDateStyleToken;
                        if (parseDateStyleToken2 != -1) {
                            dateFormat2 = DateFormat.getDateTimeInstance(parseDateStyleToken, parseDateStyleToken2, locale);
                        }
                    }
                }
                if (dateFormat2 == null) {
                    try {
                        dateFormat2 = new SimpleDateFormat(str2, locale);
                    } catch (IllegalArgumentException e) {
                        throw new _TemplateModelException((Throwable) e, new Object[]{"Can't parse ", new _DelayedJQuote(str2), " to a date format, because:\n", e});
                    }
                }
                dateFormat2.setTimeZone(timeZone);
                map2.put(dateFormatKey, dateFormat2);
            }
        }
        DateFormat dateFormat4 = (DateFormat) dateFormat2.clone();
        map.put(str2, dateFormat4);
        return dateFormat4;
    }

    /* access modifiers changed from: package-private */
    public int parseDateStyleToken(String str) {
        if ("short".equals(str)) {
            return 3;
        }
        if (FirebaseAnalytics.Param.MEDIUM.equals(str)) {
            return 2;
        }
        if ("long".equals(str)) {
            return 1;
        }
        return "full".equals(str) ? 0 : -1;
    }

    /* access modifiers changed from: package-private */
    public DateUtil.DateToISO8601CalendarFactory getISOBuiltInCalendar() {
        if (this.isoBuiltInCalendarFactory == null) {
            this.isoBuiltInCalendarFactory = new DateUtil.TrivialDateToISO8601CalendarFactory();
        }
        return this.isoBuiltInCalendarFactory;
    }

    public NumberFormat getCNumberFormat() {
        if (this.cNumberFormat == null) {
            this.cNumberFormat = (DecimalFormat) C_NUMBER_FORMAT.clone();
        }
        return this.cNumberFormat;
    }

    /* access modifiers changed from: package-private */
    public TemplateTransformModel getTransform(Expression expression) throws TemplateException {
        TemplateModel eval = expression.eval(this);
        if (eval instanceof TemplateTransformModel) {
            return (TemplateTransformModel) eval;
        }
        if (expression instanceof Identifier) {
            TemplateModel sharedVariable = getConfiguration().getSharedVariable(expression.toString());
            if (sharedVariable instanceof TemplateTransformModel) {
                return (TemplateTransformModel) sharedVariable;
            }
        }
        return null;
    }

    public TemplateModel getLocalVariable(String str) throws TemplateModelException {
        ArrayList arrayList = this.localContextStack;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                TemplateModel localVariable = ((LocalContext) this.localContextStack.get(size)).getLocalVariable(str);
                if (localVariable != null) {
                    return localVariable;
                }
            }
        }
        Macro.Context context = this.currentMacroContext;
        if (context == null) {
            return null;
        }
        return context.getLocalVariable(str);
    }

    public TemplateModel getVariable(String str) throws TemplateModelException {
        TemplateModel localVariable = getLocalVariable(str);
        if (localVariable == null) {
            localVariable = this.currentNamespace.get(str);
        }
        return localVariable == null ? getGlobalVariable(str) : localVariable;
    }

    public TemplateModel getGlobalVariable(String str) throws TemplateModelException {
        TemplateModel templateModel = this.globalNamespace.get(str);
        if (templateModel == null) {
            templateModel = this.rootDataModel.get(str);
        }
        return templateModel == null ? getConfiguration().getSharedVariable(str) : templateModel;
    }

    public void setGlobalVariable(String str, TemplateModel templateModel) {
        this.globalNamespace.put(str, (Object) templateModel);
    }

    public void setVariable(String str, TemplateModel templateModel) {
        this.currentNamespace.put(str, (Object) templateModel);
    }

    public void setLocalVariable(String str, TemplateModel templateModel) {
        Macro.Context context = this.currentMacroContext;
        if (context != null) {
            context.setLocalVar(str, templateModel);
            return;
        }
        throw new IllegalStateException("Not executing macro body");
    }

    public Set getKnownVariableNames() throws TemplateModelException {
        Set sharedVariableNames = getConfiguration().getSharedVariableNames();
        TemplateHashModel templateHashModel = this.rootDataModel;
        if (templateHashModel instanceof TemplateHashModelEx) {
            TemplateModelIterator it = ((TemplateHashModelEx) templateHashModel).keys().iterator();
            while (it.hasNext()) {
                sharedVariableNames.add(((TemplateScalarModel) it.next()).getAsString());
            }
        }
        TemplateModelIterator it2 = this.globalNamespace.keys().iterator();
        while (it2.hasNext()) {
            sharedVariableNames.add(((TemplateScalarModel) it2.next()).getAsString());
        }
        TemplateModelIterator it3 = this.currentNamespace.keys().iterator();
        while (it3.hasNext()) {
            sharedVariableNames.add(((TemplateScalarModel) it3.next()).getAsString());
        }
        Macro.Context context = this.currentMacroContext;
        if (context != null) {
            sharedVariableNames.addAll(context.getLocalVariableNames());
        }
        ArrayList arrayList = this.localContextStack;
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                sharedVariableNames.addAll(((LocalContext) this.localContextStack.get(size)).getLocalVariableNames());
            }
        }
        return sharedVariableNames;
    }

    public void outputInstructionStack(PrintWriter printWriter) {
        outputInstructionStack(getInstructionStackSnapshot(), printWriter);
        printWriter.flush();
    }

    static void outputInstructionStack(TemplateElement[] templateElementArr, PrintWriter printWriter) {
        printWriter.println("----------");
        if (templateElementArr != null) {
            int i = 0;
            while (i < templateElementArr.length) {
                TemplateElement templateElement = templateElementArr[i];
                printWriter.print(i == 0 ? "==> " : "    ");
                printWriter.println(instructionStackItemToString(templateElement));
                i++;
            }
        } else {
            printWriter.println("[the stack was empty]");
        }
        printWriter.println("----------");
    }

    /* access modifiers changed from: package-private */
    public TemplateElement[] getInstructionStackSnapshot() {
        int size = this.instructionStack.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            TemplateElement templateElement = (TemplateElement) this.instructionStack.get(i2);
            if (i2 == size || templateElement.isShownInStackTrace()) {
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        TemplateElement[] templateElementArr = new TemplateElement[i];
        int i3 = i - 1;
        for (int i4 = 0; i4 < size; i4++) {
            TemplateElement templateElement2 = (TemplateElement) this.instructionStack.get(i4);
            if (i4 == size || templateElement2.isShownInStackTrace()) {
                templateElementArr[i3] = templateElement2;
                i3--;
            }
        }
        return templateElementArr;
    }

    static String instructionStackItemToString(TemplateElement templateElement) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(MessageUtil.shorten(templateElement.getDescription(), 40));
        stringBuffer.append("  [");
        Macro enclosingMacro = getEnclosingMacro(templateElement);
        if (enclosingMacro != null) {
            stringBuffer.append(MessageUtil.formatLocationForEvaluationError(enclosingMacro, templateElement.beginLine, templateElement.beginColumn));
        } else {
            stringBuffer.append(MessageUtil.formatLocationForEvaluationError(templateElement.getTemplate(), templateElement.beginLine, templateElement.beginColumn));
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    private static Macro getEnclosingMacro(TemplateElement templateElement) {
        while (templateElement != null) {
            if (templateElement instanceof Macro) {
                return (Macro) templateElement;
            }
            templateElement = (TemplateElement) templateElement.getParent();
        }
        return null;
    }

    private void pushLocalContext(LocalContext localContext) {
        if (this.localContextStack == null) {
            this.localContextStack = new ArrayList();
        }
        this.localContextStack.add(localContext);
    }

    private void popLocalContext() {
        ArrayList arrayList = this.localContextStack;
        arrayList.remove(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public ArrayList getLocalContextStack() {
        return this.localContextStack;
    }

    public Namespace getNamespace(String str) {
        if (str.startsWith("/")) {
            str = str.substring(1);
        }
        HashMap hashMap = this.loadedLibs;
        if (hashMap != null) {
            return (Namespace) hashMap.get(str);
        }
        return null;
    }

    public Namespace getMainNamespace() {
        return this.mainNamespace;
    }

    public Namespace getCurrentNamespace() {
        return this.currentNamespace;
    }

    public Namespace getGlobalNamespace() {
        return this.globalNamespace;
    }

    public TemplateHashModel getDataModel() {
        final AnonymousClass3 r0 = new TemplateHashModel() {
            public boolean isEmpty() {
                return false;
            }

            public TemplateModel get(String str) throws TemplateModelException {
                TemplateModel templateModel = Environment.this.rootDataModel.get(str);
                return templateModel == null ? Environment.this.getConfiguration().getSharedVariable(str) : templateModel;
            }
        };
        return this.rootDataModel instanceof TemplateHashModelEx ? new TemplateHashModelEx() {
            public boolean isEmpty() throws TemplateModelException {
                return r0.isEmpty();
            }

            public TemplateModel get(String str) throws TemplateModelException {
                return r0.get(str);
            }

            public TemplateCollectionModel values() throws TemplateModelException {
                return ((TemplateHashModelEx) Environment.this.rootDataModel).values();
            }

            public TemplateCollectionModel keys() throws TemplateModelException {
                return ((TemplateHashModelEx) Environment.this.rootDataModel).keys();
            }

            public int size() throws TemplateModelException {
                return ((TemplateHashModelEx) Environment.this.rootDataModel).size();
            }
        } : r0;
    }

    public TemplateHashModel getGlobalVariables() {
        return new TemplateHashModel() {
            public boolean isEmpty() {
                return false;
            }

            public TemplateModel get(String str) throws TemplateModelException {
                TemplateModel templateModel = Environment.this.globalNamespace.get(str);
                if (templateModel == null) {
                    templateModel = Environment.this.rootDataModel.get(str);
                }
                return templateModel == null ? Environment.this.getConfiguration().getSharedVariable(str) : templateModel;
            }
        };
    }

    private void pushElement(TemplateElement templateElement) {
        this.instructionStack.add(templateElement);
    }

    private void popElement() {
        ArrayList arrayList = this.instructionStack;
        arrayList.remove(arrayList.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void replaceElemetStackTop(TemplateElement templateElement) {
        ArrayList arrayList = this.instructionStack;
        arrayList.set(arrayList.size() - 1, templateElement);
    }

    public TemplateNodeModel getCurrentVisitorNode() {
        return this.currentVisitorNode;
    }

    public void setCurrentVisitorNode(TemplateNodeModel templateNodeModel) {
        this.currentVisitorNode = templateNodeModel;
    }

    /* access modifiers changed from: package-private */
    public TemplateModel getNodeProcessor(TemplateNodeModel templateNodeModel) throws TemplateException {
        String nodeName = templateNodeModel.getNodeName();
        if (nodeName != null) {
            TemplateModel nodeProcessor = getNodeProcessor(nodeName, templateNodeModel.getNodeNamespace(), 0);
            if (nodeProcessor != null) {
                return nodeProcessor;
            }
            String nodeType = templateNodeModel.getNodeType();
            if (nodeType == null) {
                nodeType = "default";
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("@");
            stringBuffer.append(nodeType);
            return getNodeProcessor(stringBuffer.toString(), (String) null, 0);
        }
        throw new _MiscTemplateException(this, "Node name is null.");
    }

    private TemplateModel getNodeProcessor(String str, String str2, int i) throws TemplateException {
        TemplateModel templateModel = null;
        while (i < this.nodeNamespaces.size()) {
            try {
                templateModel = getNodeProcessor((Namespace) this.nodeNamespaces.get(i), str, str2);
                if (templateModel != null) {
                    break;
                }
                i++;
            } catch (ClassCastException unused) {
                throw new _MiscTemplateException(this, "A \"using\" clause should contain a sequence of namespaces or strings that indicate the location of importable macro libraries.");
            }
        }
        if (templateModel != null) {
            this.nodeNamespaceIndex = i + 1;
            this.currentNodeName = str;
            this.currentNodeNS = str2;
        }
        return templateModel;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0067, code lost:
        if ((r2 instanceof freemarker.template.TemplateTransformModel) == false) goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private freemarker.template.TemplateModel getNodeProcessor(freemarker.core.Environment.Namespace r5, java.lang.String r6, java.lang.String r7) throws freemarker.template.TemplateException {
        /*
            r4 = this;
            r0 = 0
            if (r7 != 0) goto L_0x0014
            freemarker.template.TemplateModel r5 = r5.get(r6)
            boolean r6 = r5 instanceof freemarker.core.Macro
            if (r6 != 0) goto L_0x0011
            boolean r6 = r5 instanceof freemarker.template.TemplateTransformModel
            if (r6 != 0) goto L_0x0011
            goto L_0x00a2
        L_0x0011:
            r0 = r5
            goto L_0x00a2
        L_0x0014:
            freemarker.template.Template r1 = r5.getTemplate()
            java.lang.String r2 = r1.getPrefixForNamespace(r7)
            if (r2 != 0) goto L_0x001f
            return r0
        L_0x001f:
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x0046
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r7.<init>()
            r7.append(r2)
            java.lang.String r1 = ":"
            r7.append(r1)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            freemarker.template.TemplateModel r5 = r5.get(r6)
            boolean r6 = r5 instanceof freemarker.core.Macro
            if (r6 != 0) goto L_0x0011
            boolean r6 = r5 instanceof freemarker.template.TemplateTransformModel
            if (r6 != 0) goto L_0x0011
            goto L_0x00a2
        L_0x0046:
            int r2 = r7.length()
            if (r2 != 0) goto L_0x0069
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.lang.String r3 = "N:"
            r2.append(r3)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            freemarker.template.TemplateModel r2 = r5.get(r2)
            boolean r3 = r2 instanceof freemarker.core.Macro
            if (r3 != 0) goto L_0x006a
            boolean r3 = r2 instanceof freemarker.template.TemplateTransformModel
            if (r3 != 0) goto L_0x006a
        L_0x0069:
            r2 = r0
        L_0x006a:
            java.lang.String r1 = r1.getDefaultNS()
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0092
            java.lang.StringBuffer r7 = new java.lang.StringBuffer
            r7.<init>()
            java.lang.String r1 = "D:"
            r7.append(r1)
            r7.append(r6)
            java.lang.String r7 = r7.toString()
            freemarker.template.TemplateModel r2 = r5.get(r7)
            boolean r7 = r2 instanceof freemarker.core.Macro
            if (r7 != 0) goto L_0x0092
            boolean r7 = r2 instanceof freemarker.template.TemplateTransformModel
            if (r7 != 0) goto L_0x0092
            r2 = r0
        L_0x0092:
            if (r2 != 0) goto L_0x00a1
            freemarker.template.TemplateModel r5 = r5.get(r6)
            boolean r6 = r5 instanceof freemarker.core.Macro
            if (r6 != 0) goto L_0x0011
            boolean r6 = r5 instanceof freemarker.template.TemplateTransformModel
            if (r6 != 0) goto L_0x0011
            goto L_0x00a2
        L_0x00a1:
            r0 = r2
        L_0x00a2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.Environment.getNodeProcessor(freemarker.core.Environment$Namespace, java.lang.String, java.lang.String):freemarker.template.TemplateModel");
    }

    public void include(String str, String str2, boolean z) throws IOException, TemplateException {
        include(getTemplateForInclusion(str, str2, z));
    }

    public Template getTemplateForInclusion(String str, String str2, boolean z) throws IOException {
        if (str2 == null) {
            str2 = getTemplate().getEncoding();
        }
        if (str2 == null) {
            str2 = getConfiguration().getEncoding(getLocale());
        }
        return getConfiguration().getTemplate(str, getLocale(), str2, z);
    }

    public void include(Template template) throws TemplateException, IOException {
        Template template2 = getTemplate();
        setParent(template);
        importMacros(template);
        try {
            visit(template.getRootTreeNode());
        } finally {
            setParent(template2);
        }
    }

    public Namespace importLib(String str, String str2) throws IOException, TemplateException {
        return importLib(getTemplateForImporting(str), str2);
    }

    public Template getTemplateForImporting(String str) throws IOException {
        return getTemplateForInclusion(str, (String) null, true);
    }

    public Namespace importLib(Template template, String str) throws IOException, TemplateException {
        if (this.loadedLibs == null) {
            this.loadedLibs = new HashMap();
        }
        String name = template.getName();
        Namespace namespace = (Namespace) this.loadedLibs.get(name);
        if (namespace == null) {
            Namespace namespace2 = new Namespace(template);
            if (str != null) {
                this.currentNamespace.put(str, (Object) namespace2);
                if (this.currentNamespace == this.mainNamespace) {
                    this.globalNamespace.put(str, (Object) namespace2);
                }
            }
            Namespace namespace3 = this.currentNamespace;
            this.currentNamespace = namespace2;
            this.loadedLibs.put(name, namespace2);
            Writer writer = this.out;
            this.out = NullWriter.INSTANCE;
            try {
                include(template);
            } finally {
                this.out = writer;
                this.currentNamespace = namespace3;
            }
        } else if (str != null) {
            setVariable(str, namespace);
        }
        return (Namespace) this.loadedLibs.get(name);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public String renderElementToString(TemplateElement templateElement) throws IOException, TemplateException {
        Writer writer = this.out;
        try {
            StringWriter stringWriter = new StringWriter();
            this.out = stringWriter;
            visit(templateElement);
            String stringWriter2 = stringWriter.toString();
            this.out = writer;
            return stringWriter2;
        } catch (Throwable th) {
            this.out = writer;
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void importMacros(Template template) {
        for (Macro visitMacroDef : template.getMacros().values()) {
            visitMacroDef(visitMacroDef);
        }
    }

    public String getNamespaceForPrefix(String str) {
        return this.currentNamespace.getTemplate().getNamespaceForPrefix(str);
    }

    public String getPrefixForNamespace(String str) {
        return this.currentNamespace.getTemplate().getPrefixForNamespace(str);
    }

    public String getDefaultNS() {
        return this.currentNamespace.getTemplate().getDefaultNS();
    }

    public Object __getitem__(String str) throws TemplateModelException {
        return BeansWrapper.getDefaultInstance().unwrap(getVariable(str));
    }

    public void __setitem__(String str, Object obj) throws TemplateException {
        setGlobalVariable(str, getObjectWrapper().wrap(obj));
    }

    private static final class NumberFormatKey {
        private final Locale locale;
        private final String pattern;

        NumberFormatKey(String str, Locale locale2) {
            this.pattern = str;
            this.locale = locale2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NumberFormatKey)) {
                return false;
            }
            NumberFormatKey numberFormatKey = (NumberFormatKey) obj;
            if (!numberFormatKey.pattern.equals(this.pattern) || !numberFormatKey.locale.equals(this.locale)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.pattern.hashCode() ^ this.locale.hashCode();
        }
    }

    private static final class DateFormatKey {
        private final int dateType;
        private final Locale locale;
        private final String pattern;
        private final TimeZone timeZone;

        DateFormatKey(int i, String str, Locale locale2, TimeZone timeZone2) {
            this.dateType = i;
            this.pattern = str;
            this.locale = locale2;
            this.timeZone = timeZone2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof DateFormatKey)) {
                return false;
            }
            DateFormatKey dateFormatKey = (DateFormatKey) obj;
            if (this.dateType != dateFormatKey.dateType || !dateFormatKey.pattern.equals(this.pattern) || !dateFormatKey.locale.equals(this.locale) || !dateFormatKey.timeZone.equals(this.timeZone)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((this.dateType ^ this.pattern.hashCode()) ^ this.locale.hashCode()) ^ this.timeZone.hashCode();
        }
    }

    public class Namespace extends SimpleHash {
        private Template template;

        Namespace() {
            this.template = Environment.this.getTemplate();
        }

        Namespace(Template template2) {
            this.template = template2;
        }

        public Template getTemplate() {
            Template template2 = this.template;
            return template2 == null ? Environment.this.getTemplate() : template2;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getFastInvalidReferenceExceptions() {
        return this.fastInvalidReferenceExceptions;
    }

    /* access modifiers changed from: package-private */
    public boolean setFastInvalidReferenceExceptions(boolean z) {
        boolean z2 = this.fastInvalidReferenceExceptions;
        this.fastInvalidReferenceExceptions = z;
        return z2;
    }
}
