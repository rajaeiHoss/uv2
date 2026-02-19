package com.novell.sasl.client;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

class DirectiveList {
    private static final int STATE_LOOKING_FOR_COMMA = 6;
    private static final int STATE_LOOKING_FOR_DIRECTIVE = 2;
    private static final int STATE_LOOKING_FOR_EQUALS = 4;
    private static final int STATE_LOOKING_FOR_FIRST_DIRECTIVE = 1;
    private static final int STATE_LOOKING_FOR_VALUE = 5;
    private static final int STATE_NO_UTF8_SUPPORT = 9;
    private static final int STATE_SCANNING_NAME = 3;
    private static final int STATE_SCANNING_QUOTED_STRING_VALUE = 7;
    private static final int STATE_SCANNING_TOKEN_VALUE = 8;
    private String m_curName;
    private int m_curPos = 0;
    private ArrayList m_directiveList = new ArrayList(10);
    private String m_directives;
    private int m_errorPos = -1;
    private int m_scanStart = 0;
    private int m_state = 1;

    /* access modifiers changed from: package-private */
    public boolean isValidTokenChar(char c) {
        if (c >= 0 && c <= ' ') {
            return false;
        }
        if (c < ':' || c > '@') {
            return ((c >= '[' && c <= ']') || ',' == c || '%' == c || '(' == c || ')' == c || '{' == c || '}' == c || 127 == c) ? false : true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isWhiteSpace(char c) {
        return 9 == c || 10 == c || 13 == c || ' ' == c;
    }

    DirectiveList(byte[] bArr) {
        try {
            this.m_directives = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            this.m_state = 9;
        }
    }

    /* access modifiers changed from: package-private */
    public void parseDirectives() throws SaslException {
        if (this.m_state != 9) {
            String str = "<no name>";
            char c = 0;
            boolean z = false;
            while (this.m_curPos < this.m_directives.length()) {
                char charAt = this.m_directives.charAt(this.m_curPos);
                switch (this.m_state) {
                    case 1:
                    case 2:
                        if (isWhiteSpace(charAt)) {
                            continue;
                        } else if (isValidTokenChar(charAt)) {
                            this.m_scanStart = this.m_curPos;
                            this.m_state = 3;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Invalid name character");
                        }
                    case 3:
                        if (isValidTokenChar(charAt)) {
                            continue;
                        } else if (isWhiteSpace(charAt)) {
                            str = this.m_directives.substring(this.m_scanStart, this.m_curPos);
                            this.m_state = 4;
                            break;
                        } else if ('=' == charAt) {
                            str = this.m_directives.substring(this.m_scanStart, this.m_curPos);
                            this.m_state = 5;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Invalid name character");
                        }
                    case 4:
                        if (isWhiteSpace(charAt)) {
                            continue;
                        } else if ('=' == charAt) {
                            this.m_state = 5;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Expected equals sign '='.");
                        }
                    case 5:
                        if (isWhiteSpace(charAt)) {
                            continue;
                        } else if ('\"' == charAt) {
                            this.m_scanStart = this.m_curPos + 1;
                            this.m_state = 7;
                            break;
                        } else if (isValidTokenChar(charAt)) {
                            this.m_scanStart = this.m_curPos;
                            this.m_state = 8;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Unexpected character");
                        }
                    case 6:
                        if (isWhiteSpace(charAt)) {
                            continue;
                        } else if (charAt == ',') {
                            this.m_state = 2;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Expected a comma.");
                        }
                    case 7:
                        if ('\\' == charAt) {
                            z = true;
                        }
                        if ('\"' == charAt && '\\' != c) {
                            addDirective(str, z);
                            this.m_state = 6;
                            z = false;
                            break;
                        }
                    case 8:
                        if (isValidTokenChar(charAt)) {
                            continue;
                        } else if (isWhiteSpace(charAt)) {
                            addDirective(str, false);
                            this.m_state = 6;
                            break;
                        } else if (',' == charAt) {
                            addDirective(str, false);
                            this.m_state = 2;
                            break;
                        } else {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Invalid value character");
                        }
                }
                this.m_curPos++;
                c = charAt;
            }
            int i = this.m_state;
            if (i == 2) {
                throw new SaslException("Parse error: Trailing comma.");
            } else if (i == 3 || i == 4 || i == 5) {
                throw new SaslException("Parse error: Missing value.");
            } else if (i == 7) {
                throw new SaslException("Parse error: Missing closing quote.");
            } else if (i == 8) {
                addDirective(str, false);
            }
        } else {
            throw new SaslException("No UTF-8 support on platform");
        }
    }

    /* access modifiers changed from: package-private */
    public void addDirective(String str, boolean z) {
        String str2;
        int i = 1;
        if (!z) {
            str2 = this.m_directives.substring(this.m_scanStart, this.m_curPos);
        } else {
            StringBuffer stringBuffer = new StringBuffer(this.m_curPos - this.m_scanStart);
            int i2 = 0;
            int i3 = this.m_scanStart;
            while (i3 < this.m_curPos) {
                if ('\\' == this.m_directives.charAt(i3)) {
                    i3++;
                }
                stringBuffer.setCharAt(i2, this.m_directives.charAt(i3));
                i2++;
                i3++;
            }
            str2 = new String(stringBuffer);
        }
        if (this.m_state != 7) {
            i = 2;
        }
        this.m_directiveList.add(new ParsedDirective(str, str2, i));
    }

    /* access modifiers changed from: package-private */
    public Iterator getIterator() {
        return this.m_directiveList.iterator();
    }
}
