package freemarker.cache;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

public abstract class URLTemplateLoader implements TemplateLoader {
    /* access modifiers changed from: protected */
    public abstract URL getURL(String str);

    public Object findTemplateSource(String str) throws IOException {
        URL url = getURL(str);
        if (url == null) {
            return null;
        }
        return new URLTemplateSource(url);
    }

    public long getLastModified(Object obj) {
        return ((URLTemplateSource) obj).lastModified();
    }

    public Reader getReader(Object obj, String str) throws IOException {
        return new InputStreamReader(((URLTemplateSource) obj).getInputStream(), str);
    }

    public void closeTemplateSource(Object obj) throws IOException {
        ((URLTemplateSource) obj).close();
    }

    protected static String canonicalizePrefix(String str) {
        String replace = str.replace('\\', '/');
        if (replace.length() <= 0 || replace.endsWith("/")) {
            return replace;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(replace);
        stringBuffer.append("/");
        return stringBuffer.toString();
    }
}
