package org.xbill.DNS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.text.Typography;
import org.xbill.DNS.Tokenizer;
import org.xbill.DNS.utils.Base16;

public abstract class Record implements Cloneable, Comparable, Serializable {
    private static final DecimalFormat byteFormat;
    private static final long serialVersionUID = 2694906050116005466L;
    protected int dclass;
    protected Name name;
    protected long ttl;
    protected int type;

    public Name getAdditionalName() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public abstract Record getObject();

    /* access modifiers changed from: package-private */
    public abstract void rdataFromString(Tokenizer tokenizer, Name name2) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void rrFromWire(DNSInput dNSInput) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract String rrToString();

    /* access modifiers changed from: package-private */
    public abstract void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z);

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        byteFormat = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
    }

    protected Record() {
    }

    Record(Name name2, int i, int i2, long j) {
        if (name2.isAbsolute()) {
            Type.check(i);
            DClass.check(i2);
            TTL.check(j);
            this.name = name2;
            this.type = i;
            this.dclass = i2;
            this.ttl = j;
            return;
        }
        throw new RelativeNameException(name2);
    }

    private static final Record getEmptyRecord(Name name2, int i, int i2, long j, boolean z) {
        Record record;
        if (z) {
            Record proto = Type.getProto(i);
            if (proto != null) {
                record = proto.getObject();
            } else {
                record = new UNKRecord();
            }
        } else {
            record = new EmptyRecord();
        }
        record.name = name2;
        record.type = i;
        record.dclass = i2;
        record.ttl = j;
        return record;
    }

    private static Record newRecord(Name name2, int i, int i2, long j, int i3, DNSInput dNSInput) throws IOException {
        Record emptyRecord = getEmptyRecord(name2, i, i2, j, dNSInput != null);
        if (dNSInput != null) {
            if (dNSInput.remaining() >= i3) {
                dNSInput.setActive(i3);
                emptyRecord.rrFromWire(dNSInput);
                if (dNSInput.remaining() <= 0) {
                    dNSInput.clearActive();
                } else {
                    throw new WireParseException("invalid record length");
                }
            } else {
                throw new WireParseException("truncated record");
            }
        }
        return emptyRecord;
    }

    public static Record newRecord(Name name2, int i, int i2, long j, int i3, byte[] bArr) {
        if (name2.isAbsolute()) {
            Type.check(i);
            DClass.check(i2);
            TTL.check(j);
            try {
                return newRecord(name2, i, i2, j, i3, bArr != null ? new DNSInput(bArr) : null);
            } catch (IOException unused) {
                return null;
            }
        } else {
            throw new RelativeNameException(name2);
        }
    }

    public static Record newRecord(Name name2, int i, int i2, long j, byte[] bArr) {
        return newRecord(name2, i, i2, j, bArr.length, bArr);
    }

    public static Record newRecord(Name name2, int i, int i2, long j) {
        if (name2.isAbsolute()) {
            Type.check(i);
            DClass.check(i2);
            TTL.check(j);
            return getEmptyRecord(name2, i, i2, j, false);
        }
        throw new RelativeNameException(name2);
    }

    public static Record newRecord(Name name2, int i, int i2) {
        return newRecord(name2, i, i2, 0);
    }

    static Record fromWire(DNSInput dNSInput, int i, boolean z) throws IOException {
        Name name2 = new Name(dNSInput);
        int readU16 = dNSInput.readU16();
        int readU162 = dNSInput.readU16();
        if (i == 0) {
            return newRecord(name2, readU16, readU162);
        }
        long readU32 = dNSInput.readU32();
        int readU163 = dNSInput.readU16();
        if (readU163 != 0 || !z) {
            return newRecord(name2, readU16, readU162, readU32, readU163, dNSInput);
        }
        return newRecord(name2, readU16, readU162, readU32);
    }

    static Record fromWire(DNSInput dNSInput, int i) throws IOException {
        return fromWire(dNSInput, i, false);
    }

    public static Record fromWire(byte[] bArr, int i) throws IOException {
        return fromWire(new DNSInput(bArr), i, false);
    }

    /* access modifiers changed from: package-private */
    public void toWire(DNSOutput dNSOutput, int i, Compression compression) {
        this.name.toWire(dNSOutput, compression);
        dNSOutput.writeU16(this.type);
        dNSOutput.writeU16(this.dclass);
        if (i != 0) {
            dNSOutput.writeU32(this.ttl);
            int current = dNSOutput.current();
            dNSOutput.writeU16(0);
            rrToWire(dNSOutput, compression, false);
            dNSOutput.save();
            dNSOutput.jump(current);
            dNSOutput.writeU16((dNSOutput.current() - current) - 2);
            dNSOutput.restore();
        }
    }

    public byte[] toWire(int i) {
        DNSOutput dNSOutput = new DNSOutput();
        toWire(dNSOutput, i, (Compression) null);
        return dNSOutput.toByteArray();
    }

    private void toWireCanonical(DNSOutput dNSOutput, boolean z) {
        this.name.toWireCanonical(dNSOutput);
        dNSOutput.writeU16(this.type);
        dNSOutput.writeU16(this.dclass);
        if (z) {
            dNSOutput.writeU32(0);
        } else {
            dNSOutput.writeU32(this.ttl);
        }
        int current = dNSOutput.current();
        dNSOutput.writeU16(0);
        rrToWire(dNSOutput, (Compression) null, true);
        dNSOutput.save();
        dNSOutput.jump(current);
        dNSOutput.writeU16((dNSOutput.current() - current) - 2);
        dNSOutput.restore();
    }

    private byte[] toWireCanonical(boolean z) {
        DNSOutput dNSOutput = new DNSOutput();
        toWireCanonical(dNSOutput, z);
        return dNSOutput.toByteArray();
    }

    public byte[] toWireCanonical() {
        return toWireCanonical(false);
    }

    public byte[] rdataToWireCanonical() {
        DNSOutput dNSOutput = new DNSOutput();
        rrToWire(dNSOutput, (Compression) null, true);
        return dNSOutput.toByteArray();
    }

    public String rdataToString() {
        return rrToString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.name);
        if (stringBuffer.length() < 8) {
            stringBuffer.append("\t");
        }
        if (stringBuffer.length() < 16) {
            stringBuffer.append("\t");
        }
        stringBuffer.append("\t");
        if (Options.check("BINDTTL")) {
            stringBuffer.append(TTL.format(this.ttl));
        } else {
            stringBuffer.append(this.ttl);
        }
        stringBuffer.append("\t");
        if (this.dclass != 1 || !Options.check("noPrintIN")) {
            stringBuffer.append(DClass.string(this.dclass));
            stringBuffer.append("\t");
        }
        stringBuffer.append(Type.string(this.type));
        String rrToString = rrToString();
        if (!rrToString.equals("")) {
            stringBuffer.append("\t");
            stringBuffer.append(rrToString);
        }
        return stringBuffer.toString();
    }

    protected static byte[] byteArrayFromString(String str) throws TextParseException {
        boolean z;
        byte[] bytes = str.getBytes();
        int i = 0;
        while (true) {
            if (i >= bytes.length) {
                z = false;
                break;
            } else if (bytes[i] == 92) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i2 = 0;
            boolean z2 = false;
            int i3 = 0;
            for (int i4 = 0; i4 < bytes.length; i4++) {
                byte b = bytes[i4];
                if (z2) {
                    if (b >= 48 && b <= 57 && i2 < 3) {
                        i2++;
                        i3 = (i3 * 10) + (b - 48);
                        if (i3 > 255) {
                            throw new TextParseException("bad escape");
                        } else if (i2 >= 3) {
                            b = (byte) i3;
                        }
                    } else if (i2 > 0 && i2 < 3) {
                        throw new TextParseException("bad escape");
                    }
                    byteArrayOutputStream.write(b);
                    z2 = false;
                } else if (bytes[i4] == 92) {
                    i2 = 0;
                    z2 = true;
                    i3 = 0;
                } else {
                    byteArrayOutputStream.write(bytes[i4]);
                }
            }
            if (i2 > 0 && i2 < 3) {
                throw new TextParseException("bad escape");
            } else if (byteArrayOutputStream.toByteArray().length <= 255) {
                return byteArrayOutputStream.toByteArray();
            } else {
                throw new TextParseException("text string too long");
            }
        } else if (bytes.length <= 255) {
            return bytes;
        } else {
            throw new TextParseException("text string too long");
        }
    }

    protected static String byteArrayToString(byte[] bArr, boolean z) {
        StringBuffer stringBuffer = new StringBuffer();
        if (z) {
            stringBuffer.append(Typography.quote);
        }
        for (byte b : bArr) {
            int b2 = b & UByte.MAX_VALUE;
            if (b2 < 32 || b2 >= Byte.MAX_VALUE) {
                stringBuffer.append('\\');
                stringBuffer.append(byteFormat.format((long) b2));
            } else if (b2 == 34 || b2 == 92) {
                stringBuffer.append('\\');
                stringBuffer.append((char) b2);
            } else {
                stringBuffer.append((char) b2);
            }
        }
        if (z) {
            stringBuffer.append(Typography.quote);
        }
        return stringBuffer.toString();
    }

    protected static String unknownToString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\\# ");
        stringBuffer.append(bArr.length);
        stringBuffer.append(" ");
        stringBuffer.append(Base16.toString(bArr));
        return stringBuffer.toString();
    }

    public static Record fromString(Name name2, int i, int i2, long j, Tokenizer tokenizer, Name name3) throws IOException {
        if (name2.isAbsolute()) {
            Type.check(i);
            DClass.check(i2);
            TTL.check(j);
            Tokenizer.Token token = tokenizer.get();
            if (token.type != 3 || !token.value.equals("\\#")) {
                tokenizer.unget();
                Record emptyRecord = getEmptyRecord(name2, i, i2, j, true);
                emptyRecord.rdataFromString(tokenizer, name3);
                Tokenizer.Token token2 = tokenizer.get();
                if (token2.type == 1 || token2.type == 0) {
                    return emptyRecord;
                }
                throw tokenizer.exception("unexpected tokens at end of record");
            }
            int uInt16 = tokenizer.getUInt16();
            byte[] hex = tokenizer.getHex();
            if (hex == null) {
                hex = new byte[0];
            }
            if (uInt16 == hex.length) {
                return newRecord(name2, i, i2, j, uInt16, new DNSInput(hex));
            }
            throw tokenizer.exception("invalid unknown RR encoding: length mismatch");
        }
        throw new RelativeNameException(name2);
    }

    public static Record fromString(Name name2, int i, int i2, long j, String str, Name name3) throws IOException {
        return fromString(name2, i, i2, j, new Tokenizer(str), name3);
    }

    public Name getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int getRRsetType() {
        int i = this.type;
        return i == 46 ? ((RRSIGRecord) this).getTypeCovered() : i;
    }

    public int getDClass() {
        return this.dclass;
    }

    public long getTTL() {
        return this.ttl;
    }

    public boolean sameRRset(Record record) {
        return getRRsetType() == record.getRRsetType() && this.dclass == record.dclass && this.name.equals(record.name);
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Record)) {
            Record record = (Record) obj;
            if (this.type == record.type && this.dclass == record.dclass && this.name.equals(record.name)) {
                return Arrays.equals(rdataToWireCanonical(), record.rdataToWireCanonical());
            }
        }
        return false;
    }

    public int hashCode() {
        byte[] wireCanonical = toWireCanonical(true);
        int i = 0;
        for (byte b : wireCanonical) {
            i += (i << 3) + (b & UByte.MAX_VALUE);
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public Record cloneRecord() {
        try {
            return (Record) clone();
        } catch (CloneNotSupportedException unused) {
            throw new IllegalStateException();
        }
    }

    public Record withName(Name name2) {
        if (name2.isAbsolute()) {
            Record cloneRecord = cloneRecord();
            cloneRecord.name = name2;
            return cloneRecord;
        }
        throw new RelativeNameException(name2);
    }

    /* access modifiers changed from: package-private */
    public Record withDClass(int i, long j) {
        Record cloneRecord = cloneRecord();
        cloneRecord.dclass = i;
        cloneRecord.ttl = j;
        return cloneRecord;
    }

    /* access modifiers changed from: package-private */
    public void setTTL(long j) {
        this.ttl = j;
    }

    public int compareTo(Object obj) {
        Record record = (Record) obj;
        int i = 0;
        if (this == record) {
            return 0;
        }
        int compareTo = this.name.compareTo(record.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int i2 = this.dclass - record.dclass;
        if (i2 != 0) {
            return i2;
        }
        int i3 = this.type - record.type;
        if (i3 != 0) {
            return i3;
        }
        byte[] rdataToWireCanonical = rdataToWireCanonical();
        byte[] rdataToWireCanonical2 = record.rdataToWireCanonical();
        while (i < rdataToWireCanonical.length && i < rdataToWireCanonical2.length) {
            int i4 = (rdataToWireCanonical[i] & UByte.MAX_VALUE) - (rdataToWireCanonical2[i] & UByte.MAX_VALUE);
            if (i4 != 0) {
                return i4;
            }
            i++;
        }
        return rdataToWireCanonical.length - rdataToWireCanonical2.length;
    }

    static int checkU8(String str, int i) {
        if (i >= 0 && i <= 255) {
            return i;
        }
        throw new IllegalArgumentException("\"" + str + "\" " + i + " must be an unsigned 8 " + "bit value");
    }

    static int checkU16(String str, int i) {
        if (i >= 0 && i <= 65535) {
            return i;
        }
        throw new IllegalArgumentException("\"" + str + "\" " + i + " must be an unsigned 16 " + "bit value");
    }

    static long checkU32(String str, long j) {
        if (j >= 0 && j <= 4294967295L) {
            return j;
        }
        throw new IllegalArgumentException("\"" + str + "\" " + j + " must be an unsigned 32 " + "bit value");
    }

    static Name checkName(String str, Name name2) {
        if (name2.isAbsolute()) {
            return name2;
        }
        throw new RelativeNameException(name2);
    }
}
