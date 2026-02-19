package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzahw;
import com.google.android.gms.internal.zzro;

final class zzu implements Runnable {
    private /* synthetic */ zzq zzaog;
    private /* synthetic */ zzro zzaoj;

    zzu(zzq zzq, zzro zzro) {
        this.zzaog = zzq;
        this.zzaoj = zzro;
    }

    public final void run() {
        try {
            this.zzaog.zzanm.zzaup.get(this.zzaoj.getCustomTemplateId()).zzb(this.zzaoj);
        } catch (RemoteException e) {
            zzahw.zzc("Could not call onCustomTemplateAdLoadedListener.onCustomTemplateAdLoaded().", e);
        }
    }
}
