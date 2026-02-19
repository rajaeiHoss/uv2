package org.kobjects.util;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class Util {
    public static OutputStream streamcopy(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i = Runtime.getRuntime().freeMemory() >= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED ? 16384 : 128;
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr, 0, i);
            if (read == -1) {
                inputStream.close();
                return outputStream;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static int indexOf(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public static String buildUrl(String str, String str2) {
        int indexOf = str2.indexOf(58);
        String str3 = "file:///";
        if (str2.startsWith("/") || indexOf == 1) {
            return str3 + str2;
        } else if (indexOf > 2 && indexOf < 6) {
            return str2;
        } else {
            if (str != null) {
                if (str.indexOf(58) == -1) {
                    str = str3 + str;
                }
                if (!str.endsWith("/")) {
                    str3 = str + "/";
                } else {
                    str3 = str;
                }
            }
            return str3 + str2;
        }
    }

    public static void sort(Object[] objArr, int i, int i2) {
        int i3;
        int i4 = i2 - i;
        if (i4 <= 2) {
            if (i4 == 2) {
                int i5 = i + 1;
                if (objArr[i].toString().compareTo(objArr[i5].toString()) > 0) {
                    Object obj = objArr[i];
                    objArr[i] = objArr[i5];
                    objArr[i5] = obj;
                }
            }
        } else if (i4 == 3) {
            int i6 = i + 2;
            sort(objArr, i, i6);
            sort(objArr, i + 1, i + 3);
            sort(objArr, i, i6);
        } else {
            int i7 = (i + i2) / 2;
            sort(objArr, i, i7);
            sort(objArr, i7, i2);
            Object[] objArr2 = new Object[i4];
            int i8 = i;
            int i9 = i7;
            for (int i10 = 0; i10 < i4; i10++) {
                i3 = i9;
                if (i8 == i7) {
                    i3 = i9 + 1;
                    objArr2[i10] = objArr[i9];
                } else if (i9 == i2 || objArr[i8].toString().compareTo(objArr[i9].toString()) < 0) {
                    objArr2[i10] = objArr[i8];
                    i8++;
                } else {
                    i3 = i9 + 1;
                    objArr2[i10] = objArr[i9];
                }
                i9 = i3;
            }
            System.arraycopy(objArr2, 0, objArr, i, i4);
        }
    }
}
