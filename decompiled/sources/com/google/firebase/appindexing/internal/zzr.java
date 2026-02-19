package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.zzaus;
import com.google.android.gms.internal.zzauz;

final class zzr extends zzt {
    private /* synthetic */ zza[] zzmom;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(zzq zzq, zza[] zzaArr) {
        super((zzr) null);
        this.zzmom = zzaArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaus zzaus) throws RemoteException {
        zzaus.zza(new zzauz.zzd(this), this.zzmom);
    }
}
