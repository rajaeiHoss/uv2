package org.xbill.DNS;

import java.io.IOException;
import java.util.Random;

public class Header implements Cloneable {
    public static final int LENGTH = 12;
    private static Random random = new Random();
    private int[] counts;
    private int flags;
    private int id;

    private void init() {
        this.counts = new int[4];
        this.flags = 0;
        this.id = -1;
    }

    public Header(int i) {
        init();
        setID(i);
    }

    public Header() {
        init();
    }

    Header(DNSInput dNSInput) throws IOException {
        this(dNSInput.readU16());
        this.flags = dNSInput.readU16();
        int i = 0;
        while (true) {
            int[] iArr = this.counts;
            if (i < iArr.length) {
                iArr[i] = dNSInput.readU16();
                i++;
            } else {
                return;
            }
        }
    }

    public Header(byte[] bArr) throws IOException {
        this(new DNSInput(bArr));
    }

    /* access modifiers changed from: package-private */
    public void toWire(DNSOutput dNSOutput) {
        dNSOutput.writeU16(getID());
        dNSOutput.writeU16(this.flags);
        int i = 0;
        while (true) {
            int[] iArr = this.counts;
            if (i < iArr.length) {
                dNSOutput.writeU16(iArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public byte[] toWire() {
        DNSOutput dNSOutput = new DNSOutput();
        toWire(dNSOutput);
        return dNSOutput.toByteArray();
    }

    private static boolean validFlag(int i) {
        return i >= 0 && i <= 15 && Flags.isFlag(i);
    }

    private static void checkFlag(int i) {
        if (!validFlag(i)) {
            throw new IllegalArgumentException("invalid flag bit " + i);
        }
    }

    public void setFlag(int i) {
        checkFlag(i);
        this.flags = (1 << (15 - i)) | this.flags;
    }

    public void unsetFlag(int i) {
        checkFlag(i);
        this.flags = (~(1 << (15 - i))) & this.flags;
    }

    public boolean getFlag(int i) {
        checkFlag(i);
        return ((1 << (15 - i)) & this.flags) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean[] getFlags() {
        boolean[] zArr = new boolean[16];
        for (int i = 0; i < 16; i++) {
            if (validFlag(i)) {
                zArr[i] = getFlag(i);
            }
        }
        return zArr;
    }

    public int getID() {
        int i;
        int i2 = this.id;
        if (i2 >= 0) {
            return i2;
        }
        synchronized (this) {
            if (this.id < 0) {
                this.id = random.nextInt(65535);
            }
            i = this.id;
        }
        return i;
    }

    public void setID(int i) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("DNS message ID " + i + " is out of range");
        }
        this.id = i;
    }

    public void setRcode(int i) {
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException("DNS Rcode " + i + " is out of range");
        }
        int i2 = this.flags & -16;
        this.flags = i2;
        this.flags = i | i2;
    }

    public int getRcode() {
        return this.flags & 15;
    }

    public void setOpcode(int i) {
        if (i < 0 || i > 15) {
            throw new IllegalArgumentException("DNS Opcode " + i + "is out of range");
        }
        int i2 = this.flags & 34815;
        this.flags = i2;
        this.flags = (i << 11) | i2;
    }

    public int getOpcode() {
        return (this.flags >> 11) & 15;
    }

    /* access modifiers changed from: package-private */
    public void setCount(int i, int i2) {
        if (i2 < 0 || i2 > 65535) {
            throw new IllegalArgumentException("DNS section count " + i2 + " is out of range");
        }
        this.counts[i] = i2;
    }

    /* access modifiers changed from: package-private */
    public void incCount(int i) {
        int[] iArr = this.counts;
        if (iArr[i] != 65535) {
            iArr[i] = iArr[i] + 1;
            return;
        }
        throw new IllegalStateException("DNS section count cannot be incremented");
    }

    /* access modifiers changed from: package-private */
    public void decCount(int i) {
        int[] iArr = this.counts;
        if (iArr[i] != 0) {
            iArr[i] = iArr[i] - 1;
            return;
        }
        throw new IllegalStateException("DNS section count cannot be decremented");
    }

    public int getCount(int i) {
        return this.counts[i];
    }

    public String printFlags() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            if (validFlag(i) && getFlag(i)) {
                stringBuffer.append(Flags.string(i));
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String toStringWithRcode(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(";; ->>HEADER<<- ");
        stringBuffer.append("opcode: " + Opcode.string(getOpcode()));
        stringBuffer.append(", status: " + Rcode.string(i));
        stringBuffer.append(", id: " + getID());
        stringBuffer.append("\n");
        stringBuffer.append(";; flags: " + printFlags());
        stringBuffer.append("; ");
        for (int i2 = 0; i2 < 4; i2++) {
            stringBuffer.append(Section.string(i2) + ": " + getCount(i2) + " ");
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return toStringWithRcode(getRcode());
    }

    public Object clone() {
        Header header = new Header();
        header.id = this.id;
        header.flags = this.flags;
        int[] iArr = this.counts;
        System.arraycopy(iArr, 0, header.counts, 0, iArr.length);
        return header;
    }
}
