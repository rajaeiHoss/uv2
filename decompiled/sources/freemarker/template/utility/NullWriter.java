package freemarker.template.utility;

import java.io.IOException;
import java.io.Writer;

public final class NullWriter extends Writer {
    public static final NullWriter INSTANCE = new NullWriter();

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

    public void write(int i) throws IOException {
    }

    public void write(String str) throws IOException {
    }

    public void write(String str, int i, int i2) throws IOException {
    }

    public void write(char[] cArr) throws IOException {
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
    }

    private NullWriter() {
    }
}
