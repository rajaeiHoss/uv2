package org.xbill.DNS.utils;

import kotlin.UByte;

public class HexDump {
    private static final char[] hex = "0123456789ABCDEF".toCharArray();

    public static String dump(String str, byte[] bArr, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i2 + "b");
        if (str != null) {
            stringBuffer.append(" (" + str + ")");
        }
        stringBuffer.append(':');
        int length = (stringBuffer.toString().length() + 8) & -8;
        stringBuffer.append(9);
        int i3 = (80 - length) / 3;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 != 0 && i4 % i3 == 0) {
                stringBuffer.append(10);
                for (int i5 = 0; i5 < length / 8; i5++) {
                    stringBuffer.append(9);
                }
            }
            int b = bArr[i4 + i] & UByte.MAX_VALUE;
            char[] cArr = hex;
            stringBuffer.append(cArr[b >> 4]);
            stringBuffer.append(cArr[b & 15]);
            stringBuffer.append(' ');
        }
        stringBuffer.append(10);
        return stringBuffer.toString();
    }

    public static String dump(String str, byte[] bArr) {
        return dump(str, bArr, 0, bArr.length);
    }
}
