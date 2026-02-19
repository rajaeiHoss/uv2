package org.xbill.DNS;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Zone implements Serializable {
    public static final int PRIMARY = 1;
    public static final int SECONDARY = 2;
    private static final long serialVersionUID = -9220510891189510942L;
    private RRset NS;
    private SOARecord SOA;
    /* access modifiers changed from: private */
    public Map data;
    private int dclass;
    private boolean hasWild;
    /* access modifiers changed from: private */
    public Name origin;
    /* access modifiers changed from: private */
    public Object originNode;

    class ZoneIterator implements Iterator {
        private int count;
        private RRset[] current;
        private boolean wantLastSOA;
        private Iterator zentries;

        ZoneIterator(boolean z) {
            synchronized (Zone.this) {
                this.zentries = Zone.this.data.entrySet().iterator();
            }
            this.wantLastSOA = z;
            RRset[] access$200 = Zone.this.allRRsets(Zone.this.originNode);
            this.current = new RRset[access$200.length];
            int i = 2;
            for (int i2 = 0; i2 < access$200.length; i2++) {
                int type = access$200[i2].getType();
                if (type == 6) {
                    this.current[0] = access$200[i2];
                } else if (type == 2) {
                    this.current[1] = access$200[i2];
                } else {
                    this.current[i] = access$200[i2];
                    i++;
                }
            }
        }

        public boolean hasNext() {
            return this.current != null || this.wantLastSOA;
        }

        public Object next() {
            if (hasNext()) {
                RRset[] rRsetArr = this.current;
                if (rRsetArr == null) {
                    this.wantLastSOA = false;
                    Zone zone = Zone.this;
                    return zone.oneRRset(zone.originNode, 6);
                }
                int i = this.count;
                int i2 = i + 1;
                this.count = i2;
                RRset rRset = rRsetArr[i];
                if (i2 == rRsetArr.length) {
                    this.current = null;
                    while (true) {
                        if (!this.zentries.hasNext()) {
                            break;
                        }
                        Map.Entry entry = (Map.Entry) this.zentries.next();
                        if (!entry.getKey().equals(Zone.this.origin)) {
                            RRset[] access$200 = Zone.this.allRRsets(entry.getValue());
                            if (access$200.length != 0) {
                                this.current = access$200;
                                this.count = 0;
                                break;
                            }
                        }
                    }
                }
                return rRset;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void validate() throws IOException {
        Object exactName = exactName(this.origin);
        this.originNode = exactName;
        if (exactName != null) {
            RRset oneRRset = oneRRset(exactName, 6);
            if (oneRRset == null || oneRRset.size() != 1) {
                throw new IOException(this.origin + ": exactly 1 SOA must be specified");
            }
            this.SOA = (SOARecord) oneRRset.rrs().next();
            RRset oneRRset2 = oneRRset(this.originNode, 2);
            this.NS = oneRRset2;
            if (oneRRset2 == null) {
                throw new IOException(this.origin + ": no NS set specified");
            }
            return;
        }
        throw new IOException(this.origin + ": no data specified");
    }

    private final void maybeAddRecord(Record record) throws IOException {
        int type = record.getType();
        Name name = record.getName();
        if (type == 6 && !name.equals(this.origin)) {
            throw new IOException("SOA owner " + name + " does not match zone origin " + this.origin);
        } else if (name.subdomain(this.origin)) {
            addRecord(record);
        }
    }

    public Zone(Name name, String str) throws IOException {
        this.dclass = 1;
        this.data = new TreeMap();
        if (name != null) {
            Master master = new Master(str, name);
            this.origin = name;
            while (true) {
                Record nextRecord = master.nextRecord();
                if (nextRecord != null) {
                    maybeAddRecord(nextRecord);
                } else {
                    validate();
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("no zone name specified");
        }
    }

    public Zone(Name name, Record[] recordArr) throws IOException {
        this.dclass = 1;
        this.data = new TreeMap();
        if (name != null) {
            this.origin = name;
            for (Record maybeAddRecord : recordArr) {
                maybeAddRecord(maybeAddRecord);
            }
            validate();
            return;
        }
        throw new IllegalArgumentException("no zone name specified");
    }

    private void fromXFR(ZoneTransferIn zoneTransferIn) throws IOException, ZoneTransferException {
        this.data = new TreeMap();
        this.origin = zoneTransferIn.getName();
        for (Object recordObj : zoneTransferIn.run()) {
            maybeAddRecord((Record) recordObj);
        }
        if (zoneTransferIn.isAXFR()) {
            validate();
            return;
        }
        throw new IllegalArgumentException("zones can only be created from AXFRs");
    }

    public Zone(ZoneTransferIn zoneTransferIn) throws IOException, ZoneTransferException {
        this.dclass = 1;
        fromXFR(zoneTransferIn);
    }

    public Zone(Name name, int i, String str) throws IOException, ZoneTransferException {
        this.dclass = 1;
        ZoneTransferIn newAXFR = ZoneTransferIn.newAXFR(name, str, (TSIG) null);
        newAXFR.setDClass(i);
        fromXFR(newAXFR);
    }

    public Name getOrigin() {
        return this.origin;
    }

    public RRset getNS() {
        return this.NS;
    }

    public SOARecord getSOA() {
        return this.SOA;
    }

    public int getDClass() {
        return this.dclass;
    }

    private synchronized Object exactName(Name name) {
        return this.data.get(name);
    }

    /* access modifiers changed from: private */
    public synchronized RRset[] allRRsets(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            return (RRset[]) list.toArray(new RRset[list.size()]);
        }
        return new RRset[]{(RRset) obj};
    }

    /* access modifiers changed from: private */
    public synchronized RRset oneRRset(Object obj, int i) {
        if (i != 255) {
            if (obj instanceof List) {
                List list = (List) obj;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    RRset rRset = (RRset) list.get(i2);
                    if (rRset.getType() == i) {
                        return rRset;
                    }
                }
            } else {
                RRset rRset2 = (RRset) obj;
                if (rRset2.getType() == i) {
                    return rRset2;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("oneRRset(ANY)");
    }

    private synchronized RRset findRRset(Name name, int i) {
        Object exactName = exactName(name);
        if (exactName == null) {
            return null;
        }
        return oneRRset(exactName, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0065, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void addRRset(org.xbill.DNS.Name r4, org.xbill.DNS.RRset r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.hasWild     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x000e
            boolean r0 = r4.isWild()     // Catch:{ all -> 0x0066 }
            if (r0 == 0) goto L_0x000e
            r0 = 1
            r3.hasWild = r0     // Catch:{ all -> 0x0066 }
        L_0x000e:
            java.util.Map r0 = r3.data     // Catch:{ all -> 0x0066 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0066 }
            if (r0 != 0) goto L_0x001d
            java.util.Map r0 = r3.data     // Catch:{ all -> 0x0066 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0066 }
            monitor-exit(r3)
            return
        L_0x001d:
            int r1 = r5.getType()     // Catch:{ all -> 0x0066 }
            boolean r2 = r0 instanceof java.util.List     // Catch:{ all -> 0x0066 }
            if (r2 == 0) goto L_0x0046
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0066 }
            r4 = 0
        L_0x0028:
            int r2 = r0.size()     // Catch:{ all -> 0x0066 }
            if (r4 >= r2) goto L_0x0042
            java.lang.Object r2 = r0.get(r4)     // Catch:{ all -> 0x0066 }
            org.xbill.DNS.RRset r2 = (org.xbill.DNS.RRset) r2     // Catch:{ all -> 0x0066 }
            int r2 = r2.getType()     // Catch:{ all -> 0x0066 }
            if (r2 != r1) goto L_0x003f
            r0.set(r4, r5)     // Catch:{ all -> 0x0066 }
            monitor-exit(r3)
            return
        L_0x003f:
            int r4 = r4 + 1
            goto L_0x0028
        L_0x0042:
            r0.add(r5)     // Catch:{ all -> 0x0066 }
            goto L_0x0064
        L_0x0046:
            org.xbill.DNS.RRset r0 = (org.xbill.DNS.RRset) r0     // Catch:{ all -> 0x0066 }
            int r2 = r0.getType()     // Catch:{ all -> 0x0066 }
            if (r2 != r1) goto L_0x0054
            java.util.Map r0 = r3.data     // Catch:{ all -> 0x0066 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0066 }
            goto L_0x0064
        L_0x0054:
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x0066 }
            r1.<init>()     // Catch:{ all -> 0x0066 }
            r1.add(r0)     // Catch:{ all -> 0x0066 }
            r1.add(r5)     // Catch:{ all -> 0x0066 }
            java.util.Map r5 = r3.data     // Catch:{ all -> 0x0066 }
            r5.put(r4, r1)     // Catch:{ all -> 0x0066 }
        L_0x0064:
            monitor-exit(r3)
            return
        L_0x0066:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Zone.addRRset(org.xbill.DNS.Name, org.xbill.DNS.RRset):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void removeRRset(org.xbill.DNS.Name r4, int r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.Map r0 = r3.data     // Catch:{ all -> 0x0048 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0048 }
            if (r0 != 0) goto L_0x000b
            monitor-exit(r3)
            return
        L_0x000b:
            boolean r1 = r0 instanceof java.util.List     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x0037
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0048 }
            r1 = 0
        L_0x0012:
            int r2 = r0.size()     // Catch:{ all -> 0x0048 }
            if (r1 >= r2) goto L_0x0046
            java.lang.Object r2 = r0.get(r1)     // Catch:{ all -> 0x0048 }
            org.xbill.DNS.RRset r2 = (org.xbill.DNS.RRset) r2     // Catch:{ all -> 0x0048 }
            int r2 = r2.getType()     // Catch:{ all -> 0x0048 }
            if (r2 != r5) goto L_0x0034
            r0.remove(r1)     // Catch:{ all -> 0x0048 }
            int r5 = r0.size()     // Catch:{ all -> 0x0048 }
            if (r5 != 0) goto L_0x0032
            java.util.Map r5 = r3.data     // Catch:{ all -> 0x0048 }
            r5.remove(r4)     // Catch:{ all -> 0x0048 }
        L_0x0032:
            monitor-exit(r3)
            return
        L_0x0034:
            int r1 = r1 + 1
            goto L_0x0012
        L_0x0037:
            org.xbill.DNS.RRset r0 = (org.xbill.DNS.RRset) r0     // Catch:{ all -> 0x0048 }
            int r0 = r0.getType()     // Catch:{ all -> 0x0048 }
            if (r0 == r5) goto L_0x0041
            monitor-exit(r3)
            return
        L_0x0041:
            java.util.Map r5 = r3.data     // Catch:{ all -> 0x0048 }
            r5.remove(r4)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r3)
            return
        L_0x0048:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Zone.removeRRset(org.xbill.DNS.Name, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r11 = new org.xbill.DNS.SetResponse(6);
        r12 = allRRsets(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0061, code lost:
        if (r5 >= r12.length) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0063, code lost:
        r11.addRRset(r12[r5]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0068, code lost:
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x006c, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00ab, code lost:
        if (r10.hasWild == false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00af, code lost:
        if (r5 >= (r0 - r2)) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00b1, code lost:
        r5 = r5 + 1;
        r3 = exactName(r11.wild(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00bb, code lost:
        if (r3 != null) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00be, code lost:
        r3 = oneRRset(r3, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00c2, code lost:
        if (r3 == null) goto L_0x00ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00c4, code lost:
        r11 = new org.xbill.DNS.SetResponse(6);
        r11.addRRset(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00cd, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00d3, code lost:
        return org.xbill.DNS.SetResponse.ofType(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized org.xbill.DNS.SetResponse lookup(org.xbill.DNS.Name r11, int r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            org.xbill.DNS.Name r0 = r10.origin     // Catch:{ all -> 0x00d4 }
            boolean r0 = r11.subdomain(r0)     // Catch:{ all -> 0x00d4 }
            r1 = 1
            if (r0 != 0) goto L_0x0010
            org.xbill.DNS.SetResponse r11 = org.xbill.DNS.SetResponse.ofType(r1)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x0010:
            int r0 = r11.labels()     // Catch:{ all -> 0x00d4 }
            org.xbill.DNS.Name r2 = r10.origin     // Catch:{ all -> 0x00d4 }
            int r2 = r2.labels()     // Catch:{ all -> 0x00d4 }
            r3 = r2
        L_0x001b:
            r4 = 6
            r5 = 0
            if (r3 > r0) goto L_0x00a9
            if (r3 != r2) goto L_0x0023
            r6 = 1
            goto L_0x0024
        L_0x0023:
            r6 = 0
        L_0x0024:
            if (r3 != r0) goto L_0x0028
            r7 = 1
            goto L_0x0029
        L_0x0028:
            r7 = 0
        L_0x0029:
            if (r6 == 0) goto L_0x002e
            org.xbill.DNS.Name r8 = r10.origin     // Catch:{ all -> 0x00d4 }
            goto L_0x0039
        L_0x002e:
            if (r7 == 0) goto L_0x0032
            r8 = r11
            goto L_0x0039
        L_0x0032:
            org.xbill.DNS.Name r8 = new org.xbill.DNS.Name     // Catch:{ all -> 0x00d4 }
            int r9 = r0 - r3
            r8.<init>((org.xbill.DNS.Name) r11, (int) r9)     // Catch:{ all -> 0x00d4 }
        L_0x0039:
            java.lang.Object r8 = r10.exactName(r8)     // Catch:{ all -> 0x00d4 }
            if (r8 != 0) goto L_0x0040
            goto L_0x00a5
        L_0x0040:
            r9 = 2
            if (r6 != 0) goto L_0x0051
            org.xbill.DNS.RRset r6 = r10.oneRRset(r8, r9)     // Catch:{ all -> 0x00d4 }
            if (r6 == 0) goto L_0x0051
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r12 = 3
            r11.<init>(r12, r6)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x0051:
            if (r7 == 0) goto L_0x006d
            r6 = 255(0xff, float:3.57E-43)
            if (r12 != r6) goto L_0x006d
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r11.<init>(r4)     // Catch:{ all -> 0x00d4 }
            org.xbill.DNS.RRset[] r12 = r10.allRRsets(r8)     // Catch:{ all -> 0x00d4 }
        L_0x0060:
            int r0 = r12.length     // Catch:{ all -> 0x00d4 }
            if (r5 >= r0) goto L_0x006b
            r0 = r12[r5]     // Catch:{ all -> 0x00d4 }
            r11.addRRset(r0)     // Catch:{ all -> 0x00d4 }
            int r5 = r5 + 1
            goto L_0x0060
        L_0x006b:
            monitor-exit(r10)
            return r11
        L_0x006d:
            r5 = 5
            if (r7 == 0) goto L_0x008e
            org.xbill.DNS.RRset r6 = r10.oneRRset(r8, r12)     // Catch:{ all -> 0x00d4 }
            if (r6 == 0) goto L_0x0080
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r11.<init>(r4)     // Catch:{ all -> 0x00d4 }
            r11.addRRset(r6)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x0080:
            org.xbill.DNS.RRset r4 = r10.oneRRset(r8, r5)     // Catch:{ all -> 0x00d4 }
            if (r4 == 0) goto L_0x009d
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r12 = 4
            r11.<init>(r12, r4)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x008e:
            r4 = 39
            org.xbill.DNS.RRset r4 = r10.oneRRset(r8, r4)     // Catch:{ all -> 0x00d4 }
            if (r4 == 0) goto L_0x009d
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r11.<init>(r5, r4)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x009d:
            if (r7 == 0) goto L_0x00a5
            org.xbill.DNS.SetResponse r11 = org.xbill.DNS.SetResponse.ofType(r9)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x00a5:
            int r3 = r3 + 1
            goto L_0x001b
        L_0x00a9:
            boolean r3 = r10.hasWild     // Catch:{ all -> 0x00d4 }
            if (r3 == 0) goto L_0x00ce
        L_0x00ad:
            int r3 = r0 - r2
            if (r5 >= r3) goto L_0x00ce
            int r5 = r5 + 1
            org.xbill.DNS.Name r3 = r11.wild(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.Object r3 = r10.exactName(r3)     // Catch:{ all -> 0x00d4 }
            if (r3 != 0) goto L_0x00be
            goto L_0x00ad
        L_0x00be:
            org.xbill.DNS.RRset r3 = r10.oneRRset(r3, r12)     // Catch:{ all -> 0x00d4 }
            if (r3 == 0) goto L_0x00ad
            org.xbill.DNS.SetResponse r11 = new org.xbill.DNS.SetResponse     // Catch:{ all -> 0x00d4 }
            r11.<init>(r4)     // Catch:{ all -> 0x00d4 }
            r11.addRRset(r3)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x00ce:
            org.xbill.DNS.SetResponse r11 = org.xbill.DNS.SetResponse.ofType(r1)     // Catch:{ all -> 0x00d4 }
            monitor-exit(r10)
            return r11
        L_0x00d4:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Zone.lookup(org.xbill.DNS.Name, int):org.xbill.DNS.SetResponse");
    }

    public SetResponse findRecords(Name name, int i) {
        return lookup(name, i);
    }

    public RRset findExactMatch(Name name, int i) {
        Object exactName = exactName(name);
        if (exactName == null) {
            return null;
        }
        return oneRRset(exactName, i);
    }

    public void addRRset(RRset rRset) {
        addRRset(rRset.getName(), rRset);
    }

    public void addRecord(Record record) {
        Name name = record.getName();
        int rRsetType = record.getRRsetType();
        synchronized (this) {
            RRset findRRset = findRRset(name, rRsetType);
            if (findRRset == null) {
                addRRset(name, new RRset(record));
            } else {
                findRRset.addRR(record);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeRecord(org.xbill.DNS.Record r6) {
        /*
            r5 = this;
            org.xbill.DNS.Name r0 = r6.getName()
            int r1 = r6.getRRsetType()
            monitor-enter(r5)
            org.xbill.DNS.RRset r2 = r5.findRRset(r0, r1)     // Catch:{ all -> 0x002b }
            if (r2 != 0) goto L_0x0011
            monitor-exit(r5)     // Catch:{ all -> 0x002b }
            return
        L_0x0011:
            int r3 = r2.size()     // Catch:{ all -> 0x002b }
            r4 = 1
            if (r3 != r4) goto L_0x0026
            org.xbill.DNS.Record r3 = r2.first()     // Catch:{ all -> 0x002b }
            boolean r3 = r3.equals(r6)     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x0026
            r5.removeRRset(r0, r1)     // Catch:{ all -> 0x002b }
            goto L_0x0029
        L_0x0026:
            r2.deleteRR(r6)     // Catch:{ all -> 0x002b }
        L_0x0029:
            monitor-exit(r5)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x002b }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Zone.removeRecord(org.xbill.DNS.Record):void");
    }

    public Iterator iterator() {
        return new ZoneIterator(false);
    }

    public Iterator AXFR() {
        return new ZoneIterator(true);
    }

    private void nodeToString(StringBuffer stringBuffer, Object obj) {
        RRset[] allRRsets = allRRsets(obj);
        for (RRset rRset : allRRsets) {
            Iterator rrs = rRset.rrs();
            while (rrs.hasNext()) {
                stringBuffer.append(rrs.next() + "\n");
            }
            Iterator sigs = rRset.sigs();
            while (sigs.hasNext()) {
                stringBuffer.append(sigs.next() + "\n");
            }
        }
    }

    public synchronized String toMasterFile() {
        StringBuffer stringBuffer;
        stringBuffer = new StringBuffer();
        nodeToString(stringBuffer, this.originNode);
        for (Object entryObj : this.data.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObj;
            if (!this.origin.equals(entry.getKey())) {
                nodeToString(stringBuffer, entry.getValue());
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return toMasterFile();
    }
}
