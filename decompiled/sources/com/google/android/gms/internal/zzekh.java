package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

public final class zzekh implements zzeki {
    private boolean zzmya = false;

    private final void zzbvo() {
        zzepd.zzb(this.zzmya, "Transaction expected to already be in progress.");
    }

    public final void zza(zzegu zzegu, zzegi zzegi, long j) {
        zzbvo();
    }

    public final void zza(zzegu zzegu, zzenn zzenn, long j) {
        zzbvo();
    }

    public final void zza(zzelu zzelu, zzenn zzenn) {
        zzbvo();
    }

    public final void zza(zzelu zzelu, Set<zzemq> set) {
        zzbvo();
    }

    public final void zza(zzelu zzelu, Set<zzemq> set, Set<zzemq> set2) {
        zzbvo();
    }

    public final void zzbm(long j) {
        zzbvo();
    }

    public final List<zzejn> zzbvk() {
        return Collections.emptyList();
    }

    public final void zzbvn() {
        zzbvo();
    }

    public final void zzc(zzegu zzegu, zzegi zzegi) {
        zzbvo();
    }

    public final void zzd(zzegu zzegu, zzegi zzegi) {
        zzbvo();
    }

    public final <T> T zze(Callable<T> callable) {
        zzepd.zzb(!this.zzmya, "runInTransaction called when an existing transaction is already in progress.");
        this.zzmya = true;
        try {
            T call = callable.call();
            this.zzmya = false;
            return call;
        } catch (RuntimeException e) {
            this.zzmya = false;
            throw e;
        } catch (Error e2) {
            this.zzmya = false;
            throw e2;
        } catch (Exception e3) {
            this.zzmya = false;
            throw new RuntimeException(e3);
        }
    }

    public final zzelh zzf(zzelu zzelu) {
        return new zzelh(zzeng.zza(zzene.zzcco(), zzelu.zzcba()), false, false);
    }

    public final void zzg(zzelu zzelu) {
        zzbvo();
    }

    public final void zzh(zzelu zzelu) {
        zzbvo();
    }

    public final void zzi(zzelu zzelu) {
        zzbvo();
    }

    public final void zzk(zzegu zzegu, zzenn zzenn) {
        zzbvo();
    }
}
