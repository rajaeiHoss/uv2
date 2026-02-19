package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

final class UPCEANExtensionSupport {
    private static final int[] CHECK_DIGIT_ENCODINGS = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private static final int[] EXTENSION_START_PATTERN = {1, 1, 2};
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuffer decodeRowStringBuffer = new StringBuffer();

    UPCEANExtensionSupport() {
    }

    private static int determineCheckDigit(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == CHECK_DIGIT_ENCODINGS[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int extensionChecksum(String str) {
        int length = str.length();
        int i = 0;
        for (int i2 = length - 2; i2 >= 0; i2 -= 2) {
            i += str.charAt(i2) - '0';
        }
        int i3 = i * 3;
        for (int i4 = length - 1; i4 >= 0; i4 -= 2) {
            i3 += str.charAt(i4) - '0';
        }
        return (i3 * 3) % 10;
    }

    private static Integer parseExtension2String(String str) {
        return Integer.valueOf(str);
    }

    private static String parseExtension5String(String str) {
        String str2;
        char charAt = str.charAt(0);
        if (charAt == '0') {
            str2 = "拢";
        } else if (charAt == '5') {
            str2 = "$";
        } else if (charAt != '9') {
            str2 = "";
        } else if ("99991".equals(str)) {
            return "0.00";
        } else {
            if ("99990".equals(str)) {
                return "Used";
            }
            str2 = null;
        }
        int parseInt = Integer.parseInt(str.substring(1));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(parseInt / 100);
        stringBuffer.append('.');
        stringBuffer.append(parseInt % 100);
        return stringBuffer.toString();
    }

    private static Hashtable parseExtensionString(String str) {
        Object obj;
        ResultMetadataType resultMetadataType;
        int length = str.length();
        if (length == 2) {
            resultMetadataType = ResultMetadataType.ISSUE_NUMBER;
            obj = parseExtension2String(str);
        } else if (length != 5) {
            return null;
        } else {
            resultMetadataType = ResultMetadataType.SUGGESTED_PRICE;
            obj = parseExtension5String(str);
        }
        if (obj == null) {
            return null;
        }
        Hashtable hashtable = new Hashtable(1);
        hashtable.put(resultMetadataType, obj);
        return hashtable;
    }

    /* access modifiers changed from: package-private */
    public int decodeMiddle(BitArray bitArray, int[] iArr, StringBuffer stringBuffer) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 5 && i < size; i3++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, UPCEANReader.L_AND_G_PATTERNS);
            stringBuffer.append((char) ((decodeDigit % 10) + 48));
            for (int i4 : iArr2) {
                i += i4;
            }
            if (decodeDigit >= 10) {
                i2 |= 1 << (4 - i3);
            }
            if (i3 != 4) {
                while (i < size && !bitArray.get(i)) {
                    i++;
                }
                while (i < size && bitArray.get(i)) {
                    i++;
                }
            }
        }
        if (stringBuffer.length() == 5) {
            if (extensionChecksum(stringBuffer.toString()) == determineCheckDigit(i2)) {
                return i;
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* access modifiers changed from: package-private */
    public Result decodeRow(int i, BitArray bitArray, int i2) throws NotFoundException {
        int[] findGuardPattern = UPCEANReader.findGuardPattern(bitArray, i2, false, EXTENSION_START_PATTERN);
        StringBuffer stringBuffer = this.decodeRowStringBuffer;
        stringBuffer.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, findGuardPattern, stringBuffer);
        String stringBuffer2 = stringBuffer.toString();
        Hashtable parseExtensionString = parseExtensionString(stringBuffer2);
        float f = (float) i;
        Result result = new Result(stringBuffer2, (byte[]) null, new ResultPoint[]{new ResultPoint(((float) (findGuardPattern[0] + findGuardPattern[1])) / 2.0f, f), new ResultPoint((float) decodeMiddle, f)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }
}
