package com.google.android.gms.internal;

import com.google.android.gms.common.util.zze;

public final class zzbeo {
    public static final Object zzakj = new Object();
    private static final zzbei zzeus = new zzbei("RequestTracker");
    private final zze zzata;
    private long zzfle = -1;
    private long zzfok;
    private long zzfol = 0;
    private zzben zzfom;

    public zzbeo(zze zze, long j) {
        this.zzata = zze;
        this.zzfok = j;
    }

    private final void zzagz() {
        this.zzfle = -1;
        this.zzfom = null;
        this.zzfol = 0;
    }

    public final void clear() {
        synchronized (zzakj) {
            if (this.zzfle != -1) {
                zzagz();
            }
        }
    }

    public final boolean test(long j) {
        boolean z;
        synchronized (zzakj) {
            long j2 = this.zzfle;
            z = j2 != -1 && j2 == j;
        }
        return z;
    }

    public final void zza(long j, zzben zzben) {
        zzben zzben2;
        long j2;
        synchronized (zzakj) {
            zzben2 = this.zzfom;
            j2 = this.zzfle;
            this.zzfle = j;
            this.zzfom = zzben;
            this.zzfol = this.zzata.elapsedRealtime();
        }
        if (zzben2 != null) {
            zzben2.zzx(j2);
        }
    }

    public final boolean zzaha() {
        boolean z;
        synchronized (zzakj) {
            z = this.zzfle != -1;
        }
        return z;
    }

    public final boolean zzc(long j, int i, Object obj) {
        boolean z;
        zzben zzben;
        synchronized (zzakj) {
            long j2 = this.zzfle;
            z = true;
            if (j2 == -1 || j2 != j) {
                zzben = null;
                z = false;
            } else {
                zzeus.zzb("request %d completed", Long.valueOf(j2));
                zzben = this.zzfom;
                zzagz();
            }
        }
        if (zzben != null) {
            zzben.zza(j, i, obj);
        }
        return z;
    }

    public final boolean zzd(long j, int i) {
        boolean z;
        long j2;
        zzben zzben;
        synchronized (zzakj) {
            long j3 = this.zzfle;
            z = true;
            if (j3 == -1 || j - this.zzfol < this.zzfok) {
                j2 = 0;
                zzben = null;
                z = false;
            } else {
                zzeus.zzb("request %d timed out", Long.valueOf(j3));
                j2 = this.zzfle;
                zzben = this.zzfom;
                zzagz();
            }
        }
        if (zzben != null) {
            zzben.zza(j2, i, (Object) null);
        }
        return z;
    }
}
