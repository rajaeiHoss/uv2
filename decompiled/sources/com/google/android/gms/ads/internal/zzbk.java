package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzkk;
import java.lang.ref.WeakReference;

final class zzbk implements Runnable {
    private /* synthetic */ WeakReference zzasb;
    private /* synthetic */ zzbj zzasc;

    zzbk(zzbj zzbj, WeakReference weakReference) {
        this.zzasc = zzbj;
        this.zzasb = weakReference;
    }

    public final void run() {
        boolean unused = this.zzasc.zzary = false;
        zza zza = (zza) this.zzasb.get();
        if (zza != null) {
            zzkk zzb = this.zzasc.zzarx;
            if (zza.zzc(zzb)) {
                zza.zzb(zzb);
                return;
            }
            zzahw.zzcy("Ad is not visible. Not refreshing ad.");
            zza.zzanl.zzg(zzb);
        }
    }
}
