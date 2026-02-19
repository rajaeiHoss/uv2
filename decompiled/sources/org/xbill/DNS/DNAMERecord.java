package org.xbill.DNS;

public class DNAMERecord extends SingleNameBase {
    private static final long serialVersionUID = 2670767677200844154L;

    DNAMERecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new DNAMERecord();
    }

    public DNAMERecord(Name name, int i, long j, Name name2) {
        super(name, 39, i, j, name2, "alias");
    }

    public Name getTarget() {
        return getSingleName();
    }

    public Name getAlias() {
        return getSingleName();
    }
}
