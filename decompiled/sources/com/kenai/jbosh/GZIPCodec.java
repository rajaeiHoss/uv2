package com.kenai.jbosh;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class GZIPCodec {
    private static final int BUFFER_SIZE = 512;

    public static String getID() {
        return "gzip";
    }

    private GZIPCodec() {
    }

    public static byte[] encode(byte[] bArr) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
            gZIPOutputStream.write(bArr);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream)) {
            byte[] buffer = new byte[512];
            int read;
            while ((read = gZIPInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, read);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
}
