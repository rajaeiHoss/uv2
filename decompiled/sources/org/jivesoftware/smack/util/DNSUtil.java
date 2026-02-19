package org.jivesoftware.smack.util;

import java.util.Map;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.TextParseException;

public class DNSUtil {
    private static Map<String, HostAddress> ccache = new Cache(100, 600000);
    private static Map<String, HostAddress> scache = new Cache(100, 600000);

    private static HostAddress resolveSRV(String str) {
        String str2;
        int i = -1;
        try {
            Record[] run = new Lookup(str, 33).run();
            if (run == null) {
                return null;
            }
            int length = run.length;
            int i2 = Integer.MAX_VALUE;
            str2 = null;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                try {
                    SRVRecord sRVRecord = (SRVRecord) run[i3];
                    if (!(sRVRecord == null || sRVRecord.getTarget() == null)) {
                        int weight = (int) (((double) (sRVRecord.getWeight() * sRVRecord.getWeight())) * Math.random());
                        if (sRVRecord.getPriority() < i2) {
                            i2 = sRVRecord.getPriority();
                            str2 = sRVRecord.getTarget().toString();
                            i = sRVRecord.getPort();
                        } else if (sRVRecord.getPriority() == i2 && weight > i4) {
                            i2 = sRVRecord.getPriority();
                            str2 = sRVRecord.getTarget().toString();
                            i = sRVRecord.getPort();
                        }
                        i4 = weight;
                    }
                    i3++;
                } catch (NullPointerException unused) {
                }
            }
            if (str2 == null) {
                return null;
            }
            if (str2.endsWith(".")) {
                str2 = str2.substring(0, str2.length() - 1);
            }
            return new HostAddress(str2, i);
        } catch (NullPointerException | TextParseException unused2) {
            str2 = null;
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        r0 = resolveSRV("_xmpp-client._tcp." + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r0 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r0 = resolveSRV("_jabber._tcp." + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r0 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r0 = new org.jivesoftware.smack.util.DNSUtil.HostAddress(r3, 5222, (org.jivesoftware.smack.util.DNSUtil.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r1 = ccache;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        ccache.put(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.jivesoftware.smack.util.DNSUtil.HostAddress resolveXMPPDomain(java.lang.String r3) {
        /*
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r0 = ccache
            monitor-enter(r0)
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = ccache     // Catch:{ all -> 0x005b }
            boolean r1 = r1.containsKey(r3)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0017
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = ccache     // Catch:{ all -> 0x005b }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x005b }
            org.jivesoftware.smack.util.DNSUtil$HostAddress r1 = (org.jivesoftware.smack.util.DNSUtil.HostAddress) r1     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            return r1
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "_xmpp-client._tcp."
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = resolveSRV(r0)
            if (r0 != 0) goto L_0x0044
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "_jabber._tcp."
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = resolveSRV(r0)
        L_0x0044:
            if (r0 != 0) goto L_0x004e
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = new org.jivesoftware.smack.util.DNSUtil$HostAddress
            r1 = 5222(0x1466, float:7.318E-42)
            r2 = 0
            r0.<init>(r3, r1)
        L_0x004e:
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = ccache
            monitor-enter(r1)
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r2 = ccache     // Catch:{ all -> 0x0058 }
            r2.put(r3, r0)     // Catch:{ all -> 0x0058 }
            monitor-exit(r1)     // Catch:{ all -> 0x0058 }
            return r0
        L_0x0058:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0058 }
            throw r3
        L_0x005b:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.DNSUtil.resolveXMPPDomain(java.lang.String):org.jivesoftware.smack.util.DNSUtil$HostAddress");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0018, code lost:
        r0 = resolveSRV("_xmpp-server._tcp." + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r0 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r0 = resolveSRV("_jabber._tcp." + r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r0 != null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r0 = new org.jivesoftware.smack.util.DNSUtil.HostAddress(r3, 5269, (org.jivesoftware.smack.util.DNSUtil.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r1 = scache;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0050, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        scache.put(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0057, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.jivesoftware.smack.util.DNSUtil.HostAddress resolveXMPPServerDomain(java.lang.String r3) {
        /*
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r0 = scache
            monitor-enter(r0)
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = scache     // Catch:{ all -> 0x005b }
            boolean r1 = r1.containsKey(r3)     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0017
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = scache     // Catch:{ all -> 0x005b }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x005b }
            org.jivesoftware.smack.util.DNSUtil$HostAddress r1 = (org.jivesoftware.smack.util.DNSUtil.HostAddress) r1     // Catch:{ all -> 0x005b }
            if (r1 == 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            return r1
        L_0x0017:
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "_xmpp-server._tcp."
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = resolveSRV(r0)
            if (r0 != 0) goto L_0x0044
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "_jabber._tcp."
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = resolveSRV(r0)
        L_0x0044:
            if (r0 != 0) goto L_0x004e
            org.jivesoftware.smack.util.DNSUtil$HostAddress r0 = new org.jivesoftware.smack.util.DNSUtil$HostAddress
            r1 = 5269(0x1495, float:7.383E-42)
            r2 = 0
            r0.<init>(r3, r1)
        L_0x004e:
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r1 = scache
            monitor-enter(r1)
            java.util.Map<java.lang.String, org.jivesoftware.smack.util.DNSUtil$HostAddress> r2 = scache     // Catch:{ all -> 0x0058 }
            r2.put(r3, r0)     // Catch:{ all -> 0x0058 }
            monitor-exit(r1)     // Catch:{ all -> 0x0058 }
            return r0
        L_0x0058:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0058 }
            throw r3
        L_0x005b:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jivesoftware.smack.util.DNSUtil.resolveXMPPServerDomain(java.lang.String):org.jivesoftware.smack.util.DNSUtil$HostAddress");
    }

    public static class HostAddress {
        private String host;
        private int port;

        private HostAddress(String str, int i) {
            this.host = str;
            this.port = i;
        }

        public String getHost() {
            return this.host;
        }

        public int getPort() {
            return this.port;
        }

        public String toString() {
            return this.host + ":" + this.port;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof HostAddress)) {
                return false;
            }
            HostAddress hostAddress = (HostAddress) obj;
            if (this.host.equals(hostAddress.host) && this.port == hostAddress.port) {
                return true;
            }
            return false;
        }
    }
}
