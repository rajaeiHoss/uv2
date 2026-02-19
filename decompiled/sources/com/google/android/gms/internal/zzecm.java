package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzn;
import com.google.firebase.crash.FirebaseCrash;

public final class zzecm extends zzeck {
    private final Throwable zzdka;
    private final zzecy zzmuq;

    public zzecm(Context context, FirebaseCrash.zza zza, Throwable th, zzecy zzecy) {
        super(context, zza);
        this.zzdka = th;
        this.zzmuq = zzecy;
    }

    /* access modifiers changed from: protected */
    public final String getErrorMessage() {
        return "Failed to report caught exception";
    }

    public final /* bridge */ /* synthetic */ void run() {
        super.run();
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzect zzect) throws RemoteException {
        zzecy zzecy = this.zzmuq;
        if (zzecy != null) {
            zzecy.zza(false, System.currentTimeMillis());
        }
        zzect.zzaf(zzn.zzz(this.zzdka));
    }
}
