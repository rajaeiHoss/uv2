package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import java.util.HashSet;
import java.util.Set;

final class zzcpi extends zzcsm {
    private final zzci<EndpointDiscoveryCallback> zzhov;
    private final Set<String> zzjxw = new HashSet();

    zzcpi(zzci<EndpointDiscoveryCallback> zzci) {
        this.zzhov = (zzci) zzbq.checkNotNull(zzci);
    }

    /* access modifiers changed from: package-private */
    public final synchronized void shutdown() {
        for (String zzcpl : this.zzjxw) {
            this.zzhov.zza(new zzcpl(this, zzcpl));
        }
        this.zzjxw.clear();
    }

    public final synchronized void zza(zzctl zzctl) {
        this.zzjxw.add(zzctl.zzbdi());
        this.zzhov.zza(new zzcpj(this, zzctl));
    }

    public final synchronized void zza(zzctn zzctn) {
        this.zzjxw.remove(zzctn.zzbdi());
        this.zzhov.zza(new zzcpk(this, zzctn));
    }

    public final void zza(zzctx zzctx) {
    }
}
