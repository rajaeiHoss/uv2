package freemarker.cache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

class URLTemplateSource {
    private URLConnection conn;
    private InputStream inputStream;
    private final URL url;

    URLTemplateSource(URL url2) throws IOException {
        this.url = url2;
        this.conn = url2.openConnection();
    }

    public boolean equals(Object obj) {
        if (obj instanceof URLTemplateSource) {
            return this.url.equals(((URLTemplateSource) obj).url);
        }
        return false;
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return this.url.toString();
    }

    /* access modifiers changed from: package-private */
    public long lastModified() {
        URLConnection uRLConnection = this.conn;
        if (uRLConnection instanceof JarURLConnection) {
            URL jarFileURL = ((JarURLConnection) uRLConnection).getJarFileURL();
            if (jarFileURL.getProtocol().equals("file")) {
                return new File(jarFileURL.getFile()).lastModified();
            }
            URLConnection uRLConnection2 = null;
            try {
                URLConnection openConnection = jarFileURL.openConnection();
                long lastModified = openConnection.getLastModified();
                if (openConnection != null) {
                    try {
                        openConnection.getInputStream().close();
                    } catch (IOException unused) {
                    }
                }
                return lastModified;
            } catch (IOException unused2) {
                if (uRLConnection2 != null) {
                    try {
                        uRLConnection2.getInputStream().close();
                    } catch (IOException unused3) {
                    }
                }
                return -1;
            } catch (Throwable th) {
                if (uRLConnection2 != null) {
                    try {
                        uRLConnection2.getInputStream().close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } else {
            long lastModified2 = uRLConnection.getLastModified();
            return (lastModified2 != -1 || !this.url.getProtocol().equals("file")) ? lastModified2 : new File(this.url.getFile()).lastModified();
        }
    }

    /* access modifiers changed from: package-private */
    public InputStream getInputStream() throws IOException {
        InputStream inputStream2 = this.conn.getInputStream();
        this.inputStream = inputStream2;
        return inputStream2;
    }

    /* access modifiers changed from: package-private */
    public void close() throws IOException {
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                inputStream2.close();
            } else {
                this.conn.getInputStream().close();
            }
        } finally {
            this.inputStream = null;
            this.conn = null;
        }
    }
}
