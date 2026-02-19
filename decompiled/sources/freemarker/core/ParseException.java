package freemarker.core;

import freemarker.template.Template;
import freemarker.template.utility.SecurityUtilities;
import freemarker.template.utility.StringUtil;
import java.io.IOException;

public class ParseException extends IOException implements FMParserConstants {
    static /* synthetic */ Class class$freemarker$core$ParseException;
    private static volatile Boolean jbossToolsMode;
    private final Throwable cause;
    public int columnNumber;
    public Token currentToken;
    private String description;
    protected String eol;
    public int[][] expectedTokenSequences;
    public int lineNumber;
    private String message;
    private boolean messageAndDescriptionRendered;
    protected boolean specialConstructor;
    private String templateName;
    public String[] tokenImage;

    public ParseException(Token token, int[][] iArr, String[] strArr) {
        super("");
        this.eol = SecurityUtilities.getSystemProperty("line.separator", "\n");
        this.cause = null;
        this.currentToken = token;
        this.specialConstructor = true;
        this.expectedTokenSequences = iArr;
        this.tokenImage = strArr;
        this.lineNumber = token.next.beginLine;
        this.columnNumber = this.currentToken.next.beginColumn;
    }

    protected ParseException() {
        this.eol = SecurityUtilities.getSystemProperty("line.separator", "\n");
        this.cause = null;
    }

    public ParseException(String str, int i, int i2) {
        this(str, (Template) null, i, i2, (Throwable) null);
    }

    public ParseException(String str, Template template, int i, int i2) {
        this(str, template, i, i2, (Throwable) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ParseException(String str, Template template, int i, int i2, Throwable th) {
        this(str, template == null ? null : template.getName(), i, i2, th);
    }

    public ParseException(String str, Template template, Token token) {
        this(str, template, token, (Throwable) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ParseException(String str, Template template, Token token, Throwable th) {
        this(str, template == null ? null : template.getName(), token.beginLine, token.beginColumn, th);
    }

    public ParseException(String str, TemplateObject templateObject) {
        this(str, templateObject, (Throwable) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ParseException(String str, TemplateObject templateObject, Throwable th) {
        this(str, templateObject.getTemplate() == null ? null : templateObject.getTemplate().getName(), templateObject.beginLine, templateObject.beginColumn, th);
    }

    private ParseException(String str, String str2, int i, int i2, Throwable th) {
        super(str);
        this.eol = SecurityUtilities.getSystemProperty("line.separator", "\n");
        this.cause = th;
        this.description = str;
        this.templateName = str2;
        this.lineNumber = i;
        this.columnNumber = i2;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
        synchronized (this) {
            this.messageAndDescriptionRendered = false;
            this.message = null;
        }
    }

    public Throwable getCause() {
        return this.cause;
    }

    public String getMessage() {
        String str;
        synchronized (this) {
            if (this.messageAndDescriptionRendered) {
                String str2 = this.message;
                return str2;
            }
            renderMessageAndDescription();
            synchronized (this) {
                str = this.message;
            }
            return str;
        }
    }

    private String getDescription() {
        String str;
        synchronized (this) {
            if (this.messageAndDescriptionRendered) {
                String str2 = this.description;
                return str2;
            }
            renderMessageAndDescription();
            synchronized (this) {
                str = this.description;
            }
            return str;
        }
    }

    public String getEditorMessage() {
        return getDescription();
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    private void renderMessageAndDescription() {
        String str;
        String orRenderDescription = getOrRenderDescription();
        if (!isInJBossToolsMode()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Parsing error ");
            stringBuffer.append(MessageUtil.formatLocationForSimpleParsingError(this.templateName, this.lineNumber, this.columnNumber));
            stringBuffer.append(":\n");
            str = stringBuffer.toString();
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("[col. ");
            stringBuffer2.append(this.columnNumber);
            stringBuffer2.append("] ");
            str = stringBuffer2.toString();
        }
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append(str);
        stringBuffer3.append(orRenderDescription);
        String stringBuffer4 = stringBuffer3.toString();
        String substring = stringBuffer4.substring(str.length());
        synchronized (this) {
            this.message = stringBuffer4;
            this.description = substring;
            this.messageAndDescriptionRendered = true;
        }
    }

    private boolean isInJBossToolsMode() {
        if (jbossToolsMode == null) {
            try {
                Class cls = class$freemarker$core$ParseException;
                if (cls == null) {
                    cls = class$("freemarker.core.ParseException");
                    class$freemarker$core$ParseException = cls;
                }
                jbossToolsMode = cls.getClassLoader().toString().indexOf("[org.jboss.ide.eclipse.freemarker:") != -1 ? Boolean.TRUE : Boolean.FALSE;
            } catch (Throwable unused) {
                jbossToolsMode = Boolean.FALSE;
            }
        }
        return jbossToolsMode.booleanValue();
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        if (r0 != null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        r0 = new java.lang.StringBuffer();
        r2 = 0;
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r2 >= r7.expectedTokenSequences.length) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        if (r2 == 0) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r0.append(r7.eol);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r0.append("    ");
        r4 = r7.expectedTokenSequences;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        if (r3 >= r4[r2].length) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r3 = r4[r2].length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        if (r4 >= r7.expectedTokenSequences[r2].length) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        if (r4 == 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
        r0.append(' ');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0044, code lost:
        r0.append(r7.tokenImage[r7.expectedTokenSequences[r2][r4]]);
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        r2 = "Encountered \"";
        r4 = r7.currentToken.next;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        if (r5 >= r3) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0060, code lost:
        if (r5 == 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0062, code lost:
        r6 = new java.lang.StringBuffer();
        r6.append(r2);
        r6.append(" ");
        r2 = r6.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0075, code lost:
        if (r4.kind != 0) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0077, code lost:
        r3 = new java.lang.StringBuffer();
        r3.append(r2);
        r3.append(r7.tokenImage[0]);
        r2 = r3.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x008b, code lost:
        r6 = new java.lang.StringBuffer();
        r6.append(r2);
        r6.append(add_escapes(r4.image));
        r2 = r6.toString();
        r4 = r4.next;
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a5, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append(r2);
        r1.append("\", but ");
        r1 = r1.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ba, code lost:
        if (r7.expectedTokenSequences.length != 1) goto L_0x00d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00bc, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append(r1);
        r2.append("was expecting:");
        r2.append(r7.eol);
        r1 = r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d3, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append(r1);
        r2.append("was expecting one of:");
        r2.append(r7.eol);
        r1 = r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00e9, code lost:
        r2 = new java.lang.StringBuffer();
        r2.append(r1);
        r2.append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f9, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return r2.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        if (r7.currentToken == null) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000c, code lost:
        r0 = getCustomTokenErrorDescription();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getOrRenderDescription() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = r7.description     // Catch:{ all -> 0x00fb }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)     // Catch:{ all -> 0x00fb }
            return r0
        L_0x0007:
            monitor-exit(r7)     // Catch:{ all -> 0x00fb }
            freemarker.core.Token r0 = r7.currentToken
            if (r0 == 0) goto L_0x00f9
            java.lang.String r0 = r7.getCustomTokenErrorDescription()
            if (r0 != 0) goto L_0x00fa
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            r2 = 0
            r3 = 0
        L_0x001a:
            int[][] r4 = r7.expectedTokenSequences
            int r4 = r4.length
            if (r2 >= r4) goto L_0x0057
            if (r2 == 0) goto L_0x0026
            java.lang.String r4 = r7.eol
            r0.append(r4)
        L_0x0026:
            java.lang.String r4 = "    "
            r0.append(r4)
            int[][] r4 = r7.expectedTokenSequences
            r5 = r4[r2]
            int r5 = r5.length
            if (r3 >= r5) goto L_0x0035
            r3 = r4[r2]
            int r3 = r3.length
        L_0x0035:
            r4 = 0
        L_0x0036:
            int[][] r5 = r7.expectedTokenSequences
            r5 = r5[r2]
            int r5 = r5.length
            if (r4 >= r5) goto L_0x0054
            if (r4 == 0) goto L_0x0044
            r5 = 32
            r0.append(r5)
        L_0x0044:
            java.lang.String[] r5 = r7.tokenImage
            int[][] r6 = r7.expectedTokenSequences
            r6 = r6[r2]
            r6 = r6[r4]
            r5 = r5[r6]
            r0.append(r5)
            int r4 = r4 + 1
            goto L_0x0036
        L_0x0054:
            int r2 = r2 + 1
            goto L_0x001a
        L_0x0057:
            java.lang.String r2 = "Encountered \""
            freemarker.core.Token r4 = r7.currentToken
            freemarker.core.Token r4 = r4.next
            r5 = 0
        L_0x005e:
            if (r5 >= r3) goto L_0x00a5
            if (r5 == 0) goto L_0x0073
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            r6.append(r2)
            java.lang.String r2 = " "
            r6.append(r2)
            java.lang.String r2 = r6.toString()
        L_0x0073:
            int r6 = r4.kind
            if (r6 != 0) goto L_0x008b
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            r3.append(r2)
            java.lang.String[] r2 = r7.tokenImage
            r1 = r2[r1]
            r3.append(r1)
            java.lang.String r2 = r3.toString()
            goto L_0x00a5
        L_0x008b:
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            r6.append(r2)
            java.lang.String r2 = r4.image
            java.lang.String r2 = r7.add_escapes(r2)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            freemarker.core.Token r4 = r4.next
            int r5 = r5 + 1
            goto L_0x005e
        L_0x00a5:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            r1.append(r2)
            java.lang.String r2 = "\", but "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            int[][] r2 = r7.expectedTokenSequences
            int r2 = r2.length
            r3 = 1
            if (r2 != r3) goto L_0x00d3
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = "was expecting:"
            r2.append(r1)
            java.lang.String r1 = r7.eol
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            goto L_0x00e9
        L_0x00d3:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = "was expecting one of:"
            r2.append(r1)
            java.lang.String r1 = r7.eol
            r2.append(r1)
            java.lang.String r1 = r2.toString()
        L_0x00e9:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            goto L_0x00fa
        L_0x00f9:
            r0 = 0
        L_0x00fa:
            return r0
        L_0x00fb:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x00fb }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.core.ParseException.getOrRenderDescription():java.lang.String");
    }

    private String getCustomTokenErrorDescription() {
        String str;
        Token token = this.currentToken.next;
        int i = token.kind;
        if (i == 0) {
            int i2 = 0;
            while (true) {
                int[][] iArr = this.expectedTokenSequences;
                if (i2 >= iArr.length) {
                    return "Unexpected end of file reached.";
                }
                int i3 = iArr[i2][0];
                if (i3 == 31) {
                    str = "#if";
                } else if (i3 == 32) {
                    str = "#list";
                } else if (i3 == 60) {
                    str = "#escape";
                } else if (i3 == 62) {
                    str = "#noescape";
                } else if (i3 == 64) {
                    str = "@...";
                } else if (i3 == 112) {
                    str = "[";
                } else if (i3 == 114) {
                    str = "(";
                } else if (i3 != 116) {
                    switch (i3) {
                        case 34:
                            str = "#attempt";
                            break;
                        case 35:
                            str = "#foreach";
                            break;
                        case 36:
                            str = "#local";
                            break;
                        case 37:
                            str = "#global";
                            break;
                        case 38:
                            str = "#assign";
                            break;
                        case 39:
                        case 40:
                            str = "#macro or #function";
                            break;
                        case 41:
                            str = "#compress";
                            break;
                        case 42:
                            str = "#transform";
                            break;
                        case 43:
                            str = "#switch";
                            break;
                        default:
                            str = null;
                            break;
                    }
                } else {
                    str = "{";
                }
                if (str != null) {
                    if (!str.startsWith("#") && !str.startsWith("@")) {
                        str = StringUtil.jQuote(str);
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unclosed ");
                    stringBuffer.append(str);
                    stringBuffer.append(" when the end of the file was reached.");
                    return stringBuffer.toString();
                }
                i2++;
            }
        } else if (i != 31 && i != 9 && i != 44) {
            return null;
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Unexpected directive, ");
            stringBuffer2.append(StringUtil.jQuote((Object) token));
            stringBuffer2.append(". Check whether you have a valid #if-#elseif-#else structure.");
            return stringBuffer2.toString();
        }
    }

    /* access modifiers changed from: protected */
    public String add_escapes(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt != 0) {
                if (charAt == '\"') {
                    stringBuffer.append("\\\"");
                } else if (charAt == '\'') {
                    stringBuffer.append("\\'");
                } else if (charAt == '\\') {
                    stringBuffer.append("\\\\");
                } else if (charAt == 12) {
                    stringBuffer.append("\\f");
                } else if (charAt != 13) {
                    switch (charAt) {
                        case 8:
                            stringBuffer.append("\\b");
                            break;
                        case 9:
                            stringBuffer.append("\\t");
                            break;
                        case 10:
                            stringBuffer.append("\\n");
                            break;
                        default:
                            char charAt2 = str.charAt(i);
                            if (charAt2 >= ' ' && charAt2 <= '~') {
                                stringBuffer.append(charAt2);
                                break;
                            } else {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append("0000");
                                stringBuffer2.append(Integer.toString(charAt2, 16));
                                String stringBuffer3 = stringBuffer2.toString();
                                StringBuffer stringBuffer4 = new StringBuffer();
                                stringBuffer4.append("\\u");
                                stringBuffer4.append(stringBuffer3.substring(stringBuffer3.length() - 4, stringBuffer3.length()));
                                stringBuffer.append(stringBuffer4.toString());
                                break;
                            }
                    }
                } else {
                    stringBuffer.append("\\r");
                }
            }
        }
        return stringBuffer.toString();
    }
}
