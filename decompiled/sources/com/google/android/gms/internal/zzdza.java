package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;

public abstract class zzdza {
    private static zzbhf zzehr = new zzbhf("BiChannelGoogleApi", "FirebaseAuth: ");
    private zzdzb zzmqf;

    private final zzdzb zzbtr() {
        zzdzb zzdzb;
        synchronized (this) {
            if (this.zzmqf == null) {
                this.zzmqf = zzbtq();
            }
            zzdzb = this.zzmqf;
        }
        return zzdzb;
    }

    private final GoogleApi zzc(zzdzf zzdzf) {
        zzdzb zzbtr = zzbtr();
        if (zzbtr.zzmqi.zzd(zzdzf)) {
            zzbhf zzbhf = zzehr;
            String valueOf = String.valueOf(zzbtr.zzmqh);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 43);
            sb.append("getGoogleApiForMethod() returned Fallback: ");
            sb.append(valueOf);
            zzbhf.zzf(sb.toString(), new Object[0]);
            return zzbtr.zzmqh;
        }
        zzbhf zzbhf2 = zzehr;
        String valueOf2 = String.valueOf(zzbtr.zzmqg);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 38);
        sb2.append("getGoogleApiForMethod() returned Gms: ");
        sb2.append(valueOf2);
        zzbhf2.zzf(sb2.toString(), new Object[0]);
        return zzbtr.zzmqg;
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zza(zzdzf<A, TResult> zzdzf) {
        return zzc(zzdzf).zza(zzdzf);
    }

    public final <TResult, A extends Api.zzb> Task<TResult> zzb(zzdzf<A, TResult> zzdzf) {
        return zzc(zzdzf).zzb(zzdzf);
    }

    /* access modifiers changed from: package-private */
    public abstract zzdzb zzbtq();
}
