package org.xbill.DNS;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cache {
    private static final int defaultMaxEntries = 50000;
    private CacheMap data;
    private int dclass;
    private int maxcache;
    private int maxncache;

    private interface Element {
        int compareCredibility(int i);

        boolean expired();

        int getType();
    }

    /* access modifiers changed from: private */
    public static int limitExpire(long j, long j2) {
        if (j2 >= 0 && j2 < j) {
            j = j2;
        }
        long currentTimeMillis = (System.currentTimeMillis() / 1000) + j;
        if (currentTimeMillis < 0 || currentTimeMillis > TTL.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int) currentTimeMillis;
    }

    private static class CacheRRset extends RRset implements Element {
        private static final long serialVersionUID = 5971755205903597024L;
        int credibility;
        int expire;

        public CacheRRset(Record record, int i, long j) {
            this.credibility = i;
            this.expire = Cache.limitExpire(record.getTTL(), j);
            addRR(record);
        }

        public CacheRRset(RRset rRset, int i, long j) {
            super(rRset);
            this.credibility = i;
            this.expire = Cache.limitExpire(rRset.getTTL(), j);
        }

        public final boolean expired() {
            return ((int) (System.currentTimeMillis() / 1000)) >= this.expire;
        }

        public final int compareCredibility(int i) {
            return this.credibility - i;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(super.toString());
            stringBuffer.append(" cl = ");
            stringBuffer.append(this.credibility);
            return stringBuffer.toString();
        }
    }

    private static class NegativeElement implements Element {
        int credibility;
        int expire;
        Name name;
        int type;

        public NegativeElement(Name name2, int i, SOARecord sOARecord, int i2, long j) {
            this.name = name2;
            this.type = i;
            long minimum = sOARecord != null ? sOARecord.getMinimum() : 0;
            this.credibility = i2;
            this.expire = Cache.limitExpire(minimum, j);
        }

        public int getType() {
            return this.type;
        }

        public final boolean expired() {
            return ((int) (System.currentTimeMillis() / 1000)) >= this.expire;
        }

        public final int compareCredibility(int i) {
            return this.credibility - i;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.type == 0) {
                stringBuffer.append("NXDOMAIN " + this.name);
            } else {
                stringBuffer.append("NXRRSET " + this.name + " " + Type.string(this.type));
            }
            stringBuffer.append(" cl = ");
            stringBuffer.append(this.credibility);
            return stringBuffer.toString();
        }
    }

    private static class CacheMap extends LinkedHashMap {
        private int maxsize = -1;

        CacheMap(int i) {
            super(16, 0.75f, true);
            this.maxsize = i;
        }

        /* access modifiers changed from: package-private */
        public int getMaxSize() {
            return this.maxsize;
        }

        /* access modifiers changed from: package-private */
        public void setMaxSize(int i) {
            this.maxsize = i;
        }

        /* access modifiers changed from: protected */
        public boolean removeEldestEntry(Map.Entry entry) {
            return this.maxsize >= 0 && size() > this.maxsize;
        }
    }

    public Cache(int i) {
        this.maxncache = -1;
        this.maxcache = -1;
        this.dclass = i;
        this.data = new CacheMap(defaultMaxEntries);
    }

    public Cache() {
        this(1);
    }

    public Cache(String str) throws IOException {
        this.maxncache = -1;
        this.maxcache = -1;
        this.data = new CacheMap(defaultMaxEntries);
        Master master = new Master(str);
        while (true) {
            Record nextRecord = master.nextRecord();
            if (nextRecord != null) {
                addRecord(nextRecord, 0, master);
            } else {
                return;
            }
        }
    }

    private synchronized Object exactName(Name name) {
        return this.data.get(name);
    }

    private synchronized void removeName(Name name) {
        this.data.remove(name);
    }

    private synchronized Element[] allElements(Object obj) {
        if (obj instanceof List) {
            List list = (List) obj;
            return (Element[]) list.toArray(new Element[list.size()]);
        }
        return new Element[]{(Element) obj};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r2.getType() == r7) goto L_0x002e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0030 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized org.xbill.DNS.Cache.Element oneElement(org.xbill.DNS.Name r5, java.lang.Object r6, int r7, int r8) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 255(0xff, float:3.57E-43)
            if (r7 == r0) goto L_0x0049
            boolean r0 = r6 instanceof java.util.List     // Catch:{ all -> 0x0047 }
            r1 = 0
            if (r0 == 0) goto L_0x0023
            java.util.List r6 = (java.util.List) r6     // Catch:{ all -> 0x0047 }
            r0 = 0
        L_0x000d:
            int r2 = r6.size()     // Catch:{ all -> 0x0047 }
            if (r0 >= r2) goto L_0x002d
            java.lang.Object r2 = r6.get(r0)     // Catch:{ all -> 0x0047 }
            org.xbill.DNS.Cache$Element r2 = (org.xbill.DNS.Cache.Element) r2     // Catch:{ all -> 0x0047 }
            int r3 = r2.getType()     // Catch:{ all -> 0x0047 }
            if (r3 != r7) goto L_0x0020
            goto L_0x002e
        L_0x0020:
            int r0 = r0 + 1
            goto L_0x000d
        L_0x0023:
            r2 = r6
            org.xbill.DNS.Cache$Element r2 = (org.xbill.DNS.Cache.Element) r2     // Catch:{ all -> 0x0047 }
            int r6 = r2.getType()     // Catch:{ all -> 0x0047 }
            if (r6 != r7) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r2 = r1
        L_0x002e:
            if (r2 != 0) goto L_0x0032
            monitor-exit(r4)
            return r1
        L_0x0032:
            boolean r6 = r2.expired()     // Catch:{ all -> 0x0047 }
            if (r6 == 0) goto L_0x003d
            r4.removeElement(r5, r7)     // Catch:{ all -> 0x0047 }
            monitor-exit(r4)
            return r1
        L_0x003d:
            int r5 = r2.compareCredibility(r8)     // Catch:{ all -> 0x0047 }
            if (r5 >= 0) goto L_0x0045
            monitor-exit(r4)
            return r1
        L_0x0045:
            monitor-exit(r4)
            return r2
        L_0x0047:
            r5 = move-exception
            goto L_0x0051
        L_0x0049:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0047 }
            java.lang.String r6 = "oneElement(ANY)"
            r5.<init>(r6)     // Catch:{ all -> 0x0047 }
            throw r5     // Catch:{ all -> 0x0047 }
        L_0x0051:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Cache.oneElement(org.xbill.DNS.Name, java.lang.Object, int, int):org.xbill.DNS.Cache$Element");
    }

    private synchronized Element findElement(Name name, int i, int i2) {
        Object exactName = exactName(name);
        if (exactName == null) {
            return null;
        }
        return oneElement(name, exactName, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0058, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void addElement(org.xbill.DNS.Name r4, org.xbill.DNS.Cache.Element r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.xbill.DNS.Cache$CacheMap r0 = r3.data     // Catch:{ all -> 0x0059 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x0010
            org.xbill.DNS.Cache$CacheMap r0 = r3.data     // Catch:{ all -> 0x0059 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r3)
            return
        L_0x0010:
            int r1 = r5.getType()     // Catch:{ all -> 0x0059 }
            boolean r2 = r0 instanceof java.util.List     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0039
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0059 }
            r4 = 0
        L_0x001b:
            int r2 = r0.size()     // Catch:{ all -> 0x0059 }
            if (r4 >= r2) goto L_0x0035
            java.lang.Object r2 = r0.get(r4)     // Catch:{ all -> 0x0059 }
            org.xbill.DNS.Cache$Element r2 = (org.xbill.DNS.Cache.Element) r2     // Catch:{ all -> 0x0059 }
            int r2 = r2.getType()     // Catch:{ all -> 0x0059 }
            if (r2 != r1) goto L_0x0032
            r0.set(r4, r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r3)
            return
        L_0x0032:
            int r4 = r4 + 1
            goto L_0x001b
        L_0x0035:
            r0.add(r5)     // Catch:{ all -> 0x0059 }
            goto L_0x0057
        L_0x0039:
            org.xbill.DNS.Cache$Element r0 = (org.xbill.DNS.Cache.Element) r0     // Catch:{ all -> 0x0059 }
            int r2 = r0.getType()     // Catch:{ all -> 0x0059 }
            if (r2 != r1) goto L_0x0047
            org.xbill.DNS.Cache$CacheMap r0 = r3.data     // Catch:{ all -> 0x0059 }
            r0.put(r4, r5)     // Catch:{ all -> 0x0059 }
            goto L_0x0057
        L_0x0047:
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ all -> 0x0059 }
            r1.<init>()     // Catch:{ all -> 0x0059 }
            r1.add(r0)     // Catch:{ all -> 0x0059 }
            r1.add(r5)     // Catch:{ all -> 0x0059 }
            org.xbill.DNS.Cache$CacheMap r5 = r3.data     // Catch:{ all -> 0x0059 }
            r5.put(r4, r1)     // Catch:{ all -> 0x0059 }
        L_0x0057:
            monitor-exit(r3)
            return
        L_0x0059:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Cache.addElement(org.xbill.DNS.Name, org.xbill.DNS.Cache$Element):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0033, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void removeElement(org.xbill.DNS.Name r4, int r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            org.xbill.DNS.Cache$CacheMap r0 = r3.data     // Catch:{ all -> 0x0048 }
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
            org.xbill.DNS.Cache$Element r2 = (org.xbill.DNS.Cache.Element) r2     // Catch:{ all -> 0x0048 }
            int r2 = r2.getType()     // Catch:{ all -> 0x0048 }
            if (r2 != r5) goto L_0x0034
            r0.remove(r1)     // Catch:{ all -> 0x0048 }
            int r5 = r0.size()     // Catch:{ all -> 0x0048 }
            if (r5 != 0) goto L_0x0032
            org.xbill.DNS.Cache$CacheMap r5 = r3.data     // Catch:{ all -> 0x0048 }
            r5.remove(r4)     // Catch:{ all -> 0x0048 }
        L_0x0032:
            monitor-exit(r3)
            return
        L_0x0034:
            int r1 = r1 + 1
            goto L_0x0012
        L_0x0037:
            org.xbill.DNS.Cache$Element r0 = (org.xbill.DNS.Cache.Element) r0     // Catch:{ all -> 0x0048 }
            int r0 = r0.getType()     // Catch:{ all -> 0x0048 }
            if (r0 == r5) goto L_0x0041
            monitor-exit(r3)
            return
        L_0x0041:
            org.xbill.DNS.Cache$CacheMap r5 = r3.data     // Catch:{ all -> 0x0048 }
            r5.remove(r4)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r3)
            return
        L_0x0048:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Cache.removeElement(org.xbill.DNS.Name, int):void");
    }

    public synchronized void clearCache() {
        this.data.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addRecord(org.xbill.DNS.Record r3, int r4, java.lang.Object r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            org.xbill.DNS.Name r5 = r3.getName()     // Catch:{ all -> 0x0034 }
            int r0 = r3.getRRsetType()     // Catch:{ all -> 0x0034 }
            boolean r1 = org.xbill.DNS.Type.isRR(r0)     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x0011
            monitor-exit(r2)
            return
        L_0x0011:
            org.xbill.DNS.Cache$Element r5 = r2.findElement(r5, r0, r4)     // Catch:{ all -> 0x0034 }
            if (r5 != 0) goto L_0x0023
            org.xbill.DNS.Cache$CacheRRset r5 = new org.xbill.DNS.Cache$CacheRRset     // Catch:{ all -> 0x0034 }
            int r0 = r2.maxcache     // Catch:{ all -> 0x0034 }
            long r0 = (long) r0     // Catch:{ all -> 0x0034 }
            r5.<init>((org.xbill.DNS.Record) r3, (int) r4, (long) r0)     // Catch:{ all -> 0x0034 }
            r2.addRRset(r5, r4)     // Catch:{ all -> 0x0034 }
            goto L_0x0032
        L_0x0023:
            int r4 = r5.compareCredibility(r4)     // Catch:{ all -> 0x0034 }
            if (r4 != 0) goto L_0x0032
            boolean r4 = r5 instanceof org.xbill.DNS.Cache.CacheRRset     // Catch:{ all -> 0x0034 }
            if (r4 == 0) goto L_0x0032
            org.xbill.DNS.Cache$CacheRRset r5 = (org.xbill.DNS.Cache.CacheRRset) r5     // Catch:{ all -> 0x0034 }
            r5.addRR(r3)     // Catch:{ all -> 0x0034 }
        L_0x0032:
            monitor-exit(r2)
            return
        L_0x0034:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Cache.addRecord(org.xbill.DNS.Record, int, java.lang.Object):void");
    }

    public synchronized void addRRset(RRset rRset, int i) {
        CacheRRset cacheRRset;
        long ttl = rRset.getTTL();
        Name name = rRset.getName();
        int type = rRset.getType();
        Element findElement = findElement(name, type, 0);
        if (ttl != 0) {
            if (findElement != null && findElement.compareCredibility(i) <= 0) {
                findElement = null;
            }
            if (findElement == null) {
                if (rRset instanceof CacheRRset) {
                    cacheRRset = (CacheRRset) rRset;
                } else {
                    cacheRRset = new CacheRRset(rRset, i, (long) this.maxcache);
                }
                addElement(name, cacheRRset);
            }
        } else if (findElement != null && findElement.compareCredibility(i) <= 0) {
            removeElement(name, type);
        }
    }

    public synchronized void addNegative(Name name, int i, SOARecord sOARecord, int i2) {
        Name name2 = name;
        int i3 = i2;
        synchronized (this) {
            long ttl = sOARecord != null ? sOARecord.getTTL() : 0;
            int i4 = i;
            Element findElement = findElement(name, i, 0);
            if (ttl != 0) {
                if (findElement != null && findElement.compareCredibility(i3) <= 0) {
                    findElement = null;
                }
                if (findElement == null) {
                    addElement(name, new NegativeElement(name, i, sOARecord, i2, (long) this.maxncache));
                }
            } else if (findElement != null && findElement.compareCredibility(i3) <= 0) {
                removeElement(name, i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public synchronized SetResponse lookup(Name name, int i, int i2) {
        Name name2;
        int i3 = i;
        int i4 = i2;
        synchronized (this) {
            int labels = name.labels();
            int i5 = labels;
            while (i5 >= 1) {
                boolean z = i5 == 1;
                boolean z2 = i5 == labels;
                if (z) {
                    name2 = Name.root;
                    Name name3 = name;
                } else if (z2) {
                    name2 = name;
                    Name name4 = name2;
                } else {
                    name2 = new Name(name, labels - i5);
                }
                Object obj = this.data.get(name2);
                if (obj != null) {
                    if (z2 && i3 == 255) {
                        SetResponse setResponse = new SetResponse(6);
                        Element[] allElements = allElements(obj);
                        int i6 = 0;
                        for (Element element : allElements) {
                            if (element.expired()) {
                                removeElement(name2, element.getType());
                            } else if (element instanceof CacheRRset) {
                                if (element.compareCredibility(i4) >= 0) {
                                    setResponse.addRRset((CacheRRset) element);
                                    i6++;
                                }
                            }
                        }
                        if (i6 > 0) {
                            return setResponse;
                        }
                    }
                    if (z2) {
                        Element oneElement = oneElement(name2, obj, i3, i4);
                        if (oneElement != null && (oneElement instanceof CacheRRset)) {
                            SetResponse setResponse2 = new SetResponse(6);
                            setResponse2.addRRset((CacheRRset) oneElement);
                            return setResponse2;
                        } else if (oneElement != null) {
                            SetResponse setResponse3 = new SetResponse(2);
                            return setResponse3;
                        } else {
                            Element oneElement2 = oneElement(name2, obj, 5, i4);
                            if (oneElement2 != null && (oneElement2 instanceof CacheRRset)) {
                                SetResponse setResponse4 = new SetResponse(4, (CacheRRset) oneElement2);
                                return setResponse4;
                            }
                        }
                    } else {
                        Element oneElement3 = oneElement(name2, obj, 39, i4);
                        if (oneElement3 != null && (oneElement3 instanceof CacheRRset)) {
                            SetResponse setResponse5 = new SetResponse(5, (CacheRRset) oneElement3);
                            return setResponse5;
                        }
                    }
                    Element oneElement4 = oneElement(name2, obj, 2, i4);
                    if (oneElement4 != null && (oneElement4 instanceof CacheRRset)) {
                        SetResponse setResponse6 = new SetResponse(3, (CacheRRset) oneElement4);
                        return setResponse6;
                    } else if (!z2) {
                        continue;
                    } else if (oneElement(name2, obj, 0, i4) != null) {
                        SetResponse ofType = SetResponse.ofType(1);
                        return ofType;
                    }
                }
                i5--;
            }
            SetResponse ofType2 = SetResponse.ofType(0);
            return ofType2;
        }
    }

    public SetResponse lookupRecords(Name name, int i, int i2) {
        return lookup(name, i, i2);
    }

    private RRset[] findRecords(Name name, int i, int i2) {
        SetResponse lookupRecords = lookupRecords(name, i, i2);
        if (lookupRecords.isSuccessful()) {
            return lookupRecords.answers();
        }
        return null;
    }

    public RRset[] findRecords(Name name, int i) {
        return findRecords(name, i, 3);
    }

    public RRset[] findAnyRecords(Name name, int i) {
        return findRecords(name, i, 2);
    }

    private final int getCred(int i, boolean z) {
        if (i == 1) {
            return z ? 4 : 3;
        }
        if (i == 2) {
            return z ? 4 : 3;
        }
        if (i == 3) {
            return 1;
        }
        throw new IllegalArgumentException("getCred: invalid section");
    }

    private static void markAdditional(RRset rRset, Set set) {
        if (rRset.first().getAdditionalName() != null) {
            Iterator rrs = rRset.rrs();
            while (rrs.hasNext()) {
                Name additionalName = ((Record) rrs.next()).getAdditionalName();
                if (additionalName != null) {
                    set.add(additionalName);
                }
            }
        }
    }

    public SetResponse addMessage(Message message) {
        Message message2 = message;
        boolean flag = message.getHeader().getFlag(5);
        Record question = message.getQuestion();
        int rcode = message.getHeader().getRcode();
        boolean check = Options.check("verbosecache");
        if ((rcode != 0 && rcode != 3) || question == null) {
            return null;
        }
        Name name = question.getName();
        int type = question.getType();
        int dClass = question.getDClass();
        HashSet hashSet = new HashSet();
        int i = 1;
        RRset[] sectionRRsets = message2.getSectionRRsets(1);
        SetResponse setResponse = null;
        Name name2 = name;
        int i2 = 0;
        boolean z = false;
        while (i2 < sectionRRsets.length) {
            if (sectionRRsets[i2].getDClass() == dClass) {
                int type2 = sectionRRsets[i2].getType();
                Name name3 = sectionRRsets[i2].getName();
                int cred = getCred(i, flag);
                if ((type2 == type || type == 255) && name3.equals(name2)) {
                    addRRset(sectionRRsets[i2], cred);
                    if (name2 == name) {
                        SetResponse setResponse2 = setResponse == null ? new SetResponse(6) : setResponse;
                        setResponse2.addRRset(sectionRRsets[i2]);
                        setResponse = setResponse2;
                    }
                    markAdditional(sectionRRsets[i2], hashSet);
                    z = true;
                    i2++;
                    i = 1;
                } else if (type2 == 5 && name3.equals(name2)) {
                    addRRset(sectionRRsets[i2], cred);
                    if (name2 == name) {
                        setResponse = new SetResponse(4, sectionRRsets[i2]);
                    }
                    name2 = ((CNAMERecord) sectionRRsets[i2].first()).getTarget();
                } else if (type2 == 39 && name2.subdomain(name3)) {
                    addRRset(sectionRRsets[i2], cred);
                    if (name2 == name) {
                        setResponse = new SetResponse(5, sectionRRsets[i2]);
                    }
                    try {
                        name2 = name2.fromDNAME((DNAMERecord) sectionRRsets[i2].first());
                        i2++;
                        i = 1;
                    } catch (NameTooLongException unused) {
                    }
                }
            }
            i2++;
            i = 1;
        }
        int i3 = 2;
        RRset[] sectionRRsets2 = message2.getSectionRRsets(2);
        RRset rRset = null;
        RRset rRset2 = null;
        for (int i4 = 0; i4 < sectionRRsets2.length; i4++) {
            if (sectionRRsets2[i4].getType() == 6 && name2.subdomain(sectionRRsets2[i4].getName())) {
                rRset2 = sectionRRsets2[i4];
            } else if (sectionRRsets2[i4].getType() == 2 && name2.subdomain(sectionRRsets2[i4].getName())) {
                rRset = sectionRRsets2[i4];
            }
        }
        if (!z) {
            if (rcode == 3) {
                type = 0;
            }
            if (rcode == 3 || rRset2 != null || rRset == null) {
                addNegative(name2, type, rRset2 != null ? (SOARecord) rRset2.first() : null, getCred(2, flag));
                if (setResponse == null) {
                    if (rcode == 3) {
                        i3 = 1;
                    }
                    setResponse = SetResponse.ofType(i3);
                }
            } else {
                addRRset(rRset, getCred(2, flag));
                markAdditional(rRset, hashSet);
                if (setResponse == null) {
                    setResponse = new SetResponse(3, rRset);
                }
            }
        } else if (rcode == 0 && rRset != null) {
            addRRset(rRset, getCred(2, flag));
            markAdditional(rRset, hashSet);
        }
        SetResponse setResponse3 = setResponse;
        RRset[] sectionRRsets3 = message2.getSectionRRsets(3);
        for (int i5 = 0; i5 < sectionRRsets3.length; i5++) {
            int type3 = sectionRRsets3[i5].getType();
            if ((type3 == 1 || type3 == 28 || type3 == 38) && hashSet.contains(sectionRRsets3[i5].getName())) {
                addRRset(sectionRRsets3[i5], getCred(3, flag));
            }
        }
        if (check) {
            System.out.println("addMessage: " + setResponse3);
        }
        return setResponse3;
    }

    public void flushSet(Name name, int i) {
        removeElement(name, i);
    }

    public void flushName(Name name) {
        removeName(name);
    }

    public void setMaxNCache(int i) {
        this.maxncache = i;
    }

    public int getMaxNCache() {
        return this.maxncache;
    }

    public void setMaxCache(int i) {
        this.maxcache = i;
    }

    public int getMaxCache() {
        return this.maxcache;
    }

    public int getSize() {
        return this.data.size();
    }

    public int getMaxEntries() {
        return this.data.getMaxSize();
    }

    public void setMaxEntries(int i) {
        this.data.setMaxSize(i);
    }

    public int getDClass() {
        return this.dclass;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        synchronized (this) {
            for (Object allElements : this.data.values()) {
                Element[] allElements2 = allElements(allElements);
                for (Element append : allElements2) {
                    stringBuffer.append(append);
                    stringBuffer.append("\n");
                }
            }
        }
        return stringBuffer.toString();
    }
}
