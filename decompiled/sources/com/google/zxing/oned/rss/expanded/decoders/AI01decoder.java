package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01decoder extends AbstractExpandedDecoder {
    protected static final int gtinSize = 40;

    AI01decoder(BitArray bitArray) {
        super(bitArray);
    }

    private static void appendCheckDigit(StringBuffer stringBuffer, int i) {
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 13; i4++) {
            int charAt = stringBuffer.charAt(i4 + i) - '0';
            if ((i4 & 1) == 0) {
                charAt *= 3;
            }
            i3 += charAt;
        }
        int i5 = 10 - (i3 % 10);
        if (i5 != 10) {
            i2 = i5;
        }
        stringBuffer.append(i2);
    }

    /* access modifiers changed from: protected */
    public void encodeCompressedGtin(StringBuffer stringBuffer, int i) {
        stringBuffer.append("(01)");
        int length = stringBuffer.length();
        stringBuffer.append('9');
        encodeCompressedGtinWithoutAI(stringBuffer, i, length);
    }

    /* access modifiers changed from: protected */
    public void encodeCompressedGtinWithoutAI(StringBuffer stringBuffer, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int extractNumericValueFromBitArray = this.generalDecoder.extractNumericValueFromBitArray((i3 * 10) + i, 10);
            if (extractNumericValueFromBitArray / 100 == 0) {
                stringBuffer.append('0');
            }
            if (extractNumericValueFromBitArray / 10 == 0) {
                stringBuffer.append('0');
            }
            stringBuffer.append(extractNumericValueFromBitArray);
        }
        appendCheckDigit(stringBuffer, i2);
    }
}
