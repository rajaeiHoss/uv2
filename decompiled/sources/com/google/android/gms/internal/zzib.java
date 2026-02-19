package com.google.android.gms.internal;

import java.security.MessageDigest;

@zzabh
public final class zzib extends zzhs {
    private MessageDigest zzbaj;
    private final int zzbam;
    private final int zzban;

    public zzib(int i) {
        int i2 = i / 8;
        this.zzbam = i % 8 > 0 ? i2 + 1 : i2;
        this.zzban = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0064, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzy(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.mLock
            monitor-enter(r0)
            java.security.MessageDigest r1 = r9.zzhh()     // Catch:{ all -> 0x0065 }
            r9.zzbaj = r1     // Catch:{ all -> 0x0065 }
            r2 = 0
            if (r1 != 0) goto L_0x0010
            byte[] r10 = new byte[r2]     // Catch:{ all -> 0x0065 }
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            return r10
        L_0x0010:
            r1.reset()     // Catch:{ all -> 0x0065 }
            java.security.MessageDigest r1 = r9.zzbaj     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "UTF-8"
            java.nio.charset.Charset r3 = java.nio.charset.Charset.forName(r3)     // Catch:{ all -> 0x0065 }
            byte[] r10 = r10.getBytes(r3)     // Catch:{ all -> 0x0065 }
            r1.update(r10)     // Catch:{ all -> 0x0065 }
            java.security.MessageDigest r10 = r9.zzbaj     // Catch:{ all -> 0x0065 }
            byte[] r10 = r10.digest()     // Catch:{ all -> 0x0065 }
            int r1 = r10.length     // Catch:{ all -> 0x0065 }
            int r3 = r9.zzbam     // Catch:{ all -> 0x0065 }
            if (r1 <= r3) goto L_0x002e
            goto L_0x002f
        L_0x002e:
            int r3 = r10.length     // Catch:{ all -> 0x0065 }
        L_0x002f:
            byte[] r1 = new byte[r3]     // Catch:{ all -> 0x0065 }
            java.lang.System.arraycopy(r10, r2, r1, r2, r3)     // Catch:{ all -> 0x0065 }
            int r10 = r9.zzban     // Catch:{ all -> 0x0065 }
            r4 = 8
            int r10 = r10 % r4
            if (r10 <= 0) goto L_0x0063
            r5 = 0
        L_0x003d:
            if (r2 >= r3) goto L_0x004b
            if (r2 <= 0) goto L_0x0042
            long r5 = r5 << r4
        L_0x0042:
            byte r10 = r1[r2]     // Catch:{ all -> 0x0065 }
            r10 = r10 & 255(0xff, float:3.57E-43)
            long r7 = (long) r10     // Catch:{ all -> 0x0065 }
            long r5 = r5 + r7
            int r2 = r2 + 1
            goto L_0x003d
        L_0x004b:
            int r10 = r9.zzban     // Catch:{ all -> 0x0065 }
            int r10 = r10 % r4
            int r10 = 8 - r10
            long r2 = r5 >>> r10
            int r10 = r9.zzbam     // Catch:{ all -> 0x0065 }
            int r10 = r10 + -1
        L_0x0056:
            if (r10 < 0) goto L_0x0063
            r5 = 255(0xff, double:1.26E-321)
            long r5 = r5 & r2
            int r6 = (int) r5     // Catch:{ all -> 0x0065 }
            byte r5 = (byte) r6     // Catch:{ all -> 0x0065 }
            r1[r10] = r5     // Catch:{ all -> 0x0065 }
            long r2 = r2 >>> r4
            int r10 = r10 + -1
            goto L_0x0056
        L_0x0063:
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            return r1
        L_0x0065:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0065 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzib.zzy(java.lang.String):byte[]");
    }
}
