package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class Code128Writer extends UPCEANWriter {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Can only encode CODE_128, but got ");
        stringBuffer.append(barcodeFormat);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public byte[] encode(String str) {
        int length = str.length();
        if (length <= 80) {
            int i = 35;
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                for (int i4 : Code128Reader.CODE_PATTERNS[str.charAt(i3) - ' ']) {
                    i += i4;
                }
            }
            byte[] bArr = new byte[i];
            int i5 = 104;
            int appendPattern = appendPattern(bArr, 0, Code128Reader.CODE_PATTERNS[104], 1);
            while (i2 < length) {
                int i6 = i2 + 1;
                i5 += (str.charAt(i2) - ' ') * i6;
                appendPattern += appendPattern(bArr, appendPattern, Code128Reader.CODE_PATTERNS[str.charAt(i2) - ' '], 1);
                i2 = i6;
            }
            appendPattern(bArr, appendPattern + appendPattern(bArr, appendPattern, Code128Reader.CODE_PATTERNS[i5 % 103], 1), Code128Reader.CODE_PATTERNS[106], 1);
            return bArr;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Requested contents should be less than 80 digits long, but got ");
        stringBuffer.append(length);
        throw new IllegalArgumentException(stringBuffer.toString());
    }
}
