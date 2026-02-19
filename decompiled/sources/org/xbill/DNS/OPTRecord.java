package org.xbill.DNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xbill.DNS.utils.Base16;

public class OPTRecord extends Record {
    private static final long serialVersionUID = -6254521894809367938L;
    private List options;

    public static class Option {
        public final int code;
        public final byte[] data;

        public Option(int i, byte[] bArr) {
            this.code = Record.checkU8("option code", i);
            this.data = bArr;
        }

        public String toString() {
            return "{" + this.code + " <" + Base16.toString(this.data) + ">}";
        }
    }

    OPTRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new OPTRecord();
    }

    public OPTRecord(int i, int i2, int i3, int i4, List list) {
        super(Name.root, 41, i, 0);
        checkU16("payloadSize", i);
        checkU8("xrcode", i2);
        checkU8("version", i3);
        checkU16("flags", i4);
        this.ttl = (((long) i2) << 24) + (((long) i3) << 16) + ((long) i4);
        if (list != null) {
            this.options = new ArrayList(list);
        }
    }

    public OPTRecord(int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4, (List) null);
    }

    public OPTRecord(int i, int i2, int i3) {
        this(i, i2, i3, 0, (List) null);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        if (dNSInput.remaining() > 0) {
            this.options = new ArrayList();
        }
        while (dNSInput.remaining() > 0) {
            this.options.add(new Option(dNSInput.readU16(), dNSInput.readByteArray(dNSInput.readU16())));
        }
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        throw tokenizer.exception("no text format defined for OPT");
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        List list = this.options;
        if (list != null) {
            stringBuffer.append(list);
            stringBuffer.append(" ");
        }
        stringBuffer.append(" ; payload ");
        stringBuffer.append(getPayloadSize());
        stringBuffer.append(", xrcode ");
        stringBuffer.append(getExtendedRcode());
        stringBuffer.append(", version ");
        stringBuffer.append(getVersion());
        stringBuffer.append(", flags ");
        stringBuffer.append(getFlags());
        return stringBuffer.toString();
    }

    public int getPayloadSize() {
        return this.dclass;
    }

    public int getExtendedRcode() {
        return (int) (this.ttl >>> 24);
    }

    public int getVersion() {
        return (int) ((this.ttl >>> 16) & 255);
    }

    public int getFlags() {
        return (int) (this.ttl & 65535);
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        List<Option> list = this.options;
        if (list != null) {
            for (Option option : list) {
                dNSOutput.writeU16(option.code);
                dNSOutput.writeU16(option.data.length);
                dNSOutput.writeByteArray(option.data);
            }
        }
    }

    public List getOptions() {
        List list = this.options;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(list);
    }

    public List getOptions(int i) {
        List<Option> list = this.options;
        if (list == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = null;
        for (Option option : list) {
            if (option.code == i) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(option.data);
            }
        }
        return arrayList == null ? Collections.EMPTY_LIST : arrayList;
    }
}
