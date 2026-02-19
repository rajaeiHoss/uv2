package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

final class zzczk implements zzdif, Runnable {
    private /* synthetic */ zzczg zzkvy;

    private zzczk(zzczg zzczg) {
        this.zzkvy = zzczg;
    }

    /* synthetic */ zzczk(zzczg zzczg, zzczh zzczh) {
        this(zzczg);
    }

    public final void run() {
        boolean z = true;
        if (this.zzkvy.mState != 1) {
            z = false;
        }
        zzbq.checkState(z);
        ArrayList arrayList = new ArrayList();
        boolean unused = this.zzkvy.zzkvx = false;
        if (zzdat.zzbja().zzmq(this.zzkvy.zzknc)) {
            arrayList.add(0);
        } else {
            zzczg zzczg = this.zzkvy;
            boolean unused2 = zzczg.zzkvx = zzczg.zzkvu.zzbin();
            if (!this.zzkvy.zzkvx) {
                arrayList.add(0);
                arrayList.add(1);
            } else {
                arrayList.add(1);
                arrayList.add(0);
            }
            arrayList.add(2);
        }
        this.zzkvy.zzkvq.zza(this.zzkvy.zzknc, this.zzkvy.zzkvo, this.zzkvy.zzkvn, arrayList, this, this.zzkvy.zzkvu);
    }

    public final void zza(zzdin zzdin) {
        if (zzdin.getStatus() == Status.zzftq) {
            this.zzkvy.zzkvr.execute(new zzczn(this.zzkvy, zzdin));
        } else {
            this.zzkvy.zzkvr.execute(new zzczj(this.zzkvy, (zzczh) null));
        }
    }
}
