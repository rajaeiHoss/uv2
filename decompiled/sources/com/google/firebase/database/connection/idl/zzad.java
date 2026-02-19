package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzeeq;

final class zzad extends zzl {
    private /* synthetic */ zzeeq zzndc;

    zzad(zzeeq zzeeq) {
        this.zzndc = zzeeq;
    }

    public final void zza(boolean z, zzn zzn) throws RemoteException {
        this.zzndc.zza(z, new zzae(this, zzn));
    }
}
