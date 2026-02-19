package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

final class zzczm implements zzdif, Runnable {
    private /* synthetic */ zzczg zzkvy;

    private zzczm(zzczg zzczg) {
        this.zzkvy = zzczg;
    }

    /* synthetic */ zzczm(zzczg zzczg, zzczh zzczh) {
        this(zzczg);
    }

    public final void run() {
        zzbq.checkState(this.zzkvy.mState == 2);
        if (!zzdat.zzbja().zzmq(this.zzkvy.zzknc)) {
            String zzd = this.zzkvy.zzknc;
            StringBuilder sb = new StringBuilder(String.valueOf(zzd).length() + 24);
            sb.append("Refreshing container ");
            sb.append(zzd);
            sb.append("...");
            zzdal.v(sb.toString());
            ArrayList arrayList = new ArrayList();
            arrayList.add(0);
            this.zzkvy.zzkvq.zza(this.zzkvy.zzknc, this.zzkvy.zzkvo, this.zzkvy.zzkvn, arrayList, this, this.zzkvy.zzkvu);
        }
    }

    public final void zza(zzdin zzdin) {
        if (zzdin.getStatus() == Status.zzftq) {
            String zzd = this.zzkvy.zzknc;
            StringBuilder sb = new StringBuilder(String.valueOf(zzd).length() + 47);
            sb.append("Refreshed container ");
            sb.append(zzd);
            sb.append(". Reinitializing runtime...");
            zzdal.v(sb.toString());
            this.zzkvy.zzkvr.execute(new zzczn(this.zzkvy, zzdin));
            return;
        }
        zzczg zzczg = this.zzkvy;
        zzczg.zzbj(zzczg.zzkvu.zzbfx());
    }
}
