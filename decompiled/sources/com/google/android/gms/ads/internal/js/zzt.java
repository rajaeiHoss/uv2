package com.google.android.gms.ads.internal.js;

import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzcv;
import java.util.Map;

final class zzt implements com.google.android.gms.ads.internal.gmsg.zzt<zzaj> {
    private /* synthetic */ zzc zzcft;
    private /* synthetic */ zzn zzcfu;
    private /* synthetic */ zzcv zzcfv;
    private /* synthetic */ zzakf zzcfw;

    zzt(zzn zzn, zzcv zzcv, zzc zzc, zzakf zzakf) {
        this.zzcfu = zzn;
        this.zzcfv = zzcv;
        this.zzcft = zzc;
        this.zzcfw = zzakf;
    }

    public final void zza(zzaj zzaj, Map<String, String> map) {
        synchronized (this.zzcfu.mLock) {
            zzahw.zzcy("JS Engine is requesting an update");
            if (this.zzcfu.zzcfl == 0) {
                zzahw.zzcy("Starting reload.");
                int unused = this.zzcfu.zzcfl = 2;
                this.zzcfu.zza(this.zzcfv);
            }
            this.zzcft.zzb("/requestReload", (com.google.android.gms.ads.internal.gmsg.zzt) this.zzcfw.get());
        }
    }
}
