package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbt;

final class zzadp implements Runnable {
    private /* synthetic */ zzacf zzcwe;
    private /* synthetic */ zzacq zzcwf;
    private /* synthetic */ zzadn zzcwg;

    zzadp(zzadn zzadn, zzacf zzacf, zzacq zzacq) {
        this.zzcwg = zzadn;
        this.zzcwe = zzacf;
        this.zzcwf = zzacq;
    }

    public final void run() {
        zzacj zzacj;
        try {
            zzacj = this.zzcwg.zzb(this.zzcwe);
        } catch (Exception e) {
            zzbt.zzep().zza(e, "AdRequestServiceImpl.loadAdAsync");
            zzahw.zzc("Could not fetch ad response due to an Exception.", e);
            zzacj = null;
        }
        if (zzacj == null) {
            zzacj = new zzacj(0);
        }
        try {
            this.zzcwf.zza(zzacj);
        } catch (RemoteException e2) {
            zzahw.zzc("Fail to forward ad response.", e2);
        }
    }
}
