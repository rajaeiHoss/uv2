package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int dateSize = 16;
    private static final int headerSize = 8;
    private static final int weightSize = 20;
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    private void encodeCompressedDate(StringBuffer stringBuffer, int i) {
        int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray(i, 16);
        if (extractNumericValueFromBitArray != 38400) {
            stringBuffer.append('(');
            stringBuffer.append(this.dateCode);
            stringBuffer.append(')');
            int i2 = extractNumericValueFromBitArray % 32;
            int i3 = extractNumericValueFromBitArray / 32;
            int i4 = (i3 % 12) + 1;
            int i5 = i3 / 12;
            if (i5 / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i5);
            if (i4 / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i4);
            if (i2 / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void addWeightCode(StringBuffer stringBuffer, int i) {
        stringBuffer.append('(');
        stringBuffer.append(this.firstAIdigits);
        stringBuffer.append(i / 100000);
        stringBuffer.append(')');
    }

    /* access modifiers changed from: protected */
    public int checkWeight(int i) {
        return i % 100000;
    }

    public String parseInformation() throws NotFoundException {
        if (this.information.size == 84) {
            StringBuffer stringBuffer = new StringBuffer();
            encodeCompressedGtin(stringBuffer, 8);
            encodeCompressedWeight(stringBuffer, 48, 20);
            encodeCompressedDate(stringBuffer, 68);
            return stringBuffer.toString();
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
