package com.google.android.gms.internal;

public class zzfik {
    private static final zzfhm zzpns = zzfhm.zzczf();
    private zzfgs zzpqq;
    private volatile zzfjc zzpqr;
    private volatile zzfgs zzpqs;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:7|8|9|10) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.gms.internal.zzfjc zzj(com.google.android.gms.internal.zzfjc r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.zzfjc r0 = r1.zzpqr
            if (r0 != 0) goto L_0x001c
            monitor-enter(r1)
            com.google.android.gms.internal.zzfjc r0 = r1.zzpqr     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            goto L_0x001c
        L_0x000b:
            r1.zzpqr = r2     // Catch:{ zzfie -> 0x0012 }
            com.google.android.gms.internal.zzfgs r0 = com.google.android.gms.internal.zzfgs.zzpnw     // Catch:{ zzfie -> 0x0012 }
            r1.zzpqs = r0     // Catch:{ zzfie -> 0x0012 }
            goto L_0x0009
        L_0x0012:
            r1.zzpqr = r2     // Catch:{ all -> 0x0019 }
            com.google.android.gms.internal.zzfgs r2 = com.google.android.gms.internal.zzfgs.zzpnw     // Catch:{ all -> 0x0019 }
            r1.zzpqs = r2     // Catch:{ all -> 0x0019 }
            goto L_0x0009
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0019 }
            throw r2
        L_0x001c:
            com.google.android.gms.internal.zzfjc r2 = r1.zzpqr
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfik.zzj(com.google.android.gms.internal.zzfjc):com.google.android.gms.internal.zzfjc");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfik)) {
            return false;
        }
        zzfik zzfik = (zzfik) obj;
        zzfjc zzfjc = this.zzpqr;
        zzfjc zzfjc2 = zzfik.zzpqr;
        return (zzfjc == null && zzfjc2 == null) ? toByteString().equals(zzfik.toByteString()) : (zzfjc == null || zzfjc2 == null) ? zzfjc != null ? zzfjc.equals(zzfik.zzj(zzfjc.zzczu())) : zzj(zzfjc2.zzczu()).equals(zzfjc2) : zzfjc.equals(zzfjc2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzfgs toByteString() {
        if (this.zzpqs != null) {
            return this.zzpqs;
        }
        synchronized (this) {
            if (this.zzpqs != null) {
                zzfgs zzfgs = this.zzpqs;
                return zzfgs;
            }
            this.zzpqs = this.zzpqr == null ? zzfgs.zzpnw : this.zzpqr.toByteString();
            zzfgs zzfgs2 = this.zzpqs;
            return zzfgs2;
        }
    }

    public final int zzhs() {
        if (this.zzpqs != null) {
            return this.zzpqs.size();
        }
        if (this.zzpqr != null) {
            return this.zzpqr.zzhs();
        }
        return 0;
    }

    public final zzfjc zzk(zzfjc zzfjc) {
        zzfjc zzfjc2 = this.zzpqr;
        this.zzpqq = null;
        this.zzpqs = null;
        this.zzpqr = zzfjc;
        return zzfjc2;
    }
}
