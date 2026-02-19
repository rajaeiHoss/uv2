package freemarker.template.utility;

import freemarker.template.TemplateTransformModel;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class XmlEscape implements TemplateTransformModel {
    /* access modifiers changed from: private */
    public static final char[] AMP = "&amp;".toCharArray();
    /* access modifiers changed from: private */
    public static final char[] APOS = "&apos;".toCharArray();
    /* access modifiers changed from: private */
    public static final char[] GT = "&gt;".toCharArray();
    /* access modifiers changed from: private */
    public static final char[] LT = "&lt;".toCharArray();
    /* access modifiers changed from: private */
    public static final char[] QUOT = "&quot;".toCharArray();

    public Writer getWriter(final Writer writer, Map map) {
        return new Writer() {
            public void close() {
            }

            public void write(int i) throws IOException {
                if (i == 34) {
                    writer.write(XmlEscape.QUOT, 0, 6);
                } else if (i == 60) {
                    writer.write(XmlEscape.LT, 0, 4);
                } else if (i == 62) {
                    writer.write(XmlEscape.GT, 0, 4);
                } else if (i == 38) {
                    writer.write(XmlEscape.AMP, 0, 5);
                } else if (i != 39) {
                    writer.write(i);
                } else {
                    writer.write(XmlEscape.APOS, 0, 6);
                }
            }

            public void write(char[] cArr, int i, int i2) throws IOException {
                int i3 = i2 + i;
                int i4 = i;
                while (i < i3) {
                    char c = cArr[i];
                    if (c == '\"') {
                        writer.write(cArr, i4, i - i4);
                        writer.write(XmlEscape.QUOT, 0, 6);
                    } else if (c == '<') {
                        writer.write(cArr, i4, i - i4);
                        writer.write(XmlEscape.LT, 0, 4);
                    } else if (c == '>') {
                        writer.write(cArr, i4, i - i4);
                        writer.write(XmlEscape.GT, 0, 4);
                    } else if (c == '&') {
                        writer.write(cArr, i4, i - i4);
                        writer.write(XmlEscape.AMP, 0, 5);
                    } else if (c != '\'') {
                        i++;
                    } else {
                        writer.write(cArr, i4, i - i4);
                        writer.write(XmlEscape.APOS, 0, 6);
                    }
                    i4 = i + 1;
                    i++;
                }
                int i5 = i3 - i4;
                if (i5 > 0) {
                    writer.write(cArr, i4, i5);
                }
            }

            public void flush() throws IOException {
                writer.flush();
            }
        };
    }
}
