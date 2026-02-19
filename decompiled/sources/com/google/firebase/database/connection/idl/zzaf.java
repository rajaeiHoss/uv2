package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzeeq;
import com.google.android.gms.internal.zzeer;

final class zzaf implements zzeeq {
    private /* synthetic */ zzk zznde;

    zzaf(zzk zzk) {
        this.zznde = zzk;
    }

    public final void zza(boolean z, zzeer zzeer) {
        try {
            this.zznde.zza(z, new zzag(this, zzeer));
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
