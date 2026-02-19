package org.xbill.DNS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RRset implements Serializable {
    private static final long serialVersionUID = -3270249290171239695L;
    private short nsigs;
    private short position;
    private List rrs;

    public RRset() {
        this.rrs = new ArrayList(1);
        this.nsigs = 0;
        this.position = 0;
    }

    public RRset(Record record) {
        this();
        safeAddRR(record);
    }

    public RRset(RRset rRset) {
        synchronized (rRset) {
            this.rrs = (List) ((ArrayList) rRset.rrs).clone();
            this.nsigs = rRset.nsigs;
            this.position = rRset.position;
        }
    }

    private void safeAddRR(Record record) {
        if (record instanceof RRSIGRecord) {
            this.rrs.add(record);
            this.nsigs = (short) (this.nsigs + 1);
        } else if (this.nsigs == 0) {
            this.rrs.add(record);
        } else {
            List list = this.rrs;
            list.add(list.size() - this.nsigs, record);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addRR(org.xbill.DNS.Record r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.List r0 = r6.rrs     // Catch:{ all -> 0x0075 }
            int r0 = r0.size()     // Catch:{ all -> 0x0075 }
            if (r0 != 0) goto L_0x000e
            r6.safeAddRR(r7)     // Catch:{ all -> 0x0075 }
            monitor-exit(r6)
            return
        L_0x000e:
            org.xbill.DNS.Record r0 = r6.first()     // Catch:{ all -> 0x0075 }
            boolean r1 = r7.sameRRset(r0)     // Catch:{ all -> 0x0075 }
            if (r1 == 0) goto L_0x006d
            long r1 = r7.getTTL()     // Catch:{ all -> 0x0075 }
            long r3 = r0.getTTL()     // Catch:{ all -> 0x0075 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0060
            long r1 = r7.getTTL()     // Catch:{ all -> 0x0075 }
            long r3 = r0.getTTL()     // Catch:{ all -> 0x0075 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x003c
            org.xbill.DNS.Record r7 = r7.cloneRecord()     // Catch:{ all -> 0x0075 }
            long r0 = r0.getTTL()     // Catch:{ all -> 0x0075 }
            r7.setTTL(r0)     // Catch:{ all -> 0x0075 }
            goto L_0x0060
        L_0x003c:
            r0 = 0
        L_0x003d:
            java.util.List r1 = r6.rrs     // Catch:{ all -> 0x0075 }
            int r1 = r1.size()     // Catch:{ all -> 0x0075 }
            if (r0 >= r1) goto L_0x0060
            java.util.List r1 = r6.rrs     // Catch:{ all -> 0x0075 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0075 }
            org.xbill.DNS.Record r1 = (org.xbill.DNS.Record) r1     // Catch:{ all -> 0x0075 }
            org.xbill.DNS.Record r1 = r1.cloneRecord()     // Catch:{ all -> 0x0075 }
            long r2 = r7.getTTL()     // Catch:{ all -> 0x0075 }
            r1.setTTL(r2)     // Catch:{ all -> 0x0075 }
            java.util.List r2 = r6.rrs     // Catch:{ all -> 0x0075 }
            r2.set(r0, r1)     // Catch:{ all -> 0x0075 }
            int r0 = r0 + 1
            goto L_0x003d
        L_0x0060:
            java.util.List r0 = r6.rrs     // Catch:{ all -> 0x0075 }
            boolean r0 = r0.contains(r7)     // Catch:{ all -> 0x0075 }
            if (r0 != 0) goto L_0x006b
            r6.safeAddRR(r7)     // Catch:{ all -> 0x0075 }
        L_0x006b:
            monitor-exit(r6)
            return
        L_0x006d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0075 }
            java.lang.String r0 = "record does not match rrset"
            r7.<init>(r0)     // Catch:{ all -> 0x0075 }
            throw r7     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.RRset.addRR(org.xbill.DNS.Record):void");
    }

    public synchronized void deleteRR(Record record) {
        if (this.rrs.remove(record) && (record instanceof RRSIGRecord)) {
            this.nsigs = (short) (this.nsigs - 1);
        }
    }

    public synchronized void clear() {
        this.rrs.clear();
        this.position = 0;
        this.nsigs = 0;
    }

    private synchronized Iterator iterator(boolean z, boolean z2) {
        int i;
        int i2;
        int size = this.rrs.size();
        if (z) {
            i = size - this.nsigs;
        } else {
            i = this.nsigs;
        }
        if (i == 0) {
            return Collections.EMPTY_LIST.iterator();
        }
        if (!z) {
            i2 = size - this.nsigs;
        } else if (!z2) {
            i2 = 0;
        } else {
            if (this.position >= i) {
                this.position = 0;
            }
            i2 = this.position;
            this.position = (short) (i2 + 1);
        }
        ArrayList arrayList = new ArrayList(i);
        if (z) {
            arrayList.addAll(this.rrs.subList(i2, i));
            if (i2 != 0) {
                arrayList.addAll(this.rrs.subList(0, i2));
            }
        } else {
            arrayList.addAll(this.rrs.subList(i2, size));
        }
        return arrayList.iterator();
    }

    public synchronized Iterator rrs(boolean z) {
        return iterator(true, z);
    }

    public synchronized Iterator rrs() {
        return iterator(true, true);
    }

    public synchronized Iterator sigs() {
        return iterator(false, false);
    }

    public synchronized int size() {
        return this.rrs.size() - this.nsigs;
    }

    public Name getName() {
        return first().getName();
    }

    public int getType() {
        return first().getRRsetType();
    }

    public int getDClass() {
        return first().getDClass();
    }

    public synchronized long getTTL() {
        return first().getTTL();
    }

    public synchronized Record first() {
        if (this.rrs.size() != 0) {
        } else {
            throw new IllegalStateException("rrset is empty");
        }
        return (Record) this.rrs.get(0);
    }

    private String iteratorToString(Iterator it) {
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            stringBuffer.append("[");
            stringBuffer.append(((Record) it.next()).rdataToString());
            stringBuffer.append("]");
            if (it.hasNext()) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        if (this.rrs == null) {
            return "{empty}";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{ ");
        stringBuffer.append(getName() + " ");
        stringBuffer.append(getTTL() + " ");
        stringBuffer.append(DClass.string(getDClass()) + " ");
        stringBuffer.append(Type.string(getType()) + " ");
        stringBuffer.append(iteratorToString(iterator(true, false)));
        if (this.nsigs > 0) {
            stringBuffer.append(" sigs: ");
            stringBuffer.append(iteratorToString(iterator(false, false)));
        }
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
