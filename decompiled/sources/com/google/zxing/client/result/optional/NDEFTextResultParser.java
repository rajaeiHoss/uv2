package com.google.zxing.client.result.optional;

import com.google.zxing.Result;
import com.google.zxing.client.result.TextParsedResult;
import kotlin.jvm.internal.ByteCompanionObject;

final class NDEFTextResultParser extends AbstractNDEFResultParser {
    NDEFTextResultParser() {
    }

    static String[] decodeTextPayload(byte[] bArr) {
        byte b = bArr[0];
        boolean z = (b & ByteCompanionObject.MIN_VALUE) != 0;
        int b2 = b & 31;
        return new String[]{bytesToString(bArr, 1, b2, "US-ASCII"), bytesToString(bArr, b2 + 1, (bArr.length - b2) - 1, z ? "UTF-16" : "UTF8")};
    }

    public static TextParsedResult parse(Result result) {
        NDEFRecord readRecord;
        byte[] rawBytes = result.getRawBytes();
        if (rawBytes == null || (readRecord = NDEFRecord.readRecord(rawBytes, 0)) == null || !readRecord.isMessageBegin() || !readRecord.isMessageEnd() || !readRecord.getType().equals(NDEFRecord.TEXT_WELL_KNOWN_TYPE)) {
            return null;
        }
        String[] decodeTextPayload = decodeTextPayload(readRecord.getPayload());
        return new TextParsedResult(decodeTextPayload[0], decodeTextPayload[1]);
    }
}
