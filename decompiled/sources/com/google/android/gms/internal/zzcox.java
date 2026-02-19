package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import java.util.HashSet;
import java.util.Set;

final class zzcox extends zzcse {
    private final zzci<ConnectionLifecycleCallback> zzhov;
    private final Set<String> zzjxm = new HashSet();
    private final Set<String> zzjxn = new HashSet();

    zzcox(zzci<ConnectionLifecycleCallback> zzci) {
        this.zzhov = (zzci) zzbq.checkNotNull(zzci);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void shutdown() {
        for (String zzcpc : this.zzjxm) {
            this.zzhov.zza(new zzcpc(this, zzcpc));
        }
        this.zzjxm.clear();
        for (String zzcpd : this.zzjxn) {
            this.zzhov.zza(new zzcpd(this, zzcpd));
        }
        this.zzjxn.clear();
    }

    public final void zza(zzcsz zzcsz) {
        this.zzhov.zza(new zzcpb(this, zzcsz));
    }

    public final synchronized void zza(zzctb zzctb) {
        this.zzjxm.add(zzctb.zzbde());
        this.zzhov.zza(new zzcoy(this, zzctb));
    }

    public final synchronized void zza(zzcth zzcth) {
        this.zzjxm.remove(zzcth.zzbde());
        Status zzeq = zzcov.zzcm(zzcth.getStatusCode());
        if (zzeq.isSuccess()) {
            this.zzjxn.add(zzcth.zzbde());
        }
        this.zzhov.zza(new zzcoz(this, zzcth, zzeq));
    }

    public final synchronized void zza(zzctj zzctj) {
        this.zzjxn.remove(zzctj.zzbde());
        this.zzhov.zza(new zzcpa(this, zzctj));
    }
}
