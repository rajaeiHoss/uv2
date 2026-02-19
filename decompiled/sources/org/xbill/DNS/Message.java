package org.xbill.DNS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Message implements Cloneable {
    public static final int MAXLENGTH = 65535;
    static final int TSIG_FAILED = 4;
    static final int TSIG_INTERMEDIATE = 2;
    static final int TSIG_SIGNED = 3;
    static final int TSIG_UNSIGNED = 0;
    static final int TSIG_VERIFIED = 1;
    private static RRset[] emptyRRsetArray = new RRset[0];
    private static Record[] emptyRecordArray = new Record[0];
    private Header header;
    private TSIGRecord querytsig;
    private List[] sections;
    int sig0start;
    private int size;
    int tsigState;
    private int tsigerror;
    private TSIG tsigkey;
    int tsigstart;

    private Message(Header header2) {
        this.sections = new List[4];
        this.header = header2;
    }

    public Message(int i) {
        this(new Header(i));
    }

    public Message() {
        this(new Header());
    }

    public static Message newQuery(Record record) {
        Message message = new Message();
        message.header.setOpcode(0);
        message.header.setFlag(7);
        message.addRecord(record, 0);
        return message;
    }

    public static Message newUpdate(Name name) {
        return new Update(name);
    }

    Message(DNSInput dNSInput) throws IOException {
        this(new Header(dNSInput));
        boolean z = this.header.getOpcode() == 5;
        boolean flag = this.header.getFlag(6);
        int i = 0;
        while (i < 4) {
            try {
                int count = this.header.getCount(i);
                if (count > 0) {
                    this.sections[i] = new ArrayList(count);
                }
                for (int i2 = 0; i2 < count; i2++) {
                    int current = dNSInput.current();
                    Record fromWire = Record.fromWire(dNSInput, i, z);
                    this.sections[i].add(fromWire);
                    if (fromWire.getType() == 250) {
                        this.tsigstart = current;
                    }
                    if (fromWire.getType() == 24 && ((SIGRecord) fromWire).getTypeCovered() == 0) {
                        this.sig0start = current;
                    }
                }
                i++;
            } catch (WireParseException e) {
                if (!flag) {
                    throw e;
                }
            }
        }
        this.size = dNSInput.current();
    }

    public Message(byte[] bArr) throws IOException {
        this(new DNSInput(bArr));
    }

    public void setHeader(Header header2) {
        this.header = header2;
    }

    public Header getHeader() {
        return this.header;
    }

    public void addRecord(Record record, int i) {
        List[] listArr = this.sections;
        if (listArr[i] == null) {
            listArr[i] = new LinkedList();
        }
        this.header.incCount(i);
        this.sections[i].add(record);
    }

    public boolean removeRecord(Record record, int i) {
        List[] listArr = this.sections;
        if (listArr[i] == null || !listArr[i].remove(record)) {
            return false;
        }
        this.header.decCount(i);
        return true;
    }

    public void removeAllRecords(int i) {
        this.sections[i] = null;
        this.header.setCount(i, 0);
    }

    public boolean findRecord(Record record, int i) {
        List[] listArr = this.sections;
        return listArr[i] != null && listArr[i].contains(record);
    }

    public boolean findRecord(Record record) {
        for (int i = 1; i <= 3; i++) {
            List[] listArr = this.sections;
            if (listArr[i] != null && listArr[i].contains(record)) {
                return true;
            }
        }
        return false;
    }

    public boolean findRRset(Name name, int i, int i2) {
        if (this.sections[i2] == null) {
            return false;
        }
        for (int i3 = 0; i3 < this.sections[i2].size(); i3++) {
            Record record = (Record) this.sections[i2].get(i3);
            if (record.getType() == i && name.equals(record.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean findRRset(Name name, int i) {
        return findRRset(name, i, 1) || findRRset(name, i, 2) || findRRset(name, i, 3);
    }

    public Record getQuestion() {
        List list = this.sections[0];
        if (list == null || list.size() == 0) {
            return null;
        }
        return (Record) list.get(0);
    }

    public TSIGRecord getTSIG() {
        int count = this.header.getCount(3);
        if (count == 0) {
            return null;
        }
        Record record = (Record) this.sections[3].get(count - 1);
        if (record.type != 250) {
            return null;
        }
        return (TSIGRecord) record;
    }

    public boolean isSigned() {
        int i = this.tsigState;
        return i == 3 || i == 1 || i == 4;
    }

    public boolean isVerified() {
        return this.tsigState == 1;
    }

    public OPTRecord getOPT() {
        Record[] sectionArray = getSectionArray(3);
        for (int i = 0; i < sectionArray.length; i++) {
            if (sectionArray[i] instanceof OPTRecord) {
                return (OPTRecord) sectionArray[i];
            }
        }
        return null;
    }

    public int getRcode() {
        int rcode = this.header.getRcode();
        OPTRecord opt = getOPT();
        return opt != null ? rcode + (opt.getExtendedRcode() << 4) : rcode;
    }

    public Record[] getSectionArray(int i) {
        List[] listArr = this.sections;
        if (listArr[i] == null) {
            return emptyRecordArray;
        }
        List list = listArr[i];
        return (Record[]) list.toArray(new Record[list.size()]);
    }

    private static boolean sameSet(Record record, Record record2) {
        return record.getRRsetType() == record2.getRRsetType() && record.getDClass() == record2.getDClass() && record.getName().equals(record2.getName());
    }

    public RRset[] getSectionRRsets(int i) {
        if (this.sections[i] == null) {
            return emptyRRsetArray;
        }
        LinkedList linkedList = new LinkedList();
        Record[] sectionArray = getSectionArray(i);
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < sectionArray.length; i2++) {
            Name name = sectionArray[i2].getName();
            boolean z = true;
            if (hashSet.contains(name)) {
                int size2 = linkedList.size() - 1;
                while (true) {
                    if (size2 < 0) {
                        break;
                    }
                    RRset rRset = (RRset) linkedList.get(size2);
                    if (rRset.getType() == sectionArray[i2].getRRsetType() && rRset.getDClass() == sectionArray[i2].getDClass() && rRset.getName().equals(name)) {
                        rRset.addRR(sectionArray[i2]);
                        z = false;
                        break;
                    }
                    size2--;
                }
            }
            if (z) {
                linkedList.add(new RRset(sectionArray[i2]));
                hashSet.add(name);
            }
        }
        return (RRset[]) linkedList.toArray(new RRset[linkedList.size()]);
    }

    /* access modifiers changed from: package-private */
    public void toWire(DNSOutput dNSOutput) {
        this.header.toWire(dNSOutput);
        Compression compression = new Compression();
        for (int i = 0; i < 4; i++) {
            if (this.sections[i] != null) {
                for (int i2 = 0; i2 < this.sections[i].size(); i2++) {
                    ((Record) this.sections[i].get(i2)).toWire(dNSOutput, i, compression);
                }
            }
        }
    }

    private int sectionToWire(DNSOutput dNSOutput, int i, Compression compression, int i2) {
        int size2 = this.sections[i].size();
        int current = dNSOutput.current();
        Record record = null;
        int i3 = 0;
        int i4 = 0;
        while (i3 < size2) {
            Record record2 = (Record) this.sections[i].get(i3);
            if (record != null && !sameSet(record2, record)) {
                current = dNSOutput.current();
                i4 = i3;
            }
            record2.toWire(dNSOutput, i, compression);
            if (dNSOutput.current() > i2) {
                dNSOutput.jump(current);
                return size2 - i4;
            }
            i3++;
            record = record2;
        }
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: org.xbill.DNS.Header} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: org.xbill.DNS.Header} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean toWire(org.xbill.DNS.DNSOutput r10, int r11) {
        /*
            r9 = this;
            r0 = 0
            r1 = 12
            if (r11 >= r1) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            org.xbill.DNS.TSIG r2 = r9.tsigkey
            if (r2 == 0) goto L_0x0010
            int r2 = r2.recordLength()
            int r11 = r11 - r2
        L_0x0010:
            int r2 = r10.current()
            org.xbill.DNS.Header r3 = r9.header
            r3.toWire(r10)
            org.xbill.DNS.Compression r3 = new org.xbill.DNS.Compression
            r3.<init>()
            r4 = 0
        L_0x001f:
            r5 = 4
            r6 = 3
            r7 = 1
            if (r4 >= r5) goto L_0x0061
            java.util.List[] r8 = r9.sections
            r8 = r8[r4]
            if (r8 != 0) goto L_0x002b
            goto L_0x005e
        L_0x002b:
            int r8 = r9.sectionToWire(r10, r4, r3, r11)
            if (r8 == 0) goto L_0x005e
            org.xbill.DNS.Header r11 = r9.header
            java.lang.Object r11 = r11.clone()
            r1 = r11
            org.xbill.DNS.Header r1 = (org.xbill.DNS.Header) r1
            if (r4 == r6) goto L_0x0040
            r11 = 6
            r1.setFlag(r11)
        L_0x0040:
            int r11 = r1.getCount(r4)
            int r11 = r11 - r8
            r1.setCount(r4, r11)
            int r4 = r4 + r7
        L_0x0049:
            if (r4 >= r5) goto L_0x0051
            r1.setCount(r4, r0)
            int r4 = r4 + 1
            goto L_0x0049
        L_0x0051:
            r10.save()
            r10.jump(r2)
            r1.toWire(r10)
            r10.restore()
            goto L_0x0061
        L_0x005e:
            int r4 = r4 + 1
            goto L_0x001f
        L_0x0061:
            org.xbill.DNS.TSIG r11 = r9.tsigkey
            if (r11 == 0) goto L_0x008e
            byte[] r0 = r10.toByteArray()
            int r4 = r9.tsigerror
            org.xbill.DNS.TSIGRecord r5 = r9.querytsig
            org.xbill.DNS.TSIGRecord r11 = r11.generate(r9, r0, r4, r5)
            if (r1 != 0) goto L_0x007c
            org.xbill.DNS.Header r0 = r9.header
            java.lang.Object r0 = r0.clone()
            r1 = r0
            org.xbill.DNS.Header r1 = (org.xbill.DNS.Header) r1
        L_0x007c:
            r11.toWire(r10, r6, r3)
            r1.incCount(r6)
            r10.save()
            r10.jump(r2)
            r1.toWire(r10)
            r10.restore()
        L_0x008e:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Message.toWire(org.xbill.DNS.DNSOutput, int):boolean");
    }

    public byte[] toWire() {
        DNSOutput dNSOutput = new DNSOutput();
        toWire(dNSOutput);
        this.size = dNSOutput.current();
        return dNSOutput.toByteArray();
    }

    public byte[] toWire(int i) {
        DNSOutput dNSOutput = new DNSOutput();
        toWire(dNSOutput, i);
        this.size = dNSOutput.current();
        return dNSOutput.toByteArray();
    }

    public void setTSIG(TSIG tsig, int i, TSIGRecord tSIGRecord) {
        this.tsigkey = tsig;
        this.tsigerror = i;
        this.querytsig = tSIGRecord;
    }

    public int numBytes() {
        return this.size;
    }

    public String sectionToString(int i) {
        if (i > 3) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Record[] sectionArray = getSectionArray(i);
        for (Record record : sectionArray) {
            if (i == 0) {
                stringBuffer.append(";;\t" + record.name);
                stringBuffer.append(", type = " + Type.string(record.type));
                stringBuffer.append(", class = " + DClass.string(record.dclass));
            } else {
                stringBuffer.append(record);
            }
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getOPT() != null) {
            stringBuffer.append(this.header.toStringWithRcode(getRcode()) + "\n");
        } else {
            stringBuffer.append(this.header + "\n");
        }
        if (isSigned()) {
            stringBuffer.append(";; TSIG ");
            if (isVerified()) {
                stringBuffer.append("ok");
            } else {
                stringBuffer.append("invalid");
            }
            stringBuffer.append(10);
        }
        for (int i = 0; i < 4; i++) {
            if (this.header.getOpcode() != 5) {
                stringBuffer.append(";; " + Section.longString(i) + ":\n");
            } else {
                stringBuffer.append(";; " + Section.updString(i) + ":\n");
            }
            stringBuffer.append(sectionToString(i) + "\n");
        }
        stringBuffer.append(";; Message size: " + numBytes() + " bytes");
        return stringBuffer.toString();
    }

    public Object clone() {
        Message message = new Message();
        int i = 0;
        while (true) {
            List[] listArr = this.sections;
            if (i < listArr.length) {
                if (listArr[i] != null) {
                    message.sections[i] = new LinkedList(this.sections[i]);
                }
                i++;
            } else {
                message.header = (Header) this.header.clone();
                message.size = this.size;
                return message;
            }
        }
    }
}
