package org.kobjects.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

public class Base64 {
    static final char[] charTab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    public static String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length, (StringBuffer) null).toString();
    }

    public static StringBuffer encode(byte[] bArr, int i, int i2, StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer((bArr.length * 3) / 2);
        }
        int i3 = i2 - 3;
        int i4 = i;
        loop0:
        while (true) {
            int i5 = 0;
            while (i4 <= i3) {
                int b = ((bArr[i4] & UByte.MAX_VALUE) << 16) | ((bArr[i4 + 1] & UByte.MAX_VALUE) << 8) | (bArr[i4 + 2] & UByte.MAX_VALUE);
                char[] cArr = charTab;
                stringBuffer.append(cArr[(b >> 18) & 63]);
                stringBuffer.append(cArr[(b >> 12) & 63]);
                stringBuffer.append(cArr[(b >> 6) & 63]);
                stringBuffer.append(cArr[b & 63]);
                i4 += 3;
                int i6 = i5 + 1;
                if (i5 >= 14) {
                    stringBuffer.append("\r\n");
                } else {
                    i5 = i6;
                }
            }
            break loop0;
        }
        int i7 = i + i2;
        if (i4 == i7 - 2) {
            int i8 = ((bArr[i4 + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i4] & UByte.MAX_VALUE) << 16);
            char[] cArr2 = charTab;
            stringBuffer.append(cArr2[(i8 >> 18) & 63]);
            stringBuffer.append(cArr2[(i8 >> 12) & 63]);
            stringBuffer.append(cArr2[(i8 >> 6) & 63]);
            stringBuffer.append("=");
        } else if (i4 == i7 - 1) {
            int i9 = (bArr[i4] & UByte.MAX_VALUE) << 16;
            char[] cArr3 = charTab;
            stringBuffer.append(cArr3[(i9 >> 18) & 63]);
            stringBuffer.append(cArr3[(i9 >> 12) & 63]);
            stringBuffer.append("==");
        }
        return stringBuffer;
    }

    static int decode(char c) {
        int i;
        if (c >= 'A' && c <= 'Z') {
            return c - 'A';
        }
        if (c >= 'a' && c <= 'z') {
            i = c - 'a';
        } else if (c >= '0' && c <= '9') {
            i = (c - '0') + 26;
        } else if (c == '+') {
            return 62;
        } else {
            if (c == '/') {
                return 63;
            }
            if (c == '=') {
                return 0;
            }
            throw new RuntimeException("unexpected code: " + c);
        }
        return i + 26;
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new RuntimeException();
        }
    }

    public static void decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        int i = 0;
        while (true) {
            if (i < length && str.charAt(i) <= ' ') {
                i++;
            } else if (i != length) {
                int i2 = i + 2;
                int i3 = i + 3;
                int decode = (decode(str.charAt(i)) << 18) + (decode(str.charAt(i + 1)) << 12) + (decode(str.charAt(i2)) << 6) + decode(str.charAt(i3));
                outputStream.write((decode >> 16) & 255);
                if (str.charAt(i2) != '=') {
                    outputStream.write((decode >> 8) & 255);
                    if (str.charAt(i3) != '=') {
                        outputStream.write(decode & 255);
                        i += 4;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}
