package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

final class AI013103decoder extends AI013x0xDecoder {
    AI013103decoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public void addWeightCode(StringBuffer stringBuffer, int i) {
        stringBuffer.append("(3103)");
    }

    /* access modifiers changed from: protected */
    public int checkWeight(int i) {
        return i;
    }
}
