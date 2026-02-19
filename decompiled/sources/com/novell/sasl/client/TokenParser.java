package com.novell.sasl.client;

import org.apache.harmony.javax.security.sasl.SaslException;

class TokenParser {
    private static final int STATE_DONE = 6;
    private static final int STATE_LOOKING_FOR_COMMA = 4;
    private static final int STATE_LOOKING_FOR_FIRST_TOKEN = 1;
    private static final int STATE_LOOKING_FOR_TOKEN = 2;
    private static final int STATE_PARSING_ERROR = 5;
    private static final int STATE_SCANNING_TOKEN = 3;
    private int m_curPos = 0;
    private int m_scanStart = 0;
    private int m_state = 1;
    private String m_tokens;

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

    TokenParser(String str) {
        this.m_tokens = str;
    }

    /* access modifiers changed from: package-private */
    public String parseToken() throws SaslException {
        String substring;
        String str = null;
        if (this.m_state == 6) {
            return null;
        }
        while (this.m_curPos < this.m_tokens.length() && str == null) {
            char charAt = this.m_tokens.charAt(this.m_curPos);
            int i = this.m_state;
            if (i == 1 || i == 2) {
                if (isWhiteSpace(charAt)) {
                    continue;
                } else if (isValidTokenChar(charAt)) {
                    this.m_scanStart = this.m_curPos;
                    this.m_state = 3;
                } else {
                    this.m_state = 5;
                    throw new SaslException("Invalid token character at position " + this.m_curPos);
                }
            } else if (i != 3) {
                if (i == 4 && !isWhiteSpace(charAt)) {
                    if (charAt == ',') {
                        this.m_state = 2;
                    } else {
                        this.m_state = 5;
                        throw new SaslException("Expected a comma, found '" + charAt + "' at postion " + this.m_curPos);
                    }
                }
            } else if (!isValidTokenChar(charAt)) {
                if (isWhiteSpace(charAt)) {
                    substring = this.m_tokens.substring(this.m_scanStart, this.m_curPos);
                    this.m_state = 4;
                } else if (',' == charAt) {
                    substring = this.m_tokens.substring(this.m_scanStart, this.m_curPos);
                    this.m_state = 2;
                } else {
                    this.m_state = 5;
                    throw new SaslException("Invalid token character at position " + this.m_curPos);
                }
                str = substring;
            } else {
                continue;
            }
            this.m_curPos++;
        }
        if (str != null) {
            return str;
        }
        int i2 = this.m_state;
        if (i2 == 2) {
            throw new SaslException("Trialing comma");
        } else if (i2 != 3) {
            return str;
        } else {
            String substring2 = this.m_tokens.substring(this.m_scanStart);
            this.m_state = 6;
            return substring2;
        }
    }
}
