package org.xbill.DNS;

import kotlin.UByte;

public class DNSInput {
    private byte[] array;
    private int end;
    private int pos = 0;
    private int saved_end;
    private int saved_pos;

    public DNSInput(byte[] bArr) {
        this.array = bArr;
        this.end = bArr.length;
        this.saved_pos = -1;
        this.saved_end = -1;
    }

    public int current() {
        return this.pos;
    }

    public int remaining() {
        return this.end - this.pos;
    }

    private void require(int i) throws WireParseException {
        if (i > remaining()) {
            throw new WireParseException("end of input");
        }
    }

    public void setActive(int i) {
        int length = this.array.length;
        int i2 = this.pos;
        if (i <= length - i2) {
            this.end = i2 + i;
            return;
        }
        throw new IllegalArgumentException("cannot set active region past end of input");
    }

    public void clearActive() {
        this.end = this.array.length;
    }

    public void jump(int i) {
        byte[] bArr = this.array;
        if (i < bArr.length) {
            this.pos = i;
            this.end = bArr.length;
            return;
        }
        throw new IllegalArgumentException("cannot jump past end of input");
    }

    public void save() {
        this.saved_pos = this.pos;
        this.saved_end = this.end;
    }

    public void restore() {
        int i = this.saved_pos;
        if (i >= 0) {
            this.pos = i;
            this.end = this.saved_end;
            this.saved_pos = -1;
            this.saved_end = -1;
            return;
        }
        throw new IllegalStateException("no previous state");
    }

    public int readU8() throws WireParseException {
        require(1);
        byte[] bArr = this.array;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    public int readU16() throws WireParseException {
        require(2);
        byte[] bArr = this.array;
        int i = this.pos;
        int i2 = i + 1;
        this.pos = i2;
        int b = bArr[i] & UByte.MAX_VALUE;
        this.pos = i2 + 1;
        return (b << 8) + (bArr[i2] & UByte.MAX_VALUE);
    }

    public long readU32() throws WireParseException {
        require(4);
        byte[] bArr = this.array;
        int i = this.pos;
        int i2 = i + 1;
        this.pos = i2;
        int b = bArr[i] & UByte.MAX_VALUE;
        int i3 = i2 + 1;
        this.pos = i3;
        int b2 = bArr[i2] & UByte.MAX_VALUE;
        int i4 = i3 + 1;
        this.pos = i4;
        int b3 = bArr[i3] & UByte.MAX_VALUE;
        this.pos = i4 + 1;
        return (((long) b) << 24) + ((long) (b2 << 16)) + ((long) (b3 << 8)) + ((long) (bArr[i4] & UByte.MAX_VALUE));
    }

    public void readByteArray(byte[] bArr, int i, int i2) throws WireParseException {
        require(i2);
        System.arraycopy(this.array, this.pos, bArr, i, i2);
        this.pos += i2;
    }

    public byte[] readByteArray(int i) throws WireParseException {
        require(i);
        byte[] bArr = new byte[i];
        System.arraycopy(this.array, this.pos, bArr, 0, i);
        this.pos += i;
        return bArr;
    }

    public byte[] readByteArray() {
        int remaining = remaining();
        byte[] bArr = new byte[remaining];
        System.arraycopy(this.array, this.pos, bArr, 0, remaining);
        this.pos += remaining;
        return bArr;
    }

    public byte[] readCountedString() throws WireParseException {
        require(1);
        byte[] bArr = this.array;
        int i = this.pos;
        this.pos = i + 1;
        return readByteArray(bArr[i] & UByte.MAX_VALUE);
    }
}
