package freemarker.template.utility;

import freemarker.template.TemplateTransformModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class NormalizeNewlines implements TemplateTransformModel {
    public Writer getWriter(final Writer writer, Map map) {
        final StringBuffer stringBuffer = new StringBuffer();
        return new Writer() {
            public void write(char[] cArr, int i, int i2) {
                stringBuffer.append(cArr, i, i2);
            }

            public void flush() throws IOException {
                writer.flush();
            }

            public void close() throws IOException {
                StringReader stringReader = new StringReader(stringBuffer.toString());
                StringWriter stringWriter = new StringWriter();
                NormalizeNewlines.this.transform(stringReader, stringWriter);
                writer.write(stringWriter.toString());
            }
        };
    }

    public void transform(Reader reader, Writer writer) throws IOException {
        BufferedReader bufferedReader = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
        PrintWriter printWriter = writer instanceof PrintWriter ? (PrintWriter) writer : new PrintWriter(writer);
        String readLine = bufferedReader.readLine();
        if (readLine != null && readLine.length() > 0) {
            printWriter.println(readLine);
        }
        while (true) {
            String readLine2 = bufferedReader.readLine();
            if (readLine2 != null) {
                printWriter.println(readLine2);
            } else {
                return;
            }
        }
    }
}
