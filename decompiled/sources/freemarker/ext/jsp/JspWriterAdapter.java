package freemarker.ext.jsp;

import freemarker.template.utility.SecurityUtilities;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.jsp.JspWriter;

class JspWriterAdapter extends JspWriter {
    static final char[] NEWLINE = SecurityUtilities.getSystemProperty("line.separator").toCharArray();
    private final Writer out;

    public int getRemaining() {
        return 0;
    }

    JspWriterAdapter(Writer writer) {
        super(0, true);
        this.out = writer;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("JspWriterAdapter wrapping a ");
        stringBuffer.append(this.out.toString());
        return stringBuffer.toString();
    }

    public void clear() throws IOException {
        throw new IOException("Can't clear");
    }

    public void clearBuffer() throws IOException {
        throw new IOException("Can't clear");
    }

    public void close() throws IOException {
        throw new IOException("Close not permitted.");
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void newLine() throws IOException {
        this.out.write(NEWLINE);
    }

    public void print(boolean z) throws IOException {
        this.out.write((z ? Boolean.TRUE : Boolean.FALSE).toString());
    }

    public void print(char c) throws IOException {
        this.out.write(c);
    }

    public void print(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    public void print(double d) throws IOException {
        this.out.write(Double.toString(d));
    }

    public void print(float f) throws IOException {
        this.out.write(Float.toString(f));
    }

    public void print(int i) throws IOException {
        this.out.write(Integer.toString(i));
    }

    public void print(long j) throws IOException {
        this.out.write(Long.toString(j));
    }

    public void print(Object obj) throws IOException {
        this.out.write(obj == null ? "null" : obj.toString());
    }

    public void print(String str) throws IOException {
        this.out.write(str);
    }

    public void println() throws IOException {
        newLine();
    }

    public void println(boolean z) throws IOException {
        print(z);
        newLine();
    }

    public void println(char c) throws IOException {
        print(c);
        newLine();
    }

    public void println(char[] cArr) throws IOException {
        print(cArr);
        newLine();
    }

    public void println(double d) throws IOException {
        print(d);
        newLine();
    }

    public void println(float f) throws IOException {
        print(f);
        newLine();
    }

    public void println(int i) throws IOException {
        print(i);
        newLine();
    }

    public void println(long j) throws IOException {
        print(j);
        newLine();
    }

    public void println(Object obj) throws IOException {
        print(obj);
        newLine();
    }

    public void println(String str) throws IOException {
        print(str);
        newLine();
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        this.out.write(cArr, i, i2);
    }
}
