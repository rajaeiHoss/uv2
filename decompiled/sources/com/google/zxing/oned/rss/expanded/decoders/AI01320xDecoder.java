package com.google.zxing.oned.rss.expanded.decoders;

import com.google.android.gms.search.SearchAuth;
import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public void addWeightCode(StringBuffer stringBuffer, int i) {
        stringBuffer.append(i < 10000 ? "(3202)" : "(3203)");
    }

    /* access modifiers changed from: protected */
    public int checkWeight(int i) {
        return i < 10000 ? i : i - SearchAuth.StatusCodes.AUTH_DISABLED;
    }
}
