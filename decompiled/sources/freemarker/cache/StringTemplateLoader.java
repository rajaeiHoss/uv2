package freemarker.cache;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class StringTemplateLoader implements TemplateLoader {
    private final Map templates = new HashMap();

    public void closeTemplateSource(Object obj) {
    }

    public void putTemplate(String str, String str2) {
        putTemplate(str, str2, System.currentTimeMillis());
    }

    public void putTemplate(String str, String str2, long j) {
        this.templates.put(str, new StringTemplateSource(str, str2, j));
    }

    public Object findTemplateSource(String str) {
        return this.templates.get(str);
    }

    public long getLastModified(Object obj) {
        return ((StringTemplateSource) obj).lastModified;
    }

    public Reader getReader(Object obj, String str) {
        return new StringReader(((StringTemplateSource) obj).source);
    }

    private static class StringTemplateSource {
        /* access modifiers changed from: private */
        public final long lastModified;
        private final String name;
        /* access modifiers changed from: private */
        public final String source;

        StringTemplateSource(String str, String str2, long j) {
            if (str == null) {
                throw new IllegalArgumentException("name == null");
            } else if (str2 == null) {
                throw new IllegalArgumentException("source == null");
            } else if (j >= -1) {
                this.name = str;
                this.source = str2;
                this.lastModified = j;
            } else {
                throw new IllegalArgumentException("lastModified < -1L");
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof StringTemplateSource) {
                return this.name.equals(((StringTemplateSource) obj).name);
            }
            return false;
        }

        public int hashCode() {
            return this.name.hashCode();
        }
    }
}
