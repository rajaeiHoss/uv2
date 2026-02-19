package freemarker.cache;

import freemarker.log.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.servlet.ServletContext;

public class WebappTemplateLoader implements TemplateLoader {
    private static final Logger logger = Logger.getLogger("freemarker.cache");
    private final String path;
    private final ServletContext servletContext;

    public WebappTemplateLoader(ServletContext servletContext2) {
        this(servletContext2, "/");
    }

    public WebappTemplateLoader(ServletContext servletContext2, String str) {
        if (servletContext2 == null) {
            throw new IllegalArgumentException("servletContext == null");
        } else if (str != null) {
            String replace = str.replace('\\', '/');
            if (!replace.endsWith("/")) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(replace);
                stringBuffer.append("/");
                replace = stringBuffer.toString();
            }
            if (!replace.startsWith("/")) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("/");
                stringBuffer2.append(replace);
                replace = stringBuffer2.toString();
            }
            this.path = replace;
            this.servletContext = servletContext2;
        } else {
            throw new IllegalArgumentException("path == null");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:0|1|2|(2:4|(1:6)(2:7|(1:9)))|10|11|(1:18)(2:13|17)) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        r2 = logger;
        r3 = new java.lang.StringBuffer();
        r3.append("Could not retrieve resource ");
        r3.append(freemarker.template.utility.StringUtil.jQuoteNoXSS(r6));
        r2.warn(r3.toString(), r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0057, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x002d */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object findTemplateSource(java.lang.String r6) throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = r5.path
            r0.append(r1)
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            r0 = 0
            javax.servlet.ServletContext r1 = r5.servletContext     // Catch:{ SecurityException -> 0x002d }
            java.lang.String r1 = r1.getRealPath(r6)     // Catch:{ SecurityException -> 0x002d }
            if (r1 == 0) goto L_0x002d
            java.io.File r2 = new java.io.File     // Catch:{ SecurityException -> 0x002d }
            r2.<init>(r1)     // Catch:{ SecurityException -> 0x002d }
            boolean r1 = r2.isFile()     // Catch:{ SecurityException -> 0x002d }
            if (r1 != 0) goto L_0x0026
            return r0
        L_0x0026:
            boolean r1 = r2.canRead()     // Catch:{ SecurityException -> 0x002d }
            if (r1 == 0) goto L_0x002d
            return r2
        L_0x002d:
            javax.servlet.ServletContext r1 = r5.servletContext     // Catch:{ MalformedURLException -> 0x003c }
            java.net.URL r6 = r1.getResource(r6)     // Catch:{ MalformedURLException -> 0x003c }
            if (r6 != 0) goto L_0x0036
            goto L_0x003b
        L_0x0036:
            freemarker.cache.URLTemplateSource r0 = new freemarker.cache.URLTemplateSource
            r0.<init>(r6)
        L_0x003b:
            return r0
        L_0x003c:
            r1 = move-exception
            freemarker.log.Logger r2 = logger
            java.lang.StringBuffer r3 = new java.lang.StringBuffer
            r3.<init>()
            java.lang.String r4 = "Could not retrieve resource "
            r3.append(r4)
            java.lang.String r6 = freemarker.template.utility.StringUtil.jQuoteNoXSS((java.lang.String) r6)
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r2.warn(r6, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: freemarker.cache.WebappTemplateLoader.findTemplateSource(java.lang.String):java.lang.Object");
    }

    public long getLastModified(Object obj) {
        if (obj instanceof File) {
            return ((File) obj).lastModified();
        }
        return ((URLTemplateSource) obj).lastModified();
    }

    public Reader getReader(Object obj, String str) throws IOException {
        if (obj instanceof File) {
            return new InputStreamReader(new FileInputStream((File) obj), str);
        }
        return new InputStreamReader(((URLTemplateSource) obj).getInputStream(), str);
    }

    public void closeTemplateSource(Object obj) throws IOException {
        if (!(obj instanceof File)) {
            ((URLTemplateSource) obj).close();
        }
    }
}
