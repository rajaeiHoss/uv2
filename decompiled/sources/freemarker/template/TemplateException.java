package freemarker.template;

import freemarker.core.Environment;
import freemarker.core.TemplateElement;
import freemarker.core._CoreAPI;
import freemarker.core._ErrorDescriptionBuilder;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TemplateException extends Exception {
    private static final boolean BEFORE_1_4 = before14();
    private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final String THE_FAILING_INSTRUCTION = "The failing instruction";
    static /* synthetic */ Class class$java$lang$Exception;
    private final Throwable causeException;
    private String description;
    private transient _ErrorDescriptionBuilder descriptionBuilder;
    private final transient Environment env;
    private transient TemplateElement[] ftlInstructionStackSnapshot;
    private transient Object lock;
    private transient String message;
    private transient ThreadLocal messageWasAlreadyPrintedForThisTrace;
    private transient String messageWithoutStackTop;
    private String renderedFtlInstructionStackSnapshot;
    private String renderedFtlInstructionStackSnapshotTop;

    private interface StackTraceWriter {
        void print(Object obj);

        void printStandardStackTrace(Throwable th);

        void println();

        void println(Object obj);
    }

    private static boolean before14() {
        Class cls = class$java$lang$Exception;
        if (cls == null) {
            cls = class$("java.lang.Exception");
            class$java$lang$Exception = cls;
        }
        try {
            cls.getMethod("getCause", new Class[0]);
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public TemplateException(Environment environment) {
        this((String) null, (Exception) null, environment);
    }

    public TemplateException(String str, Environment environment) {
        this(str, (Exception) null, environment);
    }

    public TemplateException(Exception exc, Environment environment) {
        this((String) null, exc, environment);
    }

    public TemplateException(Throwable th, Environment environment) {
        this((String) null, th, environment);
    }

    public TemplateException(String str, Exception exc, Environment environment) {
        this(str, (Throwable) exc, environment, (_ErrorDescriptionBuilder) null);
    }

    public TemplateException(String str, Throwable th, Environment environment) {
        this(str, th, environment, (_ErrorDescriptionBuilder) null);
    }

    protected TemplateException(Throwable th, Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder, boolean z) {
        this((String) null, th, environment, _errordescriptionbuilder);
    }

    private TemplateException(String str, Throwable th, Environment environment, _ErrorDescriptionBuilder _errordescriptionbuilder) {
        this.lock = new Object();
        environment = environment == null ? Environment.getCurrentEnvironment() : environment;
        this.env = environment;
        this.causeException = th;
        this.descriptionBuilder = _errordescriptionbuilder;
        this.description = str;
        if (environment != null) {
            this.ftlInstructionStackSnapshot = _CoreAPI.getInstructionStackSnapshot(environment);
        }
    }

    private void renderMessages() {
        String description2 = getDescription();
        if (description2 != null && description2.length() != 0) {
            this.messageWithoutStackTop = description2;
        } else if (getCause() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("No error description was specified for this error; low-level message: ");
            stringBuffer.append(getCause().getClass().getName());
            stringBuffer.append(": ");
            stringBuffer.append(getCause().getMessage());
            this.messageWithoutStackTop = stringBuffer.toString();
        } else {
            this.messageWithoutStackTop = "[No error description was available.]";
        }
        String fTLInstructionStackTop = getFTLInstructionStackTop();
        if (fTLInstructionStackTop != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(this.messageWithoutStackTop);
            stringBuffer2.append("\n\n");
            stringBuffer2.append(THE_FAILING_INSTRUCTION);
            stringBuffer2.append(fTLInstructionStackTop);
            String stringBuffer3 = stringBuffer2.toString();
            this.message = stringBuffer3;
            this.messageWithoutStackTop = stringBuffer3.substring(0, this.messageWithoutStackTop.length());
            return;
        }
        this.message = this.messageWithoutStackTop;
    }

    public Exception getCauseException() {
        Throwable th = this.causeException;
        if (th instanceof Exception) {
            return (Exception) th;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Wrapped to Exception: ");
        stringBuffer.append(this.causeException);
        return new Exception(stringBuffer.toString());
    }

    public Throwable getCause() {
        return this.causeException;
    }

    public String getFTLInstructionStack() {
        synchronized (this.lock) {
            if (this.ftlInstructionStackSnapshot == null) {
                if (this.renderedFtlInstructionStackSnapshot == null) {
                    return null;
                }
            }
            if (this.renderedFtlInstructionStackSnapshot == null) {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                _CoreAPI.outputInstructionStack(this.ftlInstructionStackSnapshot, printWriter);
                printWriter.close();
                if (this.renderedFtlInstructionStackSnapshot == null) {
                    this.renderedFtlInstructionStackSnapshot = stringWriter.toString();
                    deleteFTLInstructionStackSnapshotIfNotNeeded();
                }
            }
            String str = this.renderedFtlInstructionStackSnapshot;
            return str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getFTLInstructionStackTop() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            freemarker.core.TemplateElement[] r1 = r7.ftlInstructionStackSnapshot     // Catch:{ all -> 0x0068 }
            r2 = 0
            if (r1 != 0) goto L_0x000f
            java.lang.String r3 = r7.renderedFtlInstructionStackSnapshotTop     // Catch:{ all -> 0x0068 }
            if (r3 == 0) goto L_0x000d
            goto L_0x000f
        L_0x000d:
            monitor-exit(r0)     // Catch:{ all -> 0x0068 }
            return r2
        L_0x000f:
            java.lang.String r3 = r7.renderedFtlInstructionStackSnapshotTop     // Catch:{ all -> 0x0068 }
            if (r3 != 0) goto L_0x005c
            int r1 = r1.length     // Catch:{ all -> 0x0068 }
            if (r1 != 0) goto L_0x0019
            java.lang.String r1 = ""
            goto L_0x0053
        L_0x0019:
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ all -> 0x0068 }
            r3.<init>()     // Catch:{ all -> 0x0068 }
            r4 = 1
            if (r1 <= r4) goto L_0x0039
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ all -> 0x0068 }
            r5.<init>()     // Catch:{ all -> 0x0068 }
            java.lang.String r6 = " (print stack trace for "
            r5.append(r6)     // Catch:{ all -> 0x0068 }
            int r1 = r1 - r4
            r5.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = " more)"
            r5.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r5.toString()     // Catch:{ all -> 0x0068 }
            goto L_0x003b
        L_0x0039:
            java.lang.String r1 = ""
        L_0x003b:
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = ":\n==> "
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            freemarker.core.TemplateElement[] r1 = r7.ftlInstructionStackSnapshot     // Catch:{ all -> 0x0068 }
            r4 = 0
            r1 = r1[r4]     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = freemarker.core._CoreAPI.instructionStackItemToString(r1)     // Catch:{ all -> 0x0068 }
            r3.append(r1)     // Catch:{ all -> 0x0068 }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x0068 }
        L_0x0053:
            java.lang.String r3 = r7.renderedFtlInstructionStackSnapshotTop     // Catch:{ all -> 0x0068 }
            if (r3 != 0) goto L_0x005c
            r7.renderedFtlInstructionStackSnapshotTop = r1     // Catch:{ all -> 0x0068 }
            r7.deleteFTLInstructionStackSnapshotIfNotNeeded()     // Catch:{ all -> 0x0068 }
        L_0x005c:
            java.lang.String r1 = r7.renderedFtlInstructionStackSnapshotTop     // Catch:{ all -> 0x0068 }
            int r1 = r1.length()     // Catch:{ all -> 0x0068 }
            if (r1 == 0) goto L_0x0066
            java.lang.String r2 = r7.renderedFtlInstructionStackSnapshotTop     // Catch:{ all -> 0x0068 }
        L_0x0066:
            monitor-exit(r0)     // Catch:{ all -> 0x0068 }
            return r2
        L_0x0068:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0068 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.TemplateException.getFTLInstructionStackTop():java.lang.String");
    }

    private void deleteFTLInstructionStackSnapshotIfNotNeeded() {
        if (this.renderedFtlInstructionStackSnapshot != null && this.renderedFtlInstructionStackSnapshotTop != null) {
            this.ftlInstructionStackSnapshot = null;
        }
    }

    private String getDescription() {
        String str;
        _ErrorDescriptionBuilder _errordescriptionbuilder;
        synchronized (this.lock) {
            if (this.description == null && (_errordescriptionbuilder = this.descriptionBuilder) != null) {
                this.description = _errordescriptionbuilder.toString(getFailingInstruction());
                this.descriptionBuilder = null;
            }
            str = this.description;
        }
        return str;
    }

    private TemplateElement getFailingInstruction() {
        TemplateElement[] templateElementArr = this.ftlInstructionStackSnapshot;
        if (templateElementArr == null || templateElementArr.length <= 0) {
            return null;
        }
        return templateElementArr[0];
    }

    public Environment getEnvironment() {
        return this.env;
    }

    public void printStackTrace(PrintStream printStream) {
        printStackTrace(printStream, true, true, true);
    }

    public void printStackTrace(PrintWriter printWriter) {
        printStackTrace(printWriter, true, true, true);
    }

    public void printStackTrace(PrintWriter printWriter, boolean z, boolean z2, boolean z3) {
        synchronized (printWriter) {
            printStackTrace((StackTraceWriter) new PrintWriterStackTraceWriter(printWriter), z, z2, z3);
        }
    }

    public void printStackTrace(PrintStream printStream, boolean z, boolean z2, boolean z3) {
        synchronized (printStream) {
            printStackTrace((StackTraceWriter) new PrintStreamStackTraceWriter(printStream), z, z2, z3);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:(3:2|3|4)|(2:8|(1:10)(1:11))|(6:(6:14|41|22|23|24|25)(1:34)|35|(1:39)|40|41|(3:43|(1:45)|(1:47)))|48|49) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:48:0x00bd */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void printStackTrace(freemarker.template.TemplateException.StackTraceWriter r3, boolean r4, boolean r5, boolean r6) {
        /*
            r2 = this;
            monitor-enter(r3)
            if (r4 == 0) goto L_0x000c
            java.lang.String r4 = "FreeMarker template error:"
            r3.println(r4)     // Catch:{ all -> 0x0009 }
            goto L_0x000c
        L_0x0009:
            r4 = move-exception
            goto L_0x00bf
        L_0x000c:
            if (r5 == 0) goto L_0x002e
            java.lang.String r4 = r2.getFTLInstructionStack()     // Catch:{ all -> 0x0009 }
            if (r4 == 0) goto L_0x002c
            java.lang.String r0 = r2.getMessageWithoutStackTop()     // Catch:{ all -> 0x0009 }
            r3.println(r0)     // Catch:{ all -> 0x0009 }
            r3.println()     // Catch:{ all -> 0x0009 }
            java.lang.String r0 = "The failing instruction"
            r3.print(r0)     // Catch:{ all -> 0x0009 }
            java.lang.String r0 = " (FTL stack trace):"
            r3.println(r0)     // Catch:{ all -> 0x0009 }
            r3.print(r4)     // Catch:{ all -> 0x0009 }
            goto L_0x002e
        L_0x002c:
            r5 = 0
            r6 = 1
        L_0x002e:
            if (r6 == 0) goto L_0x00bd
            if (r5 == 0) goto L_0x006c
            r3.println()     // Catch:{ all -> 0x0009 }
            java.lang.String r4 = "Java stack trace (for programmers):"
            r3.println(r4)     // Catch:{ all -> 0x0009 }
            java.lang.String r4 = "----------"
            r3.println(r4)     // Catch:{ all -> 0x0009 }
            java.lang.Object r4 = r2.lock     // Catch:{ all -> 0x0009 }
            monitor-enter(r4)     // Catch:{ all -> 0x0009 }
            java.lang.ThreadLocal r5 = r2.messageWasAlreadyPrintedForThisTrace     // Catch:{ all -> 0x0069 }
            if (r5 != 0) goto L_0x004d
            java.lang.ThreadLocal r5 = new java.lang.ThreadLocal     // Catch:{ all -> 0x0069 }
            r5.<init>()     // Catch:{ all -> 0x0069 }
            r2.messageWasAlreadyPrintedForThisTrace = r5     // Catch:{ all -> 0x0069 }
        L_0x004d:
            java.lang.ThreadLocal r5 = r2.messageWasAlreadyPrintedForThisTrace     // Catch:{ all -> 0x0069 }
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0069 }
            r5.set(r6)     // Catch:{ all -> 0x0069 }
            monitor-exit(r4)     // Catch:{ all -> 0x0069 }
            r3.printStandardStackTrace(r2)     // Catch:{ all -> 0x0060 }
            java.lang.ThreadLocal r4 = r2.messageWasAlreadyPrintedForThisTrace     // Catch:{ all -> 0x0009 }
            java.lang.Boolean r5 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0009 }
            r4.set(r5)     // Catch:{ all -> 0x0009 }
            goto L_0x006f
        L_0x0060:
            r4 = move-exception
            java.lang.ThreadLocal r5 = r2.messageWasAlreadyPrintedForThisTrace     // Catch:{ all -> 0x0009 }
            java.lang.Boolean r6 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0009 }
            r5.set(r6)     // Catch:{ all -> 0x0009 }
            throw r4     // Catch:{ all -> 0x0009 }
        L_0x0069:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0069 }
            throw r5     // Catch:{ all -> 0x0009 }
        L_0x006c:
            r3.printStandardStackTrace(r2)     // Catch:{ all -> 0x0009 }
        L_0x006f:
            boolean r4 = BEFORE_1_4     // Catch:{ all -> 0x0009 }
            if (r4 == 0) goto L_0x0081
            java.lang.Throwable r5 = r2.causeException     // Catch:{ all -> 0x0009 }
            if (r5 == 0) goto L_0x0081
            java.lang.String r5 = "Underlying cause: "
            r3.println(r5)     // Catch:{ all -> 0x0009 }
            java.lang.Throwable r5 = r2.causeException     // Catch:{ all -> 0x0009 }
            r3.printStandardStackTrace(r5)     // Catch:{ all -> 0x0009 }
        L_0x0081:
            java.lang.Throwable r5 = r2.causeException     // Catch:{ all -> 0x00bd }
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = "getRootCause"
            java.lang.Class[] r0 = EMPTY_CLASS_ARRAY     // Catch:{ all -> 0x00bd }
            java.lang.reflect.Method r5 = r5.getMethod(r6, r0)     // Catch:{ all -> 0x00bd }
            java.lang.Throwable r6 = r2.causeException     // Catch:{ all -> 0x00bd }
            java.lang.Object[] r1 = EMPTY_OBJECT_ARRAY     // Catch:{ all -> 0x00bd }
            java.lang.Object r5 = r5.invoke(r6, r1)     // Catch:{ all -> 0x00bd }
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x00bd }
            if (r5 == 0) goto L_0x00bd
            r6 = 0
            if (r4 != 0) goto L_0x00b3
            java.lang.Throwable r4 = r2.causeException     // Catch:{ all -> 0x00bd }
            java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x00bd }
            java.lang.String r6 = "getCause"
            java.lang.reflect.Method r4 = r4.getMethod(r6, r0)     // Catch:{ all -> 0x00bd }
            java.lang.Throwable r6 = r2.causeException     // Catch:{ all -> 0x00bd }
            java.lang.Object r4 = r4.invoke(r6, r1)     // Catch:{ all -> 0x00bd }
            r6 = r4
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x00bd }
        L_0x00b3:
            if (r6 != 0) goto L_0x00bd
            java.lang.String r4 = "ServletException root cause: "
            r3.println(r4)     // Catch:{ all -> 0x00bd }
            r3.printStandardStackTrace(r5)     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            monitor-exit(r3)     // Catch:{ all -> 0x0009 }
            return
        L_0x00bf:
            monitor-exit(r3)     // Catch:{ all -> 0x0009 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.template.TemplateException.printStackTrace(freemarker.template.TemplateException$StackTraceWriter, boolean, boolean, boolean):void");
    }

    public void printStandardStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
    }

    public void printStandardStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
    }

    public String getMessage() {
        String str;
        ThreadLocal threadLocal = this.messageWasAlreadyPrintedForThisTrace;
        if (threadLocal != null && threadLocal.get() == Boolean.TRUE) {
            return "[... Exception message was already printed; see it above ...]";
        }
        synchronized (this.lock) {
            if (this.message == null) {
                renderMessages();
            }
            str = this.message;
        }
        return str;
    }

    public String getMessageWithoutStackTop() {
        String str;
        synchronized (this.lock) {
            if (this.messageWithoutStackTop == null) {
                renderMessages();
            }
            str = this.messageWithoutStackTop;
        }
        return str;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        getFTLInstructionStack();
        getFTLInstructionStackTop();
        getDescription();
        objectOutputStream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.lock = new Object();
        objectInputStream.defaultReadObject();
    }

    private static class PrintStreamStackTraceWriter implements StackTraceWriter {
        private final PrintStream out;

        PrintStreamStackTraceWriter(PrintStream printStream) {
            this.out = printStream;
        }

        public void print(Object obj) {
            this.out.print(obj);
        }

        public void println(Object obj) {
            this.out.println(obj);
        }

        public void println() {
            this.out.println();
        }

        public void printStandardStackTrace(Throwable th) {
            if (th instanceof TemplateException) {
                ((TemplateException) th).printStandardStackTrace(this.out);
            } else {
                th.printStackTrace(this.out);
            }
        }
    }

    private static class PrintWriterStackTraceWriter implements StackTraceWriter {
        private final PrintWriter out;

        PrintWriterStackTraceWriter(PrintWriter printWriter) {
            this.out = printWriter;
        }

        public void print(Object obj) {
            this.out.print(obj);
        }

        public void println(Object obj) {
            this.out.println(obj);
        }

        public void println() {
            this.out.println();
        }

        public void printStandardStackTrace(Throwable th) {
            if (th instanceof TemplateException) {
                ((TemplateException) th).printStandardStackTrace(this.out);
            } else {
                th.printStackTrace(this.out);
            }
        }
    }
}
