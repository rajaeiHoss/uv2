package org.xbill.DNS;

import java.io.IOException;
import java.util.BitSet;
import org.xbill.DNS.Tokenizer;

public class NXTRecord extends Record {
    private static final long serialVersionUID = -8851454400765507520L;
    private BitSet bitmap;
    private Name next;

    NXTRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new NXTRecord();
    }

    public NXTRecord(Name name, int i, long j, Name name2, BitSet bitSet) {
        super(name, 30, i, j);
        this.next = checkName("next", name2);
        this.bitmap = bitSet;
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.next = new Name(dNSInput);
        this.bitmap = new BitSet();
        int remaining = dNSInput.remaining();
        for (int i = 0; i < remaining; i++) {
            int readU8 = dNSInput.readU8();
            for (int i2 = 0; i2 < 8; i2++) {
                if (((1 << (7 - i2)) & readU8) != 0) {
                    this.bitmap.set((i * 8) + i2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        Tokenizer.Token token;
        this.next = tokenizer.getName(name);
        this.bitmap = new BitSet();
        while (true) {
            token = tokenizer.get();
            if (!token.isString()) {
                tokenizer.unget();
                return;
            }
            int value = Type.value(token.value, true);
            if (value <= 0 || value > 128) {
                throw tokenizer.exception("Invalid type: " + token.value);
            } else {
                this.bitmap.set(value);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.next);
        int length = this.bitmap.length();
        for (short s = 0; s < length; s = (short) (s + 1)) {
            if (this.bitmap.get(s)) {
                stringBuffer.append(" ");
                stringBuffer.append(Type.string(s));
            }
        }
        return stringBuffer.toString();
    }

    public Name getNext() {
        return this.next;
    }

    public BitSet getBitmap() {
        return this.bitmap;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        this.next.toWire(dNSOutput, (Compression) null, z);
        int length = this.bitmap.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i |= this.bitmap.get(i2) ? 1 << (7 - (i2 % 8)) : 0;
            if (i2 % 8 == 7 || i2 == length - 1) {
                dNSOutput.writeU8(i);
                i = 0;
            }
        }
    }
}
