package com.hjq.http.model;

import com.hjq.http.EasyLog;
import com.hjq.http.EasyUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileWrapper extends File {
    public FileWrapper(File file) {
        super(file.getPath());
    }

    public InputStream openInputStream() throws FileNotFoundException {
        return new FileInputStream(this);
    }

    public OutputStream openOutputStream() throws FileNotFoundException {
        return new FileOutputStream(this);
    }

    public static boolean createFolder(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            }
            file.delete();
        }
        return file.mkdirs();
    }

    public static String getFileMd5(InputStream inputStream) {
        DigestInputStream digestInputStream = null;
        if (inputStream == null) {
            return "";
        }
        try {
            digestInputStream = new DigestInputStream(inputStream, MessageDigest.getInstance("MD5"));
            do {
            } while (digestInputStream.read(new byte[262144]) > 0);
            byte[] digest = digestInputStream.getMessageDigest().digest();
            StringBuilder sb = new StringBuilder();
            int length = digest.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02X", new Object[]{Byte.valueOf(digest[i])}));
            }
            return sb.toString().toLowerCase();
        } catch (IOException | NoSuchAlgorithmException e) {
            EasyLog.print(e);
            return null;
        } finally {
            EasyUtils.closeStream(inputStream);
            EasyUtils.closeStream(digestInputStream);
        }
    }
}
