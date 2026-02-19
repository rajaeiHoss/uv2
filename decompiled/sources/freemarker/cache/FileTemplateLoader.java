package freemarker.cache;

import freemarker.template.utility.SecurityUtilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class FileTemplateLoader implements TemplateLoader {
    /* access modifiers changed from: private */
    public static final boolean SEP_IS_SLASH = (File.separatorChar == '/');
    public final File baseDir;
    /* access modifiers changed from: private */
    public final String canonicalPath;

    public void closeTemplateSource(Object obj) {
    }

    public FileTemplateLoader() throws IOException {
        this(new File(SecurityUtilities.getSystemProperty("user.dir")));
    }

    public FileTemplateLoader(File file) throws IOException {
        this(file, false);
    }

    public FileTemplateLoader(final File file, final boolean z) throws IOException {
        try {
            Object[] objArr = (Object[]) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    if (!file.exists()) {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append(file);
                        stringBuffer.append(" does not exist.");
                        throw new FileNotFoundException(stringBuffer.toString());
                    } else if (file.isDirectory()) {
                        Object[] objArr = new Object[2];
                        if (z) {
                            objArr[0] = file;
                            objArr[1] = null;
                        } else {
                            objArr[0] = file.getCanonicalFile();
                            String path = ((File) objArr[0]).getPath();
                            if (!path.endsWith(File.separator)) {
                                StringBuffer stringBuffer2 = new StringBuffer();
                                stringBuffer2.append(path);
                                stringBuffer2.append(File.separatorChar);
                                path = stringBuffer2.toString();
                            }
                            objArr[1] = path;
                        }
                        return objArr;
                    } else {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append(file);
                        stringBuffer3.append(" is not a directory.");
                        throw new IOException(stringBuffer3.toString());
                    }
                }
            });
            this.baseDir = (File) objArr[0];
            this.canonicalPath = (String) objArr[1];
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    public Object findTemplateSource(final String str) throws IOException {
        try {
            return AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    File file = new File(FileTemplateLoader.this.baseDir, FileTemplateLoader.SEP_IS_SLASH ? str : str.replace('/', File.separatorChar));
                    if (!file.isFile()) {
                        return null;
                    }
                    if (FileTemplateLoader.this.canonicalPath != null) {
                        String canonicalPath = file.getCanonicalPath();
                        if (!canonicalPath.startsWith(FileTemplateLoader.this.canonicalPath)) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(file.getAbsolutePath());
                            stringBuffer.append(" resolves to ");
                            stringBuffer.append(canonicalPath);
                            stringBuffer.append(" which ");
                            stringBuffer.append(" doesn't start with ");
                            stringBuffer.append(FileTemplateLoader.this.canonicalPath);
                            throw new SecurityException(stringBuffer.toString());
                        }
                    }
                    return file;
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }

    public long getLastModified(final Object obj) {
        return ((Long) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                return new Long(((File) obj).lastModified());
            }
        })).longValue();
    }

    public Reader getReader(final Object obj, final String str) throws IOException {
        try {
            return (Reader) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    if (obj instanceof File) {
                        return new InputStreamReader(new FileInputStream((File) obj), str);
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("templateSource is a: ");
                    stringBuffer.append(obj.getClass().getName());
                    throw new IllegalArgumentException(stringBuffer.toString());
                }
            });
        } catch (PrivilegedActionException e) {
            throw ((IOException) e.getException());
        }
    }
}
