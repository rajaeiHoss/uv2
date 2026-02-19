package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzeer;

final class zzag extends zzo {
    private /* synthetic */ zzeer zzndf;

    zzag(zzaf zzaf, zzeer zzeer) {
        this.zzndf = zzeer;
    }

    public final void onError(String str) throws RemoteException {
        this.zzndf.onError(str);
    }

    public final void zzpr(String str) throws RemoteException {
        this.zzndf.zzpr(str);
    }
}
