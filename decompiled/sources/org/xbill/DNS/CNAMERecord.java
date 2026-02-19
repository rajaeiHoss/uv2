package org.xbill.DNS;

public class CNAMERecord extends SingleCompressedNameBase {
    private static final long serialVersionUID = -4020373886892538580L;

    CNAMERecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new CNAMERecord();
    }

    public CNAMERecord(Name name, int i, long j, Name name2) {
        super(name, 5, i, j, name2, "alias");
    }

    public Name getTarget() {
        return getSingleName();
    }

    public Name getAlias() {
        return getSingleName();
    }
}
