package com.google.zxing.client.result.optional;

import kotlin.UByte;

final class NDEFRecord {
    public static final String ACTION_WELL_KNOWN_TYPE = "act";
    public static final String SMART_POSTER_WELL_KNOWN_TYPE = "Sp";
    private static final int SUPPORTED_HEADER = 17;
    private static final int SUPPORTED_HEADER_MASK = 63;
    public static final String TEXT_WELL_KNOWN_TYPE = "T";
    public static final String URI_WELL_KNOWN_TYPE = "U";
    private final int header;
    private final byte[] payload;
    private final int totalRecordLength;
    private final String type;

    private NDEFRecord(int i, String str, byte[] bArr, int i2) {
        this.header = i;
        this.type = str;
        this.payload = bArr;
        this.totalRecordLength = i2;
    }

    static NDEFRecord readRecord(byte[] bArr, int i) {
        int b = bArr[i] & UByte.MAX_VALUE;
        if (((b ^ 17) & 63) != 0) {
            return null;
        }
        int b2 = bArr[i + 1] & UByte.MAX_VALUE;
        int i2 = bArr[i + 2] & UByte.MAX_VALUE;
        int i3 = i + 3;
        String bytesToString = AbstractNDEFResultParser.bytesToString(bArr, i3, b2, "US-ASCII");
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i3 + b2, bArr2, 0, i2);
        return new NDEFRecord(b, bytesToString, bArr2, b2 + 3 + i2);
    }

    /* access modifiers changed from: package-private */
    public byte[] getPayload() {
        return this.payload;
    }

    /* access modifiers changed from: package-private */
    public int getTotalRecordLength() {
        return this.totalRecordLength;
    }

    /* access modifiers changed from: package-private */
    public String getType() {
        return this.type;
    }

    /* access modifiers changed from: package-private */
    public boolean isMessageBegin() {
        return (this.header & 128) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isMessageEnd() {
        return (this.header & 64) != 0;
    }
}
