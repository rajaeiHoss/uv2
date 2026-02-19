package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbu implements zzcy, zzj {
    private Set<Scope> zzenh = null;
    /* access modifiers changed from: private */
    public final zzh<?> zzfsn;
    /* access modifiers changed from: private */
    public final Api.zze zzfwd;
    private zzan zzfxp = null;
    final /* synthetic */ zzbm zzfzq;
    /* access modifiers changed from: private */
    public boolean zzgad = false;

    public zzbu(zzbm zzbm, Api.zze zze, zzh<?> zzh) {
        this.zzfzq = zzbm;
        this.zzfwd = zze;
        this.zzfsn = zzh;
    }

    /* access modifiers changed from: private */
    public final void zzakp() {
        zzan zzan;
        if (this.zzgad && (zzan = this.zzfxp) != null) {
            this.zzfwd.zza(zzan, this.zzenh);
        }
    }

    public final void zzb(zzan zzan, Set<Scope> set) {
        if (zzan == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzh(new ConnectionResult(4));
            return;
        }
        this.zzfxp = zzan;
        this.zzenh = set;
        zzakp();
    }

    public final void zzf(ConnectionResult connectionResult) {
        this.zzfzq.mHandler.post(new zzbv(this, connectionResult));
    }

    public final void zzh(ConnectionResult connectionResult) {
        ((zzbo) this.zzfzq.zzfwg.get(this.zzfsn)).zzh(connectionResult);
    }
}
