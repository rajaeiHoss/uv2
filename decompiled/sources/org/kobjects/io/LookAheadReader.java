package org.kobjects.io;

import java.io.IOException;
import java.io.Reader;

public class LookAheadReader extends Reader {
    char[] buf;
    int bufPos;
    int bufValid;
    Reader reader;

    public LookAheadReader(Reader reader2) {
        this.buf = new char[(Runtime.getRuntime().freeMemory() > 1000000 ? 16384 : 128)];
        this.bufPos = 0;
        this.bufValid = 0;
        this.reader = reader2;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.bufValid == 0 && peek(0) == -1) {
            return -1;
        }
        int i3 = this.bufValid;
        if (i2 > i3) {
            i2 = i3;
        }
        char[] cArr2 = this.buf;
        int length = cArr2.length;
        int i4 = this.bufPos;
        if (i2 > length - i4) {
            i2 = cArr2.length - i4;
        }
        System.arraycopy(cArr2, i4, cArr, i, i2);
        this.bufValid -= i2;
        int i5 = this.bufPos + i2;
        this.bufPos = i5;
        char[] cArr3 = this.buf;
        if (i5 > cArr3.length) {
            this.bufPos = i5 - cArr3.length;
        }
        return i2;
    }

    public String readTo(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (peek(0) != -1 && str.indexOf((char) peek(0)) == -1) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }

    public String readTo(char c) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (peek(0) != -1 && peek(0) != c) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read() throws IOException {
        int peek = peek(0);
        if (peek != -1) {
            int i = this.bufPos + 1;
            this.bufPos = i;
            if (i == this.buf.length) {
                this.bufPos = 0;
            }
            this.bufValid--;
        }
        return peek;
    }

    public int peek(int i) throws IOException {
        if (i <= 127) {
            while (true) {
                int i2 = this.bufValid;
                if (i >= i2) {
                    char[] cArr = this.buf;
                    int length = (this.bufPos + i2) % cArr.length;
                    int read = this.reader.read(this.buf, length, Math.min(cArr.length - length, cArr.length - i2));
                    if (read == -1) {
                        return -1;
                    }
                    this.bufValid += read;
                } else {
                    char[] cArr2 = this.buf;
                    return cArr2[this.bufPos + (i % cArr2.length)];
                }
            }
        } else {
            throw new RuntimeException("peek > 127 not supported!");
        }
    }

    public String readLine() throws IOException {
        if (peek(0) == -1) {
            return null;
        }
        String readTo = readTo("\r\n");
        if (read() == 13 && peek(0) == 10) {
            read();
        }
        return readTo;
    }

    public String readWhile(String str) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (peek(0) != -1 && str.indexOf((char) peek(0)) != -1) {
            stringBuffer.append((char) read());
        }
        return stringBuffer.toString();
    }

    public void skip(String str) throws IOException {
        while (peek(0) != -1 && str.indexOf((char) peek(0)) != -1) {
            read();
        }
    }
}
