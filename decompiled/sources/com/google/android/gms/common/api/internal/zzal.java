package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;

public final class zzal implements zzbh {
    /* access modifiers changed from: private */
    public final zzbi zzfxd;
    private boolean zzfxe = false;

    public zzal(zzbi zzbi) {
        this.zzfxd = zzbi;
    }

    public final void begin() {
    }

    public final void connect() {
        if (this.zzfxe) {
            this.zzfxe = false;
            this.zzfxd.zza((zzbj) new zzan(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zzfxe) {
            return false;
        }
        if (this.zzfxd.zzfvq.zzajt()) {
            this.zzfxe = true;
            for (zzdh zzalb : this.zzfxd.zzfvq.zzfyo) {
                zzalb.zzalb();
            }
            return false;
        }
        this.zzfxd.zzg((ConnectionResult) null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
        this.zzfxd.zzg((ConnectionResult) null);
        this.zzfxd.zzfzc.zzf(i, this.zzfxe);
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    /* access modifiers changed from: package-private */
    public final void zzaji() {
        if (this.zzfxe) {
            this.zzfxe = false;
            this.zzfxd.zzfvq.zzfyp.release();
            disconnect();
        }
    }

    public final <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        return zze(t);
    }

    public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        try {
            this.zzfxd.zzfvq.zzfyp.zzb(t);
            zzba zzba = this.zzfxd.zzfvq;
            Api.zze zze = zzba.zzfyj.get(t.zzahm());
            zzbq.checkNotNull(zze, "Appropriate Api was not requested.");
            if (zze.isConnected() || !this.zzfxd.zzfyy.containsKey(t.zzahm())) {
                boolean z = zze instanceof zzbz;
                Api.zzb zzb = zze;
                if (z) {
                    zzb = zzbz.zzanb();
                }
                @SuppressWarnings("unchecked")
                A client = (A) zzb;
                t.zzb(client);
                return t;
            }
            t.zzu(new Status(17));
            return t;
        } catch (DeadObjectException unused) {
            this.zzfxd.zza((zzbj) new zzam(this, this));
            return t;
        }
    }
}
