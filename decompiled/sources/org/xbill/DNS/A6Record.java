package org.xbill.DNS;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class A6Record extends Record {
    private static final long serialVersionUID = -8815026887337346789L;
    private Name prefix;
    private int prefixBits;
    private InetAddress suffix;

    A6Record() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new A6Record();
    }

    public A6Record(Name name, int i, long j, int i2, InetAddress inetAddress, Name name2) {
        super(name, 38, i, j);
        this.prefixBits = checkU8("prefixBits", i2);
        if (inetAddress == null || Address.familyOf(inetAddress) == 2) {
            this.suffix = inetAddress;
            if (name2 != null) {
                this.prefix = checkName("prefix", name2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("invalid IPv6 address");
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        int readU8 = dNSInput.readU8();
        this.prefixBits = readU8;
        int i = ((128 - readU8) + 7) / 8;
        if (readU8 < 128) {
            byte[] bArr = new byte[16];
            dNSInput.readByteArray(bArr, 16 - i, i);
            this.suffix = InetAddress.getByAddress(bArr);
        }
        if (this.prefixBits > 0) {
            this.prefix = new Name(dNSInput);
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        int uInt8 = tokenizer.getUInt8();
        this.prefixBits = uInt8;
        if (uInt8 <= 128) {
            if (uInt8 < 128) {
                String string = tokenizer.getString();
                try {
                    this.suffix = Address.getByAddress(string, 2);
                } catch (UnknownHostException unused) {
                    throw tokenizer.exception("invalid IPv6 address: " + string);
                }
            }
            if (this.prefixBits > 0) {
                this.prefix = tokenizer.getName(name);
                return;
            }
            return;
        }
        throw tokenizer.exception("prefix bits must be [0..128]");
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.prefixBits);
        if (this.suffix != null) {
            stringBuffer.append(" ");
            stringBuffer.append(this.suffix.getHostAddress());
        }
        if (this.prefix != null) {
            stringBuffer.append(" ");
            stringBuffer.append(this.prefix);
        }
        return stringBuffer.toString();
    }

    public int getPrefixBits() {
        return this.prefixBits;
    }

    public InetAddress getSuffix() {
        return this.suffix;
    }

    public Name getPrefix() {
        return this.prefix;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU8(this.prefixBits);
        InetAddress inetAddress = this.suffix;
        if (inetAddress != null) {
            int i = ((128 - this.prefixBits) + 7) / 8;
            dNSOutput.writeByteArray(inetAddress.getAddress(), 16 - i, i);
        }
        Name name = this.prefix;
        if (name != null) {
            name.toWire(dNSOutput, (Compression) null, z);
        }
    }
}
