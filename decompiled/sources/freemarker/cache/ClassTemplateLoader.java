package freemarker.cache;

import java.net.URL;

public class ClassTemplateLoader extends URLTemplateLoader {
    private Class loaderClass;
    private String path;

    public ClassTemplateLoader() {
        setFields(getClass(), "/");
    }

    public ClassTemplateLoader(Class cls) {
        setFields(cls, "");
    }

    public ClassTemplateLoader(Class cls, String str) {
        setFields(cls, str);
    }

    /* access modifiers changed from: protected */
    public URL getURL(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.path);
        stringBuffer.append(str);
        String stringBuffer2 = stringBuffer.toString();
        if (!this.path.equals("/") || isSchemeless(stringBuffer2)) {
            return this.loaderClass.getResource(stringBuffer2);
        }
        return null;
    }

    private static boolean isSchemeless(String str) {
        int length = str.length();
        for (int i = (length <= 0 || str.charAt(0) != '/') ? 0 : 1; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '/') {
                return true;
            }
            if (charAt == ':') {
                return false;
            }
        }
        return true;
    }

    private void setFields(Class cls, String str) {
        if (cls == null) {
            throw new IllegalArgumentException("loaderClass == null");
        } else if (str != null) {
            this.loaderClass = cls;
            this.path = canonicalizePrefix(str);
        } else {
            throw new IllegalArgumentException("path == null");
        }
    }
}
