package org.xbill.DNS;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.xbill.DNS.utils.Base16;
import org.xbill.DNS.utils.Base32;
import org.xbill.DNS.utils.Base64;

public class Tokenizer {
    public static final int COMMENT = 5;
    public static final int EOF = 0;
    public static final int EOL = 1;
    public static final int IDENTIFIER = 3;
    public static final int QUOTED_STRING = 4;
    public static final int WHITESPACE = 2;
    private static String delim = " \t\n;()\"";
    private static String quotes = "\"";
    private Token current;
    private String delimiters;
    private String filename;
    private PushbackInputStream is;
    private int line;
    private int multiline;
    private boolean quoting;
    private StringBuffer sb;
    private boolean ungottenToken;
    private boolean wantClose;

    public static class Token {
        public int type;
        public String value;

        private Token() {
            this.type = -1;
            this.value = null;
        }

        /* access modifiers changed from: private */
        public Token set(int i, StringBuffer stringBuffer) {
            String str;
            if (i >= 0) {
                this.type = i;
                if (stringBuffer == null) {
                    str = null;
                } else {
                    str = stringBuffer.toString();
                }
                this.value = str;
                return this;
            }
            throw new IllegalArgumentException();
        }

        public String toString() {
            int i = this.type;
            if (i == 0) {
                return "<eof>";
            }
            if (i == 1) {
                return "<eol>";
            }
            if (i == 2) {
                return "<whitespace>";
            }
            if (i == 3) {
                return "<identifier: " + this.value + ">";
            } else if (i == 4) {
                return "<quoted_string: " + this.value + ">";
            } else if (i != 5) {
                return "<unknown>";
            } else {
                return "<comment: " + this.value + ">";
            }
        }

        public boolean isString() {
            int i = this.type;
            return i == 3 || i == 4;
        }

        public boolean isEOL() {
            int i = this.type;
            return i == 1 || i == 0;
        }
    }

    static class TokenizerException extends TextParseException {
        String message;

        public TokenizerException(String str, int i, String str2) {
            super(str + ":" + i + ": " + str2);
            this.message = str2;
        }

        public String getBaseMessage() {
            return this.message;
        }
    }

    public Tokenizer(InputStream inputStream) {
        this.is = new PushbackInputStream(!(inputStream instanceof BufferedInputStream) ? new BufferedInputStream(inputStream) : inputStream, 2);
        this.ungottenToken = false;
        this.multiline = 0;
        this.quoting = false;
        this.delimiters = delim;
        this.current = new Token();
        this.sb = new StringBuffer();
        this.filename = "<none>";
        this.line = 1;
    }

    public Tokenizer(String str) {
        this((InputStream) new ByteArrayInputStream(str.getBytes()));
    }

    public Tokenizer(File file) throws FileNotFoundException {
        this((InputStream) new FileInputStream(file));
        this.wantClose = true;
        this.filename = file.getName();
    }

    private int getChar() throws IOException {
        int read = this.is.read();
        if (read == 13) {
            int read2 = this.is.read();
            if (read2 != 10) {
                this.is.unread(read2);
            }
            read = 10;
        }
        if (read == 10) {
            this.line++;
        }
        return read;
    }

    private void ungetChar(int i) throws IOException {
        if (i != -1) {
            this.is.unread(i);
            if (i == 10) {
                this.line--;
            }
        }
    }

    private int skipWhitespace() throws IOException {
        int i;
        int i2 = 0;
        while (true) {
            i = getChar();
            if (i == 32 || i == 9 || (i == 10 && this.multiline > 0)) {
                i2++;
                continue;
            }
            break;
        }
        ungetChar(i);
        return i2;
    }

    private void checkUnbalancedParens() throws TextParseException {
        if (this.multiline > 0) {
            throw exception("unbalanced parentheses");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:0x0112 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.xbill.DNS.Tokenizer.Token get(boolean r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            boolean r0 = r9.ungottenToken
            r1 = 5
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x002e
            r9.ungottenToken = r4
            org.xbill.DNS.Tokenizer$Token r0 = r9.current
            int r0 = r0.type
            if (r0 != r2) goto L_0x0015
            if (r10 == 0) goto L_0x002e
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            return r10
        L_0x0015:
            org.xbill.DNS.Tokenizer$Token r0 = r9.current
            int r0 = r0.type
            if (r0 != r1) goto L_0x0020
            if (r11 == 0) goto L_0x002e
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            return r10
        L_0x0020:
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            int r10 = r10.type
            if (r10 != r3) goto L_0x002b
            int r10 = r9.line
            int r10 = r10 + r3
            r9.line = r10
        L_0x002b:
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            return r10
        L_0x002e:
            int r0 = r9.skipWhitespace()
            r5 = 0
            if (r0 <= 0) goto L_0x003e
            if (r10 == 0) goto L_0x003e
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r2, r5)
            return r10
        L_0x003e:
            r10 = 3
            java.lang.StringBuffer r0 = r9.sb
            r0.setLength(r4)
            r0 = 4
        L_0x0045:
            int r2 = r9.getChar()
            r6 = 10
            r7 = -1
            if (r2 == r7) goto L_0x0083
            java.lang.String r8 = r9.delimiters
            int r8 = r8.indexOf(r2)
            if (r8 == r7) goto L_0x0057
            goto L_0x0083
        L_0x0057:
            r8 = 92
            if (r2 != r8) goto L_0x006e
            int r2 = r9.getChar()
            if (r2 == r7) goto L_0x0067
            java.lang.StringBuffer r6 = r9.sb
            r6.append(r8)
            goto L_0x007c
        L_0x0067:
            java.lang.String r10 = "unterminated escape sequence"
            org.xbill.DNS.TextParseException r10 = r9.exception(r10)
            throw r10
        L_0x006e:
            boolean r7 = r9.quoting
            if (r7 == 0) goto L_0x007c
            if (r2 == r6) goto L_0x0075
            goto L_0x007c
        L_0x0075:
            java.lang.String r10 = "newline in quoted string"
            org.xbill.DNS.TextParseException r10 = r9.exception(r10)
            throw r10
        L_0x007c:
            java.lang.StringBuffer r6 = r9.sb
            char r2 = (char) r2
            r6.append(r2)
            goto L_0x0045
        L_0x0083:
            if (r2 != r7) goto L_0x00a8
            boolean r11 = r9.quoting
            if (r11 != 0) goto L_0x00a1
            java.lang.StringBuffer r11 = r9.sb
            int r11 = r11.length()
            if (r11 != 0) goto L_0x0098
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r4, r5)
            return r10
        L_0x0098:
            org.xbill.DNS.Tokenizer$Token r11 = r9.current
            java.lang.StringBuffer r0 = r9.sb
            org.xbill.DNS.Tokenizer$Token r10 = r11.set(r10, r0)
            return r10
        L_0x00a1:
            java.lang.String r10 = "EOF in quoted string"
            org.xbill.DNS.TextParseException r10 = r9.exception(r10)
            throw r10
        L_0x00a8:
            java.lang.StringBuffer r8 = r9.sb
            int r8 = r8.length()
            if (r8 != 0) goto L_0x0147
            if (r10 == r0) goto L_0x0147
            r8 = 40
            if (r2 != r8) goto L_0x00bf
            int r2 = r9.multiline
            int r2 = r2 + r3
            r9.multiline = r2
            r9.skipWhitespace()
            goto L_0x0045
        L_0x00bf:
            r8 = 41
            if (r2 != r8) goto L_0x00d7
            int r2 = r9.multiline
            if (r2 <= 0) goto L_0x00d0
            int r2 = r2 + -1
            r9.multiline = r2
            r9.skipWhitespace()
            goto L_0x0045
        L_0x00d0:
            java.lang.String r10 = "invalid close parenthesis"
            org.xbill.DNS.TextParseException r10 = r9.exception(r10)
            throw r10
        L_0x00d7:
            r8 = 34
            if (r2 != r8) goto L_0x00f3
            boolean r2 = r9.quoting
            if (r2 != 0) goto L_0x00e8
            r9.quoting = r3
            java.lang.String r10 = quotes
            r9.delimiters = r10
            r10 = 4
            goto L_0x0045
        L_0x00e8:
            r9.quoting = r4
            java.lang.String r2 = delim
            r9.delimiters = r2
            r9.skipWhitespace()
            goto L_0x0045
        L_0x00f3:
            if (r2 != r6) goto L_0x00fc
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r3, r5)
            return r10
        L_0x00fc:
            r8 = 59
            if (r2 != r8) goto L_0x0141
        L_0x0100:
            int r2 = r9.getChar()
            if (r2 == r6) goto L_0x0110
            if (r2 != r7) goto L_0x0109
            goto L_0x0110
        L_0x0109:
            java.lang.StringBuffer r8 = r9.sb
            char r2 = (char) r2
            r8.append(r2)
            goto L_0x0100
        L_0x0110:
            if (r11 == 0) goto L_0x011e
            r9.ungetChar(r2)
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            java.lang.StringBuffer r11 = r9.sb
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r1, r11)
            return r10
        L_0x011e:
            if (r2 != r7) goto L_0x012c
            if (r10 == r0) goto L_0x012c
            r9.checkUnbalancedParens()
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r4, r5)
            return r10
        L_0x012c:
            int r2 = r9.multiline
            if (r2 <= 0) goto L_0x013a
            r9.skipWhitespace()
            java.lang.StringBuffer r2 = r9.sb
            r2.setLength(r4)
            goto L_0x0045
        L_0x013a:
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r3, r5)
            return r10
        L_0x0141:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            r10.<init>()
            throw r10
        L_0x0147:
            r9.ungetChar(r2)
            java.lang.StringBuffer r11 = r9.sb
            int r11 = r11.length()
            if (r11 != 0) goto L_0x015e
            if (r10 == r0) goto L_0x015e
            r9.checkUnbalancedParens()
            org.xbill.DNS.Tokenizer$Token r10 = r9.current
            org.xbill.DNS.Tokenizer$Token r10 = r10.set(r4, r5)
            return r10
        L_0x015e:
            org.xbill.DNS.Tokenizer$Token r11 = r9.current
            java.lang.StringBuffer r0 = r9.sb
            org.xbill.DNS.Tokenizer$Token r10 = r11.set(r10, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Tokenizer.get(boolean, boolean):org.xbill.DNS.Tokenizer$Token");
    }

    public Token get() throws IOException {
        return get(false, false);
    }

    public void unget() {
        if (!this.ungottenToken) {
            if (this.current.type == 1) {
                this.line--;
            }
            this.ungottenToken = true;
            return;
        }
        throw new IllegalStateException("Cannot unget multiple tokens");
    }

    public String getString() throws IOException {
        Token token = get();
        if (token.isString()) {
            return token.value;
        }
        throw exception("expected a string");
    }

    private String _getIdentifier(String str) throws IOException {
        Token token = get();
        if (token.type == 3) {
            return token.value;
        }
        throw exception("expected " + str);
    }

    public String getIdentifier() throws IOException {
        return _getIdentifier("an identifier");
    }

    public long getLong() throws IOException {
        String _getIdentifier = _getIdentifier("an integer");
        if (Character.isDigit(_getIdentifier.charAt(0))) {
            try {
                return Long.parseLong(_getIdentifier);
            } catch (NumberFormatException unused) {
                throw exception("expected an integer");
            }
        } else {
            throw exception("expected an integer");
        }
    }

    public long getUInt32() throws IOException {
        long j = getLong();
        if (j >= 0 && j <= 4294967295L) {
            return j;
        }
        throw exception("expected an 32 bit unsigned integer");
    }

    public int getUInt16() throws IOException {
        long j = getLong();
        if (j >= 0 && j <= 65535) {
            return (int) j;
        }
        throw exception("expected an 16 bit unsigned integer");
    }

    public int getUInt8() throws IOException {
        long j = getLong();
        if (j >= 0 && j <= 255) {
            return (int) j;
        }
        throw exception("expected an 8 bit unsigned integer");
    }

    public long getTTL() throws IOException {
        try {
            return TTL.parseTTL(_getIdentifier("a TTL value"));
        } catch (NumberFormatException unused) {
            throw exception("expected a TTL value");
        }
    }

    public long getTTLLike() throws IOException {
        try {
            return TTL.parse(_getIdentifier("a TTL-like value"), false);
        } catch (NumberFormatException unused) {
            throw exception("expected a TTL-like value");
        }
    }

    public Name getName(Name name) throws IOException {
        try {
            Name fromString = Name.fromString(_getIdentifier("a name"), name);
            if (fromString.isAbsolute()) {
                return fromString;
            }
            throw new RelativeNameException(fromString);
        } catch (TextParseException e) {
            throw exception(e.getMessage());
        }
    }

    public InetAddress getAddress(int i) throws IOException {
        try {
            return Address.getByAddress(_getIdentifier("an address"), i);
        } catch (UnknownHostException e) {
            throw exception(e.getMessage());
        }
    }

    public void getEOL() throws IOException {
        Token token = get();
        if (token.type != 1 && token.type != 0) {
            throw exception("expected EOL or EOF");
        }
    }

    private String remainingStrings() throws IOException {
        StringBuffer stringBuffer = null;
        while (true) {
            Token token = get();
            if (!token.isString()) {
                break;
            }
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer();
            }
            stringBuffer.append(token.value);
        }
        unget();
        if (stringBuffer == null) {
            return null;
        }
        return stringBuffer.toString();
    }

    public byte[] getBase64(boolean z) throws IOException {
        String remainingStrings = remainingStrings();
        if (remainingStrings != null) {
            byte[] fromString = Base64.fromString(remainingStrings);
            if (fromString != null) {
                return fromString;
            }
            throw exception("invalid Base64 encoding");
        } else if (!z) {
            return null;
        } else {
            throw exception("expected Base64 encoded string");
        }
    }

    public byte[] getBase64() throws IOException {
        return getBase64(false);
    }

    public byte[] getHex(boolean z) throws IOException {
        String remainingStrings = remainingStrings();
        if (remainingStrings != null) {
            byte[] fromString = Base16.fromString(remainingStrings);
            if (fromString != null) {
                return fromString;
            }
            throw exception("invalid hex encoding");
        } else if (!z) {
            return null;
        } else {
            throw exception("expected hex encoded string");
        }
    }

    public byte[] getHex() throws IOException {
        return getHex(false);
    }

    public byte[] getHexString() throws IOException {
        byte[] fromString = Base16.fromString(_getIdentifier("a hex string"));
        if (fromString != null) {
            return fromString;
        }
        throw exception("invalid hex encoding");
    }

    public byte[] getBase32String(Base32 Base32) throws IOException {
        byte[] fromString = Base32.fromString(_getIdentifier("a Base32 string"));
        if (fromString != null) {
            return fromString;
        }
        throw exception("invalid Base32 encoding");
    }

    public TextParseException exception(String str) {
        return new TokenizerException(this.filename, this.line, str);
    }

    public void close() {
        if (this.wantClose) {
            try {
                this.is.close();
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        close();
    }
}
