package freemarker.core;

import freemarker.template.Template;

public class TokenMgrError extends Error {
    static final int INVALID_LEXICAL_STATE = 2;
    static final int LEXICAL_ERROR = 0;
    static final int LOOP_DETECTED = 3;
    static final int STATIC_LEXER_ERROR = 1;
    private Integer columnNumber;
    private String detail;
    int errorCode;
    private Integer lineNumber;

    protected static final String addEscapes(String str) {
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

    protected static String LexicalError(boolean z, int i, int i2, int i3, String str, char c) {
        String str2;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Lexical error: encountered ");
        if (z) {
            str2 = "<EOF> ";
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("\"");
            stringBuffer2.append(addEscapes(String.valueOf(c)));
            stringBuffer2.append("\"");
            stringBuffer2.append(" (");
            stringBuffer2.append(c);
            stringBuffer2.append("), ");
            str2 = stringBuffer2.toString();
        }
        stringBuffer.append(str2);
        stringBuffer.append("after \"");
        stringBuffer.append(addEscapes(str));
        stringBuffer.append("\".");
        return stringBuffer.toString();
    }

    public String getMessage() {
        return super.getMessage();
    }

    public TokenMgrError() {
    }

    public TokenMgrError(String str, int i) {
        super(str);
        this.detail = str;
        this.errorCode = i;
    }

    public TokenMgrError(String str, int i, int i2, int i3) {
        super(str);
        this.detail = str;
        this.errorCode = i;
        this.lineNumber = new Integer(i2);
        this.columnNumber = new Integer(i3);
    }

    public TokenMgrError(boolean z, int i, int i2, int i3, String str, char c, int i4) {
        this(LexicalError(z, i, i2, i3, str, c), i4);
        this.lineNumber = new Integer(i2);
        this.columnNumber = new Integer(i3);
    }

    public Integer getLineNumber() {
        return this.lineNumber;
    }

    public Integer getColumnNumber() {
        return this.columnNumber;
    }

    public String getDetail() {
        return this.detail;
    }

    public ParseException toParseException(Template template) {
        String detail2 = getDetail();
        int i = 0;
        int intValue = getLineNumber() != null ? getLineNumber().intValue() : 0;
        if (getColumnNumber() != null) {
            i = getColumnNumber().intValue();
        }
        return new ParseException(detail2, template, intValue, i);
    }
}
