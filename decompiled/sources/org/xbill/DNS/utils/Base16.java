package org.xbill.DNS.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import kotlin.UByte;

public class Base16 {
    private static final String Base16 = "0123456789ABCDEF";

    private Base16() {
    }

    public static String toString(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (byte b : bArr) {
            short s = (short) (b & UByte.MAX_VALUE);
            byteArrayOutputStream.write(Base16.charAt((byte) (s >> 4)));
            byteArrayOutputStream.write(Base16.charAt((byte) (s & 15)));
        }
        return new String(byteArrayOutputStream.toByteArray());
    }

    public static byte[] fromString(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (!Character.isWhitespace((char) bytes[i])) {
                byteArrayOutputStream.write(bytes[i]);
            }
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (byteArray.length % 2 != 0) {
            return null;
        }
        byteArrayOutputStream.reset();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (int i2 = 0; i2 < byteArray.length; i2 += 2) {
            try {
                dataOutputStream.writeByte((((byte) Base16.indexOf(Character.toUpperCase((char) byteArray[i2]))) << 4) + ((byte) Base16.indexOf(Character.toUpperCase((char) byteArray[i2 + 1]))));
            } catch (IOException unused) {
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}
