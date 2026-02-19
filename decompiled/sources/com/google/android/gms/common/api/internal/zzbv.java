package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzan;
import java.util.Collections;

final class zzbv implements Runnable {
    private /* synthetic */ ConnectionResult zzgab;
    private /* synthetic */ zzbu zzgae;

    zzbv(zzbu zzbu, ConnectionResult connectionResult) {
        this.zzgae = zzbu;
        this.zzgab = connectionResult;
    }

    public final void run() {
        if (this.zzgab.isSuccess()) {
            boolean unused = this.zzgae.zzgad = true;
            if (this.zzgae.zzfwd.zzacc()) {
                this.zzgae.zzakp();
            } else {
                this.zzgae.zzfwd.zza((zzan) null, Collections.emptySet());
            }
        } else {
            ((zzbo) this.zzgae.zzfzq.zzfwg.get(this.zzgae.zzfsn)).onConnectionFailed(this.zzgab);
        }
    }
}
