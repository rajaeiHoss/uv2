package org.kobjects.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import org.kobjects.Base64.Base64;

public class Decoder {
    String boundary;
    char[] buf;
    String characterEncoding;
    boolean consumed;
    boolean eof;
    Hashtable header;
    InputStream is;

    private final String readLine() throws IOException {
        int i = 0;
        while (true) {
            int read = this.is.read();
            if (read == -1 && i == 0) {
                return null;
            }
            if (read == -1 || read == 10) {
                break;
            }
            if (read != 13) {
                char[] cArr = this.buf;
                if (i >= cArr.length) {
                    char[] cArr2 = new char[((cArr.length * 3) / 2)];
                    System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                    this.buf = cArr2;
                }
                this.buf[i] = (char) read;
                i++;
            }
        }
        return new String(this.buf, 0, i);
    }

    public static Hashtable getHeaderElements(String str) {
        int i;
        Hashtable hashtable = new Hashtable();
        int length = str.length();
        String str2 = "";
        int i2 = 0;
        while (true) {
            if (i2 >= length || str.charAt(i2) > ' ') {
                if (i2 >= length) {
                    break;
                }
                if (str.charAt(i2) == '\"') {
                    int i3 = i2 + 1;
                    int indexOf = str.indexOf(34, i3);
                    if (indexOf != -1) {
                        hashtable.put(str2, str.substring(i3, indexOf));
                        i = indexOf + 2;
                        if (i >= length) {
                            break;
                        } else if (str.charAt(i - 1) != ';') {
                            throw new RuntimeException("; expected in " + str);
                        }
                    } else {
                        throw new RuntimeException("End quote expected in " + str);
                    }
                } else {
                    int indexOf2 = str.indexOf(59, i2);
                    if (indexOf2 == -1) {
                        hashtable.put(str2, str.substring(i2));
                        break;
                    }
                    hashtable.put(str2, str.substring(i2, indexOf2));
                    i = indexOf2 + 1;
                }
                int indexOf3 = str.indexOf(61, i);
                if (indexOf3 == -1) {
                    break;
                }
                i2 = indexOf3 + 1;
                str2 = str.substring(i, indexOf3).toLowerCase().trim();
            } else {
                i2++;
            }
        }
        return hashtable;
    }

    public Decoder(InputStream inputStream, String str) throws IOException {
        this(inputStream, str, (String) null);
    }

    public Decoder(InputStream inputStream, String str, String str2) throws IOException {
        String readLine;
        this.buf = new char[256];
        this.characterEncoding = str2;
        this.is = inputStream;
        this.boundary = "--" + str;
        do {
            readLine = readLine();
            if (readLine == null) {
                throw new IOException("Unexpected EOF");
            }
        } while (!readLine.startsWith(this.boundary));
        if (readLine.endsWith("--")) {
            this.eof = true;
            inputStream.close();
        }
        this.consumed = true;
    }

    public Enumeration getHeaderNames() {
        return this.header.keys();
    }

    public String getHeader(String str) {
        return (String) this.header.get(str.toLowerCase());
    }

    public String readContent() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        readContent(byteArrayOutputStream);
        String str = this.characterEncoding == null ? new String(byteArrayOutputStream.toByteArray()) : new String(byteArrayOutputStream.toByteArray(), this.characterEncoding);
        PrintStream printStream = System.out;
        printStream.println("Field content: '" + str + "'");
        return str;
    }

    public void readContent(OutputStream outputStream) throws IOException {
        String str;
        if (!this.consumed) {
            getHeader("Content-Type");
            if ("Base64".equals(getHeader("Content-Transfer-Encoding"))) {
                new ByteArrayOutputStream();
                while (true) {
                    str = readLine();
                    if (str == null) {
                        throw new IOException("Unexpected EOF");
                    } else if (str.startsWith(this.boundary)) {
                        break;
                    } else {
                        Base64.decode(str, outputStream);
                    }
                }
            } else {
                String str2 = "\r\n" + this.boundary;
                int i = 0;
                while (true) {
                    int read = this.is.read();
                    if (read != -1) {
                        char c = (char) read;
                        if (c == str2.charAt(i)) {
                            i++;
                            if (i == str2.length()) {
                                str = readLine();
                                break;
                            }
                        } else {
                            if (i > 0) {
                                for (int i2 = 0; i2 < i; i2++) {
                                    outputStream.write((byte) str2.charAt(i2));
                                }
                                i = c == str2.charAt(0) ? 1 : 0;
                            }
                            if (i == 0) {
                                outputStream.write((byte) read);
                            }
                        }
                    } else {
                        throw new RuntimeException("Unexpected EOF");
                    }
                }
            }
            if (str.endsWith("--")) {
                this.eof = true;
            }
            this.consumed = true;
            return;
        }
        throw new RuntimeException("Content already consumed!");
    }

    public boolean next() throws IOException {
        if (!this.consumed) {
            readContent((OutputStream) null);
        }
        if (this.eof) {
            return false;
        }
        this.header = new Hashtable();
        while (true) {
            String readLine = readLine();
            if (readLine == null || readLine.equals("")) {
                this.consumed = false;
                break;
            } else {
                int indexOf = readLine.indexOf(58);
                if (indexOf != -1) {
                    this.header.put(readLine.substring(0, indexOf).trim().toLowerCase(), readLine.substring(indexOf + 1).trim());
                } else {
                    throw new IOException("colon missing in multipart header line: " + readLine);
                }
            }
        }
        return true;
    }
}
