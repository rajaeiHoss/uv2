package com.hjq.http.model;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.MediaType;

public class FileContentResolver extends FileWrapper {
    private final ContentResolver mContentResolver;
    private MediaType mContentType;
    private final Uri mContentUri;
    private String mFileName;

    public boolean exists() {
        return true;
    }

    public File getParentFile() {
        return null;
    }

    public boolean isDirectory() {
        return false;
    }

    public boolean isFile() {
        return true;
    }

    public boolean isHidden() {
        return false;
    }

    public long lastModified() {
        return 0;
    }

    public String[] list() {
        return null;
    }

    public String[] list(FilenameFilter filenameFilter) {
        return null;
    }

    public File[] listFiles() {
        return null;
    }

    public File[] listFiles(FileFilter fileFilter) {
        return null;
    }

    public File[] listFiles(FilenameFilter filenameFilter) {
        return null;
    }

    public boolean mkdir() {
        return true;
    }

    public boolean mkdirs() {
        return true;
    }

    public boolean renameTo(File file) {
        return false;
    }

    public boolean setLastModified(long j) {
        return false;
    }

    public FileContentResolver(Context context, Uri uri) {
        this(context.getContentResolver(), uri);
    }

    public FileContentResolver(ContentResolver contentResolver, Uri uri) {
        this(contentResolver, uri, (String) null);
    }

    public FileContentResolver(Context context, Uri uri, String str) {
        this(context.getContentResolver(), uri, str);
    }

    public FileContentResolver(ContentResolver contentResolver, Uri uri, String str) {
        super(new File(uri.toString()));
        this.mContentResolver = contentResolver;
        this.mContentUri = uri;
        if (!TextUtils.isEmpty(str)) {
            this.mFileName = str;
            this.mContentType = ContentType.guessMimeType(str);
            return;
        }
        this.mFileName = getName();
        this.mContentType = ContentType.STREAM;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setContentType(MediaType mediaType) {
        this.mContentType = mediaType;
    }

    public MediaType getContentType() {
        return this.mContentType;
    }

    public Uri getContentUri() {
        return this.mContentUri;
    }

    public InputStream openInputStream() throws FileNotFoundException {
        return this.mContentResolver.openInputStream(this.mContentUri);
    }

    public OutputStream openOutputStream() throws FileNotFoundException {
        return this.mContentResolver.openOutputStream(this.mContentUri);
    }

    public boolean delete() {
        return this.mContentResolver.delete(this.mContentUri, (String) null, (String[]) null) > 0;
    }

    public long length() {
        InputStream inputStream = null;
        try {
            inputStream = openInputStream();
            if (inputStream != null) {
                long available = (long) inputStream.available();
                EasyUtils.closeStream(inputStream);
                return available;
            }
        } catch (FileNotFoundException e) {
            EasyLog.print((Throwable) e);
        } catch (IOException e2) {
            EasyLog.print((Throwable) e2);
        } catch (Throwable th) {
            EasyUtils.closeStream((Closeable) null);
            throw th;
        }
        EasyUtils.closeStream(inputStream);
        return 0;
    }
}
