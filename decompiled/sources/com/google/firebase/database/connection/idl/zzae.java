package com.google.firebase.database.connection.idl;

import android.os.RemoteException;
import com.google.android.gms.internal.zzeer;

final class zzae implements zzeer {
    private /* synthetic */ zzn zzndd;

    zzae(zzad zzad, zzn zzn) {
        this.zzndd = zzn;
    }

    public final void onError(String str) {
        try {
            this.zzndd.onError(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public final void zzpr(String str) {
        try {
            this.zzndd.zzpr(str);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
