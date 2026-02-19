package com.kenai.jbosh;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

final class ZLIBCodec {
    private static final int BUFFER_SIZE = 512;

    public static String getID() {
        return "deflate";
    }

    private ZLIBCodec() {
    }

    public static byte[] encode(byte[] bArr) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream)) {
            deflaterOutputStream.write(bArr);
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static byte[] decode(byte[] bArr) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream)) {
            byte[] buffer = new byte[512];
            int read;
            while ((read = inflaterInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, read);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }
}
